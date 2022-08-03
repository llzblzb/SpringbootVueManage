package com.example.springbootmanage.exception;

import com.example.springbootmanage.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 类描述：
 *
 * @ClassName ExceptionHandler
 * @Author lzb
 * @Date 2022/8/3 13:17
 * @Version 1.0
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 如果抛出的是ServiceException,则调用该方法
     *
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result handle(ServiceException se){
        return Result.error(se.getCode(),se.getMessage());
    }
}
