package com.topaiebiz.settlement.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.merchant.info.entity.StoreInfoEntity;
import com.topaiebiz.promotion.mgmt.entity.PromotionEntity;
import com.topaiebiz.settlement.store.dto.StoreSettlementDto;
import com.topaiebiz.settlement.store.entity.StoreSettlementEntity;

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
public interface StoreSettlementDao  extends BaseDao<StoreSettlementEntity>{
	
	/**
	 * Description： 平台端店铺销售结算。 
	 * 
	 * Author Harry 
	 * @param page 
	 * 
	 * @param storeSettlementDto
	 * @return
	 */
	List<StoreSettlementDto> selectPlatformGoodsDistributionList(Page<StoreSettlementDto> page, StoreSettlementDto storeSettlementDto);
	
	/**
	 * Description： 商家端店铺销售结算列表分页加搜索。 
	 * 
	 * Author Harry  
	 * @param page 
	 * 
	 * @param storeSettlementDto
	 * @return
	 */
	List<StoreSettlementDto> selectMerchantStoreSettlementList(Page<StoreSettlementDto> page, StoreSettlementDto storeSettlementDto);
	
	/**
	 * Description： 查询店铺订单对应的 优惠后金额、美礼卡支付金额、积分抵扣金额、实际支付金额 。
	 * 
	 * Author Harry
	 * 
	 * @return
	 */
	List<StoreSettlementDto> selectStoreOrderList();
	
	/**
	 * Description：计算分销金额根据StoreId。 
	 * 
	 * Author Harry  
	 * 
	 * @param storeId
	 * @return
	 */
	Double findDistSumByStoreId(Long storeId);
	
	/**
	 * Description：根据店铺id查询该店铺的总订单。
	 * 
	 * Author Harry
	 * 
	 * @param storeId
	 * @return
	 */
	List<StoreSettlementDto> selectTotalOrderByStoreId(Long storeId);

	/**
	 * Description： 根据所用支付级营销活动查询营销活动。
	 * 
	 * Author Harry   
	 * 
	 * @param platformPromotion
	 * @return
	 */
	PromotionEntity selectPromotionByPlatformPromotion(Long platformPromotion);

	/**
	 * Description： 根据店铺id查询对应商家
	 * 
	 * Author Harry
	 * 
	 * @param storeId
	 * @return
	 */
	StoreInfoEntity selectMerchantByStore(Long storeId);
	
	/**
	 * Description： 根据总订单id查询订单使用美礼卡情况。 
	 * 
	 * Author Harry 
	 * 
	 * @param totalOrderId
	 * @return
	 */
	List<StoreSettlementDto> selectCardInfoByTotalOrederId(Long totalOrderId);
	
	/**
	 * Description： 根据店铺id查询平台订单的id与关联详情表的信息获取具体的SkuId。 
	 * 
	 * Author Harry  
	 * 
	 * @param storeId
	 * @return
	 */
	List<StoreSettlementDto> selectPlatStoreorderInfoByStoreId(Long storeId);
	
	/**
	 * Description：  平台端店铺分润结算列表分页加搜索。
	 * 
	 * Author Harry
	 * @param page 
	 * 
	 * @param storeSettlementDto
	 * @return
	 */
	List<StoreSettlementDto> getPlatStoreDistributionSettlementList(Page<StoreSettlementDto> page, StoreSettlementDto storeSettlementDto);
	
	/**
	 * Description：平台端店铺销售结算详情根据店铺id。
	 * 
	 * Author Harry   
	 * @param page 
	 * 
	 * @param storeSettlementDto
	 * @return
	 */
	List<StoreSettlementDto> selectPlatStoreSettlementListByStoreId(Page<StoreSettlementDto> page, StoreSettlementDto storeSettlementDto);
	
	/**
	 * Description：平台端店铺分润结算列表分页加搜索根据店铺id。  
	 * 
	 * Author Harry 
	 * @param page 
	 * 
	 * @param storeSettlementDto
	 * @return
	 */  
	List<StoreSettlementDto> getPlatStoreDistributionSettlementListByStoreId(Page<StoreSettlementDto> page, StoreSettlementDto storeSettlementDto);
	
	/**
	 * Description： 商家端店铺销售结算根据storeId查询分页加搜索。 
	 * 
	 * Author Harry  
	 * @param page 
	 * 
	 * @param storeSettlementDto
	 * @return
	 */
	List<StoreSettlementDto> selectMerchantStoreSettlementListByStoreId(Page<StoreSettlementDto> page, StoreSettlementDto storeSettlementDto);
	/**
	 * Description：根据skuId,storeId查询店铺分销金额。
	 * 
	 * Author Harry 
	 * 
	 * @param skuId
	 * @param storeId
	 * @return
	 */
	Double selectGoodsDistributionPrice(@Param("skuId")Long skuId, @Param("storeId")Long storeId);

	/**
	 * Description：根据skuId,storeId查询店铺会员分销金额.
	 * 
	 * Author Harry
	 * 
	 * @param skuId
	 * @param storeId
	 * @return
	 */
	Double selectMemberDistributionPrice(@Param("skuId")Long skuId, @Param("storeId")Long storeId);
	
	/**
	 * Description：商家端分润结算列表分页加搜索。
	 * 
	 * Author Harry  
	 * @param page 
	 * 
	 * @param storeSettlementDto
	 * @return
	 */
	List<StoreSettlementDto> selectMerchantStoreDistributionSettlementList(Page<StoreSettlementDto> page, StoreSettlementDto storeSettlementDto);
	
	/**
	 * Description：商家端分润结算列表分页加搜索根据店铺id。  
	 * 
	 * Author Harry   
	 * @param page 
	 * 
	 * @param storeSettlementDto
	 * @return
	 */
	List<StoreSettlementDto> selectMerchantStoreDistributionSettlementListByStoreId(Page<StoreSettlementDto> page, StoreSettlementDto storeSettlementDto);
	
	/**
	 * Description： 修改地店铺计算的状态
	 * 
	 * Author Harry   
	 * 
	 * @param id
	 * @return
	 */
	Integer updateStoreSettlementState(Long id);
	
	/**
	 * Description： 根据storeId查询平台抽佣金
	 * 
	 * Author Harry   
	 * 
	 * @param storeId
	 * @return
	 */
	Double selectBrokerageRatioListByStoreId(Long goodsId);

	/**
	 * 
	 * Description： 根据storeId查询所有的总订单 
	 * 
	 * Author Harry
	 * 
	 * @param storeId
	 * @return
	 */
	List<StoreSettlementDto> selectTotalOrderByStoreIds(Long storeId);
	
	

}
