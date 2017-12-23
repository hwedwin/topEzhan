package com.topaiebiz.goods.sku.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.goods.sku.dto.ItemPictureDto;
import com.topaiebiz.goods.sku.entity.ItemPictureEntity;

/**
 * Description 商品图片dao
 * 
 * Author Hedda
 * 
 * Date 2017年10月3日 下午7:16:30
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
@Mapper
public interface ItemPictureDao extends BaseDao<ItemPictureEntity> {

	/**
	 * Description 逻辑删除商品信息图片
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品图片信息的id
	 * @return
	 */
	Integer deleteItemPicture(Long id);

	/**
	 * Description 查询商品图片
	 * 
	 * Author Hedda
	 * 
	 * @param itemId
	 *            商品信息的id
	 * @return
	 */
	List<ItemPictureEntity> selectItemPicture(Long long1);

	/**
	 * Description 根据id查询商品图片
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品信息的id
	 * @return
	 */
	List<ItemPictureDto> selectItemPictureById(Long id);

}
