package com.afabao.itdragon.pojo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ItdragonResult {
    private static final ObjectMapper MAPPER = new ObjectMapper();//定义jackson对象

    private Integer status;     //响应业务状态

    private String msg;         //响应消息

    private Object data;        //响应中的数据

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


}
