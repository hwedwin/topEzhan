package com.topaiebiz.system.moble.security.service;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.system.moble.security.dto.TokenDto;

public interface TokenService {

	/**
	 * Description 登录
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param telephone
	 * @return
	 */
	String login(String telephone) throws GlobalException;
	
	/**
	 * Description 校验token
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param token
	 * @throws GlobalException 
	 */
	void verifyToken(String token) throws GlobalException;
	
	/**
	 * Description 根据token获取登录用户的详细信息
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param token
	 * @return
	 */
	TokenDto getTokenDetail(String token);
	
}
