/**
 * 
 */
package com.topaiebiz.payment.order.moble.dto;

import java.util.List;

import com.topaiebiz.goods.sku.entity.GoodsSkuEntity;
import com.topaiebiz.promotion.mgmt.dto.PromotionGoodsDto;

/**  
 * Description： 封装 需要结算的  商品基本信息以及它们可用的营销活动
 * 
 * 
 * Author hxpeng 
 *    
 * Date 2017年10月23日 下午7:20:05 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public class SettlementGoodInfo {
	
	/**
	 * 商品名称
	 */
	private String itemName;
	
	/**
	 * 商品数量
	 * (订单创建时使用)
	 */
	private Long goodsNum;
	
	/**
	 * 单商品总价
	 * (订单创建时使用)
	 */
	private double totalPrice;
	
	/**
	 * 单商品使用了营销活动的ID
	 * (订单创建时使用)
	 */
	private Long usedPromotion;
	
	/**
	 * 单商品被优惠的价钱
	 * (订单创建时使用)
	 */
	private double deduction;
	
	/**
	 * 单商品被优惠后的价钱
	 * (订单创建时使用)
	 */
	private double salePrice;
	
	/**
	 * 单商品邮费总价
	 * (订单创建时使用)
	 */
	private double totalFreight;
	
	/**
	 * 状态
	 * (订单创建时使用)
	 */
	private Integer state;

	/**
	 * 物流类型
	 * (订单创建时使用)
	 */
	private String logisticsType;

	/**
	 * 单种商品的物流编号，如果分批次发可记录多个 。
	 * (订单创建时使用)
	 */
	private String logisticsNo;

	/**
	 * 备注
	 * (订单创建时使用)
	 */
	private String memo;
	
	/**
	 * 商品sku 基本信息
	 * (设置为不能为空，为空抛异常)
	 */
	private GoodsSkuEntity goodsSkuEntity;
	
	/**
	 * 商品能使用的优惠活动
	 * (跳完结算页面带值，生成订单页为空值)
	 */
	private List<PromotionGoodsDto> goodsPromotions;
	
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

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getUsedPromotion() {
		return usedPromotion;
	}

	public void setUsedPromotion(Long usedPromotion) {
		this.usedPromotion = usedPromotion;
	}

	public double getDeduction() {
		return deduction;
	}

	public void setDeduction(double deduction) {
		this.deduction = deduction;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public double getTotalFreight() {
		return totalFreight;
	}

	public void setTotalFreight(double totalFreight) {
		this.totalFreight = totalFreight;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getLogisticsType() {
		return logisticsType;
	}

	public void setLogisticsType(String logisticsType) {
		this.logisticsType = logisticsType;
	}

	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public GoodsSkuEntity getGoodsSkuEntity() {
		return goodsSkuEntity;
	}

	public void setGoodsSkuEntity(GoodsSkuEntity goodsSkuEntity) {
		this.goodsSkuEntity = goodsSkuEntity;
	}

	public List<PromotionGoodsDto> getGoodsPromotions() {
		return goodsPromotions;
	}

	public void setGoodsPromotions(List<PromotionGoodsDto> goodsPromotions) {
		this.goodsPromotions = goodsPromotions;
	}
}
