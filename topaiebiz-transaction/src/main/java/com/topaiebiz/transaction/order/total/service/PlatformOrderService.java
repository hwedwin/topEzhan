package com.topaiebiz.transaction.order.total.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.transaction.order.total.dto.PlatformPayOrderLogDto;

/**
 * 
 * Description 平台订单业务接口  
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月5日 下午3:58:02 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public interface PlatformOrderService {
	
	/**
	 * 
	 * Description 平台订单可查询，可分页的列表接口  
	 * 
	 * Author zhushuyong   
	 * 
	 * @param page
	 * 			翻页对象，可以作为 xml参数直接使用，传递参数Page 即自动分页
	 * @param platformPayOrderLog
	 * @return
	 */
	Page<PlatformPayOrderLogDto> queryPlatformPayOrderLog(Page<PlatformPayOrderLogDto> page,PlatformPayOrderLogDto platformPayOrderLogDto);
	
	/**
	 * 
	 * Description 根据平台订单id查询指定表的指定字段  
	 * 
	 * Author zhushuyong   
	 * 
	 * @param id
	 * 		平台订单id
	 * @return
	 */
	PlatformPayOrderLogDto queryPayOrderLogId(Long id)  throws GlobalException ;

}
