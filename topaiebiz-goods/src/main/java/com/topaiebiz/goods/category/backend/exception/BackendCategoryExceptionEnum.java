package com.topaiebiz.goods.category.backend.exception;

import com.nebulapaas.web.exception.ExceptionInfo;

/**
 * Description 商品后台类目异常枚举。  
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

public enum BackendCategoryExceptionEnum implements ExceptionInfo{
	
	BACKENDCATEGORY_NAME_NOT_REPETITION("2000005", "Goods background category name cannot be repeated!"),
	
	BACKENDCATEGORY_ID_NOT_NULL("2000006", "Goods background category ID cannot be empty!"),
	
	BACKENDCATEGORY_ID_NOT_EXIST("2000007", "Goods background category ID does not exist!"),
	
	BACKENDCATEGORYATTR_NAME_NOT_REPETITION("2000008", "Goods background category attribute name cannot be repeated!"),
	
	BACKENDCATEGORY_PARENTID_NOT_NULL("2000009", "Goods background category parentId cannot be empty!"),
	
	BACKENDCATEGORYATTR_BELONGCATEGORY_NOT_NULL("2000010", "Goods background category attributes belongCategory can not be empty!"),
	
	BACKENDCATEGORYATTR_ID_NOT_NULL("2000011", "Goods background category attributes ID can not be empty!"),
	
	BACKENDCATEGORYATTR_ID_NOT_EXIST("2000012", "Goods background category attributes ID does not exist!"),
	
	SPU_BACKENDCATEGORY_ID_EXIST("2000031","There is a commodity under the category, please hang the goods in other categories, and delete the category!"),
	
	ITEM_BACKENDCATEGORY_ID_EXIST("2000032","There is a commodity under the category, please hang the goods in other categories, and delete the category"),
	
	BACKENDCATEGORY_IS_USE("2000033","This kind of business is in use, do not delete!"),
	
	BACKENDCATEGORY_ALREADY("2000042","This category already exists!");
	
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
	BackendCategoryExceptionEnum(String code, String defaultMessage) {
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
