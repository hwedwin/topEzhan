package com.topaiebiz.merchant.info.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.nebulapaas.web.util.IllegalParamValidationUtils;
import com.topaiebiz.goods.category.backend.dto.BackendCategorysDto;
import com.topaiebiz.goods.category.backend.service.BackendCategoryService;
import com.topaiebiz.merchant.info.dto.MerchantInfoDto;
import com.topaiebiz.merchant.info.dto.MerchantInfoListDto;
import com.topaiebiz.merchant.info.entity.MerchantInfoEntity;
import com.topaiebiz.merchant.info.service.MerchantInfoService;

/**
 * Description: 商家管理控制层
 * 
 * Author : Anthony
 * 
 * Date :2017年9月27日 下午1:25:19
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Controller
@RequestMapping(path = "/merchant/merchantInfo")
public class MerchantInfoController {

	@Autowired
	private MerchantInfoService merchantInfoService;

	@Autowired
	private BackendCategoryService backendCategoryService;

	/**
	 * Description： 添加商家信息。
	 * 
	 * Author: Anthony
	 * 
	 * param: dto 商家信息dto
	 * 
	 * param: result 绑定的结果异常
	 * 
	 * throws: GlobalException 全局统一异常类
	 * 
	 * return: saveInteger 执行成功或失败的提示信息
	 */
	@RequestMapping(path = "/insertMerchantInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addMerchantInfo(@Valid MerchantInfoDto dto, BindingResult result) throws GlobalException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。
		MerchantInfoEntity entity = new MerchantInfoEntity();
		BeanUtils.copyProperties(dto, entity);
		return new ResponseInfo(merchantInfoService.saveMerchantInfo(entity));
	}

	/**
	 * Description： 商家信息列表分页检索
	 * 
	 * Author: Anthony
	 * 
	 * param : page 分页参数
	 * 
	 * param : merchantInfoDto 商家信息Dto
	 * 
	 * return : list 商家信息列表数据
	 * 
	 * throws : GlobalException 全局统一异常类
	 */
	@RequestMapping(path = "/getMerchantInfoList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantInfoList(Page<MerchantInfoListDto> page, MerchantInfoListDto MerchantInfoListDto)
			throws GlobalException {
		return new ResponseInfo(merchantInfoService.getMerchantInfoList(page, MerchantInfoListDto));
	}

	/**
	 * Description：删除商家信息(冻结)。
	 * 
	 * Author: Anthony
	 * 
	 * param: id 商家信息id
	 * 
	 * return : 执行成功与否信息参数
	 * 
	 * throws : GlobalException 全局异常类
	 */
	@RequestMapping(path = "/deleteMerchantInfoById", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelMerchantInfoById(Long id) throws GlobalException {
		return new ResponseInfo(merchantInfoService.removeMerchantInfoById(id));
	}

	/**
	 * Description：编辑(修改)商家信息。
	 * 
	 * Author: Anthony
	 * 
	 * param : dto 商家信息dto对象
	 * 
	 * param : result 绑定异常结果集
	 * 
	 * return : 执行成功与否的信息
	 * 
	 * throws : GlobalException 全局异常类
	 */
	@RequestMapping(path = "updateMerchantInfoById", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editMerchantInfoById(@Valid MerchantInfoDto dto, BindingResult result) throws GlobalException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。
		return new ResponseInfo(merchantInfoService.modifyMerchantInfoById(dto));
	}

	/**
	 * Description：查看商家详情(根据Id查看商家详情数据回显)。
	 * 
	 * Author: Anthony
	 * 
	 * param : id 商家信息id
	 * 
	 * return : 商家详情 dto对象
	 * 
	 * throws : GlobalException 全局异常类
	 */
	@RequestMapping(path = "/getMerchantParticulaById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantParticularsByIrsd(Long id) throws GlobalException {
		List<BackendCategorysDto> backendCategoryDtoByBelongStore = backendCategoryService
				.getBackendCategoryDtoByBelongStore(id);
		MerchantInfoDto merchantParticularsById = merchantInfoService.getMerchantParticularsById(id);
		merchantParticularsById.setBackendCategorysDtos(backendCategoryDtoByBelongStore);
		return new ResponseInfo(merchantParticularsById);
	}

	/**
	 * Description：查看门店信息(根据Id查看门店信息数据回显)。
	 * 
	 * Author: Anthony
	 * 
	 * param : id 门店信息id
	 * 
	 * return : 门店信息实体类对象
	 * 
	 * throws : GlobalException 全局异常类
	 */
	@RequestMapping(path = "/getStoreInfoById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getStoreInfoById(Long merchantId) throws GlobalException {
		return new ResponseInfo(merchantInfoService.getStoreInfoById(merchantId));

	}

	/**
	 * Description： 商户类型下拉框展示
	 * 
	 * Author: Anthony
	 * 
	 * return : MerchantType 商户类型的名称和对应的id
	 */
	@RequestMapping(path = "/getMerchantTypeList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantTypeList() throws GlobalException {
		return new ResponseInfo(merchantInfoService.getMerchantType());
	}

	/**
	 * Description：获取店铺信息
	 * 
	 * Author: Anthony
	 * 
	 * return : StoreInfoByName
	 */
	@RequestMapping(path = "/getStoreInfoList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getStoreInfoList(MerchantInfoDto dto) throws GlobalException {
		return new ResponseInfo(merchantInfoService.getStoreInfoByName(dto));

	}

	/**
	 * Description：商家等级设置。
	 * 
	 * Author: Anthony
	 * 
	 * param : id 商家id , merchantGradeId 商家等级id
	 * 
	 * param : result 绑定异常结果集
	 * 
	 * return : 执行成功与否的信息
	 * 
	 * throws : GlobalException 全局异常类
	 */
	@RequestMapping(path = "updateMerchantInfoByMerchantGradeId", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editMerchantInfoByMerchantGradeId(Long id, Long merchantGradeId) throws GlobalException {
		return new ResponseInfo(merchantInfoService.modifyMerchantInfoByMerchantGradeId(id, merchantGradeId));
	}

	/**
	 * Description： 商户类型下拉框
	 * 
	 * Author: Anthony
	 * 
	 * return 商家信息Dto
	 * 
	 * throws GlobalException
	 */
	@RequestMapping(path = "getMerchantInfoByMerchantType", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantInfoByMerchantType() throws GlobalException {
		return new ResponseInfo(merchantInfoService.getMerchantInfoByMerchantType());
	}

	/**
	 * Description： 所属商家。
	 * 
	 * Author: Anthony   
	 * 
	 * @return
	 */
	@RequestMapping(path = "getMerchantInfoByName", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantInfoByName() {
		return new ResponseInfo(merchantInfoService.getMerchantInfoByName());
	}
	
	/**
	 * Description： 所属店铺。
	 * 
	 * Author: Anthony   
	 * 
	 * @return
	 */
	@RequestMapping(path = "getStoreInfoListById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo selectStoreInfoList() {
		return new ResponseInfo(merchantInfoService.getStoreInfoList());
	}
}
