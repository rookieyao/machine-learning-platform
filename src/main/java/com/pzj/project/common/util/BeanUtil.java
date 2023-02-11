package com.pzj.project.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.*;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;


/**
 * @Author yaoqi
 * @Description
 * @Date 2022/6/7 9:54
 **/
public class BeanUtil {

    private final static Logger log = LoggerFactory.getLogger(BeanUtil.class);
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 忽略null值
     * @param src
     * @param target
     */
    public static void CopyProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    /**
     * Bean转Map 包含父类信息
     * @param bean
     * @return
     */
    public static Map<String, Object> transBean2Map(Object bean) {
        if (bean == null) {
            return null;
        }
        Map<String,Object>map = new HashMap<>();
        final BeanWrapper src = new BeanWrapperImpl(bean);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(PropertyDescriptor pd : pds) {
            map.put(pd.getName(),src.getPropertyValue(pd.getName()));
        }
       return map;
    }


    /**
     * Bean转Map 不包含父类信息
     * @param bean
     * @return
     */
    public static Map<String, Object> beanToMap(Object bean) {
        if (bean == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<>();
        // 得到类对象
        Class userCla = bean.getClass();
        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            map.put(f.getName(),"");
            f.setAccessible(true); // 设置些属性是可以访问的
            Object val = new Object();
            try {
                val = f.get(bean);
                // 得到此属性的值
                map.put(f.getName(), val);// 设置键值
            } catch (IllegalArgumentException e) {
                log.info("bean 转 map 异常：{}", e.getMessage());
            } catch (IllegalAccessException e) {
                log.info("bean 转 map 异常：{}", e.getMessage());
            }
        }
        return map;
    }


    public static  <T> List<T> objToBeanList (Object src, Class<T> clazz){
        if(null == src){
            return null;
        }

        String json = JSONObject.toJSONString(src);
        List<T> ts = JSONObject.parseArray(json, clazz);
        return ts;
    }


    public static <T> T objToBean (Object src,Class<T> clazz){
        if(null == src){
            return null;
        }

        String json = JSONObject.toJSONString(src);
        T t = JSONObject.parseObject(json, clazz);
        return t;
    }
    public static <T> List<T> objToList(List src, Class<T> tClass) {

        if(!CollectionUtils.isEmpty(src)){
            List<T> list = new ArrayList<>();
            src.forEach(
                    x->{
                        try {
                            T t = tClass.newInstance();
                            BeanUtil.CopyProperties(x,t);
                            list.add(t);
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
            );
            return list;
        }
        return null;
    }

    /**
     * ps：Test2 test2 = CopyBeanUtils.springCopyProperties(i1, Test2.class);
     *  spring 的 BeanUtils.copyProperties(source, target) 的封装
     * @param source      原对象
     * @param targetClass 目标对象class
     * @param <T>
     * @return copy完后的对象
     */
    public static <T> T springCopyProperties(Object source, Class<T> targetClass) {
        Assert.notNull(source, "targetClass must not be null");

        T target = BeanUtils.instantiateClass(targetClass);
        BeanUtils.copyProperties(source, target);
        return target;
    }

    /**
     * ps：Test2 test2 = CopyBeanUtils.copyProperties(i1, Test2.class);
     *
     * @param source      原对象
     * @param targetClass 目标对象class
     * @param <T>
     * @return copy完后的对象
     */
    public static <T> T copyProperties(Object source, Class<T> targetClass) {
        Assert.notNull(source, "targetClass must not be null");

        T target = BeanUtils.instantiateClass(targetClass);
        copyProperties(source, target);
        return target;
    }

    public static void copyProperties(Object source, Object target) throws BeansException {
        copyProperties(source, target, null, (String[]) null);
    }

    public static void copyProperties(Object source, Object target, Class<?> editable) throws BeansException {
        copyProperties(source, target, editable, (String[]) null);
    }

    /**
     * @param source           原对象
     * @param target           目标对象
     * @param ignoreProperties 排除某些不需要复制的属性
     * @throws BeansException
     */
    public static void copyProperties(Object source, Object target, String... ignoreProperties) throws BeansException {
        copyProperties(source, target, null, ignoreProperties);
    }

    private static void copyProperties(Object source, Object target, @Nullable Class<?> editable, @Nullable String... ignoreProperties) throws BeansException {

        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        Class<?> actualEditable = target.getClass();
        if (editable != null) {
            if (!editable.isInstance(target)) {
                throw new IllegalArgumentException("Target class [" + target.getClass().getName() +
                        "] not assignable to Editable class [" + editable.getName() + "]");
            }
            actualEditable = editable;
        }
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

        HashMap<String, String> copySourceNameMap = new HashMap<>();

        for (Field field : actualEditable.getDeclaredFields()) {
            CopySourceName annotation = field.getAnnotation(CopySourceName.class);
            if (annotation != null) {
                copySourceNameMap.put(field.getName(), annotation.value());
            }
        }

        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            String name = targetPd.getName();
            String sourceName = copySourceNameMap.get(name);
            if (sourceName != null) name = sourceName;
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(name))) {
                PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), name);
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null &&
                            ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object value = readMethod.invoke(source);
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            writeMethod.invoke(target, value);
                        } catch (Throwable ex) {
                            throw new FatalBeanException(
                                    "Could not copy property '" + name + "' from source to target", ex);
                        }
                    }
                }
            }
        }
    }
}