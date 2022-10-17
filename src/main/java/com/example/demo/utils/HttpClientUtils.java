package com.example.demo.utils;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * HttpClient远程调用工具类
 *
 * @author User
 * @date 2022-10-06
 */
@Slf4j
@Component
public class HttpClientUtils {

    private static final String WITHOUT_ID = "";

    private static final int HTTP_200 = 200;

    private static final int HTTP_401 = 401;

    public static String executeProxy(RequestData requestData) {
        if (null == requestData.getQueryParams()) {
            requestData.setQueryParams(WITHOUT_ID);
        }
        OrientHttpEntityRequest request =
                new OrientHttpEntityRequest(requestData.getMethod(), requestData.getUrl() + requestData.getQueryParams());
        HttpClient client = HttpClientBuilder.create().build();
        if (requestData.getHeaders() != null){
            JSONObject object = new JSONObject(requestData.getHeaders());
            Set<String> keySet = object.keySet();
            Iterator<?> keys = keySet.iterator();
            while (keys.hasNext()){
                String key = (String) keys.next();
                if (object.containsKey(key)){
                    request.setHeader(key,object.getStr(key));
                }
            }
        }
        if (requestData.getContentType() != null){
            request.setHeader("Content-Type", requestData.getContentType());
        }
        if (requestData.getRequestBodyObject() != null){
            request.setEntity(new ByteArrayEntity(requestData.getRequestBodyObject().toString().getBytes()));
        }
        if (requestData.getRequestBodyArray() != null){
            request.setEntity(new ByteArrayEntity(requestData.getRequestBodyArray().toString().getBytes()));
        }
        if (requestData.getQueryMap() != null && requestData.getQueryMap().size()>0){
            ArrayList<NameValuePair> paramList = new ArrayList<>();
            for (String key : requestData.getQueryMap().keySet()) {
                paramList.add(new BasicNameValuePair(key,requestData.getQueryMap().get(key).toString()));
            }
            try {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, HTTP.UTF_8);
                request.setEntity(entity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        HttpPost httpPost = new HttpPost(requestData.getUrl());
        if (requestData.getMultipartFile() != null){
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            try {
                builder.setCharset(Charsets.UTF_8).addBinaryBody("multipartFile",
                        requestData.getMultipartFile().getInputStream(), ContentType.MULTIPART_FORM_DATA,
                        requestData.getMultipartFile().getOriginalFilename());
                builder.addTextBody("encode", requestData.getData());
                HttpEntity httpEntity = builder.build();
                httpPost.setEntity(httpEntity);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        HttpResponse response = null;
        try {
            if (requestData.getMultipartFile() != null){
                response = client.execute(httpPost);//上传文件特殊处理
            }else {
                response = client.execute(request);
            }
        }catch (Exception e){

        }
        String result = "";
        if (response.getStatusLine().getStatusCode() == HTTP_200){
            try {
                result = EntityUtils.toString(response.getEntity());
            } catch (Exception e) {
                log.error("执行代理请求失败：{}", e);
            }
        } else if (response.getStatusLine().getStatusCode() == HTTP_401){
            log.error("访问的url没有权限：{}",response.getEntity().toString());
        }else {
            log.error("执行代理请求失败：{}",response.getEntity().toString());
        }
        return result;
    }
}
