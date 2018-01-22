package com.aotain.baobiao.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class Msg {
        private String title;
        private String content;
        private String extraInfo;
}
