package com.topaiebiz.goods.spu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.goods.spu.dto.GoodsSpuAttrDto;
import com.topaiebiz.goods.spu.entity.GoodsSpuAttrEntity;

/**
 * Description 商品spu属性dao
 * 
 * Author Hedda
 * 
 * Date 2017年9月29日 下午8:35:42
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
@Mapper
public interface GoodsSpuAttrDao extends BaseDao<GoodsSpuAttrEntity> {

	/**
	 * Description 查询商品spu所对应的商品spu属性
	 * 
	 * Author Hedda
	 * 
	 * @param spuId
	 *            商品spu的id
	 * @return
	 */
	List<GoodsSpuAttrEntity> selectGoodsSpuAttrBySpuId(Long spuId);

	/**
	 * Description 商品spu的属性逻辑删除
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品spu属性的id
	 */
	void deleteGoodsSpuAttr(Long id);

	/**
	 * Description 查询商品spu属性
	 * 
	 * Author Hedda
	 * 
	 * @param spuId
	 *            商品spu的id
	 * @return
	 */
	List<GoodsSpuAttrDto> selectListGoodsSpuAttrs(Long spuId);

}
