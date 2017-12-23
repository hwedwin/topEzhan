package com.topaiebiz.distribution.member.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.distribution.member.dto.MemberDisributionAndItemDto;
import com.topaiebiz.distribution.member.dto.MemberDistributionAllocationDto;
import com.topaiebiz.distribution.member.dto.MemberDistributionLogDto;
import com.topaiebiz.distribution.member.entity.MemberDistributionAllocationEntity;
import com.topaiebiz.distribution.member.service.MemberDistributionAllocationService;

/**
 * Description：会员分销控制层。
 * 
 * Author Harry
 * 
 * Date 2017年10月5日 下午3:30:34
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@RestController
@RequestMapping("/distribution/member")
public class MemberDistributionAllocationController {

	@Autowired
	private MemberDistributionAllocationService memberDistributionAllocationService;

	/**
	 * Description： 添加(配置)会员分销比例。
	 * 
	 * Author Harry
	 * 
	 * @param skuId
	 * @param memberDistributionAllocationDtos
	 *            会员分销Dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/addMemberDistributionAllocation", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addMemberDistributionAllocation(
			@RequestBody MemberDisributionAndItemDto memberDisributionAndItemDto) throws GlobalException {
		/** 获取参数ItemId */
		Long itemId = memberDisributionAndItemDto.getItemId();
		/** 创建新会员分销实体类 */
		List<MemberDistributionAllocationEntity> memberDistributionAllocationEntitis = new ArrayList<MemberDistributionAllocationEntity>();
		/** 获取会员分销Dto */
		List<MemberDistributionAllocationDto> memberDistributionAllocationDtos = memberDisributionAndItemDto
				.getMemberDistributionAllocationDtos();
		for (MemberDistributionAllocationDto memberDistributionAllocationDto : memberDistributionAllocationDtos) {
			MemberDistributionAllocationEntity memberDistributionAllocationEntity = new MemberDistributionAllocationEntity();
			BeanUtils.copyProperties(memberDistributionAllocationDto, memberDistributionAllocationEntity);
			memberDistributionAllocationEntitis.add(memberDistributionAllocationEntity);
		}
		return new ResponseInfo(memberDistributionAllocationService.saveMemberDistributionAllocation(itemId,
				memberDistributionAllocationEntitis));
	}
	/**
	 * Description：根据itemId查询会员分销比例(回显数据)。
	 * 
	 * Author Harry   
	 * 
	 * @param itemId
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/findMemberDistributionAllocationByItemId", method = RequestMethod.GET)
	@ResponseBody
	private ResponseInfo findMemberDistributionAllocationByItemId(Long itemId) throws GlobalException {
		List<MemberDistributionAllocationDto> memberDistributionAllocationDtos = memberDistributionAllocationService
				.findMemberDistributionAllocationByItemId(itemId);
		return new ResponseInfo(memberDistributionAllocationDtos);
	}	
	
	/**
	 * 
	 * Description：定时任务计算会员的分销记录。
	 * 
	 * Author Harry   
	 * 
	 * @return
	 * @throws GlobalException
	 */
	/*@Scheduled(cron = "0 10 10 ? * * ")
	@RequestMapping(path = "/addMemberDistributionLog", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addMemberDistributionLog() throws GlobalException {
		return new ResponseInfo(memberDistributionAllocationService.saveMemberDistributionLog());
	}*/

	/**
	 * Description：平台端会员分销记录列表分页加搜索。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 *            分页单位
	 * @param memberDistributionLogDto
	 *            会员分销记录Dto
	 * @return
	 */
	@RequestMapping(path = "/getMemberDistributionLogList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMemberDistributionLogList(Page<MemberDistributionLogDto> page,
			MemberDistributionLogDto memberDistributionLogDto) {
		Page<MemberDistributionLogDto> memberdistributionlist = memberDistributionAllocationService
				.getMemberDistributionLogList(page, memberDistributionLogDto);
		return new ResponseInfo(memberdistributionlist);

	}

	/**
	 * Description： 根据会员id查询会员子集与下级分销记录列表分页加搜索。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * @param goodsDistributionLogDto
	 * @return
	 */
	@RequestMapping(path = "/findMemberDistributionListByMemberId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo findMembersDistributionListByMemberId(Page<MemberDistributionLogDto> page,
			MemberDistributionLogDto memberDistributionLogDto) {
		Page<MemberDistributionLogDto> memberdistributionlist = memberDistributionAllocationService
				.findMembersDistributionListByMemberId(page, memberDistributionLogDto);
		return new ResponseInfo(memberdistributionlist);
	}

	/**
	 * Description： 商家端会员分销记录列表分页加搜索。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * @param memberDistributionLogDto
	 * @return
	 */
	@RequestMapping(path = "/getMerchantMemberDistributionLogList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantMemberDistributionLogList(Page<MemberDistributionLogDto> page,
			MemberDistributionLogDto memberDistributionLogDto) {
		Page<MemberDistributionLogDto> memberdistributionlist = memberDistributionAllocationService
				.getMerchantMemberDistributionLogList(page, memberDistributionLogDto);
		return new ResponseInfo(memberdistributionlist);
	}

	/**
	 * Description：商家端根据memberId查询会员分销商品的记录。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * @param memberDistributionLogDto
	 * @return
	 */
	@RequestMapping(path = "/findMemberDistributionListByMemberIds", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo findMemberDistributionListByMemberId(Page<MemberDistributionLogDto> page,
			MemberDistributionLogDto memberDistributionLogDto) {
		Page<MemberDistributionLogDto> memberdistributionlist = memberDistributionAllocationService
				.findMemberDistributionListByMemberId(page, memberDistributionLogDto);
		return new ResponseInfo(memberdistributionlist);
	}

}
