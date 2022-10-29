package com.example.demo.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author User
 * */
public class GsonUtil {

    /**
     * 简单对象转Json
     *
     * @param obj
     * @return
     */
    public static String simpleObjToJson(Object obj) {
        if (Objects.isNull(obj)) {
            return "";
        }
        try {
            Gson gson = new Gson();
            return gson.toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 简单Json转对象
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T simpleJsonToObj(String json, Class<T> cls) {
        Gson gson = new Gson();
        if (Objects.isNull(json)) {
            return null;
        }
        T obj = gson.fromJson(json, cls);
        if (Objects.isNull(obj)) {
            return null;
        } else {
            return obj;
        }
    }

    /**
     * 复杂对象转Json
     *
     * @param obj
     * @return
     */
    public static String complexObjToJson(Object obj) {
        Gson gson = new Gson();
        if (Objects.isNull(obj)) {
            return "";
        }
        String json = gson.toJson(obj);
        if (Objects.isNull(json)) {
            return "";
        } else {
            return json;
        }
    }
    /**
     * 复杂Json转对象
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T complexJsonToObj(String json, Class<T> cls) {
        Gson gson = new Gson();
        if (Objects.isNull(json)) {
            return null;
        }
        T obj = gson.fromJson(json, cls);
        if (Objects.isNull(obj)) {
            return null;
        } else {
            return obj;
        }
    }

    /**
     * 数组转Json
     *
     * @param array
     * @return
     */
    public static String arrayToJson(Object array) {
        if (Objects.isNull(array)) {
            return "";
        }
        Gson gson = new Gson();
        String json = gson.toJson(array);
        if (Objects.isNull(json)) {
            return "";
        } else {
            return json;
        }
    }

    /**
     * list To Json
     *
     * @param list
     * @return
     */
    public static String listToJson(List list) {
        if (Objects.isNull(list)) {
            return "";
        }
        try {
            Gson gson = new Gson();
            return gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * json to list
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T jsonToList(String json,Class<T> cls) {
        if (Objects.isNull(json)) {
            return null;
        }
        try {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<T>>(){}.getType();
            return gson.fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用expose注解 obj to json
     *
     * @param obj
     * @return
     */
    public static String exposeObjToJson(Object obj) {
        if (Objects.isNull(obj)) {
            return "";
        }
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.excludeFieldsWithoutExposeAnnotation();
            Gson gson = gsonBuilder.create();
            return gson.toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 使用expose注解 json to obj
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T exposeJsonToObj(String json, Class<T> cls) {
        if (Objects.isNull(json)) {
            return null;
        }
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.excludeFieldsWithoutExposeAnnotation();
            Gson gson = gsonBuilder.create();
            return gson.fromJson(json, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
