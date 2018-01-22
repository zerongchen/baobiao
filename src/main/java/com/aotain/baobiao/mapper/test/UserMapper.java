package com.aotain.baobiao.mapper.test;

import com.aotain.baobiao.model.test.User;

public interface UserMapper {

    int insert(User record);

    int insertSelective(User record);

    String selectOne();
}