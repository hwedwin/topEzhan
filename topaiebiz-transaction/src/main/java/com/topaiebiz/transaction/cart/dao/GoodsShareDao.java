package com.topaiebiz.transaction.cart.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.transaction.cart.entity.GoodsShareEntity;

/**
 * Description 商品分享dao
 * 
 * Author Hedda
 * 
 * Date 2017年10月29日 下午7:52:10
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
@Mapper
public interface GoodsShareDao extends BaseDao<GoodsShareEntity> {

	/**
	 * Description 根据会员id和itemid查询分享商品
	 * 
	 * Author Hedda
	 * 
	 * @param memberId
	 *            会员id
	 * @param itemId
	 *            item的id
	 * @return
	 */
	GoodsShareEntity selectGoodsShareByMemberItemId(@Param("memberId")Long memberId,@Param("itemId") Long itemId);

}
