package com.atguigu.myzhxy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("tb_clazz")
public class Clazz {
    //班级信息
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String number;
    private String introducation;
    //班主任信息
    private String headmaster;
    private String telephone;
    private String email;
    //所属年级
    private String gradeName;


}
