package com.topaiebiz.decorate.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.decorate.entity.CollocateGoodsEntity;

@Mapper
public interface CollocateGoodsDao extends BaseDao<CollocateGoodsEntity> {

	/**
	 * Description： 查询15调配置商品 
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param id
	 * @return
	 */
	List<CollocateGoodsEntity> selectListByType(Long collocateTypeId);
	
	/**
	 * Description： 查询所有配置商品
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param id
	 * @return
	 */
	List<CollocateGoodsEntity> selectAllListByType(Long collocateTypeId);

}
