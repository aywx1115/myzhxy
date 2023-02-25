package com.atguigu.myzhxy.controller;

import com.atguigu.myzhxy.pojo.Admin;
import com.atguigu.myzhxy.service.AdminService;
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

@Api(tags = "AdminController")
@RestController
@RequestMapping("/sms/adminController")
public class AdminController {
    @Autowired
    private AdminService adminService ;

    // GET
    //	http://localhost:9002/sms/adminController/getAllAdmin/1/3?    adminName=a
    @ApiOperation("Query administrator information with pagination and conditions")
    @GetMapping("/getAllAdmin/{pageNo}/{pageSize}")
    public Result getAllAdmin(
            @ApiParam("PageNO") @PathVariable("pageNo") Integer pageNo,
            @ApiParam("PageSize") @PathVariable("pageSize") Integer pageSize,
            @ApiParam("Administrator Name") String adminName
    ){
        Page<Admin> pageParam =new Page<Admin>(pageNo,pageSize);

        IPage<Admin> iPage=adminService.getAdminsByOpr(pageParam,adminName);
        return Result.ok(iPage);
    }

    //POST
    //	http://localhost:9002/sms/adminController/saveOrUpdateAdmin  admin
    @ApiOperation("Add or Modify Admin Info")
    @PostMapping("/saveOrUpdateAdmin")
    public Result  saveOrUpdateAdmin(
            @ApiParam("JSON Admin Object") @RequestBody Admin admin
    ){
        Integer id = admin.getId();
        if (id==null || 0 ==id) {
            admin.setPassword(MD5.encrypt(admin.getPassword()));
        }
        adminService.saveOrUpdate(admin);
        return Result.ok();

    }

    // DELETE
    //	http://localhost:9002/sms/adminController/deleteAdmin List<Integer> ids
    @ApiOperation("Delete single or multiple Admin ")
    @DeleteMapping("/deleteAdmin")
    public Result deleteAdmin(
            @ApiParam("Delete Admin by ID") @RequestBody List<Integer> ids
    ){
        adminService.removeByIds(ids);
        return Result.ok();
    }



}
