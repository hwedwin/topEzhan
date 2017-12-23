/**
 * 
 */
package com.topaiebiz.payment.order.moble.dto;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.payment.order.moble.exception.MobleOrderExceptionEnum;
import com.topaiebiz.transaction.order.merchant.entity.OrderInvoiceEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * Description： 结算/订单提交 接口参数容器
 * 
 * 
 * Author hxpeng
 * 
 * Date 2017年10月23日 上午10:59:05
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public class SettlementParamDto {
	
	/**
	 * 用户token
	 */
	private String token;
	
	/**
	 * 店铺订单ID
	 */
	private Long storeOrderId;
	
	/**
	 * 总订单ID
	 */
	private Long totalOrderId;
	
	/**
	 * 收货地址ID
	 */
	private Long memberAddressId;
	
	/**
	 * 店铺营销活动ID
	 */
	private Long shopPromotionId;
	
	/**
	 * 平台营销活动ID
	 */
	private Long platformPromotionId;
	
	/**
	 * 物流营销活动ID
	 */
	private Long logisticsPromotionId;
	
	/**
	 * 美礼卡面值
	 */
	private double giftCardValue;
	
	/**
	 * 发票ID
	 */
	private Long memberInvoiceId;
	
	/**
	 * 订单发票实体类对象
	 */
	private OrderInvoiceEntity orderInvoiceEntity;
	
	/**
	 * 配送方式
	 * (1:配送，2:自提)
	 */
	private Short deliveryType;

	/**
	 * 留言
	 */
	private String memo;
	
	/**
	 * 使用积分
	 */
	private Long usageScore;
	
	/**
	 * 结算商品集合
	 */
	private List<SettlementGoodParamDto> settlementGoodParamDtos;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getStoreOrderId() {
		return storeOrderId;
	}

	public void setStoreOrderId(Long storeOrderId) {
		this.storeOrderId = storeOrderId;
	}

	public Long getTotalOrderId() {
		return totalOrderId;
	}

	public void setTotalOrderId(Long totalOrderId) {
		this.totalOrderId = totalOrderId;
	}

	public Long getMemberAddressId() {
		return memberAddressId;
	}

	public void setMemberAddressId(Long memberAddressId) {
		this.memberAddressId = memberAddressId;
	}

	public Long getShopPromotionId() {
		return shopPromotionId;
	}

	public void setShopPromotionId(Long shopPromotionId) {
		this.shopPromotionId = shopPromotionId;
	}

	public Long getPlatformPromotionId() {
		return platformPromotionId;
	}

	public void setPlatformPromotionId(Long platformPromotionId) {
		this.platformPromotionId = platformPromotionId;
	}

	public Long getLogisticsPromotionId() {
		return logisticsPromotionId;
	}

	public void setLogisticsPromotionId(Long logisticsPromotionId) {
		this.logisticsPromotionId = logisticsPromotionId;
	}

	public double getGiftCardValue() {
		return giftCardValue;
	}

	public void setGiftCardValue(double giftCardValue) {
		this.giftCardValue = giftCardValue;
	}

	public Long getMemberInvoiceId() {
		return memberInvoiceId;
	}

	public void setMemberInvoiceId(Long memberInvoiceId) {
		this.memberInvoiceId = memberInvoiceId;
	}

	public OrderInvoiceEntity getOrderInvoiceEntity() {
		return orderInvoiceEntity;
	}

	public void setOrderInvoiceEntity(OrderInvoiceEntity orderInvoiceEntity) {
		this.orderInvoiceEntity = orderInvoiceEntity;
	}

	public Short getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(Short deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getUsageScore() {
		return usageScore;
	}

	public void setUsageScore(Long usageScore) {
		this.usageScore = usageScore;
	}

	public List<SettlementGoodParamDto> getSettlementGoodParamDtos() {
		return settlementGoodParamDtos;
	}

	public void setSettlementGoodParamDtos(List<SettlementGoodParamDto> settlementGoodParamDtos) {
		if(null ==settlementGoodParamDtos || settlementGoodParamDtos.size() < 1){
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_PARAM_ILLEGAL);
		}
		List<Long> skuIds = new ArrayList<>();
		for (SettlementGoodParamDto settlementGoodParamDto : settlementGoodParamDtos){
			Long goodSkuId = settlementGoodParamDto.getGoodSkuId();
			if(null == goodSkuId){
				throw new GlobalException(MobleOrderExceptionEnum.ORDER_PARAM_ILLEGAL);
			}
			// 重复的SKU 商品 订单提交
			if(skuIds.contains(goodSkuId)){
				throw new GlobalException(MobleOrderExceptionEnum.ORDER_PARAM_ILLEGAL);
			}
			skuIds.add(goodSkuId);
		}
		this.settlementGoodParamDtos = settlementGoodParamDtos;
	}

	
	

	
}
