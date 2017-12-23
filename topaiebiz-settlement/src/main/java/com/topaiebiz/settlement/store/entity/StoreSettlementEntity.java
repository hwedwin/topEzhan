package com.topaiebiz.settlement.store.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description： 店铺结算Entity。 
 * 
 * Author Harry
 *    
 * Date 2017年10月31日 下午2:24:36 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_set_store_settlement")
public class StoreSettlementEntity  extends BaseBizEntity<Long> implements Serializable {

	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = -8680872187627605238L;
	
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
	
	/**支付渠道佣金*/
	private Double paymentChannelCommission;
	
	/**平台佣金*/
	private Double platformCommission;
	
	/**平台抽佣率*/
	private Double platformRate;
	
	/**支付渠道费率*/
	private Double paymentChannelRate;
	
	/**支付渠道*/
	private String channelDisbursement;
	
	/**三方支付流水号*/
	private String tripartitePaymentFloat;
	
	/**备注*/
	private String memo;
	
	public Double getPaymentChannelRate() {
		return paymentChannelRate;
	}

	public void setPaymentChannelRate(Double paymentChannelRate) {
		this.paymentChannelRate = paymentChannelRate;
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
	
	
	
	

}
