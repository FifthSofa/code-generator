package com.sergio.out.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("girl_friend")
public class GirlFriend {

    private String id;

    private String accountId;

    private String name;

    private Integer age;

    private Long mobile;

    private Date createTime;

    private String createBy;

    private Date modifyTime;

    private String modifyBy;
}