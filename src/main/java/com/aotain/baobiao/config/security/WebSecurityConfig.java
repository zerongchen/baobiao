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

	/**
	 * <P>HttpSecurity 定义</P>
	 * access(String) 如果给定的SPEL表达是为true，允许访问
	 * anonymous() 允许匿名访问
	 * authenticated() 允许认证过的用户访问
	 * denyAll() 无条件拒绝访问
	 * fullyAuthenticated() 如果用户是完整认证的话(不是通remember-me功能认证的)就允许访问
	 * hasAnyAuthority(String ... ) 如果用户具备给定权限中的一个就允许访问
	 * hasAnyRole(String...) 如果用户具备给定角色中的一个就允许访问
	 * hasAuthority(String) 如果用户具备给定权限就允许访问
	 * hasIpAddress(String) 如果请求来自给定IP就允许访问
	 * hasRole(String) 如果用户具备给定角色就允许访问 ,会自动使用前缀 ROLE_
	 * not() 对其他访问方法的结果求反
	 * permitAll() 无条件允许访问
	 * remember() 如果用户是通过remember-me 功能认证的,就允许访问
	 *
	 *
	 * <p> Spring Security el 表达式</p>
	 * authentication 认证对象
	 * denyAll 结果始终为false
	 * hasAnyRole(list of role) 结果用户被授予了列表中任意的指定角色，为true
	 * hasRole(role) 结果用户被授予了列表指定角色，为true
	 * hasIpAddress(ip address) 如果请求来自给定IP 结果为true
	 * isAnonymous () 匿名用户true
	 * isAuthenticated() 如果用户已经认证 true
	 * isFullyAuthenticated() 如果用户是完整认证的话(不是通remember-me功能认证的) --true
	 * isRemember() 如果用户是通过remember-me 功能认证的,true
	 * permitAll 始终为true
	 * principal 用户的principal 对象
	 *
	 *
	 * csrf 防护 服务端自动生成token ，
	 *
	 * 使用规则:具体的放在前面，不具体(anyRequest())的放在后面,否则会被覆盖
	 * @param http
	 * @throws Exception
	 */
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


		http.authorizeRequests()
				.and().authorizeRequests().antMatchers("/user/test").permitAll()//指定谁都可以访问的路径
				.and().authorizeRequests().antMatchers("/admin").access("isAuthenticated() and principal.username='admin'") //认证的，且用户名为admin才能访问该URL ，与前端的 authorize-url 对应才渲染效果
				.anyRequest().authenticated() //其余的都需要认证
				.and().formLogin()
				.loginPage("/login")
				//设置默认登录成功跳转页面
				.defaultSuccessUrl("/user/index").failureUrl("/login?error").permitAll()
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
				.permitAll()
				.and().requiresChannel().antMatchers("/user/test").requiresSecure() //指定https 安全通道
				.and().csrf().disable() //禁止CSRF防护
				.httpBasic().realmName("csf") //起用http basic认证
				;

	}

	
	//配置Spring Security的Filter链
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/");
	}
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		内容权限验证
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
