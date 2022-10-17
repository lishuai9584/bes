package com.example.demo.utils;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 执行请求需要的数据
 * @author User
 * */
@Data
public class RequestData {
    private String method;

    private String url;

    private String contentType;

    private String headers;

    private String queryParams;

    private Map<String,Object> queryMap;

    private String pathParams;

    private String data;

    private MultipartFile multipartFile;

    private JSONObject requestBodyObject;

    private JSONArray requestBodyArray;


}
