package com.example.springbootmanage.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootmanage.common.Result;
import com.example.springbootmanage.entity.Files;
import com.example.springbootmanage.entity.User;
import com.example.springbootmanage.mapper.FilesMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 类描述：
 *
 * @ClassName FileController
 * @Author lzb
 * @Date 2022/8/5 13:46
 * @Version 1.0
 */

@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    private FilesMapper filesMapper;

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Value("${server.ip}")
    private String serverIp;
    /**
     * 文件上传接口
     * @param file 前端上传过来的文件
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();

        //定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();

        String fileUUID = uuid + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + fileUUID);


        //判断配置的文件目录是否存在，若不存在，则创建新目录

        File parentFile  = uploadFile.getParentFile();
        if (!parentFile.exists()){
            parentFile.mkdirs();
        }

        String md5;
        String url;
        //把获取到的文件存储到磁盘目录
        file.transferTo(uploadFile);
        //获取文件MD5
        md5=SecureUtil.md5(uploadFile);
        //从数据库查询是否存在相同记录
        Files dbFiles = getFilesByMd5(md5);

        if (dbFiles != null){
            url = dbFiles.getUrl();
            //由于文件已存在，删除刚刚上传重复文件
            uploadFile.delete();
        }else {
            //数据库若不存在重复文件，则不删除刚刚上传文件
            url="http://"+ serverIp +":9090/file/" + fileUUID;
        }



        //存储数据库
        Files saveFile = new Files();
        saveFile.setName(originalFilename);
        saveFile.setType(type);
        saveFile.setSize(size/1024);
        saveFile.setUrl(url);


        saveFile.setMd5(md5);
        filesMapper.insert(saveFile);
        return url;
    }

    /**
     * 文件下载接口  http://localhost:9090/file/{fileUUID}
     * @param fileUUID
     * @param response
     * @throws IOException
     */
    @GetMapping("/{fileUUID}")
    public void downloadfiles(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        //根据文件唯一标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUUID);

        //设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileUUID,"UTF-8"));
        response.setContentType("application/octet_stream");

        //读取文件字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();

    }

    /**
     * 通过
     * @param md5
     * @return
     */
    private Files getFilesByMd5(String md5){
        //查询MD5是否
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5",md5);
        List<Files> filesList = filesMapper.selectList(queryWrapper);
        return filesList.size() == 0? null : filesList.get(0) ;
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name) {
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
//        查询未删除的记录
        queryWrapper.eq("is_delete",false);
        if (!"".equals(name)) {
            queryWrapper.like("name", name);
        }
//        queryWrapper.orderByDesc("id");

        return Result.success(filesMapper.selectPage(new Page<>(pageNum,pageSize),queryWrapper));
    }
    //新增或更新
    @PostMapping("/update")
    public Result update(@RequestBody Files files) {
        return Result.success(filesMapper.updateById(files));
    }



    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Files files = filesMapper.selectById(id);
        files.setIs_delete(true);
        filesMapper.updateById(files);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        //select * from sys_file where id in(id,id,id。。。）
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",ids);
        List<Files> files = filesMapper.selectList(queryWrapper);
        for (Files file : files){
            file.setIs_delete(true);
            filesMapper.updateById(file);
        }
        return Result.success();

    }
}
