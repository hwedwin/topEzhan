//package com.topaiebiz.system.security.filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//
//import org.springframework.security.access.SecurityMetadataSource;
//import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
//import org.springframework.security.access.intercept.InterceptorStatusToken;
//import org.springframework.security.web.FilterInvocation;
//import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
///**
// * Description：该过滤器的主要作用就是通过spring著名的IoC生成securityMetadataSource。 
// * securityMetadataSource相当于本包中自定义的MyInvocationSecurityMetadataSourceService。 
// * 该MyInvocationSecurityMetadataSourceService的作用提从数据库提取权限和资源，装配到HashMap中， 
// * 供Spring Security使用，用于权限校验。 
// * 
// * Author Aaron.Xue 
// *    
// * Date 2017年12月2日 下午5:02:47 
// * 
// * Copyright Cognieon technology group co.LTD. All rights reserved.
// * 
// * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
// */
//public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
//
//	private FilterInvocationSecurityMetadataSource securityMetadataSource;
//
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//
//		FilterInvocation fi = new FilterInvocation(request, response, chain);
//		invoke(fi);
//
//	}
//
//	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
//		return this.securityMetadataSource;
//	}
//
//	public Class<? extends Object> getSecureObjectClass() {
//		return FilterInvocation.class;
//	}
//
//	public void invoke(FilterInvocation fi) throws IOException, ServletException {
//		InterceptorStatusToken token = super.beforeInvocation(fi);
//		try {
//			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
//		} finally {
//			super.afterInvocation(token, null);
//		}
//	}
//
//	@Override
//	public SecurityMetadataSource obtainSecurityMetadataSource() {
//		return this.securityMetadataSource;
//	}
//
//	public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
//		this.securityMetadataSource = securityMetadataSource;
//	}
//
//	public void destroy() {
//
//	}
//
//	public void init(FilterConfig filterconfig) throws ServletException {
//
//	}
//
//}
