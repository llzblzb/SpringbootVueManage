package com.example.springbootmanage.service.impl;

import com.example.springbootmanage.entity.User;
import com.example.springbootmanage.mapper.UserMapper;
import com.example.springbootmanage.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luzber
 * @since 2022-08-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
