package com.topaiebiz.system.district.exception;

import com.nebulapaas.web.exception.ExceptionInfo;

public enum DistrictExceptionEnum implements ExceptionInfo {

	DISTRICT_ID_NOT_NULL("8000102", "District's id can't be null"),
	
	DISTRICT_CODE_NOT_EXIST("8000103", "District's code can't be null。"),

	DISTRICT_CODE_CANNOT_BE_REPEATED("8000104", "District's code cannot be repeated"),
	
	DISTRICT_TYPE_ID_NOT_EXIST("8000105", "DistrictType's id can't be null"),
	
	DISTRICT_NOT_EXIST("8000106", "District does not exist!"),
	
	DISTRICT_TYPE_NOT_EXIST("8000107", "DistrictType does not exist!"),

	DISTRICT_TYPE_CODE_CANNOT_BE_NULL("8000108", "District's code cannot be repeated"),
	
	DISTRICT_TYPE_CODE_CANNOT_BE_REPEATED("8000109", "District's code cannot be repeated");


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
	DistrictExceptionEnum(String code, String defaultMessage) {
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
