package com.afabao.itdragon.pojo;

/**
 * @author: 啊发包
 * @Date: 2019/07/29 2019-07-29
 */
public class User {

    private String password;

    private String salt;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
