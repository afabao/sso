package com.afabao.itdragon;

import com.afabao.itdragon.pojo.User;
import com.afabao.itdragon.service.UserService;
import com.afabao.itdragon.utils.ItDragonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItdragonApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void registerUser() {
        User user = new User();
        user.setAccount("itdragon");
        user.setUserName("ITDragonGit");
        user.setEmail("itdragon@git.com");
        user.setIphone("12349857999");
        user.setPlainPassword("123456789");
        user.setPlatform("github");
        user.setCreatedDate(ItDragonUtil.getCurrentDateTime());
        user.setUpdatedDate(ItDragonUtil.getCurrentDateTime());
        ItDragonUtil.entryptPassword(user);
        System.out.println(user);
        userService.registerUser(user);
    }

}
