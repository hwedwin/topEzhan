package com.topaiebiz.thirdparty.enumdata;

/**
 * Description 支付宝枚举类
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017/11/18 10:30
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public enum WecahtEnum {
	ORDER_REFUND_IS_SUCCESSED("1", "订单已退款成功"),
	ORDER_TRADE_IS_SUCCESSED("2", "订单交易已成功"),
	ORDER_REFUND_HAD_SUCCESSED("3", "订单已经退款过了"),
	ORDER_REFUND_FAIL("4","订单退款失败"),
	ORDER_REFUND_AMOUNT_IS_DISSIMILARITY("5", "申请退款金额不一致");

	private String code;
	private String value;

	private WecahtEnum(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
