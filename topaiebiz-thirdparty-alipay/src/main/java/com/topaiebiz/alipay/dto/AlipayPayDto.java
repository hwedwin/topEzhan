/**
 *
 */
package com.topaiebiz.alipay.dto;

import java.math.BigDecimal;

/**
 * Description： 支付的业务参数 DTO
 * <p>
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017年11月5日 下午3:08:15
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public class AlipayPayDto {

	/**
	 * 订单描述
	 */
	private String body;

	/**
	 * 商品名字等关键字
	 * 非空
	 */
	private String subject;

	/**
	 * 商户网站唯一订单号
	 * 非空
	 */
	private String out_trade_no;

	/**
	 * 最晚付款时间
	 * 1m~15d
	 */
	private String timeout_express;

	/**
	 * 绝对超时时间
	 * yyyy-MM-dd HH:mm
	 */
	private String time_expire;

	/**
	 * 订单总金额
	 * 非空
	 */
	private BigDecimal total_amount;

	/**
	 * 收款支付宝账号
	 */
	private String seller_id;

	private String auth_token;

	private String product_code;

	private String goods_type;

	private String passback_params;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTimeout_express() {
		return timeout_express;
	}

	public void setTimeout_express(String timeout_express) {
		this.timeout_express = timeout_express;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public BigDecimal getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(BigDecimal total_amount) {
		this.total_amount = total_amount;
	}

	public String getAuth_token() {
		return auth_token;
	}

	public void setAuth_token(String auth_token) {
		this.auth_token = auth_token;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getGoods_type() {
		return goods_type;
	}

	public void setGoods_type(String goods_type) {
		this.goods_type = goods_type;
	}

	public String getPassback_params() {
		return passback_params;
	}

	public void setPassback_params(String passback_params) {
		this.passback_params = passback_params;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
}
