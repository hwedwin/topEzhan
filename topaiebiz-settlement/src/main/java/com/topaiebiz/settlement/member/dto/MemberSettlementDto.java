package com.topaiebiz.settlement.member.dto;

import java.util.Date;

/**
 * Description：会员结算Dto 
 * 
 * Author Harry
 *    
 * Date 2017年10月31日 下午4:47:06 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class MemberSettlementDto {
	
	/** 全局唯一主键标识符。支持泛型，具体类型由传入的类型指定。 */
	private Long id;
	
	/**会员编号。*/
	private Long memberId;
	
	/**可提现余额。*/
	private Double allowWithdrawalSum;
	
	/**不可体现余额。*/
	private Double notWithdrawalSum;
	
	/**已提现余额。*/
	private Double yetWithdrawalSum;
	
	/**总获得分销金额。*/
	private Double totalSum;
	
	/**账户状态。1 已出账     2 申请提现  3 未出账*/
	private Integer status;
	
	/**备注*/
	private String memo;
	
	/**商家名称*/
	private String merchantName;
	
	/**店铺名称*/
	private String storeName;
	
	/**会员名称*/
	private String memberName;
	
	/**上级会员姓名*/
	private String parentName;
	
	/**订单时间*/
	private Date createdTime;
	
	/**商品skuId*/
	private Long skuId;
	
	/**商品名称*/
	private String itemName;
	
	/**商品数量*/
	private Long goodsNum;
	
	/**商品单价*/
	private Double unitPrice;
	
	/**营销活动名称*/
	private String  promotionName;
	
	/**总金额*/
	private Double salePrice;
	
	/**店铺分销金额*/
	private Double storeDistributionPrice;
	
	/**会员分销金额*/
	private Double memberDistributionPrice;
	
	/**店铺id*/
	private Long  storeId;
	
	/**商家id*/
	private Long merchantId;
	
	/**订单中使用现金支付额金额*/
	private Double cashSum;
	
	/**订单中使用积分支付的金额*/
	private Double pointSum;

	/**订单中使用美丽卡支付的金额*/
	private Double cardSum;
	
	/**平台佣金*/
	private Double platformCommission;
	
	/**支付渠道佣金*/
	private Double paymentChannelCommission;
	
	
	public Double getPlatformCommission() {
		return platformCommission;
	}

	public void setPlatformCommission(Double platformCommission) {
		this.platformCommission = platformCommission;
	}

	public Double getPaymentChannelCommission() {
		return paymentChannelCommission;
	}

	public void setPaymentChannelCommission(Double paymentChannelCommission) {
		this.paymentChannelCommission = paymentChannelCommission;
	}

	public Double getCashSum() {
		return cashSum;
	}

	public void setCashSum(Double cashSum) {
		this.cashSum = cashSum;
	}

	public Double getPointSum() {
		return pointSum;
	}

	public void setPointSum(Double pointSum) {
		this.pointSum = pointSum;
	}

	public Double getCardSum() {
		return cardSum;
	}

	public void setCardSum(Double cardSum) {
		this.cardSum = cardSum;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Double getStoreDistributionPrice() {
		return storeDistributionPrice;
	}

	public void setStoreDistributionPrice(Double storeDistributionPrice) {
		this.storeDistributionPrice = storeDistributionPrice;
	}

	public Double getMemberDistributionPrice() {
		return memberDistributionPrice;
	}

	public void setMemberDistributionPrice(Double memberDistributionPrice) {
		this.memberDistributionPrice = memberDistributionPrice;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Long getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Long goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getPromotionName() {
		return promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Double getAllowWithdrawalSum() {
		return allowWithdrawalSum;
	}

	public void setAllowWithdrawalSum(Double allowWithdrawalSum) {
		this.allowWithdrawalSum = allowWithdrawalSum;
	}

	public Double getNotWithdrawalSum() {
		return notWithdrawalSum;
	}

	public void setNotWithdrawalSum(Double notWithdrawalSum) {
		this.notWithdrawalSum = notWithdrawalSum;
	}

	public Double getYetWithdrawalSum() {
		return yetWithdrawalSum;
	}

	public void setYetWithdrawalSum(Double yetWithdrawalSum) {
		this.yetWithdrawalSum = yetWithdrawalSum;
	}

	public Double getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(Double totalSum) {
		this.totalSum = totalSum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	


}
