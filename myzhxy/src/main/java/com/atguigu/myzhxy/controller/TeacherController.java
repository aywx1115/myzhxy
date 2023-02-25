package com.atguigu.myzhxy.controller;

import com.atguigu.myzhxy.pojo.Teacher;
import com.atguigu.myzhxy.service.TeacherService;
import com.atguigu.myzhxy.util.MD5;
import com.atguigu.myzhxy.util.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "TeacherController")
@RestController
@RequestMapping("/sms/teacherController")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("Query teacher info")
    @GetMapping("/getTeachers/{pageNo}/{pageSize}")
    public Result getTeachers(
            @ApiParam("PageNO") @PathVariable("pageNo") Integer pageNo ,
            @ApiParam("PageSize") @PathVariable("pageSize") Integer pageSize ,
            @ApiParam("Teacher name") Teacher teacher
    ){
        Page<Teacher> paraParam =new Page<>(pageNo,pageSize);

        IPage<Teacher> page = teacherService.getTeachersByOpr(paraParam,teacher);

        return Result.ok(page);
    }


    @ApiOperation("Add or modify teacher")
    @PostMapping("/saveOrUpdateTeacher")
    public Result saveOrUpdateTeacher(
            @ApiParam("JOSN Teacher") @RequestBody Teacher teacher
    ){

        if (teacher.getId() == null || teacher.getId() ==0){
            teacher.setPassword(MD5.encrypt(teacher.getPassword()));
        }

        teacherService.saveOrUpdate(teacher);
        return  Result.ok();
    }


    @ApiOperation("Delete single or multiple teacher")
    @DeleteMapping("/deleteTeacher")
    public Result deleteTeacher(
            @ApiParam("Delete teacher by ID")  @RequestBody List<Integer> ids
    ){
        teacherService.removeByIds(ids);
        return Result.ok();
    }


}
