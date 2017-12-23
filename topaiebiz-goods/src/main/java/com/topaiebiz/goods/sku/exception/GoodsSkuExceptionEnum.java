package com.topaiebiz.goods.sku.exception;

import com.nebulapaas.web.exception.ExceptionInfo;

/**
 * Description 商品评价异常枚举。  
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

public enum GoodsSkuExceptionEnum implements ExceptionInfo{
	
	GOODSITEM_ID_NOT_NULL("2000022", "Goods item ID cannot be empty!"),
	
	GOODSITEM_ID_NOT_EXIST("2000023", "Goods item ID cannot be exist!"),
	
	GOODSITEM_ITEMCODE_NOT_REPETITION("2000024", "Goods sku code cannot be repeated!"),
	
	GOODSITEM_STOCKNUMBER_DEFICIENCY("2000034", "Goods sku is not in stock!"),
	
	MEMBER_ID_NOT_EXIST("2000023", "Member ID cannot be exist!"),
	
	GOODS_ARE_OUT_OF_DATE("2000037","Goods are out of date!"),
	
	GOODSPICTURE_NAME_NOT_NULL("2000038","Please upload at least one picture!"),
	
	GOODSSKU_PRICE_NOT_NULL("2000039","The price of the goods should not be empty!"),
	
	GOODSSKU_STOCKNUMBER_NOT_NULL("2000040","Goods inventory cannot be zero！"),
	
	GOODSKU_ID_NOT_EXIST("2000041","This commodity does not exist！");
	
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
	GoodsSkuExceptionEnum(String code, String defaultMessage) {
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
