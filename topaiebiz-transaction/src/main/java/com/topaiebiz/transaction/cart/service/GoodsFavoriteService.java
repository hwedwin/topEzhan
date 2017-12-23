package com.topaiebiz.transaction.cart.service;

import com.topaiebiz.transaction.cart.entity.GoodsFavoriteEntity;

/**
 * 
 * Description 收藏夹数据库访问层（以商品最小sku单元为收藏）业务接口 
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月11日 上午11:28:06 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public interface GoodsFavoriteService {
	
	GoodsFavoriteEntity findById(GoodsFavoriteEntity goodsFavoriteEntity);
	
	GoodsFavoriteEntity findById(Long id);
	
	Integer save(GoodsFavoriteEntity goodsFavoriteEntity);
	
	/**
	 * 
	 * Description 根据会员id，sku最小单元商品id，查询当前会员收藏夹中是否存在此商品   
	 * 
	 * Author zhushuyong   
	 * 
	 * @param goodsFavoriteDto
	 * @return
	 */
	GoodsFavoriteEntity findByMemberSkuId(Long memberId,Long goodsId);

}
