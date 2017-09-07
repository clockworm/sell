package com.imooc.sell.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
    /** 对象格式化Json输出*/
    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        //逗号换行符
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String json = gson.toJson(object);
        return json;
    }
}
