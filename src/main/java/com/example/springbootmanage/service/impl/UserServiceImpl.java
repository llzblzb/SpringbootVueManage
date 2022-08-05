package com.example.springbootmanage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springbootmanage.common.Constants;
import com.example.springbootmanage.common.Result;
import com.example.springbootmanage.controller.dto.UserDTO;
import com.example.springbootmanage.entity.User;
import com.example.springbootmanage.exception.ServiceException;
import com.example.springbootmanage.mapper.UserMapper;
import com.example.springbootmanage.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootmanage.utils.TokenUtils;
import jdk.nashorn.internal.parser.Token;
import org.springframework.stereotype.Service;

import java.util.Locale;

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

    private static final Log LOG = Log.get();
    @Override
    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one!=null) {
            BeanUtil.copyProperties(one,userDTO,true);
            //设置token
            String token = TokenUtils.getToken(one.getId().toString(),one.getPassword());
            userDTO.setToken(token);
            return userDTO;
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }

    }

    @Override
    public User register(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one == null){
            one = new User();
            BeanUtil.copyProperties(userDTO,one,true);
            save(one);     //把one存到数据库
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名已存在");
        }

        return one;
    }

    private User getUserInfo(UserDTO userDTO){
        QueryWrapper<User> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("username",userDTO.getUsername());
        queryWrapper.eq("password",userDTO.getPassword());
        User one;
        try{
            one = getOne(queryWrapper); //从数据库查询用户信息
        }catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        return one;
    }
}
