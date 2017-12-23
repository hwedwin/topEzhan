package com.topaiebiz.goods.category.frontend.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.goods.category.backend.dto.BackendCategoryDto;
import com.topaiebiz.goods.category.frontend.dto.FrontBackCategoryDto;
import com.topaiebiz.goods.category.frontend.dto.FrontendCategoryDto;
import com.topaiebiz.goods.category.frontend.entity.FrontBackCategoryEntity;
import com.topaiebiz.goods.category.frontend.entity.FrontendCategoryEntity;

/**
 * Description 商品前台类目接口
 * 
 * Author Hedda
 * 
 * Date 2017年8月25日 下午3:14:26
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public interface FrontendCategoryService {

	/**
	 * Description 商品前台类目一,二，三级类目列表
	 * 
	 * Author Hedda
	 * 
	 * @param frontendCategoryDto
	 *            商品前台类目dto
	 * @return
	 * @throws GlobalException
	 */
	List<FrontendCategoryDto> getFrontendCategoryList(FrontendCategoryDto frontendCategoryDto) throws GlobalException;

	/**
	 * Description 商品前台类目添加
	 * 
	 * Author Hedda
	 * 
	 * @param frontendCategory
	 *            商品前台类目
	 * @return
	 */
	Integer saveFrontendCategory(FrontendCategoryEntity frontendCategory) throws GlobalException;

	/**
	 * Description 商品前台类目修改
	 * 
	 * Author Hedda
	 * 
	 * @param frontendCategoryDto
	 *            商品前台类目dto
	 * @return
	 * @throws GlobalException
	 */
	Integer modifyFrontendCategory(FrontendCategoryDto frontendCategoryDto) throws GlobalException;

	/**
	 * Description 商品前台类目逻辑删除
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品前台类目id
	 */
	void removeFrontendCategory(Long id) throws GlobalException;

	/**
	 * Description 绑定前后台类目
	 * 
	 * Author Hedda
	 * 
	 * @param frontBackCategory
	 *            绑定桥后台类目信息
	 * @return
	 * @throws GlobalException 
	 */
	Integer saveFrontBackCategory(FrontBackCategoryEntity frontBackCategory) throws GlobalException;

	/**
	 * Description 删除绑定前后台信息
	 * 
	 * Author Hedda
	 * 
	 * @param frontBackCategoryDto
	 *            商品前后台绑定id
	 */
	void removeFrontBackCategory(FrontBackCategoryDto frontBackCategoryDto) throws GlobalException;

	/**
	 * Description 根据id查询商品前台类目
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品前台类目id
	 * @return
	 * @throws GlobalException
	 */
	FrontendCategoryDto getFrontendCategoryById(Long id) throws GlobalException;

	/**
	 * Description 根据id查询前台类目添加图片
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品前台类目id
	 * @param image
	 *            商品前台类目图片
	 * @return
	 * @throws GlobalException
	 */
	Integer addFrontendCategoryById(Long id, String image) throws GlobalException;
	
	/**
	 * Description 根据id查询商品后台类目  
	 * 
	 * Author Hedda  
	 * 
	 * @param id
	 * @return
	 * @throws GlobalException
	 */
	List<BackendCategoryDto> getBackendCategoryList(Long id) throws GlobalException;

	/**
	 * Description 查看商品前台类目名称验证唯一
	 * 
	 * Author Hedda
	 * 
	 * @param frontendCategoryDto
	 *            商品前台类目dto
	 * @return
	 * @throws GlobalException
	 */
	FrontendCategoryDto getFrontendCategoryByName(FrontendCategoryDto frontendCategoryDto) throws GlobalException;

	/**
	 * Description 给三级类目删除图片
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品后台类目id
	 * @return
	 * @throws GlobalException
	 */
	Integer cancelFrontendCategoryImage(Long id) throws GlobalException;

	/**
	 * Description 商家商品前台类目一,二，三级类目列表
	 * 
	 * Author Hedda
	 * 
	 * @param frontendCategoryDto
	 *            商品前台类目dto
	 * @return
	 * @throws GlobalException 
	 */
	List<FrontendCategoryDto> getMerchantFrontendCategoryList(FrontendCategoryDto frontendCategoryDto) throws GlobalException;

	/**
	 * Description 商家商品前台类目添加
	 * 
	 * Author Hedda
	 * 
	 * @param frontendCategoryDto
	 *            商品前台类目
	 * @return
	 * @throws GlobalException
	 */
	Integer saveMerchantFrontendCategory(FrontendCategoryEntity frontendCategory) throws GlobalException;

	/**
	 * Description app端商家前台类目一,二，三级类目列表
	 * 
	 * Author Hedda
	 * 
	 * @param frontendCategoryDto
	 *            商品前台类目dto
	 * @return
	 * @throws GlobalException
	 */
	List<FrontendCategoryDto> getMerchantAppFrontendCategoryList(FrontendCategoryDto frontendCategoryDto) throws GlobalException;

	/**
	 * Description app端商家前台类目第三级类目列表  
	 * 
	 * Author Hedda  
	 * 
	 * @param storeId
	 * @return
	 */
	List<FrontendCategoryDto> getAppThreeFrontendCategoryList(Long storeId);

	/**
	 * Description 装修时查询前台第三级类目列表    
	 * 
	 * Author Hedda  
	 * 
	 * @return
	 */
	Page<FrontBackCategoryDto> getThreeFrontendCategoryList(Page<FrontBackCategoryDto> page);

}
