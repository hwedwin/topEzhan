package com.topaiebiz.merchant.enter.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.merchant.enter.dto.MerchantAuditDto;
import com.topaiebiz.merchant.enter.dto.MerchantauditLogDto;
import com.topaiebiz.merchant.enter.entity.MerchantauditLogEntity;


/**
 * Description: 商家入驻审核记录dao
 * 
 * Author : Anthony
 * 
 * Date :2017年10月13日 上午9:35:07
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface MerchantauditLogDao extends BaseDao<MerchantauditLogEntity> {

	/**
	 * Description：不通过集合
	 * 
	 * Author: Anthony
	 * 
	 * @param state
	 * @return
	 */
	List<MerchantauditLogDto> getMerchantauditLog(Long merchantId);

	/**
	 * Description： 不通过集合
	 * 
	 * Author: Anthony
	 * 
	 * @param merchantId
	 * @return
	 */
	List<MerchantauditLogDto> getMerchantauditLogs(Long id);

	/**
	 * Description： 商家不通过原因回显(我要查询)的操作。
	 * 
	 * Author: Anthony
	 * 
	 * @param merchantId
	 * @return
	 */
	MerchantAuditDto selectMerchantAuditByMerchantId(Long merchantId);

}
