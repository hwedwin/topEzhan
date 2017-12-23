package com.topaiebiz.decorate.exception;

import com.nebulapaas.web.exception.ExceptionInfo;

/**
 * Description 装修异常类
 * 
 * Author Aaron.Xue 
 *    
 * Date 2017年9月23日 下午8:00:09 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */

public enum CollocateExceptionEnum implements ExceptionInfo{
	
	TITLE_ID_NOT_NULL("13000001", "The title is not null!"),
	
	JUMP_URL_NOT_NULL("13000002", "The jump URL is not null!"),
	
	JUMP_ID_NOT_NULL("13000003", "The jump ID is not null!"),
	
	GOODS_ID_NOT_NULL("13000004", "The goods ID is not null!"),
	
	IS_NOT_GIFTCARD_TYPE("13000005", "Is not giftcard type!"),
	
	SORTNO_NOT_NULL("13000006", "SortNo is not null!"),
	
	IMAGE_NOT_NULL("13000007", "Image is not null!"),
	
	JUMP_TYPE_NOT_NULL("13000008", "The jump type is not null!");
	
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
	 * Description 根据异常的代码、默认提示信息构建一个异常信息对象。
	 *
	 * Author Hedda
	 * 
	 * @param code
	 *            异常的代码。
	 * 
	 * @param defaultMessage
	 *            异常的默认提示信息。
	 */
	CollocateExceptionEnum(String code, String defaultMessage) {
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
