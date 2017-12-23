package com.topaiebiz.system.moble.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.system.moble.security.dto.UserDto;
import com.topaiebiz.system.moble.security.service.TokenService;
import com.topaiebiz.system.moble.security.util.TokenUtil;

/**
 * Description 用户登录处理器 
 * 
 * Author Aaron.Xue 
 *    
 * Date 2017年10月17日 下午8:27:27 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@RestController
@RequestMapping("/app/security/login")
public class LoginController {
//
	@Autowired
	private TokenService tokenService;
	
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	@ResponseBody
	public ResponseInfo login(UserDto userDto) throws GlobalException {
		//调用service验证用户名密码
		String token = tokenService.login(userDto.getUserName());
		return new ResponseInfo(token);
	}
	

	@RequestMapping(value = "/test",method=RequestMethod.GET)
	@ResponseBody
	public ResponseInfo test(String token, UserDto userDto) throws GlobalException {
		//校验token
		TokenUtil.verifyToken(token);
		return new ResponseInfo();
	}
	
}
