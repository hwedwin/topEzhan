package com.topaiebiz.transaction.order.merchant.dao;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.transaction.order.merchant.dto.OrderDetailsDto;
import com.topaiebiz.transaction.order.merchant.entity.OrderDetailsEntity;
import com.topaiebiz.transaction.order.merchant.entity.OrderInvoiceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * Description 店铺订单项的数据访问层  
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月2日 下午9:43:38 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface OrderDetailsDao extends BaseDao<OrderDetailsEntity> {
	
	/**
	*
	* Description: 根据订单ID获取订单详情ID
	*
	* Author: hxpeng
	* createTime: 2017/12/2
	*
	* @param:
	**/
	List<OrderDetailsEntity> selectOrderDetailByOrderId(Long orderId);
	
	/**
	 * Description 根据店铺订单ID查询订单详情
	 *
	 * Author Aaron.Xue   
	 * 
	 * @param orderId
	 * @return
	 */
	List<OrderDetailsEntity> selectlistByOrderId(Long orderId);

	/**
	 * 
	 * Description 根据营销活动ID查询使用活动记录  
	 * 
	 * Author Joe   
	 * 
	 * @param promotionId
	 * @return
	 */
	List<OrderDetailsDto> selectPromotionCount(Long usedPromotion);
	
	/**
	 * 
	 * Description 改变订单详情状态
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param state
	 * @param id
	 * @return
	 */
	Integer updateOrderDetailsStatus(@Param(value = "state")Integer state, @Param(value = "id")Long id);

	/**
	 * 
	 * Description 根据营销活动ID和商品sku和会员编号查询使用活动记录  
	 * 
	 * Author Joe   
	 * 
	 * @param promotionId
	 * @return
	 */
	List<OrderDetailsDto> selectPromotionGoodsNum(Long usedPromotion, Long goodsId, Long memberId);

	/**
	 * 
	 * Description： 根据订单编号查询礼卡的发票信息。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param id
	 * @return
	 */
	OrderInvoiceEntity selectOrederInvoiceByOrderId(Long id);


	/**
	 * 
	 * Description： 根据商品SKU ID 和使用的营销活动ID 查询单条店铺订单详情集合
	 * 
	 * Author hxpeng   
	 * 
	 * @param usedPromotionId
	 * @param goodsId
	 * @return
	 */
	List<OrderDetailsEntity> findOneByPromotionAndSkuId(@Param(value="usedPromotionId")Long usedPromotionId, @Param(value="goodsId")Long goodsId);

	/**
	 * 
	 * Description 根据订单编号和商品skuId获取店铺订单详情数据
	 * 
	 * Author Joe   
	 * 
	 * @param orderId
	 * @param goodsId
	 * @return
	 */
	OrderDetailsDto selectOrderDetailsByOrderIdAndSkuId(@Param(value="orderId") Long orderId, @Param(value="goodsId") Long goodsId);


	/**
	*
	* Description: 根据商品的skuIds 和 订单ID 查询订单详情集合
	*
	* Author: hxpeng
	* createTime: 2017/12/2
	*
	* @param:
	**/
	List<OrderDetailsEntity> getOrderDetailListBySkidsAndOrderId(@Param("skuIds") List<Long> skuIds, @Param("storeOrderId") Long storeOrderId);

	/**
	*
	* Description: 获取订单详情根据订单详情ID集合
	*
	* Author: hxpeng
	* createTime: 2017/12/6
	*
	* @param:
	**/
	List<OrderDetailsEntity> getOrderDetailByIds(@Param("orderDetailIds") List<Long> orderDetailIds);


	/**
	*
	* Description: 根据订单ID和 商品ID 获取该商品的订单详情ID
	*
	* Author: hxpeng
	* createTime: 2017/12/7
	*
	* @param:
	**/
	OrderDetailsEntity findByOrderIdAndGoodsId(@Param("orderId") Long orderId, @Param("goodsId") Long goodsId);

}
