package com.pzj.project.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.pzj.project.common.util.ThreadLocalUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 处理新增和更新的基础数据填充，配合BaseEntity和MyBatisPlusConfig使用
 */
@Component
public class MetaHandler implements MetaObjectHandler {
 
    /**
     * 新增数据执行
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Object userId = ThreadLocalUtil.get("userId");
        if(userId != null){
            this.setFieldValByName("creator", userId.toString(),metaObject);
            this.setFieldValByName("updator", userId.toString(), metaObject);
            this.setFieldValByName("userId", userId.toString(),metaObject);
            ThreadLocalUtil.remove("userId");
        }
        this.setFieldValByName("createTime", Timestamp.valueOf(LocalDateTime.now()), metaObject);
        this.setFieldValByName("updateTime", Timestamp.valueOf(LocalDateTime.now()), metaObject);
        this.setFieldValByName("isDeleted", 0, metaObject);

    }

    /**
     * 更新数据执行
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Object userId = ThreadLocalUtil.get("userId");
        if(userId != null ){
            this.setFieldValByName("updator", userId.toString(),metaObject);
            ThreadLocalUtil.remove("userId");
        }
        this.setFieldValByName("updateTime", Timestamp.valueOf(LocalDateTime.now()), metaObject);
    }
 
}