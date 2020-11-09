package com.sergio.out.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sergio.generator.dependency.JsonUtil;
import com.sergio.out.controller.dto.${entityName?cap_first}CreateInDTO;
import com.sergio.out.controller.dto.${entityName?cap_first}QueryInDTO;
import com.sergio.out.controller.dto.${entityName?cap_first}UpdateInDTO;
import com.sergio.out.mybatis.entity.${entityName?cap_first};
import com.sergio.out.service.${entityName?cap_first}Service;
import com.sergio.out.service.bo.${entityName?cap_first}CreateInBO;
import com.sergio.out.service.bo.${entityName?cap_first}QueryInBO;
import com.sergio.out.service.bo.${entityName?cap_first}UpdateInBO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/${entityName?lower_case}")
@Slf4j
@Api(value = "${entityName?cap_first} API", tags = {"1 ${entityName?cap_first} API"})
public class ${entityName?cap_first}Controller {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private ${entityName?cap_first}Service ${entityName?uncap_first}Service;

    @GetMapping
    public Object get${entityName?cap_first}ById(@RequestParam String id) {

        ${entityName?cap_first} ${entityName?uncap_first} = ${entityName?uncap_first}Service.loadById(id);

        return ${entityName?uncap_first};
    }

    @PostMapping
    public Object create${entityName?cap_first}(@Valid @RequestBody ${entityName?cap_first}CreateInDTO createArgs) {

        ${entityName?cap_first}CreateInBO createInBo = JsonUtil.objectToObject(createArgs, ${entityName?cap_first}CreateInBO.class);
        ${entityName?cap_first} ${entityName?uncap_first} = ${entityName?uncap_first}Service.create(createInBo);

        return ${entityName?uncap_first};
    }

    @PutMapping
    public Object update${entityName?cap_first}(@Valid @RequestBody ${entityName?cap_first}UpdateInDTO updateArgs) throws NotFoundException {

        ${entityName?cap_first}UpdateInBO updateInBo = JsonUtil.objectToObject(updateArgs, ${entityName?cap_first}UpdateInBO.class);
        ${entityName?uncap_first}Service.update(updateInBo);

        return "success";
    }

    @DeleteMapping
    public Object deleteById(@RequestParam String id) throws NotFoundException {
        ${entityName?uncap_first}Service.delete(id);

        return "success";
    }

    @GetMapping("/query")
    public Object pageQuery(@Valid ${entityName?cap_first}QueryInDTO queryArgs,
    @RequestParam(value = "page", required = false) Integer page,
    @RequestParam(value = "size", required = false) Integer size) {

        ${entityName?cap_first}QueryInBO queryInBo = JsonUtil.objectToObject(queryArgs, ${entityName?cap_first}QueryInBO.class);
        IPage<${entityName?cap_first}> iPage = ${entityName?uncap_first}Service.pageQuery(queryInBo, page, size);

        return iPage;
    }

}




































