package com.topaiebiz.payment.order.moble.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.transaction.order.merchant.entity.StoreOrderEntity;
import com.topaiebiz.transaction.order.total.dto.TotalOrderParamDto;

/**  
 * Description： 订单接口类
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
public interface MobleStoreOrderDao extends BaseDao<StoreOrderEntity> {

	/**
	 * 
	 * Description： 查询订单---统计报表功能调用
	 * 
	 * Author hxpeng   
	 * 
	 * @param totalOrderParamDto
	 * @return
	 */
	List<StoreOrderEntity> selectStoreOrderByStatistics(TotalOrderParamDto totalOrderParamDto);
	
}
