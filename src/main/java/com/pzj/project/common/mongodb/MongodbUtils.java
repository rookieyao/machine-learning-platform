//package com.pzj.project.common.mongodb;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.mongodb.*;
//import com.mongodb.client.*;
//import com.mongodb.client.model.Filters;
//import com.mongodb.client.result.DeleteResult;
//import com.pzj.project.common.config.RedisConfig;
//import com.pzj.project.common.util.DateUtil;
//import org.apache.commons.lang3.StringUtils;
//import org.bson.Document;
//import org.bson.conversions.Bson;
//import org.bson.types.ObjectId;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//import java.io.File;
//import java.time.LocalDateTime;
//import java.util.*;
//
///**
// * @ClassName MongodbUtils
// * @Description
// * @Author yaoqi
// * @Date 2022/5/25 17:08
// * @Version 1.0
// **/
//@Component
//public class MongodbUtils {
//
//    Logger logger = LoggerFactory.getLogger(MongodbUtils.class);
//
//    @Value("${spring.profiles.active}")
//    private String active;
//
////    @Value("${spring.redis.dbIndex}")
////    private Integer redisDbIndex;
//
//    @Value("${mongodb.current.addr}")
//    private String mongodbAddr;
//
//    @Value("${mongodb.current.user}")
//    private String user;
//
//    @Value("${mongodb.current.password}")
//    private String password;
//
//    @Value("${mongodb.mongoDumpShellPath}")
//    private String mongoDumpShellPath;
//
//    @Value("${mongodb.mongoDumpShellFileName}")
//    private String mongoDumpShellFileName;
//
//
//    @Resource
//    RedisConfig redisConfig;
//
//    private final static Logger log = LoggerFactory.getLogger(MongodbUtils.class);
//
//    private static MongoClient mongoClient;
//
//    private static MongoClient onLinemongoClient;
//
//    private static String dumpShellPath;
//    private static String dumpShellFileName;
//
//    static String offLineDbName = "ann";
//    static String onLineDbName = "ann_online";
//    static String collName = "task_data";
//
//    @PostConstruct
//    public void initClient(){
//
//        this.dumpShellPath = mongoDumpShellPath;
//        this.dumpShellFileName = mongoDumpShellFileName;
//
//        log.info("===============MongoDBUtil初始化========================");
//        //String mongodbAddr = "192.168.0.100:27017";
//        //String mongodbAddr = "192.168.110.165:27017";
//        //String databaseName = "ann_dev";
//        //String user = "root";
//        //String pswd = "QJcXOCQcOYW7";
//        //String uri = String.format("mongodb://%s:%s@%s", user, pswd, mongodbAddr);
//        logger.info("当前环境为:{}",active);
//        String uri = null;
//        if(StringUtils.equals("dev", active)){ //
//            uri = String.format("mongodb://%s:%s@%s", user, password, mongodbAddr);
//
//        }else if(StringUtils.equals("prod", active)) {
//
//            uri = String.format("mongodb://%s", mongodbAddr);
//        }else if(StringUtils.equals("fat", active)) {
//
//            uri = String.format("mongodb://%s:%s@%s", user, password, mongodbAddr);
//        }else {
//            throw new RuntimeException("unknown active:"+active);
//        }
//        log.info("初始化的mongodb的连接信息为:{}",uri);
//
//        mongoClient = new MongoClient(new MongoClientURI(uri));
//
//        MongoClientOptions.Builder options = new MongoClientOptions.Builder();
//        options.cursorFinalizerEnabled(true);
//        options.connectionsPerHost(300);
//        options.connectTimeout(30000);
//        options.maxWaitTime(5000);
//        options.socketTimeout(0);
//        options.threadsAllowedToBlockForConnectionMultiplier(5000);
//        options.writeConcern(WriteConcern.SAFE);
//        options.build();
//        log.info("===============MongoDBUtil初始化结束========================");
//    }
//
//    //static {
//    //    log.info("===============MongoDBUtil初始化========================");
//    //    //String mongodbAddr = "192.168.0.100:27017";
//    //    //String mongodbAddr = "127.0.0.1:27017";
//    //    String mongodbAddr = "192.168.110.165:27017";
//    //    String databaseName = "ann_dev";
//    //    String user = "root";
//    //    String pswd = "QJcXOCQcOYW7";
//    //    //String uri = String.format("mongodb://%s:%s@%s", user, pswd, mongodbAddr);
//    //    String uri = String.format("mongodb://%s", mongodbAddr);
//    //    log.info("初始化的mongodb的连接信息为:{}",uri);
//    //    mongoClient = new MongoClient(new MongoClientURI(uri));
//    //
//    //    MongoClientOptions.Builder options = new MongoClientOptions.Builder();
//    //    options.cursorFinalizerEnabled(true);
//    //    options.connectionsPerHost(300);
//    //    options.connectTimeout(30000);
//    //    options.maxWaitTime(5000);
//    //    options.socketTimeout(0);
//    //    options.threadsAllowedToBlockForConnectionMultiplier(5000);
//    //    options.writeConcern(WriteConcern.SAFE);
//    //    options.build();
//    //
//    //
//    //}
//
//
//    public MongoClient getMongoClient(String mongoUri){
//
//        MongoClient mongoClient = new MongoClient(new MongoClientURI(mongoUri));
//        MongoClientOptions.Builder options = new MongoClientOptions.Builder();
//        options.cursorFinalizerEnabled(true);
//        options.connectionsPerHost(300);
//        options.connectTimeout(30000);
//        options.maxWaitTime(5000);
//        options.socketTimeout(0);
//        options.threadsAllowedToBlockForConnectionMultiplier(5000);
//        options.writeConcern(WriteConcern.SAFE);
//        options.build();
//        return mongoClient;
//    }
//
//    public static void mai222n(String[] args) {
//        String mongodbAddr = "192.168.0.100:27017";
//        //String mongodbAddr = "127.0.0.1:27017";
//        String databaseName = "ann_dev";
//        String user = "root";
//        String pswd = "QJcXOCQcOYW7";
//        String uri = String.format("mongodb://%s:%s@%s", user, pswd, mongodbAddr);
//        System.out.println(uri);
//    }
//
//    // ------------------------------------共用方法---------------------------------------------------
//
//    /**
//     * 获取DB实例 - 指定DB
//     *
//     * @param dbName
//     * @return
//     */
//    public static MongoDatabase getDB(String dbName) {
//        if (dbName != null && !"".equals(dbName)) {
//            return mongoClient.getDatabase(dbName);
//        }
//        return null;
//    }
//
//    /**
//     * 获取collection对象 - 指定Collection
//     *
//     * @param collName
//     * @return
//     */
//    public static MongoCollection<Document> getCollection(String dbName, String collName) {
//        if (null == collName || "".equals(collName)) {
//            return null;
//        }
//        if (null == dbName || "".equals(dbName)) {
//            return null;
//        }
//        return mongoClient.getDatabase(dbName).getCollection(collName);
//    }
//
//    /**
//     * 查询DB下的所有表名
//     */
//    public static List<String> getAllCollections(String dbName) {
//        MongoIterable<String> colls = getDB(dbName).listCollectionNames();
//        List<String> list = new ArrayList<String>();
//        for (String s : colls) {
//            list.add(s);
//        }
//        return list;
//    }
//
//    /**
//     * 获取所有数据库名称列表
//     *
//     * @return
//     */
//    public static MongoIterable<String> getAllDBNames() {
//        return mongoClient.listDatabaseNames();
//    }
//
//    /**
//     * 删除一个数据库
//     */
//    public static void dropDB(String dbName) {
//        getDB(dbName).drop();
//    }
//
//    /**
//     * 查找对象 - 根据主键_id
//     *
//     * @param coll
//     * @param id
//     * @return
//     */
//    public static Document findById(MongoCollection<Document> coll, String id) {
//        ObjectId idobj = null;
//        try {
//            idobj = new ObjectId(id);
//        } catch (Exception e) {
//            return null;
//        }
//        return coll.find(Filters.eq("_id", idobj)).first();
//    }
//
//
//    /**
//     * @Author yaoqi
//     * @Description 通过taskType获得相应的collection
//     * @Date 2022/6/20 17:08
//     * @Param [taskType]
//     * @return com.mongodb.client.MongoCollection<org.bson.Document>
//     **/
//    private static MongoCollection<Document> getMongoCollection(String taskType, String collName){
//        MongoCollection<Document> coll = null;
//        if(StringUtils.equals("8085", taskType)){ // 线下  ann
//            coll = MongodbUtils.getCollection(offLineDbName,collName);
//        }else if(StringUtils.equals("8086", taskType)){ // 线上 ann_online
//            coll = MongodbUtils.getCollection(onLineDbName,collName);
//        }else {
//            throw new RuntimeException("unknown taskType:"+taskType);
//        }
//
//        return coll;
//    }
//
//    /**
//     * 根据taskId 查询记录
//     * todo 这里需要根据 taskType 区分是线上还是线下
//     **/
//    public static Map<String,String> findByTaskId(String taskId, String taskType) {
//
//
//        MongoCollection<Document> coll = getMongoCollection(taskType, collName);
//
//        // 这里最好提前声明下容量，扩容对性能有损耗
//        Map<String,String> contentMap = new HashMap<>();
//        MongoCursor<Document> list = MongodbUtils.find(coll, Filters.and(Filters.eq("tid", taskId)));
//        while (list.hasNext()) {
//            Document document = list.next();
//            String s = document.toJson();
//            JSONObject jsonObject = JSONObject.parseObject(s);
//            contentMap.put(jsonObject.getString("content"),"1"); // 这里设置1的目的是为了节省内存，所以随便设置一个值，主要是为了使用map的key
//        }
//
//        return contentMap;
//    }
//
//    public static String getTaskNameByTaskId(String taskId, String taskType) {
//
//        MongoCollection<Document> coll = getMongoCollection(taskType, "tasks");
//        Document byId = MongodbUtils.findById(coll, taskId);
//        String s = byId.toJson();
//        JSONObject jsonObject = JSONObject.parseObject(s);
//        return jsonObject.getString("name");
//    }
//
//    public static String getMappingDataByTaskId(String ytLabelTaskId, String ytLabelTaskType) {
//
//        MongoCollection<Document> coll = getMongoCollection(ytLabelTaskType, "cem_tag_mapping");
//        MongoCursor<Document> list = MongodbUtils.find(coll, Filters.and(Filters.eq("taskId", ytLabelTaskId)));
//
//        Map<String, String> projectMap = new HashMap<>();
//        while (list.hasNext()){
//            Document next = list.next();
//            String s = next.toJson();
//            JSONObject jsonObject = JSONObject.parseObject(s);
//            return jsonObject.getString("mappingData");
//        }
//        return null;
//    }
//
//    /**
//     * @Author yaoqi
//     * @Description 每一个tag都有一个其对应的type
//     * @Date 2022/10/10 17:30
//     * @Param [taskId, taskType]
//     * @return java.util.Map<java.lang.String,java.lang.String>
//     **/
//    public static Map<String,String> getTagTypeMapByTaskId(String taskId, String taskType, Integer projectType) {
//
//        Map<String, String> configMap = new HashMap<>();
//
//        MongoCollection<Document> coll = getMongoCollection(taskType, "tasks");
//        Document byId = MongodbUtils.findById(coll, taskId);
//
//        String s = byId.toJson();
//        JSONObject jsonObject = JSONObject.parseObject(s);
//
//        JSONArray config = JSONArray.parseArray(jsonObject.getString("config"));
//        Iterator<Object> iterator = config.iterator();
//        while (iterator.hasNext()){
//
//            JSONObject next = JSONObject.parseObject(iterator.next().toString());
//            String tagType = next.getString("type");
//
//            //// 情感不参与比较
//            //if(StringUtils.equals("sentiment", tagType)){
//            //    continue;
//            //}
//
//            if(projectType == 1 && !(StringUtils.equals("aspect", tagType) || StringUtils.equals("sentiment", tagType))){
//                continue;
//            }
//
//            if(projectType == 2 &&
//                    !(StringUtils.equals("focus", tagType)
//                            || StringUtils.equals("intent", tagType)
//                            || StringUtils.equals("label", tagType))){
//                continue;
//            }
//            JSONArray child = next.getJSONArray("child");
//            Iterator<Object> iterator1 = child.iterator();
//            while (iterator1.hasNext()){
//
//                JSONObject jsonObject1 = JSONObject.parseObject(iterator1.next().toString());
//
//                String tag = jsonObject1.getString("type");
//
//                configMap.put(tag,tagType);
//            }
//        }
//
//        return configMap;
//    }
//
//
//    public static Map<String,String> findTaskConfigByTaskId(String taskId, String taskType, Integer labelLanguage) {
//
//        Map<String, String> configMap = new HashMap<>();
//
//        String key= null;
//        if(labelLanguage.intValue() == 1){ // 中文
//            key = "type_zh";
//        }else if(labelLanguage.intValue() == 2){
//            key = "type_en";
//        }
//
//        MongoCollection<Document> coll = getMongoCollection(taskType, "tasks");
//        Document byId = MongodbUtils.findById(coll, taskId);
//
//        String s = byId.toJson();
//        JSONObject jsonObject = JSONObject.parseObject(s);
//        /**
//         * [
//         *   {
//         *     "type": "sentiment",
//         *     "type_zh": "情感表达",
//         *     "type_en": "sentiment",
//         *     "enable": false,
//         *     "child": [
//         *       {
//         *         "type": "positive",
//         *         "type_zh": "正面",
//         *         "type_en": "positive",
//         *         "enable": true,
//         *         "tip": "正面"
//         *       },
//         *       {
//         *         "type": "negative",
//         *         "type_zh": "负面",
//         *         "type_en": "negative",
//         *         "enable": true,
//         *         "tip": "负面"
//         *       }
//         *     ]
//         *   }
//         * ]
//         */
//        JSONArray config = JSONArray.parseArray(jsonObject.getString("config"));
//        Iterator<Object> iterator = config.iterator();
//        while (iterator.hasNext()){
//
//            JSONObject next = JSONObject.parseObject(iterator.next().toString());
//            JSONArray child = next.getJSONArray("child");
//            Iterator<Object> iterator1 = child.iterator();
//            while (iterator1.hasNext()){
//
//                JSONObject jsonObject1 = JSONObject.parseObject(iterator1.next().toString());
//                String typeLanguageValue = jsonObject1.getString(key);
//
//                String typeValue = jsonObject1.getString("type");
//                if(typeValue.startsWith("#")){
//                    continue;
//                }
//                if(StringUtils.isBlank(typeLanguageValue)){
//
//                    configMap.put(typeValue,typeValue);
//                }else{
//
//                    configMap.put(typeValue,typeLanguageValue);
//                }
//
//            }
//        }
////        MongoCursor<Document> list = MongodbUtils.find(coll, Filters.and(Filters.eq("_id", taskId)));
////        while (list.hasNext()) {
////            Document document = list.next();
////            String s = document.toJson();
////            JSONObject jsonObject = JSONObject.parseObject(s);
////            /**
////             * [
////             *   {
////             *     "type": "sentiment",
////             *     "type_zh": "情感表达",
////             *     "type_en": "sentiment",
////             *     "enable": false,
////             *     "child": [
////             *       {
////             *         "type": "positive",
////             *         "type_zh": "正面",
////             *         "type_en": "positive",
////             *         "enable": true,
////             *         "tip": "正面"
////             *       },
////             *       {
////             *         "type": "negative",
////             *         "type_zh": "负面",
////             *         "type_en": "negative",
////             *         "enable": true,
////             *         "tip": "负面"
////             *       }
////             *     ]
////             *   }
////             * ]
////             */
////            JSONArray config = jsonObject.getJSONArray("config");
////            Iterator<Object> iterator = config.iterator();
////            while (iterator.hasNext()){
////
////                JSONObject next = JSONObject.parseObject(iterator.next().toString());
////                JSONArray child = next.getJSONArray("child");
////                Iterator<Object> iterator1 = child.iterator();
////                while (iterator1.hasNext()){
////
////                    JSONObject jsonObject1 = JSONObject.parseObject(iterator1.next().toString());
////                    String typeLanguageValue = jsonObject1.getString(key);
////
////                    String typeValue = jsonObject1.getString("type");
////                    if(typeValue.startsWith("#")){
////                        continue;
////                    }
////                    if(StringUtils.isBlank(typeLanguageValue)){
////
////                        configMap.put(typeValue,typeValue);
////                    }else{
////
////                        configMap.put(typeValue,typeLanguageValue);
////                    }
////
////                }
////            }
////        }
//        return configMap;
//    }
//
//    /**
//     * @Author yaoqi
//     * @Description 获取task_data中的content
//     * 由taskType确定是从ann还是从ann_online库获取数据
//     * @Date 2022/6/13 10:36
//     * @Param [taskId, taskType]
//     * @return java.util.Map<java.lang.String,java.lang.String>
//     **/
//    public static Map<String,String> getSubTaskData(String taskId, String taskType, int pageNo, int pageSize) {
//
//        MongoCollection<Document> coll = getMongoCollection(taskType, "subtask_data");
////        coll.aggregate([{'$lookup':{'from': "inventory", "localField": "item", "foreignField": "sku", "as": "inventory_docs"}}]);
//
//        // 这里最好提前声明下容量，扩容对性能有损耗
//        Map<String,String> contentMap = new HashMap<>();
//        // todo 暂时不用分页查询的方式进行查询，一次把一个任务的数据全部查询出来，不过这样很容易出问题，所以，还是使用分批查询的方式吧
////        MongoCursor<Document> list = MongodbUtils.find(coll, Filters.and(Filters.eq("tid", taskId),Filters.eq("status",1)));
//        MongoCursor<Document> list = MongodbUtils.findByPage(coll, Filters.and(Filters.eq("tid", taskId), Filters.eq("status", 1)), pageNo, pageSize);
//        while (list.hasNext()) {
//            Document document = list.next();
//            String s = document.toJson();
//            JSONObject jsonObject = JSONObject.parseObject(s);
//            contentMap.put(jsonObject.getString("did"), document.getObjectId("_id").toString()+"&"+jsonObject.getString("content"));
//        }
//
//        return contentMap;
//    }
//
//    public static Map<String,String> getSubTaskDataNoPage(String taskId, String taskType) {
//
//        MongoCollection<Document> coll = getMongoCollection(taskType, "subtask_data");
//
//        // 这里最好提前声明下容量，扩容对性能有损耗
//        Map<String,String> contentMap = new HashMap<>();
//        // todo 暂时不用分页查询的方式进行查询，一次把一个任务的数据全部查询出来，不过这样很容易出问题，所以，还是使用分批查询的方式吧
//        MongoCursor<Document> list = MongodbUtils.find(coll, Filters.and(Filters.eq("tid", taskId), Filters.eq("status", 1), Filters.ne("spam", "1")));
//        while (list.hasNext()) {
//            Document document = list.next();
//            String s = document.toJson();
//            JSONObject jsonObject = JSONObject.parseObject(s);
//            contentMap.put(jsonObject.getString("did"), document.getObjectId("_id").toString()+"&"+jsonObject.getString("content"));
//            //contentMap.put(jsonObject.getString("did"), jsonObject.getString("did")+"&"+jsonObject.getString("content"));
//        }
//
//        return contentMap;
//    }
//
//    public static long getSubTaskTotalCount(String taskId, String taskType) {
//
//        MongoCollection<Document> coll = getMongoCollection(taskType, "subtask_data");
////        coll.aggregate([{'$lookup':{'from': "inventory", "localField": "item", "foreignField": "sku", "as": "inventory_docs"}}]);
//
//
//        // 这里最好提前声明下容量，扩容对性能有损耗
//        Map<String,String> contentMap = new HashMap<>();
//        return MongodbUtils.findCountByParams(coll, Filters.and(Filters.eq("tid", taskId)));
//
//    }
//
//    /**
//     * 根据taskId 查询标注数据   这里需要注意标注数据会有如下问题，
//     * 1. task_data表中的content没有标注记录
//     * 2. task_data表中的content有标注记录，但是标注记录为空
//     * 3. task_data表中的content有标注记录，但是标注记录有误
//     *
//     * todo 这里需要根据 taskType 区分是线上还是线下
//     **/
//    public static Map<String,Map<String, JSONArray>> findTagDataByTaskId(String taskId, String taskType, int pageNo, int pageSize) {
//
//        final Map<String, String> subTaskDataMap = getSubTaskDataNoPage(taskId, taskType);
//
//        MongoCollection<Document> coll = getMongoCollection(taskType,"tag_data");
//        Map<String, Map<String, JSONArray>> contentMap = new HashMap<>();
//        MongoCursor<Document> list = MongodbUtils.findByPage(coll, Filters.and(Filters.eq("tid", taskId)),pageNo, pageSize);
//        while (list.hasNext()) {
//            Document document = list.next();
//            String s = document.toJson();
//            JSONObject jsonObject = JSONObject.parseObject(s);
//            String did = jsonObject.getString("did");
//            String content = subTaskDataMap.get(did);
//
//            if(content != null && jsonObject.getJSONArray("entities").size() >0){
//
//                Map<String ,JSONArray> tempMap = new HashMap<>();
//                tempMap.put(content, jsonObject.getJSONArray("entities"));
//
//                contentMap.put(did, tempMap);
//            }
//        }
//        return contentMap;
//    }
//
//    public static void filterDistinctTagDataByTaskId(String taskId, String taskType, Set<String> tagSet) {
//
//        MongoCollection<Document> coll = getMongoCollection(taskType,"tag_data");
//        MongoCursor<Document> list = MongodbUtils.find(coll, Filters.and(Filters.eq("tid", taskId)));
//
//        while (list.hasNext()) {
//            Document document = list.next();
//            String s = document.toJson();
//            JSONObject jsonObject = JSONObject.parseObject(s);
//            JSONArray entities = jsonObject.getJSONArray("entities");
//            Iterator<Object> iterator = entities.iterator();
//            while (iterator.hasNext()){
//                JSONObject jsonObject1 = JSONObject.parseObject(iterator.next().toString());
//                String tag = jsonObject1.getString("tag");
//                if(StringUtils.isNotBlank(tag)){
//                    tagSet.add(tag);
//                }
//            }
//        }
//    }
//
//    public static Map<String, String> findConvertedTagDataByTaskId(String taskId, String taskType, Map<String, String> taskConfigByTaskId, Map<String, String> subTaskDataMap, int pageNo, int pageSize) {
//
//        Map<String, String> convertedTagMap = new HashMap<>();
//
//        MongoCollection<Document> coll = getMongoCollection(taskType,"tag_data");
//        // tag_data里面的数据 分页查询得到
//        MongoCursor<Document> list = MongodbUtils.findByPage(coll, Filters.and(Filters.eq("tid", taskId)), pageNo, pageSize);
//
//        while (list.hasNext()) {
//            Document document = list.next();
//            String s = document.toJson();
//            JSONObject jsonObject = JSONObject.parseObject(s);
//            String did = jsonObject.getString("did");
//            if(subTaskDataMap.containsKey(did) && jsonObject.getJSONArray("entities").size() >0){
//
//                JSONArray entities = jsonObject.getJSONArray("entities");
//                Iterator<Object> iterator = entities.iterator();
//                while (iterator.hasNext()){
//
//                    JSONObject jsonObject1 = JSONObject.parseObject(iterator.next().toString());
//                    String key = jsonObject1.getString("tag");
//                    String convertedTag = taskConfigByTaskId.get(key);
//                    convertedTagMap.put(convertedTag, convertedTag);
//                }
//            }
//        }
//        return convertedTagMap;
//    }
//
////    /**
////     * @Author yaoqi
////     * @Description key id   key did value content
////     * @Date 2022/6/16 18:12
////     * @Param [did, subTaskDataMap]
////     * @return java.lang.String
////     **/
////    private static String getContentByDid(String did, Map<String, Map<String, String>> subTaskDataMap) {
////
////        Iterator<Map.Entry<String, Map<String, String>>> iterator = subTaskDataMap.entrySet().iterator();
////        if(iterator.hasNext()){
////            Map.Entry<String, Map<String, String>> next = iterator.next();
////            String id = next.getKey();
////
////        }
////
////    }
//
//    /**
//     * @Author yaoqi
//     * @Description 根据requestId查询信息
//     * @Date 2022/6/9 10:15
//     * @Param [requestId]
//     * @return java.util.Map<java.lang.String,java.lang.String>
//     **/
//    public static List<String> findPageByRequestId(String requestId, Integer pageNo, Integer pageSize) {
//
//        MongoCollection<Document> coll = MongodbUtils.getCollection(offLineDbName, "origin_data");
//
//        // 这里最好提前声明下容量，扩容对性能有损耗
//        List<String> contentList = new ArrayList<>();
//        MongoCursor<Document> list = MongodbUtils.findByPage(coll, Filters.and(Filters.eq("requestid", requestId)), pageNo, pageSize);
//        while (list.hasNext()) {
//            Document document = list.next();
//            String s = document.toJson();
//            JSONObject jsonObject = JSONObject.parseObject(s);
//            contentList.add(jsonObject.getString("content"));
//        }
//
//        return contentList;
//    }
//
//    /**
//     * 统计数
//     */
//    public static int getCount(MongoCollection<Document> coll) {
//        return (int) coll.count();
//    }
//
//    /**
//     * 条件查询
//     */
//    public static MongoCursor<Document> find(MongoCollection<Document> coll, Bson filter) {
//        return coll.find(filter).iterator();
//    }
//
//    public static long findCountByParams(MongoCollection<Document> coll, Bson filter) {
//        return coll.count(filter);
//    }
//
//    /**
//     * 分页查询
//     */
//    public static MongoCursor<Document> findByPage(MongoCollection<Document> coll, Bson filter, int pageNo, int pageSize) {
//        Bson orderBy = new BasicDBObject("_id", 1);
//        return coll.find(filter).sort(orderBy).skip((pageNo - 1) * pageSize).limit(pageSize).iterator();
//    }
//
//
//    /**
//     * 通过ID删除
//     *
//     * @param coll
//     * @param id
//     * @return
//     */
//    public static int deleteById(MongoCollection<Document> coll, String id) {
//        int count = 0;
//        ObjectId _id = null;
//        try {
//            _id = new ObjectId(id);
//        } catch (Exception e) {
//            return 0;
//        }
//        Bson filter = Filters.eq("_id", _id);
//        DeleteResult deleteResult = coll.deleteOne(filter);
//        count = (int) deleteResult.getDeletedCount();
//        return count;
//    }
//
//    /**
//     * @Author yaoqi
//     * @Description
//     * @Date 2022/6/9 11:37
//     * @Param [coll, fileName, fileValue]
//     * @return int
//     **/
//    public static int deleteByFiled(String fileName, String fileValue) {
//
//        MongoCollection<Document> coll = MongodbUtils.getCollection(offLineDbName, "origin_data");
//        int count = 0;
//        Bson filter = Filters.eq(fileName, fileValue);
//        DeleteResult deleteResult = coll.deleteMany(filter);
////        DeleteResult deleteResult = coll.deleteOne(filter);
//        count = (int) deleteResult.getDeletedCount();
//        return count;
//    }
//
//    /**
//     * FIXME
//     *
//     * @param coll
//     * @param id
//     * @param newdoc
//     * @return
//     */
//    public static Document updateById(MongoCollection<Document> coll, String id, Document newdoc) {
//        ObjectId idobj = null;
//        try {
//            idobj = new ObjectId(id);
//        } catch (Exception e) {
//            return null;
//        }
//        Bson filter = Filters.eq("_id", idobj);
//        // coll.replaceOne(filter, newdoc); // 完全替代
//        coll.updateOne(filter, new Document("$set", newdoc));
//        return newdoc;
//    }
//
//    public static void dropCollection(String dbName, String collName) {
//        getDB(dbName).getCollection(collName).drop();
//    }
//
//    /**
//     * 关闭Mongodb
//     */
//    public static void close() {
//        if (mongoClient != null) {
//            mongoClient.close();
//            mongoClient = null;
//        }
//    }
//
//    /**
//     * java程序调用shell脚本
//     * //要执行的命令所在目录shellPath
//     */
//    public static int autoRunShell(String shellPath, String shellFileName){
//        try{
//            log.info("start to run mongo data syc script......");
//            //要执行的shell脚本命令文件
//            String command = "./"+shellFileName;
//
//            //1.执行命令，要先切换目录
//            ProcessBuilder processBuilder = new ProcessBuilder();
//            //切换目标目录
//            processBuilder.directory(new File(shellPath));
//            //添加要执行的命令
//            processBuilder.command(command);
//            Process ps = processBuilder.start();
//            int execStatus  = ps.waitFor(); //阻塞，直到上述命令执行完,返回为0则表示执行成功
//            log.info("mongo data syc script execStatus:{},返回为0表示执行成功",execStatus);
//
//            return execStatus;
//        }catch (Exception e){
//            log.error("mongo data syc script:error:"+e.getMessage(),e);
//
//            return -1;
//        }
//    }
//
//    static String localSMDataBaseName = "scene_mining_anno";
//
//    static String onlineSMDataBaseName = "scene_mining_anno";
//
//    public static boolean synLocalDataToOnline(String mongouri) {
//
//        log.info("同步mongodb数据到线上开始:{}", LocalDateTime.now());
//        int executeStatus = MongodbUtils.autoRunShell(dumpShellPath, dumpShellFileName);
//        log.info("同步mongodb数据到线上结束:{}", LocalDateTime.now());
//        return executeStatus ==0?true:false;
//
//        //MongoClient onlinemongoClient = new MongoClient(new MongoClientURI(mongouri));
//        //MongoClientOptions.Builder options = new MongoClientOptions.Builder();
//        //options.cursorFinalizerEnabled(true);
//        //options.connectionsPerHost(300);
//        //options.connectTimeout(30000);
//        //options.maxWaitTime(5000);
//        //options.socketTimeout(0);
//        //options.threadsAllowedToBlockForConnectionMultiplier(5000);
//        //options.writeConcern(WriteConcern.SAFE);
//        //options.build();
//        //
//        //MongoIterable<String> sceneMiningAnnoCollectionNames = mongoClient.getDatabase(localSMDataBaseName).listCollectionNames();
//        //
//        //MongoCursor<String> tableIterator = sceneMiningAnnoCollectionNames.iterator();
//        //try {
//        //    log.info("====================数据同步开始=======================================");
//        //    while (tableIterator.hasNext()){
//        //
//        //        String collectionName = tableIterator.next();
//        //        if(whiteListCollectionMap.containsKey(collectionName)){ //在白名单中的collection才进行同步
//        //
//        //
//        //            MongoDatabase onlineSMDataBaseNameTemp = onlinemongoClient.getDatabase(onlineSMDataBaseName);
//        //            // 先将线上的collection  给 drop掉
//        //
//        //            onlineSMDataBaseNameTemp.getCollection(collectionName).drop();
//        //            log.info("线上旧数据:{},被drop,开始批量同步数据到线上库！",collectionName);
//        //
//        //            // 然后创建相应的collection
//        //            onlineSMDataBaseNameTemp.createCollection(collectionName);
//        //
//        //            MongoCollection<Document> scene_mining_anno = mongoClient.getDatabase(localSMDataBaseName).getCollection(collectionName);
//        //
//        //            batchSyncData(scene_mining_anno, collectionName, onlineSMDataBaseNameTemp.getCollection(collectionName));
//        //
//        //            log.info("本地数据:{},批量同步到线上完毕",collectionName);
//        //        }
//        //
//        //
//        //    }
//        //    log.info("====================数据同步结束=======================================");
//        //} catch (Exception e) {
//        //    e.printStackTrace();
//        //    return false;
//        //}
//        //return true;
//    }
//
//    /**
//     * 批量同步数据
//     * @param localCollection
//     * @param collectionName
//     * @param onlineCollection
//     */
//    private static void batchSyncData(MongoCollection<Document> localCollection, String collectionName, MongoCollection<Document> onlineCollection) {
//
//        long total = localCollection.count();
//        log.info("The collection {} contains {} documents.", collectionName, total);
//        /*
//         * 检索所有文档
//         * 1. 获取迭代器FindIterable<Document>
//         * 2. 获取游标MongoCursor<Document>
//         * 3. 通过游标遍历检索出的文档集合
//         */
//        int count = 0;
//        FindIterable<Document> documents = localCollection.find();
//        try (MongoCursor<Document> cursor = documents.iterator()) {
//            List<Document> list = new ArrayList<>();
//            long begin = System.currentTimeMillis();
//            while (cursor.hasNext()) {
//                list.add(cursor.next());
//                count++;
//                if (count % 100 == 0 || total - count == 0) {
//                    long duration = System.currentTimeMillis() - begin;
//
//                    // 消费数据!!!
//                    onlineCollection.insertMany(list);
//                    list.clear();
//                    begin = System.currentTimeMillis();
//                }
//            }
//        }
//
//    }
//
//    static Map<String,String> excludeCollectionMap = new HashMap<String, String>()
//    {
//        {
//            put("snippet_data", "snippet_data");
//            put("tasks_log", "tasks_log");
//
//        }
//    };
//
//    static Map<String,String> whiteListCollectionMap = new HashMap<String, String>()
//    {
//        {
//            put("tag_data", "tag_data");
//            put("tag_mapping", "tag_mapping");
//            put("tasks", "tasks");
//        }
//    };
//
//    /**
//     * @Author yaoqi
//     * @Description todo 这里需要根据 taskType 区分是线上还是线下的库
//     * @Date 2022/6/7 18:21
//     * @Param [taskId, labelTaskType, contentList]
//     * @return boolean
//     **/
//    public boolean batchInsertDocs(String taskId, String taskType, List<String> contentList){
//
//        try {
//
//            List<String> taskDataIdList = new ArrayList<>();
//            MongoCollection<Document> coll = MongodbUtils.getMongoCollection(taskType, collName);
//
//            List<Document> docs = new ArrayList<>();
//            contentList.stream().forEach(content->{
//
//                Document document = new Document();
//
//                document.put("tid",taskId);
//                document.put("content",content);
//                docs.add(document);
//            });
//            logger.info("批量插入task_data开始");
//            coll.insertMany(docs);
//            logger.info("批量插入task_data结束");
//
//
//            docs.stream().forEach( document -> {
//
//                String taskDataId = document.getObjectId("_id").toString();
//                taskDataIdList.add(taskDataId);
//            });
//
//            logger.info("===============批量插入redis开始=====taskId:List=================");
////            redisConfig.getRedisTemplateByDb(redisDbIndex).opsForList().leftPushAll("queue:"+taskId, taskDataIdList);
//            logger.info("===============批量插入redis结束=====taskId:List=================");
//
//
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//
//    /**
//     * @Author yaoqi
//     * @Description
//     * @Date 2022/6/7 18:21
//     * @Param [taskId, labelTaskType, contentList]
//     * @return boolean
//     **/
//    public static boolean batchInsertPreInfo(String requestId, List<String> contentList){
//
//        try {
//            MongoCollection<Document> coll = MongodbUtils.getCollection(offLineDbName, "origin_data");
//
//            List<Document> docs = new ArrayList<>();
//            contentList.stream().forEach(content->{
//
//                Document document = new Document();
//
//                document.put("requestid",requestId);
//                document.put("content",content);
//                docs.add(document);
//            });
//            coll.insertMany(docs);
//
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//
//    /**
//     * 单个插入
//     * @param taskId
//     * @param did
//     * @param entities
//     */
//    public static void insertTagData(String taskId, String did, JSONArray entities) {
//
//        try {
//
//            MongoCollection<Document> coll = MongodbUtils.getCollection("ann_online", "tag_data");
//
//            Document document = new Document();
//            document.put("did", did);
//            document.put("tid", taskId);
//            document.put("entities", entities);
//            document.put("relations", new JSONArray());
//
//            coll.insertOne(document);
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * 批量插入tagData表
//     * @param taskId
//     * @param contentList
//     */
//    public static void batchInsertTagData(String taskId, List<JSONObject> contentList) {
//
//        try {
//            MongoCollection<Document> coll = MongodbUtils.getCollection("ann_online", "tag_data");
//
//            List<Document> docs = new ArrayList<>();
//            contentList.stream().forEach(jsonObject->{
//
//                Document document = new Document();
//
//                String did = jsonObject.getString("did");
//
//                document.put("did",did);
//                document.put("tid",taskId);
//                document.put("entities",getEntities(jsonObject));
//                docs.add(document);
//            });
//            coll.insertMany(docs);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("批量插入TagData出现异常:{}", e.getMessage());
//        }
//    }
//
//    private static JSONArray getEntities(JSONObject jsonObject) {
//
//        JSONArray jsonArray = new JSONArray();
//
//        JSONArray entities = jsonObject.getJSONArray("entities");
//
//        for (int i = 0; i <entities.size() ; i++) {
//
//            JSONObject tempObj = new JSONObject();
//
//            JSONObject entityObj = entities.getJSONObject(i);
//
//            tempObj.put("begin", entityObj.getInteger("begin"));
//            tempObj.put("end", entityObj.getInteger("end"));
//            tempObj.put("id", i);
//            tempObj.put("tag", entityObj.get("annotation"));
//            tempObj.put("text", entityObj.get("text"));
//
//            jsonArray.add(tempObj);
//        }
//
//        return jsonArray;
//    }
//
//
//    /**
//     * 测试入口
//     *
//     * @param args
//     * @throws CloneNotSupportedException
//     */
////    public static void main(String[] args) {
////
////        MongoCollection<Document> coll = MongodbUtils.getCollection(dbName, collName);
////
////        MongoCursor<Document> list = MongodbUtils.find(coll, Filters.and(Filters.eq("tid", "6200c862b1a85fcb5d843b17")));
////
////        while (list.hasNext()) {
////            Document document = list.next();
////            String s = document.toJson();
////            JSONObject jsonObject = JSONObject.parseObject(s);
////            log.info("content:{}",jsonObject.getString("content"));
////        }
////
////
////        //coll.createIndex(new Document("validata",1));//创建索引
////        //coll.createIndex(new Document("id",1));
////        // coll.createIndex(new Document("ut_wos",1),new IndexOptions().unique(true));//创建唯一索引
////        //coll.dropIndexes();//删除索引
////        //coll.dropIndex("validata_1");//根据索引名删除某个索引
////       /* ListIndexesIterable<Document> list = coll.listIndexes();//查询所有索引
////        for (Document document : list) {
////            System.out.println(document.toJson());
////        }
////        coll.find(Filters.and(Filters.eq("x", 1), Filters.lt("y", 3)));
////        coll.find(Filters.and(Filters.eq("x", 1), Filters.lt("y", 3)));
////        coll.find().sort(ascending("title"));
////        coll.find().sort(new Document("id", 1));
////        coll.find(new Document("$or", Arrays.asList(new Document("owner", "tom"), new Document("words", new Document("$gt", 350)))));
////        coll.find().projection(fields(include("title", "owner"), excludeId()));*/
////        // coll.updateMany(Filters.eq("validata", 1), Updates.set("validata", 0));
////        //coll.updateMany(Filters.eq("validata", 1), new Document("$unset", new Document("jigou", "")));//删除某个字段
////        //coll.updateMany(Filters.eq("validata", 1), new Document("$rename", new Document("affiliation", "affiliation_full")));//修改某个字段名
////        //coll.updateMany(Filters.eq("validata", 1), new Document("$rename", new Document("affiliationMeta", "affiliation")));
////        //coll.updateMany(Filters.eq("validata", 1), new Document("$set", new Document("validata", 0)));//修改字段值
//////        MongoCursor<Document> cursor1 =coll.find(Filters.eq("ut_wos", "WOS:000382970200003")).iterator();
//////        while(cursor1.hasNext()){
//////            Document sd=cursor1.next();
//////            System.out.println(sd.toJson());
//////            coll.insertOne(sd);
//////        }
////
//////        MongoCursor<Document> cursor1 =coll.find(Filters.elemMatch("affInfo", Filters.eq("firstorg", 1))).iterator();
//////        while(cursor1.hasNext()){
//////            Document sd=cursor1.next();
//////            System.out.println(sd.toJson());
//////        }
////        //查询只返回指定字段
////        // MongoCursor<Document> doc= coll.find().projection(Projections.fields(Projections.include("ut_wos","affiliation"),Projections.excludeId())).iterator();
////        //"ut_wos" : "WOS:000382970200003"
////        //coll.updateMany(Filters.eq("validata", 1), new Document("$set", new Document("validata", 0)));
////        //coll.updateMany(Filters.eq("validata", 0), new Document("$rename", new Document("sid", "ssid")), new UpdateOptions().upsert(false));
////        //coll.updateOne(Filters.eq("ut_wos", "WOS:000382970200003"), new Document("$set", new Document("validata", 0)));
////        //long isd=coll.count(Filters.elemMatch("affInfo", Filters.elemMatch("affiliationJGList", Filters.eq("sid", 0))));
////        // System.out.println(isd);
////        //MongoCursor<Document> doc= coll.find(Filters.elemMatch("affInfo", Filters.elemMatch("affiliationJGList", Filters.ne("sid", 0)))).projection(Projections.fields(Projections.elemMatch("affInfo"),Projections.excludeId())).iterator();
//////       MongoCursor<Document> doc= coll.find().projection(Projections.include("affInfo","ssid")).iterator();
//////       while(doc.hasNext()){
//////            Document sd=doc.next();
//////            System.out.println(sd.toJson());
//////
//////        }
////        MongodbUtils.close();
////        // 插入多条
//////         for (int i = 1; i <= 4; i++) {
//////         Document doc = new Document();
//////         doc.put("name", "zhoulf");
//////         doc.put("school", "NEFU" + i);
//////         Document interests = new Document();
//////         interests.put("game", "game" + i);
//////         interests.put("ball", "ball" + i);
//////         doc.put("interests", interests);
//////         coll.insertOne(doc);
//////         }
//////
////       /* MongoCursor<Document> sd =coll.find().iterator();
////        while(sd.hasNext()){
////            Document doc = sd.next();
////            String id =  doc.get("_id").toString();
////            List<AffiliationJG> list = new ArrayList<AffiliationJG>();
////            AffiliationJG jg = new AffiliationJG();
////            jg.setAddress("123");
////            jg.setCid(2);
////            jg.setCname("eeee");
////            jg.setSid(3);
////            jg.setSname("rrrr");
////            AffiliationJG jg2 = new AffiliationJG();
////            jg2.setAddress("3242");
////            jg2.setCid(2);
////            jg2.setCname("ers");
////            jg2.setSid(3);
////            jg2.setSname("rasdr");
////            list.add(jg);
////            list.add(jg2);
////            AffiliationList af = new AffiliationList();
////            af.setAffiliationAuthos("33333");
////            af.setAffiliationinfo("asdsa");
////            af.setAffiliationJGList(list);
////            JSONObject json = JSONObject.fromObject(af);
////            doc.put("affInfo", json);
////            MongoDBUtil.instance.updateById(coll, id, doc);
////        }*/
////
//////        Bson bs = Filters.eq("name", "zhoulf");
//////        coll.find(bs).iterator();
////        // // 根据ID查询
////        // String id = "556925f34711371df0ddfd4b";
////        // Document doc = MongoDBUtil2.instance.findById(coll, id);
////        // System.out.println(doc);
////
////        // 查询多个
////        // MongoCursor<Document> cursor1 = coll.find(Filters.eq("name", "zhoulf")).iterator();
////        // while (cursor1.hasNext()) {
////        // org.bson.Document _doc = (Document) cursor1.next();
////        // System.out.println(_doc.toString());
////        // }
////        // cursor1.close();
////
////        // 查询多个
//////         MongoCursor<WdPaper> cursor2 = coll.find(WdPaper.class).iterator();
//////         while(cursor2.hasNext()){
//////             WdPaper doc = cursor2.next();
//////             System.out.println(doc.getUt_wos());
//////         }
////        // 删除数据库
////        // MongoDBUtil2.instance.dropDB("testdb");
////
////        // 删除表
////        // MongoDBUtil2.instance.dropCollection(dbName, collName);
////
////        // 修改数据
////        // String id = "556949504711371c60601b5a";
////        // Document newdoc = new Document();
////        // newdoc.put("name", "时候");
////        // MongoDBUtil.instance.updateById(coll, id, newdoc);
////
////        // 统计表
////        //System.out.println(MongoDBUtil.instance.getCount(coll));
////
////        // 查询所有
//////        Bson filter = Filters.eq("count", 0);
//////        MongoDBUtil.instance.find(coll, filter);
////
////    }
//
//
//    /**
//     * @Author yaoqi
//     * @Description 插入task表
//     * @Date 2022/6/16 10:36
//     * @Param [trainDataVersionId, overlapTagVoList]
//     * @return void
//     **/
//    public static String insertTask(String taskName, String config, String modelType) {
//
//        try {
//
//            MongoCollection<Document> coll = MongodbUtils.getCollection("ann_online", "tasks");
//
//            Document document = new Document();
//            document.put("name", taskName);
//            document.put("type", "0");
//            document.put("modelType", modelType); // 标识任务的类型  1 指标类型的标注任务  2 标签类型的标注任务
//            JSONArray tagArray = new JSONArray();
//            tagArray.add("云听");
//            document.put("tags", tagArray);
//            document.put("config", config);
//
//            coll.insertOne(document);
//
//            ObjectId id = document.getObjectId("_id");
//
//            return id.toString();
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//            return null;
//        }
//    }
//
//    /**
//     *
//     * @param taskId
//     * @param accountName
//     * @param projectId
//     * @param mappingData json字符串
//     */
//    public static void insertCemTagMapping(String taskId, String accountName,
//                                           String projectId, String mappingData) {
//
//        try {
//
//            MongoCollection<Document> coll = MongodbUtils.getCollection("ann_online", "cem_tag_mapping");
//
//            Document document = new Document();
//            document.put("taskId", taskId);
//            document.put("accountName", accountName);
//            document.put("projectId", projectId);
//            document.put("importTime", DateUtil.getYYMMddHHmmssStr(new Date()));
//            document.put("mappingData", mappingData);
//
//            coll.insertOne(document);
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//            log.error("insertCemTagMapping error:{}", e.getMessage());
//        }
//    }
//
//    /**
//     * 插入task_data表
//     * @param content
//     * @param taskId
//     * @return
//     */
//    public static String insertTaskData(String content, String taskId) {
//
//        try {
//
//            MongoCollection<Document> coll = MongodbUtils.getCollection("ann_online", "task_data");
//
//            Document document = new Document();
//            document.put("tid", taskId);
//            document.put("content", content);
//
//            coll.insertOne(document);
//
//            ObjectId id = document.getObjectId("_id");
//
//            return id.toString();
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//            return null;
//        }
//    }
//
//    /**
//     * 插入task_data表
//     * @param content
//     * @param taskId
//     * @return
//     */
//    public static void insertSubTaskData(String content, String taskId,
//                                           String did, Integer index) {
//
//        try {
//
//            MongoCollection<Document> coll = MongodbUtils.getCollection("ann_online", "subtask_data");
//
//            Document document = new Document();
//            document.put("did", did);
//            document.put("name", "ytcem");
//            document.put("tid", taskId);
//            document.put("content", content);
//            document.put("index", index);
//            document.put("ctime", DateUtil.getNowTimestamp());
//            document.put("status", 1);
//
//            coll.insertOne(document);
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * @Author yaoqi
//     * @Description 根据服务版本主键id更新服务配置信息
//     * @Date 2022/9/9 17:45
//     * @Param [projectServiceVersionId]
//     * @return void
//     **/
//    public static void updateByProjectServiceId(Long projectServiceVersionId, String serviceConfigInfo) {
//
//        MongoCollection<Document> coll = MongodbUtils.getCollection(offLineDbName, "project_version_config_info");
//
//        Bson filter = Filters.eq("project_version_id", String.valueOf(projectServiceVersionId));
//        Document newDoc = new Document();
//        newDoc.put("config_info", serviceConfigInfo);
//        coll.updateOne(filter, new Document("$set", newDoc));
//
//    }
//
//    /**
//     * @Author yaoqi
//     * @Description 根据任务主键id更新任务config信息
//     * @Date 2022/9/9 17:45
//     * @Param [projectServiceVersionId]
//     * @return void
//     **/
//    public static void updateByTaskId(String taskId, String config) {
//
//        MongoCollection<Document> coll = MongodbUtils.getCollection("ann_online", "tasks");
//
//        ObjectId idobj = null;
//        try {
//            idobj = new ObjectId(taskId);
//        } catch (Exception e) {
//            log.error("updateByTaskId error:{}",e.getMessage());
//        }
//
//        Bson filter = Filters.eq("_id", idobj);
//        Document newDoc = new Document();
//        newDoc.put("config", config);
//        coll.updateOne(filter, new Document("$set", newDoc));
//
//    }
//
//    /**
//     * @Author yaoqi
//     * @Description 插入服务版本的配置信息到mongodb中
//     * @Date 2022/9/9 18:08
//     * @Param [projectServiceVersionId, serviceConfigInfo]
//     * @return void
//     **/
//    public static void insertByProjectServiceId(Long projectServiceVersionId, String serviceConfigInfo) {
//
//        try {
//
//            MongoCollection<Document> coll = MongodbUtils.getCollection(offLineDbName, "project_version_config_info");
//
//            Document document = new Document();
//            document.put("project_version_id", String.valueOf(projectServiceVersionId));
//            document.put("config_info", serviceConfigInfo);
//
//            coll.insertOne(document);
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 根据projectServiceVersionId查询对应的配置信息
//     * @param projectServiceVersionId
//     * @return
//     */
//    public static String getServiceConfigInfo(Long projectServiceVersionId) {
//
//        MongoCollection<Document> coll = MongodbUtils.getCollection(offLineDbName, "project_version_config_info");
//
//        MongoCursor<Document> list = MongodbUtils.find(coll, Filters.and(Filters.eq("project_version_id", String.valueOf(projectServiceVersionId))));
//        while (list.hasNext()) {
//            Document document = list.next();
//            String s = document.toJson();
//            JSONObject jsonObject = JSONObject.parseObject(s);
//            String configInfo = jsonObject.getString("config_info");
//            String projectVersionId = jsonObject.getString("project_version_id");
//
//            if(StringUtils.equals(String.valueOf(projectServiceVersionId), projectVersionId)){
//
//                return configInfo;
//            }
//        }
//
//        return null;
//    }
//
//    static String evaluate = "evaluate";
//
//    public static Map<String, Map<String, String>> getReport1InfoByEid(String reportId) {
//
//
//        Map<String, Map<String, String>> tempMap = new HashMap<>();
//
//        MongoCollection<Document> coll = MongodbUtils.getCollection(evaluate, "report1");
//
//        // Filters.and(Filters.eq("tid", taskId), Filters.eq("status", 1)), pageNo, pageSize);
//        MongoCursor<Document> list = MongodbUtils.find(coll, Filters.and(Filters.eq("eid", reportId), Filters.ne("second", null), Filters.ne("second", "")));
//
//        while (list.hasNext()){
//
//            Map<String, String> tagMap = new HashMap<>();
//            Document document = list.next();
//            String s = document.toJson();
//            JSONObject jsonObject = JSONObject.parseObject(s);
//            String tagName = jsonObject.getString("second");
//            String f1 = jsonObject.getString("f1");
//            String precision = jsonObject.getString("precision"); // 精度
//            String recall = jsonObject.getString("recall");
//
//            tagMap.put("f1", f1);
//            tagMap.put("precision", precision);
//            tagMap.put("recall", recall);
//
//            tempMap.put(tagName, tagMap);
//        }
//
//        return tempMap;
//    }
//
//    public static Map<String, String> getReport1MainInfoByEid(String reportId) {
//
//
//        Map<String, Map<String, String>> tempMap = new HashMap<>();
//
//        MongoCollection<Document> coll = MongodbUtils.getCollection(evaluate, "report1");
//
//        // Filters.and(Filters.eq("tid", taskId), Filters.eq("status", 1)), pageNo, pageSize);
//        MongoCursor<Document> list = MongodbUtils.find(coll, Filters.and(Filters.eq("eid", reportId), Filters.eq("first", "总")));
//
//        while (list.hasNext()){
//
//            Map<String, String> mainReportMap = new HashMap<>();
//            Document document = list.next();
//            String s = document.toJson();
//            JSONObject jsonObject = JSONObject.parseObject(s);
//            String f1 = jsonObject.getString("f1");
//            String precision = jsonObject.getString("precision"); // 精度
//            String recall = jsonObject.getString("recall");
//
//            mainReportMap.put("f1", f1);
//            mainReportMap.put("precision", precision);
//            mainReportMap.put("recall", recall);
//
//            return mainReportMap;
//        }
//
//        return null;
//    }
//
//    //public static void main(String[] args) {
//    //    // 1568599029636612098
//    //    // 1569568168131911682
//    //    insertByProjectServiceId(1568599029636612098L, "test");
//    //    insertByProjectServiceId(1569568168131911682L, "test");
//    //    //System.out.println(getServiceConfigInfo(1568599029636612099L));
//    //}
//
//
//
//}
