package com.topaiebiz.settlement.store.dto;

import java.util.Date;

/**
 * Description： 店铺结算Dto。
 * 
 * Author Harry 
 *    
 * Date 2017年10月31日 下午2:20:00 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class StoreSettlementDto {

	/** 全局唯一主键标识符。支持泛型，具体类型由传入的类型指定。 */
	private Long id;
	
	/**商家Id*/
	private Long merchantId;
	
	/**店铺id*/
	private Long storeId;
	
	/**计算开始日期*/
	private Date settleStartDate;
	
	/**结算结束日期*/
	private Date settleEndDate;
	
	/**订单中使用现金支付额金额*/
	private Double cashSum;
	
	/**订单中使用积分支付的金额*/
	private Double pointSum;

	/**订单中使用美丽卡支付的金额*/
	private Double cardSum;
	
	/**现金营业额+积分营业额+美丽卡营业额（销售金额）*/
	private Double saleSum;
	
	/**分销平台商品获得的金额。*/
	private Double distSum;
	
	/**营销活动商家扣补金额。*/
	private Double promStoreSum;
	
	/**营销活动平台扣补金额。*/
	private Double promPlatformSum;
	
	/**美礼卡商家扣补金额。*/
	private Double cardStoreSubsidy;
	
	/**美礼卡平台扣补金额。*/
	private Double cardPlatformSubsidy;
	
	/**会员分销店铺商品得的金额，从店铺账户里减去。*/
	private Double memberDistPrice;
	
	/**实际需要结算的金额*/
	private Double settlePrice;
	
	/**结算类型。1为销售结算。2为分润结算。*/
	private Integer settleType;
	
	/**结算状态。1为已出账   2 已结算*/
	private Integer state;
	
	/**备注*/
	private String memo;
	
	/**优惠后金额（实际金额）*/
	private Double lastPrice;
	
	/**美礼卡支付金额*/
	private Double cardPrice;
	
	/**积分抵扣金额*/
	private Double scorePrice;
	
	/**实际支付金额*/
	private Double payPrice;
	
	/**店铺名称*/
	private String storeName;
	
	/**商家名称*/
	private String merchantName;
	
	/**分销金额*/
	private Double lastPrices;
	
	/**所用支付级营销活动*/
	private	Long platformPromotion;
	
	/**平台优惠活动金额*/
	private Double platformDeduction;
	
	/**总订单Id*/
	private Long totalOrderId;
	
	/**平台补差*/
	private Double platformPrice;
	
	/**店铺补差*/
	private Double storePrice;
	/**卡售价*/
	private Double price;
	
	/**卡面值*/
	private Double value;
	
	/**商品skuId*/
	private Long skuId;
	
	/**会员名称*/
	private String memberName;
	
	/**会员id*/
	private Long memberId;
	
	/**商品名称*/
	private String itemName;
	
	/**商品数量*/
	private Long goodsNum;
	
	/**商品单价*/
	private Double unitPrice;
	
	/**营销活动名称*/
	private String promotionName;
	
	/**总金额*/
	private Double salePrice;
	
	/**店铺分销金额*/
	private Double storeDistributionPrice;
	
	/**会员分销金额*/
	private Double memberDistributionPrice;
	
	/**订单时间*/
	private Date createdTime;
	
	/**美丽卡扣补金额*/
	private Double cardButtonPrice;
	
	/**商品分销金额*/
	private Double storedistriPrice;
	
	/**支付渠道佣金*/
	private Double paymentChannelCommission;
	
	/**平台佣金*/
	private Double platformCommission;
	
	/**平台抽佣率*/
	private Double platformRate;
	
	/**支付渠道*/
	private String channelDisbursement;
	
	/**支付渠道费率*/
	private Double paymentChannelRate;
	
	/**三方支付流水号*/
	private String tripartitePaymentFloat;
	
	/**支付类型编码*/
	private String payType;
	
	/**支付交易号*/
	private String payCallbackNo;
	
	/**优惠后的价格*/
	private Double salePrices;
	
	/**佣金比例*/
	private Double brokerageRatio;
	
	/**订单时间*/
	private Date orderTime;
	
	/**支付时间*/
	private Date payTime;
	
	/**具体商品的营销活动Id*/
	private Long usedplatformPromotion;
	
	/**具体商品的优惠金额*/
	private Double deduction;
	
	/**店铺分润比例*/
	private Double storedistriRatio;
	
	/**订单类型 1.平台订单 2.店铺订单*/
	private Integer orderType;
	
	
	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Double getStoredistriRatio() {
		return storedistriRatio;
	}

	public void setStoredistriRatio(Double storedistriRatio) {
		this.storedistriRatio = storedistriRatio;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Double getDeduction() {
		return deduction;
	}

	public void setDeduction(Double deduction) {
		this.deduction = deduction;
	}

	public Long getUsedplatformPromotion() {
		return usedplatformPromotion;
	}

	public void setUsedplatformPromotion(Long usedplatformPromotion) {
		this.usedplatformPromotion = usedplatformPromotion;
	}

	public Double getPaymentChannelRate() {
		return paymentChannelRate;
	}

	public void setPaymentChannelRate(Double paymentChannelRate) {
		this.paymentChannelRate = paymentChannelRate;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Double getBrokerageRatio() {
		return brokerageRatio;
	}

	public void setBrokerageRatio(Double brokerageRatio) {
		this.brokerageRatio = brokerageRatio;
	}

	public Double getSalePrices() {
		return salePrices;
	}

	public void setSalePrices(Double salePrices) {
		this.salePrices = salePrices;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayCallbackNo() {
		return payCallbackNo;
	}

	public void setPayCallbackNo(String payCallbackNo) {
		this.payCallbackNo = payCallbackNo;
	}

	public Double getPaymentChannelCommission() {
		return paymentChannelCommission;
	}

	public void setPaymentChannelCommission(Double paymentChannelCommission) {
		this.paymentChannelCommission = paymentChannelCommission;
	}

	public Double getPlatformCommission() {
		return platformCommission;
	}

	public void setPlatformCommission(Double platformCommission) {
		this.platformCommission = platformCommission;
	}

	public Double getPlatformRate() {
		return platformRate;
	}

	public void setPlatformRate(Double platformRate) {
		this.platformRate = platformRate;
	}

	public String getChannelDisbursement() {
		return channelDisbursement;
	}

	public void setChannelDisbursement(String channelDisbursement) {
		this.channelDisbursement = channelDisbursement;
	}

	public String getTripartitePaymentFloat() {
		return tripartitePaymentFloat;
	}

	public void setTripartitePaymentFloat(String tripartitePaymentFloat) {
		this.tripartitePaymentFloat = tripartitePaymentFloat;
	}

	public Double getStoredistriPrice() {
		return storedistriPrice;
	}

	public void setStoredistriPrice(Double storedistriPrice) {
		this.storedistriPrice = storedistriPrice;
	}

	public Double getCardButtonPrice() {
		return cardButtonPrice;
	}

	public void setCardButtonPrice(Double cardButtonPrice) {
		this.cardButtonPrice = cardButtonPrice;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
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

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
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

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Double getPlatformPrice() {
		return platformPrice;
	}

	public void setPlatformPrice(Double platformPrice) {
		this.platformPrice = platformPrice;
	}

	public Double getStorePrice() {
		return storePrice;
	}

	public void setStorePrice(Double storePrice) {
		this.storePrice = storePrice;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Long getTotalOrderId() {
		return totalOrderId;
	}

	public void setTotalOrderId(Long totalOrderId) {
		this.totalOrderId = totalOrderId;
	}

	public Double getLastPrices() {
		return lastPrices;
	}

	public void setLastPrices(Double lastPrices) {
		this.lastPrices = lastPrices;
	}

	public Double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public Double getCardPrice() {
		return cardPrice;
	}

	public void setCardPrice(Double cardPrice) {
		this.cardPrice = cardPrice;
	}

	public Double getScorePrice() {
		return scorePrice;
	}

	public void setScorePrice(Double scorePrice) {
		this.scorePrice = scorePrice;
	}

	public Double getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(Double payPrice) {
		this.payPrice = payPrice;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getSettleStartDate() {
		return settleStartDate;
	}

	public void setSettleStartDate(Date settleStartDate) {
		this.settleStartDate = settleStartDate;
	}

	public Date getSettleEndDate() {
		return settleEndDate;
	}

	public void setSettleEndDate(Date settleEndDate) {
		this.settleEndDate = settleEndDate;
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

	public Double getSaleSum() {
		return saleSum;
	}

	public void setSaleSum(Double saleSum) {
		this.saleSum = saleSum;
	}

	public Double getDistSum() {
		return distSum;
	}

	public void setDistSum(Double distSum) {
		this.distSum = distSum;
	}

	public Double getPromStoreSum() {
		return promStoreSum;
	}

	public void setPromStoreSum(Double promStoreSum) {
		this.promStoreSum = promStoreSum;
	}

	public Double getPromPlatformSum() {
		return promPlatformSum;
	}

	public void setPromPlatformSum(Double promPlatformSum) {
		this.promPlatformSum = promPlatformSum;
	}

	public Double getCardStoreSubsidy() {
		return cardStoreSubsidy;
	}

	public void setCardStoreSubsidy(Double cardStoreSubsidy) {
		this.cardStoreSubsidy = cardStoreSubsidy;
	}

	public Double getCardPlatformSubsidy() {
		return cardPlatformSubsidy;
	}

	public void setCardPlatformSubsidy(Double cardPlatformSubsidy) {
		this.cardPlatformSubsidy = cardPlatformSubsidy;
	}

	public Double getMemberDistPrice() {
		return memberDistPrice;
	}

	public void setMemberDistPrice(Double memberDistPrice) {
		this.memberDistPrice = memberDistPrice;
	}

	public Double getSettlePrice() {
		return settlePrice;
	}

	public void setSettlePrice(Double settlePrice) {
		this.settlePrice = settlePrice;
	}

	public Integer getSettleType() {
		return settleType;
	}

	public void setSettleType(Integer settleType) {
		this.settleType = settleType;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	
	public Long getPlatformPromotion() {
		return platformPromotion;
	}

	public void setPlatformPromotion(Long platformPromotion) {
		this.platformPromotion = platformPromotion;
	}

	public Double getPlatformDeduction() {
		return platformDeduction;
	}

	public void setPlatformDeduction(Double platformDeduction) {
		this.platformDeduction = platformDeduction;
	}
	
}
