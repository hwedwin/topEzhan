package com.topaiebiz.promotion.mgmt.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * 
 * Description： 营销活动商品表
 * 
 * 
 * Author Joe
 * 
 * Date 2017年9月22日 上午11:06:29
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_pro_promotion_goods")
public class PromotionGoodsEntity extends BaseBizEntity<Long> {

	private static final long serialVersionUID = -6899335865303603472L;

	/**
	 * 营销活动
	 */
	private Long promotionId;

	/**
	 * 所属店铺
	 */
	private Long storeId;

	/**
	 * 所属商品
	 */
	private Long itemId;

	/**
	 * 商品SKU
	 */
	private Long goodsSkuId;

	/**
	 * 原有库存
	 */
	private Long repertoryNum;

	/**
	 * 活动数量
	 */
	private Long promotionNum;
	
	/**
	 * 锁定数量
	 */
	private Long lockedNum;

	/**
	 * 活动价格
	 */
	private Double promotionPrice;

	/**
	 * ID限购
	 */
	private Long confineNum;

	/**
	 * 优惠类型
	 */
	private Integer discountType;

	/**
	 * 优惠值
	 */
	private Double discountValue;

	/**
	 * 优惠赠品
	 */
	private Long giveawayGoods;

	/**
	 * 平台补贴
	 */
	private Double platformPrice;

	/**
	 * 状态
	 */
	private Integer state;

	/**
	 * 备注
	 */
	private String memo;

	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getGoodsSkuId() {
		return goodsSkuId;
	}

	public void setGoodsSkuId(Long goodsSkuId) {
		this.goodsSkuId = goodsSkuId;
	}

	public Long getRepertoryNum() {
		return repertoryNum;
	}

	public void setRepertoryNum(Long repertoryNum) {
		this.repertoryNum = repertoryNum;
	}

	public Long getPromotionNum() {
		return promotionNum;
	}

	public void setPromotionNum(Long promotionNum) {
		this.promotionNum = promotionNum;
	}

	public Double getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(Double promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public Long getConfineNum() {
		return confineNum;
	}

	public void setConfineNum(Long confineNum) {
		this.confineNum = confineNum;
	}

	public Integer getDiscountType() {
		return discountType;
	}

	public void setDiscountType(Integer discountType) {
		this.discountType = discountType;
	}

	public Double getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(Double discountValue) {
		this.discountValue = discountValue;
	}

	public Long getGiveawayGoods() {
		return giveawayGoods;
	}

	public void setGiveawayGoods(Long giveawayGoods) {
		this.giveawayGoods = giveawayGoods;
	}

	public Double getPlatformPrice() {
		return platformPrice;
	}

	public void setPlatformPrice(Double platformPrice) {
		this.platformPrice = platformPrice;
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

	public Long getLockedNum() {
		return lockedNum;
	}

	public void setLockedNum(Long lockedNum) {
		this.lockedNum = lockedNum;
	}

}
