/**
 * 
 */
package com.topaiebiz.payment.order.moble.dto;

import java.util.List;

/**
 * Description：订单参数容器DTO
 * 
 * 
 * Author hxpeng 
 *    
 * Date 2017年10月24日 下午3:14:25 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public class OrderParamsDto {
	
	/**
	 * 店铺订单详情ID
	 */
	private Long storeDetailOrderId;
	
	/**
	 * 店铺订单ID
	 */
	private Long storeOrderId;
	
	/**
	 * 物流类型：物流公司编码
	 */
	private String logisticsType;

	/**
	 * 物流类型：物流编号
	 */
	private String logisticsNo;

	private String token;

	/**
	 * 订单详情ID集合
	 */
	private List<Long> storeOrderDetailIds;

	public Long getStoreDetailOrderId() {
		return storeDetailOrderId;
	}

	public void setStoreDetailOrderId(Long storeDetailOrderId) {
		this.storeDetailOrderId = storeDetailOrderId;
	}

	public Long getStoreOrderId() {
		return storeOrderId;
	}

	public void setStoreOrderId(Long storeOrderId) {
		this.storeOrderId = storeOrderId;
	}

	public String getLogisticsType() {
		return logisticsType;
	}

	public void setLogisticsType(String logisticsType) {
		this.logisticsType = logisticsType;
	}

	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
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
