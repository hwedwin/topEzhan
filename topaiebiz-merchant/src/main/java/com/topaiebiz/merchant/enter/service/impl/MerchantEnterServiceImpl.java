package com.topaiebiz.merchant.enter.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.goods.category.backend.dao.BackendCategoryDao;
import com.topaiebiz.goods.category.backend.dao.BackendMerchantCategoryDao;
import com.topaiebiz.goods.category.backend.dto.BackendCategorysDto;
import com.topaiebiz.goods.category.backend.exception.BackendCategoryExceptionEnum;
import com.topaiebiz.goods.category.backend.service.BackendCategoryService;
import com.topaiebiz.member.mgmt.dao.MemberMgmtDao;
import com.topaiebiz.member.mgmt.entity.MemberEntity;
import com.topaiebiz.member.mgmt.service.MemberMgmtService;
import com.topaiebiz.merchant.enter.dao.FreightTempleteDao;
import com.topaiebiz.merchant.enter.dao.FreightTempleteDetailDao;
import com.topaiebiz.merchant.enter.dao.MerchantAccountDao;
import com.topaiebiz.merchant.enter.dao.MerchantAuditDetailDao;
import com.topaiebiz.merchant.enter.dao.MerchantInfoDaos;
import com.topaiebiz.merchant.enter.dao.MerchantQualificationDao;
import com.topaiebiz.merchant.enter.dao.MerchantauditLogDao;
import com.topaiebiz.merchant.enter.dao.StoreInfoDao;
import com.topaiebiz.merchant.enter.dto.AddFreightTempleteDto;
import com.topaiebiz.merchant.enter.dto.CapitalDto;
import com.topaiebiz.merchant.enter.dto.CostInfoDto;
import com.topaiebiz.merchant.enter.dto.DistrictInfoDto;
import com.topaiebiz.merchant.enter.dto.FreightTempleteDto;
import com.topaiebiz.merchant.enter.dto.MerFreightTempleteDto;
import com.topaiebiz.merchant.enter.dto.MercahntManageInfoDto;
import com.topaiebiz.merchant.enter.dto.MerchantAuditDto;
import com.topaiebiz.merchant.enter.dto.MerchantBasicInfoDto;
import com.topaiebiz.merchant.enter.dto.MerchantManageDto;
import com.topaiebiz.merchant.enter.dto.MerchantQualificationDto;
import com.topaiebiz.merchant.enter.dto.MerchantauditLogDto;
import com.topaiebiz.merchant.enter.dto.MerchantbasicInfo;
import com.topaiebiz.merchant.enter.dto.StateDto;
import com.topaiebiz.merchant.enter.dto.StoreInfoDto;
import com.topaiebiz.merchant.enter.dto.StoreInfoDtos;
import com.topaiebiz.merchant.enter.entity.FreightTempleteDetailEntity;
import com.topaiebiz.merchant.enter.entity.FreightTempleteEntity;
import com.topaiebiz.merchant.enter.entity.MerchantAuditDetailEntity;
import com.topaiebiz.merchant.enter.entity.MerchantauditLogEntity;
import com.topaiebiz.merchant.enter.exception.MerchantEnterException;
import com.topaiebiz.merchant.enter.service.MerchantEnterService;
import com.topaiebiz.merchant.info.entity.MerchantAccountEntity;
import com.topaiebiz.merchant.info.entity.MerchantInfoEntity;
import com.topaiebiz.merchant.info.entity.MerchantQualificationEntity;
import com.topaiebiz.merchant.info.entity.StoreInfoEntity;
import com.topaiebiz.system.district.dao.DistrictDao;
import com.topaiebiz.system.district.entity.DistrictEntity;
import com.topaiebiz.system.security.dao.SystemUserDao;
import com.topaiebiz.system.security.dao.SystemUserRoleDao;
import com.topaiebiz.system.security.entity.SystemUserEntity;
import com.topaiebiz.system.security.entity.SystemUserRoleEntity;
import com.topaiebiz.system.security.util.SecurityContextUtils;

/**
 * Description: 商家入驻流程业务层实现类
 * 
 * Author : Anthony
 * 
 * Date :2017年10月9日 上午11:05:48
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Transactional
@Service
public class MerchantEnterServiceImpl implements MerchantEnterService {

	@Autowired
	private MerchantQualificationDao merchantQualificationDao;

	@Autowired
	private MerchantAccountDao merchantAccountDao;

	@Autowired
	private StoreInfoDao storeInfoDao;

	@Autowired
	private MerchantInfoDaos merchantInfoDaos;

	@Autowired
	private MerchantAuditDetailDao merchantAuditDetailDao;

	@Autowired
	private MerchantauditLogDao merchantauditLogDao;

	@Autowired
	private FreightTempleteDao freightTempleteDao;

	@Autowired
	private MemberMgmtDao memberMgmtDao;

	@Autowired
	private FreightTempleteDetailDao freightTempleteDetailDao;

	@Autowired
	private DistrictDao districtDao;
	
	@Autowired
	private BackendCategoryService backendCategoryService;
	
	@Autowired
	private MemberMgmtService memberMgmtService;
	
	@Autowired
	private BackendMerchantCategoryDao backendMerchantCategoryDao;
	
	@Autowired
	private BackendCategoryDao backendCategoryDao;
	
	@Autowired
	private SystemUserDao systemUserDao;
	
	@Autowired
	private SystemUserRoleDao systemUserRoleDao;
	
//	@Autowired
//	private MerchantEnterService merchantEnterService;

	@Override
	public void saveMerchantQualification(MerchantBasicInfoDto merchantBasicInfoDto, HttpSession session) throws GlobalException {
		// 商家信息表中插入公司名称使商家信息表正成主键id
		MerchantInfoEntity merchantInfoEntity = new MerchantInfoEntity();
		merchantInfoEntity.setName(merchantBasicInfoDto.getName());
		merchantInfoEntity.setContactTele(merchantBasicInfoDto.getContactTele());
		merchantInfoEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		merchantInfoEntity.setCreatedTime(new Date());
		merchantInfoEntity.setState(0);
		merchantInfoDaos.insert(merchantInfoEntity);
		// 添加商家公司及联系人信息
		MerchantQualificationEntity merchantQualificationEntity = new MerchantQualificationEntity();
		BeanUtils.copyProperties(merchantBasicInfoDto, merchantQualificationEntity);
		merchantQualificationEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		merchantQualificationEntity.setCreatedTime(new Date());
		merchantQualificationEntity.setMerchantId(merchantInfoEntity.getId());
		merchantQualificationDao.insert(merchantQualificationEntity);
		// 根据电话查到会员，把所属店铺放进去
		String telephone = SecurityContextUtils.getCurrentSystemUser().getMobilePhone();
		MemberEntity member = memberMgmtDao.getMemberByuserTelephone(telephone);
		member.setMerchantId(merchantInfoEntity.getId());
		memberMgmtDao.updateById(member);
		SecurityContextUtils.getCurrentSystemUser().setMerchantId(merchantInfoEntity.getId());
	}

	@Override
	public void saveMerchantAccount(MercahntManageInfoDto mercahntManageInfoDto) throws ParseException {
		// 时间格式转换格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 获取的起始时间字符串转换成Date类型
		Date licenseBeginDate = format.parse(mercahntManageInfoDto.getLicenseBegin());
		// 获取的结束时间字符串转换成Date类型
		Date licenseEndDate = format.parse(mercahntManageInfoDto.getLicenseEnd());
		// 根据当前用户查出商家id
		String telephone = SecurityContextUtils.getCurrentSystemUser().getMobilePhone();
		MemberEntity member = memberMgmtDao.getMemberByuserTelephone(telephone);
		Long belongId = member.getMerchantId();
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象，添加营业执照信息
		MerchantQualificationEntity merchantQualificationEntity = new MerchantQualificationEntity();
		merchantQualificationEntity = merchantQualificationDao.selectMerchantInfoByMerchantId(belongId);
		Long id = merchantQualificationEntity.getId();
		BeanUtils.copyProperties(mercahntManageInfoDto, merchantQualificationEntity);
		merchantQualificationEntity.setId(id);
		merchantQualificationEntity.setLicenseBegin(licenseBeginDate);
		merchantQualificationEntity.setLicenseEnd(licenseEndDate);
		merchantQualificationDao.updateById(merchantQualificationEntity);
		// 添加银行账户信息
		MerchantAccountEntity merchantAccountEntity = new MerchantAccountEntity();
		BeanUtils.copyProperties(mercahntManageInfoDto, merchantAccountEntity);
		merchantAccountEntity.setMerchantId(belongId);
		merchantAccountEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		merchantAccountEntity.setCreatedTime(new Date());
		merchantAccountDao.insert(merchantAccountEntity);
	}

	@Override
	public Integer saveStoreInfo(StoreInfoEntity entity) {
		// 根据电话查到会员，获取所属店铺
		String telephone = SecurityContextUtils.getCurrentSystemUser().getMobilePhone();
		MemberEntity member = memberMgmtDao.getMemberByuserTelephone(telephone);
		Long belongId = member.getMerchantId();
		//改变会员所属
		//店铺表里新增
		entity.setMerchantId(belongId);
		entity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		entity.setCreatedTime(new Date());
		storeInfoDao.insert(entity);
		//所属店铺换一下
		member = memberMgmtDao.selectById(member.getId());
		member.setStoreId(entity.getId());
		return memberMgmtDao.updateById(member);
	}

	@Override
	public Page<MerchantQualificationDto> getMerchantQualificationList(Page<MerchantQualificationDto> page,
			MerchantQualificationDto merchantQualificationDto) {
		page.setRecords(merchantQualificationDao.selectMerchantQualificationList(page, merchantQualificationDto));
		return page;
	}

	@Override
	public MerchantQualificationDto getMerchantQualificationListById(Long id) {
		return merchantQualificationDao.selectMerchantQualificationListById(id);
	}

	@Override
	public Integer saveMerchantAuditDetail(MerchantAuditDetailEntity merchantAuditDetailEntity) {
		merchantAuditDetailEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		merchantAuditDetailEntity.setCreatedTime(new Date());
		return merchantAuditDetailDao.insert(merchantAuditDetailEntity);
	}

	@Override
	public Integer saveMerchantauditLog(MerchantauditLogEntity merchantauditLogEntity) {
		merchantauditLogEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		merchantauditLogEntity.setCreatedTime(new Date());
		return merchantauditLogDao.insert(merchantauditLogEntity);
	}

	@Override
	public List<FreightTempleteDto> getFreightTempleteByStoreId(Long storeId) {
		return freightTempleteDao.selectFreightTempleteByStoreId(storeId);
	}

	@Override
	public StoreInfoDto getStoreInfoById(Long id) {
		return storeInfoDao.selectStoreInfoById(id);
	}

	@Override
	public Double getGoodsNumberPrice(Long storeId, Long goodsId, Long districtId, Integer goodsNumber) {
		return 0.0;

	}

	@Override
	public void insertMerchantInfo(MerchantInfoEntity merchantInfoEntity) {
		merchantInfoDaos.insert(merchantInfoEntity);

	}

	@Override
	public void updateManageInfoByQualificationId(MerchantQualificationEntity merchantQualificationEntity) {
		merchantQualificationDao.updateById(merchantQualificationEntity);

	}

	@Override
	public Long getQualificationIdByMerchantId(Long qualificationId) {
		return merchantQualificationDao.selectQualificationIdByMerchantId(qualificationId);
	}

	@Override
	public void updateMerchantInfoByMerchantType(StoreInfoDtos storeInfoDto) {
		// 根据当前用户查出商家id
		String telephone = SecurityContextUtils.getCurrentSystemUser().getMobilePhone();
		MemberEntity member = memberMgmtDao.getMemberByuserTelephone(telephone);
		Long belongId = member.getMerchantId();
		StoreInfoEntity StoreInfoEntity = new StoreInfoEntity();
		BeanUtils.copyProperties(storeInfoDto, StoreInfoEntity);
		StoreInfoEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		StoreInfoEntity.setCreatedTime(new Date());
		StoreInfoEntity.setMerchantId(belongId);
		StoreInfoEntity.setName(storeInfoDto.getName());
		storeInfoDao.insert(StoreInfoEntity);
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象，添加店铺经营信息
		MerchantInfoEntity merchantInfoEntity = new MerchantInfoEntity();
		BeanUtils.copyProperties(storeInfoDto, merchantInfoEntity);
		merchantInfoEntity.setId(belongId);
		merchantInfoEntity.setMerchantType(storeInfoDto.getMerchantType());
		merchantInfoEntity.setState(1);
		merchantInfoDaos.updateById(merchantInfoEntity);
	}

	@Override
	public List<FreightTempleteDto> getFreightTempleteList() {
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		return freightTempleteDao.selectFreightTempleteList(storeId);
	}

	@Override
	public StateDto getMerchantInfoStateByLoginName(String username) throws GlobalException {
		// 根据当前登录的用户名查出商家id
		MemberEntity member = memberMgmtDao.selectMemberByUserNameOrTelephone(username);
		Long merchantId = member.getMerchantId();
		if (merchantId== null) {
			throw new GlobalException(MerchantEnterException.MERCHANTENTER_NOT_ENTER);
		}
		MerchantInfoEntity merchantInfoEntity = merchantInfoDaos.selectById(merchantId);
		if (null == merchantInfoEntity) {
			throw new GlobalException(MerchantEnterException.MERCHANTENTER_NOT_ENTER);
		}
		StateDto stateDto = new StateDto();
		// 入驻状态。 1申请中，2审核通过 3 审核不通过 4类目不通过  5类目通过 6待付款 7付款已提交 8付款未通过 9已完成
		if (merchantInfoEntity.getState() == 0) {
			stateDto.setState(merchantInfoEntity.getState());
			return stateDto;
		}
		if (merchantInfoEntity.getState() == 1) {
			// 1申请
			stateDto.setState(merchantInfoEntity.getState());
			return stateDto;
		}
		if (merchantInfoEntity.getState() == 2) {
			// 2审核通过,进入缴纳费用阶段
			stateDto.setState(merchantInfoEntity.getState());
			return stateDto;
		}
		if (merchantInfoEntity.getState() == 3) {
			// 3 审核不通过
			// 商家信息关联商家信息表的ID
			List<MerchantauditLogDto> merchantauditLogDto = merchantauditLogDao.getMerchantauditLog(merchantId);
			// 审核不通过，查看不通过原因以及不通过字段（回显）
			List<MerchantAuditDetailEntity> merchantAuditDetailEntity = merchantAuditDetailDao.getMerchantAuditDetailByMerchantId(merchantauditLogDto.get(0).getId());
			merchantauditLogDto.get(0).setDetailList(merchantAuditDetailEntity);
			stateDto.setLogList(merchantauditLogDto);
		}
		if (merchantInfoEntity.getState() == 4) {
			// 4待付款
			stateDto.setState(merchantInfoEntity.getState());
			return stateDto;
		}
		if (merchantInfoEntity.getState() == 5) {
			// 5 付款已提交
			stateDto.setState(merchantInfoEntity.getState());
			return stateDto;
		}
		if (merchantInfoEntity.getState() == 6) {
			// 6 已完成
			stateDto.setState(merchantInfoEntity.getState());
			return stateDto;
		}
		stateDto.setState(merchantInfoEntity.getState());
		return stateDto;
	}

	@Override
	public StateDto getMerchantAuditLogByMerchantId(Long id) {
		StateDto stateDto = new StateDto();
		// 商家信息关联商家信息表的ID
		List<MerchantauditLogDto> merchantauditLogDto = merchantauditLogDao.getMerchantauditLogs(id);
		// 审核不通过，查看不通过原因以及不通过字段（回显）
		List<MerchantAuditDetailEntity> merchantAuditDetailEntity = merchantAuditDetailDao
				.getMerchantAuditDetailByMerchantId(merchantauditLogDto.get(0).getId());
		merchantauditLogDto.get(0).setDetailList(merchantAuditDetailEntity);
		stateDto.setLogList(merchantauditLogDto);
		return stateDto;
	}

	@Override
	public MerchantBasicInfoDto getMerchantInfoByLoginName() {
		// 根据当前用户查出商家id
		String telephone = SecurityContextUtils.getCurrentSystemUser().getMobilePhone();
		MemberEntity member = memberMgmtDao.getMemberByuserTelephone(telephone);
		Long merchantId = member.getMerchantId();
		return merchantQualificationDao.selectMerchantInfoByLoginName(merchantId);
	}

	@Override
	public MercahntManageInfoDto getMercahntManageInfoByLoginName() {
		// 根据当前用户查出商家id
		String telephone = SecurityContextUtils.getCurrentSystemUser().getMobilePhone();
		MemberEntity member = memberMgmtDao.getMemberByuserTelephone(telephone);
		Long merchantId = member.getMerchantId();
		return merchantQualificationDao.selectMercahntManageInfoByLoginName(merchantId);
	}

	@Override
	public StoreInfoDto getMerchantTypeByLoginName() {
		// 根据当前用户查出商家id
		String telephone = SecurityContextUtils.getCurrentSystemUser().getMobilePhone();
		MemberEntity member = memberMgmtDao.getMemberByuserTelephone(telephone);
		Long id = member.getMerchantId();
		return storeInfoDao.selectMerchantTypeByLoginName(id);
	}

	@Override
	public void savePayImage(CapitalDto capitalDto) {
		// 根据当前用户查出商家id
		String telephone = SecurityContextUtils.getCurrentSystemUser().getMobilePhone();
		MemberEntity member = memberMgmtDao.getMemberByuserTelephone(telephone);
		Long belongId = member.getMerchantId();
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象，上传缴费凭证
		MerchantQualificationEntity merchantQualificationEntity = merchantQualificationDao.selectMerchantInfoByMerchantId(belongId);
		if(null != merchantQualificationEntity) {
			MerchantQualificationEntity selectById = merchantQualificationDao.selectById(merchantQualificationEntity.getId());
			selectById.setPayImage(capitalDto.getPayImage());
			selectById.setPayTime(new Date());
			selectById.setLastModifiedTime(new Date());
			selectById.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
			merchantQualificationDao.updateById(selectById);
		}
		MerchantInfoEntity merchantInfo = merchantInfoDaos.selectById(belongId);
		merchantInfo.setState(7);
		merchantInfo.setLastModifiedTime(new Date());
		merchantInfo.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		merchantInfoDaos.updateById(merchantInfo);
	}

	@Override
	public void saveMerchantAuditLogAndDetail(MerchantauditLogDto merchantauditLogDto) {
		// 添加审核记录
		MerchantauditLogEntity merchantauditLogEntity = new MerchantauditLogEntity();
		merchantauditLogEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		merchantauditLogEntity.setCreatedTime(new Date());
		BeanUtils.copyProperties(merchantauditLogDto, merchantauditLogEntity);
		//不通过
		merchantauditLogEntity.setAuditResult(3);
		merchantauditLogDao.insert(merchantauditLogEntity);
		// 添加审核记录详情
		List<MerchantAuditDetailEntity> detailList = merchantauditLogDto.getDetailList();
		for (MerchantAuditDetailEntity merchantAuditDetailEntity : detailList) {
			merchantAuditDetailEntity.setAuditLogId(merchantauditLogEntity.getId());
			merchantAuditDetailEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
			merchantAuditDetailEntity.setMerchantId(merchantauditLogEntity.getMerchantId());
			merchantAuditDetailDao.insert(merchantAuditDetailEntity);
		}
		// 添加审核记录的同时改变商家入驻的状态
		MerchantInfoEntity entity = merchantInfoDaos.selectById(merchantauditLogEntity.getMerchantId());
		entity.setState(3);
		entity.setLastModifiedTime(new Date());
		entity.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		merchantInfoDaos.updateById(entity);
	}

	@Override
	public Page<MerFreightTempleteDto> getMerFreightTempleteList(Page<MerFreightTempleteDto> page,
			MerFreightTempleteDto merFreightTempleteDto) {
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		if (storeId != null) {
			merFreightTempleteDto.setStoreId(storeId);
		}
		page.setRecords(freightTempleteDao.selectMerFreightTempleteList(page, merFreightTempleteDto));
		return page;
	}

	@Override
	public Integer removeMerFreightTempleteById(Long id) {
		return freightTempleteDao.delectMerFreightTempleteById(id);
	}

	@Override
	public void saveMerFreightTemplete(AddFreightTempleteDto addFreightTempleteDto) {
		FreightTempleteEntity FreightTempleteEntity = new FreightTempleteEntity();
		// 根据当前用户查出商家id
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		if (storeId != null) {
			addFreightTempleteDto.setStoreId(storeId);
		}
		BeanUtils.copyProperties(addFreightTempleteDto, FreightTempleteEntity);
		FreightTempleteEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		FreightTempleteEntity.setCreatedTime(new Date());
		freightTempleteDao.insert(FreightTempleteEntity);
		List<FreightTempleteDetailEntity> freightTempleteDetails = addFreightTempleteDto.getFreightTempleteDetails();
		for (FreightTempleteDetailEntity freightTempleteDetailEntity : freightTempleteDetails) {
			freightTempleteDetailEntity.setFreightId(FreightTempleteEntity.getId());
			freightTempleteDetailEntity.setCreatedTime(new Date());
			freightTempleteDetailEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
			freightTempleteDetailDao.insert(freightTempleteDetailEntity);
		}
	}

	@Override
	public AddFreightTempleteDto selectMerFreightTempleteById(Long id) {
		FreightTempleteEntity entity = freightTempleteDao.selectById(id);
		List<FreightTempleteDetailEntity> freightTempleteDetails = freightTempleteDetailDao
				.selectDetailByTeleplateId(entity.getId());
		AddFreightTempleteDto addFreightTempleteDto = new AddFreightTempleteDto();
		BeanUtils.copyProperties(entity, addFreightTempleteDto);
		addFreightTempleteDto.setFreightTempleteDetails(freightTempleteDetails);
		// 详情列表
		for (FreightTempleteDetailEntity freightTempleteDetailEntity : freightTempleteDetails) {
			// 默认的没有区域集合
			if (freightTempleteDetailEntity.getIsDefault() == 0) {
				freightTempleteDetailEntity.setNameListStr("");
				continue;
			}
			StringBuffer nameList = new StringBuffer();
			List<DistrictInfoDto> districtDtoList = new ArrayList<DistrictInfoDto>();
			String listStr = freightTempleteDetailEntity.getDistrictIdList();
			String[] split = listStr.split(",");
			for (String s : split) {
				// 拼接名字
				DistrictEntity selectById = districtDao.selectById(Long.valueOf(s));
				nameList.append((selectById.getFullName() == null || "".equals(selectById.getFullName())) ? ""
						: selectById.getFullName() + ",");

				// 是否为一级区域
				DistrictEntity selectOneLevelById = districtDao.selectOneLevelById(selectById.getId());
				// 一级区域
				if (null != selectOneLevelById) {
					DistrictInfoDto oneDto = new DistrictInfoDto();
					BeanUtils.copyProperties(selectOneLevelById, oneDto);
					districtDtoList.add(oneDto);
					List<DistrictInfoDto> twoList = new ArrayList<DistrictInfoDto>();
					oneDto.setChildList(twoList);
					// 二级区域
					for (String s2 : split) {
						DistrictEntity twoDistrict = districtDao.selectByIdAndParentId(selectById.getId(),
								Long.valueOf(s2));
						if (null != twoDistrict) {
							DistrictInfoDto twoDto = new DistrictInfoDto();
							BeanUtils.copyProperties(twoDistrict, twoDto);
							twoList.add(twoDto);
							List<DistrictInfoDto> threeList = new ArrayList<DistrictInfoDto>();
							twoDto.setChildList(threeList);
							// 三级区域
							for (String s3 : split) {
								DistrictEntity threeDistrict = districtDao.selectByIdAndParentId(twoDistrict.getId(),
										Long.valueOf(s3));
								if (null != threeDistrict) {
									DistrictInfoDto dto3 = new DistrictInfoDto();
									BeanUtils.copyProperties(threeDistrict, dto3);
									threeList.add(dto3);
								}
							}
						}
					}

				}
			}
			String nameListStr = nameList.toString().substring(0, nameList.toString().length() - 1);
			// 名字
			freightTempleteDetailEntity.setNameListStr(nameListStr);
			freightTempleteDetailEntity.setDistrictDtoList(districtDtoList);
		}
		return addFreightTempleteDto;
	}

	@Override
	public void updateMerFreightTempleteById(AddFreightTempleteDto addFreightTempleteDto) {
		Long id = addFreightTempleteDto.getId();
		FreightTempleteEntity freightTemplete = freightTempleteDao.selectById(id);
		BeanUtils.copyProperties(addFreightTempleteDto, freightTemplete);
		freightTemplete.setLastModifiedTime(new Date());
		freightTemplete.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		freightTempleteDao.updateById(freightTemplete);
		List<FreightTempleteDetailEntity> detailList = addFreightTempleteDto.getFreightTempleteDetails();
		// 根据模板id删除模板详情
		freightTempleteDetailDao.deleteByTemplateId(freightTemplete.getId());
		for (FreightTempleteDetailEntity detail : detailList) {
			detail.setId(null);
			detail.setFreightId(freightTemplete.getId());
			detail.setLastModifiedTime(new Date());
			detail.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
			freightTempleteDetailDao.insert(detail);
		}
	}

	@Override
	public MerchantbasicInfo selectMerchantBasicInfOById(Long id) {
		return merchantQualificationDao.selectMerchantBasicInfOById(id);
	}

	@Override
	public MerchantManageDto getMerchantManageInfoById(Long id) {
		return merchantQualificationDao.selectMerchantManageInfoById(id);
	}

	@Override
	public CostInfoDto getCostInfoById(Long id) {
		return merchantQualificationDao.selectCostInfoById(id);
	}

	@Override
	public MerchantAuditDto selectMerchantAuditByMerchantId(Long merchantId) {
		return merchantauditLogDao.selectMerchantAuditByMerchantId(merchantId);
	}

	@Override
	public void updateMerchantInfoStateById(Long id, Integer state) {
		MerchantInfoEntity selectById = merchantInfoDaos.selectById(id);
		selectById.setState(state);//审核通过
		selectById.setLastModifiedTime(new Date());
		selectById.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		merchantInfoDaos.updateById(selectById);
		//审核记录插入一条数据
		MerchantauditLogEntity merchantauditLogEntity = new MerchantauditLogEntity();
		merchantauditLogEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		merchantauditLogEntity.setCreatedTime(new Date());
		merchantauditLogEntity.setAuditResult(state);
		merchantauditLogEntity.setMerchantId(id);
		merchantauditLogDao.insert(merchantauditLogEntity);
		//将入驻成功的添加到用户表
		if(state == 9) {
			MemberEntity member = memberMgmtDao.selectMemberByMerchantId(id);
			SystemUserEntity entity = new SystemUserEntity();
			entity.setUsername(member.getUserName());
			entity.setPassword(member.getPassword());
			entity.setMobilePhone(member.getTelephone());
			entity.setType(3);
			systemUserDao.insert(entity);
			//讲该用户的角色，设置为商家
			SystemUserRoleEntity userRole = new SystemUserRoleEntity();
			userRole.setUserId(entity.getId());
			userRole.setRoleId(2L);
			systemUserRoleDao.insert(userRole);
			
			SystemUserRoleEntity userRole2 = new SystemUserRoleEntity();
			userRole2.setUserId(entity.getId());
			userRole2.setRoleId(1001L);
			systemUserRoleDao.insert(userRole2);
		}
	}

	@Override
	public void updateMerchantInfoStateByMerchantId(Long id) {
		MerchantInfoEntity selectById = merchantInfoDaos.selectById(id);
		selectById.setState(3);
		selectById.setLastModifiedTime(new Date());
		selectById.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		merchantInfoDaos.updateById(selectById);
		//审核记录插入一条数据
		MerchantauditLogEntity merchantauditLogEntity = new MerchantauditLogEntity();
		merchantauditLogEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		merchantauditLogEntity.setCreatedTime(new Date());
		merchantauditLogEntity.setAuditResult(3);
		merchantauditLogEntity.setMerchantId(id);
		merchantauditLogDao.insert(merchantauditLogEntity);
	}

	@Override
	public void updateStateByMerchantId(Long id) {
		MerchantInfoEntity selectById = merchantInfoDaos.selectById(id);
		selectById.setState(4);
		merchantInfoDaos.updateById(selectById);
	}

	@Override
	public void modifyMerchantQualificationById(MerchantBasicInfoDto merchantBasicInfoDto) {
		MerchantQualificationEntity merchantQualificationEntity = merchantQualificationDao.selectById(merchantBasicInfoDto.getId());
		merchantQualificationEntity.setName(merchantBasicInfoDto.getName());
		merchantQualificationEntity.setDistrictId(merchantBasicInfoDto.getDistrictId());
		merchantQualificationEntity.setAddress(merchantBasicInfoDto.getAddress());
		merchantQualificationEntity.setStoreNumber(merchantBasicInfoDto.getStoreNumber());
		merchantQualificationEntity.setTelephone(merchantBasicInfoDto.getTelephone());
		merchantQualificationEntity.setStaffNo(merchantBasicInfoDto.getStaffNo());
		merchantQualificationEntity.setCapital(merchantBasicInfoDto.getCapital());
		merchantQualificationEntity.setContactTele(merchantBasicInfoDto.getContactTele());
		merchantQualificationEntity.setIdCard(merchantBasicInfoDto.getIdCard());
		merchantQualificationEntity.setEmail(merchantBasicInfoDto.getEmail());
		merchantQualificationEntity.setIdCardImage(merchantBasicInfoDto.getIdCardImage());
		merchantQualificationEntity.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		merchantQualificationEntity.setLastModifiedTime(new Date());
		merchantQualificationDao.updateById(merchantQualificationEntity);
	}

	@Override
	public void modifyMerchantInfoById(MercahntManageInfoDto mercahntManageInfoDto) throws ParseException {
		// 营业执照信息修改
		// 时间格式转换格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// 获取的起始时间字符串转换成Date类型
		Date licenseBeginDate = format.parse(mercahntManageInfoDto.getLicenseBegin());
		// 获取的结束时间字符串转换成Date类型
		Date licenseEndDate = format.parse(mercahntManageInfoDto.getLicenseEnd());
		// 根据当前用户查出商家id
		String telephone = SecurityContextUtils.getCurrentSystemUser().getMobilePhone();
		MemberEntity member = memberMgmtDao.getMemberByuserTelephone(telephone);
		Long belongId = member.getMerchantId();
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。
		MerchantQualificationEntity merchantQualificationEntity = merchantQualificationDao.selectMerchantInfoByMerchantId(belongId);
		Long id = merchantQualificationEntity.getId();
		BeanUtils.copyProperties(mercahntManageInfoDto, merchantQualificationEntity);
		merchantQualificationEntity.setId(id);
		merchantQualificationEntity.setLicenseBegin(licenseBeginDate);
		merchantQualificationEntity.setLicenseEnd(licenseEndDate);
		merchantQualificationEntity.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		merchantQualificationEntity.setLastModifiedTime(new Date());
		merchantQualificationDao.updateById(merchantQualificationEntity);
		// 银行账户信息修改
		MerchantAccountEntity merchantAccountEntity = new MerchantAccountEntity();
		Long aid = merchantAccountDao.selectMerchantAccountById(belongId);
		merchantAccountEntity = merchantAccountDao.selectById(aid);
		BeanUtils.copyProperties(mercahntManageInfoDto, merchantAccountEntity);
		merchantAccountEntity.setId(aid);
		merchantAccountEntity.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		merchantAccountEntity.setLastModifiedTime(new Date());
		merchantAccountDao.updateById(merchantAccountEntity);
	}

	@Override
	public void savePaymentPrice(CapitalDto capitalDto) {
		Long belongId = capitalDto.getMerchantId();
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象，添加营业执照信息
		MerchantQualificationEntity merchantQualificationEntity =  merchantQualificationDao.selectMerchantInfoByMerchantId(belongId);
		merchantQualificationEntity.setMemo(capitalDto.getMemo());
		merchantQualificationEntity.setPaymentPrice(capitalDto.getPaymentPrice());
		merchantQualificationEntity.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		merchantQualificationEntity.setLastModifiedTime(new Date());
		merchantQualificationDao.updateById(merchantQualificationEntity);
		// 商家入驻状态为5:付款已提交
		merchantInfoDaos.updateMerchantStatus(6, belongId);
	}

	@Override
	public void addStoreBackendCategoryInfo(StoreInfoDtos storeInfoDto) {
		Long[] ids = storeInfoDto.getIds();
		// 根据当前用户查出商家id
		String telephone = SecurityContextUtils.getCurrentSystemUser().getMobilePhone();
		MemberEntity member = memberMgmtService.getMemberByuserTelephone(telephone);
		Long merchantId = member.getMerchantId();
		//删除原有的
		backendMerchantCategoryDao.deleteMerchantBackend(merchantId);
		//增加新的
		backendCategoryService.addBackendCategoryDtoByBelongStore(ids);
		MerchantInfoEntity entity = merchantInfoDaos.selectById(merchantId);
		entity.setMerchantType(storeInfoDto.getMerchantType());
		entity.setState(1);
		entity.setLastModifiedTime(new Date());
		entity.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		merchantInfoDaos.updateById(entity);
	}

	@Override
	public List<BackendCategorysDto> getBackendCategoryDtoByBelongStore() {
		Long merchantId = SecurityContextUtils.getCurrentSystemUser().getMerchantId();
		return backendCategoryService.getBackendCategoryDtoByBelongStore(merchantId);
	}

	@Override
	public void cancelMerchantBackendCategory(Long merchantId, Long[] ids) {
		/** 判断id是否为空 */
		if (null == ids) {
			throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORY_ID_NOT_NULL);
		}
		if(null == merchantId) {
			throw new GlobalException(MerchantEnterException.FREIGHTNAME_NOT_NOLL);
		}
		for (Long category : ids) {
			backendCategoryDao.deleteBackendMerchantCategory(category,merchantId);
		}
	}

	@Override
	public List<MerchantauditLogDto> selectMerchantauditLog(Long merchantId) {
		return merchantauditLogDao.getMerchantauditLogs(merchantId);
	}

	@Override
	public List<MerchantAuditDetailEntity> getMerchantauditLogDetail(Long id) {
		List<MerchantAuditDetailEntity> selectAuditDetailByLogId = merchantAuditDetailDao.selectAuditDetailByLogId(id);
		return selectAuditDetailByLogId;
	}
}
