package com.example.springbootmanage.controller.dto;

import lombok.Data;

/**
 * 类描述：接收前端请求参数
 *
 * @ClassName UserDTO
 * @Author lzb
 * @Date 2022/8/3 11:16
 * @Version 1.0
 */

@Data
public class UserDTO {
    private String username;
    private String password;
    private String nickname;
    private String avatarUrl;
}
