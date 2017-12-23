package com.topaiebiz.promotion.mgmt.dao;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.promotion.mgmt.entity.PromotionStoreUsageLogEntity;

public interface PromotionStoreUsageLogDao extends BaseDao<PromotionStoreUsageLogEntity> {

	/**
	 * 
	 * Description： 根据订单ID获取营销活动使用的记录
	 * 
	 * Author hxpeng
	 * 
	 * @param orderId
	 * @return
	 */
	PromotionStoreUsageLogEntity selectOneByOrderId(Long orderId);

}
