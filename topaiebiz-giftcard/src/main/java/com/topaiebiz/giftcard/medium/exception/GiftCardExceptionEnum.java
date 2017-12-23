package com.topaiebiz.giftcard.medium.exception;

import com.nebulapaas.web.exception.ExceptionInfo;

/**
 * 
 * Description： 礼卡异常枚举。
 * 
 * 
 * Author Murray.Li
 * 
 * Date 2017年9月25日 上午10:48:21
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public enum GiftCardExceptionEnum implements ExceptionInfo {
	

	CARDMEDIUM_ID_NOT_NULL("5000021", "THE medium id not is null."),

	CARDMEDIUM_NAME_NOT_REPETITION("5000022", "THE medium name not is  repetition."),

	CARDMEDIUM_CODE_NOT_REPETITION("5000023", "THE medium code not is  repetition."),

	CARDMEDIUM_ID_NOT_EXIST("5000024", "THE medium id  not is  exist.");

	
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

	GiftCardExceptionEnum(String code, String defaultMessage) {
		this.code = code;
		this.defaultMessage = defaultMessage;
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

	@Override
	public String getCode() {
		return code;
	}

}
