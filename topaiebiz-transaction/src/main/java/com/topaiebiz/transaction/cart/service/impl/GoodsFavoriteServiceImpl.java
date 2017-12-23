package com.topaiebiz.transaction.cart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.topaiebiz.transaction.cart.dao.GoodsFavoriteDao;
import com.topaiebiz.transaction.cart.entity.GoodsFavoriteEntity;
import com.topaiebiz.transaction.cart.service.GoodsFavoriteService;

/**
 * 
 * Description 收藏夹数据库访问层（以商品最小sku单元为收藏）业务接口实现
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月11日 上午11:29:20 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
public class GoodsFavoriteServiceImpl implements GoodsFavoriteService {
	
	@Autowired
	private GoodsFavoriteDao goodsFavoriteDao;

	@Override
	public GoodsFavoriteEntity findByMemberSkuId(Long memberId,Long goodsId) {
		return goodsFavoriteDao.selectByMemberItemId(memberId,goodsId);
	}

	@Override
	public GoodsFavoriteEntity findById(GoodsFavoriteEntity goodsFavoriteEntity) {
		return goodsFavoriteDao.selectOne(goodsFavoriteEntity);
	}

	@Override
	public GoodsFavoriteEntity findById(Long id) {
		return goodsFavoriteDao.selectById(id);
	}

	@Override
	public Integer save(GoodsFavoriteEntity goodsFavoriteEntity) {
		return goodsFavoriteDao.insert(goodsFavoriteEntity);
	}

}
