package com.topaiebiz.member.point.service.impl;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.member.mgmt.dao.MemberMgmtDao;
import com.topaiebiz.member.mgmt.entity.MemberEntity;
import com.topaiebiz.member.mgmt.exception.MemberMgmtExceptionEnum;
import com.topaiebiz.member.point.dao.MemberPointDao;
import com.topaiebiz.member.point.dao.MemberPointRuleDao;
import com.topaiebiz.member.point.dao.MemberPointUsageLogDao;
import com.topaiebiz.member.point.dto.MemberPointLogDto;
import com.topaiebiz.member.point.dto.MemberPointRuleDto;
import com.topaiebiz.member.point.entity.MemberPointLogEntity;
import com.topaiebiz.member.point.entity.MemberPointRuleEntity;
import com.topaiebiz.member.point.entity.MemberPointUsageLogEntity;
import com.topaiebiz.member.point.exception.MemberPointExceptionEnum;
import com.topaiebiz.member.point.service.MemberPointService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MemberPointServiceImpl implements MemberPointService {

	@Autowired
	private MemberPointDao memberPointDao;
	/** 会员积分积分规则 */
	@Autowired
	private MemberPointRuleDao memberPointRuleDao;
	/*** 会员管理 */
	@Autowired
	private MemberMgmtDao memberMgmtDao;
	/** 会员使用记录 */
	@Autowired
	private MemberPointUsageLogDao memberPointUsageLogDao;

	@Override
	public List<MemberPointRuleDto> listMemberPointRule() {
		return memberPointRuleDao.selectMemberPointRule();
	}

	@Override
	public void saveMemberPointLog(Long id, Long MemberId) throws GlobalException {
		MemberPointRuleEntity MemberPointRule = memberPointRuleDao.selectById(id);
		MemberPointLogEntity memberPointLogEntity = null;
		if (null == MemberPointRule) {
			throw new GlobalException(MemberPointExceptionEnum.MEMBERPOINT_ID_NOT_EXIST);
		}
		memberPointLogEntity = new MemberPointLogEntity();
		memberPointLogEntity.setGainType(id);
		memberPointLogEntity.setMemberId(MemberId);
		if (id == 1) {
			MemberPointLogEntity member = memberPointDao.selectByGainType1(memberPointLogEntity);
			if (null != member && MemberPointRule.getRepeatState() == 0) {
				throw new GlobalException(MemberPointExceptionEnum.MEMBERPOINT_ALREADY_RECEIVED);
			} else {
				MemberPointLogEntity memberPointLog = new MemberPointLogEntity();
				memberPointLog.setGainType(MemberPointRule.getId());
				memberPointLog.setMemberId(MemberId);
				memberPointLog.setGainScore(MemberPointRule.getValue());
				memberPointLog.setCreatedTime(new Date());
				memberPointLog.setCreatorId(MemberId);
				memberPointDao.insert(memberPointLog);
				MemberEntity memberEntity = memberMgmtDao.selectById(MemberId);
				Long ownScore = memberEntity.getOwnScore();
				if (ownScore == null) {
					ownScore = new Long("0");
				}
				memberEntity.setOwnScore(ownScore+MemberPointRule.getValue());
				memberEntity.setLastModifierId(MemberId);
				memberEntity.setCreatedTime(new Date());
				memberMgmtDao.updateById(memberEntity);
			}
		}
		if (id == 2) {
			List<MemberPointLogEntity> memberPointLogList = memberPointDao.selectByGainType2(memberPointLogEntity);
			if (memberPointLogList.isEmpty()) {
				MemberPointLogEntity memberPointLog = new MemberPointLogEntity();
				memberPointLog.setGainType(MemberPointRule.getId());
				memberPointLog.setMemberId(MemberId);
				memberPointLog.setGainScore(MemberPointRule.getValue());
				memberPointLog.setCreatedTime(new Date());
				memberPointLog.setCreatorId(MemberId);
				memberPointDao.insert(memberPointLog);
				MemberEntity memberEntity = memberMgmtDao.selectById(MemberId);
				Long ownScore = memberEntity.getOwnScore();
				if (ownScore == null) {
					ownScore = new Long("0");
				}
				memberEntity.setOwnScore(ownScore+MemberPointRule.getValue());
				memberEntity.setLastModifierId(MemberId);
				memberEntity.setCreatedTime(new Date());
				memberMgmtDao.updateById(memberEntity);
			} else {
				MemberPointLogEntity memberPointLog = memberPointLogList.get(memberPointLogList.size() - 1);
				/*** 获取最后一次签到时间 */
				Date createdTime = memberPointLog.getCreatedTime();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
				String format = simpleDateFormat.format(createdTime);
				String format2 = simpleDateFormat.format(new Date());
				/** 获取当前时间 */
				if (format.equals(format2)) {
					throw new GlobalException(MemberPointExceptionEnum.MEMBERPOINT_ALREADY_RECEIVED_DAY);
				} else {
					memberPointLog = new MemberPointLogEntity();
					memberPointLog.setGainType(MemberPointRule.getId());
					memberPointLog.setMemberId(MemberId);
					memberPointLog.setGainScore(MemberPointRule.getValue());
					memberPointLog.setCreatedTime(new Date());
					memberPointLog.setCreatorId(MemberId);
					memberPointDao.insert(memberPointLog);
					MemberEntity memberEntity = memberMgmtDao.selectById(MemberId);
					Long ownScore = memberEntity.getOwnScore();
					if (ownScore == null) {
						ownScore = new Long("0");
					}
					memberEntity.setOwnScore(ownScore+MemberPointRule.getValue());
					memberEntity.setLastModifierId(MemberId);
					memberEntity.setCreatedTime(new Date());
					memberMgmtDao.updateById(memberEntity);
				}
			}
		}

	}

	@Override
	public MemberPointLogDto getMemberPointSum(Long memberId) {
		return memberPointDao.selectMemberPointSum(memberId);
	}

	@Override
	public List<MemberPointLogDto> getMemberPointLog(Long memberId) {
		return memberPointDao.selectMemberPointLog(memberId);
	}

	@Override
	public void subtractMemberPoint(Long memberId, Long orderId, Long usageScore, Double deductibleAmount)
			throws GlobalException {
		if (null == memberId) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		if (null == orderId) {
			throw new GlobalException(MemberPointExceptionEnum.MEMBERPOINT_ORDERID_NOT_NULL);
		}
		if (null == usageScore) {
			throw new GlobalException(MemberPointExceptionEnum.MEMBERPOINT_USAGESCORE_NOT_NULL);
		}
		if (null == deductibleAmount) {
			throw new GlobalException(MemberPointExceptionEnum.MEMBERPOINT_DEDUCTIBLEAMOUNT_NOT_NULL);
		}
		MemberEntity member = memberMgmtDao.selectById(memberId);
		if (null == member) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_EXIST);
		}
		Long ownScore = new Long("0");
		ownScore = member.getOwnScore();
		int compareToResult = ownScore.compareTo(usageScore);
		if (compareToResult == 1 || compareToResult == 0) {
			Long subtract = ownScore - usageScore;
			member.setOwnScore(subtract);
			Long usedScore = member.getUsedScore();
			if (usedScore == null) {
				usedScore = new Long("0");
			}
			member.setUsedScore(usedScore + usageScore);
			memberMgmtDao.updateById(member);
			MemberPointUsageLogEntity memberPointUsageLogEntity = new MemberPointUsageLogEntity();
			memberPointUsageLogEntity.setMemberId(memberId); 
			memberPointUsageLogEntity.setOrderId(orderId);
			memberPointUsageLogEntity.setUsageScore(usageScore);
			memberPointUsageLogEntity.setDeductibleAmount(deductibleAmount);
			memberPointUsageLogEntity.setCreatedTime(new Date());
			memberPointUsageLogEntity.setCreatorId(memberId);
			memberPointUsageLogDao.insert(memberPointUsageLogEntity);
		} else {
			throw new GlobalException(MemberPointExceptionEnum.MEMBERPOINT_USAGESCORE_NOT_MORE);
		}

	}

	@Override
	public void returnMemberPoint(Long orderId) throws GlobalException {
		if (null == orderId) {
			throw new GlobalException(MemberPointExceptionEnum.MEMBERPOINT_ORDERID_NOT_NULL);
		}
		MemberPointUsageLogEntity memberPointUsageLogEntity = memberPointUsageLogDao.selectByOrderId(orderId);
		if (null != memberPointUsageLogEntity) {
			Long memberId = memberPointUsageLogEntity.getMemberId();
			MemberEntity memberEntity = memberMgmtDao.selectById(memberId);
			memberEntity.setOwnScore(memberEntity.getOwnScore() + memberPointUsageLogEntity.getUsageScore());
			memberEntity.setUsedScore(memberEntity.getUsedScore() - memberPointUsageLogEntity.getUsageScore());
			memberEntity.setLastModifiedTime(new Date());
			memberEntity.setLastModifierId(memberId);
			memberMgmtDao.updateById(memberEntity);
			memberPointUsageLogDao.deletememberPointUsageLog(memberPointUsageLogEntity.getId());
		}
	}

	@Override
	public void returnMemberPoint(MemberPointLogDto memberPointLogDto) throws GlobalException {
		MemberEntity memberEntity = memberMgmtDao.selectById(memberPointLogDto.getMemberId());
		memberEntity.setOwnScore(memberEntity.getOwnScore() + memberPointLogDto.getIntegral());
		memberEntity.setUsedScore(memberEntity.getUsedScore() - memberPointLogDto.getIntegral());
		memberEntity.setLastModifiedTime(new Date());
		memberEntity.setLastModifierId(memberPointLogDto.getMemberId());
		MemberPointLogDto memberPointLog = memberPointDao.returnMemberPointLog(memberPointLogDto);
		Long gainScore = memberPointLog.getGainScore();
		Integer integral = memberPointLogDto.getIntegral();
		memberPointLog.setGainScore(gainScore - integral);
		MemberPointLogEntity memberPointLogEntity = memberPointDao.selectById(memberPointLog.getId());
		BeanUtils.copyProperties(memberPointLog, memberPointLogEntity);
		memberPointDao.updateById(memberPointLogEntity);
		memberMgmtDao.updateById(memberEntity);
	}

	@Override
	public void saveMemberPointLogForOrder(Long memberId, Long orderId, Double price) throws GlobalException {
		if (null == memberId) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		if (null == orderId) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ORDERID_NOT_NULL);
		}
		if (null == price) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_PRICE_NOT_NULL);
		}
		MemberPointRuleEntity MemberPointRule = memberPointRuleDao.selectById(4);
		if (null == MemberPointRule) {
			throw new GlobalException(MemberPointExceptionEnum.MEMBERPOINT_ID_NOT_EXIST);
		}
		Double needValue = MemberPointRule.getNeedValue();
		int multiply = (int) (price / needValue);
		Integer a = (Integer) multiply;
		long parseLong = Long.parseLong(a.toString());
		long deservedPoints = parseLong * MemberPointRule.getValue();
		MemberPointLogEntity memberPointLog = new MemberPointLogEntity();
		memberPointLog.setGainType(MemberPointRule.getId());
		memberPointLog.setOrderId(orderId);
		memberPointLog.setCostMoney(price);
		memberPointLog.setMemberId(memberId);
		memberPointLog.setGainScore(deservedPoints);
		memberPointLog.setCreatedTime(new Date());
		memberPointLog.setCreatorId(memberId);
		memberPointDao.insert(memberPointLog);
		MemberEntity memberEntity = memberMgmtDao.selectById(memberId);
		Long ownScore = memberEntity.getOwnScore();
		if (ownScore == null) {
			ownScore = new Long("0");
		}
		memberEntity.setOwnScore(ownScore+deservedPoints);
		memberEntity.setLastModifierId(memberId);
		memberEntity.setCreatedTime(new Date());
		memberMgmtDao.updateById(memberEntity);
	}

	@Override
	public List<MemberPointLogDto> getMemberPointSpendingLog(Long memberId) {
		return memberPointUsageLogDao.getMemberPointSpendingLog(memberId);
	}

	@Override
	public MemberPointLogEntity selectByGainType1(Long memberId, Long gainType1) {
		MemberPointLogEntity memberPointLogEntity = new MemberPointLogEntity();
		memberPointLogEntity.setMemberId(memberId);
		memberPointLogEntity.setGainType(gainType1);
		return memberPointDao.selectByGainType1(memberPointLogEntity);
	}

	@Override
	public List<MemberPointLogEntity> selectByGainType2(Long memberId, Long gainType2) {
		MemberPointLogEntity memberPointLogEntity = new MemberPointLogEntity();
		memberPointLogEntity.setMemberId(memberId);
		memberPointLogEntity.setGainType(gainType2);
		return memberPointDao.selectByGainType2(memberPointLogEntity);
	}

}
