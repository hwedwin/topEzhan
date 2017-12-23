package com.topaiebiz.transaction.order.total.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.transaction.order.total.dto.PlatformPayOrderLogDto;
import com.topaiebiz.transaction.order.total.exception.PlatformOrderExceptionEnum;
import com.topaiebiz.transaction.order.total.service.PlatformOrderService;

/**
 * 
 * Description 平台订单展现层  
 * 
 * 
 * Author:zhushuyong 
 *    
 * Date 2017年9月5日 下午4:30:03 
 * 
 * Copyright:Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@RestController
@RequestMapping("/order/total/totalOrder")
public class TotalOrderController{
	
	@Autowired
	private PlatformOrderService platformOrderService;
	
	/**
	 * 
	 * Description 平台订单可查询，可分页的列表接口   
	 * 
	 * Author:zhushuyong   
	 * 
	 * @param platformPayOrderLogDto
	 * @return
	 * @throws GlobalException 
	 */
	@RequestMapping(value = "/queryPlatformPayOrderLogPage",method=RequestMethod.GET)
	@ResponseBody
	public ResponseInfo queryPlatformPayOrderLog(Page<PlatformPayOrderLogDto> page,PlatformPayOrderLogDto platformPayOrderLogDto)
			throws GlobalException{		
		Page<PlatformPayOrderLogDto> list=platformOrderService.queryPlatformPayOrderLog(page, platformPayOrderLogDto);
		return new ResponseInfo(list);
	}
	
	/**
	 * 
	 * Description 根据平台订单id查询指定表的指定字段  
	 * 
	 * Author:zhushuyong   
	 * 
	 * @param id
	 * 		平台订单id
	 * @return
	 * @throws GlobalException 
	 */
	@RequestMapping(value = "/queryPlatformPayOrderLog",method=RequestMethod.GET)
	@ResponseBody
	public ResponseInfo queryPlatformPayOrderLog(Long id) throws GlobalException{
		if(StringUtils.isEmpty(id)) {
			throw new GlobalException(PlatformOrderExceptionEnum.PLATFORMORDER_ID_NULL);
		}
		PlatformPayOrderLogDto platformPayOrderLogDto=platformOrderService.queryPayOrderLogId(id);
		return new ResponseInfo(platformPayOrderLogDto);
	}

}
