package com.topaiebiz.goods.category.frontend.exception;

import com.nebulapaas.web.exception.ExceptionInfo;

/**
 * Description 商品异常枚举。  
 * 
 * Author Hedda 
 *    
 * Date 2017年9月23日 下午8:00:09 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */

public enum FrontendCategoryExceptionEnum implements ExceptionInfo{
	
	FRONTENDCATEGORY_PARENTID_NOT_NULL("2000013", "Goods frontend category parentId cannot be empty!"),

	FRONTENDCATEGORY_NAME_NOT_REPETITION("2000014", "Goods frontend category name cannot be repeated!"),
	
	FRONTENDCATEGORY_ID_NOT_NULL("2000015", "Goods frontend category ID cannot be empty!"),
	
	FRONTENDCATEGORY_ID_NOT_EXIST("2000016", "Goods frontend category ID cannot be exist!"),
	
    FRONTBACKCATEGORY_ID_NOT_NULL("2000017", "Goods frontend category ID cannot be empty!"),
	
    FRONTBACKCATEGORY_ID_NOT_EXIST("2000018", "Goods frontend category ID cannot be exist!"),
    
    FRONTBACKCATEGORY_BACKID_NOT_EXIST("2000027", "Goods frontend category back ID cannot be exist!"),
   
    FRONTBACKCATEGORY_BACKID_NOT_REPETITION("2000028", "Goods frontend category back ID cannot be repeated!"),
    
    FRONTBACKCATEGORY_FRONTID_NOT_EXIST("2000029", "Goods frontend category front ID cannot be exist!");
	
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
	FrontendCategoryExceptionEnum(String code, String defaultMessage) {
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
