package com.topaiebiz.decorate.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.decorate.dto.CollocateDto;
import com.topaiebiz.decorate.entity.CollocateTypeEntity;

@Mapper
public interface CollocateTypeDao extends BaseDao<CollocateTypeEntity> {

	/**
	 * Description 根据店铺ID，装修类型，模版类型 查询模版
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param storeId
	 * @param decType
	 * @param templateId
	 * @return
	 */
	CollocateTypeEntity selectBydecTypeAndTeleplate(@Param("storeId")Long storeId, @Param("decType") String decType, @Param("templateId") Long templateId);

	/**
	 * Description： 根据店铺ID,模版ID，装饰类型查询类型信息 
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param collocateDto
	 * @return
	 */
	CollocateDto selectListCollocate(CollocateDto collocateDto);

	
}
