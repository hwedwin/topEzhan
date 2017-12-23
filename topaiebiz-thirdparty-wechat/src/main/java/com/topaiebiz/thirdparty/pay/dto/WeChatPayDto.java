package com.topaiebiz.thirdparty.pay.dto;

/**
 * Description 微信支付业务参数实体类
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017/11/13 14:02
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class WeChatPayDto {

	/**
	 * 用户的openId
	 */
	private String openId;

	/**
	 * 商品描述（128位，非空）
	 */
	private String body;

	/**
	 * 商品详情（6000位，可空）
	 */
	private String detail;

	/**
	 * 附加数据（127位，可空）
	 */
	private String attach;

	/**
	 * 商户订单号（32位，非空）
	 */
	private String out_trade_no;

	/**
	 * 标价金额（88位，分为单位，非空）
	 */
	private String total_fee;

	/**
	 * 终端IP（16位，非空）
	 */
	private String spbill_create_ip;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
}
