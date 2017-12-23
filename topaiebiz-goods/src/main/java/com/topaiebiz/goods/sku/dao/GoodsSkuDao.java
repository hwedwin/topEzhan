package com.topaiebiz.goods.sku.dao;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.goods.sku.dto.GoodsSkuDto;
import com.topaiebiz.goods.sku.entity.GoodsSkuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description 商品sku的dao
 * 
 * Author Hedda
 * 
 * Date 2017年10月3日 下午7:09:38
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
@Mapper
public interface GoodsSkuDao extends BaseDao<GoodsSkuEntity> {

	/**
	 * Description 商品商品sku信息
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品sku的id
	 * @return
	 */
	Integer deleteGoodsSku(Long id);

	/**
	 * Description 查询商品sku信息
	 * 
	 * Author Hedda
	 * 
	 * @param itemId
	 *            商品item的id
	 * @return
	 */
	List<GoodsSkuEntity> selectGoodsSku(Long itemId);

	/**
	 * Description 根据id查询商品sku信息集合
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 */
	List<GoodsSkuDto> selectGoodsSkuById(Long itemId);

	/**
	 * Description 根据skuId查询商品sku信息
	 * 
	 * Author Hedda
	 * 
	 * @param skuId
	 *            商品sku的id
	 * @return
	 */
	GoodsSkuEntity selectGoodsSkuBySkuId(Long skuId);

	/**
	 * Description 根据skuId查询商品sku信息(商品状态为2)
	 * 
	 * Author Hedda
	 * 
	 * @param skuId
	 *            商品sku的id
	 * @return
	 */
	GoodsSkuDto selectGoodsSkuDtoBySkuId(Long skuId);
	
	/**
	 * Description 根据skuId查询商品sku信息
	 * 
	 * Author Hedda
	 * 
	 * @param skuId
	 *            商品sku的id
	 * @return
	 */
	GoodsSkuDto selectGoodsSkuDtoById(Long skuId);

	/**
	 * Description 根据itemId查询商品sku信息以及营销的信息  
	 * 
	 * Author Hedda  
	 * 
	 * @param itemId
	 * @return
	 */
	List<GoodsSkuDto> selectPromotionByItemId(@Param("itemId")Long itemId,@Param("promotionId")Long promotionId);


	/**
	*
	* Description: 减少库存
	*
	* Author: hxpeng
	* createTime: 2017/11/30
	*
	* @param:
	**/
	Integer reduceStock(@Param("number") Integer number, @Param("skuId") Long skuId);
}
