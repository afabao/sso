package com.afabao.itdragon.utils;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: 啊发包
 * @Date: 2019/07/29 2019-07-29
 */
public class HttpClientUtils {

    /**无参get请求*/
    public static String doGet(String url){
        return doGet(url,null);
    }

    /**带参get请求*/
    public static String doGet(String url, Map<String, String> param){
        /*创建一个可关闭的httpclient对象**/
        CloseableHttpClient httpClient = HttpClients.createDefault();
        /**设置返回值*/
        String resultMsg = "";
        /**定义HttpResponse对象*/
        CloseableHttpResponse response = null;
        try {
            /**创建uri，可以设置host等参数*/
            URIBuilder builder = new URIBuilder(url);
            if(param != null){
                for (String key : param.keySet()) {
                    builder.addParameter(key,param.get(key));
                }
            }
            URI uri = builder.build();
            /**创建http get请求*/
            HttpGet httpGet = new HttpGet(uri);
            /**执行请求*/
            response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == 200){
                resultMsg = EntityUtils.toString(response.getEntity(),"utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(response != null){
                    response.close();
                }
                httpClient.close();
            } catch (IOException e){
                e.printStackTrace();
            }

        }
        return resultMsg;
    }

    /**无参post请求*/
    public static String doPost(String url){
        return doPost(url,null);
    }

    public static String doPost(String url, Map<String, String> param) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultMsg = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                /**模拟表单*/
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                resultMsg = EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultMsg;
    }

    public static String doPostJson(String url, String json) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }
}
