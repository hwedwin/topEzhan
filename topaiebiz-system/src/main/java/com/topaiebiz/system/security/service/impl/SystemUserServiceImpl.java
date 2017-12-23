package com.topaiebiz.system.security.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.system.security.dao.SystemResourceDao;
import com.topaiebiz.system.security.dao.SystemRoleDao;
import com.topaiebiz.system.security.dao.SystemRoleResourceDao;
import com.topaiebiz.system.security.dao.SystemUserDao;
import com.topaiebiz.system.security.dto.SecurityMemberDto;
import com.topaiebiz.system.security.dto.SystemUserDto;
import com.topaiebiz.system.security.entity.SystemResourceEntity;
import com.topaiebiz.system.security.entity.SystemRoleEntity;
import com.topaiebiz.system.security.entity.SystemRoleResourceEntity;
import com.topaiebiz.system.security.entity.SystemUserEntity;
import com.topaiebiz.system.security.exception.SystemExceptionEnum;
import com.topaiebiz.system.security.service.SystemUserService;

/**
 * Description： 系统权限用户表业务层
 * 
 * Author Aaron.Xue
 * 
 * Date 2017年10月28日 下午8:11:51
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service //, UserDetailsService
public class SystemUserServiceImpl implements SystemUserService, UserDetailsService {
	
	@Autowired
	private SystemUserDao systemUserDao;
	
	@Autowired
	private SystemRoleDao systemRoleDao;
	
	@Autowired
	private SystemResourceDao systemResourceDao;
	
	@Autowired
	private SystemRoleResourceDao systemRoleResourceDao;

	@Override
	public SystemUserEntity login(SystemUserDto systemUserDto) throws GlobalException {
//		SystemUserEntity entity = new SystemUserEntity();
//		BeanUtils.copyProperties(systemUserDto, entity);
//		String password = MD5Utils.encode(entity.getPassword());
//		entity.setPassword(password);
//		/**根据用户名or手机号 ，密码 查询用户。*/
//		SystemUserEntity systemUserEntity = systemUserDao.selectUserByUsernameOrTelephone(entity);
//		SecurityMemberDto selectUserByMember = systemUserDao.selectUserByMember(entity.getUsername());
//		/**入驻的，需要到用户表中查找*/
//		if("4".equals(systemUserDto.getType())) {
//			/**根据用户名或密码查询。*/
//			if(null == selectUserByMember) {
//				throw new GlobalException(SystemExceptionEnum.USERNAME_OR_PASSWORD_ERROR);
//			}
//			/**入驻成功的登录商家。*/
//			if(null != systemUserEntity) {
//				throw new GlobalException(SystemExceptionEnum.USER_TYPE_CORRESPONDENCE);
//			}
//			entity.setId(selectUserByMember.getId());
//			entity.setUsername(selectUserByMember.getUserName());
//			entity.setPassword("******");
//			entity.setMobilePhone(selectUserByMember.getTelephone());
//			entity.setMerchantId(selectUserByMember.getMerchantId());
//			return entity;
//		}
//		/**用户不存在。*/
//		if(null == systemUserEntity) {
//			throw new GlobalException(SystemExceptionEnum.USERNAME_OR_PASSWORD_ERROR);
//		}
//		/**权限不对。*/
//		if(!systemUserDto.getType().equals(String.valueOf(systemUserEntity.getType()))) {
//			throw new GlobalException(SystemExceptionEnum.USER_TYPE_ERROR);
//		}else {
//			systemUserEntity.setPassword("******");
//			systemUserEntity.setMobilePhone(selectUserByMember.getTelephone());
//			systemUserEntity.setMerchantId(selectUserByMember.getMerchantId());
//			
//			Set<SystemResourceEntity> resoucesSet = new HashSet<SystemResourceEntity>();
//	        
//	        List<SystemRoleEntity> roleList = systemRoleDao.selectByUserId(systemUserEntity.getId());
//	        
//	        for (SystemRoleEntity systemRole : roleList) {
//				List<SystemResourceEntity> resourceList = systemResourceDao.selectByRoleId(systemRole.getId());
//				resoucesSet.addAll(resourceList);
//			}
//	        List<String> urlList = this.parseUrl(resoucesSet);
//	        this.formatResource(resoucesSet);
//	        System.out.println(resoucesSet.size());
//	        systemUserEntity.setResoucesSet(resoucesSet);
//	        systemUserEntity.setUrlList(urlList);
//			
//			return systemUserEntity;
//		}
		return null;
	}

	@Override
	public Boolean modifySystemUser(String telephone, Integer type) throws GlobalException {
		SystemUserEntity systemUser = systemUserDao.selectUserByTelephone(telephone);
		if(null != systemUser) {
			systemUser.setType(type);
			systemUserDao.updateById(systemUser);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String[] userList = username.split(",");
		String userName = "";
		String type = "";
		if(userList != null && userList.length > 1) {
			userName = userList[0];
			type = userList[1];
		}
		SystemUserEntity user = systemUserDao.selectUserByUsername(userName);
		SecurityMemberDto selectUserByMember = systemUserDao.selectUserByMember(userName);
		SystemUserEntity entity = new SystemUserEntity();
		if("4".equals(type)) {
			/**根据用户名或密码查询。*/
			if(null == selectUserByMember) {
				throw new GlobalException(SystemExceptionEnum.USERNAME_OR_PASSWORD_ERROR);
			}
			/**入驻成功的登录商家。*/
			if(null != user) {
				throw new GlobalException(SystemExceptionEnum.USER_TYPE_CORRESPONDENCE);
			}
			entity.setId(selectUserByMember.getId());
			entity.setUsername(selectUserByMember.getUserName());
			entity.setPassword(selectUserByMember.getPassword());
			entity.setMobilePhone(selectUserByMember.getTelephone());
			entity.setMerchantId(selectUserByMember.getMerchantId());
			List<SystemResourceEntity> resourceList = systemResourceDao.selectByRoleId(3001L);
			Set<SystemResourceEntity> resoucesSet = new HashSet<SystemResourceEntity>();         
			resoucesSet.addAll(resourceList);//给set填充 
			List<String> urlList = this.parseUrl(resoucesSet);
	        this.formatResource(resoucesSet);
	        entity.setResoucesSet(resoucesSet);
	        entity.setUrlList(urlList);
			return entity;
		}
		//用户不存在
        if(user == null){
            throw new GlobalException(SystemExceptionEnum.USER_DONT_EXIST);
        }
        /**权限不对。*/
		if(!type.equals(String.valueOf(user.getType()))) {
			throw new GlobalException(SystemExceptionEnum.USER_TYPE_ERROR);
		}
        Set<SystemResourceEntity> resoucesSet = this.getAllResource(user.getId());
        List<String> urlList = this.parseUrl(resoucesSet);
        this.formatResource(resoucesSet);
        System.out.println(resoucesSet.size());
        user.setResoucesSet(resoucesSet);
        user.setUrlList(urlList);
        user.setMerchantId(selectUserByMember.getMerchantId());
        return user;
	}
	
	/**获取用户的所有资源*/
	private Set<SystemResourceEntity> getAllResource(Long userId){
		Set<SystemResourceEntity> resoucesSet = new HashSet<SystemResourceEntity>();
        List<SystemRoleEntity> roleList = systemRoleDao.selectByUserId(userId);
        for (SystemRoleEntity systemRoleEntity : roleList) {
			List<SystemResourceEntity> resourceList = systemResourceDao.selectByRoleId(systemRoleEntity.getId());
			resoucesSet.addAll(resourceList);
		}
        return resoucesSet;
	}
	
	/**
	 * Description： 所有权限的URL 
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param resoucesSet
	 * @return
	 */
	private List<String> parseUrl(Set<SystemResourceEntity> resoucesSet) {
		List<String> urlList = new ArrayList<String>();
		for (SystemResourceEntity systemResourceEntity : resoucesSet) {
			if(systemResourceEntity.getAccessUrl() != null && !"".equals(systemResourceEntity.getAccessUrl())) {
				String[] split = systemResourceEntity.getAccessUrl().split(",");
				for (String s : split) {
					urlList.add(s);
				}
			}
		}
		return urlList;
	}

	/**
	 * Description： 将资源构建成资源树  
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param resoucesSet
	 * @return
	 */
	private Set<SystemResourceEntity> formatResource(Set<SystemResourceEntity> resoucesSet){
		List<SystemResourceEntity> delList = new ArrayList<SystemResourceEntity>();
		for (SystemResourceEntity systemResourceEntity : resoucesSet) {
			//子权限集合
			List<SystemResourceEntity> childList = new ArrayList<SystemResourceEntity>();
			//查询出所有子权限
			for (SystemResourceEntity systemResource : resoucesSet) {
				if(systemResource.getParentId().equals(systemResourceEntity.getId())) {
					childList.add(systemResource);
					delList.add(systemResource);
				}
			}
			systemResourceEntity.setChildList(childList);
		}
		resoucesSet.removeAll(delList);
		for (SystemResourceEntity systemResourceEntity : resoucesSet) {
			if(systemResourceEntity.getParentId() != 0) {
				delList.add(systemResourceEntity);
			}
		}
		resoucesSet.removeAll(delList);
		return resoucesSet;
	}

	@Override
	public void userTest(Long userId) {
		List<SystemResourceEntity> selectList = systemResourceDao.selectList();
		for (SystemResourceEntity systemResourceEntity : selectList) {
			String valueOf = String.valueOf(systemResourceEntity.getId());
			if(valueOf.startsWith("1")) {
				SystemRoleResourceEntity roleResource = new SystemRoleResourceEntity();
				roleResource.setResourceId(systemResourceEntity.getId());
				roleResource.setRoleId(1001L);
				systemRoleResourceDao.insert(roleResource);
			}
		}
		
	}
	
	
}
