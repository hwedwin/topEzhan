package com.topaiebiz.merchant.enter.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.merchant.enter.dto.CapitalDto;
import com.topaiebiz.merchant.info.entity.MerchantInfoEntity;

@Mapper
public interface MerchantInfoDaos extends BaseDao<MerchantInfoEntity> {

	/**
	 * Description： 根据账户查询商家入驻状态
	 * 
	 * Author: Anthony
	 * 
	 * @param loginName
	 * @return
	 */
	MerchantInfoEntity getMerchantInfoStateByLoginName(String loginName);

	/**
	 * Description： 根据loginName查出当前用户的入驻状态。
	 * 
	 * Author: Anthony
	 * 
	 * param : capitalDto 请求参数
	 * 
	 */
	MerchantInfoEntity getMerchantInfoStateByLoginNames(CapitalDto capitalDto);

	/**
	 * Description：根据状态去查商家的id。
	 * 
	 * Author: Anthony
	 * 
	 * param : state 商家入驻状态
	 */
	Long selectMerchantInfoByState(Integer state);

	MerchantInfoEntity selectMerchantInfoByMerchantId(Long belongId);

	void selectByUpdate(Long belongId);

	void selectByIds(Long belongId);

	void updateByIds(Long id);

	/***
	 * Description： 修改状态
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @return
	 */
	Integer updateMerchantStatus(@Param("state") Integer state, @Param("id") Long id);
	

}
