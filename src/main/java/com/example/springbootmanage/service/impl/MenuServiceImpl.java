package com.example.springbootmanage.service.impl;

import com.example.springbootmanage.entity.Menu;
import com.example.springbootmanage.mapper.MenuMapper;
import com.example.springbootmanage.service.IMenuService;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
