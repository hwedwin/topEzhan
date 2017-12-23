package com.topaiebiz.member.address.mobile.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.nebulapaas.web.util.IllegalParamValidationUtils;
import com.topaiebiz.member.address.dto.MemberAddressDto;
import com.topaiebiz.member.address.entity.MemberAddressEntity;
import com.topaiebiz.member.address.exception.MemberAddressExceptionEnum;
import com.topaiebiz.member.address.service.MemberAddressService;
import com.topaiebiz.system.moble.security.dto.TokenDto;
import com.topaiebiz.system.moble.security.util.TokenUtil;
/**
 * 
 * Description：会员收货地址控制层  
 * 
 * Author Scott.Yang
 *    
 * Date 2017年10月13日 下午8:20:57 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 *  
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@RestController
@RequestMapping("/app/member/address")
public class MemberAddressController {
	
	@Autowired
	private MemberAddressService memberAddressService;
	
	/**
	 * 
	 * Description：添加会员收货地址  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberAddressDto
	 * 			会员收货地址Dto
	 * @param result
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/saveMemberAddress",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addMemberAddress(@Valid String token,MemberAddressDto memberAddressDto, BindingResult result)
			throws GlobalException {
		/** 校验Token*/
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		Long memberId = tokenDetail.getMemberId();
		memberAddressDto.setMemberId(memberId);
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。
		MemberAddressEntity entity = new MemberAddressEntity();
		BeanUtils.copyProperties(memberAddressDto, entity);
		Integer saveInteger = memberAddressService.saveMemberAddress(entity);
		return new ResponseInfo(saveInteger);
	}
	
	/**
	 * 
	 * Description：修改会员收货地址  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberAddressDto
	 * 			会员收货地址Dto
	 * @param result
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/updateMemberAddress",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editMemberAddress(String token,MemberAddressDto memberAddressDto, BindingResult result)
			throws GlobalException {
		/** 校验Token*/
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		Long memberId = tokenDetail.getMemberId();
		memberAddressDto.setMemberId(memberId);
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。
		Integer updateInteger = memberAddressService.modifyMemberAddress(memberAddressDto);
		return new ResponseInfo(updateInteger);
	}
	
	/**
	 * Description： 根据id查询收货地址信息
	 * 
	 * Author Scott.Yang
	 * 
	 * @param id
	 * 		收货编号ID
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/findMemberAddressById")
	@ResponseBody
	public ResponseInfo findMemberAddressById(String token) throws GlobalException {
		/** 校验Token*/
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		Long memberId = tokenDetail.getMemberId();
		MemberAddressDto memberAddressDto = new MemberAddressDto();
		if (null == memberId) {
			throw new GlobalException(MemberAddressExceptionEnum.MEMBERADDRESS_ID_NOT_NULL);
		}
			MemberAddressEntity memberAddress = memberAddressService.findMemberAddressById(memberId);
			BeanUtils.copyProperties(memberAddress, memberAddressDto);
		return new ResponseInfo(memberAddressDto);
	}
	
	/**
	 * 
	 * Description： 删除会员收货地址   
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param id
	 * 		会员收货地址ID
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/cancelMemberAddress",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelMemberAddress(String token,Long id)
			throws GlobalException {
		/** 校验Token*/
		TokenUtil.verifyToken(token);
		if (null == id) {
			throw new GlobalException(MemberAddressExceptionEnum.MEMBERADDRESS_ID_NOT_NULL);
		}
		memberAddressService.removeMemberAddress(id);
		return new ResponseInfo();
	}
	
	/**
	 * 
	 * Description： 会员收货地址列表 
	 * 
	 * Author Scott.Yang   
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/listMemberAddress")
	@ResponseBody
	public ResponseInfo listMemberAddress(String token)
			throws GlobalException {
		/** 校验Token*/
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		Long memberId = tokenDetail.getMemberId();
		List<MemberAddressDto> MemberAddressList = memberAddressService.listMemberAddress(memberId);
		return new ResponseInfo(MemberAddressList);
	}
	
}
