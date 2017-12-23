package com.topaiebiz.payment.order.dto;

import java.util.List;

/**
 * Description 退款/退货 参数DTO
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017/11/23 15:28
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class RefundPriceParamsDto {

	// 售后订单ID
	private Long refundOrderId;

	private String token;

	// 退款类型
	private String refundType;

	// 店铺订单ID
	private Long storeOrderId;

	// 退款订单详情集合ID（待退款的，订单详情ID集合）
	private List<Long> storeOrderDetailIds;

	// 物流编号
	private String logisticsNo;

	// 物流公司
	private String logisticsType;

	// 退货理由
	private String reason;

	/**
	 * 退货凭证图片1
	 */
	private String image1;

	/**
	 * 退货凭证图片2
	 */
	private String image2;

	/**
	 * 退货凭证图片3
	 */
	private String image3;

	/**
	 * 退货凭证图片4
	 */
	private String image4;

	private String memo;

	/**
	 * 退款金额
	 */
	private double refundPrice;

	// 是否为修改/重新申请 售后
	private Integer isUpdate;

	public Long getRefundOrderId() {
		return refundOrderId;
	}

	public void setRefundOrderId(Long refundOrderId) {
		this.refundOrderId = refundOrderId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRefundType() {
		return refundType;
	}

	public void setRefundType(String refundType) {
		this.refundType = refundType;
	}

	public Long getStoreOrderId() {
		return storeOrderId;
	}

	public void setStoreOrderId(Long storeOrderId) {
		this.storeOrderId = storeOrderId;
	}

	public List<Long> getStoreOrderDetailIds() {
		return storeOrderDetailIds;
	}

	public void setStoreOrderDetailIds(List<Long> storeOrderDetailIds) {
		this.storeOrderDetailIds = storeOrderDetailIds;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String getImage4() {
		return image4;
	}

	public void setImage4(String image4) {
		this.image4 = image4;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}

	public String getLogisticsType() {
		return logisticsType;
	}

	public void setLogisticsType(String logisticsType) {
		this.logisticsType = logisticsType;
	}

	public double getRefundPrice() {
		return refundPrice;
	}

	public void setRefundPrice(double refundPrice) {
		this.refundPrice = refundPrice;
	}

	public Integer getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Integer isUpdate) {
		this.isUpdate = isUpdate;
	}
}
