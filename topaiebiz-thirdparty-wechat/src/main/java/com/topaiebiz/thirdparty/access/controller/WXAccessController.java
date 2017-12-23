package com.topaiebiz.thirdparty.access.controller;

import com.topaiebiz.thirdparty.access.util.AccessCheckSignUtil;
import com.topaiebiz.thirdparty.config.WeChatConfig;
import com.topaiebiz.thirdparty.pay.util.LocalWXPayUtil;
import com.topaiebiz.thirdparty.util.Sha1Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Description 微信服务器接入以及授权验证通知/回调等等
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017/11/12 17:15
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */


@RestController
@RequestMapping("/app/wx")
public class WXAccessController {

	private final static Logger logger = LoggerFactory.getLogger(WXAccessController.class);

	/**
	*
	* Description: 微信接入验证接口
	*
	* Author: hxpeng
	* createTime: 2017/11/12
	*
	* @param:
	**/
	@ResponseBody
	@RequestMapping(value = "/access", method = RequestMethod.GET)
	public String servletMain(HttpServletRequest request, HttpServletResponse response) {
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");

		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (AccessCheckSignUtil.checkSignature(signature, timestamp, nonce)) {
			try {
				response.getWriter().write(echostr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	*
	* Description: 微信SDK授权
	*
	* Author: hxpeng
	* createTime: 2017/11/16
	*
	* @param:
	**/

	@RequestMapping(value = "/wechatSdkAuth", method = RequestMethod.GET)
	public ResponseEntity<?> wechatSdkAuth(String url){
		if(!StringUtils.isEmpty(url)){
			Map<String,String> map = new HashMap<>();
			String noncestr = LocalWXPayUtil.generateNonceStr();
			String timestamp = String.valueOf(LocalWXPayUtil.getCurrentTimestamp());
			String appId = WeChatConfig.APP_ID;

			SortedMap<String, String> signParams = new TreeMap<>();
			signParams.put("noncestr", noncestr);
			signParams.put("timestamp", timestamp);
			signParams.put("jsapi_ticket", WeChatConfig.getJsapiTicket());
			signParams.put("url", url);
			String signature;
			try {
				signature = Sha1Util.createSHA1Sign(signParams);
				map.put("appId", appId);
				map.put("timestamp", timestamp);
				map.put("noncestr", noncestr);
				map.put("signature", signature);
			} catch (Exception e) {
				throw new RuntimeException("[校验微信SDK,生成签名错误：]" + e.getMessage());
			}
			return new ResponseEntity(map, HttpStatus.OK);
		}
		return new ResponseEntity(null, HttpStatus.OK);
	}



}
