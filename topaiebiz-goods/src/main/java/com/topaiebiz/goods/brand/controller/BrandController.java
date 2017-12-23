package com.topaiebiz.goods.brand.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.nebulapaas.web.util.IllegalParamValidationUtils;
import com.topaiebiz.goods.brand.dto.BrandDto;
import com.topaiebiz.goods.brand.dto.SuitableAgeDto;
import com.topaiebiz.goods.brand.entity.BrandEntity;
import com.topaiebiz.goods.brand.service.BrandService;

/**
 * Description 商品品牌控制层
 * 
 * Author Hedda
 * 
 * Date 2017年8月23日 下午4:14:40
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@RestController
@RequestMapping("/goods/brand")
public class BrandController {

	@Autowired
	private BrandService brandService;

	/**
	 * Description 商品品牌添加
	 * 
	 * Author Hedda
	 * 
	 * @param brandDto
	 *            商品品牌dto
	 * @param result
	 *            错误结果
	 * @return ResponseInfo
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/addBrand", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addBrand(@Valid BrandDto brandDto, BindingResult result) throws GlobalException {
		/** 对商品品牌字段进行校验 */
		if (result.hasErrors()) {
			/**初始化非法参数的提示信息。*/
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/**获取非法参数异常信息对象，并抛出异常。*/
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		/** 对商品品牌进行添加 */
		BrandEntity brand = new BrandEntity();
		BeanUtils.copyProperties(brandDto, brand);
		return new ResponseInfo(brandService.saveBrand(brand));
	}

	/**
	 * Description 商品品牌修改
	 * 
	 * Author Hedda
	 * 
	 * @param brandDto
	 *            商品品牌dto
	 * @param result
	 *            错误结果
	 * @return ResponseInfo
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/editBrand",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editbrand(@Valid BrandDto brandDto, BindingResult result)
			throws GlobalException {
		/** 对商品品牌字段进行校验 */
		if (result.hasErrors()) {
			/**初始化非法参数的提示信息。*/
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/** 获取非法参数异常信息对象，并抛出异常。*/
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		return new ResponseInfo( brandService.modifyBrand(brandDto));
	}

	/**
	 * Description 批量逻辑删除商品品牌
	 * 
	 * Author Hedda
	 * 
	 * @param brandDto
	 *            商品品牌dto
	 * @return ResponseInfo
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/cancelBrands",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelBrands(Long[] id) throws GlobalException {
		return new ResponseInfo(brandService.removeBrands(id));
	}

	/**
	 * Description 根据id查询品牌信息
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品品牌ID
	 * @return ResponseInfo
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/findBrandById",method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo findbrandById(Long id) throws GlobalException {
		BrandDto brand = brandService.findBrandById(id);
		return new ResponseInfo(brand);
	}

	/**
	 * Description 商品品牌分页检索
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页参数
	 * @param brandDto
	 *            商品品牌dto
	 * @return ResponseInfo
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getBrandList",method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getBrandList(Page<BrandDto> page, BrandDto brandDto)
			throws GlobalException {
		 Page<BrandDto> list = brandService.getBrandList(page, brandDto);
		return new ResponseInfo(list);
	}

	/**
	 * Description 查看商品品牌列表
	 * 
	 * Author Hedda
	 * 
	 * @return ResponseInfo
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/listBrand",method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo listBrand() throws GlobalException {
		List<BrandDto> listbrand = brandService.getBrandList();
		return new ResponseInfo(listbrand);

	}
	
	
	/**
	 * Description 查看商品品牌名称验证唯一
	 * 
	 * Author Hedda
	 * 
	 * @return ResponseInfo
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/getBrandByName",method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getBrandByName(BrandDto brandDto) throws GlobalException {
		BrandDto brand = brandService.getBrandByName(brandDto);
		return new ResponseInfo(brand);
	}
	
	/**
	 * Description 查看商品品牌编码验证唯一 
	 * 
	 * Author Hedda  
	 * 
	 * @param brandDto 商品品牌dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/getBrandByCode",method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getBrandByCode(BrandDto brandDto) throws GlobalException {
		BrandDto brand = brandService.getBrandByCode(brandDto);
		return new ResponseInfo(brand);

	}
	
	/**
	 * Description 查询年龄段列表  
	 * 
	 * Author Hedda  
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/getListSuitableAge",method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getListSuitableAge() throws GlobalException {
		List<SuitableAgeDto> listSuitableAge = brandService.getListSuitableAge();
		return new ResponseInfo(listSuitableAge);

	}
}
