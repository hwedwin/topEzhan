package com.topaiebiz.merchant.enter.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.topaiebiz.system.security.util.SecurityContextUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.nebulapaas.web.util.IllegalParamValidationUtils;
import com.topaiebiz.goods.category.backend.dto.BackendCategorysDto;
import com.topaiebiz.goods.category.backend.service.BackendCategoryService;
import com.topaiebiz.member.mgmt.entity.MemberEntity;
import com.topaiebiz.member.mgmt.service.MemberMgmtService;
import com.topaiebiz.merchant.enter.dto.AddFreightTempleteDto;
import com.topaiebiz.merchant.enter.dto.CapitalDto;
import com.topaiebiz.merchant.enter.dto.MerFreightTempleteDto;
import com.topaiebiz.merchant.enter.dto.MercahntManageInfoDto;
import com.topaiebiz.merchant.enter.dto.MerchantBasicInfoDto;
import com.topaiebiz.merchant.enter.dto.MerchantManageDto;
import com.topaiebiz.merchant.enter.dto.MerchantQualificationDto;
import com.topaiebiz.merchant.enter.dto.MerchantauditLogDto;
import com.topaiebiz.merchant.enter.dto.StateDto;
import com.topaiebiz.merchant.enter.dto.StoreInfoDto;
import com.topaiebiz.merchant.enter.dto.StoreInfoDtos;
import com.topaiebiz.merchant.enter.exception.MerchantEnterException;
import com.topaiebiz.merchant.enter.service.MerchantEnterService;

/**
 * Description: 商家入驻流程控制层
 * 
 * Author : Anthony
 * 
 * Date :2017年10月9日 上午11:04:02
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Controller
@RequestMapping(path = "/merchant/merchantEnter")
public class MerchantEnterController {

	@Autowired
	private MerchantEnterService merchantEnterService;

	@Autowired
	private BackendCategoryService backendCategoryService;

	@Autowired
	private MemberMgmtService memberMgmtService;

	/**
	 * Description：商家入驻流程--基本信息填写（公司及联系人信息）
	 * 
	 * Author: Anthony
	 * 
	 * param : dto 商家资质Dto
	 * 
	 * param : result 绑定结果集
	 * 
	 * return : 执行结果参数提示
	 * 
	 * throws GlobalException : 全局异常类
	 */
	@RequestMapping(path = "/insertMerchantQualification", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addMerchantQualification(HttpSession session, @Valid MerchantBasicInfoDto merchantBasicInfoDto, BindingResult result)
			throws GlobalException, ParseException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		merchantEnterService.saveMerchantQualification(merchantBasicInfoDto, session);
		return new ResponseInfo();
	}
	
	/**
	 * Description：修改基本信息
	 * 
	 * Author: Anthony
	 * 
	 * param merchantBasicInfoDto 基本信息Dto参数
	 */
	@RequestMapping(path = "/updateMerchantQualificationById", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editMerchantQualificationById(MerchantBasicInfoDto merchantBasicInfoDto) {
		merchantEnterService.modifyMerchantQualificationById(merchantBasicInfoDto);
		return new ResponseInfo();
	}
	
	/**
	 * Description： 经营信息(营业执照信息、银行账户信息)添加
	 * 
	 * Author: Anthony
	 * 
	 * param : dto 商家账户信息Dto
	 * 
	 * param : result 绑定结果集
	 * 
	 * return : 执行结果参数提示
	 * 
	 * throws GlobalException : 全局异常类
	 */
	@RequestMapping(path = "/insertMerchantAccount", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addMerchantAccount(@Valid MercahntManageInfoDto mercahntManageInfoDto, BindingResult result,
			Long qualificationId) throws GlobalException, ParseException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		merchantEnterService.saveMerchantAccount(mercahntManageInfoDto);
		return new ResponseInfo();
	}

	/**
	 * Description： 修改经营信息。
	 * 
	 * Author: Anthony
	 * 
	 * param mercahntManageInfoDto 经营信息dto参数
	 */
	@RequestMapping(path = "/updateMerchantManageInfoById", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editMerchantManageInfoById(MercahntManageInfoDto mercahntManageInfoDto) throws ParseException {
		merchantEnterService.modifyMerchantInfoById(mercahntManageInfoDto);
		return new ResponseInfo();
	}
	
	/**
	 * Description：商家入驻流程--在线经营范围（店铺经营信息）添加类目
	 * 
	 * Author: Anthony
	 * 
	 * param : dto 店铺经营信息Dto
	 * 
	 * param : result 绑定结果集
	 * 
	 * return : 执行结果参数提示
	 * 
	 * throws GlobalException : 全局异常类
	 */
	@RequestMapping(path = "/insertStoreInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addStoreInfo(@Valid StoreInfoDtos storeInfoDto, BindingResult result)
			throws GlobalException, ParseException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		merchantEnterService.addStoreBackendCategoryInfo(storeInfoDto);
		return new ResponseInfo();
	}
	
	/**
	 * Description： 平台端删除商家类目
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param merchantId
	 * @param ids
	 * @return
	 * @throws GlobalException
	 * @throws ParseException
	 */
	@RequestMapping(path = "/cancelMerchantBackendCategory", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelMerchantBackendCategory(Long merchantId, Long[] ids) throws GlobalException, ParseException {
		merchantEnterService.cancelMerchantBackendCategory(merchantId, ids);
		return new ResponseInfo();
	}
	
	/////////////////////////////////以下回显
	
	/**
	 * Description : 我要查询功能，信息审核，根据商家的入驻状态回显审核结果(信息查询)
	 * 
	 * Author: Anthony
	 * 
	 * param username 账号
	 */
	@RequestMapping(path = "/getMerchantInfoStateByLoginName", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantInfoStateByLoginName(String username) throws GlobalException {
		StateDto stateDto = merchantEnterService.getMerchantInfoStateByLoginName(username);
		return new ResponseInfo(stateDto);
	}
	
	/**
	 * Description：根据商家id回显提交的信息 (公司及联系人信息)
	 * 
	 * Author: Anthony
	 * 
	 * param : loginName 商家的账户
	 */
	@RequestMapping(path = "/getMerchantInfoByLoginName", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantInfoByLoginName() {
		return new ResponseInfo(merchantEnterService.getMerchantInfoByLoginName());
	}
	
	/**
	 * Description：根据商家id回显商家的提交信息 (经营信息)。
	 * 
	 * Author: Anthony
	 * 
	 * param loginName 商家的账户
	 */
	@RequestMapping(path = "/getMercahntManageInfoByLoginName", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMercahntManageInfoByLoginName() {
		return new ResponseInfo(merchantEnterService.getMercahntManageInfoByLoginName());
	}
	
	/**
	 * Description：入驻回显经营类目
	 * 
	 * Author: Aaron
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(path = "/getMerchantCategory", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantCategory() {
		// 根据店铺id查出店铺所选的经营类目
		List<BackendCategorysDto> backendCategoryDtoByBelongStore = merchantEnterService.getBackendCategoryDtoByBelongStore();
		return new ResponseInfo(backendCategoryDtoByBelongStore);
	}
	
	/***
	 * Description：商家端费用信息回显。
	 * 
	 * Author: Anthony
	 * 
	 * param : id 商家信息id
	 */
	@RequestMapping(path = "/getCostInfoByIds", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getCostInfoByIds() {
		// 根据当前用户查出商家id
		String telephone = SecurityContextUtils.getCurrentSystemUser().getMobilePhone();
		MemberEntity member = memberMgmtService.getMemberByuserTelephone(telephone);
		Long id = member.getMerchantId();
		return new ResponseInfo(merchantEnterService.getCostInfoById(id));
	}

	/**
	 * Description： 上传缴费凭证.
	 * 
	 * Author: Anthony
	 * 
	 * param : capitalDto
	 */
	@RequestMapping(path = "/insertPayImage", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addPayImage(CapitalDto capitalDto) {
		merchantEnterService.savePayImage(capitalDto);
		return new ResponseInfo();
	}

	/**
	 * Description：商家不通过原因回显(我要查询)的操作。
	 * 
	 * Author: Anthony
	 * 
	 * @return
	 */
	@RequestMapping(path = "/selectMerchantAuditByMerchantId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo selectMerchantAuditByMerchantId() {
		// 根据当前用户查出商家id
		String telephone = SecurityContextUtils.getCurrentSystemUser().getMobilePhone();
		MemberEntity member = memberMgmtService.getMemberByuserTelephone(telephone);
		Long merchantId = member.getMerchantId();
		return new ResponseInfo(merchantEnterService.selectMerchantAuditByMerchantId(merchantId));
	}
	
	
	///////////////////////////////////////查询

	/**
	 * Description：根据商家id回显商家的提交信息 (在线经营范围)。
	 * 
	 * Author: Anthony
	 * 
	 * param : loginName 商家的账户
	 */
	@RequestMapping(path = "/getMerchantTypeByLoginName", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantTypeByLoginName() {
		StoreInfoDto storeInfoDto = merchantEnterService.getMerchantTypeByLoginName();
		Long storeId = storeInfoDto.getId();
		List<BackendCategorysDto> backendCategoryDtoByBelongStore = backendCategoryService
				.getBackendCategoryDtoByBelongStore(storeId);
		storeInfoDto.setBackendCategorysDtos(backendCategoryDtoByBelongStore);
		return new ResponseInfo(storeInfoDto);
	}



	/***
	 * Description：平台端费用信息回显。
	 * 
	 * Author: Anthony
	 * 
	 * param : id 商家信息id
	 */
	@RequestMapping(path = "/getCostInfoById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getCostInfoById(Long id) {
		return new ResponseInfo(merchantEnterService.getCostInfoById(id));
	}

	

	/**
	 * Description： 创建店铺后店铺信息的回显。
	 * 
	 * Author: Anthony
	 * 
	 * param : id 店铺id
	 * 
	 * return : 店铺信息
	 */
	@RequestMapping(path = "/selectStoreInfoById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getStoreInfoById(Long id) {
		return new ResponseInfo(merchantEnterService.getStoreInfoById(id));
	}

	/**
	 * Description：点击审核通过修改商家入驻状态。
	 * 
	 * Author: Anthony
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(path = "/updateMerchantInfoStateById", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo updateMerchantInfoStateById(Long id, Integer state) {
		merchantEnterService.updateMerchantInfoStateById(id, state);
		return new ResponseInfo();
	}

	/**
	 * Description：点击不通过修改商家入驻状态。无用
	 * 
	 * Author: Anthony
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(path = "/updateMerchantInfoStateByMerchantId", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo updateMerchantInfoStateByMerchantId(Long id) {
		merchantEnterService.updateMerchantInfoStateByMerchantId(id);
		return new ResponseInfo();
	}

	/**
	 * Description：点击通过修改商家入驻状态。
	 * 
	 * Author: Anthony
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(path = "/updateStateByMerchantId", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo updateStateByMerchantId(Long id) {
		merchantEnterService.updateStateByMerchantId(id);
		return new ResponseInfo();
	}

	/**
	 * Description： 平台端缴费
	 * 
	 * Author: Anthony
	 * 
	 * param : capitalDto 缴费信息
	 */
	@RequestMapping(path = "/insertPaymentPrice", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addPaymentPrice(CapitalDto capitalDto) {
		merchantEnterService.savePaymentPrice(capitalDto);
		return new ResponseInfo();
	}

	////////////////////////////////////////////////////以下后台审核功能
	/**
	 * Description : 商家资质信息分页检索。
	 * 
	 * Author : Anthony
	 * 
	 * param : page 分页参数
	 * 
	 * param : merchantQualificationDto 商家资质Dto对象
	 * 
	 * return : 商家资质List
	 */
	@RequestMapping(path = "/getMerchantQualificationList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantQualificationList(Page<MerchantQualificationDto> page,
			MerchantQualificationDto merchantQualificationDto) {
		return new ResponseInfo(merchantEnterService.getMerchantQualificationList(page, merchantQualificationDto));
	}
	
	/**
	 * Description：商家基本信息回显
	 * 
	 * Author: Anthony
	 * 
	 * param : id 商家资质信息id
	 * 
	 * return : 商家资质信息数据
	 * 
	 * throws : GlobalException 全局异常类
	 */
	@RequestMapping(path = "/getMerchantQualificationListById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantParticularsByIrsd(Long id) throws GlobalException {
		return new ResponseInfo(merchantEnterService.selectMerchantBasicInfOById(id));
	}

	/**
	 * Description：经营类目信息回显。
	 * 
	 * Author: Anthony
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(path = "/getMerchantManageInfoById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantManageInfoById(Long id) {
		MerchantManageDto merchantManageDto = new MerchantManageDto();
		// 根据店铺id查出店铺所选的经营类目
		List<BackendCategorysDto> backendCategoryDtoByBelongStore = backendCategoryService.getBackendCategoryDtoByBelongStore(id);
		merchantManageDto = merchantEnterService.getMerchantManageInfoById(id);
		merchantManageDto.setBackendCategorysDtos(backendCategoryDtoByBelongStore);
		return new ResponseInfo(merchantManageDto);
	}
	
	/**
	 * Description：审核商家基本信息并保存不通过原因及不通过字段(平台端点击审核完成)
	 * 
	 * Author: Anthony
	 * 
	 * param : merchantauditLogDto 商家入驻审核记录Dto对象
	 * 
	 * param : result 绑定的结果集
	 * 
	 * return : 执行结果参数
	 * 
	 * throws : GlobalException 全局异常类
	 */
	@RequestMapping(path = "/insertMerchantauditLog", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addMerchantauditLog(@RequestBody MerchantauditLogDto MerchantauditLogDto, BindingResult result)
			throws GlobalException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		merchantEnterService.saveMerchantAuditLogAndDetail(MerchantauditLogDto);
		return new ResponseInfo();
	}
	
	/**
	 * Description： 审核记录
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/selectMerchantauditLog", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo selectMerchantauditLog() throws GlobalException {
		String telephone = SecurityContextUtils.getCurrentSystemUser().getMobilePhone();
		MemberEntity member = memberMgmtService.getMemberByuserTelephone(telephone);
		Long merchantId = member.getMerchantId();
		List<MerchantauditLogDto> list = merchantEnterService.selectMerchantauditLog(merchantId);
		return new ResponseInfo(list);
	}
	
	/**
	 * Description：不通过详情
	 * 
	 * Author: Anthony
	 * 
	 * @return
	 */
	@RequestMapping(path = "/selectMerchantauditLogDetail", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo selectMerchantauditLogDetail(Long id) {
		// 根据当前用户查出商家id
		return new ResponseInfo(merchantEnterService.getMerchantauditLogDetail(id));
	}
	
	
	
	
	
	////////////////////////////////////////////////////以下为运费模版
	/**
	 * Description：运费模板列表、分页检索。
	 * 
	 * Author: Anthony
	 * 
	 * param : page 分页参数
	 * 
	 * param : merFreightTempleteDto 运费模板Dto
	 */
	@RequestMapping(path = "/getMerFreightTempleteList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerFreightTempleteList(Page<MerFreightTempleteDto> page,
			MerFreightTempleteDto merFreightTempleteDto) {
		return new ResponseInfo(merchantEnterService.getMerFreightTempleteList(page, merFreightTempleteDto));
	}

	/**
	 * Description：查询运费模板详情。
	 * 
	 * Author: Anthony
	 * 
	 * param : storeId 店铺id
	 * 
	 * return : 运费模板与运费模板详情
	 * 
	 * throws : GlobalException
	 */
	@RequestMapping(path = "/getMerchantFreightTempleteBydStoreId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getFreightTempleteByStoreId(Long storeId) throws GlobalException {
		return new ResponseInfo(merchantEnterService.getFreightTempleteByStoreId(storeId));
	}

	/**
	 * Description: 平台的运费模板详情
	 * 
	 * Author: Anthony
	 * 
	 * return : 运费模板详情信息
	 */
	@RequestMapping(path = "/getFreightTempleteList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getFreightTempleteList() {
		return new ResponseInfo(merchantEnterService.getFreightTempleteList());
	}

	/**
	 * Description： 删除运费模板
	 * 
	 * Author: Anthony
	 * 
	 * param : id 运费模板id
	 */
	@RequestMapping(path = "/delectMerFreightTempleteById", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelMerFreightTempleteById(Long id) {
		return new ResponseInfo(merchantEnterService.removeMerFreightTempleteById(id));
	}

	/**
	 * Description：添加运费模板。
	 * 
	 * Author: Anthony
	 * 
	 * param : addFreightTempleteDto : 添加运费模板所需的dto
	 */
	@RequestMapping(path = "/addMerFreightTemplete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addMerFreightTemplete(@RequestBody AddFreightTempleteDto addFreightTempleteDto)
			throws GlobalException {
		if (addFreightTempleteDto.getFreightName() == null) {
			throw new GlobalException(MerchantEnterException.FREIGHTNAME_NOT_NOLL);
		}
		if (addFreightTempleteDto.getPricing() == null) {
			throw new GlobalException(MerchantEnterException.PRICING_NOT_NOLL);
		}
		merchantEnterService.saveMerFreightTemplete(addFreightTempleteDto);
		return new ResponseInfo();
	}

	/**
	 * Description： 运费模板修改回显。
	 * 
	 * Author: Anthony
	 * 
	 * param : id 运费模板id
	 */
	@RequestMapping(path = "/selectMerFreightTempleteById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo selectMerFreightTempleteById(Long id) {
		return new ResponseInfo(merchantEnterService.selectMerFreightTempleteById(id));
	}

	/**
	 * Description：修改运费模板
	 * 
	 * Author: Anthony
	 * 
	 * param : id 运费模板id
	 */
	@RequestMapping(path = "/updateMerFreightTempleteById", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo updateMerFreightTempleteById(@RequestBody AddFreightTempleteDto addFreightTempleteDto) {
		if (addFreightTempleteDto.getFreightName() == null) {
			throw new GlobalException(MerchantEnterException.FREIGHTNAME_NOT_NOLL);
		}
		if (addFreightTempleteDto.getPricing() == null) {
			throw new GlobalException(MerchantEnterException.PRICING_NOT_NOLL);
		}
		merchantEnterService.updateMerFreightTempleteById(addFreightTempleteDto);
		return new ResponseInfo();
	}

}
