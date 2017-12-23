package com.topaiebiz.transaction.payment.log.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.transaction.payment.log.entity.PayOrderLogEntity;

/**
 * 
 * Description 第三方支付记录数据访问层  
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月7日 下午4:57:51 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface PayOrderLogDao extends BaseDao<PayOrderLogEntity> {
	
	/**
	 * 
	 * Description 第三方记录列表，带分页查询   
	 * 
	 * Author zhushuyong   
	 * 
	 * @param payOrderLog
	 * @return
	 */
	List<PayOrderLogEntity> selectPayOrderLogPage(Page<PayOrderLogEntity> page,PayOrderLogEntity payOrderLog);
	
}
