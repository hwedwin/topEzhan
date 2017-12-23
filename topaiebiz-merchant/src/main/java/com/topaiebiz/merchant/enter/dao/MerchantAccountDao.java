package com.topaiebiz.merchant.enter.dao;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.merchant.enter.dto.CapitalDto;
import com.topaiebiz.merchant.info.entity.MerchantAccountEntity;

/**
 * Description: 商家入驻流程数据持久层接口。
 * 
 * Author : Anthony
 * 
 * Date :2017年10月10日 上午9:09:22
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface MerchantAccountDao extends BaseDao<MerchantAccountEntity> {

	/**
	 * Description：缴费信息数据回显
	 * 
	 * Author: Anthony
	 * 
	 * @param state
	 * @return
	 */
	CapitalDto selectAccountByInfo(Integer state);
    
	/**
	 * Description： 根据商家Id查出账户信息表的主键id
	 * 
	 * Author: Anthony   
	 * 
	 * @param belongId
	 * @return
	 */
	Long selectMerchantAccountById(Long belongId);

}
