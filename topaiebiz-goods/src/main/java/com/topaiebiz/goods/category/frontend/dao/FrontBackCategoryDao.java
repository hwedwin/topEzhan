package com.topaiebiz.goods.category.frontend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.goods.category.frontend.dto.FrontBackCategoryDto;
import com.topaiebiz.goods.category.frontend.entity.FrontBackCategoryEntity;

/**
 * Description 商品前台后台类目绑定dao
 * 
 * Author Hedda
 * 
 * Date 2017年9月28日 下午3:15:34
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
@Mapper
public interface FrontBackCategoryDao extends BaseDao<FrontBackCategoryEntity> {

	/**
	 * Description 删除商品前后台绑定类目
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品前台类目id
	 */
	void deleteFrontBackCategory(Long id);

	/**
	 * Description 根据前台类目id查询商品前后台绑定数据
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品前台类目id
	 * @return
	 */
	List<FrontBackCategoryEntity> selectAllFrontBackCategoryById(Long id);

	/**
	 * Description 根据前台后台id查询绑定类目信息
	 * 
	 * Author Hedda
	 * 
	 * @param frontBackCategoryDto
	 *            绑定类目dto
	 * @return
	 */
	FrontBackCategoryDto selectFrontBackCategory(FrontBackCategoryDto frontBackCategoryDto);

	/**
	 * Description 根据后台类目id查询绑定类目表
	 * 
	 * Author Hedda
	 * 
	 * @param backId
	 *            后台类目id
	 * @return
	 */
	List<FrontBackCategoryDto> selectFrontBackCategoryByBackId(Long backId);

	/**
	 * Description app端根据前台第三级类目id查询出关联的后台类目
	 * 
	 * Author Hedda  
	 * 
	 * @param frontendId
	 * @return
	 */
	List<FrontBackCategoryEntity> selectFrontBackCategoryByFrontId(@Param("frontendId")Long frontendId);

}
