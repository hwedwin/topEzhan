package com.topaiebiz.transaction.cart.exception;

import com.nebulapaas.web.exception.ExceptionInfo;

/**
 * 
 * Description： 购物车订单流程异常枚举  
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月25日 下午10:52:13 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public enum ShoppingCartExceptionEnum implements ExceptionInfo {
	
	GOODSCART_GOODSATTR_NOT_NULL("6000301","Detecting the corresponding parameter commodity does not exist"),
	GOODSCART_MEMBER_NOT_NULL("6000302","member does not exist"),
	GOODSCART_GOODSNUM_NOT_NULL("6000303","is not a legal number"),
	GOODSCART_STOCKNUM("6000304","The number of shopping carts added is greater than"),
	GOODSCART_NOT_EXIST("6000305","GoodsCart is does not exist"),
	GOODSCART_ID_NOT_NULL("6000306","GoodsCart is not null"),
	GOODSFAVOR_ALREADY_SXISTS("6000307","GoodsFavorite Already exists"),
	GOODSFAVOR_GOODSID_NOT_NULL("6000308","Please select goods!"),
	GOODSFAVOR_ID_NOT_NULL("6000308","Please select goods!"),
	GOODSFAVOR_ID_NOT_EXIST("6000309","This goods does not exist!"),
	PLEASE_LOG_IN_FIRST("6000310","Please log in first！"),
	FAVORITE_FULL("6000311","Your collection is full, please delete the collection of items in the collection！");
	
	
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
	ShoppingCartExceptionEnum(String code, String defaultMessage) {
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
