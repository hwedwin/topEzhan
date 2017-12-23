package com.topaiebiz.goods.category.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.goods.category.backend.dto.BackendCategoryDto;
import com.topaiebiz.goods.category.backend.dto.BackendMerchantCategoryDto;
import com.topaiebiz.goods.category.backend.dto.MerchantStoreDto;
import com.topaiebiz.goods.category.backend.entity.BackendCategoryEntity;
import com.topaiebiz.goods.category.backend.entity.BackendMerchantCategoryEntity;

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
public interface BackendCategoryDao extends BaseDao<BackendCategoryEntity> {

	/**
	 * Description 商品后台一级类目
	 * 
	 * Author Hedda
	 * 
	 * @param backendCategoryDto
	 *            商品后台类目dto
	 * @return
	 */
	List<BackendCategoryDto> selectListOneLevelBackendCategory(BackendCategoryDto backendCategoryDto);

	/**
	 * Description 商品后台类目二，三级类目列表
	 * 
	 * Author Hedda
	 * 
	 * @param backendCategoryDto
	 *            商品后台类目dto
	 * @return
	 */
	List<BackendCategoryDto> selectListTwoOrThreeLevelBackendCategory(BackendCategoryDto backendCategoryDto);

	/**
	 * Description 逻辑删除商品类目
	 * 
	 * Author Hedda
	 * 
	 * @param BackendCategoryId
	 *            商品类目id
	 * @return
	 */
	Integer deleteBackendCategory(Long backendCategoryId);

	/**
	 * Description 通过上级类目id查询出下级所有类目
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品类目id
	 * @return
	 */
	List<BackendCategoryEntity> selectAllBackendCategoryById(Long id);

	/**
	 * Description 根据id查询商品后台类目
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品后台类目id
	 * @return
	 */
	BackendCategoryDto selectBackendCategoryById(Long id);

	/**
	 * Description 根据商品类目名称查询类目
	 * 
	 * Author Hedda
	 * 
	 * @param name
	 *            商品类目名称
	 * @return
	 */
	BackendCategoryEntity selectBackendCategoryByName(BackendCategoryEntity backendCategory);

	/**
	 * Description 根据id查询商品后台类目信息
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品后台类目id
	 * @return
	 */
	BackendCategoryEntity selectBackendCategoryEntityById(Long id);

	/**
	 * Description 根据id查询后台类目
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 * @return
	 */
	BackendCategoryDto selectBackendCategoryByBackId(Long id);

	/**
	 * Description 查询最近使用类目
	 * 
	 * Author Hedda
	 * 
	 * @return
	 */
	List<BackendCategoryDto> selectRecentlyCategoryList();

	/**
	 * Description 根据商品后台类目名称和id查询商品后台类目
	 * 
	 * Author Hedda
	 * 
	 * @param backendCategoryDto
	 *            商品后台类目dto
	 * @return
	 */
	BackendCategoryDto selectBackendCategoryByNameAndId(BackendCategoryDto backendCategoryDto);

	/**
	 * Description 根据商家id查询商品后台三级类目
	 * 
	 * Author Hedda
	 * 
	 * @param belongStore
	 *            商家id
	 * @return
	 */
	List<BackendCategoryDto> selectThreeBackendCategoryDtoByBelongStore(Long belongStore);

	/**
	 * Description 平台 查询最近使用类目
	 * 
	 * Author Hedda
	 * 
	 * @return
	 * @throws GlobalException
	 */
	List<BackendCategoryDto> selectItemRecentlyCategoryList();

	/**
	 * Description 商家 查询最近使用类目
	 * 
	 * Author Hedda
	 * 
	 * @return
	 * @throws GlobalException
	 */
	List<BackendCategoryDto> selectMerchantItemRecentlyCategoryList(Long belongId);

	/**
	 * Description 根据商家id查询商品后台二级类目
	 * 
	 * Author Hedda
	 * 
	 * @param belongStore
	 *            商家id
	 * @param parentId
	 *            父级id
	 * @return
	 */
	BackendCategoryDto selectTwoBackendCategoryDtoByParentId(Long parentId);

	/**
	 * Description 根据商家id查询商品后台一级类目
	 * 
	 * Author Hedda
	 * 
	 * @param belongStore
	 *            商家id
	 * @param parentId
	 *            父级id
	 * @return
	 */
	BackendCategoryDto selectOneBackendCategoryDtoByParentId(Long parentId);

	/**
	 * Description 查看商品后台第三级类目
	 * 
	 * Author Hedda
	 * 
	 * @return
	 */
	List<BackendCategoryDto> selectThreeBackendCategoryList();

	/**
	 * Description 根据商品后台类目名称查询商家后台类目
	 * 
	 * Author Hedda
	 * 
	 * @param name
	 * @return
	 */
	List<BackendCategoryEntity> selectBackendCategoryByNameAndBelongStore(String name);

	/**
	 * Description 根据后台类目id查询本类目是否为平台类目
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 * @return
	 */
	BackendCategoryDto selectBackendCategoryByIdAndBelongStore(Long id);

	/**
	 * Description 查看商品后台第三级类目
	 * 
	 * Author Hedda
	 * 
	 * @return
	 */
	List<BackendCategoryDto> selectMerchantThreeBackendCategoryList(Long belongId);

	/**
	 * Description 根据电话查询对应商家店铺
	 * 
	 * Author Hedda
	 * 
	 * @param telephone
	 *            电话
	 * @return
	 */
	MerchantStoreDto getMemberByuserTelephone(String telephone);

	/**
	 * Description 通过商家id和第三级类目名称查询商家第三级类目
	 * 
	 * Author Hedda
	 * 
	 * @param backendCategoryDto2
	 * @return
	 */
	BackendCategoryDto selectThreeBackendCategoryDto(BackendCategoryDto backendCategoryDto2);

	/**
	 * Description 根据商家 id查询对应类目
	 * 
	 * Author Hedda
	 * 
	 * @param storeId
	 *            商家id
	 * @return
	 */
	List<BackendCategoryEntity> selectBackendCategoryByStoreId(Long storeId);

	/**
	 * Description： 根据商家ID，父类目查询类目
	 * 
	 * Author Aaron.Xue
	 * 
	 * @param merchartId
	 * @param parentId
	 * @return
	 */
	List<BackendMerchantCategoryDto> selectMerchantCategory(BackendMerchantCategoryDto backendMerchantCategoryDto);

	/**
	 * Description： 查看商家三级类目
	 * 
	 * Author Aaron.Xue
	 * 
	 * @param merchantId
	 * @return
	 */
	List<BackendMerchantCategoryDto> selectMerchantThreeCategory(Long merchantId);

	List<BackendCategoryEntity> getAll();

	/**
	 * Description 根据商家id和类目id查询对应类目
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            类目id
	 * @param merchantId
	 *            商家id
	 * @return
	 */
	BackendMerchantCategoryEntity selectBackendMerchantCategoryById(@Param("id") Long id,
			@Param("merchantId") Long merchantId);

	/**
	 * Description 通过商家id查询商家类目
	 * 
	 * Author Hedda
	 * 
	 * @param merchantId
	 * @return
	 */
	List<BackendMerchantCategoryDto> selectThreeBackendCategoryDtoByMerchantId(Long merchantId);

	/**
	 * Description 删除商家类目
	 * 
	 * Author Hedda
	 * 
	 * @param category
	 *            类目id
	 * @param merchantId
	 *            商家id
	 * @return
	 */
	Integer deleteBackendMerchantCategory(@Param("category")Long category,@Param("merchantId") Long merchantId);
	
}
