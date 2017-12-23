package com.topaiebiz.transaction.cart.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.transaction.cart.entity.GoodsFootprintEntity;

/**
 * Description 我的足迹dao
 * 
 * Author Hedda
 * 
 * Date 2017年11月16日 下午5:18:54
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
@Mapper
public interface GoodsFootprintDao extends BaseDao<GoodsFootprintEntity> {

	/**
	 * Description 根据会员id和商品id查询足迹
	 * 
	 * Author Hedda
	 * 
	 * @param memberId
	 *            会员id
	 * @param itemId
	 *            商品id
	 * @return
	 */
	GoodsFootprintEntity selectGoodsFootprintByGoodsIdAndMemberId(@Param("memberId") Long memberId,
			@Param("itemId") Long itemId);

	/**
	 * Description 删除足迹
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品id
	 * @return
	 */
	Integer deleteGoodsFootprint(Long[] id);

	/**
	 * Description 根据会员id查询足迹表
	 * 
	 * Author Hedda
	 * 
	 * @param memberId
	 *            会员id
	 * @return
	 */
	List<GoodsFootprintEntity> selectGoodsFootprintByMemberId(Long memberId);

	/**
	 * Description 删除足迹
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            足迹id
	 * @return
	 */
	Integer deleteGoodsFootprints(Long id);

}
