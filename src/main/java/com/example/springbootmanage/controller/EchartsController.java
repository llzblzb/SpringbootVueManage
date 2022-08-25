package com.example.springbootmanage.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import com.example.springbootmanage.common.Result;
import com.example.springbootmanage.config.AuthAccess;
import com.example.springbootmanage.entity.User;
import com.example.springbootmanage.mapper.FilesMapper;
import com.example.springbootmanage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类描述：
 *
 * @ClassName EchartsController
 * @Author lzb
 * @Date 2022/8/7 13:11
 * @Version 1.0
 */

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Resource
    private FilesMapper filesMapper;
    @Autowired
    private IUserService userService;
    @GetMapping("/example")
    public Result get(){
        Map<String, Object> map = new HashMap<>();
        map.put("x", CollUtil.newArrayList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        map.put("y",CollUtil.newArrayList(150,230, 224, 242, 234, 123, 153));
        return Result.success(map);
    }

    @GetMapping("/members")
    public Result members(){
        List<User> list =userService.list();
        int q1 = 0;  //第一季度
        int q2 = 0;  //第二季度
        int q3 = 0;  //第三季度
        int q4 = 0;  //第四季度
        for(User user : list){
            Date createTime = user.getCreateTime();
            Quarter quarter = DateUtil.quarterEnum(createTime);
            switch (quarter){
                case Q1:q1+=1;break;
                case Q2:q2+=1;break;
                case Q3:q3+=1;break;
                case Q4:q4+=1;break;
                default: break;
            }
        }
        return Result.success(CollUtil.newArrayList(q1,q2,q3,q4));
    }
    @AuthAccess
    @GetMapping("/file/front/all")
    public Result frontAll(){
        return Result.success(filesMapper.selectList(null));
    }

}
