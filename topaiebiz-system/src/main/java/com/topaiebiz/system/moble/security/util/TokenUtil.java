package com.topaiebiz.system.moble.security.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.system.moble.security.dto.TokenDto;
import com.topaiebiz.system.moble.security.service.TokenService;

/**
 * 
 * Description 校验token工具类 
 * 
 * Author Aaron.Xue 
 *    
 * Date 2017年10月17日 下午7:49:35 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Component
public class TokenUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext = null;

	/**
	 * Description 校验token
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param token
	 * @throws GlobalException
	 */
	public static void verifyToken(String token) throws GlobalException {
		TokenService ts = applicationContext.getBean(TokenService.class);
		ts.verifyToken(token);
	}
	
	/**
	 * Description 获取token详情，包括所属会员，所属店铺，电话
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param token
	 * @return
	 */
	public static TokenDto getTokenDetail(String token) {
		TokenService ts = applicationContext.getBean(TokenService.class);
		return ts.getTokenDetail(token);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (TokenUtil.applicationContext == null) {
			TokenUtil.applicationContext = applicationContext;
		}
	}
	
	//获取applicationContext
    public static ApplicationContext getApplicationContext() {
       return applicationContext;
    }
    
    //通过name获取 Bean.
    public static Object getBean(String name){
       return getApplicationContext().getBean(name);
    }
   
    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
       return getApplicationContext().getBean(clazz);
    }
   
    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name,Class<T> clazz){
       return getApplicationContext().getBean(name, clazz);
    }

}
