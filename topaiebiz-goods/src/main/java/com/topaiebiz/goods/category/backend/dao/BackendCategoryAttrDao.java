package com.topaiebiz.goods.category.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.goods.category.backend.dto.BackendCategoryAttrDto;
import com.topaiebiz.goods.category.backend.entity.BackendCategoryAttrEntity;

/**
 * Description 商品后台类目dao
 * 
 * Author Hedda
 * 
 * Date 2017年8月24日 下午4:48:35
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface BackendCategoryAttrDao extends BaseDao<BackendCategoryAttrEntity> {

	/**
	 * Description 商品三级类目中所对应的规格属性
	 * 
	 * Author Hedda
	 * 
	 * @param belongCategory
	 *            商品后台类目id
	 * @return
	 * @throws Exception
	 */
	List<BackendCategoryAttrEntity> selectListBackendCategoryAttr(Long belongCategory);

	/**
	 * Description 删除叶子类目下面的属性（逻辑删除）
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品类目id
	 * @return
	 */
	Integer deleteBackendCategoryAttr(Long id);

	/**
	 * Description 根据id查询商品后台类目规格属性
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品后台类目规格属性id
	 * @return
	 */
	BackendCategoryAttrDto selectBackendCategoryAttrById(Long id);

	/**
	 * Description 根据商品类目属性名称查询商品类目属性
	 * 
	 * Author Hedda
	 * 
	 * @param backendCategoryAttr
	 *            商品后台类目属性
	 * @return
	 */
	BackendCategoryAttrEntity selectBackendCategoryAttrByName(BackendCategoryAttrEntity backendCategoryAttr);

	/**
	 * Description 添加商品后台类目属性
	 * 
	 * Author Hedda
	 * 
	 * @param backendCategoryAttr
	 *            商品后台类目属性
	 * @return
	 */
	Integer insertBackendCategoryAttr(BackendCategoryAttrEntity backendCategoryAttr);

	/**
	 * Description 根据商品后台类目属性id查询商品后台类目属性
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品后台类目属性id
	 * @return
	 */
	BackendCategoryAttrEntity selectBackendCategoryAttrEntityById(Long id);

	/**
	 * Description 根据商品后台类目属性id和名称查询商品后台类目属性
	 * 
	 * Author Hedda
	 * 
	 * @param backendCategoryAttrDto
	 *            商品后台类目属性dto
	 * @return
	 */
	BackendCategoryAttrDto selectBackendCategoryAttrByNameAndId(BackendCategoryAttrDto backendCategoryAttrDto);

	/**
	 * Description 查询商品后台类目属性最大的sortNo
	 * 
	 * Author Hedda
	 * 
	 * @return
	 */
	BackendCategoryAttrDto selectMaxSortNoByBackendCategoryAttr(BackendCategoryAttrEntity backendCategoryAttr);

	/**
	 * Description 对商品后台类目进行升降序操作
	 * 
	 * Author Hedda
	 * 
	 * @param backendCategoryAttrDto
	 *            商品后台类目属性dto
	 * @return
	 */
	List<BackendCategoryAttrDto> selectBackendCategoryAttrBySortNo(BackendCategoryAttrDto backendCategoryAttrDto);

	/**
	 * Description app端根据后台类目id查询商品属性
	 * 
	 * Author Hedda
	 * 
	 * @param backId
	 *            后台类目id
	 * @return
	 */
	List<BackendCategoryAttrDto> selectListBackendCategoryAttrDto(Long backId);

}
