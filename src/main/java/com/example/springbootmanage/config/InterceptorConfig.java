package com.example.springbootmanage.config;

import com.example.springbootmanage.config.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 类描述：
 *
 * @ClassName InteceptorConfig
 * @Author lzb
 * @Date 2022/8/5 11:18
 * @Version 1.0
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")    //拦截所有请求，通过判断token是否合法决定是否需要登录
                .excludePathPatterns("/file/**","/user/login","/user/register","/**/export","/**/import");    //排除

    }
    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }
}
