package com.topaiebiz.promotion.mgmt.service;

import java.text.ParseException;
import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.topaiebiz.goods.sku.dto.GoodsSkuDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionGoodsDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionPriceDto;

/**
 * 
 * Description 营销活动商品信息
 * 
 * 
 * Author Joe
 * 
 * Date 2017年10月13日 下午4:42:39
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public interface PromotionGoodsService {

	/**
	 * 
	 * Description 根据商品与营销活动查询商品信息 （店铺级）
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	PromotionPriceDto getDiscountPromotionGoods(PromotionPriceDto promotionPriceDto, Long promotionId);

	/**
	 * 
	 * Description 根据订单级活动查询优惠信息
	 * 
	 * Author Joe   
	 * 
	 * @param goodsSkuList
	 * @param promotionStoreId
	 * @param memberId
	 * @return
	 */
	PromotionPriceDto getStorePromotionGoods(List<GoodsSkuDto> goodsSkuList, Long promotionStoreId, Long memberId);
	
	/**
	 * 
	 * Description 根据订单级活动与支付级活动查询优惠信息
	 * 
	 * Author Joe
	 * 
	 * @param goodsSkuList
	 *            商品集合
	 * @param promotionStoreId
	 *            店铺活动id
	 * @param promotionPlatformId
	 *            平台活动id
	 * @return
	 */
	PromotionPriceDto getPlatformPromotionGoods(List<GoodsSkuDto> goodsSkuList, Long promotionStoreId, Long promotionPlatformId, Long memberId);

	/**
	 * 
	 * Description 校验该单品活动该会员与该商品是否可用
	 * 
	 * Author Joe
	 * 
	 * @param memberId
	 * @param promotionId
	 * @param goodsSkuId
	 * @return
	 */
	PromotionGoodsDto getPromotionGoods(Long memberId, Long promotionId, Long goodsSkuId);

	/**
	 * 
	 * Description 查询首页秒杀活动
	 * 
	 * Author Joe
	 * 
	 * @param token
	 * @param page
	 * @param startTime
	 * @return
	 * @throws ParseException
	 */
	Page<PromotionGoodsDto> getHomePageSeckill(String token, Page<PromotionGoodsDto> page) throws ParseException;

	/**
	 * 
	 * Description 根据营销活动id查询活动商品分页列表
	 * 
	 * Author Joe
	 * 
	 * @param token
	 * @param page
	 * @param promotionId
	 * @return
	 */
	Page<PromotionGoodsDto> getPromotionSeckillList(String token, Page<PromotionGoodsDto> page, Long promotionId);

	/**
	 * 
	 * Description 商家报名商品
	 * 
	 * Author Joe   
	 * 
	 * @param page
	 * @param promotionGoodsDto
	 * @return
	 */
	Page<PromotionGoodsDto> getStoreEnrolGoodsList(Page<PromotionGoodsDto> page, PromotionGoodsDto promotionGoodsDto);

	/**
	 * 
	 * Description 报名商家商品审核列表   
	 * 
	 * Author Joe   
	 * 
	 * @param page
	 * @param promotionGoodsDto
	 * @return
	 */
	Page<PromotionGoodsDto> getStoreGoodsAuditList(Page<PromotionGoodsDto> page, PromotionGoodsDto promotionGoodsDto);

	/**
	 * 
	 * Description 审核sku商品 
	 * 
	 * Author Joe   
	 * 
	 * @param promotionGoodsId
	 * @param state
	 */
	void modifyAuditSingleSkuGoods(Long promotionGoodsId, Integer state);

	/**
	 * 
	 * Description sku商品审核完成 
	 * 
	 * Author Joe   
	 * 
	 * @param promotionGoodsList
	 */
	void modifyAuditSkuGoods(List<PromotionGoodsDto> promotionGoodsList);

	/**
	 * 
	 * Description 审核item商品通过/不通过    
	 * 
	 * Author Joe   
	 * 
	 * @param promotionGoodsDto
	 */
	void modifyAuditItemGoods(PromotionGoodsDto promotionGoodsDto);

	/**
	 * 
	 * Description 商家商品审核完成   
	 * 
	 * Author Joe    
	 * 
	 * @param promotionGoodsDto
	 */
	void modifyAuditGoods(PromotionGoodsDto promotionGoodsDto);

	
	
}
