package com.topaiebiz.goods.brand.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.goods.brand.dto.BrandDto;
import com.topaiebiz.goods.brand.entity.BrandEntity;

/**
 * Description 商品品牌dao层
 * 
 * Author Hedda
 * 
 * Date 2017年8月23日 下午4:17:10
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface BrandDao extends BaseDao<BrandEntity> {

	/**
	 * Description 根据id查询品牌
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品品牌ID
	 * @return
	 */
	BrandDto selectBrandById(Long id);

	/**
	 * Description 商品品牌分页检索
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param brandDto
	 *            商品品牌dto
	 * @return
	 */
	List<BrandDto> selectPageBrand(Page<BrandDto> page, BrandDto brandDto);

	/**
	 * Description 商品品牌批量逻辑删除
	 * 
	 * Author Hedda
	 * 
	 * @param ids
	 *            商品品牌ids
	 * @return
	 */
	Integer deleteBrands(Long[] id);

	/**
	 * Description 查看商品列表
	 * 
	 * Author Hedda
	 * 
	 * @return
	 */
	List<BrandDto> selectListBrand();

	/**
	 * Description 根据品牌名称查询商品品牌信息
	 * 
	 * Author Hedda
	 * 
	 * @param name
	 *            商品品牌名称
	 * @return
	 */
	BrandEntity selectBrandByName(String name);

	/**
	 * Description 根据品牌编号查询商品品牌信息
	 * 
	 * Author Hedda
	 * 
	 * @param brandCode
	 *            商品品牌编码
	 * @return
	 */
	BrandEntity selectBrandByBrandCode(String brandCode);

	/**
	 * Description 根据品牌id查询商品品牌信息
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品品牌id
	 * @return
	 */
	BrandEntity selectBrandEntityById(Long id);

	/**
	 * Description 根据id和编号 查询商品品牌信息
	 * 
	 * Author Hedda
	 * 
	 * @param brandDto
	 *            商品品牌dto
	 * @return
	 */
	BrandDto selectBrandByBrandCodeAndId(BrandDto brandDto);

	/**
	 * Description 根据id和名称查询商品品牌信息
	 * 
	 * Author Hedda
	 * 
	 * @param brandDto
	 *            商品品牌dto
	 * @return
	 */
	BrandDto selectBrandByNameAndId(BrandDto brandDto);

}
