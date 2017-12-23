package com.topaiebiz.transport.expressage.service;

import java.util.List;

import com.topaiebiz.transport.expressage.dto.ExpressageDto;
import com.topaiebiz.transport.expressage.dto.LogisticsCompanyDto;
import com.topaiebiz.transport.expressage.entity.ExpressageInfoEntity;
import com.topaiebiz.transport.expressage.entity.ExpressageSubscriptionLogEntity;

/**
 * Description 快递处理业务层 
 * 
 * Author Aaron.Xue 
 *    
 * Date 2017年10月17日 下午9:09:09 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public interface ExpressageService {

	/**
	 * Description 根据条件查询物流信息
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param expressageDto
	 * @return
	 */
	ExpressageDto getExpressageInfo(ExpressageDto expressageDto);

	/**
	 * Description 保存订阅日志
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param entity
	 */
	void saveExpressageSubscriptionLog(ExpressageSubscriptionLogEntity entity);

	/**
	 * Description 添加快递信息
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param entity
	 */
	void saveExpressageInfo(ExpressageInfoEntity entity);

	/**
	 * Description 获取物流公司
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @return
	 */
	List<LogisticsCompanyDto> getListLogisticsCompany();

}
