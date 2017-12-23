package com.topaiebiz.transaction.refundOrder.dto;

/**
 * Description PC：退款/退货列表
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017/11/29 13:51
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class PCRefundOrderListDto {

	private String id;

	private String state;

	private String storeId;

	private String totalFreight;

	private String refundTotalPrice;

	private String goodsNum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getTotalFreight() {
		return totalFreight;
	}

	public void setTotalFreight(String totalFreight) {
		this.totalFreight = totalFreight;
	}

	public String getRefundTotalPrice() {
		return refundTotalPrice;
	}

	public void setRefundTotalPrice(String refundTotalPrice) {
		this.refundTotalPrice = refundTotalPrice;
	}

	public String getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

}
