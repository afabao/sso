package com.itdragon.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author: 啊发包
 * @Date: 2019/07/29 2019-07-29
 */
public class CookieUtils {

    /**获取cookies的值，不编码*/
    public static String getCookieValue(HttpServletRequest request, String cookieName){
        return getCookieValue(request,cookieName,false);
    }

    /**获取cookie的值，是否编码*/
    public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder){
        Cookie[] cookies = request.getCookies();
        if(cookies == null || cookieName ==null){
            return null;
        }
        String retValue = null;
        try {
            for(int i=0; i<cookies.length - 1; i++){
                if(cookies[i].getName().equals(cookieName)){
                    retValue = isDecoder ? URLDecoder.decode(cookies[i].getValue(),"utf-8") : cookies[i].getValue();
                    break;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return retValue;
    }

    /**获取cookie的值，编码格式*/
    public static String getCookieValue(HttpServletRequest request, String cookieName, String encodeString){
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookieName == null) {
            return null;
        }
        String retValue = null;
        try {
            for(int i=0; i<cookies.length - 1; i++){
                if(cookies[i].getName().equals(cookieName)){
                    retValue = URLDecoder.decode(cookies[i].getValue(),encodeString);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return retValue;
    }

    /**设置Cookie的值，不设置生效时间，默认关闭浏览器即失效，不编码*/
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue){
        setCookie(request,response,cookieName,cookieValue,-1);
    }

    /**设置cookies在指定的时间内生效，不编码*/
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxAge){
        setCookie(request,response,cookieName,cookieValue,cookieMaxAge,false);
    }

    /**设置cookie在指定时间内生效，编码*/
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxAge, boolean decoder){
        doSetCookie(request,response,cookieName,cookieValue,cookieMaxAge,false);
    }

    /**设置cookie在指定时间内生效，按指定字符集编码*/
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxAge, String encodeString){
        doSetCookie(request,response,cookieName,cookieValue,cookieMaxAge,encodeString);
    }

    /**删除cookie ， 带cookie域名*/
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName){
        doSetCookie(request, response, cookieName, "", -1, false);
    }

    /**设置cookie的值，并使其在指定时间内生效*/
    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxAge, boolean isEncode){
        try {
            cookieValue = isEncode ? URLEncoder.encode(cookieValue,"utf-8") : null == cookieValue ? "" : cookieValue;
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if(cookieMaxAge > 0){
                cookie.setMaxAge(cookieMaxAge);
            }
            if(null != request){
                /**设置域名的cookie*/
                String domainName = getDomainName(request);
                System.out.println("domainName" + "==" + domainName);
                if(!"localhost".equals(domainName)){
                    cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置 Cookie的值，并使其在指定时间内生效
     * @param cookieMaxage cookie生效的最大秒数
     */
    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response,
                                          String cookieName, String cookieValue, int cookieMaxage, String encodeString) {
        try {
            cookieValue = null == cookieValue? "" : URLEncoder.encode(cookieValue, encodeString);
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0) {
                cookie.setMaxAge(cookieMaxage);
            }
            if (null != request) {
                /**设置域名的cookie*/
                String domainName = getDomainName(request);
                System.out.println("domainName : " + domainName);
                if (!"localhost".equals(domainName)) {
                    cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**获取cookie域名*/
    public static final String getDomainName(HttpServletRequest request){
        String domainName = null;
        String serverName = request.getRequestURL().toString();
        if(StringUtils.isBlank(serverName)){
            domainName = "";
        }else{
            serverName = serverName.toLowerCase();
            serverName.substring(7);
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0,end);
            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            if(len > 3){
                domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
            }else if(len <= 3 && len > 1) {
                domainName = "." + domains[len - 2] + "." + domains[len - 1];
            } else {
                domainName = serverName;
            }
            if (domainName != null && domainName.indexOf(":") > 0) {
                String[] ary = domainName.split("\\:");
                domainName = ary[0];
            }
        }
        return domainName;
    }
}
