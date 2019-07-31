package com.afabao.itdragon.repository;

import com.afabao.itdragon.pojo.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User,Long>, JpaSpecificationExecutor<User> {
    /**
     * 重点知识：SpringData 查询方法定义规范
     *
     * 1. 查询方法名一般以 find | read | get 开头，建议用find
     * 	findByAccount : 通过account查询User
     * 	account是User的属性，拼接时首字母需大写
     * 2. 支持的关键词有很多比如 Or,Between,isNull,Like,In等
     * 	findByEmailEndingWithAndCreatedDateLessThan : 查询在指定时间前注册，并以xx邮箱结尾的用户
     * 	And : 并且
     * 	EndingWith : 以某某结尾
     * 	LessThan : 小于
     *
     * 注意
     * 若有User(用户表) Platform(用户平台表) 存在一对一的关系，且User表中有platformId字段
     * SpringData 为了区分：
     * findByPlatFormId 	表示通过platformId字段查询
     * findByPlatForm_Id 	表示通过platform实体类中id字段查询
     *
     * 开发建议
     * 表的设计，尽量做单表查询，以确保高并发场景减轻数据库的压力。
     */

    /**通过账号查用户信息*/
    User account(String account);

    /**获取指定时间以内以xx邮箱结尾的用户*/
    List<User>  findByEmailEndingWithAndCreatedDateLessThan(String email, String createDate);

    @Query(value = "select count (u.id) from User u where u.platform = :platform and u.updatedDate <= :updatedDate")
    long getActiveUserCount(@Param("platform") String platform, @Param("updatedDate") String updatedDate);

    @Query(value = "select u from User u where u.email like %?1% and u.iphone like %?2%")
    List<User> findByEmailandIphoneLike(String email, String iphone);
    @Modifying
    @Query("UPDATE User u SET u.email = :email WHERE u.id = :id")
    void updateUserEmail(@Param("id") String id,@Param("email") String email);
}
