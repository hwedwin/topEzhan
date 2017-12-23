package com.topaiebiz.member.point.service;

import java.util.List;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.member.point.dto.MemberCouponDto;
import com.topaiebiz.member.point.dto.MemberPromotionDto;

public interface MemberCouponService {
	/**
	 * 
	 * Description：  获取会员优惠券  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberCouponDto
	 * @throws GlobalException
	 */
	void saveMemberCoupon(MemberCouponDto memberCouponDto) throws GlobalException;
	/**
	 * 
	 * Description：提供订单--->优惠券使用(更改状态)  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberCouponDto
	 * @throws GlobalException
	 */
	void usageMemberCoupon(MemberCouponDto memberCouponDto) throws GlobalException;
	/**
	 * 
	 * Description：  查询会员优惠券未使用  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberId
	 * @return
	 */
	List<MemberPromotionDto> getMemberCoupon(Long memberId);
	/**
	 * 
	 * Description：查询会员优惠券已使用  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberId
	 * @return
	 */
	List<MemberPromotionDto> getUsageMemberCoupon(Long memberId);
	/**
	 * 
	 * Description： 查询会员优惠券已过期  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberId
	 * @return
	 */
	List<MemberPromotionDto> getExpireMemberCoupon(Long memberId);
	/**
	 * 
	 * Description： 查询会员优惠券总数  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberId
	 * @return
	 */
	Long getMemberCouponSum(Long memberId);

}
