package com.topaiebiz.transaction.refundOrder.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.transaction.order.merchant.dto.OrderListParamsDto;
import com.topaiebiz.transaction.refundOrder.dto.APPRefundOrderListDto;
import com.topaiebiz.transaction.refundOrder.dto.PCRefundOrderListDto;
import com.topaiebiz.transaction.refundOrder.dto.RefundOrderDto;
import com.topaiebiz.transaction.refundOrder.entity.RefundOrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * Description 退货订单详情
 * 
 * 
 * Author Joe
 * 
 * Date 2017年10月17日 下午2:01:38
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface RefundOrderDao extends BaseDao<RefundOrderEntity> {

	/**
	 * 
	 * Description 获取退货订单列表
	 * 
	 * Author Joe   
	 * 
	 * @param page
	 * @param refundOrderDto
	 * @return
	 */
	List<RefundOrderDto> getRefundOrderList(Page<RefundOrderDto> page, RefundOrderDto refundOrderDto);


	/**
	 *
	 * Description: PC：退款/退货订单列表
	 *
	 * Author: hxpeng
	 * createTime: 2017/11/29
	 *
	 * @param:
	 **/
	List<PCRefundOrderListDto> getRefundOrderListInPC(Page<PCRefundOrderListDto> page, OrderListParamsDto orderListParamsDto);


	/**
	 *
	 * Description: APP：退款/退货订单列表
	 *
	 * Author: hxpeng
	 * createTime: 2017/11/29
	 *
	 * @param:
	 **/
	List<APPRefundOrderListDto> getRefundOrderListInAPP(Page<APPRefundOrderListDto> page, @Param("memberId") Long memberId);

	/**
	*
	* Description: 根据支付店铺订单 查询售后订单集合
	*
	* Author: hxpeng
	* createTime: 2017/12/6
	*
	* @param:
	**/
	List<RefundOrderEntity> findRefundOrderByStoreOrderId(@Param("storeOrderId") Long storeOrderId);


}
