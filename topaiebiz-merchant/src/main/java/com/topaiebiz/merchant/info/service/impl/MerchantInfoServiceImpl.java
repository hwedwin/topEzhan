package com.topaiebiz.merchant.info.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.topaiebiz.system.security.dao.SystemUserRoleDao;
import com.topaiebiz.system.security.util.SecurityContextUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.member.mgmt.dao.MemberMgmtDao;
import com.topaiebiz.member.mgmt.entity.MemberEntity;
import com.topaiebiz.merchant.enter.dao.StoreInfoDao;
import com.topaiebiz.merchant.info.dao.MerchantInfoDao;
import com.topaiebiz.merchant.info.dto.MerchantInfoDto;
import com.topaiebiz.merchant.info.dto.MerchantInfoListDto;
import com.topaiebiz.merchant.info.dto.StoreInfoDetailDto;
import com.topaiebiz.merchant.info.entity.MerchantInfoEntity;
import com.topaiebiz.merchant.info.entity.StoreInfoEntity;
import com.topaiebiz.merchant.info.exception.MerchantInfoException;
import com.topaiebiz.merchant.info.service.MerchantInfoService;

/**
 * Description: 商家管理业务实现类
 * 
 * Author : Anthony
 * 
 * Date :2017年9月27日 下午1:25:54
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
public class MerchantInfoServiceImpl implements MerchantInfoService {

	@Autowired
	private MerchantInfoDao merchantInfoDao;
	
	@Autowired
	private StoreInfoDao storeInfoDao;
	
	@Autowired
	private MemberMgmtDao memberMgmtDao;
	
	@Autowired
	private SystemUserRoleDao systemUserRoleDao;

	@Override
	public Integer saveMerchantInfo(MerchantInfoEntity entity) throws GlobalException {
		/** 联系人手机号进行重复验证 */
		MerchantInfoEntity findMerchantInfoBycontactTele = merchantInfoDao
				.selectMerchantInfoBycontactTele(entity.getContactTele());
		if (findMerchantInfoBycontactTele != null) {
			throw new GlobalException(MerchantInfoException.MERCHANTINFO_CONTACTELE_NOT_REPETITION);
		}
		entity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		entity.setCreatedTime(new Date());
		return merchantInfoDao.insert(entity);
	}

	@Override
	public Integer removeMerchantInfoById(Long id) {
		return merchantInfoDao.deleteMerchantInfoById(id);
	}

	@Override
	public Integer modifyMerchantInfoById(MerchantInfoDto dto) throws GlobalException {
		/** 联系人手机号进行重复验证 */
		MerchantInfoEntity findMerchantInfoBycontactTele = merchantInfoDao
				.selectMerchantInfoBycontactTele(dto.getContactTele());
		if (findMerchantInfoBycontactTele != null) {
			throw new GlobalException(MerchantInfoException.MERCHANTINFO_CONTACTELE_NOT_REPETITION);
		}
		MerchantInfoEntity merchantInfo = merchantInfoDao.selectById(dto.getId());
		BeanUtils.copyProperties(dto, merchantInfo);
		merchantInfo.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		merchantInfo.setLastModifiedTime(new Date());
		return merchantInfoDao.updateById(merchantInfo);
	}

	@Override
	public Page<MerchantInfoListDto> getMerchantInfoList(Page<MerchantInfoListDto> page,
			MerchantInfoListDto merchantInfoListDto) {
		page.setRecords(merchantInfoDao.selectMerchantInfoList(page, merchantInfoListDto));
		return page;
	}

	@Override
	public MerchantInfoDto getMerchantParticularsById(Long id) {
		return merchantInfoDao.selectMerchantParticularsById(id);

	}

	@Override
	public MerchantInfoDto getStoreInfoById(Long merchantId) {
		return merchantInfoDao.selectStoreInfoById(merchantId);
	}

	@Override
	public List<MerchantInfoDto> getMerchantType() {
		return merchantInfoDao.selectMerchantType();
	}

	@Override
	public List<StoreInfoEntity> getStoreInfoByName(MerchantInfoDto dto) {
		return merchantInfoDao.selectStoreInfoByName(dto);
	}

	@Override
	public Integer modifyMerchantInfoByMerchantGradeId(Long id, Long merchantGradeId) {
		return merchantInfoDao.updateMerchantInfoByMerchantGradeId(id, merchantGradeId);
	}

	@Override
	public List<MerchantInfoDto> getMerchantInfoByMerchantType() {
		return merchantInfoDao.selectMerchantInfoByMerchantType();
	}

	@Override
	public List<MerchantInfoDto> getMerchantInfoByName() {
		return merchantInfoDao.selectMerchantInfoByName();
	}

	@Override
	public List<StoreInfoEntity> getStoreInfoList() {

		return merchantInfoDao.selectStoreInfoList();
	}

	@Override
	public StoreInfoDetailDto getAllStoreByLoginName() {
		StoreInfoDetailDto storeDto = new StoreInfoDetailDto();
		//登录人的所属商家
		String telephone = SecurityContextUtils.getCurrentSystemUser().getMobilePhone();
		MemberEntity member = memberMgmtDao.getMemberByuserTelephone(telephone);
		//所有店铺
		List<StoreInfoEntity> selectByMerchantId = storeInfoDao.selectByMerchantId(member.getMerchantId());
		//商家信息
		MerchantInfoEntity selectById = merchantInfoDao.selectById(member.getMerchantId());
		//用户角色是否为商家2
		Long roleId = systemUserRoleDao.selectByMerchantId(SecurityContextUtils.getCurrentSystemUser().getId());
		storeDto.setIsCreate(false);
		//商家 ，并且是连锁店  可创建  或者是没有店 可创建
		if(null != roleId && roleId == 2) {
			if(selectById.getMerchantType()==1 || null==selectByMerchantId || selectByMerchantId.size()==0) {
				storeDto.setIsCreate(true);
			}
		}
		//标注可管理的店铺
		if(selectByMerchantId != null) {
			//如果是商家，可管理所有店铺
			if(null != roleId && roleId == 2) {
				storeDto.setIsCreate(true);
				for (StoreInfoEntity storeInfoEntity : selectByMerchantId) {
						storeInfoEntity.setFlag(true);
				}
			}else {
				storeDto.setIsCreate(false);
				for (StoreInfoEntity storeInfoEntity : selectByMerchantId) {
					if(member.getStoreId().equals(storeInfoEntity.getId())) {
						storeInfoEntity.setFlag(true);
						break;
					}
				}
			}
		}
		storeDto.setMerchantType(selectById.getMerchantType());
		storeDto.setName(selectById.getName());
		storeDto.setStoreList(selectByMerchantId);
		return storeDto;
	}

}
