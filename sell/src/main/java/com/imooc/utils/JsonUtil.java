package com.imooc.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @program: sell
 * @description
 * @author: Tian
 * @create: 2020-07-26 17:51
 **/
public class JsonUtil {
    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
