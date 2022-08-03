package com.example.springbootmanage.exception;

import lombok.Data;

/**
 * 类描述：
 *
 * @ClassName ServiceException
 * @Author lzb
 * @Date 2022/8/3 13:20
 * @Version 1.0
 */

@Data
public class ServiceException extends RuntimeException{
    private String code;

    public ServiceException(String code,String msg){
        super(msg);
        this.code = code;
    }
}
