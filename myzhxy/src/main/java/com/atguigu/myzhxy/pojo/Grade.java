package com.atguigu.myzhxy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("tb_grade")
public class Grade {

    //年级信息
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String introducation;

    private String manager;
    private String email;
    private String telephone;

}