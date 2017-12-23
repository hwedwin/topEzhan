/**
 * 
 */
package com.topaiebiz.payment.order.moble.common;

/**
 * Description： 支付类别枚举类
 * 
 * 
 * Author hxpeng
 * 
 * Date 2017年11月5日 下午7:30:51
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public enum PayTypeEnum {

	// 支付宝支付
	ALIPAY("1", "alipay"),
	
	// 微信支付
	WECHAT("2", "wechatPay");
	
	
	private String code;

	private String value;

	PayTypeEnum(String code, String value) {
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
