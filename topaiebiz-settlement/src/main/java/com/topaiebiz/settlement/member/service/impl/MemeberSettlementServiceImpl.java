package com.topaiebiz.settlement.member.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.topaiebiz.system.security.util.SecurityContextUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.distribution.member.dao.MemberDistributionLogDao;
import com.topaiebiz.distribution.member.dto.MemberDistributionLogDto;
import com.topaiebiz.goods.category.backend.dao.BackendCategoryDao;
import com.topaiebiz.goods.category.backend.dto.MerchantStoreDto;
import com.topaiebiz.member.mgmt.entity.MemberEntity;
import com.topaiebiz.settlement.member.dao.MemberWithdrawalLogDao;
import com.topaiebiz.settlement.member.dao.MemeberSettlementDao;
import com.topaiebiz.settlement.member.dto.MemberSettlementDto;
import com.topaiebiz.settlement.member.entity.MemberSettlementEntity;
import com.topaiebiz.settlement.member.entity.MemberWithdrawalLogEntity;
import com.topaiebiz.settlement.member.exception.MemeberSettlementExceptionEnum;
import com.topaiebiz.settlement.member.service.MemeberSettlementService;
import com.topaiebiz.settlement.store.dao.StoreSettlementDao;

/**
 * Description： 会员结算实现类
 * 
 * Author Harry
 * 
 * Date 2017年10月31日 下午5:33:54
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
public class MemeberSettlementServiceImpl implements MemeberSettlementService {

	@Autowired
	private MemeberSettlementDao memeberSettlementDao;

	@Autowired
	private StoreSettlementDao storeSettlementDao;

	@Autowired
	private BackendCategoryDao backendCategoryDao;

	@Autowired
	private MemberDistributionLogDao memberDistributionLogDao;
	
	@Autowired
	private MemberWithdrawalLogDao memberWithdrawalLogDao;

	@Override
	public Page<MemberSettlementDto> getPlatMemberSettlementList(Page<MemberSettlementDto> page,
			MemberSettlementDto memberSettlementDto) {
		List<MemberSettlementDto> platmemberSettlementDtoList = memeberSettlementDao
				.selectPlatMemberSettlementList(page, memberSettlementDto);
		if (!(platmemberSettlementDtoList == null || platmemberSettlementDtoList.size() == 0)) {
			for (MemberSettlementDto memberSettlementDtos : platmemberSettlementDtoList) {
				Long memberId = memberSettlementDtos.getMemberId();
				if (memberId != null) {
					// 根据会员id查询上级会员
					String parentName = memeberSettlementDao.selectParentNameByMemberId(memberId);
					memberSettlementDtos.setParentName(parentName);
				}

			}
		}
		page.setRecords(platmemberSettlementDtoList);
		return page;
	}

	@Override
	public Page<MemberSettlementDto> getPlatMemberSettlementListByMemberId(Page<MemberSettlementDto> page,
			MemberSettlementDto memberSettlementDto) {
		List<MemberSettlementDto> platmemberSettlementDtoLists = memeberSettlementDao
				.selectPlatMemberSettlementListByMemberId(page, memberSettlementDto);
		page.setRecords(platmemberSettlementDtoLists);
		return page;
	}

	@Override
	public Page<MemberSettlementDto> getMerchantMemberSettlementList(Page<MemberSettlementDto> page,
			MemberSettlementDto memberSettlementDto) {
		String telephone = SecurityContextUtils.getCurrentSystemUser().getMobilePhone();
		MerchantStoreDto merchantStoreDto = backendCategoryDao.getMemberByuserTelephone(telephone);
		// 获得到店铺的id
		Long merchantId = merchantStoreDto.getMerchantId();
		memberSettlementDto.setMerchantId(merchantId);
		List<MemberSettlementDto> merchantMemberSettlementList = memeberSettlementDao
				.selectMerchantMemberSettlementList(page, memberSettlementDto);
		page.setRecords(merchantMemberSettlementList);
		return page;
	}

	@Override
	public Page<MemberSettlementDto> getMerchantMemberSettlementListByMemberId(Page<MemberSettlementDto> page,
			MemberSettlementDto memberSettlementDto) {
		List<MemberSettlementDto> merchantMemberSettlementLists = memeberSettlementDao
				.selectMerchantMemberSettlementListByMemberId(page, memberSettlementDto);
		if (!(merchantMemberSettlementLists == null || merchantMemberSettlementLists.size() == 0)) {
			for (MemberSettlementDto memberSettlementDto2 : merchantMemberSettlementLists) {
				Long skuId = memberSettlementDto2.getSkuId();
				Long storeId = memberSettlementDto2.getStoreId();
				/** 根据skuId,storeId查询店铺分销金额，会员分销金额 */
				Double storeDistributionPrice = storeSettlementDao.selectGoodsDistributionPrice(skuId, storeId);
				if (storeDistributionPrice != null) {
					memberSettlementDto2.setStoreDistributionPrice(storeDistributionPrice);
				}
				Double memberDistributionPrice = storeSettlementDao.selectMemberDistributionPrice(skuId, storeId);
				if (memberDistributionPrice != null) {
					memberSettlementDto2.setMemberDistributionPrice(memberDistributionPrice);
				}
			}
		}
		page.setRecords(merchantMemberSettlementLists);
		return page;
	}

	@Override
	public Integer saveMemeberSettlement() {
		Integer i = 0;
		/** 查询会员分销记录所有会员，以及会员的分销金额 */
		List<MemberDistributionLogDto> memberDistributionDistriPriceList = memberDistributionLogDao
				.selectDistributionDistriPrice();
		if(!(memberDistributionDistriPriceList ==null || memberDistributionDistriPriceList.size()==0)) {
			for (MemberDistributionLogDto memberDistributionLogDtos : memberDistributionDistriPriceList) {
				MemberSettlementEntity memberSettlementEntity = new MemberSettlementEntity();
				Long memberId = memberDistributionLogDtos.getMemberId();
				Double notWithdrawalSum = 0.0;
				/**会员分销总金额*/
				Double totalSum = memberDistributionLogDtos.getDistriPrice();
				/**根据会员id查询可提现金额*/
				Double allowWithdrawalSum =	memberDistributionLogDao.selectAllowWithdrawalSum(memberId);
				/**已提现金额*/
				Double yetWithdrawalSum = memberWithdrawalLogDao.selectWithdrawalSum(memberId);
				if(totalSum >= allowWithdrawalSum) {
					/**会员不可提现的金额*/
					notWithdrawalSum = totalSum - allowWithdrawalSum;
				}
				memberSettlementEntity.setMemberId(memberId);
				memberSettlementEntity.setAllowWithdrawalSum(allowWithdrawalSum);;
				memberSettlementEntity.setNotWithdrawalSum(notWithdrawalSum);
				memberSettlementEntity.setStatus(3);
				memberSettlementEntity.setYetWithdrawalSum(yetWithdrawalSum);
				memberSettlementEntity.setTotalSum(totalSum);
				memberSettlementEntity.setCreatedTime(new Date());
				i = memeberSettlementDao.insert(memberSettlementEntity);
			}
		}
		return i;
	}

	@Override
	public Integer saveMemberWithdrawalLogDto(MemberWithdrawalLogEntity memberWithdrawalLogEntity)throws GlobalException {
		MemberEntity memberEntity = memberWithdrawalLogDao.selectMemberByMemberId(memberWithdrawalLogEntity.getMemberId() );
		if(memberEntity == null) {
			throw new GlobalException(MemeberSettlementExceptionEnum.MEMBER_ID_NOT_EXIST);
		}
		/** 可提现的钱*/
		Double allowWithdrawalSum =	memberDistributionLogDao.selectAllowWithdrawalSum(memberWithdrawalLogEntity.getMemberId());
		/** 判断体现的钱是否大于可提现的钱*/
		if(memberWithdrawalLogEntity.getWithdrawalSum() > allowWithdrawalSum) {
			throw new GlobalException(MemeberSettlementExceptionEnum.YETWITHDRAWALSUM_ALLOWWITHDRAWALSUM);
		}
		memberWithdrawalLogEntity.setCreatedTime(new Date());
		memberWithdrawalLogEntity.setWithdrawalTime(new Date());
		return memberWithdrawalLogDao.insert(memberWithdrawalLogEntity);
	}

}
