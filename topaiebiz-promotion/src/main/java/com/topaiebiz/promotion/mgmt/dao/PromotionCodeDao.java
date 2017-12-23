package com.topaiebiz.promotion.mgmt.dao;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.promotion.mgmt.entity.PromotionCodeEntity;

/**
 * 
 * Description 活动优惠码
 * 
 * 
 * Author Joe
 * 
 * Date 2017年9月28日 下午2:37:45
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface PromotionCodeDao extends BaseDao<PromotionCodeEntity> {

	/**
	 * 
	 * Description 删除营销活动对应商品
	 * 
	 * Author Joe
	 * 
	 * @param id
	 */
	void deletePromotionCode(Long promotionId);

}
