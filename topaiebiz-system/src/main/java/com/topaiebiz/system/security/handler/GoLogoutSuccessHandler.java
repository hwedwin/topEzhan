package com.topaiebiz.system.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * Description： 退出成功
 * 
 * Author Aaron.Xue 
 *    
 * Date 2017年12月2日 上午10:08:09 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class GoLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		System.out.println("GoLogoutSuccessHandler---------------------执行了");
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		response.getWriter().print("{\"code\":200,\"message\":\"已注销\"}");
		response.getWriter().flush();
	}

}
