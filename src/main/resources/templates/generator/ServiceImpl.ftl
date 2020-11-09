package com.sergio.out.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sergio.out.mybatis.entity.${entityName?cap_first};
import com.sergio.out.mybatis.mapper.${entityName?cap_first}Mapper;
import com.sergio.out.service.${entityName?cap_first}Service;
import com.sergio.out.service.bo.${entityName?cap_first}CreateInBO;
import com.sergio.out.service.bo.${entityName?cap_first}QueryInBO;
import com.sergio.out.service.bo.${entityName?cap_first}UpdateInBO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;

@Service
@Slf4j
public class ${entityName?cap_first}ServiceImpl implements ${entityName?cap_first}Service {

    @SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "SpringJavaAutowiredFieldsWarningInspection"})
    @Autowired
    private ${entityName?cap_first}Mapper ${entityName?uncap_first}Mapper;

    @Override
    public ${entityName?cap_first} loadById(String id) {
        return ${entityName?uncap_first}Mapper.selectById(id);
    }

    @Override
    @Transactional
    public ${entityName?cap_first} create(${entityName?cap_first}CreateInBO createArgs) {

        // TODO validate
        // ......

        // prepare insert DO args
        String ${entityName?uncap_first}Id = "xxxx";

        ${entityName?cap_first} ${entityName?uncap_first} = new ${entityName?cap_first}();
        BeanUtils.copyProperties(createArgs, ${entityName?uncap_first});

        ${entityName?uncap_first}.setId(${entityName?uncap_first}Id);
        ${entityName?uncap_first}.setAccountId("xxxx");
        ${entityName?uncap_first}.setCreateTime(new Date());
        ${entityName?uncap_first}.setCreateBy("xxxx");
        ${entityName?uncap_first}.setModifyTime(new Date());
        ${entityName?uncap_first}.setModifyBy("xxxx");

        // insert DB
        ${entityName?uncap_first}Mapper.insert(${entityName?uncap_first});

        return this.loadById(${entityName?uncap_first}Id);
    }

    @Override
    @Transactional
    public void update(${entityName?cap_first}UpdateInBO updateArgs) throws NotFoundException {


        // check exists
        if (${entityName?uncap_first}Mapper.selectById(updateArgs.getId()) == null) {
            throw new NotFoundException("not fount");
        }

        // TODO validate
        // ......

        // prepare update DO args
        ${entityName?cap_first} ${entityName?uncap_first} = new ${entityName?cap_first}();
        BeanUtils.copyProperties(updateArgs, ${entityName?uncap_first});

        ${entityName?uncap_first}.setModifyTime(new Date());
        ${entityName?uncap_first}.setModifyBy("xxxx");

        // update DB
        ${entityName?uncap_first}Mapper.updateById(${entityName?uncap_first});
    }

    @Override
    @Transactional
    public void delete(String id) throws NotFoundException {

        // check exists
        if (${entityName?uncap_first}Mapper.selectById(id) == null) {
            throw new NotFoundException("not fount");
        }

        // delete DB
        ${entityName?uncap_first}Mapper.deleteById(id);
    }

    @Override
    public IPage<${entityName?cap_first}> pageQuery(${entityName?cap_first}QueryInBO args, int page, int size) {

        // TODO prepare query args
        QueryWrapper<${entityName?cap_first}> wrapper = new QueryWrapper<>();
        wrapper.eq("account_id", "xxxx");
        if (args.getIds() != null && args.getIds().length > 0) {
            wrapper.in("id", Arrays.asList(args.getIds()));
        }
        // TODO
        wrapper.orderByDesc("create_time");

        // page query
        IPage<${entityName?cap_first}> iPage = new Page<>(page, size);
        IPage<${entityName?cap_first}> pages = ${entityName?uncap_first}Mapper.selectPage(iPage, wrapper);

        return pages;
    }

}