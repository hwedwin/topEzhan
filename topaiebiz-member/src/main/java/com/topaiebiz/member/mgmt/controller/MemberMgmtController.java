package com.topaiebiz.member.mgmt.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.nebulapaas.web.util.IllegalParamValidationUtils;
import com.topaiebiz.member.reserve.entity.MemberTypeEntity;
import com.topaiebiz.member.mgmt.dto.MemberDto;
import com.topaiebiz.member.mgmt.dto.MemberRegisterDto;
import com.topaiebiz.member.mgmt.dto.MemberStatisticsDto;
import com.topaiebiz.member.mgmt.entity.MemberEntity;
import com.topaiebiz.member.mgmt.exception.MemberMgmtExceptionEnum;
import com.topaiebiz.member.mgmt.service.MemberMgmtService;


/**
 * Description：会员管理控制层   
 * 
 * Author Scott.Yang
 *    
 * Date 2017年9月26日 下午4:53:39 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@RestController
@RequestMapping("/member/mgmt")
public class MemberMgmtController {
	
	@Autowired
	private MemberMgmtService memberMgmtService;
	
	/**
	 * Description： 会员管理分页加列表加查询  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param page
	 * 		分页参数
	 * @param memberDto
	 * 		会员信息Dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/listMemberMgmt")
	@ResponseBody
	public ResponseInfo listMemberMgmt(Page<MemberDto> page, MemberDto memberDto)
			throws GlobalException {
		Page<MemberDto> list = memberMgmtService.listMemberMgmt(page, memberDto);
		return new ResponseInfo(list);
	}
	
	/**
	 * Description： 会员管理分页加列表加查询(商家端)  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param page
	 * 		分页参数
	 * @param memberDto
	 * 		会员信息Dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/listMemberMgmtForSeller")
	@ResponseBody
	public ResponseInfo listMemberMgmtForSeller(Page<MemberDto> page, MemberDto memberDto)
			throws GlobalException {
		Page<MemberDto> list = memberMgmtService.listMemberMgmtForSeller(page, memberDto);
		return new ResponseInfo(list);
	}
	/**
	 * Description：获取会员类型列表  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getListOfMemberType")
	@ResponseBody
	public ResponseInfo getlistMemberType()
			throws GlobalException {
		List<MemberTypeEntity> listMemberType = memberMgmtService.getListOfMemberType();
		return new ResponseInfo(listMemberType);
	}
	
	
	/**
	 * Description： 添加注册会员信息 
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberRegisterDto
	 * 		会员信息Dto
	 * @param result
	 * 		错误结果	
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/saveRegisterMemberMgmt",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addRegisterMemberMgmt(@Valid String captcha,MemberRegisterDto memberRegisterDto, BindingResult result,HttpServletRequest request)
			throws GlobalException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。
		
		Map<String,Object> map = memberMgmtService.quickLoginMemberMgmt(captcha,memberRegisterDto,request);
		return new ResponseInfo(map);
	}
	
	/**
	 * Description： 添加会员信息  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberDto
	 * 		会员信息Dto
	 * @param result
	 * 		错误结果	
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/saveMemberMgmt",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addMemberMgmt(@Valid MemberDto memberDto, BindingResult result)
			throws GlobalException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。
		MemberEntity entity = new MemberEntity();
		BeanUtils.copyProperties(memberDto, entity);
		Integer saveInteger = memberMgmtService.saveMemberMgmt(entity);
		return new ResponseInfo(saveInteger);
	}
	
	/**
	 * Description： 修改会员信息  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberDto
	 * 		会员信息Dto
	 * @param result
	 * 		错误结果	
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/updateMemberMgmt",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editMemberMgmt(@Valid MemberDto memberDto, BindingResult result)
			throws GlobalException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。
		Integer updateInteger = memberMgmtService.modifyMemberMgmt(memberDto);
		return new ResponseInfo(updateInteger);
	}
	
	/**
	 * Description： 根据id查询会员信息
	 * 
	 * Author Scott.Yang
	 * 
	 * @param id
	 * 		会员编号ID
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/findMemberMgmtById")
	@ResponseBody
	public ResponseInfo findMemberMgmtById(Long id) throws GlobalException {
		MemberDto memberDto = new MemberDto();
		if (null == id) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
			MemberEntity member = memberMgmtService.findMemberMgmtById(id);
			BeanUtils.copyProperties(member, memberDto);
		return new ResponseInfo(memberDto);
	}
	
	/**
	 * Description： 根据id删除会员信息(修改状态)
	 * 
	 * Author Scott.Yang
	 * 
	 * @param id
	 * 		会员编号ID
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/cancelMemberMgmts",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelMemberMgmts(Long[] id) throws GlobalException {
		if (null == id) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		memberMgmtService.removeMemberMgmts(id);
		return new ResponseInfo();
	}
	
	/**
	 * Description： 根据id冻结会员状态
	 * 
	 * Author Scott.Yang
	 * 
	 * @param id
	 * 		会员编号ID
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/congelationMemberMgmt",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo congelationMemberMgmt(Long id) throws GlobalException {
		if (null == id) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		memberMgmtService.congelationMemberMgmt(id);
		return new ResponseInfo();
	}
	
	/**
	 * Description： 根据id解除冻结会员状态
	 * 
	 * Author Scott.Yang
	 * 
	 * @param id
	 * 		会员编号ID
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/relieveMemberMgmt",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo relieveMemberMgmt(Long id) throws GlobalException {
		if (null == id) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		memberMgmtService.relieveMemberMgmt(id);
		return new ResponseInfo();
	}
	
	@RequestMapping(path = "/getMemberMgmt",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo getMemberMgmt(MemberDto memberDto)
			throws GlobalException {
	
		Map<String,Object> map = memberMgmtService.getMemberMgmt(memberDto);
		return new ResponseInfo(map);
	}
	
	@RequestMapping(path = "/addMerchantPerson",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addMerchantPerson(@Valid String captcha,MemberRegisterDto memberRegisterDto, BindingResult result) throws GlobalException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。
		Map<String,Object> map = memberMgmtService.registerMemberMgmt(memberRegisterDto);
		return new ResponseInfo(map);
	}
	/**
	 * 
	 * Description： 会员增值情况视图(按年展示) 
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberStatisticsDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(path = "/listMemberRecordByYear")
	@ResponseBody
	public ResponseInfo listMemberRecordByYear(MemberStatisticsDto memberStatisticsDto)
			throws Exception {
		List<MemberStatisticsDto> list = memberMgmtService.listMemberRecordByYear(memberStatisticsDto);
		return new ResponseInfo(list);
	}
	/**
	 * 
	 * Description：  会员增值情况视图(按月展示)   
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberStatisticsDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(path = "/listMemberRecordByMonths")
	@ResponseBody
	public ResponseInfo listMemberRecordByMonths(MemberStatisticsDto memberStatisticsDto)
			throws Exception {
		List<MemberStatisticsDto> list = memberMgmtService.listMemberRecordByMonths(memberStatisticsDto);
		return new ResponseInfo(list);
	}
	/**
	 * 
	 * Description： 会员增值情况视图(按年展示商家端) 
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberStatisticsDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(path = "/listMemberRecordByYearOfBusiness")
	@ResponseBody
	public ResponseInfo listMemberRecordByYearOfBusiness(MemberStatisticsDto memberStatisticsDto)
			throws Exception {
		List<MemberStatisticsDto> list = memberMgmtService.listMemberRecordByYearOfBusiness(memberStatisticsDto);
		return new ResponseInfo(list);
	}
	/**
	 * 
	 * Description：  会员增值情况视图(按月展示商家端)   
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberStatisticsDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(path = "/listMemberRecordByMonthsOfBusiness")
	@ResponseBody
	public ResponseInfo listMemberRecordByMonthsOfBusiness(MemberStatisticsDto memberStatisticsDto)
			throws Exception {
		List<MemberStatisticsDto> list = memberMgmtService.listMemberRecordByMonthsOfBusiness(memberStatisticsDto);
		return new ResponseInfo(list);
	}
	
	@ResponseBody
	@PostMapping(value = "/addUser")
	public ResponseInfo addUser(Long memberId, Integer type) throws GlobalException {
		// 如果参数非法，抛出异常。
		if(memberId == null) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		if(null == type) {
			throw new GlobalException(MemberMgmtExceptionEnum.TYPE_NOT_NULL);
		}
		memberMgmtService.addUser(memberId, type);
		return new ResponseInfo();

	}
}
