package com.topaiebiz.goods.category.frontend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.topaiebiz.goods.category.frontend.dto.FrontBackCategoryDto;
import com.topaiebiz.goods.category.frontend.dto.FrontendCategoryDto;
import com.topaiebiz.goods.category.frontend.entity.FrontendCategoryEntity;

/**
 * Description 商品前台类目dao层
 * 
 * Author Hedda
 * 
 * Date 2017年8月25日 下午3:15:12
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface FrontendCategoryDao extends BaseMapper<FrontendCategoryEntity> {

	/**
	 * Description 商品前台一级类目
	 * 
	 * Author Hedda
	 * 
	 * @param frontendCategoryDto
	 *            商品前台类目dto
	 * @return
	 */
	List<FrontendCategoryDto> selectListOneLevelFrontendCategory(FrontendCategoryDto frontendCategoryDto);

	/**
	 * Description 商品前台一级，二级类目
	 * 
	 * Author Hedda
	 * 
	 * @param frontendCategoryDto
	 *            商品前台类目dto
	 * @return
	 */
	List<FrontendCategoryDto> selectListTwoOrThreeLevelFrontendCategory(FrontendCategoryDto frontendCategoryDto);

	/**
	 * Description 根据name查询商品前台类目
	 * 
	 * Author Hedda
	 * 
	 * @param name
	 *            商品前台类目名称
	 * @return
	 */
	FrontendCategoryEntity selectFrontendCategoryByName(FrontendCategoryEntity frontendCategory);

	/**
	 * Description 查询下级类目
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品前台类目id
	 * @return
	 */
	List<FrontendCategoryEntity> selectAllFrontendCategoryById(Long id);

	/**
	 * Description 删除商品前台类目
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品前台类目id
	 */
	void deleteFrontendCategory(Long id);

	/**
	 * Description 根据id查询商品前台类目
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品前台类目id
	 * @return
	 */
	FrontendCategoryDto selectFrontendCategoryById(Long id);

	/**
	 * Description 根据名称和id查询商品前台类目
	 * 
	 * Author Hedda
	 * 
	 * @param frontendCategoryDto
	 *            商品前台类目dto
	 * @return
	 */
	FrontendCategoryDto selectFrontendCategoryByNameAndId(FrontendCategoryDto frontendCategoryDto);

	/**
	 * Description 根据前台类目id查询出前台类目
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品前台类目id
	 * @return
	 */
	FrontendCategoryEntity selectFrontendCategoryEntityById(Long id);

	/**
	 * Description 根据第二级类目id查询第三级类目 
	 * 
	 * Author Hedda  
	 * 
	 * @param frontendCategoryId
	 * @return
	 */
	List<FrontendCategoryDto> selectThreeLevelFrontendCategory(Long frontendCategoryId);

	/**
	 * Description app端商品前台一级类目
	 * 
	 * Author Hedda
	 * 
	 * @param frontendCategoryDto
	 *            商品前台类目dto
	 * @return
	 */
	List<FrontendCategoryDto> selectAppListOneLevelFrontendCategory(FrontendCategoryDto frontendCategoryDto);

	/**
	 * Description 商品前台二级，三级类目
	 * 
	 * Author Hedda
	 * 
	 * @param frontendCategoryDto
	 *            商品前台类目dto
	 * @return
	 */
	List<FrontendCategoryDto> selectAppListTwoOrThreeLevelFrontendCategory(FrontendCategoryDto frontendCategoryDto);

	/**
	 * Description app端商家前台类目第三级类目列表    
	 * 
	 * Author Hedda  
	 * 
	 * @param storeId
	 * @return
	 */
	List<FrontendCategoryDto> selectAppThreeFrontendCategoryList(Long storeId);

	/**
	 * Description 装修时查询前台第三级类目列表    
	 * 
	 * Author Hedda  
	 * 
	 * @return
	 */
	List<FrontBackCategoryDto> selectThreeFrontendCategoryList(Page<FrontBackCategoryDto> page,Long storeId);

}
