package com.afabao.itdragon.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/07/29 2019-07-29
 */
public class JsonUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转化成json字符串
     */
    public static String objectToJson(Object data){
        try {
            String string = MAPPER.writeValueAsString(data);
            return string;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json结果姐转化为对象
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType){
        try {
            T t = MAPPER.readValue(jsonData,beanType);
            return t;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json转为pojo的list
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType){
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class,beanType);
        try {
            List<T> list = MAPPER.readValue(jsonData,javaType);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
