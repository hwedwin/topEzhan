package com.topaiebiz.transaction.payment.log.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.transaction.payment.log.entity.PayOrderLogEntity;

/**
 * 
 * Description 订单三方支付记录接口 
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月7日 下午4:58:16 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public interface PayOrderLogService {
	
	/**
	 * 
	 * Description 第三方记录列表，带分页查询   
	 * 
	 * Author zhushuyong   
	 * 
	 * @param payOrderLogEntity
	 * @return
	 */
	Page<PayOrderLogEntity> queryPayOrderLogPage(Page<PayOrderLogEntity> page,PayOrderLogEntity payOrderLog);
	
	/**
	 * 
	 * Description 根据id查询第三方支付记录  
	 * 
	 * Author zhushuyong   
	 * 
	 * @param payOrderLogEntity
	 * @return
	 */
	PayOrderLogEntity queryPayOrderLogView(Long id)throws GlobalException ;

}
