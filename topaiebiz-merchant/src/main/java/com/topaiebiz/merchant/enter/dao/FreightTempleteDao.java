package com.topaiebiz.merchant.enter.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.merchant.enter.dto.FreightTempleteDto;
import com.topaiebiz.merchant.enter.dto.MerFreightTempleteDto;
import com.topaiebiz.merchant.enter.entity.FreightTempleteEntity;

/**
 * Description: 运费模板数据持久层
 * 
 * Author : Anthony
 * 
 * Date :2017年10月13日 下午1:12:22
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface FreightTempleteDao extends BaseDao<FreightTempleteEntity> {

	/**
	 * Description： 查询运费模板详情
	 * 
	 * Author: Anthony
	 * 
	 * param : storeId 店铺id
	 * 
	 * return : 运费模板信息
	 */
	List<FreightTempleteDto> selectFreightTempleteByStoreId(Long storeId);

	/**
	 * Description：平台的运费模板详情。
	 * 
	 * Author: Anthony
	 * 
	 * return : 运费模板详情信息。
	 */
	List<FreightTempleteDto> selectFreightTempleteList(@Param("storeId") Long storeId);

	/**
	 * Description：运费模板列表、详情
	 * 
	 * Author: Anthony
	 * 
	 * @param page
	 * @param merFreightTempleteDto
	 * @return
	 */
	List<MerFreightTempleteDto> selectMerFreightTempleteList(Page<MerFreightTempleteDto> page,
			MerFreightTempleteDto merFreightTempleteDto);

	/**
	 * Description：删除运费模板详情
	 * 
	 * Author: Anthony
	 * 
	 * param : id 运费模板id
	 */
	Integer delectMerFreightTempleteById(Long id);

}
