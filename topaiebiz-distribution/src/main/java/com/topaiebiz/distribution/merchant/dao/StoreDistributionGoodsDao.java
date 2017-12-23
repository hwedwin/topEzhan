package com.topaiebiz.distribution.merchant.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.distribution.merchant.entity.StoreDistributionGoodsEntity;

/**
 * Description： 店铺分销商品dao
 * 
 * Author Harry
 * 
 * Date 2017年10月29日 下午6:09:07
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface StoreDistributionGoodsDao extends BaseDao<StoreDistributionGoodsEntity> {

	/**
	 * Description 商家端取消选售商品
	 * 
	 * Author Hedda
	 * 
	 * @param storeId
	 * @param id
	 * @return
	 */
	Integer deleteStoreDistributionGoods(@Param("storeId") Long storeId, @Param("id") Long id);

	/**
	 * Description 根据商家id和商品id查询是否分销此商品
	 * 
	 * Author Hedda
	 * 
	 * @param storeId
	 *            店铺id
	 * @param id
	 *            item的id
	 * @return
	 */
	StoreDistributionGoodsEntity selectStoreDistributionGoods(@Param("storeId") Long storeId, @Param("id") Long itemId);

}
