package com.example.springbootmanage.service;

import com.example.springbootmanage.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luzber
 * @since 2022-08-07
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}
