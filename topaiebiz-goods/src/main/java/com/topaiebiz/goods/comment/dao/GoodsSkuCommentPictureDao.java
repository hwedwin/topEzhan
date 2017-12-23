package com.topaiebiz.goods.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.goods.comment.dto.GoodsSkuCommentPictureDto;
import com.topaiebiz.goods.comment.entity.GoodsSkuCommentPictureEntity;

/**
 * Description 商品sku评价图片dao
 * 
 * Author Hedda
 * 
 * Date 2017年10月2日 下午8:15:05
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
@Mapper
public interface GoodsSkuCommentPictureDao extends BaseDao<GoodsSkuCommentPictureEntity> {

	/**
	 * Description 根据id查询商品sku评价图片
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品sku
	 * @return
	 */
	List<GoodsSkuCommentPictureEntity> selectGoodsSkuCommentPicture(Long id);

	/**
	 * Description 根据id删除商品sku图片
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 * @return
	 */
	Integer deleteGoodsSkuCommentPicture(Long id);

	/**
	 * Description 查询评价图片
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            评价id
	 * @return
	 */
	List<GoodsSkuCommentPictureDto> selectGoodsSkuCommentPictureDto(Long id);

}
