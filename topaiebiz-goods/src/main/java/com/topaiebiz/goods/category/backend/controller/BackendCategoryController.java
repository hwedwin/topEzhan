package com.topaiebiz.goods.category.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.nebulapaas.web.util.IllegalParamValidationUtils;
import com.topaiebiz.goods.category.backend.dto.BackendCategoryAttrDto;
import com.topaiebiz.goods.category.backend.dto.BackendCategoryDto;
import com.topaiebiz.goods.category.backend.dto.BackendMerchantCategoryDto;
import com.topaiebiz.goods.category.backend.entity.BackendCategoryAttrEntity;
import com.topaiebiz.goods.category.backend.entity.BackendCategoryEntity;
import com.topaiebiz.goods.category.backend.service.BackendCategoryService;

/**
 * Description 商品后台类目控制层
 * 
 * Author Hedda
 * 
 * Date 2017年8月24日 下午4:46:22
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@RestController
@RequestMapping("/backend/category")
public class BackendCategoryController {

	@Autowired
	private BackendCategoryService backendCategoryService;

	/**
	 * Description 平台端商品后台类目添加
	 * 
	 * Author Hedda
	 * 
	 * @param backendCategoryDto
	 *            商品后台类目dto
	 * @param result
	 *            错误结果
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/addBackendCategory", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addBackendCategory(@Valid BackendCategoryDto backendCategoryDto, BindingResult result)
			throws GlobalException {
		/** 对商品后台类目字段进行校验 */
		if (result.hasErrors()) {
			/** 初始化非法参数的提示信息。 */
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/** 获取非法参数异常信息对象，并抛出异常。 */
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		/** 对商品后台类目进行添加 */
		BackendCategoryEntity backendCategory = new BackendCategoryEntity();
		BeanUtils.copyProperties(backendCategoryDto, backendCategory);
		return new ResponseInfo(backendCategoryService.saveBackendCategory(backendCategory));
	}

	/**
	 * Description 商家端商品后台类目添加   有问题 暂时不能用
	 * 
	 * Author Hedda
	 * 
	 * @param backendCategoryDto
	 *            商品后台类目dto
	 * @param result
	 *            错误结果
	 * @return
	 * @throws GlobalException
	 */
//	@RequestMapping(path = "/addMerchantBackendCategory", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseInfo addMerchantBackendCategory(@Valid BackendCategoryDto backendCategoryDto, BindingResult result)
//			throws GlobalException {
//		/** 对商品后台类目字段进行校验 */
//		if (result.hasErrors()) {
//			/** 初始化非法参数的提示信息。 */
//			IllegalParamValidationUtils.initIllegalParamMsg(result);
//			/** 获取非法参数异常信息对象，并抛出异常。 */
//			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
//		}
//		/** 对商品后台类目进行添加 */
//		BackendCategoryEntity backendCategory = new BackendCategoryEntity();
//		BeanUtils.copyProperties(backendCategoryDto, backendCategory);
//		return new ResponseInfo(backendCategoryService.saveMerchantBackendCategory(backendCategory));
//	}

	/**
	 * Description 商品后台类目修改
	 * 
	 * Author Hedda
	 * 
	 * @param BackendCategoryDto
	 *            商品后台类目dto
	 * @param result
	 *            错误结果
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/editBackendCategory", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editBackendCategory(@Valid BackendCategoryDto backendCategoryDto, BindingResult result)
			throws GlobalException {
		/** 对商品后台类目字段进行校验 */
		if (result.hasErrors()) {
			/** 初始化非法参数的提示信息。 */
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/** 获取非法参数异常信息对象，并抛出异常。 */
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		return new ResponseInfo(backendCategoryService.modifyBackendCategory(backendCategoryDto));
	}

	/**
	 * Description 商品后台类目逻辑删除
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品后台类目id
	 * @return
	 */
	@RequestMapping(path = "/cancelBackendCategory",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelBackendCategory(Long id) throws GlobalException {
		return new ResponseInfo(backendCategoryService.removeBackendCategory(id));
	}

	/**
	 * Description 根据商家 id删除对应的商家类目
	 * 
	 * Author Hedda
	 * 
	 * @param storeId
	 *            商家id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/cancelBackendCategoryByMerchantId", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelBackendMerchantCategoryByStoreId(Long[] categoryId) throws GlobalException {
		return new ResponseInfo(backendCategoryService.removeBackendMerchantCategoryByStoreId(categoryId));
	}

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
	@RequestMapping(path = "/getListLevelBackendCategory" ,method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getListLevelBackendCategory(BackendCategoryDto backendCategoryDto) throws GlobalException {
		List<BackendCategoryDto> listLevelBackendCategory = backendCategoryService
				.getListLevelBackendCategory(backendCategoryDto);
		return new ResponseInfo(listLevelBackendCategory);
	}

	/**
	 * Description 商家 商品后台类目一，二，三级类目列表
	 * 
	 * Author Hedda
	 * 
	 * @param backendCategoryDto
	 *            商品后台类目dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getMerchantListLevelBackendCategory", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantListLevelBackendCategory(BackendMerchantCategoryDto backendMerchantCategoryDto)
			throws GlobalException {
		List<BackendMerchantCategoryDto> listLevelBackendCategory = backendCategoryService.getMerchantCategory(backendMerchantCategoryDto);
		return new ResponseInfo(listLevelBackendCategory);
	}

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
	@RequestMapping(path = "/getListBackendCategoryAttr", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getListBackendCategoryAttr(Long belongCategory) throws GlobalException {
		List<BackendCategoryAttrEntity> listBackendCategoryAttr = backendCategoryService
				.getListBackendCategoryAttr(belongCategory);
		return new ResponseInfo(listBackendCategoryAttr);
	}

	/**
	 * @Description 平台端商品后台类目属性修改
	 * 
	 * @Author Hedda
	 * 
	 * @param backendCategoryAttr
	 *            商品后台类目规格属性
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/editBackendCategoryAttr", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editBackendCategoryAttr(@Valid BackendCategoryAttrDto backendCategoryAttrDto,
			BindingResult result) throws GlobalException {
		/** 对商品后台类目属性字段进行校验 */
		if (result.hasErrors()) {
			/** 初始化非法参数的提示信息。 */
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/** 获取非法参数异常信息对象，并抛出异常。 */
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		/** 对商品后台类目进行修改 */
		return new ResponseInfo(backendCategoryService.modifyBackendCategoryAttr(backendCategoryAttrDto));
	}

	/**
	 * @Description 商品后台类目中规格属性逻辑删除
	 * 
	 * @Author Hedda
	 * 
	 * @param id
	 *            商品后台类目规格属性id
	 * @return
	 */
	@RequestMapping(path = "/cancelBackendCategoryAttr", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelBackendCategoryAttr(Long id) throws GlobalException {
		return new ResponseInfo(backendCategoryService.removeBackendCategoryAttr(id));
	}

	/**
	 * Description 平台端商品后台类目中规格属性添加
	 * 
	 * Author Hedda
	 * 
	 * @param BackendCategoryAttr
	 *            商品后台类目规格属性
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/addBackendCategoryAttr", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addBackendCategoryAttr(@Valid BackendCategoryAttrDto backendCategoryAttrDto,
			BindingResult result) throws GlobalException {
		/** 对商品后台类目字段进行校验 */
		if (result.hasErrors()) {
			/** 初始化非法参数的提示信息。 */
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/** 获取非法参数异常信息对象，并抛出异常。 */
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		BackendCategoryAttrEntity backendCategoryAttr = new BackendCategoryAttrEntity();
		BeanUtils.copyProperties(backendCategoryAttrDto, backendCategoryAttr);
		return new ResponseInfo(backendCategoryService.saveBackendCategoryAttr(backendCategoryAttr));
	}
	
	/**
	 * Description  商家端端商品后台类目中规格属性添加  
	 * 
	 * Author Hedda  
	 * 
	 * @param backendCategoryAttrDto
	 * @param result
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/addMerchantBackendCategoryAttr", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addMerchantBackendCategoryAttr(@Valid BackendCategoryAttrDto backendCategoryAttrDto,
			BindingResult result) throws GlobalException {
		/** 对商品后台类目字段进行校验 */
		if (result.hasErrors()) {
			/** 初始化非法参数的提示信息。 */
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/** 获取非法参数异常信息对象，并抛出异常。 */
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		BackendCategoryAttrEntity backendCategoryAttr = new BackendCategoryAttrEntity();
		BeanUtils.copyProperties(backendCategoryAttrDto, backendCategoryAttr);
		return new ResponseInfo(backendCategoryService.saveMerchantBackendCategoryAttr(backendCategoryAttr));
	}

	/**
	 * 
	 * @Description 商品类目根据id进行查询
	 * 
	 * @Author Hedda
	 * 
	 * @param id
	 *            商品后台类目id
	 * @return
	 */
	@RequestMapping(path = "/findBackendCategoryById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo findBackendCategoryById(Long id) throws GlobalException {
		return new ResponseInfo(backendCategoryService.findBackendCategoryById(id));
	}

	/**
	 * Description 对商品后台类目属性进行升降序操作
	 * 
	 * Author Hedda
	 * 
	 * @param backendCategoryAttrDto
	 *            商品后台类目属性dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getBackendCategoryAttrBySortNo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editBackendCategoryAttrBySortNo(
			@RequestBody List<BackendCategoryAttrDto> backendCategoryAttrDto) throws GlobalException {
		return new ResponseInfo(backendCategoryService.modifyBackendCategoryAttrBySortNo(backendCategoryAttrDto));
	}

	/**
	 * @Description 商品后台类目规格属性根据id进行查询
	 * 
	 * @Author Hedda
	 * 
	 * @param id
	 *            商品后台类目规格属性主键id
	 * @return
	 */
	@RequestMapping(path = "/findBackendCategoryAttrById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo findBackendCategoryAttrById(Long id) throws GlobalException {
		return new ResponseInfo(backendCategoryService.findBackendCategoryAttrById(id));
	}

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
	@RequestMapping(value = "/getBackendCategoryByName", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getBackendCategoryByName(BackendCategoryDto backendCategoryDto) throws GlobalException {
		BackendCategoryDto backendCategory = backendCategoryService.getBackendCategoryByName(backendCategoryDto);
		return new ResponseInfo(backendCategory);

	}

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
	@RequestMapping(value = "/getBackendCategoryAttrByName", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getBackendCategoryAttrByName(BackendCategoryAttrDto backendCategoryAttrDto)
			throws GlobalException {
		BackendCategoryAttrDto backendCategoryAttr = backendCategoryService
				.getBackendCategoryAttrByName(backendCategoryAttrDto);
		return new ResponseInfo(backendCategoryAttr);

	}

	/**
	 * Description 平台 查看商品后台第三级类目
	 * 
	 * Author Hedda
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/getThreeBackendCategory", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getThreeBackendCategoryList() throws GlobalException {
		List<BackendCategoryDto> backendCategoryList = backendCategoryService.getThreeBackendCategoryList();
		return new ResponseInfo(backendCategoryList);

	}

	/**
	 * Description 商家 查看商品后台第三级类目
	 * 
	 * Author Hedda
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/getMerchantThreeBackendCategory", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantThreeBackendCategoryList() throws GlobalException {
		List<BackendMerchantCategoryDto> backendMerchantCategoryList = backendCategoryService.getMerchantThreeCategory();
		return new ResponseInfo(backendMerchantCategoryList);

	}

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
	@RequestMapping(value = "/getOneAndTwoBackendCategoryById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getOneAndTwoBackendCategoryById(Long backendCategoryId) throws GlobalException {
		List<BackendCategoryDto> backendCategoryList = backendCategoryService
				.getOneAndTwoBackendCategoryById(backendCategoryId);
		return new ResponseInfo(backendCategoryList);

	}

	/**
	 * Description 根据商家id添加商品后台类目
	 * 
	 * Author Hedda
	 * 
	 * @param belongStore
	 *            商家id
	 * @param ids
	 *            商家后台类目ids
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/saveBackendCategoryDtoByBelongStore", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo saveBackendCategoryDtoByBelongStore(Long[] ids) throws GlobalException {
		return new ResponseInfo(backendCategoryService.addBackendCategoryDtoByBelongStore(ids));

	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public void test() {
		backendCategoryService.test();
	}

}
