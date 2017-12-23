package com.topaiebiz.merchant.enter.service;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.goods.category.backend.dto.BackendCategorysDto;
import com.topaiebiz.merchant.enter.dto.AddFreightTempleteDto;
import com.topaiebiz.merchant.enter.dto.CapitalDto;
import com.topaiebiz.merchant.enter.dto.CostInfoDto;
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
import com.topaiebiz.merchant.enter.entity.MerchantAuditDetailEntity;
import com.topaiebiz.merchant.enter.entity.MerchantauditLogEntity;
import com.topaiebiz.merchant.info.entity.MerchantInfoEntity;
import com.topaiebiz.merchant.info.entity.MerchantQualificationEntity;
import com.topaiebiz.merchant.info.entity.StoreInfoEntity;

/**
 * Description: 商家入驻流程业务层接口
 * 
 * Author : Anthony
 * 
 * Date :2017年10月9日 上午11:09:03
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public interface MerchantEnterService {

	/**
	 * Description：商家入驻流程--基本信息填写（公司及联系人信息）
	 * 
	 * Author: Anthony
	 * 
	 * param : entity 商家资质实体类对象
	 * 
	 * return : 执行成功结果参数
	 */
	void saveMerchantQualification(MerchantBasicInfoDto merchantBasicInfoDto, HttpSession session) throws GlobalException;

	/**
	 * Description：商家入驻流程--基本信息填写（商家账户信息填写）
	 * 
	 * Author: Anthony
	 * 
	 * param : entity 商家账户实体类对象
	 * 
	 * return : 执行成功结果参数
	 */
	void saveMerchantAccount(MercahntManageInfoDto mercahntManageInfoDto) throws ParseException;

	/**
	 * Description：创建店铺
	 * 
	 * Author: Anthony
	 * 
	 * param: entity 店铺经营信息Dto
	 * 
	 * return : 执行成功结果参数
	 */
	Integer saveStoreInfo(StoreInfoEntity entity);

	/**
	 * Description： 商家资质列表分页检索
	 * 
	 * Author: Anthony
	 * 
	 * param : page 分页参数
	 * 
	 * param : merchantQualificationDto 资质的dto对象
	 * 
	 * return : 商家资质的信息
	 */
	Page<MerchantQualificationDto> getMerchantQualificationList(Page<MerchantQualificationDto> page,
			MerchantQualificationDto merchantQualificationDto);

	/**
	 * Description：商家入驻审核信息（商家资质信息数据回显） 。
	 * 
	 * Author: Anthony
	 * 
	 * param : id 商家资质信息id
	 * 
	 * return : 商家资质信息数据
	 */
	MerchantQualificationDto getMerchantQualificationListById(Long id);

	/**
	 * Description：商家入驻审核详情（添加某字段不通过的详细原因）。
	 * 
	 * Author: Anthony
	 * 
	 * param : merchantAuditDetailEntity 商家入驻审核详情信息对象
	 * 
	 * return : 执行成功结果参数
	 */
	Integer saveMerchantAuditDetail(MerchantAuditDetailEntity merchantAuditDetailEntity);

	/**
	 * Description： 商家入驻审核记录。
	 * 
	 * Author: Anthony
	 * 
	 * param : merchantauditLogEntity 商家入驻审核记录对象
	 * 
	 * return : 执行成功结果参数
	 */
	Integer saveMerchantauditLog(MerchantauditLogEntity merchantauditLogEntity);

	/**
	 * Description： 查询运费模板详情
	 * 
	 * Author: Anthony
	 * 
	 * param : storeId 店铺id
	 * 
	 * return : 运费模板与运费模板详情数据
	 */
	List<FreightTempleteDto> getFreightTempleteByStoreId(Long storeId);

	/**
	 * Description： 创建店铺后店铺的回显
	 * 
	 * Author: Anthony
	 * 
	 * param : id 店铺id
	 * 
	 * return : 店铺信息
	 */
	StoreInfoDto getStoreInfoById(Long id);

	/**
	 * Description： 商家id商品id区域id件数（goodsNumber）算出金额
	 * 
	 * Author: Anthony
	 * 
	 * @param storeId
	 *            店铺id
	 * 
	 * @param goodsId
	 *            商品id
	 * 
	 * @param districtId
	 *            区域id
	 * 
	 * @param goodsNumber
	 *            商品数量
	 * 
	 * @return 总金额
	 */
	Double getGoodsNumberPrice(Long storeId, Long goodsId, Long districtId, Integer goodsNumber);

	/**
	 * Description：首先添加商家信息表中的主键id
	 * 
	 * Author: Anthony
	 * 
	 * @param merchantInfoEntity
	 */
	void insertMerchantInfo(MerchantInfoEntity merchantInfoEntity);

	/**
	 * Description：根据第一张页面传过来的qualificationId去添加之前商家资质表中没有添加的字段。
	 * 
	 * Author: Anthony
	 * 
	 * @param merchantQualificationEntity
	 */
	void updateManageInfoByQualificationId(MerchantQualificationEntity merchantQualificationEntity);

	/**
	 * Description：根据传过来的qualificationId查出对应数据的merchantId。
	 * 
	 * Author: Anthony
	 * 
	 * @param qualificationId
	 * @return
	 */
	Long getQualificationIdByMerchantId(Long qualificationId);

	/**
	 * Description： 根据商家id修改商家（店铺类型）merchantType。
	 * 
	 * Author: Anthony
	 * 
	 * @param merchantInfoEntity
	 *            商家信息Dto对象
	 * 
	 * @return 执行结果参数
	 */
	void updateMerchantInfoByMerchantType(StoreInfoDtos storeInfoDto);

	/**
	 * Description： FreightTempleteDto。
	 * 
	 * Author: Anthony
	 * 
	 * return : 运费模板详情信息
	 */
	List<FreightTempleteDto> getFreightTempleteList();

	/**
	 * Description： 根据账户查询商家入驻状态
	 * 
	 * Author: Anthony
	 * 
	 * param : loginName 商家账号
	 * 
	 * @return
	 */
	StateDto getMerchantInfoStateByLoginName(String username) throws GlobalException;

	/**
	 * Description： 根据商家id查出本商家的入驻审核记录
	 * 
	 * Author: Anthony
	 * 
	 * @param merchantId
	 * @return
	 */
	StateDto getMerchantAuditLogByMerchantId(Long id);

	/**
	 * Description： 根据商家id回显商家的错误提交信息 (公司及联系人信息)
	 * 
	 * Author: Anthony
	 * 
	 * @param loginName
	 * @return
	 */
	MerchantBasicInfoDto getMerchantInfoByLoginName();

	/**
	 * Description： 根据商家id回显商家的提交信息 (经营信息)。
	 * 
	 * Author: Anthony
	 * 
	 * param : loginName 商家账户
	 */
	MercahntManageInfoDto getMercahntManageInfoByLoginName();

	/**
	 * Description： 根据商家id回显商家的提交信息 (在线经营范围)。
	 * 
	 * Author: Anthony
	 * 
	 * param : loginName 用户名
	 */
	StoreInfoDto getMerchantTypeByLoginName();

	/**
	 * Description： 上传缴费凭证。
	 * 
	 * Author: Anthony
	 * 
	 * param : capitalDto 缴费凭证图片页面所需Dto参数
	 */
	void savePayImage(CapitalDto capitalDto);

	/**
	 * Description： 商家入驻审核记录。
	 * 
	 * Author: Anthony
	 * 
	 * param : merchantauditLogDto 商家入驻审核记录Dto
	 */
	void saveMerchantAuditLogAndDetail(MerchantauditLogDto merchantauditLogDto);

	/**
	 * Description： 运费模板列表、分页检索。
	 * 
	 * Author: Anthony
	 * 
	 * param : page 分页参数
	 * 
	 * param : merFreightTempleteDto 运费模板Dto参数
	 * 
	 * return : 运费模板列表
	 */
	Page<MerFreightTempleteDto> getMerFreightTempleteList(Page<MerFreightTempleteDto> page,
			MerFreightTempleteDto merFreightTempleteDto);

	/**
	 * Description： 删除运费模板
	 * 
	 * Author: Anthony
	 * 
	 * param : id运费模板id
	 * 
	 * return : 成功执行返回的结果参数
	 */
	Integer removeMerFreightTempleteById(Long id);

	/**
	 * Description： 添加运费模板
	 * 
	 * Author: Anthony
	 * 
	 * param : addFreightTempleteDto 运费模板参数
	 */
	void saveMerFreightTemplete(AddFreightTempleteDto addFreightTempleteDto);

	/**
	 * Description： 运费模板修改回显。
	 * 
	 * Author: Anthony
	 * 
	 * param : id 运费模板id
	 * 
	 * return : 运费模板信息实体类对象
	 */
	AddFreightTempleteDto selectMerFreightTempleteById(Long id);

	/**
	 * Description：修改运费模板
	 * 
	 * Author: Anthony
	 * 
	 * param : addFreightTempleteDto 运费模板Dto对象
	 */
	void updateMerFreightTempleteById(AddFreightTempleteDto addFreightTempleteDto);

	/**
	 * Description：商家基本信息回显。
	 * 
	 * Author: Anthony
	 * 
	 * param : id 商家id
	 * 
	 * return : 商家基本信息数据
	 */
	MerchantbasicInfo selectMerchantBasicInfOById(Long id);

	/**
	 * Description：经营类目信息回显。
	 * 
	 * Author: Anthony
	 * 
	 * param : id 商家id
	 * 
	 * return : 商家经营类目信息数据
	 */
	MerchantManageDto getMerchantManageInfoById(Long id);

	/**
	 * Description：费用信息回显。
	 * 
	 * Author: Anthony
	 * 
	 * param : id 商家id
	 * 
	 * return : 费用信息数据
	 */
	CostInfoDto getCostInfoById(Long id);

	/**
	 * Description： 商家不通过原因回显(我要查询)的操作。
	 * 
	 * Author: Anthony
	 * 
	 * @param merchantId
	 * @return
	 */
	MerchantAuditDto selectMerchantAuditByMerchantId(Long merchantId);

	/**
	 * Description： 点击审核完整修改商家入驻状态
	 * 
	 * Author: Anthony
	 * 
	 * @param id
	 */
	void updateMerchantInfoStateById(Long id, Integer state);

	/**
	 * Description：点击不通过修改商家入驻状态。
	 * 
	 * Author: Anthony
	 * 
	 * @param id
	 */
	void updateMerchantInfoStateByMerchantId(Long id);

	/**
	 * Description：点击通过修改商家入驻状态。
	 * 
	 * Author: Anthony
	 * 
	 * @param id
	 */
	void updateStateByMerchantId(Long id);

	/**
	 * Description：修改基本信息。
	 * 
	 * Author: Anthony
	 * 
	 * @param merchantBasicInfoDto
	 */
	void modifyMerchantQualificationById(MerchantBasicInfoDto merchantBasicInfoDto);
    
	/**
	 * Description： 修改经营信息 
	 * 
	 * Author: Anthony   
	 * 
	 * @param mercahntManageInfoDto
	 */
	void modifyMerchantInfoById(MercahntManageInfoDto mercahntManageInfoDto) throws ParseException;
    
	/**
	 * Description： 平台端缴费
	 * 
	 * Author: Anthony   
	 * 
	 * param : capitalDto 缴费信息 
	 */
	void savePaymentPrice(CapitalDto capitalDto);

	/**
	 * Description： 选择类目，添加店铺类型
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param storeInfoDto
	 */
	void addStoreBackendCategoryInfo(StoreInfoDtos storeInfoDto);

	/**
	 * Description： 查询商家类目
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @return
	 */
	List<BackendCategorysDto> getBackendCategoryDtoByBelongStore();

	/**
	 * Description： 删除商家类目  
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param merchantId
	 * @param id
	 */
	void cancelMerchantBackendCategory(Long merchantId, Long[] ids);

	/**
	 * Description： 审核记录
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param merchantId
	 * @return
	 */
	List<MerchantauditLogDto> selectMerchantauditLog(Long merchantId);

	/**
	 * Description： 根据审核ID查询审核详情  
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param id
	 * @return
	 */
	List<MerchantAuditDetailEntity> getMerchantauditLogDetail(Long id);

}
