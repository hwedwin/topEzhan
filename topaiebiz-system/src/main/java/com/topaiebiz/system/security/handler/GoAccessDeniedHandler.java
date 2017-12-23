package com.topaiebiz.system.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * Description： 登录失败处理器
 * 
 * Author Aaron.Xue 
 *    
 * Date 2017年12月2日 上午9:58:09 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class GoAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException exception) throws IOException, ServletException {
		System.out.println("GoAccessDeniedHandler---------------------执行了" );
		response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.getWriter().print("{\"code\":-500,\"message\":\""+exception.getMessage()+"\"}");
        response.getWriter().flush();
	}

}
