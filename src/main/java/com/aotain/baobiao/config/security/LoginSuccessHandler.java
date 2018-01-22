package com.aotain.baobiao.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		System.out.println("LoginSuccessHandler:onAuthenticationSuccess=>"+authentication.getName());
		// 获得授权后可得到用户信息 可使用SUserService进行数据库操作
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
