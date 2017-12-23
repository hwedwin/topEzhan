package com.topaiebiz.thirdparty.util;

import com.alibaba.fastjson.JSONObject;
import com.topaiebiz.thirdparty.config.WeChatConfig;
import com.topaiebiz.thirdparty.dto.AccessTokenDto;
import com.topaiebiz.thirdparty.dto.JsapiTicketDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Description 微信各种请求url工具类
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017/11/13 20:58
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class WeChatMethodUtil {

	private final static Logger logger = LoggerFactory.getLogger(WeChatMethodUtil.class);


	/**
	*
	* Description: 获取AccessToken
	*
	* Author: hxpeng
	* createTime: 2017/11/16
	*
	* @param:
	**/
//	@Cacheable(value = "accessTokenCache")
	public synchronized static String getTokenMethod(){
		String strUrl = WeChatConfig.getTokenUrl();
		String buf = postDataToWeiXin(strUrl, null, false);
		AccessTokenDto accessTokenDto = JSONObject.parseObject(buf,AccessTokenDto.class);
		if (accessTokenDto.getErrcode() == null || accessTokenDto.getErrcode() == "") {
			return accessTokenDto.getAccess_token();
		}
		return null;
	}


	/**
	*
	* Description: 获取AccessToken中的openId
	*
	* Author: hxpeng
	* createTime: 2017/11/13
	*
	* @param:
	**/
	public static String getAccessTokenString(String code){
		String buf="";
		try {
			String strUrl = WeChatConfig.getOpenID(code);
			buf = postDataToWeiXin(strUrl, null, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buf;
	}

	/**
	*
	* Description: 判断参数区分请求类型
	*
	* Author: hxpeng
	* createTime: 2017/11/16
	*
	* @param:
	**/
	public static String postDataToWeiXin(String strURL,String params,boolean appendBR) {
		String resString="";
		// 请求方式
		if(params!=null && params!="")
		{
			resString=postDataToWeiXin(strURL,params,"POST",appendBR);
		}else {
			resString=postDataToWeiXin(strURL,params,"GET",appendBR);
		}

		return resString;
	}


	public synchronized static String getJsapiTicketMethod() {
		logger.info("===========================================");
		logger.info("获取微信sdk凭证");
		JsapiTicketDto jsapiTicketDto = new JsapiTicketDto();
		String buf = postDataToWeiXin(WeChatConfig.getJsapiTicketUrl(), null, false);
		jsapiTicketDto = JSONObject.parseObject(buf, JsapiTicketDto.class);
		if (jsapiTicketDto.getErrcode().equals("0")) {
			return jsapiTicketDto.getTicket();
		}else{
			throw new RuntimeException("获取微信jsapiticket失败！");
		}
	}



	/**
	 * Description 请求微信
	 *
	 * @param strURL  提交的网址
	 * @param params  提交的参数
	 * @param method  提交方式
	 * @param appendBR  是否换行
	 * @return   返回的结果
	 * @throws  Exception
	 */
	public static String postDataToWeiXin(String strURL,String params,String method,boolean appendBR) {
		StringBuffer bufferRes = new StringBuffer();
		String resString="";
		try {
			URL realUrl=new URL(strURL);
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
			// 连接超时
			conn.setConnectTimeout(25000);
			// 读取超时 --服务器响应比较慢,增大时间
			conn.setReadTimeout(25000);
			HttpURLConnection.setFollowRedirects(true);
			// 请求方式
			conn.setRequestMethod(method);
			conn.setDoOutput(true);
			conn.setDoInput(true);
//			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0");
			conn.setRequestProperty("Referer", "https://api.weixin.qq.com/");
			conn.setRequestProperty("Charset", "UTF-8");
			conn.connect();
			// 获取URLConnection对象对应的输出流
			if(params!=null && params!="")
			{
				OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
				//out.write(URLEncoder.encode(params,"UTF-8"));
				out.write(params);
				out.flush();
				out.close();
			}
			InputStream in = conn.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in,"UTF-8"));
			String valueString = null;
			while ((valueString=read.readLine())!=null){
				bufferRes.append(valueString);
				if(appendBR){
					bufferRes.append("\r\n");
				}
			}
			resString=bufferRes.toString();
			logger.info(resString);
			in.close();
			if (conn != null) {
				conn.disconnect();
			}
			return resString;
		} catch (Exception e) {
			e.printStackTrace();
			resString=e.getMessage();
		}
		return resString;
	}



}
