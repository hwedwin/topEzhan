package com.topaiebiz.promotion.mgmt.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.promotion.mgmt.entity.PromotionPlatformUsageLogEntity;

/**
 * 
 * Description 平台活动使用记录表
 * 
 * 
 * Author Joe
 * 
 * Date 2017年9月27日 下午4:19:24
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface PromotionPlatformUsageLogDao extends BaseDao<PromotionPlatformUsageLogEntity> {

	/**
	 * 
	 * Description 查询优惠码已使用数量
	 * 
	 * Author Joe
	 * 
	 * @param marketId
	 * @return
	 */
	Integer selectCountUse(@Param(value = "marketId") Long marketId);

	/**
	 * 
	 * Description： 根据订单ID获取营销活动使用的记录
	 * 
	 * Author hxpeng
	 * 
	 * @param orderId
	 * @return
	 */
	PromotionPlatformUsageLogEntity selectOneByOrderId(Long orderId);

}
