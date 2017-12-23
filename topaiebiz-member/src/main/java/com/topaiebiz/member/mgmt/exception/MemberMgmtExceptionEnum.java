package com.topaiebiz.member.mgmt.exception;

import com.nebulapaas.web.exception.ExceptionInfo;

public enum MemberMgmtExceptionEnum implements ExceptionInfo {
	
	MEMBERMGMT_MEMBERCODE_NOT_REPETITION("4000005", "Member numbers cannot be duplicated!"),
	
	MEMBERMGMT_USERNAME_NOT_REPETITION("4000006", "Member user name cannot be duplicated!"),
	
	MEMBERMGMT_ID_NOT_NULL("4000007", "Member ID cannot be empty!"),
	
	MEMBERMGMT_ID_NOT_EXIST("4000008", "Member ID does not exist!"),
	
	MEMBERMGMT_USERNAME_NOT_NULL("4000019", "Member accounts cannot be empty!"),
	
	MEMBERMGMT_PASSWORD_NOT_NULL("4000020", "Member passwords cannot be empty!"),
	
	MEMBERMGMT_MEMBER_NOT_FOUND("4000021", "Membership account was not found!"),
	
	MEMBERMGMT_PASSWORD_ERROR("4000022", "Membership password error!"),
	
	MEMBERMGMT_ORDERID_NOT_NULL("4000023", "Order ID cannot be empty!"),
	
	MEMBERMGMT_PRICE_NOT_NULL("4000024", "Order Price cannot be empty!"),
	
	MEMBERMGMT_USER_EXISTENCE("4000025", "User already exists!"),
	
	MEMBERMGMT_USER_NO_EXISTENCE("4000026", "User already no exists!"),
	
	MEMBERMGMT_USERNAME_PASSWORD_NOT_NULL("4000027", "User username or password cannot be empty!"),
	
	MEMBERMGMT_CAPTCHA_ERROE("4000028", "Captcha error!"),
	
	MEMBERMGMT_CAPTCHA_NOT_NULL("4000029", "Captcha cannot be empty!"),
	
	MEMBERMGMT_PHONENUMBER_EXIST("4000030", "The user has been registered. Please login!"),
	
	MEMBERMGMT_FROZEN("4000031", "This account has been frozen. Please contact the administrator!"),
	
	MEMBERMGMT_PHONENUMBER_EXISTS("4000033", "User has been registered. Please change one!"),
	
	MEMBERMGMT_PAYPASSWORD_NOT_NULL("4000034", "Please enter the payment password!"),
	
	TYPE_NOT_NULL("4000039", "Type not null!");
	
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
	MemberMgmtExceptionEnum(String code, String defaultMessage) {
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
