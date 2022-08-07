package com.example.springbootmanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 类描述：
 *
 * @ClassName Fiel
 * @Author lzb
 * @Date 2022/8/5 14:12
 * @Version 1.0
 */

@Data
@TableName("sys_file")
public class Files {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String name;
    private String type;
    private Long size;
    private String url;
    private Boolean is_delete;
    private Boolean enable;
    private String md5;
}
