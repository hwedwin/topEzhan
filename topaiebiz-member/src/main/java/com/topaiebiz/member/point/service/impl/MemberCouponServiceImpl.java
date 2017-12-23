package com.topaiebiz.member.point.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.member.mgmt.exception.MemberMgmtExceptionEnum;
import com.topaiebiz.member.point.dao.MemberCouponDao;
import com.topaiebiz.member.point.dto.MemberCouponDto;
import com.topaiebiz.member.point.dto.MemberPromotionDto;
import com.topaiebiz.member.point.entity.MemberCouponEntity;
import com.topaiebiz.member.point.exception.MemberCouponExceptionEnum;
import com.topaiebiz.member.point.service.MemberCouponService;
import com.topaiebiz.system.moble.security.dto.TokenDto;
import com.topaiebiz.system.moble.security.util.TokenUtil;
@Service
public class MemberCouponServiceImpl implements MemberCouponService {
	
	@Autowired
	private MemberCouponDao memberCouponDao;

	@Override
	public void saveMemberCoupon(MemberCouponDto memberCouponDto) throws GlobalException {
		TokenUtil.verifyToken(memberCouponDto.getToken());
		TokenDto tokenDetail = TokenUtil.getTokenDetail(memberCouponDto.getToken());
		Long storeId = tokenDetail.getStoreId();
		if (null == memberCouponDto.getCouponId()) {
			throw new GlobalException(MemberCouponExceptionEnum.MEMBERCONPON_ID_NOT_NULL);
		}
		if(null == memberCouponDto.getMemberId()) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		MemberCouponEntity memberCoupon = new MemberCouponEntity();
		memberCouponDto.setUsageState(0);
		memberCouponDto.setCreatedTime(new Date());
		memberCouponDto.setCreatorId(memberCouponDto.getMemberId());
		memberCouponDto.setStoreId(storeId);
		memberCouponDto.setReceiverTime(new Date());
		BeanUtils.copyProperties(memberCouponDto, memberCoupon);
		memberCouponDao.insert(memberCoupon);
	}

	@Override
	public void usageMemberCoupon(MemberCouponDto memberCouponDto) throws GlobalException {
		if(memberCouponDto.getCouponId() == null) {
			throw new GlobalException(MemberCouponExceptionEnum.MEMBERCONPON_ID_NOT_NULL);
		}
		if(memberCouponDto.getMemberId() == null) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		memberCouponDao.usageMemberCoupon(memberCouponDto);
		
	}

	@Override
	public List<MemberPromotionDto> getMemberCoupon(Long memberId) {
		
		return memberCouponDao.selectMemberCoupon(memberId);
	}

	@Override
	public List<MemberPromotionDto> getUsageMemberCoupon(Long memberId) {
		return memberCouponDao.selectUsageMemberCoupon(memberId);
	}

	@Override
	public List<MemberPromotionDto> getExpireMemberCoupon(Long memberId) {
		return memberCouponDao.selectExpireMemberCoupon(memberId);
	}

	@Override
	public Long getMemberCouponSum(Long memberId) {
		return memberCouponDao.selectMemberCouponSum(memberId);
	}
}
