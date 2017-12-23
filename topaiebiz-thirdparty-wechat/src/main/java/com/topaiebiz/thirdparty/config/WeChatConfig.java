package com.topaiebiz.thirdparty.config;

import com.topaiebiz.thirdparty.util.WeChatMethodUtil;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URLEncoder;
import java.text.MessageFormat;

/**
 * Description 微信配置类
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017/11/13 10:46
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class WeChatConfig {

	/**
	 * 公众号APPID
	 */
	public final static String APP_ID = "wx39c064f7fdf78b61";

	/**
	 *  公众号密码
	 */
	public final static String APP_SECRET = "b2754ef4e73e48de85edf5b431a322e7";

	/**
	 *  商户号
	 */
	public final static String MCH_ID = "1490695562";

	/**
	 * 设备号
	 */
	public final static String DEVICE_INFO = "WEB";

	/**
	 * 签名类型
	 */
	public final static String SIGN_TYPE = "MD5";

	/**
	 * 微信统一下单路径
	 */
	public final static String WECHAT_PAY = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	/**
	 * 微信统一下单路径
	 */
	public final static String REFUND_PAY = "https://api.mch.weixin.qq.com/secapi/pay/refund";

	/**
	 * 支付回调地址
	 */
	public final static String PAY_NOTIFY_URL = "http://m.qinziezhan.com/app/payment/mobile/order/wechatPayAsyncNotic";

	/**
	 * 授权之后请求的后台地址
	 */
	public final static String AUTH_URL = "http://m.qinziezhan.com/app/member/mgmt/auth";

	/**
	 * 授权之后请求的后台地址，之后又跳转的前台地址
	 */
	public final static String AUTH_QIANTAI_URL = "http://www.baidu.com";

	/**
	 * 商品平台密钥key
	 */
	public final static String APP_KEY = "1cqdYaUKRoHmrIX0PzxaE0JrO0aNDK4u";

	/**
	 * token可变，非常量
	 */
	private static String ACCESS_TOKEN = null;

	/**
	 * JS SDK 授权门票
	 */
	private static String JSAPITICKET = null;

	/**
	 * 证书路径
	 */
	private static String CERTURL = "/home/topaiebiz/mmall/apiclient_cert.p12";

	/**
	 * 证书 字节数组
 	 */
//	private static InputStream CERTINPUTSTREAM;
	private static byte[] CERTBYTEARRAY;


	/**
	*
	* Description: 获取access_token,非授权token
	*
	* Author: hxpeng
	* createTime: 2017/11/16
	*
	* @param:
	**/
	public static String getAccessToken(){
		if(StringUtils.isEmpty(ACCESS_TOKEN)){
			ACCESS_TOKEN = WeChatMethodUtil.getTokenMethod();
		}
		return ACCESS_TOKEN;
	}

	/**
	 *
	 * Description: 获取access_token
	 *
	 * Author: hxpeng
	 * createTime: 2017/11/16
	 *
	 * @param:
	 **/
	public static String getJsapiTicket(){
		if(StringUtils.isEmpty(JSAPITICKET)){
			JSAPITICKET = WeChatMethodUtil.getJsapiTicketMethod();
		}
		return JSAPITICKET;
	}

	/**
	*
	* Description: 初始化证书 静态代码块
	*
	* Author: hxpeng
	* createTime: 2017/11/18
	*
	* @param:
	**/
	public static byte[] getCertByteArray() throws IOException {
		if(null == CERTBYTEARRAY){
			File file = new File(CERTURL);
			CERTBYTEARRAY = new byte[(int) file.length()];
			synchronized (CERTBYTEARRAY){
				InputStream inputStream = new FileInputStream(file);
				inputStream.read(CERTBYTEARRAY);
				inputStream.close();
			}
			return CERTBYTEARRAY;
		}else{
			return CERTBYTEARRAY;
		}
	}

	/**
	*
	* Description: 获取token url,method参照微信名称
	*
	* Author: hxpeng
	* createTime: 2017/11/16
	*
	* @param:
	**/
	public static String getTokenUrl()
	{
		String urlString=MessageFormat.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}", APP_ID,APP_SECRET);
		return urlString;
	}

	/**
	 *
	 * Description 获取微信网页授权的access_Code的url
	 *
	 * @param redirect_uri 业务跳转路径
	 * @param getUserInfo 是否需要获取到用户其他基本信息
	 * @param token 是否需要获取到用户其他基本信息
	 * @return
	 */
	public static String getUserinfoAccessCode(String redirect_uri, boolean getUserInfo)
	{
		String scope = "";
		if(getUserInfo){
			scope = "snsapi_userinfo";
		}else{
			scope = "snsapi_base";
		}
		try {
			redirect_uri=URLEncoder.encode(redirect_uri,"utf-8");
			return MessageFormat.format("https://open.weixin.qq.com/connect/oauth2/authorize?appid={0}&redirect_uri={1}&response_type=code&scope={2}&state=1#wechat_redirect",APP_ID, redirect_uri, scope);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 *
	 * Description 根access_Code获取微信openId
	 *
	 * @param code
	 * @return
	 */
	public static String getOpenID(String code)
	{
		return MessageFormat.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code",APP_ID,APP_SECRET,code);
	}

	/**
	 * 获取js sdk的ticket
	 * @return
	 */
	public static String getJsapiTicketUrl(){
		String urlString = MessageFormat.format("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={0}&type=jsapi",WeChatConfig.getAccessToken());
		return urlString;
	}




}
