package com.example.springbootmanage.controller.dto;

import com.example.springbootmanage.entity.Menu;
import lombok.Data;

import java.util.List;

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
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String avatarUrl;
    private String token;
    private String role;
    private List<Menu> menus;
}
