package com.topaiebiz.transaction.order.merchant.dao;

import org.apache.ibatis.annotations.Mapper;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.transaction.order.merchant.entity.OrderAddressEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * Description 订单收货地址的数据访问层  
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月4日 下午2:11:13 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface OrderAddressDao extends BaseDao<OrderAddressEntity> {

	/**
	*
	* Description: 根据店铺订单ID
	*
	* Author: hxpeng
	* createTime: 2017/11/9
	*
	* @param:
	**/
	OrderAddressEntity getOrderAddressByOrderId(@Param(value = "orderId") Long orderId);


}
