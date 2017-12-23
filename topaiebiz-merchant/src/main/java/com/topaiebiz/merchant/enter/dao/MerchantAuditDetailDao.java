package com.topaiebiz.merchant.enter.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.merchant.enter.entity.MerchantAuditDetailEntity;

@Mapper
public interface MerchantAuditDetailDao extends BaseDao<MerchantAuditDetailEntity> {

	/**
	 * Description： 审核不通过，查看不通过原因以及不通过字段（回显）。
	 * 
	 * Author: Anthony
	 * 
	 * @param state
	 * @return
	 */
	List<MerchantAuditDetailEntity> getMerchantAuditDetailByMerchantId(Long id);
	
	/**
	 * Description： 根据审核ID，查询审核详情 
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param id
	 * @return
	 */
	List<MerchantAuditDetailEntity> selectAuditDetailByLogId(Long id);

}
