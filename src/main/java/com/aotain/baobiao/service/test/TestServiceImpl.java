package com.aotain.baobiao.service.test;

import com.aotain.baobiao.mapper.test.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <P></P>
 */
@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private UserMapper userMapper;

    /**
     *
     * @return
     */
    @Override
    public String getOneForTest(){
        String value = userMapper.selectOne();
        return value;
    }

}
