package com.sergio.out.controller.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("${entityName?cap_first} Create Args")
public class ${entityName?cap_first}CreateInDTO {

// TODO javax validation: @NotBlank | @NotNull

<#list entityFields as entityField>
    <#if entityField.fieldName??>
        <#if entityField.fieldName != "id" && entityField.fieldName != "accountId" >
    private ${entityField.fieldType} ${entityField.fieldName};

        </#if>
    </#if>
</#list>

}