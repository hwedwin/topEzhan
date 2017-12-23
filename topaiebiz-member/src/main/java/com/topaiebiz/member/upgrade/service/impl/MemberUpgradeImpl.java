package com.topaiebiz.member.upgrade.service.impl;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.member.mgmt.dao.MemberMgmtDao;
import com.topaiebiz.member.mgmt.entity.MemberEntity;
import com.topaiebiz.member.point.exception.MemberPointExceptionEnum;
import com.topaiebiz.member.upgrade.dao.MemberUpgradeLogDao;
import com.topaiebiz.member.upgrade.dao.MemberUpgradeRuleDao;
import com.topaiebiz.member.upgrade.entity.MemberUpgradeLogEntity;
import com.topaiebiz.member.upgrade.entity.MemberUpgradeRuleEntity;
import com.topaiebiz.member.upgrade.service.MemberUpgradeService;
@Service
@Transactional
public class MemberUpgradeImpl implements MemberUpgradeService {
	@Autowired
	private MemberUpgradeRuleDao memberUpgradeRuleDao;
	/** 会员成长分记录*/
	@Autowired
	private MemberUpgradeLogDao memberUpgradeLogDao;
	/** 会员管理*/
	@Autowired
	private MemberMgmtDao memberMgmtDao;
	
	@Override
	public void saveMemberUpgraderadeLog(Long id, Long memberId,Long promotionId) throws GlobalException {
		MemberUpgradeRuleEntity memberUpgradeRule = memberUpgradeRuleDao.selectById(id);
		if(null == memberUpgradeRule) {
			throw new GlobalException(MemberPointExceptionEnum.MEMBERPOINT_ID_NOT_EXIST);
		}
		MemberUpgradeLogEntity memberUpgradeLog = new MemberUpgradeLogEntity();
		memberUpgradeLog.setMemberId(memberId);
		memberUpgradeLog.setPromotionId(promotionId);
		memberUpgradeLog.setUpgradeRuleId(id);
		memberUpgradeLog.setUpgradeScore(memberUpgradeRule.getUpgradeScore());
		memberUpgradeLog.setFinishTime(new Date());
		memberUpgradeLog.setCreatedTime(new Date());
		memberUpgradeLog.setCreatorId(memberId);
		memberUpgradeLogDao.insert(memberUpgradeLog);
		MemberEntity memberEntity = memberMgmtDao.selectById(memberId);
		Long upgradeScore = memberEntity.getUpgradeScore();
		if (upgradeScore == null) {
			upgradeScore = new Long("0");
		}
		memberEntity.setUpgradeScore(upgradeScore+memberUpgradeRule.getUpgradeScore());
		memberEntity.setLastModifierId(memberId);
		memberEntity.setCreatedTime(new Date());
		memberMgmtDao.updateById(memberEntity);
	}
}
