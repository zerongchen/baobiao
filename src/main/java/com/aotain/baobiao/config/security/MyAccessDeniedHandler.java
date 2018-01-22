package com.aotain.baobiao.config.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//handle 403 page

public class MyAccessDeniedHandler implements AccessDeniedHandler {

 @Override
 public void handle(HttpServletRequest httpServletRequest,
                    HttpServletResponse httpServletResponse,
                    AccessDeniedException e) throws IOException, ServletException {

     Authentication auth = SecurityContextHolder.getContext().getAuthentication();

     if (auth != null) {
         System.out.println("User '" + auth.getName()+ "' attempted to access the protected URL: "+ httpServletRequest.getRequestURI());
     }else {
    	 System.out.println("成功了，但是还有问题："+auth.getName());
     }

     httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/403");

 }
}