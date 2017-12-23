/**
 * 
 */
package com.topaiebiz.transaction.order.total.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**  
 * Description： 总订单查询参数dto
 * 
 * 
 * Author hxpeng 
 *    
 * Date 2017年10月29日 下午7:27:39 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public class TotalOrderParamDto {

	/**
	 * 订单店铺类型(1:平台订单，2:店铺订单)
	 */
	private int orderStroeType;
	
	/**
	 * 单品id:店铺详情Id
	 */
	private Long storeOrderDetailId;
	
	/**
	 * 店铺订单Id
	 */
	private Long storeOrderId;
	
	/**
	 * 多少天之类
	 */
	@NotNull(message = "{validation.statistics.days}")
	@Min(1)
	private int days;
	
	/**
	 * 订单支付状态
	 */
	private int payState;
	
	/**
	 * 订单类型(支付/退款)
	 * (1 购买商品 2  退货  3 美礼卡购买)
	 * 等于0  默认查询所有非退款的总订单数据 <> 2
	 */
	private int orderType;

	// 开始时间
	private String beforDate;
	
	// 结束时间
	private String afterDate;

	// token验证
	private String token;

	private List<Long> storeOrderDetailIds;

	public int getOrderStroeType() {
		return orderStroeType;
	}

	public void setOrderStroeType(int orderStroeType) {
		this.orderStroeType = orderStroeType;
	}

	public Long getStoreOrderDetailId() {
		return storeOrderDetailId;
	}

	public void setStoreOrderDetailId(Long storeOrderDetailId) {
		this.storeOrderDetailId = storeOrderDetailId;
	}

	public Long getStoreOrderId() {
		return storeOrderId;
	}

	public void setStoreOrderId(Long storeOrderId) {
		this.storeOrderId = storeOrderId;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getPayState() {
		return payState;
	}

	public void setPayState(int payState) {
		this.payState = payState;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public String getBeforDate() {
		return beforDate;
	}

	public void setBeforDate(String beforDate) {
		this.beforDate = beforDate;
	}

	public String getAfterDate() {
		return afterDate;
	}

	public void setAfterDate(String afterDate) {
		this.afterDate = afterDate;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Long> getStoreOrderDetailIds() {
		return storeOrderDetailIds;
	}

	public void setStoreOrderDetailIds(List<Long> storeOrderDetailIds) {
		this.storeOrderDetailIds = storeOrderDetailIds;
	}
}
