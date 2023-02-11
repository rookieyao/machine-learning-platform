package com.pzj.project.common.util;

import com.github.pagehelper.PageInfo;
import com.pzj.project.common.CommonPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

public class CommonUtil {


    public static String getUUidStr(){
        String str = UUID.randomUUID().toString().replaceAll("-", "");
        return str;
    }

    /**
     *
     * @param file
     * @param contentList 保存每行的内容
     */
    public static void beginLoadFile(File file, List<String> contentList) {
        FileInputStream fis = null;
        try {

             fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            while (StringUtils.isNotBlank(line = br.readLine())) {
                contentList.add(line);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(fis !=null){
                    fis.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void beginLoadFile(File file, StringBuilder stringBuilder) {
        FileInputStream fis = null;
        try {

            fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            while (StringUtils.isNotBlank(line = br.readLine())) {
                stringBuilder.append(line);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(fis !=null){
                    fis.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void beginLoadFile(MultipartFile file, List<String> contentList) {
        InputStream inputStream = null;
        try {

            inputStream= file.getResource().getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while (StringUtils.isNotBlank(line = br.readLine())) {
                contentList.add(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(inputStream !=null){

                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Author yaoqi
     * @Description 加载关键词组文件内容
     * @Date 2022/5/25 20:13
     * @Param [multipartFile]
     * @return void
     **/
    public static Map<String, Integer> beginLoadKeyWordFile(MultipartFile multipartFile, Map<String, Integer> keyWordsCountMap) {


        InputStream inputStream = null;
        try {

            inputStream= multipartFile.getResource().getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            String line;
            while (StringUtils.isNotBlank(line = br.readLine())) {
                keyWordsCountMap.put(line,0);
            }

            return keyWordsCountMap;

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(inputStream !=null){

                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 分页方法
     * @param data
     * @param paging
     * @param <T>
     * @return
     */
    public static <T> PageInfo<T> pageCache(List<T> data, CommonPage paging) {
        //要处理为空的时候的情况
        if (CollectionUtils.isEmpty(data)) {
            PageInfo<T> objectPageInfo = new PageInfo<>();
            objectPageInfo.setPageSize(paging.getPageSize());
            objectPageInfo.setTotal(0);
            objectPageInfo.setPages(1);
            objectPageInfo.setPageNum(paging.getPageNum());
            objectPageInfo.setList(Collections.emptyList());

            return objectPageInfo;
        }

        PageInfo<T> pageInfo = new PageInfo<>(data);
        List<T> temps = new ArrayList<>();
        int pageNum = paging.getPageNum();
        Map<Integer, List<T>> map = new HashMap<>();
        int index = 1;
        for (int i = 0; i < data.size(); i++) {
            temps.add(data.get(i));
            if ((i + 1) % paging.getPageSize() == 0) {
                map.put(index++, temps);
                temps = new ArrayList<>();
            }
            if (i == (data.size() - 1)) {
                map.put(index, temps);
            }
        }
        if (map.get(pageNum).size() == 0) {
            pageInfo.setPageSize(paging.getPageSize());
            pageInfo.setTotal(data.size());
            pageInfo.setPages(--index);
            pageInfo.setPageNum(--pageNum);
            pageInfo.setList(map.get(pageNum));
        } else {
            pageInfo.setPageSize(paging.getPageSize());
            pageInfo.setTotal(data.size());
            pageInfo.setPages(index);
            pageInfo.setPageNum(pageNum);
            pageInfo.setList(map.get(pageNum));
        }
        return pageInfo;
    }

}
