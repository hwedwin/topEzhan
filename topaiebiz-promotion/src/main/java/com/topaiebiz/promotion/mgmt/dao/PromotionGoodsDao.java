package com.topaiebiz.promotion.mgmt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.goods.sku.dto.ItemDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionGoodsDto;
import com.topaiebiz.promotion.mgmt.entity.PromotionGoodsEntity;

/**
 * 
 * Description 营销活动商品
 * 
 * 
 * Author Joe
 * 
 * Date 2017年10月9日 下午4:01:57
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public interface PromotionGoodsDao extends BaseDao<PromotionGoodsEntity> {

	/**
	 * 
	 * Description 根据营销活动ID和商品sku查询营销活动商品信息 (关联sku表)
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	PromotionGoodsDto getPromotionGoods(@Param(value = "promotionId") Long promotionId, @Param(value = "goodsSkuId") Long goodsSkuId);

	/**
	 * 
	 * Description 根据营销活动ID和商品sku查询商品信息（不关联sku表）
	 * 
	 * Author Joe
	 * 
	 * @param promotionId
	 * @return
	 */
	PromotionGoodsEntity selectPromotionGoodsByPromotionId(@Param(value = "promotionId") Long promotionId, @Param(value = "goodsSkuId") Long goodsSkuId);

	/**
	 * 
	 * Description 根据营销活动删除对应活动商品
	 * 
	 * Author Joe
	 * 
	 * @param id
	 */
	void deletePromotionGoods(Long promotionId);

	/**
	 * 
	 * Description 根据营销活动查询所选商品(根据所属item去重)
	 * 
	 * Author Joe
	 * 
	 * @param id
	 * @return
	 */
	List<PromotionGoodsDto> findPromotionGoodsByPromotionId(Page<PromotionGoodsDto> page, ItemDto itemDto);

	/**
	 * 
	 * Description 根据营销活动id和itemId查询商品
	 * 
	 * Author Joe
	 * 
	 * @param promotionId
	 * @param itemId
	 * @return
	 */
	List<PromotionGoodsDto> selectPromotionGoodsByItemId(@Param(value = "promotionId") Long promotionId, @Param(value = "itemId") Long itemId);

	/**
	 * 
	 * Description 根据营销活动id分页查询商品列表
	 * 
	 * Author Joe
	 * 
	 * @param page
	 * @param promotionGoodsDto
	 * @return
	 */
	List<PromotionGoodsDto> selectPromotionGoodsList(Page<PromotionGoodsDto> page, PromotionGoodsDto promotionGoodsDto);

	/**
	 * 
	 * Description 取消活动商品
	 * 
	 * Author Joe
	 * 
	 * @param promotionId
	 * @param itemId
	 */
	void deleteGoods(@Param(value = "promotionId") Long promotionId, @Param(value = "itemId") Long itemId);

	/**
	 * 
	 * Description 优惠券获取所选商品
	 * 
	 * Author Joe
	 * 
	 * @param itemDto
	 * @return
	 */
	List<PromotionGoodsDto> selectPromotionCouponGoods(ItemDto itemDto);

	/**
	 * 
	 * Description 根据商品sku编号查询所属营销活动 (单品级)
	 * 
	 * Author Joe
	 * 
	 * @param goodsSkuId
	 * @return
	 */
	List<PromotionGoodsDto> findPromotionSingleByGoodsSkuId(@Param("goodsSkuId") Long goodsSkuId);

	/**
	 * 
	 * Description 根据活动id和itmId回显商品(单品)
	 * 
	 * Author Joe
	 * 
	 * @param promotionId
	 * @param itemId
	 * @return
	 */
	List<PromotionGoodsDto> selectPromotionGoodsByPromotionIdAndItemid(@Param("promotionId") Long promotionId, @Param("itemId") Long itemId);

	/**
	 * 
	 * Description 根据活动ID查询所有活动商品(不分页)
	 * 
	 * Author Joe
	 * 
	 * @param promotionId
	 * @return
	 */
	List<PromotionGoodsDto> selectAllPromotionGoodsList(@Param("promotionId") Long promotionId);

	/**
	 * 
	 * Description 根据商品SkuId和营销活动id删除信息
	 * 
	 * Author Administrator   
	 * 
	 * @param goodsSkuId
	 * @param promotionId
	 */
	void deleteGoodsByGoodsSkuIdAndPromotionId(@Param("goodsSkuId")Long goodsSkuId, @Param("promotionId")Long promotionId);

	/**
	 * 
	 * Description 根据活动id和店铺id查询商品信息
	 * 
	 * Author Joe   
	 * 
	 * @param promotionId
	 * @param storeId
	 * @return
	 */
	List<PromotionGoodsDto> selectGoodsByPromotionIdAndStoreId(@Param("promotionId")Long promotionId, @Param("storeId")Long storeId);

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
	List<PromotionGoodsDto> selectStoreEnrolGoodsList(Page<PromotionGoodsDto> page, PromotionGoodsDto promotionGoodsDto);

	/**
	 * 
	 * Description 根据活动id,店铺id,itemId查询sku商品信息
	 * 
	 * Author Joe   
	 * 
	 * @return
	 */
	List<PromotionGoodsDto> selectGoodsByPromotionIdAndStoreIdAndItemId(@Param("promotionId")Long promotionId, @Param("storeId")Long storeId, @Param("itemId")Long itemId);

	/**
	 * 
	 * Description 报名商家商品审核列表(分页查询)
	 * 
	 * Author Joe    
	 * 
	 * @param page
	 * @param promotionGoodsDto
	 * @return
	 */
	List<PromotionGoodsDto> selectStoreGoodsAuditList(Page<PromotionGoodsDto> page, PromotionGoodsDto promotionGoodsDto);

	
}
