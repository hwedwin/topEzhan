package com.topaiebiz.transaction.order.merchant.exception;

import com.nebulapaas.web.exception.ExceptionInfo;

public enum StoreOrderExceptionEnum implements ExceptionInfo {
	
	STOREORDER_ID_NULL("6000201","StoreOrder ID is not null."),
	
	PURCHASED_GOODS_DO_NOT_EXIST("6000203","The purchased goods do not exist!"),
	
	INSUFFICIENT_STOCK_OF_GOODS("6000204","Insufficient stock of goods!"),
	
	STOREORDER_ID_NOT_EXIST("6000202","StoreOrder ID does not exist."),
	
	LOGISTICSNO_IS_NOT_NULL("6000205","THE logisticsno is not null"),
	
	LOGISTICSTYPE_IS_NOT_NULL("6000206","THE logisticstype is not null"),

	THE_CARDORDER_IS_NOT_FINISH("6000207", "The cardorder is not finish."),

	ORDER_QUERY_PARAMETERS_ARE_ILLEGAL("6000208", "Order query parameters are illegal"),

	ORDER_NOT_FOUND("6000209", "order not found！");
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
	StoreOrderExceptionEnum(String code, String defaultMessage) {
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
