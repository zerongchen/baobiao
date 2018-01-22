package com.aotain.baobiao.baobiao;

import com.aotain.baobiao.BaobiaoApplication;
import com.aotain.baobiao.config.TestDev;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BaobiaoApplication.class)
public class TestD {

    @Autowired
    private TestDev testdev;

    @Test
    public void test(){
        System.out.println("value --->"+testdev.getField());
    }
}
