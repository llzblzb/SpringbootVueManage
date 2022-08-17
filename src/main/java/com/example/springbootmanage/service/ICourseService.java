package com.example.springbootmanage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootmanage.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luzber
 * @since 2022-08-14
 */
public interface ICourseService extends IService<Course> {

    Page<Course> findPage(Page<Course> page, String name);

    void setStudentCourse(Integer courseId, Integer studentId);

}
