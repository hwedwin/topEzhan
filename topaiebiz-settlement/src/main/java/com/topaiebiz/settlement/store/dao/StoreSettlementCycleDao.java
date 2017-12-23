package com.topaiebiz.settlement.store.dao;


import org.apache.ibatis.annotations.Mapper;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.settlement.store.entity.StoreSettlementCycleEntity;

/**
 * Description： 商家结算Dao。
 * 
 * Author Harry 
 *    
 * Date 2017年10月31日 下午2:13:41 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface StoreSettlementCycleDao  extends BaseDao<StoreSettlementCycleEntity>{
	
}