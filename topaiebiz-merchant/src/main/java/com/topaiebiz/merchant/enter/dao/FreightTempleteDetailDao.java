/**
 * 
 */
package com.topaiebiz.merchant.enter.dao;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.merchant.enter.dto.AddFreightTempleteDto;
import com.topaiebiz.merchant.enter.entity.FreightTempleteDetailEntity;

import java.util.List;

/**
 * Description： 运费模板详情dao接口
 * 
 * 
 * Author hxpeng
 * 
 * Date 2017年10月24日 上午9:32:21
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public interface FreightTempleteDetailDao extends BaseDao<FreightTempleteDetailEntity> {

	/**
	 * 
	 * Description： 查询运费模板的详情
	 * 
	 * Author hxpeng
	 * 
	 * @param freightId
	 * @return 运费模板详情实体类
	 */
	List<FreightTempleteDetailEntity> selectOneByFreightId(Long freightId);

	/**
	 * Description： 运费模板修改回显。
	 * 
	 * Author: Anthony
	 * 
	 * param : id 运费模板id
	 */
	AddFreightTempleteDto selectMerFreightTempleteById(Long id);

	Long selectFreightTempleteDetailById(Long id);

	void updateByfreightTempleteDetailById(Long freightId);

	//void updateByfreightTempleteDetailById(FreightTempleteDetailEntity freightTempleteDetailEntity);
	
	List<FreightTempleteDetailEntity> selectDetailByTeleplateId(Long freightId);
    
	/**
	 * Description：  根据模板id删除模板详情。 
	 * 
	 * Author: Anthony   
	 * 
	 * @param id
	 */
	void deleteByTemplateId(Long id);
	

}
