package com.example.springbootmanage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootmanage.controller.dto.UserDTO;
import com.example.springbootmanage.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luzber
 * @since 2022-08-01
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);

    Page<User> findPage(Page<User> page, String username, String email, String address);
}
