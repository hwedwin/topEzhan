package com.topaiebiz.transaction.cart.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.transaction.cart.dto.GoodsFavoriteDto;
import com.topaiebiz.transaction.cart.entity.GoodsFavoriteEntity;

/**
 * 
 * Description 收藏夹数据库访问层（以商品最小sku单元为收藏）
 * 
 * 
 * Author zhushuyong
 * 
 * Date 2017年9月11日 上午11:23:18
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface GoodsFavoriteDao extends BaseDao<GoodsFavoriteEntity> {

	/**
	 * 
	 * Description (根据会员id，sku最小单元商品id，查询当前会员收藏夹中是否存在此商品)
	 * 
	 * Author zhushuyong
	 * 
	 * @param memberId
	 *            会员ID
	 * @param goodsId
	 *            商品id
	 * @return
	 */
	GoodsFavoriteEntity selectByMemberItemId(@Param("memberId") Long memberId, @Param("itemId") Long itemId);

	/**
	 * Description 删除收藏夹
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            收藏夹id
	 * @return
	 */
	Integer deleteGoodsFavorite(Long[] id);

	/**
	 * Description 根据会员id查询收藏夹
	 * 
	 * Author Hedda
	 * 
	 * @param memberId
	 *            会员id
	 * @return
	 */
	List<GoodsFavoriteEntity> selectGoodsFavoriteByMemberId(Long memberId);

	/**
	 * Description 查询此商品是否被选为收藏
	 * 
	 * Author Hedda
	 * 
	 * @param memberId
	 *            会员id
	 * @param itemId
	 *            商品id
	 * @return
	 */
	GoodsFavoriteDto selectGoodsFavoriteByMemberIdAndItemId(@Param("memberId")Long memberId,@Param("itemId") Long itemId);

}
