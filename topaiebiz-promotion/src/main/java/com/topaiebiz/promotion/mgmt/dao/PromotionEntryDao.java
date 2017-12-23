package com.topaiebiz.promotion.mgmt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.promotion.mgmt.dto.PromotionEntryDto;
import com.topaiebiz.promotion.mgmt.entity.PromotionEntryEntity;

/**
 * 
 * Description 营销活动商家报名  
 * 
 * 
 * Author Joe  
 *    
 * Date 2017年12月6日 下午8:44:49 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface PromotionEntryDao extends BaseDao<PromotionEntryEntity> {

	/**
	 * 
	 * Description 根据活动id查询商家报名信息
	 * 
	 * Author Joe   
	 * 
	 * @param id
	 * @return
	 */
	List<PromotionEntryDto> selectPromotionEntryByPromotionId(@Param("promotionId")Long promotionId);

	/**
	 * 
	 * Description 营销活动报名商家列表
	 * 
	 * Author Joe    
	 * 
	 * @param page
	 * @param promotionEntryDto
	 * @return
	 */
	List<PromotionEntryDto> selectPromotionEnrolStoreList(Page<PromotionEntryDto> page, PromotionEntryDto promotionEntryDto);

	/**
	 * 
	 * Description 查看店铺报名详情
	 * 
	 * Author Joe   
	 * 
	 * @param id
	 * @return
	 */
	PromotionEntryDto selectPromotionEnrolStore(@Param("id")Long id);

	/**
	 * 
	 * Description 根据活动id和店铺id查询报名信息
	 * 
	 * Author Joe    
	 * 
	 * @param id
	 * @param storeId
	 * @return
	 */
	PromotionEntryDto selectPromotionEntryByPromotionIdAndStoreId(@Param("promotionId")Long promotionId, @Param("storeId")Long storeId);

}
