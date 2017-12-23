package com.topaiebiz.system.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.system.security.entity.SystemRoleEntity;

@Mapper
public interface SystemRoleDao extends BaseDao<SystemRoleEntity> {

	List<SystemRoleEntity> selectList();

	/**
	 * Description： 根据用户查询对应的角色
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param id
	 * @return
	 */
	List<SystemRoleEntity> selectByUserId(Long id);
}
