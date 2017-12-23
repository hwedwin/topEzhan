package com.topaiebiz.system.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.system.security.entity.SystemResourceEntity;

@Mapper
public interface SystemResourceDao extends BaseDao<SystemResourceEntity> {

	List<SystemResourceEntity> selectList();

	/**
	 * Description：根据角色ID，查询对应的资源  
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param id
	 * @return
	 */
	List<SystemResourceEntity> selectByRoleId(Long id);
}
