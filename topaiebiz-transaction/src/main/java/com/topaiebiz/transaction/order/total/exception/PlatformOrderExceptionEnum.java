package com.topaiebiz.transaction.order.total.exception;

import com.nebulapaas.web.exception.ExceptionInfo;

/**
 * 
 * Description： 平台订单的异常枚举 
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月23日 下午7:54:59 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public enum PlatformOrderExceptionEnum implements ExceptionInfo {
	
	PLATFORMORDER_ID_NULL("6000101","PlatformOrder ID id not null."),
	PLATFORMORDER_ID_NOT_EXIST("6000102","PlatformOrder ID does not exist.");
	
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
	 * 
	 * Description： 根据异常的代码、默认提示信息构建一个异常信息对象。
	 * 
	 * Author zhushuyong   
	 * 
	 * @param code
	 * @param defaultMessage
	 */
	PlatformOrderExceptionEnum(String code, String defaultMessage) {
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
