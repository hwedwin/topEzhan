package com.topaiebiz.goods.category.frontend.controller;

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
import com.topaiebiz.goods.category.backend.dto.BackendCategoryDto;
import com.topaiebiz.goods.category.frontend.dto.FrontBackCategoryDto;
import com.topaiebiz.goods.category.frontend.dto.FrontendCategoryDto;
import com.topaiebiz.goods.category.frontend.entity.FrontBackCategoryEntity;
import com.topaiebiz.goods.category.frontend.entity.FrontendCategoryEntity;
import com.topaiebiz.goods.category.frontend.service.FrontendCategoryService;

/**
 * Description 商品前台类目控制层
 * 
 * Author Hedda
 * 
 * Date 2017年8月25日 下午3:13:52
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@RestController
@RequestMapping("/frontend/category")
public class FrontendCategoryController {

	@Autowired
	private FrontendCategoryService frontendCategoryService;

	/**
	 * Description 平台商品前台类目一,二，三级类目列表
	 * 
	 * Author Hedda
	 * 
	 * @param frontendCategoryDto
	 *            商品前台类目dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getFrontendCategoryList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getFrontendCategoryList(FrontendCategoryDto frontendCategoryDto) throws GlobalException {
		List<FrontendCategoryDto> listFrontendCategoryEntity = frontendCategoryService
				.getFrontendCategoryList(frontendCategoryDto);
		return new ResponseInfo(listFrontendCategoryEntity);
	}
	
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
	@RequestMapping(path = "/getMerchantFrontendCategoryList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantFrontendCategoryList(FrontendCategoryDto frontendCategoryDto) throws GlobalException {
		List<FrontendCategoryDto> listFrontendCategoryEntity = frontendCategoryService
				.getMerchantFrontendCategoryList(frontendCategoryDto);
		return new ResponseInfo(listFrontendCategoryEntity);
	}

	/**
	 * Description 平台端商品前台类目添加
	 * 
	 * Author Hedda
	 * 
	 * @param frontendCategoryDto
	 *            商品前台类目
	 * @param result
	 *            错误结果
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/addFrontendCategory", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addFrontendCategory(@Valid FrontendCategoryDto frontendCategoryDto, BindingResult result)
			throws GlobalException {
		/** 对商品前台类目属性字段进行校验 */
		if (result.hasErrors()) {
			/** 初始化非法参数的提示信息。 */
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/** 获取非法参数异常信息对象，并抛出异常。 */
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		FrontendCategoryEntity frontendCategory = new FrontendCategoryEntity();
		BeanUtils.copyProperties(frontendCategoryDto, frontendCategory);
		return new ResponseInfo(frontendCategoryService.saveFrontendCategory(frontendCategory));
	}
	
	/**
	 * Description 商家端商品前台类目添加
	 * 
	 * Author Hedda
	 * 
	 * @param frontendCategoryDto
	 *            商品前台类目
	 * @param result
	 *            错误结果
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/addMerchantFrontendCategory", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addMerchantFrontendCategory(@Valid FrontendCategoryDto frontendCategoryDto, BindingResult result)
			throws GlobalException {
		/** 对商品前台类目属性字段进行校验 */
		if (result.hasErrors()) {
			/** 初始化非法参数的提示信息。 */
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/** 获取非法参数异常信息对象，并抛出异常。 */
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		FrontendCategoryEntity frontendCategory = new FrontendCategoryEntity();
		BeanUtils.copyProperties(frontendCategoryDto, frontendCategory);
		return new ResponseInfo(frontendCategoryService.saveMerchantFrontendCategory(frontendCategory));
	}

	/**
	 * Description 商品前台类目修改
	 * 
	 * Author Hedda
	 * 
	 * @param frontendCategoryDto
	 *            商品前台类目
	 * @param result
	 *            错误结果
	 * @return
	 */
	@RequestMapping(path = "/editFrontendCategory", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editFrontendCategory(@Valid FrontendCategoryDto frontendCategoryDto, BindingResult result)
			throws GlobalException {
		/** 对商品前台类目属性字段进行校验 */
		if (result.hasErrors()) {
			/** 初始化非法参数的提示信息。 */
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/** 获取非法参数异常信息对象，并抛出异常。 */
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		return new ResponseInfo(frontendCategoryService.modifyFrontendCategory(frontendCategoryDto));
	}

	/**
	 * Description 商品前台类目逻辑删除
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品前台类目id
	 * @return b
	 */
	@RequestMapping(path = "/cancelFrontendCategory", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo removeFrontendCategory(Long id) throws GlobalException {
		frontendCategoryService.removeFrontendCategory(id);
		return new ResponseInfo("删除成功！");
	}

	/**
	 * Description 绑定前后台类目
	 * 
	 * Author Hedda
	 * 
	 * @param frontBackCategory
	 *            前后台类目对照表
	 * @return
	 */
	@RequestMapping(path = "/addFrontBackCategory", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addFrontBackCategory(@Valid FrontBackCategoryDto frontBackCategoryDto,
			BindingResult result) throws GlobalException {
		/** 对商品前台类目属性字段进行校验 */
		if (result.hasErrors()) {
			/** 初始化非法参数的提示信息。 */
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/** 获取非法参数异常信息对象，并抛出异常。 */
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		FrontBackCategoryEntity frontBackCategory = new FrontBackCategoryEntity();
		BeanUtils.copyProperties(frontBackCategoryDto, frontBackCategory);
		return new ResponseInfo(frontendCategoryService.saveFrontBackCategory(frontBackCategory));
	}

	/**
	 * Description 商品前后台类目绑定类目逻辑删除
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品前后台关联表id
	 * @return b
	 */
	@RequestMapping(path = "/cancelFrontBackCategory", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelFrontBackCategory(@Valid FrontBackCategoryDto frontBackCategoryDto,BindingResult result) throws GlobalException {
		/** 对商品前台类目属性字段进行校验 */
		if (result.hasErrors()) {
			/** 初始化非法参数的提示信息。 */
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/** 获取非法参数异常信息对象，并抛出异常。 */
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		frontendCategoryService.removeFrontBackCategory(frontBackCategoryDto);
		return new ResponseInfo("删除成功！");
	}

	/**
	 * Description 根据id查询商品前台类目
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品前台类目id
	 * @return
	 */
	@RequestMapping(value = "/getFrontendCategoryById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getFrontendCategoryById(Long id) throws GlobalException {
		return new ResponseInfo(frontendCategoryService.getFrontendCategoryById(id));
	}

	/**
	 * Description 查询后台类目列表
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品后台类目id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/getBackendCategoryList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getBackendCategoryList(Long frontId) throws GlobalException {
		List<BackendCategoryDto> backendCategoryList = frontendCategoryService.getBackendCategoryList(frontId);
		return new ResponseInfo(backendCategoryList);
	}
	
	/**
	 * Description 装修时查询前台第三级类目列表  
	 * 
	 * Author Hedda  
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/getThreeFrontendCategoryList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getThreeFrontendCategoryList(Page<FrontBackCategoryDto> page) throws GlobalException {
		Page<FrontBackCategoryDto> frontBackCategoryDtos = frontendCategoryService.getThreeFrontendCategoryList(page);
		return new ResponseInfo(frontBackCategoryDtos);
	}
	
	
	/**
	 * Description 给三级类目添加图片
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品后台类目id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/addFrontendCategoryImage", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addBackendCategoryList(Long id,String image) throws GlobalException {
		return new ResponseInfo(frontendCategoryService.addFrontendCategoryById(id,image));
	}
	
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
	@RequestMapping(value = "/cancelFrontendCategoryImage", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelFrontendCategoryImage(Long id) throws GlobalException {
		return new ResponseInfo(frontendCategoryService.cancelFrontendCategoryImage(id));
	}
	
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
	@RequestMapping(value = "/getFrontendCategoryByName", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getFrontendCategoryByName(FrontendCategoryDto frontendCategoryDto) throws GlobalException {
		FrontendCategoryDto frontendCategory = frontendCategoryService.getFrontendCategoryByName(frontendCategoryDto);
		return new ResponseInfo(frontendCategory);

	}

}
