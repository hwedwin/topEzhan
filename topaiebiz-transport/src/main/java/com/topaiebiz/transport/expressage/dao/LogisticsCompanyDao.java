package com.topaiebiz.transport.expressage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.topaiebiz.transport.expressage.dto.LogisticsCompanyDto;

/**
 * Description TODO 
 * 
 * 
 * Author Aaron.Xue 
 *    
 * Date 2017年10月24日 下午4:46:16 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface LogisticsCompanyDao {

	/**
	 * Description 获取所有物流公司
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @return
	 */
	List<LogisticsCompanyDto> selectListLogisticsCompany();
	
}
