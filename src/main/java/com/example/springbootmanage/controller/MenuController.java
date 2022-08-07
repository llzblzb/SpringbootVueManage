package com.example.springbootmanage.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import com.example.springbootmanage.service.IMenuService;
import com.example.springbootmanage.entity.Menu;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luzber
 * @since 2022-08-07
 */
@RestController
@RequestMapping("/menu")
        public class MenuController {
    

        @Resource
        private IMenuService menuService;

        //新增或更新
        @PostMapping
        public Result save(@RequestBody Menu menu){
        return Result.success(menuService.saveOrUpdate(menu));
        }
        //删除
        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id){
        return Result.success(menuService.removeById(id));
        }
        //查询所有
        @GetMapping
        public Result findAll(){
        return Result.success(menuService.list());
        }
        //根据id查询
        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id){
        return Result.success(menuService.getById(id));
        }

        @PostMapping("/del/batch")
        public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(menuService.removeByIds(ids));

        }

        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
        @RequestParam Integer pageSize){

            QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
             queryWrapper.orderByDesc("id");
        return Result.success(menuService.page(new Page<>(pageNum,pageSize),queryWrapper));

        }
        }




