package com.aotain.baobiao.config.webflow;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.mvc.UrlFilenameViewController;
import org.springframework.webflow.context.servlet.FlowUrlHandler;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

import javax.servlet.ServletContext;

public class CusFlowHandlerMapping extends FlowHandlerMapping {


    @Override
    protected void initServletContext( ServletContext servletContext ) {
        super.initServletContext(servletContext);



    }

    @Override
    public void setFlowUrlHandler( FlowUrlHandler flowUrlHandler ) {
        super.setFlowUrlHandler((FlowUrlHandler) getUrlFilenameViewController());
    }



    @Bean
    protected UrlFilenameViewController getUrlFilenameViewController(){
        return new UrlFilenameViewController();
    }

}
