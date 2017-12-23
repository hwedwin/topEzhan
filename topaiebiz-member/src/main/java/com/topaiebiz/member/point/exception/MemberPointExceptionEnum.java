package com.topaiebiz.member.point.exception;

import com.nebulapaas.web.exception.ExceptionInfo;

public enum MemberPointExceptionEnum implements ExceptionInfo {
	
	MEMBERPOINT_ID_NOT_NULL("4000013", "The member integral ID cannot be empty!"),
	
	MEMBERPOINT_ID_NOT_EXIST("4000014", "The member integral ID does not exist!"),
	
	MEMBERPOINT_ORDERID_NOT_NULL("4000015", "The member order ID cannot be empty!"),
	
	MEMBERPOINT_USAGESCORE_NOT_NULL("4000016", "The member usageScore  cannot be empty!"),
	
	MEMBERPOINT_DEDUCTIBLEAMOUNT_NOT_NULL("4000017", "The member deductibleAmount cannot be empty!"),
	
	MEMBERPOINT_USAGESCORE_NOT_MORE("4000018", "Members are not able to integrate!"),
	
	MEMBERPOINT_ALREADY_RECEIVED("4000035", "You have received a new exclusive access integral integral, there are more!"),
	
	MEMBERPOINT_ALREADY_RECEIVED_DAY("4000036", "You have a try tomorrow today integral!");
	
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
	 * Author: Scott.Yang
	 * 
	 * @param code
	 *            异常的代码。
	 * 
	 * @param defaultMessage
	 *            异常的默认提示信息。
	 */
	MemberPointExceptionEnum(String code, String defaultMessage) {
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
