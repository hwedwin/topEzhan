package com.topaiebiz.member.point.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.member.point.dto.MemberCouponDto;
import com.topaiebiz.member.point.dto.MemberPromotionDto;
import com.topaiebiz.member.point.entity.MemberCouponEntity;
/**
 * 
 * Description： 描述会员优惠券的接口，并向会员优惠券控制层提供相关的方法。 
 * 
 * Author Scott.Yang
 *    
 * Date 2017年11月28日 下午1:37:49 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface MemberCouponDao extends BaseDao<MemberCouponEntity>{
	/**
	 * 
	 * Description：提供订单--->优惠券使用(更改状态)  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberCouponDto
	 */
	void usageMemberCoupon(MemberCouponDto memberCouponDto);
	/**
	 * 
	 * Description：查询会员优惠券未使用    
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberId
	 * @return
	 */
	List<MemberPromotionDto> selectMemberCoupon(Long memberId);
	/**
	 * 
	 * Description： 查询会员优惠券已使用   
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberId
	 * @return
	 */
	List<MemberPromotionDto> selectUsageMemberCoupon(Long memberId);
	/**
	 * 
	 * Description： 查询会员优惠券已过期   
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberId
	 * @return
	 */
	List<MemberPromotionDto> selectExpireMemberCoupon(Long memberId);
	/**
	 * 
	 * Description： 查询会员优惠券总数 
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberId
	 * @return
	 */
	Long selectMemberCouponSum(Long memberId);
	/**
	 * 
	 * Description 根据会员编号和活动id查询会员领取记录
	 * 
	 * Author Joe   
	 * 
	 * @param memberId
	 * @param id
	 * @return
	 */
	List<MemberCouponDto> selectCouponByMemberIdAndPromotionId(@Param(value = "memberId")Long memberId, @Param(value = "couponId")Long couponId);

}
