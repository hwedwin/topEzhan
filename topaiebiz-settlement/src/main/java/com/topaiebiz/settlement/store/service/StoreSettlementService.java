package com.topaiebiz.settlement.store.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.topaiebiz.settlement.store.dto.StoreSettlementDto;

/**
 * Description： 店铺结算的接口。  
 * 
 * Author Harry
 *    
 * Date 2017年10月31日 下午5:09:53 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public interface StoreSettlementService {
	
	/**
	 * Description： 平台端店铺销售结算 。 
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * @param storeSettlementDto
	 * @return
	 */
	Page<StoreSettlementDto> getPlatformGoodsDistributionList(Page<StoreSettlementDto> page,
			StoreSettlementDto storeSettlementDto);
	
	/**
	 * Description：商家端店铺销售结算列表分页加搜索。
	 * 
	 * Author Harry  
	 * 
	 * @param page
	 * @param storeSettlementDto
	 * @return
	 */
	Page<StoreSettlementDto> getMerchantStoreSettlementList(Page<StoreSettlementDto> page,
			StoreSettlementDto storeSettlementDto);
	/**
	 * Description：  定时计算商家的结算。 
	 * 
	 * Author Harry   
	 * 
	 * @return
	 */
	Integer saveStoreSettlement();
	
	/**
	 * Description：  平台端店铺分润结算列表分页加搜索。 
	 * 
	 * Author Harry   
	 * 
	 * @param page
	 * @param storeSettlementDto
	 * @return
	 */
	Page<StoreSettlementDto> getPlatStoreDistributionSettlementList(Page<StoreSettlementDto> page,
			StoreSettlementDto storeSettlementDto);
	
	/**
	 * Description：  平台端店铺销售结算详情根据店铺id。
	 * 
	 * Author Harry  
	 * 
	 * @param page
	 * @param storeSettlementDto
	 * @return
	 */
	Page<StoreSettlementDto> getPlatStoreSettlementListByStoreId(Page<StoreSettlementDto> page,
			StoreSettlementDto storeSettlementDto);
	
	/**
	 * Description： 平台端店铺分润结算列表分页加搜索根据店铺id。
	 * 
	 * Author Harry   
	 * 
	 * @param page
	 * @param storeSettlementDto
	 * @return
	 */
	Page<StoreSettlementDto> getPlatStoreDistributionSettlementListByStoreId(Page<StoreSettlementDto> page,
			StoreSettlementDto storeSettlementDto);
	
	/**
	 * Description： 商家端店铺销售结算根据storeId查询分页加搜索。 
	 * 
	 * Author Harry 
	 * 
	 * @param page
	 * @param storeSettlementDto
	 * @return
	 */
	Page<StoreSettlementDto> getMerchantStoreSettlementListByStoreId(Page<StoreSettlementDto> page,
			StoreSettlementDto storeSettlementDto);
	
	/**
	 * Description： 商家端分润结算列表分页加搜索。 
	 * 
	 * Author Harry  
	 * 
	 * @param page
	 * @param storeSettlementDto
	 * @return
	 */
	Page<StoreSettlementDto> getMerchantStoreDistributionSettlementList(Page<StoreSettlementDto> page,
			StoreSettlementDto storeSettlementDto);
	
	/**
	 * Description： 商家端分润结算列表分页加搜索根据店铺id。 
	 * 
	 * Author Harry  
	 * 
	 * @param page
	 * @param storeSettlementDto
	 * @return
	 */
	Page<StoreSettlementDto> getMerchantStoreDistributionSettlementListByStoreId(Page<StoreSettlementDto> page,
			StoreSettlementDto storeSettlementDto);
	
	/**
	 * Description： 修改店铺结算状态
	 * 
	 * Author Harry 
	 * 
	 * @param id
	 * @return
	 */
	Integer updateStoreSettlementState(Long id);
	
	/**
	 * Description： 计算店铺结算详情。
	 * 
	 * Author Harry
	 * 
	 * @return
	 */
	List<StoreSettlementDto> getStoreSettlementDetailsList();

	


}
