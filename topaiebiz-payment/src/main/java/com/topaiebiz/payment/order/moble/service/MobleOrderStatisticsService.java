/**
 * 
 */
package com.topaiebiz.payment.order.moble.service;

import java.util.List;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.payment.order.moble.dto.OrderStatisticsDto;
import com.topaiebiz.transaction.order.total.dto.NewOldCustomerOrderRangeDto;
import com.topaiebiz.transaction.order.total.dto.TotalOrderParamDto;
import com.topaiebiz.transaction.order.total.dto.TotalOrderPriceRangeDto;

/**  
 * Description： 订单报表统计接口类
 * 
 * 
 * Author hxpeng 
 *    
 * Date 2017年10月29日 下午4:00:03 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public interface MobleOrderStatisticsService {

	/**
	 * 
	 * Description： 交易统计分析
	 * 
	 * Author hxpeng   
	 * 
	 * @param totalOrderParamDto
	 * @return
	 */
	OrderStatisticsDto orderStatistics(TotalOrderParamDto totalOrderParamDto) throws GlobalException,Exception;
	
	/**
	 * 
	 * Description： 订单总价区间统计
	 * 
	 * Author hxpeng   
	 * 
	 * @param totalOrderParamDto
	 * @return
	 */
	List<TotalOrderPriceRangeDto> getTotalOrderRange(TotalOrderParamDto totalOrderParamDto);
	
	/**
	 * 
	 * Description：新老顾客 ，月份数据对比
	 * 
	 * Author hxpeng   
	 * 
	 * @param totalOrderParamDto
	 * @return
	 */
	NewOldCustomerOrderRangeDto newOldCustomerCompare(TotalOrderParamDto totalOrderParamDto) throws Exception ;
}
