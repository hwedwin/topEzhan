package com.topaiebiz.distribution.merchant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.distribution.merchant.dto.GoodsDistributionDto;
import com.topaiebiz.distribution.merchant.entity.GoodsDistributionEntity;
import com.topaiebiz.merchant.enter.dto.StoreInfoDto;
import com.topaiebiz.transaction.order.merchant.dto.StoreOrderDto;

/**
 * Description： 商品分销Dao。
 * 
 * Author Harry
 * 
 * Date 2017年10月3日 下午3:09:36
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface GoodsDistributionDao extends BaseDao<GoodsDistributionEntity> {

	/**
	 * Description：根据SkuId查询商品分销比例信息
	 * 
	 * Author Harry
	 * 
	 * @param id
	 * @return
	 */
	GoodsDistributionEntity selectBySkuId(Long id);

	/**
	 * 
	 * Description：查询店铺订单数据
	 * 
	 * Author Harry
	 * 
	 * @return
	 */
	List<StoreOrderDto> selectStoreOrderList();

	/**
	 * Description：根据goodsId查询分润比例和店铺等级。
	 * 
	 * Author Harry
	 * 
	 * @param goodsId
	 * @param merchantGradeId
	 * @returns
	 */
	GoodsDistributionEntity findStoreRatioByGoodsId(@Param("goodsId") Long goodsId,
			@Param("merchantGradeId") Long merchantGradeId);

	/**
	 * Description：根据店铺id查询店铺信息
	 * 
	 * Author Harry
	 * 
	 * @param storeId
	 * @return
	 */
	StoreInfoDto selectStoreInfoByStoreId(Long storeId);

	/**
	 * Description： 通过skuId查询分销记录。
	 * 
	 * Author Harry
	 * 
	 * @param skuId
	 * @return
	 */
	List<GoodsDistributionDto> findGoodsDistributionAllocationBySkuId(Long skuId);

	/**
	 * Description： 删除商品分销记录
	 * 
	 * Author Harry
	 * 
	 * @param id
	 *            商品sku的id
	 * @return
	 */
	Integer deleteGoodsDistribution(Long id);


}
