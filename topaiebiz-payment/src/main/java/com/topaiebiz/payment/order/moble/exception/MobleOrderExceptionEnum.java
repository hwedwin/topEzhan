/**
 * 
 */
package com.topaiebiz.payment.order.moble.exception;

import com.nebulapaas.web.exception.ExceptionInfo;

/**  
 * Description： 移动端订单相关异常枚举类
 * 
 * 
 * Author hxpeng 
 *    
 * Date 2017年10月23日 下午1:17:33 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public enum MobleOrderExceptionEnum implements ExceptionInfo{

	ORDER_PARAM_ILLEGAL("11000026","order param illegal"),
	
	GOODS_IS_NOT_FOUND("11000013","Good is not found!"),
	
	FREIGHT_INFO_IS_NOT_FOUND("11000014","freight info is not found!"),
	
	FREIGHT_CALCULATION_FAILED("11000015","freight calculation failed!"),
	
	SETTLEMENT_PARAM_ILLEGAL("11000016","settlement param illegal!"),
	
	SETTLEMENT_GOODS_SKUIDS_IS_NULL("11000017","settlement goods skuids is null!"),
	
	USERS_INTEGRAL_FOUND_FAIL("11000018","User integral query failed!"),
	
	PROMOTION_PROCESS_FAILURE("11000019","Failure in dealing with marketing activities!"),
	
	GOODS_INSUFFICIENT_STOCK("11000020","Insufficient stock of goods"),
	
	ORDER_INVOICE_ILLEGAL("11000021","order invoice cant be illegal!"),

	PREFERENTIAL_USE_FAILURE("11000022","preferential use failure!"),
	
	GET_MEMBERADDRESS_INFO_FAILURE("11000023","get memberaddress info failure"),

	ORDER_HAS_BEEN_CANCELLED("11000024","order has been cancelled"),
	
	ORDER_CANCEL_FAILURE("11000025","order cancel failure"),
	
	ORDER_IS_NOT_FOUND("11000036","order is not found"),
	
	CONFIRM_RECEIPT_FAILURE("11000027","confirm receipt failure"),

	EXTENDED_RECEIPT_FAILURE("11000028","extended receipt failure"),
	
	ORDER_STATISTICS_PARAMETER_ILLEGAL("11000029","Order statistics parameter illegal!"),
	
	ORDER_STATISTICS_FAILURE("11000030","order statistics failure！"),
	
	FAILURE_OF_ORDER_DELIVERY("11000031","Failure of order delivery！"),

	CHOOSE_TIME_CANT_EXCEED_30_DAYS("11000032","The choice time should not exceed 30 days！"),

	ORDER_SUBMIT_FAILURE("11000033","order submit failure！"),

	APPLY_REFUND_FAILURE("11000034","apply refund failure！"),

	CHANGE_ORDER_STATE_FAILURE("11000035","change order state failure！"),

	USERS_OPEN_ID_IS_NULL("11000037","users open id is null！"),

	AREA_CAN_NOT_BE_DELIVERED("11000038","Area can not be delivered"),

	THE_DELIVERY_ADDRESS_CANNOT_BE_FOUND("11000039","The delivery address cannot be found"),

	FAILURE_TO_CALCULATE_REFUND_AMOUNT("11000040","Failure to calculate refund amount"),

	ILLEGAL_PROCESS_MODIFY_STATE_FAILURE("11000041","illegal process， modify state failure"),

	THE_GOODS_HAVE_BEEN_LAID_DOWN("11000042", "The goods have been laid down");


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
	MobleOrderExceptionEnum(String code, String defaultMessage) {
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
