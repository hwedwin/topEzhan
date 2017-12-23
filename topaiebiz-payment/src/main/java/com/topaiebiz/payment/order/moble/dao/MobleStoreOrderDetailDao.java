package com.topaiebiz.payment.order.moble.dao;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.goods.sku.dto.StoreOrderDetailDto;
import com.topaiebiz.payment.order.moble.entity.StoreOrderDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**  
 * Description： 订单详情接口类
 * 
 * 
 * Author hxpeng 
 *    
 * Date 2017年10月25日 下午7:43:34 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@Mapper
public interface MobleStoreOrderDetailDao extends BaseDao<StoreOrderDetailEntity> {

	/**
	 * 
	 * Description： 根据店铺订单ID查询订单详情集合
	 * 
	 * Author hxpeng   
	 * 
	 * @param orderId
	 * @return
	 */
	List<StoreOrderDetailEntity> findMobleStoreOrderDetailByOrderId(Long orderId);
	
	/**
	 * 
	 * Description： 根据商品SKU ID 和使用的营销活动ID 查询单条店铺订单详情集合
	 * 
	 * Author hxpeng   
	 * 
	 * @param usedPromotionId
	 * @param skuId
	 * @return
	 */
	List<StoreOrderDetailEntity> findOneByPromotionAndSkuId(Long usedPromotionId, Long skuId);

	/**
	*
	* Description: 查询订单详情集合 根据详情id集合he
	*
	* Author: hxpeng
	* createTime: 2017/11/30
	*
	* @param:
	**/
	List<StoreOrderDetailEntity> findOrderDetailList(@Param("orderDetailsIds") List<Long> orderDetailsIds, @Param("storeOrderId") Long storeOrderId);

	/**
	*
	* Description: 获取订单下 未发货的列表
	*
	* Author: hxpeng
	* createTime: 2017/12/1
	*
	* @param:
	**/
	List<StoreOrderDetailDto> getWaitToDeliverGoods(@Param("storeOrderId") Long storeOrderId);
}
