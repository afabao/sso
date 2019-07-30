package com.afabao.itdragon.service;

import com.afabao.itdragon.pojo.ItdragonResult;
import com.afabao.itdragon.pojo.User;
import com.afabao.itdragon.repository.JedisClient;
import com.afabao.itdragon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Service
@Transactional
@PropertySource("classpath:redis.properties")
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;

    @Value("${SSO_SESSION_EXPIRE}")
    private String SSO_SESSION_EXPIRE;

    public ItdragonResult registerUser(User user){
        // 检查用户名是否注册，一般在前端验证的时候处理，因为注册不存在高并发的情况，这里再加一层查询是不影响性能的
        if(null != userRepository.findAccount(user.getAccount())){
            return ItdragonResult.build(400,"用户名已经存在");
        }
        userRepository.save(user);
        // 注册成功后选择发送邮件激活。现在一般都是短信验证码
        return ItdragonResult.build(200, "注册成功");
    }

    public ItdragonResult userLogin(String account, String password, HttpServletRequest request, HttpServletResponse response){

    }


}
