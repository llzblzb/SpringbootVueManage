package com.example.springbootmanage.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootmanage.entity.Files;
import com.example.springbootmanage.entity.User;
import com.example.springbootmanage.service.IUserService;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.springbootmanage.common.Result;

import javax.annotation.Resource;
import java.util.List;

import com.example.springbootmanage.service.ICourseService;
import com.example.springbootmanage.entity.Course;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author luzber
 * @since 2022-08-14
 */
@RestController
@RequestMapping("/course")
public class CourseController {


    @Resource
    private ICourseService courseService;

    @Resource
    private IUserService userService;


    //新增或更新
    @PostMapping
    public Result save(@RequestBody Course course) {
        return Result.success(courseService.saveOrUpdate(course));
    }

    @PostMapping("/studentCourse/{courseId}/{studentId}")
    public Result studentCourse(@PathVariable Integer courseId,
                                @PathVariable Integer studentId) {
        courseService.setStudentCourse(courseId,studentId);
        return Result.success();
    }

    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(courseService.removeById(id));
    }

    //查询所有
    @GetMapping
    public Result findAll() {
        return Result.success(courseService.list());
    }

    //根据id查询
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(courseService.getById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(courseService.removeByIds(ids));

    }

    //新增或更新
    @PostMapping("/update")
    public Result update(@RequestBody Course course) {
        return Result.success(courseService.updateById(course));
    }


    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam String name) {

//        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
//        queryWrapper.orderByDesc("id");
//        Page<Course> page = courseService.page(new Page<>(pageNum, pageSize), queryWrapper);
//        List<Course> records= page.getRecords();
//        for (Course record : records){
//            User user = userService.getById(record.getTeacherId());
//            if (user != null) {
//                record.setTeacher(user.getNickname());
//            }
//        }

        Page<Course> page = courseService.findPage(new Page<>(pageNum, pageSize), name);
        return Result.success(page);

    }
}




