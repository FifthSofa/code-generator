package com.sergio.out.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sergio.out.mybatis.entity.GirlFriend;
import com.sergio.out.mybatis.mapper.GirlFriendMapper;
import com.sergio.out.service.GirlFriendService;
import com.sergio.out.service.bo.GirlFriendCreateInBO;
import com.sergio.out.service.bo.GirlFriendQueryInBO;
import com.sergio.out.service.bo.GirlFriendUpdateInBO;
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
public class GirlFriendServiceImpl implements GirlFriendService {

    @SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "SpringJavaAutowiredFieldsWarningInspection"})
    @Autowired
    private GirlFriendMapper girlFriendMapper;

    @Override
    public GirlFriend loadById(String id) {
        return girlFriendMapper.selectById(id);
    }

    @Override
    @Transactional
    public GirlFriend create(GirlFriendCreateInBO createArgs) {

        // TODO validate
        // ......

        // prepare insert DO args
        String girlFriendId = "xxxx";

        GirlFriend girlFriend = new GirlFriend();
        BeanUtils.copyProperties(createArgs, girlFriend);

        girlFriend.setId(girlFriendId);
        girlFriend.setAccountId("xxxx");
        girlFriend.setCreateTime(new Date());
        girlFriend.setCreateBy("xxxx");
        girlFriend.setModifyTime(new Date());
        girlFriend.setModifyBy("xxxx");

        // insert DB
        girlFriendMapper.insert(girlFriend);

        return this.loadById(girlFriendId);
    }

    @Override
    @Transactional
    public void update(GirlFriendUpdateInBO updateArgs) throws NotFoundException {


        // check exists
        if (girlFriendMapper.selectById(updateArgs.getId()) == null) {
            throw new NotFoundException("not fount");
        }

        // TODO validate
        // ......

        // prepare update DO args
        GirlFriend girlFriend = new GirlFriend();
        BeanUtils.copyProperties(updateArgs, girlFriend);

        girlFriend.setModifyTime(new Date());
        girlFriend.setModifyBy("xxxx");

        // update DB
        girlFriendMapper.updateById(girlFriend);
    }

    @Override
    @Transactional
    public void delete(String id) throws NotFoundException {

        // check exists
        if (girlFriendMapper.selectById(id) == null) {
            throw new NotFoundException("not fount");
        }

        // delete DB
        girlFriendMapper.deleteById(id);
    }

    @Override
    public IPage<GirlFriend> pageQuery(GirlFriendQueryInBO args, int page, int size) {

        // TODO prepare query args
        QueryWrapper<GirlFriend> wrapper = new QueryWrapper<>();
        wrapper.eq("account_id", "xxxx");
        if (args.getIds() != null && args.getIds().length > 0) {
            wrapper.in("id", Arrays.asList(args.getIds()));
        }
        // TODO
        wrapper.orderByDesc("create_time");

        // page query
        IPage<GirlFriend> iPage = new Page<>(page, size);
        IPage<GirlFriend> pages = girlFriendMapper.selectPage(iPage, wrapper);

        return pages;
    }

}