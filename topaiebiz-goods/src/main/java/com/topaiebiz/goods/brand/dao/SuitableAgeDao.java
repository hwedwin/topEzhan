package com.topaiebiz.goods.brand.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.goods.brand.dto.SuitableAgeDto;
import com.topaiebiz.goods.brand.entity.SuitableAgeEntity;
/**
 * Description 年龄段dao  
 * 
 * Author Hedda 
 *    
 * Date 2017年10月6日 下午1:55:56 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
@Mapper
public interface SuitableAgeDao extends BaseDao<SuitableAgeEntity>{

	/**
	 * Description pc端查询年龄段列表
	 * 
	 * Author Hedda  
	 * 
	 * @return
	 */
	List<SuitableAgeDto> selectListSuitableAge();

	/**
	 * Description app端查询年龄段列表  
	 * 
	 * Author Hedda  
	 * 
	 * @return
	 */
	List<SuitableAgeDto> selectAppListSuitableAge();

}
