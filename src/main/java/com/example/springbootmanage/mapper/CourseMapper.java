package com.example.springbootmanage.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootmanage.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luzber
 * @since 2022-08-14
 */
public interface CourseMapper extends BaseMapper<Course> {

    Page<Course> findPage(Page<Course> page, @Param("name") String name);

    void deleteStudentCourse(Integer courseId, Integer studentId);

    void setStudentCourse(Integer courseId, Integer studentId);
}
