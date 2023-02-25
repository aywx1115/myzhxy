package com.atguigu.myzhxy.controller;

import com.atguigu.myzhxy.pojo.Clazz;
import com.atguigu.myzhxy.service.ClazzService;
import com.atguigu.myzhxy.util.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "ClassController")
@RestController
@RequestMapping("/sms/clazzController")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;


    //GET /sms/clazzController/getClazzs

    @ApiOperation("Query all class info")
    @GetMapping("/getClazzs")
    public Result getClazzs(){
        List<Clazz> clazzes= clazzService.getClazzs();
        return Result.ok(clazzes);
    }



    //DELETE sms/clazzController/deleteClazz  [1,2,3]
    @ApiOperation("Delete single or multiple classes")
    @DeleteMapping("/deleteClazz")
    public Result deleteClazz(
            @ApiParam("delete multiple classes by ID")@RequestBody List<Integer> ids
    ){
        clazzService.removeByIds(ids);

        return Result.ok();
    }



    //	/sms/clazzController/saveOrUpdateClazz


    @ApiOperation("Add or update class info")
    @PostMapping("/saveOrUpdateClazz")
    public Result saveOrUpdateClazz(
            @ApiParam("JSON Class Object")@RequestBody Clazz clazz
    ){
        clazzService.saveOrUpdate(clazz);
        return Result.ok();
    }



    // sms/clazzController/getClazzsByOpr/1/3?gradeName=&name=

    @ApiOperation("Query class info with conditions")
    @GetMapping("/getClazzsByOpr/{pageNo}/{pageSize}")
    public Result getClazzByOpr(
            @ApiParam("PageNO") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("PageSize")@PathVariable("pageSize") Integer pageSize,
            @ApiParam("Class name") Clazz clazz
    ){
        Page<Clazz> page =new Page<>(pageNo,pageSize);

        IPage<Clazz> iPage=clazzService.getClazzsByOpr(page,clazz);

        return Result.ok(iPage);

    }

}
