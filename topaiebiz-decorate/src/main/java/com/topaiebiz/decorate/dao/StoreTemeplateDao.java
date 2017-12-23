package com.topaiebiz.decorate.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.decorate.entity.StoreTemeplateEntity;

@Mapper
public interface StoreTemeplateDao extends BaseDao<StoreTemeplateEntity> {

	void deleteStoreTemeplate(Long id);

	/**
	 * Description： 根据店铺ID和模版ID查找数据
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param storeId
	 * @param temeplateId
	 * @return
	 */
	StoreTemeplateEntity selectByStoreIdAndTemeplateId(@Param("storeId") Long storeId, @Param("templateId") Long templateId);

	/**
	 * Description： 根据店铺ID查询正在适用的模版  
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param sotreId
	 * @return
	 */
	StoreTemeplateEntity selectByStoreId(Long sotreId); 

}
