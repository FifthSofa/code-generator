package com.sergio.out.service.bo;

import lombok.Data;

@Data
public class ${entityName?cap_first}QueryInBO {

	private String[] ids;

<#list entityFields as entityField>
    <#if entityField.fieldName??>
        <#if entityField.fieldName != "id" && entityField.fieldName != "accountId">
    private ${entityField.fieldType} ${entityField.fieldName};

        </#if>
    </#if>
</#list>
}