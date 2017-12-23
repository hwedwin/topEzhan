package com.topaiebiz.transport.expressage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topaiebiz.transport.expressage.dao.ExpressageInfoDao;
import com.topaiebiz.transport.expressage.dao.ExpressageSubscriptionLogDao;
import com.topaiebiz.transport.expressage.dao.LogisticsCompanyDao;
import com.topaiebiz.transport.expressage.dto.ExpressageDto;
import com.topaiebiz.transport.expressage.dto.LogisticsCompanyDto;
import com.topaiebiz.transport.expressage.entity.ExpressageInfoEntity;
import com.topaiebiz.transport.expressage.entity.ExpressageSubscriptionLogEntity;
import com.topaiebiz.transport.expressage.service.ExpressageService;

/**
 * 
 * Description 快递处理业务层 实现类 
 * 
 * Author Aaron.Xue 
 *    
 * Date 2017年10月17日 下午9:11:02 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
public class ExpressageServiceImpl implements ExpressageService {
	
	@Autowired
	private ExpressageInfoDao expressageInfoDao;
	
	@Autowired
	private ExpressageSubscriptionLogDao expressageSubscriptionLogDao;
	
	@Autowired
	private LogisticsCompanyDao logisticsCompanyDao;

	@Override
	public ExpressageDto getExpressageInfo(ExpressageDto expressageDto) {
		//根据快递单号查询出快递信息
		ExpressageInfoEntity expressageInfo = expressageInfoDao.selectExpressageInfo(expressageDto.getNumber());
		//取出快递信息
		if(expressageInfo == null) { //空的 
			return null;
		}
		String dataStr = expressageInfo.getData();
		String[] dataArray = dataStr.split("[$]");
		BeanUtils.copyProperties(expressageInfo, expressageDto);
		expressageDto.setDataArray(dataArray);
		//返回快递信息1
		return expressageDto;
	}

	@Override
	public void saveExpressageSubscriptionLog(ExpressageSubscriptionLogEntity entity) {
		entity.setLastModifiedTime(new Date());
		expressageSubscriptionLogDao.insert(entity);
	}

	@Override
	public void saveExpressageInfo(ExpressageInfoEntity entity) {
		entity.setLastModifiedTime(new Date());
		expressageInfoDao.insert(entity);
	}

	@Override
	public List<LogisticsCompanyDto> getListLogisticsCompany() {
		return logisticsCompanyDao.selectListLogisticsCompany();
	}

}
