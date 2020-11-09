package com.sergio.out.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sergio.out.mybatis.entity.GirlFriend;
import com.sergio.out.service.bo.GirlFriendCreateInBO;
import com.sergio.out.service.bo.GirlFriendQueryInBO;
import com.sergio.out.service.bo.GirlFriendUpdateInBO;
import org.apache.ibatis.javassist.NotFoundException;

public interface GirlFriendService {

    GirlFriend loadById(String id);

    GirlFriend create(GirlFriendCreateInBO createArgs);

    void update(GirlFriendUpdateInBO updateArgs) throws NotFoundException;

    void delete(String id) throws NotFoundException;

    IPage<GirlFriend> pageQuery(GirlFriendQueryInBO args, int page, int size);
}