package com.example.springbootmanage.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springbootmanage.entity.User;
import com.example.springbootmanage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webservices.client.HttpWebServiceMessageSenderBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 类描述：
 *
 * @ClassName TokenUtils
 * @Author lzb
 * @Date 2022/8/4 15:19
 * @Version 1.0
 */

@Component
public class TokenUtils {

    private static IUserService staticUserService;

    @Resource
    private IUserService userService;

    @PostConstruct
    public void setUserService(){
        staticUserService = userService;
    }

    public static String getToken(String userId,String sign){
       return JWT.create()
                .withAudience(userId)  // Header将user id保存到token里面，作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(),2))  // 过期时间设置2小时
                .sign(Algorithm.HMAC256(sign));  //将password作为token的密匙

    }
    /*
    获取当前登录的用户信息
     */
    public static User getCurrentUser(){
        try{
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isNotBlank(token)){
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserService.getById(Integer.valueOf(userId));
            }
        }catch (Exception e){
            return null;
        }

        return null;
    }
}
