package com.topaiebiz.member.grade.exception;

import com.nebulapaas.web.exception.ExceptionInfo;

public enum MemberGradeExceptionEnum implements ExceptionInfo {
	
	MEMBERGRADE_GRADECODE_NOT_REPETITION("4000001", "Member level number cannot be duplicated!"),
	
	MEMBERGRADE_NAME_NOT_REPETITION("4000002", "Member level name cannot be duplicated!"),
	
	MEMBERGRADE_ID_NOT_NULL("4000003", "Membership level ID cannot be empty!"),
	
	MEMBERGRADE_ID_NOT_EXIST("4000004", "Membership level ID does not exist!"),
	
	MEMBERGRADE_CODE_EXCEED("4000009", "The current membership growth score cannot be higher than the previous grade!"),
	
	MEMBERGRADE_CODE_UNDER("4000010", "The current membership growth score cannot be lower than the next level!");
	
	
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
	MemberGradeExceptionEnum(String code, String defaultMessage) {
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
