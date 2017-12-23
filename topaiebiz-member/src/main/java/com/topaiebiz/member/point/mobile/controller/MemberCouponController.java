package com.topaiebiz.member.point.mobile.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.member.mgmt.exception.MemberMgmtExceptionEnum;
import com.topaiebiz.member.point.dto.MemberCouponDto;
import com.topaiebiz.member.point.dto.MemberPromotionDto;
import com.topaiebiz.member.point.service.MemberCouponService;
import com.topaiebiz.system.moble.security.dto.TokenDto;
import com.topaiebiz.system.moble.security.util.TokenUtil;
/**
 * 
 * Description： 会员优惠券控制层  
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
@RequestMapping("/app/member/coupon")
public class MemberCouponController {
	
	@Autowired
	private MemberCouponService memberCouponService;
	
	/**
	 * Description： 获取会员优惠券
	 * 
	 * Author Scott.Yang
	 * 
	 * @param id
	 * 		会员积分ID
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/saveMemberCoupon",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addMemberCoupon(String token,MemberCouponDto memberCouponDto) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		Long memberId = tokenDetail.getMemberId();
		memberCouponDto.setMemberId(memberId);
		memberCouponDto.setToken(token);
		memberCouponService.saveMemberCoupon(memberCouponDto);
		return new ResponseInfo();
	}
	
	/**
	 * Description： 查询会员优惠券未使用
	 * 
	 * Author Scott.Yang
	 * 
	 * @param id
	 * 		会员ID
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getMemberCoupon",method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMemberCoupon(String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		Long memberId = tokenDetail.getMemberId();
		if(null == memberId) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		List<MemberPromotionDto> MemberCoupon =memberCouponService.getMemberCoupon(memberId);
		return new ResponseInfo(MemberCoupon);
	}
	
	/**
	 * Description： 查询会员优惠券已使用
	 * 
	 * Author Scott.Yang
	 * 
	 * @param id
	 * 		会员ID
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getUsageMemberCoupon",method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getUsageMemberCoupon(String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		Long memberId = tokenDetail.getMemberId();
		if(null == memberId) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		List<MemberPromotionDto> MemberCoupon =memberCouponService.getUsageMemberCoupon(memberId);
		return new ResponseInfo(MemberCoupon);
	}
	
	/**
	 * Description： 查询会员优惠券已过期
	 * 
	 * Author Scott.Yang
	 * 
	 * @param id
	 * 		会员ID
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getExpireMemberCoupon",method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getExpireMemberCoupon(String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		Long memberId = tokenDetail.getMemberId();
		if(null == memberId) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		List<MemberPromotionDto> MemberCoupon =memberCouponService.getExpireMemberCoupon(memberId);
		return new ResponseInfo(MemberCoupon);
	}
	
	/**
	 * Description： 查询会员优惠券总数
	 * 
	 * Author Scott.Yang
	 * 
	 * @param id
	 * 		会员ID
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getMemberCouponSum",method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMemberCouponSum(String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		Long memberId = tokenDetail.getMemberId();
		if(null == memberId) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		Long MemberCouponSum =memberCouponService.getMemberCouponSum(memberId);
		return new ResponseInfo(MemberCouponSum);
	}
	
}
