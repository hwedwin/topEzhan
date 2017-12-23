package com.topaiebiz.goods.spu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.goods.spu.dto.GoodsSpuDto;
import com.topaiebiz.goods.spu.entity.GoodsSpuEntity;

/**
 * Description 商品spudao
 * 
 * Author Hedda
 * 
 * Date 2017年9月29日 下午8:40:18
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
@Mapper
public interface GoodsSpuDao extends BaseDao<GoodsSpuEntity> {

	/**
	 * Description 商品spu列表
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param goodsSpuDto
	 *            商品spudto
	 * @return
	 */
	List<GoodsSpuDto> selectListGoodsSpuDto(Page<GoodsSpuDto> page, GoodsSpuDto goodsSpuDto);

	/**
	 * Description 商品spu逻辑删除
	 * 
	 * Author Hedda
	 * 
	 * @param spuId
	 *            商品spu的id
	 */
	void deleteGoodsSpu(Long spuId);

	/**
	 * Description 根据商品spu编号查询商品spu
	 * 
	 * Author Hedda
	 * 
	 * @param spuCode
	 *            商品spu编号
	 * @return
	 */
	GoodsSpuEntity selectGoodsSpuBySpuCode(String spuCode);

	/**
	 * Description 根据id和编号查询商品spu信息
	 * 
	 * Author Hedda
	 * 
	 * @param goodsSpuDto
	 *            商品spu的dto
	 * @return
	 */
	GoodsSpuDto selectGoodsSpuBySpuCodeAndId(GoodsSpuDto goodsSpuDto);

	/**
	 * Description 根据商品名称查询商品spu
	 * 
	 * Author Hedda
	 * 
	 * @param name
	 *            商品spu名称
	 * @return
	 */
	GoodsSpuEntity selectGoodsSpuByName(String name);

	/**
	 * Description 根据商品名称和id查询商品spu信息
	 * 
	 * Author Hedda
	 * 
	 * @param goodsSpuDto
	 *            商品spu的dto
	 * @return
	 */
	GoodsSpuDto selectGoodsSpuByNameAndId(GoodsSpuDto goodsSpuDto);

	/**
	 * Description 根据id查询商品spu信息
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品spu的id
	 * @return
	 */
	GoodsSpuDto selectGoodsSpuById(Long id);

	/**
	 * Description 根据商品spu的id查询商品spu信息
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品spu的id
	 * @return
	 */
	GoodsSpuDto selectGoodsSpuBySpuId(Long id);

	/**
	 * Description 根据类目id查询是否有商品spu
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            类目id
	 * @return
	 */
	List<GoodsSpuDto> selectGoodsSpuByBelongCategory(Long id);

	/**
	 * Description  商家根据商品模板发布商品列表  
	 * 
	 * Author Hedda  
	 * 
	 * @param page
	 * @param goodsSpuDto
	 * @return
	 */
	List<GoodsSpuDto> selectGoodsSpuListByBelongCategory(Page<GoodsSpuDto> page, GoodsSpuDto goodsSpuDto);

}
