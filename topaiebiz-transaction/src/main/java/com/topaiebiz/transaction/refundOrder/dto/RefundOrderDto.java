package com.topaiebiz.transaction.refundOrder.dto;

import java.util.Date;
import java.util.List;

/**
 * 
 * Description 退货订单详情表DTO
 * 
 * 
 * Author Joe
 * 
 * Date 2017年10月17日 下午2:01:38
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class RefundOrderDto {

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

	/**
	 * 平台订单号
	 */
	private Long platformOrderNo;

	/**
	 * 备注
	 */
	private String memo;
	
	/**
	 * 会员名
	 */
	private String memberName;
	
	/**
	 * 修改时间
	 */
	private Date lastModifiedTime;
	
	/**
	 * 创建时间
	 */
	private Date createdTime;
	
	/**
	 * 退货数量
	 */
	private Long refundGoodsNum;
	
	/**
	 * 订单金额
	 */
	private Double orderPrice;
	
	/**
	 * 收货地址
	 */
	private String refundAddress;
	
	/**
	 * 收货人手机号
	 */
	private String refundTelephone;
	
	/**
	 * 收货人
	 */
	private String refundName;
	
	/**
	 * 订单类型
	 */
	private Integer storeOrderType;
	
	/**
	 * 店铺名称
	 */
	private String storeName;
	
	/**
	 * 商家名称
	 */
	private String merchantName;
	
	/**
	 * 美礼卡金额
	 */
	private Double refundCardPrice;
	
	/**
	 * 使用积分
	 */
	private Integer refundUsageScore;
	
	/**
	 * 退货订单详情
	 */
	private List<RefundOrderDetailDto> refundOrderDetailList;

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

	public Long getPlatformOrderNo() {
		return platformOrderNo;
	}

	public void setPlatformOrderNo(Long platformOrderNo) {
		this.platformOrderNo = platformOrderNo;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Long getRefundGoodsNum() {
		return refundGoodsNum;
	}

	public void setRefundGoodsNum(Long refundGoodsNum) {
		this.refundGoodsNum = refundGoodsNum;
	}

	public Double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getRefundAddress() {
		return refundAddress;
	}

	public void setRefundAddress(String refundAddress) {
		this.refundAddress = refundAddress;
	}

	public String getRefundTelephone() {
		return refundTelephone;
	}

	public void setRefundTelephone(String refundTelephone) {
		this.refundTelephone = refundTelephone;
	}

	public String getRefundName() {
		return refundName;
	}

	public void setRefundName(String refundName) {
		this.refundName = refundName;
	}

	public Integer getStoreOrderType() {
		return storeOrderType;
	}

	public void setStoreOrderType(Integer storeOrderType) {
		this.storeOrderType = storeOrderType;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public Double getRefundCardPrice() {
		return refundCardPrice;
	}

	public void setRefundCardPrice(Double refundCardPrice) {
		this.refundCardPrice = refundCardPrice;
	}

	public Integer getRefundUsageScore() {
		return refundUsageScore;
	}

	public void setRefundUsageScore(Integer refundUsageScore) {
		this.refundUsageScore = refundUsageScore;
	}

	public List<RefundOrderDetailDto> getRefundOrderDetailList() {
		return refundOrderDetailList;
	}

	public void setRefundOrderDetailList(List<RefundOrderDetailDto> refundOrderDetailList) {
		this.refundOrderDetailList = refundOrderDetailList;
	}

	public RefundOrderDto() {
		// TODO Auto-generated constructor stub
	}

}
