package com.topaiebiz.system.security.config;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.topaiebiz.system.security.filter.SessionFilter;

/**
 * Description: session过滤器配置，支持跨域访问。
 * 
 * Author: Amir Wang 
 *    
 * Date: 2017年9月15日 下午5:11:53 
 * 
 * Copyright: Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Configuration
public class SessionFilterConfig extends WebMvcConfigurerAdapter {

	@Bean
	public FilterRegistrationBean sessionFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(sessionFilter());
		registration.addUrlPatterns("/*");
		registration.setName("sessionFilter");
		registration.setOrder(Ordered.LOWEST_PRECEDENCE);
		return registration;
	}

	@Bean
	public Filter sessionFilter() {
		return new SessionFilter();
	}
}
