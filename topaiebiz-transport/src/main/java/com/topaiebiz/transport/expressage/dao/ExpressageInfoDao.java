package com.topaiebiz.transport.expressage.dao;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.transport.expressage.entity.ExpressageInfoEntity;

/**
 * Description 快递信息持久层 
 * 
 * Author Aaron.Xue 
 *    
 * Date 2017年10月17日 下午9:07:04 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface ExpressageInfoDao extends BaseDao<ExpressageInfoEntity> {

	/**
	 * Description 根据单号查询快递信息。
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param number
	 * @return
	 */
	ExpressageInfoEntity selectExpressageInfo(String number);

}
