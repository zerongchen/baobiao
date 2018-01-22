package com.aotain.baobiao.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@EnableWebSecurity 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//	private CusUserDetailsService

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.headers().frameOptions().sameOrigin().httpStrictTransportSecurity().disable()//spring boot配置iframe同源可访问
			.and().antMatcher("*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*").authorizeRequests().anyRequest().fullyAuthenticated()
			.and().authorizeRequests().antMatchers("/home").permitAll()
			.and().authorizeRequests().antMatchers("/login").permitAll()
			.and().formLogin().loginPage("/dealLogin").permitAll()
//			.usernameParameter("username").passwordParameter("password")
		  	.and().logout().permitAll();
	}

	
	//配置Spring Security的Filter链
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/");
	}
	
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//    	auth
//    	.inMemoryAuthentication()
//    	.withUser("tomy").password("123456").roles("ADMIN");
//    }

//	@Bean
//	UserDetailsService cusUserDetailsService() {
//		return new CusUserDetailsService();
//	}
}
