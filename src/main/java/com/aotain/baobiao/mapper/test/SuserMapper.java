package com.aotain.baobiao.mapper.test;

import com.aotain.baobiao.model.test.Suser;

public interface SuserMapper {
    int insert(Suser record);

    int insertSelective(Suser record);
}