package com.aotain.baobiao.controller;

import com.aotain.baobiao.service.test.TestService;
import com.aotain.baobiao.service.test.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Transactional(readOnly = true)
@RequestMapping("/user")
public class TestController {

    @Autowired()
    private TestService testService;

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("user",testService.getOneForTest());
        return modelAndView;
    }


}
