package com.topaiebiz.system.security.util;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.topaiebiz.system.security.entity.SystemUserEntity;



/**
 * Description: 安全上下文工具类。获取安全上下文对象、当前登录用户等。
 * 
 * Author: Amir Wang
 * 
 * Date: 2017年9月23日 上午5:31:46
 * 
 * Copyright: Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Component
public class SecurityContextUtils {
	
	/**
	 * Description: 获取安全上下文对象。
	 *
	 * Author: Amir Wang
	 * 
	 * @return 安全上下文对象。
	 */
	public static SecurityContext getSecurityContext() {
		return SecurityContextHolder.getContext();

	}

	/**
	 * Description: 获取当前系统用户。
	 *
	 * Author: Amir Wang
	 * 
	 * @return 当前系统用户。
	 */
	public static SystemUserEntity getCurrentSystemUser() {
		// 暂时写死。
//		SystemUserEntity systemUser = new SystemUserEntity();
//		systemUser.setId(1111111111L);
//		systemUser.setMobilePhone("13333333333");
//		systemUser.setUsername("Admin");
//		systemUser.setRealname("超级管理员");
//		systemUser.setPassword("Admin123");
//		return systemUser;
		//从session中取出当前登录用户
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); 
//		Object entity = request.getSession().getAttribute("user");
//		return (SystemUserEntity)entity;
		return (SystemUserEntity) getSecurityContext().getAuthentication().getPrincipal();
	}
}

