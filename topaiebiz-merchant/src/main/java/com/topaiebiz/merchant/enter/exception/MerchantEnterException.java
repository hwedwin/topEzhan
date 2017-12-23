package com.topaiebiz.merchant.enter.exception;

import com.nebulapaas.web.exception.ExceptionInfo;

/**
 * Description: 商家入驻异常枚举类
 * 
 * Author : Anthony
 * 
 * Date :2017年10月14日 下午3:02:44
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public enum MerchantEnterException implements ExceptionInfo {

	MERCHANTENTER_LOGINNAME_NOT_REPETITION("3000004", "Account cannot be duplicated!"),

	MERCHANTENTER_TELEPHONE_NOT_REPETITION("3000005", "Phone Numbers cannot be repeated!"),

	MERCHANTENTER_PASSWORD_NOT_REPETITION("3000006", "The password cannot be repeated!"),

	MERCHANTENTER_CONFIRMPASSWORD_NOT_REPETITION("3000007", "Make sure the password is incorrect!"),

	MERCHANTENTER_LOGINNAME_NOT_EXIST("3000008", "LoginName does not exist!"),

	MERCHANTENTER_PASSWORD_NOT_EXIST("3000009", "Password does not exist!"),

	MERCHANTENTER_LOGIN_SUCCESS("3000010", "login successfully"),

	MERCHANTENTER_LOGIN_ERROR("3000011", "login error"),

	MERCHANTENTER_NOT_LOGIN("30000012", "Business not logged in!!!"),

	MERCHANTENTER_LOGGED_IN("30000013", "The merchant has logged in!!!"),
	
	MERCHANTENTER_NO_APPLICATION("30000014","The merchant No application!!!"),
	
	MERCHANTENTER_PASS_THE_AUDIT("30000015","The merchant pass the audit !!!"),

	MERCHANTENTER_NOT_ENTER("30000018","The merchant did not enter!!!"),
	
	FREIGHTNAME_NOT_NOLL("30000019","The template name cannot be empty!!!"),
	
	PRICING_NOT_NOLL("30000020","Remember that the price method cannot be null!!!"),
	
	CANT_CREATE_MULTIPLE_STORES("30000021","Can't create multiple stores!");
	
	/** 异常代码。 */
	private String code;

	/** 异常对应的默认提示信息。 */
	private String defaultMessage;

	/** 异常对应的原始提示信息。 */
	private String originalMessage;

	/** 当前请求的URL。 */
	private String requestUrl;

	/** 默认的转向（重定向）的URL，默认为空。 */
	private String defaultRedirectUrl = "";

	/** 异常对应的响应数据。 */
	private Object data;

	/**
	 * Description: 根据异常的代码、默认提示信息构建一个异常信息对象。
	 *
	 * Author: Anthony
	 * 
	 * @param code
	 *            异常的代码。
	 * 
	 * @param defaultMessage
	 *            异常的默认提示信息。
	 */
	MerchantEnterException(String code, String defaultMessage) {
		this.code = code;
		this.defaultMessage = defaultMessage;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getDefaultMessage() {
		return defaultMessage;
	}

	@Override
	public String getOriginalMessage() {
		return originalMessage;
	}

	@Override
	public void setOriginalMessage(String originalMessage) {
		this.originalMessage = originalMessage;
	}

	@Override
	public String getRequestUrl() {
		return requestUrl;
	}

	@Override
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	@Override
	public String getDefaultRedirectUrl() {
		return defaultRedirectUrl;
	}

	@Override
	public void setDefaultRedirectUrl(String defaultRedirectUrl) {
		this.defaultRedirectUrl = defaultRedirectUrl;
	}

	@Override
	public Object getData() {
		return data;
	}

	@Override
	public void setData(Object data) {
		this.data = data;
	}
}
