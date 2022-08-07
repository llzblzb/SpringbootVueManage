package com.example.springbootmanage.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * 类描述：
 *
 * @ClassName CodeGenerator  mybatis-plus代码生成器
 * @Author lzb
 * @Date 2022/8/1 19:42
 * @Version 1.0
 */


public class CodeGenerator {
    public static void main(String[] args) {
            generate();
    }
    private static void generate(){
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/manage?serverTimezone=GMT%2b8", "root", "745632")
                .globalConfig(builder -> {
                    builder.author("luzber") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\Users\\lzb\\IdeaProjects\\Vue\\SpringbootManage\\src\\main\\java\\"); // 指定输出目录
                })
                .packageConfig(builder -> {

                    builder.parent("com.example.springbootmanage") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "C:\\Users\\lzb\\IdeaProjects\\Vue\\SpringbootManage\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();
//                    builder.mapperBuilder().enableMapperAnnotation().build();
                    builder.controllerBuilder().enableHyphenStyle()  // 开启驼峰转连字符
                            .enableRestStyle();  // 开启生成@RestController 控制器
                    builder.addInclude("sys_menu") // 设置需要生成的表名
                            .addTablePrefix("t_", "sys_"); // 设置过滤表前缀
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
