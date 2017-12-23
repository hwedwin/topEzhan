package com.topaiebiz.member.point.mobile.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.member.mgmt.exception.MemberMgmtExceptionEnum;
import com.topaiebiz.member.point.dto.MemberPointLogDto;
import com.topaiebiz.member.point.dto.MemberPointRuleDto;
import com.topaiebiz.member.point.entity.MemberPointLogEntity;
import com.topaiebiz.member.point.exception.MemberPointExceptionEnum;
import com.topaiebiz.member.point.service.MemberPointService;
import com.topaiebiz.system.moble.security.dto.TokenDto;
import com.topaiebiz.system.moble.security.util.TokenUtil;


/**
 * 
 * Description： 会员积分控制层
 * 
 * 
 * Author Scott.Yang
 * 
 * Date 2017年10月16日 上午9:28:31
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@RestController
@RequestMapping("/app/member/point")
public class MemberPointController {

	@Autowired
	private MemberPointService memberPointService;

	/**
	 * 
	 * Description：会员积分规则列表
	 * 
	 * Author Scott.Yang
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/listMemberPointRule", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo listMemberPointRule(String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		List<MemberPointRuleDto> listMemberPointRuleList = memberPointService.listMemberPointRule();
		return new ResponseInfo(listMemberPointRuleList);
	}

	/**
	 * Description： 添加会员积分获取记录
	 * 
	 * Author Scott.Yang
	 * 
	 * @param id
	 *            会员积分ID
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/saveMemberPointLog", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addMemberPointLog(String token, Long id) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		Long memberId = tokenDetail.getMemberId();
		if (null == id) {
			throw new GlobalException(MemberPointExceptionEnum.MEMBERPOINT_ID_NOT_NULL);
		}
		if (null == memberId) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		memberPointService.saveMemberPointLog(id, memberId);
		return new ResponseInfo();
	}
	
	/**
	 * 
	 * Description： 判断是否可以签到状态  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param token
	 * @param gainType1
	 * @param gainType2
	 * @return
	 */
	@RequestMapping(path = "/getVerificationStatusList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getList(String token, Long gainType1, Long gainType2) {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		Long memberId = tokenDetail.getMemberId();
		if (null == memberId) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		/*** 定义新用户获取积分状态和今天的签到状态 */
		Integer state1 = 0;
		Integer state2 = 0;
		MemberPointLogEntity memberPointLogEntity = memberPointService.selectByGainType1(memberId, gainType1);
		List<MemberPointLogEntity> memberPointLogList = memberPointService.selectByGainType2(memberId, gainType2);
		if(memberPointLogList.isEmpty()) {
			state2 = 0;
		}else {
			MemberPointLogEntity memberPointLog = memberPointLogList.get(memberPointLogList.size() - 1);
			/*** 获取最后一次签到时间 */
			Date createdTime = memberPointLog.getCreatedTime();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			String format = simpleDateFormat.format(createdTime);
			String format2 = simpleDateFormat.format(new Date());
			/** 获取当前时间 */
			if (format.equals(format2)) {
				/** 若已签到 */
				state2 = 1;
			}
		}
		if (memberPointLogEntity != null) {
			state1 = 1;
		}
		Map<String, Object> map = new HashMap<>();
		map.put("state1", state1);
		map.put("state2", state2);
		return new ResponseInfo(map);
	}

	/**
	 * Description： 查询会员积分获取记录
	 * 
	 * Author Scott.Yang
	 * 
	 * @param id
	 *            会员ID
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getMemberPointLog", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMemberPointLog(String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		Long memberId = tokenDetail.getMemberId();
		if (null == memberId) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		List<MemberPointLogDto> memberPointLog = memberPointService.getMemberPointLog(memberId);
		return new ResponseInfo(memberPointLog);
	}

	/**
	 * Description： 查询会员积分支出记录
	 * 
	 * Author Scott.Yang
	 * 
	 * @param id
	 *            会员ID
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getMemberPointSpendingLog", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMemberPointSpendingLog(String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		Long memberId = tokenDetail.getMemberId();
		if (null == memberId) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		List<MemberPointLogDto> memberPointLog = memberPointService.getMemberPointSpendingLog(memberId);
		return new ResponseInfo(memberPointLog);
	}

	/**
	 * Description： 查询会员总积分
	 * 
	 * Author Scott.Yang
	 * 
	 * @param id
	 *            会员ID
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getMemberPointSum", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMemberPointSum(String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		Long memberId = tokenDetail.getMemberId();
		if (null == memberId) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		MemberPointLogDto memberPointLog = memberPointService.getMemberPointSum(memberId);
		return new ResponseInfo(memberPointLog);
	}

}
