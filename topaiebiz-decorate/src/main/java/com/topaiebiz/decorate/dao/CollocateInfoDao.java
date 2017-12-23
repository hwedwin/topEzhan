package com.topaiebiz.decorate.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.decorate.dto.CollocateInfoDto;
import com.topaiebiz.decorate.entity.CollocateInfoEntity;

@Mapper
public interface CollocateInfoDao extends BaseDao<CollocateInfoEntity> {

	/**
	 * Description： 根据装修类型ID，排序号查询 模版配置详情
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param collocateType
	 * @param sortNo
	 * @return
	 */
	CollocateInfoEntity selectByCollocateTypeAndSortNo(@Param("collocateType") Long collocateType, @Param("sortNo") Integer sortNo);

	/***
	 * Description：根据typeID查询详情 
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param id
	 * @return
	 */
	List<CollocateInfoDto> selectListByType(Long id);


}
