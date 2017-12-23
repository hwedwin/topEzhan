package com.topaiebiz.sms.dahantc.service;

import com.topaiebiz.sms.dahantc.dto.MessageDto;

/**
 * Description 短信发送业务层接口 
 * 
 * Author Aaron.Xue 
 *    
 * Date 2017年10月19日 下午2:47:17 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public interface MessageService {


	/**
	 * Description 发送验证码
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param telephone
	 * 		接收手机号，多个以英文逗号隔开
	 * @param content
	 * 		短信内容
	 * @param captcha
	 * 		验证码
	 * @return
	 * @throws Exception
	 */
	MessageDto sendCaptcha(String telephone, String content, String captcha) throws Exception;
	
	/**
	 * Description： 校验验证码是否正确，是否过期,如果过期或者不正确直接抛出异常
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param telephone
	 * @param captcha
	 * @return
	 * 	   boolean falg
	 */
	boolean verifyCaptcha(String telephone, String captcha);
	
}
