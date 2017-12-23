package com.topaiebiz.nebula.ssm.demo.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.nebula.ssm.demo.user.entity.UserEntity;

@Mapper
public interface UserDao extends BaseDao<UserEntity> {

	List<UserEntity> selectList();

}
