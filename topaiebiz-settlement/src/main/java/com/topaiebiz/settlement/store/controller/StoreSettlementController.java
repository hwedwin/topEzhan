package com.topaiebiz.settlement.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.settlement.store.dto.StoreSettlementDto;
import com.topaiebiz.settlement.store.service.StoreSettlementService;

/**
 * Description： 商家结算Controller。
 * 
 * Author Harry
 *    
 * Date 2017年10月31日 下午1:26:44 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@RestController
@RequestMapping("settlement/store")
public class StoreSettlementController {
	
	@Autowired 
	private StoreSettlementService  storeSettlementService;

	
	/**
	 * Description： 平台端店铺销售结算 。
	 * 
	 * Author Harry  
	 * 
	 * @param page
	 * @param goodsDistributionLogDto
	 * @return
	 */
	@RequestMapping(path = "getPlatStoreSettlementList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getPlatStoreSettlementList(Page<StoreSettlementDto> page,
			StoreSettlementDto storeSettlementDto) {
		Page<StoreSettlementDto> goodsdistributionallocationlist = storeSettlementService
				.getPlatformGoodsDistributionList(page, storeSettlementDto);
		return new ResponseInfo(goodsdistributionallocationlist);
	}
	/**
	 * Description：  平台端店铺销售结算详情根据店铺id。
	 * 
	 * Author Harry  
	 * 
	 * @param page
	 * @param storeSettlementDto
	 * @return
	 */
	@RequestMapping(path = "getPlatStoreSettlementListByStoreId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getPlatStoreSettlementListByStoreId(Page<StoreSettlementDto> page,
			StoreSettlementDto storeSettlementDto) {
		Page<StoreSettlementDto> goodsdistributionallocationlist = storeSettlementService
				.getPlatStoreSettlementListByStoreId(page, storeSettlementDto);
		return new ResponseInfo(goodsdistributionallocationlist);
	}
	
	/**
	 * Description： 平台端店铺分润结算列表分页加搜索。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * @param storeSettlementDto
	 * @return
	 */
	@RequestMapping(path = "getPlatStoreDistributionSettlementList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getPlatStoreDistributionSettlementList(Page<StoreSettlementDto> page,
			StoreSettlementDto storeSettlementDto) {
		Page<StoreSettlementDto> goodsdistributionallocationlist = storeSettlementService
				.getPlatStoreDistributionSettlementList(page, storeSettlementDto);
		return new ResponseInfo(goodsdistributionallocationlist);
	}
	
	/**
	 * Description：   平台端店铺分润结算列表分页加搜索根据店铺id。
	 * 
	 * Author Harry   
	 * 
	 * @param page
	 * @param storeSettlementDto
	 * @return
	 */
	@RequestMapping(path = "getPlatStoreDistributionSettlementListByStoreId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getPlatStoreDistributionSettlementListByStoreId(Page<StoreSettlementDto> page,
			StoreSettlementDto storeSettlementDto) {
		Page<StoreSettlementDto> goodsdistributionallocationlist = storeSettlementService
				.getPlatStoreDistributionSettlementListByStoreId(page, storeSettlementDto);
		return new ResponseInfo(goodsdistributionallocationlist);
	}
	/**
	 * Description：商家端店铺销售结算列表分页加搜索。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * @param storeSettlementDto
	 * @return
	 */
	@RequestMapping(path = "getMerchantStoreSettlementList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantStoreSettlementList(Page<StoreSettlementDto> page,
			StoreSettlementDto storeSettlementDto) {
		Page<StoreSettlementDto> goodsdistributionallocationlist = storeSettlementService
				.getMerchantStoreSettlementList(page, storeSettlementDto);
		return new ResponseInfo(goodsdistributionallocationlist);
	}
	/**
	 * Description：   商家端分润结算列表分页加搜索。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * @param storeSettlementDto
	 * @return
	 */
	@RequestMapping(path = "getMerchantStoreDistributionSettlementList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantStoreDistributionSettlementList(Page<StoreSettlementDto> page,
			StoreSettlementDto storeSettlementDto) {
		Page<StoreSettlementDto> goodsdistributionallocationlist = storeSettlementService
				.getMerchantStoreDistributionSettlementList(page, storeSettlementDto);
		return new ResponseInfo(goodsdistributionallocationlist);
	}
	/**
	 * Description：商家端店铺销售结算根据storeId查询分页加搜索。
	 * 
	 * Author Harry  
	 * 
	 * @param page
	 * @param storeSettlementDto
	 * @return
	 */
	@RequestMapping(path = "getMerchantStoreSettlementListByStoreId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantStoreSettlementListByStoreId(Page<StoreSettlementDto> page,
			StoreSettlementDto storeSettlementDto) {
		Page<StoreSettlementDto> goodsdistributionallocationlist = storeSettlementService
				.getMerchantStoreSettlementListByStoreId(page, storeSettlementDto);
		return new ResponseInfo(goodsdistributionallocationlist);
	}
	
	/**
	 * Description：   商家端分润结算列表分页加搜索根据店铺id。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 * @param storeSettlementDto
	 * @return
	 */
	@RequestMapping(path = "getMerchantStoreDistributionSettlementListByStoreId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantStoreDistributionSettlementListByStoreId(Page<StoreSettlementDto> page,
			StoreSettlementDto storeSettlementDto) {
		Page<StoreSettlementDto> goodsdistributionallocationlist = storeSettlementService
				.getMerchantStoreDistributionSettlementListByStoreId(page, storeSettlementDto);
		return new ResponseInfo(goodsdistributionallocationlist);
	}
	
	/**
	 * Description：   定时计算商家的结算。
	 * 
	 * Author Harry  
	 * 
	 * @return
	 * @throws GlobalException
	 */
	/*@Scheduled(cron = "0 12 16 ? * * ")
	@RequestMapping(path = "/addStoreSettlement", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addStoreSettlement() throws GlobalException {
		return new ResponseInfo(storeSettlementService.saveStoreSettlement());
	}*/
	/**
	 * Description：修改店铺结算状态 
	 * 
	 * Author Harry   
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/updateStoreSettlementState", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo updateStoreSettlementState(Long id) throws GlobalException {
		return new ResponseInfo(storeSettlementService.updateStoreSettlementState(id));
	}
	
	/**
	 * Description： 计算店铺详情商品结算。 
	 * 
	 * Author Harry
	 *
	 */
	@RequestMapping(path = "/getStoreSettlementDetailsList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getStoreSettlementDetailsList() throws GlobalException{
	return new ResponseInfo(storeSettlementService.getStoreSettlementDetailsList());
	}
	
}
