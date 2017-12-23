package com.topaiebiz.sms.dahantc.controller;

import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.sms.dahantc.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Description 手机短信处理器 （引用三方：大汉三通）
 *
 * Author Aaron.Xue
 *
 * Date 2017年10月19日 下午2:10:09
 *
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 *
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@RestController
@RequestMapping("/app/sms/message")
public class MessageController {

	@Autowired
	private MessageService messageService;

	/**
	 *
	 * Description 发送手机验证码
	 *
	 * Author Aaron.Xue
	 *
	 * @param session
	 * @param telephone
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(path = "/sendCaptcha", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo sendCaptcha(HttpSession session, String telephone) throws Exception {
		// 6位验证码
		int randNum = (int) ((Math.random() * 9 + 1) * 100000);
//		int randNum = 111111; 
		// 获取用户名      
		messageService.sendCaptcha(telephone.trim(), "验证码："+String.valueOf(randNum)+",您正在贝因美亲子e站上使用验证码，验证码十分钟内有效。", String.valueOf(randNum));
		return new ResponseInfo();
	}

}
