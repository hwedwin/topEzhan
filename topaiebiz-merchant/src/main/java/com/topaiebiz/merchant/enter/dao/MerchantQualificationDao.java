package com.topaiebiz.merchant.enter.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.merchant.enter.dto.CapitalDto;
import com.topaiebiz.merchant.enter.dto.CostInfoDto;
import com.topaiebiz.merchant.enter.dto.MercahntManageInfoDto;
import com.topaiebiz.merchant.enter.dto.MerchantBasicInfoDto;
import com.topaiebiz.merchant.enter.dto.MerchantManageDto;
import com.topaiebiz.merchant.enter.dto.MerchantQualificationDto;
import com.topaiebiz.merchant.enter.dto.MerchantbasicInfo;
import com.topaiebiz.merchant.enter.dto.StoreInfoDtos;
import com.topaiebiz.merchant.info.entity.MerchantQualificationEntity;

/**
 * Description: 商家入驻流程数据持久层接口
 * 
 * Author : Anthony
 * 
 * Date :2017年10月9日 上午11:20:40
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface MerchantQualificationDao extends BaseDao<MerchantQualificationEntity> {

	/**
	 * Description：验证手机号唯一
	 * 
	 * Author: Anthony
	 * 
	 * param: 商家联系人手机号
	 * 
	 * return: 商家资质dto对象
	 */
	MerchantQualificationDto selectMerchantQualificationBycontactTele(String contactTele);

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
	List<MerchantQualificationDto> selectMerchantQualificationList(Page<MerchantQualificationDto> page,
			MerchantQualificationDto merchantQualificationDto);

	/**
	 * Description：商家入驻审核信息（商家资质信息数据回显）。
	 * 
	 * Author: Anthony
	 * 
	 * param : id 商家资质的id
	 * 
	 * return : MerchantQualificationDto 商家资质数据
	 */
	MerchantQualificationDto selectMerchantQualificationListById(Long id);

	/**
	 * Description：根据传过来的qualificationId查出对应数据的merchantId
	 * 
	 * Author: Anthony
	 * 
	 * @param qualificationId
	 *            商家资质id
	 * @return
	 */
	Long selectQualificationIdByMerchantId(Long qualificationId);

	/**
	 * Description：获取的商家的id,set到商家银行账户表中的merchantId中。
	 * 
	 * Author: Anthony
	 * 
	 * @param merchantInfoEntity
	 *            商家实体类对象
	 * 
	 * @return 执行结果
	 */
	Integer updateMerchantInfoByMerchantType(StoreInfoDtos storeInfoDto);

	/**
	 * Description：根据商家id回显商家的错误提交信息 (公司及联系人信息)。
	 * 
	 * Author: Anthony
	 * 
	 * @param 商家信息id
	 */
	MerchantBasicInfoDto selectMerchantInfoByLoginName(Long merchantId);

	/**
	 * Description： 根据商家id回显商家的提交信息 (经营信息)。
	 * 
	 * Author: Anthony
	 * 
	 * param : loginName 商家账号
	 */
	MercahntManageInfoDto selectMercahntManageInfoByLoginName(Long merchantId);

	/**
	 * Description：添加缴费凭证图片
	 * 
	 * Author: Anthony
	 * 
	 * @param capitalDto
	 */
	void updateMerchantInfoByState(CapitalDto capitalDto);
    
	/**
	 * Description：   根据商家id查出商家资质表信息
	 * 
	 * Author: Anthony   
	 * 
	 * param : belongId
	 */
	MerchantQualificationEntity selectMerchantInfoByMerchantId(Long belongId);

	/**
	 * Description： 商家基本信息回显
	 * 
	 * Author: Anthony
	 * 
	 * @param id
	 * @return
	 */
	MerchantbasicInfo selectMerchantBasicInfOById(Long id);

	/**
	 * Description： 经营类目信息回显。
	 * 
	 * Author: Anthony
	 * 
	 * @param id
	 * @return
	 */
	MerchantManageDto selectMerchantManageInfoById(Long id);

	/**
	 * Description： 费用信息回显。
	 * 
	 * Author: Anthony
	 * 
	 * param : id 商家账户信息id 
	 */
	CostInfoDto selectCostInfoById(Long id);

}
