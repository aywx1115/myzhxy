package com.atguigu.myzhxy.controller;

import com.atguigu.myzhxy.pojo.Student;
import com.atguigu.myzhxy.service.StudentService;
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

@Api(tags = "Students Controller")
@RestController
@RequestMapping("/sms/studentController")
public class StudentController {
    @Autowired
    private StudentService studentService;
    // DELETE
    //	http://localhost:9001/sms/studentController/delStudentById
    @ApiOperation("Delete single or multiple students")
    @DeleteMapping("/delStudentById")
    public Result delStudentById(
            @ApiParam("Delete student by ID") @RequestBody List<Integer> ids
    ){
        studentService.removeByIds(ids);
        return Result.ok();
    }


    // POST  http://localhost:9002/sms/studentController/addOrUpdateStudent

    @ApiOperation("Add or modify student info")
    @PostMapping("/addOrUpdateStudent")
    public Result addOrUpdateStudent(
            @ApiParam("JSON Student Object")@RequestBody Student student
    ){
        Integer id = student.getId();
        if (null == id || 0 ==id) {
            student.setPassword(MD5.encrypt(student.getPassword()));
        }
        studentService.saveOrUpdate(student);
        return Result.ok();
    }



    //GET /sms/studentController/getStudentByOpr/1/3?name=&clazzName=

    @ApiOperation("Query student info")
    @GetMapping("/getStudentByOpr/{pageNo}/{pageSize}")
    public Result getStudentBuOpr(
            @ApiParam("PageNO") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("PageSize") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("Student name") Student student
    ){
        Page<Student> pageParam =new Page(pageNo,pageSize);
        IPage<Student> studentPage =studentService.getStudentByOpr(pageParam,student);
        return Result.ok(studentPage);
    }


}
