package com.atguigu.myzhxy.controller;

import com.atguigu.myzhxy.pojo.Grade;
import com.atguigu.myzhxy.service.GradeService;
import com.atguigu.myzhxy.util.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "GradeController")
@RestController
@RequestMapping("/sms/gradeController")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    // /sms/gradeController/getGrades


    @ApiOperation("Get all grades")
    @GetMapping("/getGrades")
    public Result getGrades(){
        List<Grade> grades =gradeService.getGrades();
        return Result.ok(grades);
    }


    //sms/gradeController/deleteGrade
    @ApiOperation("Delete grade info")
    @DeleteMapping("/deleteGrade")
    public Result deleteGrade(
            @ApiParam("Delete grade by IDs") @RequestBody List<Integer> ids){

        gradeService.removeByIds(ids);
        return Result.ok();

    }

    @ApiOperation("Add or update grade")
    @PostMapping("/saveOrUpdateGrade")
    public Result saveOrUpdateGrade(
            @ApiParam("JSON grade object")@RequestBody Grade grade
    ){
        gradeService.saveOrUpdate(grade);
        return Result.ok();
    }



    @ApiOperation("Query grade info")
    @GetMapping("/getGrades/{pageNo}/{pageSize}")
    public Result getGrades(
            @ApiParam("PageNO") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("PageSize") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("Grade Name") String gradeName

    ){
        Page<Grade> page =new Page<>(pageNo,pageSize);
        IPage<Grade> pageRs=gradeService.getGradeByOpr(page,gradeName);

        return Result.ok(pageRs);
    }
}
