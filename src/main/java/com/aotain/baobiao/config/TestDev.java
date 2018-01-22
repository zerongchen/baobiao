package com.aotain.baobiao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//@ConfigurationProperties(prefix = "test")
@Component
//@Configuration
public class TestDev {

    @Value("${test.field}")
    private String field;

    public String getField() {
        return field;
    }
}

