package com.example.springbootmanage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootmanage.common.Constants;

import com.example.springbootmanage.controller.dto.UserDTO;
import com.example.springbootmanage.entity.Menu;
import com.example.springbootmanage.entity.User;
import com.example.springbootmanage.exception.ServiceException;
import com.example.springbootmanage.mapper.RoleMapper;
import com.example.springbootmanage.mapper.RoleMenuMapper;
import com.example.springbootmanage.mapper.UserMapper;
import com.example.springbootmanage.service.IMenuService;
import com.example.springbootmanage.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootmanage.utils.TokenUtils;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private IMenuService menuService;

    @Resource
    private UserMapper userMapper;


    @Override
    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, userDTO,true);
            //设置token
            String token = TokenUtils.getToken(one.getId().toString(),one.getPassword());
            userDTO.setToken(token);

            //查出用户名对应的role以及显示菜单
            String role = one.getRole();

            //设置用户的菜单列表
            List<Menu> roleMenus = getRoleMenus(role);
            userDTO.setMenus(roleMenus);
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

    @Override
    public Page<User> findPage(Page<User> page, String username, String email, String address) {
       return userMapper.findPage( page,username,email,address);
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

    /**
     * 获取当前角色的菜单列表
     * @param roleFlag
     * @return
     */
    private List<Menu> getRoleMenus(String roleFlag){
        Integer roleId = roleMapper.selectByFlag(roleFlag);
        //当前角色的所有菜单id集合
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);

        //查出系统的所有菜单
        List<Menu> menus = menuService.findMenus("");
        //new一个最后筛选完成之后的list
        List<Menu> roleMenus = new ArrayList<>();
        //筛选出当前用户角色的菜单
        for(Menu menu : menus){
            if (menuIds.contains(menu.getId())){
                roleMenus.add(menu);
            }
            List<Menu> children = menu.getChildren();
            //removeIf移除children内不在menuIds集合中的元素
            children.removeIf(child->!menuIds.contains(child.getId()));

        }
        return roleMenus;
    }
}
