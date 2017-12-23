package com.topaiebiz.member.grade.controller;

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
import com.topaiebiz.member.grade.dto.MemberGradeDto;
import com.topaiebiz.member.grade.dto.MemberGradePrivilegeDto;
import com.topaiebiz.member.grade.entity.MemberGradeEntity;
import com.topaiebiz.member.grade.entity.MemberGradePrivilegeEntity;
import com.topaiebiz.member.grade.service.MemberGradeService;
import com.topaiebiz.member.mgmt.exception.MemberMgmtExceptionEnum;

/**
 * Description：会员等级控制层。
 * 
 * Author Scott.Yang
 * 
 * Date 2017年9月23日 下午9:03:38
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@RestController
@RequestMapping("/member/grade")
public class MemberGradeController {

	@Autowired
	private MemberGradeService memberGradeService;

	/**
	 * Description：添加会员等级  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberGradeDto
	 * 			会员等级Dto
	 * @param result
	 * 			错误结果
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/saveMemberGrade",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addMemberGrade(@Valid MemberGradeDto memberGradeDto, BindingResult result)
			throws GlobalException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。
		Integer saveInteger = memberGradeService.saveMemberGrade(memberGradeDto);
		return new ResponseInfo(saveInteger);
	}

	/**
	 * Description：修改会员等级 
	 * 
	 * Author Scott.Yang
	 * 
	 * @param memberGradeDto
	 * 			会员等级Dto
	 * @param result
	 * 			错误结果
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/updateMemberGrade",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editMemberGrade(MemberGradeDto memberGradeDto, BindingResult result) throws GlobalException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。
		Integer updateInteger = memberGradeService.modifyMemberGrade(memberGradeDto);
		return new ResponseInfo(updateInteger); 
	}

	/**
	 * Description： 会员等级分页加列表加查询
	 * 
	 * Author Scott.Yang
	 * 
	 * @param page
	 * 		分页参数
	 * @param memberGradeDto
	 * 		会员等级Dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/listMemberGrade")
	@ResponseBody
	public ResponseInfo listMemberGrade(Page<MemberGradeDto> page, MemberGradeDto memberGradeDto)
			throws GlobalException {
		Page<MemberGradeDto> list = memberGradeService.listMemberGrade(page, memberGradeDto);
		return new ResponseInfo(list);
	}

	/**
	 * Description： 批量删除会员等级 (修改状态)
	 * 
	 * Author Scott.Yang
	 * 
	 * @param id
	 * 		会员等级编号
	 * @param result
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/cancelMemberGrades",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelMemberGrades(Long[] id) throws GlobalException {
		if (null == id) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		memberGradeService.removeMemberGrades(id);
		return new ResponseInfo();
	}

	/**
	 * Description： 会员等级配置特权
	 * 
	 * Author Scott.Yang
	 * 
	 * @param memberGradePrivilegeDto
	 * 			会员等级权限Dto
	 * @param result
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/addMemberGradePrivilege",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addMemberGradePrivilege(@Valid MemberGradePrivilegeDto memberGradePrivilegeDto, BindingResult result)
			throws GlobalException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。
		MemberGradePrivilegeEntity entity = new MemberGradePrivilegeEntity();
		BeanUtils.copyProperties(memberGradePrivilegeDto, entity);
		Integer saveInteger = memberGradeService.saveMemberGradePrivilege(entity);
		return new ResponseInfo(saveInteger);
	}

	/**
	 * Description： 根据id查询等级信息
	 * 
	 * Author Scott.Yang
	 * 
	 * @param id
	 * 		会员等级编号ID
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/findMemberGradesById")
	@ResponseBody
	public ResponseInfo findMemberGradesById(Long id) throws GlobalException {
		MemberGradeDto memberGradeDto = new MemberGradeDto();
		if (null != id) {
			MemberGradeEntity memberGrade = memberGradeService.findMemberGradesById(id);
			BeanUtils.copyProperties(memberGrade, memberGradeDto);
		}
		return new ResponseInfo(memberGradeDto);
	}
	
	/**
	 * Description： 获取会员的等级列表.
	 * 
	 * Author Scott.Yang   
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getListOfMemberGrade")
	@ResponseBody
	public ResponseInfo getListOfMemberGrade()
			throws GlobalException {
		List<MemberGradeEntity> listMemberGrade = memberGradeService.getListOfMemberGrade();
		return new ResponseInfo(listMemberGrade);
	}

}