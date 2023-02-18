package com.pzj.project.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pzj.project.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author rookie
 * @Date 2023-02-18 11:49
 * @Description
 **/
@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/pipelineFileConfig/listAll")
    public Result<?> getPipelineConfig(){
        String str = "[\n" +
                "    {\n" +
                "      \"id\": \"1\",\n" +
                "      \"pipelineName\": \"客服会话\",\n" +
                "      \"pipelineFilePath\": \"customer_service.yaml\",\n" +
                "      \"remark\": \"\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"2\",\n" +
                "      \"pipelineName\": \"电商-英文\",\n" +
                "      \"pipelineFilePath\": \"ecom_en.yaml\",\n" +
                "      \"remark\": null\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"3\",\n" +
                "      \"pipelineName\": \"电商-中文\",\n" +
                "      \"pipelineFilePath\": \"ecom.yaml\",\n" +
                "      \"remark\": null\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"4\",\n" +
                "      \"pipelineName\": \"insta360-intercom\",\n" +
                "      \"pipelineFilePath\": \"insta360_intercom.yaml\",\n" +
                "      \"remark\": null\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"5\",\n" +
                "      \"pipelineName\": \"insta360-zendesk\",\n" +
                "      \"pipelineFilePath\": \"insta360_zendesk.yaml\",\n" +
                "      \"remark\": null\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"6\",\n" +
                "      \"pipelineName\": \"社交媒体-百度\",\n" +
                "      \"pipelineFilePath\": \"social_baidu.yaml\",\n" +
                "      \"remark\": null\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"7\",\n" +
                "      \"pipelineName\": \"社交媒体-bilibili\",\n" +
                "      \"pipelineFilePath\": \"social_bilibili.yaml\",\n" +
                "      \"remark\": null\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"8\",\n" +
                "      \"pipelineName\": \"社交媒体-抖音/小红书\",\n" +
                "      \"pipelineFilePath\": \"social_dyxhs.yaml\",\n" +
                "      \"remark\": null\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"9\",\n" +
                "      \"pipelineName\": \"社交媒体-快手\",\n" +
                "      \"pipelineFilePath\": \"social_kuaishou.yaml\",\n" +
                "      \"remark\": null\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"10\",\n" +
                "      \"pipelineName\": \"社交媒体-微信文章\",\n" +
                "      \"pipelineFilePath\": \"social_wechat.yaml\",\n" +
                "      \"remark\": null\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"11\",\n" +
                "      \"pipelineName\": \"社交媒体-微博\",\n" +
                "      \"pipelineFilePath\": \"social_weibo.yaml\",\n" +
                "      \"remark\": null\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"12\",\n" +
                "      \"pipelineName\": \"定长字符替换\",\n" +
                "      \"pipelineFilePath\": \"trans.yaml\",\n" +
                "      \"remark\": null\n" +
                "    }\n" +
                "  ]";
        JSONArray jsonArray = JSONArray.parseArray(str);
        return Result.success(jsonArray);
    }

    @GetMapping("/auth/routes")
    public Result<?> authRoutes() {

        String str = "{\n" +
                "    \"authedRoutes\": [\n" +
                "      \"/aiPlatform/train\",\n" +
                "      \"/aiPlatform/model\",\n" +
                "      \"/aiPlatform/data\",\n" +
                "      \"/aiPlatform/approving\",\n" +
                "      \"/aiPlatform\"\n" +
                "    ]\n" +
                "  }";
        return Result.success(JSONObject.parseObject(str));
    }

    @GetMapping("/alice/task/list")
    public Result<?> taskList() {

        String str = "[\n" +
                "    {\n" +
                "      \"_id\": \"63b553a0bbd78073816dc270\",\n" +
                "      \"name\": \"test_maihe\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"_id\": \"63b552a6bbd78073816dc26e\",\n" +
                "      \"name\": \"test_aac\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"_id\": \"63b391082c8b6ddbc9419e64\",\n" +
                "      \"name\": \"分类标注测试\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"_id\": \"638ea1a1ea13437b4213ba67\",\n" +
                "      \"name\": \"AAC_声学器件_AS\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"_id\": \"63631c4f31e6ea65af8e8ab5\",\n" +
                "      \"name\": \"石头_洗地机_AS\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"_id\": \"636232f231e6ea65af8e82ab\",\n" +
                "      \"name\": \"ubras_内衣_AS\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"_id\": \"635f9d6731e6ea65af8e4a7f\",\n" +
                "      \"name\": \"insta360_相机_AS\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"_id\": \"635f913031e6ea65af8e439c\",\n" +
                "      \"name\": \"麦和_宠物类_AS\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"_id\": \"635b344631e6ea65af8e33e1\",\n" +
                "      \"name\": \"HFP_话题分析_CS\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"_id\": \"635b317b31e6ea65af8e33db\",\n" +
                "      \"name\": \"薄荷健康_话题分析_CS\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"_id\": \"635a05fc31e6ea65af8e1a69\",\n" +
                "      \"name\": \"优时颜_客服会话_CS\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"_id\": \"6350cca631e6ea65af8de2ea\",\n" +
                "      \"name\": \"优时颜_清洁护肤_AS\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"_id\": \"6350b50031e6ea65af8dcd7a\",\n" +
                "      \"name\": \"卫里健康_客服会话_CS\"\n" +
                "    }\n" +
                "  ]";
        return Result.success(JSONObject.parseArray(str));
    }

    @PostMapping("/auth/login")
    public JSONObject login() {

        String str =  "{\"access_token\":\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1IjoieWFvcWkiLCJyIjoiYW5uX21hbmFnZXIsZGV2ZWxvcGVyLG1vZGVsX21hbmFnZXIsc2NlbmVfbWFuYWdlciIsImV4cCI6MTY4MTQ2Mjk1Nn0.9RNjd8qFun-Ji0oveOg05tOKQUw4HDoiSj-OlDUH-50\",\"token_type\":\"bearer\"}";
        return JSONObject.parseObject(str);
    }

    @PostMapping("/auth/logout")
    public JSONObject logout() {

        String str =  "{\"status\":200}";
        return JSONObject.parseObject(str);
    }


    @GetMapping("/user/me")
    public JSONObject userMe() {

        String str = "{\"username\":\"yaoqi\",\"disabled\":false,\"email\":\"yaoqi@skieer.com\",\"avatar\":null,\"roles\":[\"ann_manager\",\"developer\",\"model_manager\",\"scene_manager\"]}";
        return JSONObject.parseObject(str);
    }
}
