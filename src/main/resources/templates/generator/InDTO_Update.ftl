package com.sergio.out.controller.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("${entityName?cap_first} Update Args")
public class ${entityName?cap_first}UpdateInDTO {

<#list entityFields as entityField>
    <#if entityField.fieldName??>
        <#if entityField.fieldName != "accountId">
            <#if entityField.fieldName == "id">
    @NotBlank
            </#if>
    private ${entityField.fieldType} ${entityField.fieldName};

        </#if>
    </#if>
</#list>

}