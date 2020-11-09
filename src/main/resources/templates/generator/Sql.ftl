DROP TABLE IF EXISTS `${tableName}`;
CREATE TABLE `${tableName}` (
<#list entityFields as entityField>
    <#if entityField.dbColumn??>
  `${entityField.dbColumn}` ${entityField.dbType} <#if entityField.notNull == "on">NOT NULL<#else>DEFAULT NULL</#if> <#if entityField.comment?? && entityField.comment != "">COMMENT '${entityField.comment}'</#if>,
    </#if>
</#list>
  `create_time` datetime NOT NULL,
  `create_by` varchar(36) NOT NULL,
  `modify_time` datetime NOT NULL,
  `modify_by` varchar(36) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4;