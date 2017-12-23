package com.topaiebiz.transaction.refundOrder.dto;

import java.util.Date;
import java.util.List;

/**
 * Description TODO
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017/11/29 20:38
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class APPRefundOrderListDto {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 所属店铺
	 */
	private Long storeId;

	/**
	 * 所属会员
	 */
	private Long memberId;

	/**
	 * 购货订单号
	 */
	private Long storeOrderId;

	/**
	 * 退货理由
	 */
	private String reason;

	/**
	 * 退款订单状态
	 */
	private Integer state;

	/**
	 * 退货类型
	 */
	private String returnType;

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

	/**
	 * 需退总价格
	 */
	private Double totalPrice;

	// 运费
	private Double actualFreight;

	// 件数
	private Integer goodsNum;

	// 申请时间
	private Date orderTime;

	private List<RefundOrderDetailDto> refundOrderDetailDtoList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getStoreOrderId() {
		return storeOrderId;
	}

	public void setStoreOrderId(Long storeOrderId) {
		this.storeOrderId = storeOrderId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
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

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getActualFreight() {
		return actualFreight;
	}

	public void setActualFreight(Double actualFreight) {
		this.actualFreight = actualFreight;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public List<RefundOrderDetailDto> getRefundOrderDetailDtoList() {
		return refundOrderDetailDtoList;
	}

	public void setRefundOrderDetailDtoList(List<RefundOrderDetailDto> refundOrderDetailDtoList) {
		this.refundOrderDetailDtoList = refundOrderDetailDtoList;
	}
}
