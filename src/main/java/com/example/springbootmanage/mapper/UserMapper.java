package com.example.springbootmanage.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootmanage.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luzber
 * @since 2022-08-01
 */
public interface UserMapper extends BaseMapper<User> {

    Page<User> findPage(Page<User> page, @Param("username")String username, @Param("email")String email,@Param("address") String address);
}
