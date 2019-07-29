package com.afabao.itdragon.pojo;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.mapper.Mapper;

import java.io.IOException;
import java.util.List;

public class ItdragonResult {
    /**定义jackson对象*/
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**响应业务状态*/
    private Integer status;

    /**响应消息*/
    private String msg;

    /**响应中的数据*/
    private Object data;

    public ItdragonResult() {
    }

    public ItdragonResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ItdragonResult(Object data){
        this.status = 200;
        this.msg = "ok";
        this.data = "data";
    }

    public static ItdragonResult ok(Object data){
        return new ItdragonResult(data);
    }

    public static ItdragonResult build(Integer status, String msg, Object data){
        return new ItdragonResult(status, msg, data);
    }

    public static ItdragonResult build(Integer status, String msg){
        return new ItdragonResult(status, msg, null);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 将json结果集转化为ItDragonResult对象
     */

    public static ItdragonResult formatToPojo(String jsonData, Class<?> clazz){
        try {
            if(clazz == null){
                return MAPPER.readValue(jsonData, ItdragonResult.class);
            }

            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if(clazz != null){
                if(data.isObject()){
                    obj = MAPPER.readValue(data.traverse(),clazz);
                }else if(data.isTextual()){
                    obj = MAPPER.readValue(data.asText(),clazz);
                }
            }
            return build(jsonNode.get("status").intValue(),jsonNode.get("msg").asText(),obj);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * jsonData 是集合转化
     * @param jsonDate  json数据
     * @param clazz     集合中的类型
     * @return
     */
    public static ItdragonResult formatToList(String jsonDate,Class<?> clazz){
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonDate);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if(data.isArray() && data.size() > 0){
                obj = MAPPER.readValue(data.traverse(),MAPPER.getTypeFactory().constructCollectionType(List.class,clazz));
            }
            return build(jsonNode.get("status").intValue(),jsonNode.get("msg").asText(),obj);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
