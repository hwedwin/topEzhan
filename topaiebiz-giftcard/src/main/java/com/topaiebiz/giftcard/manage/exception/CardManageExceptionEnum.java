package com.topaiebiz.giftcard.manage.exception;

import com.nebulapaas.web.exception.ExceptionInfo;

/**
 * 
 * Description： 礼卡异常枚举。
 * 
 * 
 * Author Murray.Li
 * 
 * Date 2017年9月25日 上午10:48:21
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public enum CardManageExceptionEnum implements ExceptionInfo {
	

	CARDINFO_ID_NOT_NULL("5000041", "THE card info id not is null."),

	CARDINFO_NAME_NOT_REPETITION("5000042", "THE card info name not is  repetition"),

	CARDINFO_CODE_NOT_REPETITION("5000043", "THE card info code not is  repetition"),

	CARDINFO_ID_NOT_EXIST("5000044", "THE card info id  not is  exist."),

	HOST_CARD_COMPENSATION_ERROR("5000045","THE host card compensation is error"),
	
	VICE_CARD_COMPENSATION_ERROR("5000046","THE vice card compensation is error"),
	
	CARD_IS_PUTAWAY_ALREADY("5000047","These gift cards contain gift cards"),
	
	CARD_INFO_DATA_IS_NULL("5000048","THE cardinfo data is null"),
	
	THE_EXPIRATIONTIME_IS_NOT_FUTURE("5000049","THE expirationtime is not future"),
	
	CARDDETAIL_ID_NOT_NULL("5000051", "THE card detail id not is null."),

	CARDDETAIL_NAME_NOT_REPETITION("5000052", "THE card detail info not is  repetition"),

	CARDDETAIL_CODE_NOT_REPETITION("5000053", "THE card detail code not is  repetition"),

	CARDDETAIL_ID_NOT_EXIST("5000054", "THE card detail id  not is  exist."),
	
	DETAIL_CARD_IS_NOT_NEW("5000055","THE card detail is not new."),
	
	DETAIL_CARDNO_IS_NOT_EXIST("5000056","THE card detail cardNo is not exist."),
	
	CARD_BALANCE_IS_NOT_MINUS("5000057","THE card balance is not minus"),
	
	BUY_CARD_NUMBER_IS_OVER_MEMBER("5000058","The quantity you purchased has reached the membership purchase amount"),
	
	THE_CARD_IS_NOT_ACTIVE("5000059","The card is not active."),
	
	ORDER_ID_IS_NOT_NULL("5000061","THE card order id is  null."),
	
	ORDER_ID_IS_NOT_EXIST("5000062","THE card order id is not exit."),
	
	CARD_NUMBER_IS_NOT_ENOUGH("5000063","THE card number is not enough."),
	
	ORDER_STATE_IS_UNFINISHED("5000064","THE order is unfinished"),
	
	CARD_BALANCE_IS_NOT_ENOUGH("5000065","THE card balance is not enough"),
	
	THE_CURRENT_CARD_HAS_NOT_YET_BEEN_ACTIVATED("5000066","The current card has not yet been activated"),
	
	THE_CARDORDER_IS_NOT_FINISH("5000067","The cardOrder is not finish."),
	
	THE_CARD_IS_BINDING_YET("5000068","The card is banding yet"),
	
	ADDRESS_ID_IS_NOT_NULL("5000071","THE address id is null."),
	
	ADDRESS_ID_IS_NOT_EXIST("5000072","THE address id is not exsit."),
	
	LOGISTICSNO_IS_NOT_NULL("5000073","THE logisticsNo is not null."),
	
	LOGISTICSTYPE_IS_NOT_NULL("5000074","THE logisticstype is not null."),
	
	THE_STORE_ID_IS_EXIST("5000081","THE store id is not exist."),
	
	THE_GOODS_PRICE_IS_NOT_NULL("5000091","THE goods price is not null.");
	
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

	CardManageExceptionEnum(String code, String defaultMessage) {
		this.code = code;
		this.defaultMessage = defaultMessage;
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

	@Override
	public String getCode() {
		return code;
	}

}
