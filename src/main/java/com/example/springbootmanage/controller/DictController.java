package com.example.springbootmanage.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootmanage.common.Constants;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.springbootmanage.common.Result;

import javax.annotation.Resource;
import java.util.List;

import com.example.springbootmanage.service.IDictService;
import com.example.springbootmanage.entity.Dict;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author luzber
 * @since 2022-08-08
 */
@RestController
@RequestMapping("/dict")
public class DictController {


    @Resource
    private IDictService dictService;

    //新增或更新
    @PostMapping
    public Result save(@RequestBody Dict dict) {
        return Result.success(dictService.saveOrUpdate(dict));
    }

    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(dictService.removeById(id));
    }

    //查询所有
    @GetMapping
    public Result findAll() {
        return Result.success(dictService.list());
    }

    //根据id查询
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(dictService.getById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(dictService.removeByIds(ids));

    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {

        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(dictService.page(new Page<>(pageNum, pageSize), queryWrapper));

    }

    //根据id查询
    @GetMapping("/icons")
    public Result getIcons() {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", Constants.DICT_TYPE_ICON);
        return Result.success(dictService.list(queryWrapper));
    }

}




