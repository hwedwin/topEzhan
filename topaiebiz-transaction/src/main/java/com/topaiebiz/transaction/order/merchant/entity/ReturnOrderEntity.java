package com.topaiebiz.transaction.order.merchant.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * 
 * Description： 退货订单  
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月25日 下午2:28:48 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_tsa_return_order")
public class ReturnOrderEntity extends BaseBizEntity<Long> {

	/*** 版本化序列号*/
	private static final long serialVersionUID = -3979301469191853440L;
	
	/*** 退货订单号*/
	private String orderCode;
	
	/*** 所属店铺*/
	private Long storeId;
	
	/*** 所属会员。*/
	private Long memberId;
	
	/*** 购货订单号。*/
	private Long storeOrderId;
	
	/*** 退货理由。*/
	private String reason;
	
	/*** 状态*/
	private Integer state;
	
	/*** 退货类型。1 为仅退货  0为退货退款。*/
	private String returnType;
	
	/*** 退货凭证图片1。*/
	private String image1;
	
	/*** 退货凭证图片2*/
	private String image2;
	
	/*** 退货凭证图片3*/
	private String image3;
	
	/*** 退货凭证图片4*/
	private String image4;
	
	/*** 需退总价格*/
	private Double totalPrice;
	
	/*** 支付订单号*/
	private Long platformOrderNo;

	public ReturnOrderEntity() {
		super();
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
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

	public Long getPlatformOrderNo() {
		return platformOrderNo;
	}

	public void setPlatformOrderNo(Long platformOrderNo) {
		this.platformOrderNo = platformOrderNo;
	}

}
