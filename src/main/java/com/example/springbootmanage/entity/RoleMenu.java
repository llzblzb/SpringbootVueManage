package com.example.springbootmanage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 类描述：
 *
 * @ClassName RoleMenu
 * @Author lzb
 * @Date 2022/8/9 11:02
 * @Version 1.0
 */

@TableName("sys_role_menu")
@Data
public class RoleMenu {
    private Integer roleId;
    private Integer menuId;
}
