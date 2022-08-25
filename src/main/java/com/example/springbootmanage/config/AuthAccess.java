package com.example.springbootmanage.config;

import java.lang.annotation.*;

/**
 * 接口放行注解
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthAccess {

}
