package com.topaiebiz.merchant.enter.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.merchant.enter.dto.StoreInfoDto;
import com.topaiebiz.merchant.info.entity.StoreInfoEntity;

/**
 * Description: 商家入驻流程数据持久层接口。
 * 
 * Author : Anthony
 * 
 * Date :2017年10月10日 下午1:22:27
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface StoreInfoDao extends BaseDao<StoreInfoEntity> {

	/**
	 * Description： 创建店铺后店铺的回显。
	 * 
	 * Author: Anthony
	 * 
	 * param : id 店铺id
	 * 
	 * return : 店铺信息
	 */
	StoreInfoDto selectStoreInfoById(Long id);
	
	/**
	 * Description：根据商家id回显商家的提交信息 (在线经营范围)。
	 * 
	 * Author: Anthony
	 * 
	 * param loginName
	 */
	StoreInfoDto selectMerchantTypeByLoginName(Long id);
	
	/**
	 * 
	 * Description： 根据商家查询店铺
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param merchantId
	 * @return
	 */
	List<StoreInfoEntity> selectByMerchantId(Long merchantId);

}
