package com.topaiebiz.goods.brand.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.goods.brand.dto.BrandDto;
import com.topaiebiz.goods.brand.dto.SuitableAgeDto;
import com.topaiebiz.goods.brand.entity.BrandEntity;

/**
 * Description 商品品牌接口
 * 
 * Author Hedda
 * 
 * Date 2017年8月23日 下午4:14:59
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public interface BrandService {

	/**
	 * Description 商品品牌添加
	 * 
	 * Author Hedda
	 * 
	 * @param brand
	 *            商品品牌对象
	 * @return
	 * @throws GlobalException
	 */
	Integer saveBrand(BrandEntity brand) throws GlobalException;

	/**
	 * Description 商品品牌修改
	 * 
	 * Author Hedda
	 * 
	 * @param brandDto
	 *            商品品牌对象
	 * @return
	 * @throws GlobalException
	 */
	Integer modifyBrand(BrandDto brandDto) throws GlobalException;

	/**
	 * Description 根据id查询品牌
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品品牌ID
	 * @return
	 * @throws GlobalException
	 */
	BrandDto findBrandById(Long id) throws GlobalException;

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
	Page<BrandDto> getBrandList(Page<BrandDto> page, BrandDto brandDto);

	/**
	 * Description 批量逻辑删除商品品牌
	 * 
	 * Author Hedda
	 * 
	 * @param brandDto
	 *            商品品牌dto
	 * @return
	 * @throws GlobalException
	 */
	Integer removeBrands(Long[] id) throws GlobalException;

	/**
	 * Description 查看商品品牌列表
	 * 
	 * Author Hedda
	 * 
	 * @return
	 */
	List<BrandDto> getBrandList();

	/**
	 * Description 查看商品品牌名称验证唯一
	 * 
	 * Author Hedda
	 * 
	 * @param name
	 *            商品品牌名称
	 * @return
	 */
	BrandDto getBrandByName(BrandDto brandDto) throws GlobalException;

	/**
	 * Description 查看商品品牌编码验证唯一
	 * 
	 * Author Hedda
	 * 
	 * @param brandCode
	 *            商品品牌编码
	 * @return
	 */
	BrandDto getBrandByCode(BrandDto brandDto)throws GlobalException;

	/**
	 * Description  查询年龄段列表 
	 * 
	 * Author Hedda  
	 * 
	 * @return
	 */
	List<SuitableAgeDto> getListSuitableAge();

	/**
	 * Description app端商品品牌列表
	 * 
	 * Author Hedda
	 * 
	 * @return ResponseInfo
	 */
	List<BrandDto> getAppBrandList();

	/**
	 * Description  app端年龄段列表  
	 * 
	 * Author Hedda  
	 * 
	 * @return
	 */
	List<SuitableAgeDto> getAppListSuitableAge();

}
