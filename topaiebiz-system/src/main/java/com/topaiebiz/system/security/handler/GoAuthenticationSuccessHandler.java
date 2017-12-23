package com.topaiebiz.system.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.system.security.entity.SystemUserEntity;
import com.topaiebiz.system.security.util.SecurityContextUtils;

/**
 * Description：登录成功处理器 
 * 
 * Author Aaron.Xue 
 *    
 * Date 2017年12月2日 上午10:07:11 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class GoAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		System.out.println("GoAuthenticationSuccessHandler---------------------执行了" );
		SystemUserEntity currentSystemUser = SecurityContextUtils.getCurrentSystemUser();
		System.out.println(currentSystemUser.getId());
		ObjectMapper objectMapper = new ObjectMapper();  
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		response.getWriter().print(objectMapper.writeValueAsString(new ResponseInfo(currentSystemUser)));
		response.getWriter().flush();
	}

}
