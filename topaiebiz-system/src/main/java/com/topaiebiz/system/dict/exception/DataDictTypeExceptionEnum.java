package com.topaiebiz.system.dict.exception;

import com.nebulapaas.web.exception.ExceptionInfo;

public enum DataDictTypeExceptionEnum implements ExceptionInfo {

	DICTIONARYTYPE_ID_NOT_NULL("8000001", "The report ID is null."),

	DICTIONARYTYPE_ID_NOT_EXIST("8000002", "Dictionary Type ID does not exist!"),

	DICTIONARYTYPE_TYPENAME_NOT_REPETITION("8000003", "The data dictionary name cannot be repeated！"),

	DICTIONARYTYPE_TYPECODE_NOT_REPETITION("8000004",
			"The unique encoding of the data dictionary type cannot be repeated！"),

	DICTIONARYTYPE__DESCRIPTION_NOT_REPETITION("8000005",
			"The description of the data dictionary type cannot be repeated！"),

	DICTIONARY_DICTCODE_NOT_REPETITION("8000006", "The encoding of the data dictionary cannot be repeated"),

	DICTIONARY_DICTVALUE_NOT_REPETITION("8000007", "The value of the data dictionary cannot be repeated"),

	DICTIONARY_ID_NOT_NULL("8000008", "The data dictionary information id cannot be empty!"),

	DICTIONARY_MEMO_NOT_REPETITION("8000009", "The description of data dictionary information cannot be repeated!");

	

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
	DataDictTypeExceptionEnum(String code, String defaultMessage) {
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
