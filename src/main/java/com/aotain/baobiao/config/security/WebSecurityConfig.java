package com.aotain.baobiao.config.security;

import com.aotain.baobiao.service.security.CusUserDetailsService;
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

//	private CusUserDetailsService cusUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		http.csrf().disable();
//		http.headers().frameOptions().sameOrigin().httpStrictTransportSecurity().disable()//spring boot配置iframe同源可访问
//			.and().antMatcher("*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*").authorizeRequests().anyRequest().fullyAuthenticated()
//			.and().authorizeRequests().antMatchers("/home").permitAll()
//			.and().authorizeRequests().antMatchers("/login").permitAll()
//			.and().formLogin().loginPage("/dealLogin").permitAll()
//			.usernameParameter("username").passwordParameter("password")
//		  	.and().logout().permitAll();

//		http.authorizeRequests().antMatchers("/admin/*").authenticated().and().formLogin().and().httpBasic().and()
//				.authorizeRequests().antMatchers("/user/*").permitAll();

		http.authorizeRequests()
				.anyRequest().authenticated()
				.and().formLogin()
//				.loginPage("/login")
				//设置默认登录成功跳转页面
				.defaultSuccessUrl("/user/test").failureUrl("/login?error").permitAll()

				.and().authorizeRequests().antMatchers("/user/index").permitAll()
//				.and().authorizeRequests().anyRequest()
				.and()
//				//开启cookie保存用户数据
				.rememberMe()
//				//设置cookie有效期
				.tokenValiditySeconds(60*60*24)
//				//设置cookie的私钥
				.key("123456")
				.and()
				.logout()
				//默认注销行为为logout，可以通过下面的方式来修改 ,logout 的action
				.logoutUrl("/custom-logout")
				//设置注销成功后跳转页面，默认是跳转到登录页面
				.logoutSuccessUrl("/login")
//				.and().authorizeRequests()
				.permitAll();
	}

	
	//配置Spring Security的Filter链
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/");
	}
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//    	auth
//    	.inMemoryAuthentication()
//    	.withUser("tomy").password("123456").roles("ADMIN");
    	auth.userDetailsService(cusUserDetailsService());
    }

	@Bean
	UserDetailsService cusUserDetailsService() {
		return new CusUserDetailsService();
	}
}
