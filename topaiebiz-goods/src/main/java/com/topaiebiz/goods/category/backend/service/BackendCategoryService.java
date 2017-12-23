package com.topaiebiz.goods.category.backend.service;

import java.util.List;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.goods.category.backend.dto.BackendCategoryAttrDto;
import com.topaiebiz.goods.category.backend.dto.BackendCategoryDto;
import com.topaiebiz.goods.category.backend.dto.BackendCategorysDto;
import com.topaiebiz.goods.category.backend.dto.BackendMerchantCategoryDto;
import com.topaiebiz.goods.category.backend.entity.BackendCategoryAttrEntity;
import com.topaiebiz.goods.category.backend.entity.BackendCategoryEntity;

/**
 * Description 商品后台类目接口
 * 
 * Author Hedda
 * 
 * Date 2017年8月24日 下午4:46:51
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public interface BackendCategoryService {

	/**
	 * Description 商品后台类目逻辑删除
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品后台类目id
	 * @return
	 */
	Integer removeBackendCategory(Long id) throws GlobalException;

	/**
	 * Description 商品后台类目修改
	 * 
	 * Author Hedda
	 * 
	 * @param BackendCategory
	 *            商品后台类目
	 * @return
	 */
	Integer modifyBackendCategory(BackendCategoryDto backendCategoryDto) throws GlobalException;

	/**
	 * Description 平台 商品后台类目一，二，三级类目列表
	 * 
	 * Author Hedda
	 * 
	 * @param backendCategoryDto
	 *            商品后台类目dto
	 * @return
	 * @throws GlobalException
	 */
	List<BackendCategoryDto> getListLevelBackendCategory(BackendCategoryDto backendCategoryDto) throws GlobalException;

	/**
	 * Description 商品三级类目中所对应的规格属性
	 * 
	 * Author Hedda
	 * 
	 * @param belongCategory
	 *            商品后台类目id
	 * @return
	 * @throws GlobalException
	 */
	List<BackendCategoryAttrEntity> getListBackendCategoryAttr(Long belongCategory) throws GlobalException;

	/**
	 * Description 商品后台类目中规格属性修改
	 * 
	 * Author Hedda
	 * 
	 * @param backendCategoryAttr
	 *            商品后台类目规格属性
	 * @return
	 */
	Integer modifyBackendCategoryAttr(BackendCategoryAttrDto backendCategoryAttrDto) throws GlobalException;

	/**
	 * Description 商品后台类目中规格属性逻辑删除
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品后台类目属性id
	 * @return
	 */
	Integer removeBackendCategoryAttr(Long id) throws GlobalException;

	/**
	 * Description 商品后台类目中规格属性添加
	 * 
	 * Author Hedda
	 * 
	 * @param belongCategory
	 * @return
	 */
	Integer saveBackendCategoryAttr(BackendCategoryAttrEntity backendCategoryAttr) throws GlobalException;

	/**
	 * Description 商品后台类目添加
	 * 
	 * Author Hedda
	 * 
	 * @param backendCategory
	 *            商品后台类目
	 * @return
	 * @throws GlobalException
	 */
	Integer saveBackendCategory(BackendCategoryEntity backendCategory) throws GlobalException;

	/**
	 * Description 商品类目根据id进行查询
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品
	 * @return
	 * @throws GlobalException
	 */
	BackendCategoryDto findBackendCategoryById(Long id) throws GlobalException;

	/**
	 * Description 商品类目规格属性根据id进行查询
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品后台类目属性id
	 * @return
	 * @throws GlobalException
	 */
	BackendCategoryAttrDto findBackendCategoryAttrById(Long id) throws GlobalException;

	/**
	 * Description 查看商品后台类目属性名称验证唯一
	 * 
	 * Author Hedda
	 * 
	 * @param backendCategoryAttrDto
	 *            商品后台类目属性dto
	 * @return
	 * @throws GlobalException
	 */
	BackendCategoryAttrDto getBackendCategoryAttrByName(BackendCategoryAttrDto backendCategoryAttrDto)
			throws GlobalException;

	/**
	 * Description 查看商品后台类目名称验证唯一
	 * 
	 * Author Hedda
	 * 
	 * @param backendCategoryDto
	 *            商品后台类目dto
	 * @return
	 * @throws GlobalException
	 */
	BackendCategoryDto getBackendCategoryByName(BackendCategoryDto backendCategoryDto) throws GlobalException;

	/**
	 * Description 对商品后台类目属性进行升降序操作
	 * 
	 * Author Hedda
	 * 
	 * @param backendCategoryAttrDto
	 * @return
	 */
	Integer modifyBackendCategoryAttrBySortNo(List<BackendCategoryAttrDto> backendCategoryAttrDto);

	/**
	 * Description 根据商家id查询商品后台类目
	 * 
	 * Author Hedda
	 * 
	 * @param belongStore
	 *            商家id
	 * @return
	 */
	List<BackendCategorysDto> getBackendCategoryDtoByBelongStore(Long merchantId);

	/**
	 * Description 根据商家id添加商品后台类目
	 * 
	 * Author Hedda
	 * 
	 * @param belongStore
	 */
	Integer addBackendCategoryDtoByBelongStore(Long[] ids);

	/**
	 * Description 查看平台商品后台第三级类目
	 * 
	 * Author Hedda
	 * 
	 * @return
	 */
	List<BackendCategoryDto> getThreeBackendCategoryList();

	/**
	 * Description 商家 商品后台类目添加
	 * 
	 * Author Hedda
	 * 
	 * @param backendCategoryDto
	 *            商品后台类目dto
	 * @return
	 * @throws GlobalException
	 */
	Integer saveMerchantBackendCategory(BackendCategoryEntity backendCategory) throws GlobalException;

	/**
	 * Description 商家 查看商品后台第三级类目
	 * 
	 * Author Hedda
	 * 
	 * @return
	 * @throws GlobalException
	 */
	List<BackendCategoryDto> getMerchantThreeBackendCategoryList();

	/**
	 * Description app端根据商品前台第三级类目中所对应的规格属性
	 * 
	 * Author Hedda
	 * 
	 * @param frontendId
	 *            商品前台第三级类目id
	 * @return
	 * @throws GlobalException
	 */
	List<BackendCategoryAttrDto> getAppListBackendCategoryAttr(Long frontendId) throws GlobalException;

	/**
	 * Description 根据第三级类目id查询第一二级类目
	 * 
	 * Author Hedda
	 * 
	 * @param backendCategoryId
	 *            第三级类目id
	 * @return
	 * @throws GlobalException
	 */
	List<BackendCategoryDto> getOneAndTwoBackendCategoryById(Long backendCategoryId) throws GlobalException;

	/**
	 * Description 根据商家 id删除对应的商家类目
	 * 
	 * Author Hedda
	 * 
	 * @param storeId
	 *            商家id
	 * @return
	 */
	Integer removeBackendCategoryByStoreId(Long storeId);

	/**
	 * Description： 查询商家类目 
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param backendMerchantCategoryDto
	 * @return
	 */
	List<BackendMerchantCategoryDto> getMerchantCategory(BackendMerchantCategoryDto backendMerchantCategoryDto);

	/**
	 * Description： 查看商家三级类目
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @return
	 */
	List<BackendMerchantCategoryDto> getMerchantThreeCategory();

	void test();

	/**
	 * Description 商家端端商品后台类目中规格属性添加   
	 * 
	 * Author Hedda  
	 * 
	 * @param backendCategoryAttr
	 * @return
	 */
	Integer saveMerchantBackendCategoryAttr(BackendCategoryAttrEntity backendCategoryAttr);

	/**
	 * Description 删除商家类目
	 * 
	 * Author Hedda  
	 * 
	 * @param categoryId 类目id
	 * @return
	 */
	Integer removeBackendMerchantCategoryByStoreId(Long[] categoryId);

}
