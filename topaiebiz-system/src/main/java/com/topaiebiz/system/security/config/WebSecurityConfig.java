package com.topaiebiz.system.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.topaiebiz.system.moble.security.util.MD5Util;
import com.topaiebiz.system.security.handler.GoAccessDeniedHandler;
import com.topaiebiz.system.security.handler.GoAuthenticationEntryPoint;
import com.topaiebiz.system.security.handler.GoAuthenticationFailureHandler;
import com.topaiebiz.system.security.handler.GoAuthenticationSuccessHandler;
import com.topaiebiz.system.security.handler.GoLogoutSuccessHandler;
import com.topaiebiz.system.security.service.impl.SystemUserServiceImpl;

/**
 * Description： security的配置类
 * 
 * Author Aaron.Xue
 * 
 * Date 2017年12月1日 下午2:21:14
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	UserDetailsService customUserService() { // 注册UserDetailsService 的bean
		return new SystemUserServiceImpl();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService()).passwordEncoder(new PasswordEncoder() {
			@Override
			public String encode(CharSequence rawPassword) {
				return MD5Util.encode((String) rawPassword);
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return encodedPassword.equals(MD5Util.encode((String) rawPassword));
			}
		});

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling().accessDeniedHandler(new GoAccessDeniedHandler())
				.authenticationEntryPoint(new GoAuthenticationEntryPoint()).and().authorizeRequests()
				.antMatchers("/app/**").permitAll()
				.antMatchers("/member/mgmt/addMerchantPerson").permitAll()
				//.antMatchers("/sysmgt/api/user/userTest").permitAll()
				.anyRequest().authenticated().and().csrf().disable().formLogin()
				.loginProcessingUrl("/sysmgt/api/user/login").permitAll()
				.successHandler(new GoAuthenticationSuccessHandler())
				.failureHandler(new GoAuthenticationFailureHandler()).and().logout().logoutUrl("/sysmgt/api/user/logout")
				.logoutSuccessHandler(new GoLogoutSuccessHandler()).invalidateHttpSession(true)
				.deleteCookies("JSESSIONID");
	}

}
