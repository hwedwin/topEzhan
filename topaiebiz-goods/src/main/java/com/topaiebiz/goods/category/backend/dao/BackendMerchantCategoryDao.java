package com.topaiebiz.goods.category.backend.dao;

import org.apache.ibatis.annotations.Param;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.goods.category.backend.entity.BackendMerchantCategoryEntity;

/**
 * Description 商家可用后台类目
 * 
 * Author Hedda 
 *    
 * Date 2017年11月8日 下午8:22:23 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
public interface BackendMerchantCategoryDao extends BaseDao<BackendMerchantCategoryEntity>{

	//根据
	BackendMerchantCategoryEntity selectByCategoryId(@Param("categoryId") Long categoryId, @Param("merchantId") Long merchantId);

	/**
	 * Description：删除商家后台类目
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param merchantId
	 * @return
	 */
	Integer deleteMerchantBackend(Long merchantId);
	
}
