package com.example.springbootmanage.service.impl;

import com.example.springbootmanage.entity.Role;
import com.example.springbootmanage.mapper.RoleMapper;
import com.example.springbootmanage.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luzber
 * @since 2022-08-07
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
