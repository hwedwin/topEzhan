package com.topaiebiz.goods.spu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.goods.spu.dto.GoodsSpuPictureDto;
import com.topaiebiz.goods.spu.entity.GoodsSpuPictureEntity;

/**
 * Description 商品spu图片dao
 * 
 * Author Hedda
 * 
 * Date 2017年9月29日 下午8:42:14
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
@Mapper
public interface GoodsSpuPictureDao extends BaseDao<GoodsSpuPictureEntity> {

	/**
	 * Description 查询商品spu所对应的商品spu图片
	 * 
	 * Author Hedda
	 * 
	 * @param spuId
	 *            商品spu的id
	 * @return
	 */
	List<GoodsSpuPictureEntity> selectGoodsSpuPictureBySpuId(Long spuId);

	/**
	 * Description 商品spu图片的逻辑删除
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品spu图片的id
	 */
	void deleteGoodsSpuPicture(Long id);

	/**
	 * Description 查询商品spu图片
	 * 
	 * Author Hedda
	 * 
	 * @param spuId
	 *            商品spu的id
	 * @return
	 */
	List<GoodsSpuPictureDto> selectGoodsSpuPictures(Long spuId);

}
