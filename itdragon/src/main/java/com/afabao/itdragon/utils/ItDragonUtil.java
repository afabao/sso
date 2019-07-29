package com.afabao.itdragon.utils;

import com.afabao.itdragon.pojo.User;
import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

/**
 * @author: 啊发包
 * @Date: 2019/07/29 2019-07-29
 */
public class ItDragonUtil {

    /**
     * 加盐加密的策略很多，根据实际情况来
     */
    public static void entryptPassword(User user) {
        String salt = UUID.randomUUID().toString();
        String tempPassword = salt + user.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(tempPassword.getBytes());
        user.setSalt(salt);
        user.setPassword(md5Password);
    }

    public static boolean decryptPassword(User user, String plainPassword) {
        String temPassword = user.getSalt() + plainPassword;
        String md5Password = DigestUtils.md5DigestAsHex(temPassword.getBytes());
        return user.getPassword().equals(md5Password);
    }

    public static String getCurrentDateTime() {
        TimeZone zone = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(zone);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }
}
