package com.topaiebiz.sms.dahantc.dao;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.sms.dahantc.entity.CaptchaEntity;

/**
 * Description： 验证码持久层接口 
 * 
 * Author Aaron.Xue 
 *    
 * Date 2017年11月6日 下午3:25:24 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface CaptchaDao extends BaseDao<CaptchaEntity>{

	/**
	 * Description： 根据电话查询验证码数据是否存在  
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param telephone
	 * @return
	 */
	CaptchaEntity selectByTelephone(String telephone);

}
