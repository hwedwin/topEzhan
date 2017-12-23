package com.topaiebiz.system.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * Description： TODO (描述Java类的用途或使用说明。)
 * 
 * Author Aaron.Xue
 * 
 * Date 2017年12月2日 上午10:03:13
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class GoAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		System.out.println("GoAuthenticationEntryPoint---------------------执行了" );
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		response.getWriter().print("{\"code\":-500,\"message\":\"" + exception.getMessage() + "\"}");
		response.getWriter().flush();
	}

}
