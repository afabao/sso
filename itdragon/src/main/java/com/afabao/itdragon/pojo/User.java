package com.afabao.itdragon.pojo;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: 啊发包
 * @Date: 2019/07/29 2019-07-29
 */
@Entity
@Table(name = "itdragon_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**自增长主键*/
    private Long id;

    /**登录账号*/
    private String account;

    /**加密后密码*/
    private String password;

    /**盐*/
    private String salt;

    /**昵称*/
    private String userName;

    @Transient
    /**登录时的密码，不持久化到数据库*/
    private String plainPassword;

    /**iphone*/
    private String iphone;

    /**email*/
    private String email;

    /**用户来自的平台*/
    private String platform;

    /**用户注册的时间*/
    private String createdDate;

    /**最后一次登录时间*/
    private String updatedDate;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", userName='" + userName + '\'' +
                ", plainPassword='" + plainPassword + '\'' +
                ", iphone='" + iphone + '\'' +
                ", email='" + email + '\'' +
                ", platform='" + platform + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
