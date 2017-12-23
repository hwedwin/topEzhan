/**
 * 
 */
package com.topaiebiz.payment.order.moble.dto;

import com.topaiebiz.giftcard.manage.dto.GiftCardDto;
import com.topaiebiz.member.address.dto.MemberAddressDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionGoodsDto;

import java.util.List;

/**  
 * Description： 结算结果DTO
 * 
 * 
 * Author hxpeng 
 *    
 * Date 2017年10月23日 下午5:44:34 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public class SettlementResultDto {

	/**
	 * 店铺Id
	 */
	private Long shopId;
	
	/**
	 * 总金额（不包含运费）
	 */
	private double totalMoney;
	
	/**
	 * 总积分
	 */
	private Long totalIntegral;
	
	/**
	 * 总运费
	 */
	private double totalFreight;
	
	/**
	 * 结算商品的sku id集合
	 */
	private List<Long> goodSkuIds;
	
	/**
	 * 收货人地址/联系信息
	 */
	private MemberAddressDto memberAddressDto;
	
	/**
	 * 商品及它所拥有的营销活动（单品等级）
	 */
	private List<SettlementGoodInfo> settlementGoodInfoList;

	/**
	 * 店铺平台等 的营销活动
	 */
	private List<PromotionDto> settlementOtherPromotionList;

	/**
	 * 用户能使用的美礼卡
	 */
	private List<GiftCardDto> settlementGoodsGiftCardList;
	
	/**
	 * 可用美礼卡额度
	 */
	private double giftCardValue;

	/**
	 * 商品ID
	 */
	private List<Long> itemIds;

	/**
	 * 查询店铺/支付等优惠活动的参数容器
	 */
	private List<PromotionGoodsDto> promotionGoodsDtos;

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Long getTotalIntegral() {
		return totalIntegral;
	}

	public void setTotalIntegral(Long totalIntegral) {
		this.totalIntegral = totalIntegral;
	}

	public double getTotalFreight() {
		return totalFreight;
	}

	public void setTotalFreight(double totalFreight) {
		this.totalFreight = totalFreight;
	}

	public List<Long> getGoodSkuIds() {
		return goodSkuIds;
	}

	public void setGoodSkuIds(List<Long> goodSkuIds) {
		this.goodSkuIds = goodSkuIds;
	}

	public MemberAddressDto getMemberAddressDto() {
		return memberAddressDto;
	}

	public void setMemberAddressDto(MemberAddressDto memberAddressDto) {
		this.memberAddressDto = memberAddressDto;
	}

	public List<SettlementGoodInfo> getSettlementGoodInfoList() {
		return settlementGoodInfoList;
	}

	public void setSettlementGoodInfoList(List<SettlementGoodInfo> settlementGoodInfoList) {
		this.settlementGoodInfoList = settlementGoodInfoList;
	}

	public List<PromotionDto> getSettlementOtherPromotionList() {
		return settlementOtherPromotionList;
	}

	public void setSettlementOtherPromotionList(List<PromotionDto> settlementOtherPromotionList) {
		this.settlementOtherPromotionList = settlementOtherPromotionList;
	}

	public List<GiftCardDto> getSettlementGoodsGiftCardList() {
		return settlementGoodsGiftCardList;
	}

	public void setSettlementGoodsGiftCardList(List<GiftCardDto> settlementGoodsGiftCardList) {
		this.settlementGoodsGiftCardList = settlementGoodsGiftCardList;
	}

	public double getGiftCardValue() {
		return giftCardValue;
	}

	public void setGiftCardValue(double giftCardValue) {
		this.giftCardValue = giftCardValue;
	}

	public List<Long> getItemIds() {
		return itemIds;
	}

	public void setItemIds(List<Long> itemIds) {
		this.itemIds = itemIds;
	}

	public List<PromotionGoodsDto> getPromotionGoodsDtos() {
		return promotionGoodsDtos;
	}

	public void setPromotionGoodsDtos(List<PromotionGoodsDto> promotionGoodsDtos) {
		this.promotionGoodsDtos = promotionGoodsDtos;
	}
}
