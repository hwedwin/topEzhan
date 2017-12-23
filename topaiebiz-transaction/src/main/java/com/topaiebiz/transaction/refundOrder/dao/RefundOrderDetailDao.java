package com.topaiebiz.transaction.refundOrder.dao;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.transaction.refundOrder.dto.RefundOrderDetailDto;
import com.topaiebiz.transaction.refundOrder.entity.RefundOrderDetailEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * Description 退货订单详情 
 * 
 * 
 * Author Joe 
 *    
 * Date 2017年10月24日 下午8:49:28 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public interface RefundOrderDetailDao extends BaseDao<RefundOrderDetailEntity> {

	/**
	 * 
	 * Description 根据退货订单编号查询退货商品详情 
	 * 
	 * Author Joe   
	 * 
	 * @param refundOrderId
	 * @return
	 */
	List<RefundOrderDetailDto> selectRefundOrderDetailByOrderId(@Param("orderId") Long orderId);

	/**
	*
	* Description: 根据退货订单编号查询退货商品详情
	*
	* Author: hxpeng
	* createTime: 2017/11/29
	*
	* @param:
	**/
	List<RefundOrderDetailEntity> selectOrderDetailByRefundOrderId(@Param("refundOrderId") Long refundOrderId);


	/**
	 * 
	 * Description 根据退货编号删除退货商品详情
	 * 
	 * Author Joe   
	 * 
	 * @param refundOrderId
	 */
	void deleteRefundOrderDetail(Long refundOrderId);


	/**
	 *
	 * Description: 查询售后订单详情集合 根据详情id集合和售后订单ID
	 *
	 * Author: hxpeng
	 * createTime: 2017/11/30
	 *
	 * @param:
	 **/
	List<RefundOrderDetailEntity> findRefundOrderDetailList(@Param("refundOrderDetailIds") List<Long> refundOrderDetailIds, @Param("refundOrderId") Long refundOrderId);

	/**
	*
	* Description: 查询商品最近申请的售后订单
	*
	* Author: hxpeng
	* createTime: 2017/12/7
	*
	* @param:
	**/
	List<RefundOrderDetailEntity> findByStoreOrderIdAndGoodsId(@Param("storeOrderId") Long storeOrderId, @Param("goodsId") Long goodsId);

	/**
	*
	* Description: 查询订单详情售后申请次数
	*
	* Author: hxpeng
	* createTime: 2017/12/8
	*
	* @param:
	**/
	List<RefundOrderDetailEntity> findRefundOrderByOrderDetailId(@Param("orderDetailId") Long orderDetailId);
}
