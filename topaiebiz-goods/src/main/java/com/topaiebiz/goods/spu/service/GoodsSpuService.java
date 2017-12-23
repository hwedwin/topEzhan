package com.topaiebiz.goods.spu.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.goods.category.backend.dto.BackendCategorysDto;
import com.topaiebiz.goods.spu.dto.GoodsSpuAttrDto;
import com.topaiebiz.goods.spu.dto.GoodsSpuDto;
import com.topaiebiz.goods.spu.dto.GoodsSpuPictureDto;
import com.topaiebiz.goods.spu.entity.GoodsSpuAttrEntity;
import com.topaiebiz.goods.spu.entity.GoodsSpuEntity;
import com.topaiebiz.goods.spu.entity.GoodsSpuPictureEntity;

/**
 * Description 商品SPU管理接口
 * 
 * Author Hedda
 * 
 * Date 2017年9月29日 下午8:10:59
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
public interface GoodsSpuService {

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
	Page<GoodsSpuDto> getListGoodsSpuDto(Page<GoodsSpuDto> page, GoodsSpuDto goodsSpuDto);

	/**
	 * Description 商品spu批量逻辑删除
	 * 
	 * Author Hedda
	 * 
	 * @param ids
	 *            商品spu的id
	 * @return
	 * @throws GlobalException
	 */
	void removeGoodsSpu(Long[] ids) throws GlobalException;

	/**
	 * Description 查询最近使用类目
	 * 
	 * Author Hedda
	 * 
	 * @return
	 */
	List<BackendCategorysDto> getRecentlyCategoryList();

	/**
	 * Description 添加spu商品
	 * 
	 * Author Hedda
	 * 
	 * @param goodsSpu
	 *            商品spu
	 * @param goodsSpuAttrEntities
	 *            商品spu属性
	 * @param goodsSpuPictureEntities
	 *            商品spu图片
	 * @return
	 */
	Integer saveGoodsSpu(GoodsSpuEntity goodsSpu, List<GoodsSpuAttrEntity> goodsSpuAttrEntities,
			List<GoodsSpuPictureEntity> goodsSpuPictureEntities) throws GlobalException;

	/**
	 * Description 商品spu修改
	 * 
	 * Author Hedda
	 * 
	 * @param goodsSpuDto
	 *            商品spu
	 * @param goodsSpuAttrDtos
	 *            商品spu属性
	 * @param goodsSpuPictureDtos
	 *            商品spu图片
	 * @return
	 * @throws GlobalException
	 */
	Integer modifyGoodsSpu(GoodsSpuDto goodsSpuDto, List<GoodsSpuAttrDto> goodsSpuAttrDtos,
			List<GoodsSpuPictureDto> goodsSpuPictureDtos) throws GlobalException;

	/**
	 * Description 根据id查询商品spu
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 * @return
	 * @throws GlobalException
	 */
	GoodsSpuDto findGoodsSpuById(Long id) throws GlobalException;

	/**
	 * Description 根据id查询商品spu属性
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 * @return
	 * @throws GlobalException
	 */
	List<GoodsSpuAttrDto> findGoodsSpuAttrById(Long id) throws GlobalException;

	/**
	 * Description 根据id查询商品spu图片
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 * @return
	 * @throws GlobalException
	 */
	List<GoodsSpuPictureDto> findGoodsSpuPictureById(Long id) throws GlobalException;

	/**
	 * Description 查看商品编码验证唯一
	 * 
	 * Author Hedda
	 * 
	 * @param goodsSpuDto
	 *            商品dto
	 * @return
	 * @throws GlobalException
	 */
	GoodsSpuDto getGoodsSpuByCode(GoodsSpuDto goodsSpuDto) throws GlobalException;

	/**
	 * Description 查看商品名称验证唯一
	 * 
	 * Author Hedda
	 * 
	 * @param goodsSpuDto
	 *            商品dto
	 * @return
	 * @throws GlobalException
	 */
	GoodsSpuDto getGoodsSpuByName(GoodsSpuDto goodsSpuDto) throws GlobalException;

	/**
	 * Description 商家根据商品模板发布商品列表
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param goodsSpuDto
	 *            商品spudto
	 * @return
	 */
	Page<GoodsSpuDto> getGoodsSpuListByBelongCategory(Page<GoodsSpuDto> page, GoodsSpuDto goodsSpuDto);

	/**
	 * Description 修改商品spu的类目
	 * 
	 * Author Hedda
	 * 
	 * @param spuId
	 *            商品的id
	 * @param belongCategory
	 *            商品后台第三级类目id
	 * @return
	 * @throws GlobalException
	 */
	Integer modifyGoodsSpuCategory(Long spuId, Long belongCategory)throws GlobalException;

}
