package com.topaiebiz.alipay.dto;

/**
 * Description 处理结果容器DTO
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017/11/18 15:04
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class AlipayResultDto {

	// 结果code
	private String resultCode;

	// 操作结果消息
	private String msg;

	// 返回流水号
	private String tradeNo;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
}
