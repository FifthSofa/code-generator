package com.sergio.out.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sergio.generator.dependency.JsonUtil;
import com.sergio.out.controller.dto.GirlFriendCreateInDTO;
import com.sergio.out.controller.dto.GirlFriendQueryInDTO;
import com.sergio.out.controller.dto.GirlFriendUpdateInDTO;
import com.sergio.out.mybatis.entity.GirlFriend;
import com.sergio.out.service.GirlFriendService;
import com.sergio.out.service.bo.GirlFriendCreateInBO;
import com.sergio.out.service.bo.GirlFriendQueryInBO;
import com.sergio.out.service.bo.GirlFriendUpdateInBO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/girlfriend")
@Slf4j
@Api(value = "GirlFriend API", tags = {"1 GirlFriend API"})
public class GirlFriendController {

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private GirlFriendService girlFriendService;

    @GetMapping
    public Object getGirlFriendById(@RequestParam String id) {

        GirlFriend girlFriend = girlFriendService.loadById(id);

        return girlFriend;
    }

    @PostMapping
    public Object createGirlFriend(@Valid @RequestBody GirlFriendCreateInDTO createArgs) {

        GirlFriendCreateInBO createInBo = JsonUtil.objectToObject(createArgs, GirlFriendCreateInBO.class);
        GirlFriend girlFriend = girlFriendService.create(createInBo);

        return girlFriend;
    }

    @PutMapping
    public Object updateGirlFriend(@Valid @RequestBody GirlFriendUpdateInDTO updateArgs) throws NotFoundException {

        GirlFriendUpdateInBO updateInBo = JsonUtil.objectToObject(updateArgs, GirlFriendUpdateInBO.class);
        girlFriendService.update(updateInBo);

        return "success";
    }

    @DeleteMapping
    public Object deleteById(@RequestParam String id) throws NotFoundException {
        girlFriendService.delete(id);

        return "success";
    }

    @GetMapping("/query")
    public Object pageQuery(@Valid GirlFriendQueryInDTO queryArgs,
    @RequestParam(value = "page", required = false) Integer page,
    @RequestParam(value = "size", required = false) Integer size) {

        GirlFriendQueryInBO queryInBo = JsonUtil.objectToObject(queryArgs, GirlFriendQueryInBO.class);
        IPage<GirlFriend> iPage = girlFriendService.pageQuery(queryInBo, page, size);

        return iPage;
    }

}




































