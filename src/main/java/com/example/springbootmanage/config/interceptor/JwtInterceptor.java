package com.example.springbootmanage.config.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.springbootmanage.common.Constants;
import com.example.springbootmanage.config.AuthAccess;
import com.example.springbootmanage.entity.User;
import com.example.springbootmanage.exception.ServiceException;
import com.example.springbootmanage.service.IUserService;
import com.example.springbootmanage.service.impl.UserServiceImpl;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Struct;

/**
 * 类描述：
 *
 * @ClassName JwtInterceptor
 * @Author lzb
 * @Date 2022/8/5 10:46
 * @Version 1.0
 */


public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");

        //判断接口是否有@AuthAccess自定义注解若有则放行
        if(!(handler instanceof HandlerMethod)){
            return true;
        }else {
            HandlerMethod h = (HandlerMethod) handler;
            AuthAccess authAccess = h.getMethodAnnotation(AuthAccess.class);
            if (authAccess != null){
                return true;
            }
        }
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //执行认证
        if (StrUtil.isBlank(token)){
            throw new ServiceException(Constants.CODE_401,"无token，请重新登录");
        }
        //获取token中的user id
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j){
            throw new ServiceException(Constants.CODE_401,"token验证失败");
        }
        User user = userService.getById(userId);
        if (user == null){
            throw new ServiceException(Constants.CODE_401,"用户不存在，请重新登录");
        }
        //用户密码加签验证token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);  //验证token
        }catch (JWTVerificationException e){
            throw new ServiceException(Constants.CODE_401,"token验证失败，请重新登录");
        }
        return true;
    }
}
