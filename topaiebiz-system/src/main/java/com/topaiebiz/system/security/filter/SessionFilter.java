package com.topaiebiz.system.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


/**
 * Description: session过滤器，判断是否有权限。
 * 
 * Author: Aaron.Xue
 * 
 * Date: 2017年9月15日 下午5:52:39
 * 
 * Copyright: Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
public class SessionFilter implements Filter{

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
//		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		//app开头
		if (uri.startsWith("/app")) {
			chain.doFilter(request, response);
			return ;
		} 
		//|| uri.equals("/sysmgt/api/user/userTest"
		else if(uri.equals("/member/mgmt/addMerchantPerson")) {
			chain.doFilter(request, response);
			return ;
		}
//		List<String> urlList = SecurityContextUtils.getCurrentSystemUser().getUrlList();
//		//控制URL的，配置完整后放开
//		if(null == urlList) {
//			res.getWriter().print("{\"code\":\"-501\"}");
//			return ;
//		} 
//		else if(!urlList.contains(uri)){
//			res.getWriter().print("{\"code\":\"-501\"}");
//			return ;
//		} 
		else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}

