package com.topaiebiz.payment.order.exception;

import com.nebulapaas.web.exception.ExceptionInfo;

/**
 * Description 交易订单。
 * 
 * Author Aaron.Xue 
 *    
 * Date 2017年10月14日 下午5:09:51
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
public enum PaymentOrderExceptionEnum implements ExceptionInfo{
	
	PURCHASED_GOODS_DO_NOT_EXIST("11000001","The purchased goods do not exist!"),
	
	INSUFFICIENT_STOCK_OF_GOODS("11000002","Insufficient stock of goods!"),
	
	GOODS_EXCEED_THE_LIMIT("11000003","Goods exceed the limit!"),
	
	ORDER_CANNOT_BE_CANCELLED("11000004","The order has been paid and cannot be cancelled!"),
	
	REFUNDORDER_ID_NOT_NULL("11000101","refundOrder id not null!"),
	
	LOGISTICS_TYPES_NOT_NULL("11000102","logistics types not null!"),
	
	LOGISTICS_NUMBER_NOT_NULL("11000103","logistics number not null!"),
	
	RETURNTYPE_NOT_NULL("11000104","returntype not null!"),
	
	/** 退货信息为空*/
	RETURN_INFORMATION_IS_NULL("11000105","return information is null!"),
	
	/** 店铺订单ID为空*/
	STORE_ORDER_ID_NOT_NULL("11000106","store order id not null!"),
	
	/** 店铺订单信息为空*/
	STORE_ORDER_INFORMATION_IS_NULL("11000107","store order information is null!"),

	/** 总订单信息为空*/
	TOTALORDER_INFORMATION_IS_NULL("11000108","totalorder information is null!"),

	/** 退款金额计算失败 */
	CALCULATION_REFUND_PRICE_FAIL("11000109","calculation refund price fail!"),

	/** 退款参数非法 */
	CALCULATION_PARAMS_ILLEGAL("11000110","calculation params illegal!"),

	/** 暂不能申请售后 */
	ORDER_CANT_POST_THE_REFUND_TEMPORARY("11000111","order cant post the refund temporary"),

	/** 已超过申请次数限制 */
	CANT_POST_THE_REFUND_MORE_TIME("11000112","order cant post the refund more time");
	
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
	 * Author Aaron.Xue 
	 * 
	 * @param code
	 *            异常的代码。
	 * 
	 * @param defaultMessage
	 *            异常的默认提示信息。
	 */
	PaymentOrderExceptionEnum(String code, String defaultMessage) {
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
