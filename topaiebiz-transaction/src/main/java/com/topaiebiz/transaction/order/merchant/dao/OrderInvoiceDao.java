package com.topaiebiz.transaction.order.merchant.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.transaction.order.merchant.entity.OrderInvoiceEntity;

/**
 * 
 * Description 订单发票的数据访问层  
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月4日 下午3:31:03 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface OrderInvoiceDao extends BaseDao<OrderInvoiceEntity> {
	
	/**
	 * 
	 * Description 根据订单id查询订单发票 
	 * 
	 * Author zhushuyong   
	 * 
	 * @param orderId
	 * 			订单id
	 * @return
	 */
	OrderInvoiceEntity selectOrderInvoice(@Param("orderId") Long orderId);

}
