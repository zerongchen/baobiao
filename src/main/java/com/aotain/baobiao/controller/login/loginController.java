package com.aotain.baobiao.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/login")
public class loginController {


    @RequestMapping("/index")
    public String index(){
        return "login";
    }
}
