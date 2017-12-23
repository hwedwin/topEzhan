package com.topaiebiz.member.grade.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.topaiebiz.system.security.util.SecurityContextUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.member.grade.dao.MemberGradeDao;
import com.topaiebiz.member.grade.dto.MemberGradeDto;
import com.topaiebiz.member.grade.entity.MemberGradeEntity;
import com.topaiebiz.member.grade.entity.MemberGradePrivilegeEntity;
import com.topaiebiz.member.grade.exception.MemberGradeExceptionEnum;
import com.topaiebiz.member.grade.service.MemberGradeService;

/**
 * 
 * Description： 会员等级实现类
 * 
 * 
 * Author Scott.Yang
 * 
 * Date 2017年9月26日 下午7:57:49
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
public class MemberGradeServiceIpml implements MemberGradeService {

	@Autowired
	private MemberGradeDao memberGradeDao;
	
	@Override
	public Integer saveMemberGrade(MemberGradeDto memberGrade) throws GlobalException {
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		memberGrade.setStoreId(storeId);
		/** 对会员等级名称进行重复验证 */
		MemberGradeEntity findMemberGradeByName = memberGradeDao.selectMemberGradeByName(memberGrade);
		if (null != findMemberGradeByName) {
			throw new GlobalException(MemberGradeExceptionEnum.MEMBERGRADE_NAME_NOT_REPETITION);
		}
		
		MemberGradeEntity entity = new MemberGradeEntity();
		BeanUtils.copyProperties(memberGrade, entity);
		entity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		entity.setCreatedTime(new Date());
		return memberGradeDao.insert(entity);
	}

	@Override
	public Integer modifyMemberGrade(MemberGradeDto memberGrade) throws GlobalException {
		/** 对会员等级名称进行重复验证 */
		MemberGradeEntity findMemberGradeByName = memberGradeDao.selectMemberGradeByNames(memberGrade);
		if (null != findMemberGradeByName) {
			throw new GlobalException(MemberGradeExceptionEnum.MEMBERGRADE_NAME_NOT_REPETITION);
		}
		MemberGradeEntity memberGradeEntity = memberGradeDao.selectById(memberGrade.getId());
		if(null == memberGrade.getSmallIcon() || memberGrade.getSmallIcon() == "") {
			memberGrade.setSmallIcon(" ");
		}
		BeanUtils.copyProperties(memberGrade, memberGradeEntity);
		memberGradeEntity.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		memberGradeEntity.setLastModifiedTime(new Date());
		return memberGradeDao.updateById(memberGradeEntity);
	}

	@Override
	public Page<MemberGradeDto> listMemberGrade(Page<MemberGradeDto> page, MemberGradeDto memberGradeDto) {
		//根据电话查到会员，获取所属店铺
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		memberGradeDto.setStoreId(storeId);
		page.setRecords(memberGradeDao.selectPageMemberGrade(page, memberGradeDto));
		return page;
	}

	@Override
	public void removeMemberGrades(Long[] id) throws GlobalException {
		for (Long longs : id) {
			/** 通过ID查询对象是否存在 */
			MemberGradeEntity selectMemberGradeById = memberGradeDao.selectMemberGradeById(longs);
			if (null == selectMemberGradeById) {
				throw new GlobalException(MemberGradeExceptionEnum.MEMBERGRADE_ID_NOT_EXIST);
			}
		}
		memberGradeDao.deleteMemberGrades(id);
	}

	@Override
	public Integer saveMemberGradePrivilege(MemberGradePrivilegeEntity memberGradePrivilegeEntity)
			throws GlobalException {
		if (null == memberGradePrivilegeEntity.getId()) {
			throw new GlobalException(MemberGradeExceptionEnum.MEMBERGRADE_ID_NOT_EXIST);
		}
		memberGradePrivilegeEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		memberGradePrivilegeEntity.setCreatedTime(new Date());
		return memberGradeDao.insertMemberGradePrivilege(memberGradePrivilegeEntity);
	}

	@Override
	public MemberGradeEntity findMemberGradesById(Long id) throws GlobalException {
		return memberGradeDao.selectById(id);
	}

	@Override
	public List<MemberGradeEntity> getListOfMemberGrade() {
		return memberGradeDao.selectListOfMemberGrade();
	}

}
