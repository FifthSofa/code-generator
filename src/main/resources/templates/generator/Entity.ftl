package com.sergio.out.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("${tableName}")
public class ${entityName?cap_first} {

<#list entityFields as entityField>
    <#if entityField.fieldName??>
    private ${entityField.fieldType} ${entityField.fieldName};

    </#if>
</#list>
    private Date createTime;

    private String createBy;

    private Date modifyTime;

    private String modifyBy;
}