package com.topaiebiz.settlement.member.controller;

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
import com.topaiebiz.settlement.member.dto.MemberSettlementDto;
import com.topaiebiz.settlement.member.dto.MemberWithdrawalLogDto;
import com.topaiebiz.settlement.member.entity.MemberWithdrawalLogEntity;
import com.topaiebiz.settlement.member.service.MemeberSettlementService;

/**
 * Description： 会员结算Controller。
 * 
 * Author Harry
 * 
 * Date 2017年10月31日 下午5:29:35
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@RestController
@RequestMapping("/settlement/member")
public class MemeberSettlementController {

	@Autowired
	private MemeberSettlementService memeberSettlementService;

	/**
	 * Description： 定时计算会员结算
	 * 
	 * Author Harry
	 * 
	 * @return
	 * @throws GlobalException
	 */
	/*@Scheduled(cron = "0 46 20 ? * * ")
	@RequestMapping(path = "/addMemeberSettlement", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addMemeberSettlement() throws GlobalException {
		return new ResponseInfo(memeberSettlementService.saveMemeberSettlement());
	}
*/
	/**
	 * Description 添加会员结算记录
	 * 
	 * Author Hedda
	 * 
	 * @param memberWithdrawalLogDto
	 *            会员记录dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/addMemberWithdrawalLogDto", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addMemberWithdrawalLogDto(@Valid MemberWithdrawalLogDto memberWithdrawalLogDto,BindingResult result)
			throws GlobalException {
		/** 对字段进行校验 */
		if (result.hasErrors()) {
			/**初始化非法参数的提示信息。*/
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/**获取非法参数异常信息对象，并抛出异常。*/
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		MemberWithdrawalLogEntity memberWithdrawalLogEntity = new MemberWithdrawalLogEntity();
		BeanUtils.copyProperties(memberWithdrawalLogDto, memberWithdrawalLogEntity);
		return new ResponseInfo(memeberSettlementService.saveMemberWithdrawalLogDto(memberWithdrawalLogEntity));
	}

	/**
	 * Description：平台端会员分销结算分页列表搜索。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * @param storeSettlementDto
	 * @return
	 */
	@RequestMapping(path = "getPlatMemberSettlementList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getPlatMemberSettlementList(Page<MemberSettlementDto> page,
			MemberSettlementDto memberSettlementDto) {
		Page<MemberSettlementDto> goodsdistributionallocationlist = memeberSettlementService
				.getPlatMemberSettlementList(page, memberSettlementDto);
		return new ResponseInfo(goodsdistributionallocationlist);
	}

	/**
	 * Description：平台端会员分销结算分页列表搜索根据会员id。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * @param memberSettlementDto
	 * @return
	 */
	@RequestMapping(path = "getPlatMemberSettlementListByMemberId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getPlatMemberSettlementListByMemberId(Page<MemberSettlementDto> page,
			MemberSettlementDto memberSettlementDto) {
		Page<MemberSettlementDto> goodsdistributionallocationlists = memeberSettlementService
				.getPlatMemberSettlementListByMemberId(page, memberSettlementDto);
		return new ResponseInfo(goodsdistributionallocationlists);
	}

	/**
	 * Description：商家端会员分销结算分页列表加分页加搜索。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * @param memberSettlementDto
	 * @return
	 */
	@RequestMapping(path = "getMerchantMemberSettlementList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantMemberSettlementList(Page<MemberSettlementDto> page,
			MemberSettlementDto memberSettlementDto) {
		Page<MemberSettlementDto> goodsdistributionallocationlist = memeberSettlementService
				.getMerchantMemberSettlementList(page, memberSettlementDto);
		return new ResponseInfo(goodsdistributionallocationlist);
	}

	/**
	 * Description： 商家端会员分销结算分页列表加分页加搜索根据会员id
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * @param memberSettlementDto
	 * @return
	 */
	@RequestMapping(path = "getMerchantMemberSettlementListByMemberId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantMemberSettlementListByMemberId(Page<MemberSettlementDto> page,
			MemberSettlementDto memberSettlementDto) {
		Page<MemberSettlementDto> goodsdistributionallocationlists = memeberSettlementService
				.getMerchantMemberSettlementListByMemberId(page, memberSettlementDto);
		return new ResponseInfo(goodsdistributionallocationlists);
	}

}
