package com.topaiebiz.settlement.store.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.topaiebiz.system.security.util.SecurityContextUtils;
import com.topaiebiz.distribution.member.dao.MemberDistributionLogDao;
import com.topaiebiz.distribution.member.dto.MemberDistributionLogDto;
import com.topaiebiz.distribution.merchant.dao.GoodsDistributionLogDao;
import com.topaiebiz.goods.category.backend.dao.BackendCategoryDao;
import com.topaiebiz.goods.category.backend.dto.MerchantStoreDto;
import com.topaiebiz.merchant.info.entity.StoreInfoEntity;
import com.topaiebiz.promotion.mgmt.entity.PromotionEntity;
import com.topaiebiz.settlement.store.dao.StoreSettlementCycleDao;
import com.topaiebiz.settlement.store.dao.StoreSettlementDao;
import com.topaiebiz.settlement.store.dto.StoreSettlementDto;
import com.topaiebiz.settlement.store.entity.StoreSettlementCycleEntity;
import com.topaiebiz.settlement.store.entity.StoreSettlementEntity;
import com.topaiebiz.settlement.store.service.StoreSettlementService;

/**
 * Description：店铺结算实现类。
 * 
 * Author Harry
 * 
 * Date 2017年10月31日 下午5:11:15
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
public class StoreSettlementServiceImpl implements StoreSettlementService {

	@Autowired
	private StoreSettlementDao storeSettlementDao;

	@Autowired
	private MemberDistributionLogDao memberDistributionLogDao;

	@Autowired
	private GoodsDistributionLogDao goodsDistributionLogDao;

	@Autowired
	private BackendCategoryDao backendCategoryDao;
	
	@Autowired
	private StoreSettlementCycleDao storeSettlementCycleDao;

	@Override
	public Page<StoreSettlementDto> getPlatformGoodsDistributionList(Page<StoreSettlementDto> page,
			StoreSettlementDto storeSettlementDto) {
		List<StoreSettlementDto> platstoreSettlementDtoList = storeSettlementDao
				.selectPlatformGoodsDistributionList(page, storeSettlementDto);
		page.setRecords(platstoreSettlementDtoList);
		return page;
	}

	@Override
	public Page<StoreSettlementDto> getPlatStoreSettlementListByStoreId(Page<StoreSettlementDto> page,
			StoreSettlementDto storeSettlementDto) {
		List<StoreSettlementDto> platstoreSettlementDtoList = storeSettlementDao
				.selectPlatStoreSettlementListByStoreId(page, storeSettlementDto);
		if (!(platstoreSettlementDtoList == null || platstoreSettlementDtoList.size() == 0)) {
			for (StoreSettlementDto storeSettlementDto2 : platstoreSettlementDtoList) {
				Long skuId = storeSettlementDto2.getSkuId();
				Long storeId = storeSettlementDto2.getStoreId();
				/** 根据skuId,storeId查询店铺分销金额，会员分销金额 */
				Double storeDistributionPrice = storeSettlementDao.selectGoodsDistributionPrice(skuId, storeId);
				if (storeDistributionPrice != null) {
					storeSettlementDto2.setStoreDistributionPrice(storeDistributionPrice);
				}
				Double memberDistributionPrice = storeSettlementDao.selectMemberDistributionPrice(skuId, storeId);
				if (memberDistributionPrice != null) {
					storeSettlementDto2.setMemberDistributionPrice(memberDistributionPrice);
				}
			}
		}
		page.setRecords(platstoreSettlementDtoList);
		return page;
	}

	@Override
	public Page<StoreSettlementDto> getMerchantStoreSettlementList(Page<StoreSettlementDto> page,
			StoreSettlementDto storeSettlementDto) {
		//String telephone = SecurityContextUtils.getCurrentSystemUser().getMobilePhone();
		//MerchantStoreDto merchantStoreDto = backendCategoryDao.getMemberByuserTelephone(telephone);
		// 获得到店铺的id
	//	Long merchantId = merchantStoreDto.getMerchantId();
		storeSettlementDto.setMerchantId(933690115088289793L);
		List<StoreSettlementDto> merchantstoreSettlementDtoList = storeSettlementDao
				.selectMerchantStoreSettlementList(page, storeSettlementDto);
		page.setRecords(merchantstoreSettlementDtoList);
		return page;
	}

	@Override
	public Page<StoreSettlementDto> getMerchantStoreSettlementListByStoreId(Page<StoreSettlementDto> page,
			StoreSettlementDto storeSettlementDto) {
		List<StoreSettlementDto> merchantstoreSettlementDtoList = storeSettlementDao
				.selectMerchantStoreSettlementListByStoreId(page, storeSettlementDto);
		page.setRecords(merchantstoreSettlementDtoList);
		return page;
	}

	@Override
	public Page<StoreSettlementDto> getMerchantStoreDistributionSettlementListByStoreId(Page<StoreSettlementDto> page,
			StoreSettlementDto storeSettlementDto) {
		List<StoreSettlementDto> merchantStoreSettlementLists = storeSettlementDao
				.selectMerchantStoreDistributionSettlementListByStoreId(page, storeSettlementDto);
		if (!(merchantStoreSettlementLists == null || merchantStoreSettlementLists.size() == 0)) {
			for (StoreSettlementDto storeSettlementDto2 : merchantStoreSettlementLists) {
				Long skuId = storeSettlementDto2.getSkuId();
				Long storeId = storeSettlementDto2.getStoreId();
				/** 根据skuId,storeId查询店铺分销金额，会员分销金额 */
				Double storeDistributionPrice = storeSettlementDao.selectGoodsDistributionPrice(skuId, storeId);
				if (storeDistributionPrice != null) {
					storeSettlementDto2.setStoreDistributionPrice(storeDistributionPrice);
				}
				Double memberDistributionPrice = storeSettlementDao.selectMemberDistributionPrice(skuId, storeId);
				if (memberDistributionPrice != null) {
					storeSettlementDto2.setMemberDistributionPrice(memberDistributionPrice);
				}
			}
		}
		page.setRecords(merchantStoreSettlementLists);
		return page;
	}

	@Override
	public Page<StoreSettlementDto> getPlatStoreDistributionSettlementListByStoreId(Page<StoreSettlementDto> page,
			StoreSettlementDto storeSettlementDto) {
		List<StoreSettlementDto> platStoreDistributionSettlementDtoList = storeSettlementDao
				.getPlatStoreDistributionSettlementListByStoreId(page, storeSettlementDto);
		if (!(platStoreDistributionSettlementDtoList == null || platStoreDistributionSettlementDtoList.size() == 0)) {
			for (StoreSettlementDto storeSettlementDto2 : platStoreDistributionSettlementDtoList) {
				Long skuId = storeSettlementDto2.getSkuId();
				Long storeId = storeSettlementDto2.getStoreId();
				/** 根据skuId,storeId查询店铺分销金额，会员分销金额 */
				Double storeDistributionPrice = storeSettlementDao.selectGoodsDistributionPrice(skuId, storeId);
				if (storeDistributionPrice != null) {
					storeSettlementDto2.setStoreDistributionPrice(storeDistributionPrice);
				}
				Double memberDistributionPrice = storeSettlementDao.selectMemberDistributionPrice(skuId, storeId);
				if (memberDistributionPrice != null) {
					storeSettlementDto2.setMemberDistributionPrice(memberDistributionPrice);
				}
			}
		}
		page.setRecords(platStoreDistributionSettlementDtoList);
		return page;
	}

	@Override
	public Page<StoreSettlementDto> getPlatStoreDistributionSettlementList(Page<StoreSettlementDto> page,
			StoreSettlementDto storeSettlementDto) {
		List<StoreSettlementDto> platStoreDistributionSettlementDtoList = storeSettlementDao
				.getPlatStoreDistributionSettlementList(page, storeSettlementDto);
		page.setRecords(platStoreDistributionSettlementDtoList);
		return page;
	}

	@Override
	public Page<StoreSettlementDto> getMerchantStoreDistributionSettlementList(Page<StoreSettlementDto> page,
			StoreSettlementDto storeSettlementDto) {
		String telephone = SecurityContextUtils.getCurrentSystemUser().getMobilePhone();
		MerchantStoreDto merchantStoreDto = backendCategoryDao.getMemberByuserTelephone(telephone);
		// 获得到店铺的id
		Long merchantId = merchantStoreDto.getMerchantId();
		storeSettlementDto.setMerchantId(merchantId);
		List<StoreSettlementDto> merchantStoreDistributionSettlementList = storeSettlementDao
				.selectMerchantStoreDistributionSettlementList(page, storeSettlementDto);
		page.setRecords(merchantStoreDistributionSettlementList);
		return page;
	}

	@Override
	public Integer saveStoreSettlement() {
		Integer i = 0;
		/** 查询可以结算的店铺，状态为6 */
		List<StoreSettlementDto> storeOrederList = storeSettlementDao.selectStoreOrderList();
		/** 商家周期 */
		StoreSettlementCycleEntity storeSettlementCycleEntits = new StoreSettlementCycleEntity();
		StoreSettlementCycleEntity storeSettlementCycleEntits2 = new StoreSettlementCycleEntity();
		if (!(storeOrederList == null || storeOrederList.size() == 0)) {
			for (StoreSettlementDto storeSettlementDto : storeOrederList) {
				Long storeId = storeSettlementDto.getStoreId();
				/** 根据店铺id查询该所有订单 */
				List<StoreSettlementDto> storeSettlementDtoList = storeSettlementDao.selectTotalOrderByStoreIds(storeId);
				if (!(storeSettlementDtoList == null || storeSettlementDtoList.size() == 0)) {
					StoreSettlementEntity storeSettlementEntity = new StoreSettlementEntity();
					StoreSettlementEntity storeSettlementEntity2 = new StoreSettlementEntity();
					StoreInfoEntity storeInfoEntity = storeSettlementDao.selectMerchantByStore(storeId);
					storeSettlementEntity.setStoreId(storeId);
					storeSettlementEntity.setMerchantId(storeInfoEntity.getMerchantId());
					storeSettlementEntity2.setStoreId(storeId);
					storeSettlementEntity2.setMerchantId(storeInfoEntity.getMerchantId());
					storeSettlementCycleEntits.setStoreId(storeId);
					storeSettlementCycleEntits2.setStoreId(storeId);
					storeSettlementCycleEntits2.setMerchantId(storeInfoEntity.getMerchantId());
					storeSettlementCycleEntits.setMerchantId(storeInfoEntity.getMerchantId());
					storeSettlementCycleEntits.setCycleType(1);
					storeSettlementCycleEntits2.setCycleType(2);
					/** 下个结算日 */
					storeSettlementCycleEntits.setNextSettleDate(this.getNextDay(new Date()));
					storeSettlementCycleEntits2.setNextSettleDate(this.getNextDay(new Date()));
					/** 结算周期 */
					Double cardPrices = 0.0;
					Double scorePrices = 0.0;
					Double payPrices = 0.0;
					Double sanPrices = 0.0;
					Double distSums = 0.0;
					Double promPlatformSums = 0.0;
					Double distributionPriceAmounts = 0.0;
					Double promStoreSums = 0.0;
					Double cardStoreSubsidys = 0.0;
					Double cardPlatformSubsidys = 0.0;
					Double storedistriPrices = 0.0;
					Double platformCommissions = 0.0;
					Double brokerageRatio = 0.0;
					String payType = null;
					String payCallbackNo = null;
					Double paymentChannelCommissions = 0.0;
					Double paymentChannelCommission = 0.0;
					Double paymentChannelRate=0.0;
					for (StoreSettlementDto storeSettlementDtos : storeSettlementDtoList) {
						/** 优惠后金额 */
						Double lastPrice = storeSettlementDtos.getLastPrice();
						/** 美礼卡支付金额 */
						Double cardPrice = storeSettlementDtos.getCardPrice();
						/** 积分抵扣金额 */
						Double scorePrice = storeSettlementDtos.getScorePrice();
						Double payPrice = 0.0;
						Double sanPrice = 0.0;
						/** 实际支付金额 */
						if(scorePrice != null) {
							payPrice = storeSettlementDtos.getPayPrice();
						    sanPrice = payPrice + scorePrice + cardPrice;
						}else {
							payPrice = storeSettlementDtos.getPayPrice();
							sanPrice = payPrice + cardPrice;
						}
						/** 支付交易号 */
						payCallbackNo = storeSettlementDtos.getPayCallbackNo();
						/** 支付类型编码 */
						payType = storeSettlementDtos.getPayType();
						/** 优惠后的价格 */
						Double salePrices = storeSettlementDtos.getSalePrices();
						Long goodsId = storeSettlementDtos.getSkuId();
						/** 根据goodsId查询平台抽佣金 */
						brokerageRatio = storeSettlementDao.selectBrokerageRatioListByStoreId(goodsId);
						if(brokerageRatio != null) {
							/** 平台佣金=平台抽佣率*商品优惠后的金额 */
							platformCommissions =platformCommissions + salePrices * brokerageRatio;
						}
						/** 支付渠道佣金 */
						if (payType.equals("1")) {
							/**支付渠道费率*/
							paymentChannelRate = 0.06;
							paymentChannelCommission = payPrice * paymentChannelRate;
							paymentChannelCommissions = paymentChannelCommissions + paymentChannelCommission;
						}
						if (payType.equals("2")) {
							/**支付渠道费率*/
						    paymentChannelRate = 0.05;
							paymentChannelCommission = payPrice * paymentChannelRate;
							paymentChannelCommissions = paymentChannelCommissions + paymentChannelCommission;
						}
						if(sanPrice != 0 && scorePrice != null) {
							/** 1.现金支付金额=美礼卡支付金额/(美礼卡支付金额+积分抵扣金额+实际支付金额)*优惠后金额 */
							payPrices = payPrices + (payPrice / sanPrice * lastPrice);
							/** 2. 积分制金额=美礼卡支付金额/(美礼卡支付金额+积分抵扣金额+实际支付金额)*优惠后金额 */
							scorePrices = scorePrices + (scorePrice / sanPrice * lastPrice);
							/** 3.实际支付金额=美礼卡支付金额/(美礼卡支付金额+积分抵扣金额+实际支付金额)*优惠后金额 */
							cardPrices = cardPrices + (cardPrice / sanPrice * lastPrice);
						}
						/** 4.销售金额 */
						sanPrices = payPrices + scorePrices + cardPrices;
						/** 根据总订单id查询订单使用美礼卡情况 */
						Long totalOrderId = storeSettlementDtos.getTotalOrderId();
						/** 平台优惠活动金额 */
						Double platformDeduction = storeSettlementDtos.getPlatformDeduction();
						List<StoreSettlementDto> storeSettlementDtoLists = storeSettlementDao
								.selectCardInfoByTotalOrederId(totalOrderId);
						if (!(storeSettlementDtoLists == null || storeSettlementDtoLists.size() == 0)) {
							for (StoreSettlementDto storeSettlementDto2 : storeSettlementDtoLists) {
								/** 平台补差 */
								Double platformPrice = storeSettlementDto2.getPlatformPrice();
								/** 店铺补差 */
								Double storePrice = storeSettlementDto2.getStorePrice();
								/** 卡售价 */
								// Double price = storeSettlementDto2.getPrice();
								/** 美礼卡扣补金额 */
								Double cardButtonPrice = storeSettlementDto2.getCardButtonPrice();
								/** 计算平台店铺补差比例 */
								Double sanCardPrice = platformPrice + storePrice;
								/** 5.美礼卡商家扣补金额 */
								cardStoreSubsidys = cardStoreSubsidys + cardButtonPrice * (storePrice / sanCardPrice);
								/** 6.计算美丽卡对应平台补的金额 */
								cardPlatformSubsidys = cardPlatformSubsidys
										+ cardButtonPrice * (platformPrice / sanCardPrice);
							}
						}
						/** 计算会员分销店铺商品得的金额 根据StoreId查询会员分销记录的7.分销金额 */
						List<MemberDistributionLogDto> memberDistributionDistributionDtoList = memberDistributionLogDao
								.getMemberDistibutionDisPriceByStoreId(storeId);
						if (!(memberDistributionDistributionDtoList == null
								|| memberDistributionDistributionDtoList.size() == 0)) {
							for (MemberDistributionLogDto memberDistributionLogDto : memberDistributionDistributionDtoList) {
								Double distriPrice = memberDistributionLogDto.getDistriPrice();
								distributionPriceAmounts = distributionPriceAmounts + distriPrice;
							}
						}
						/** 所用支付级营销活动 */
						Long platformPromotion = storeSettlementDtos.getPlatformPromotion();
						PromotionEntity promotionEntity = storeSettlementDao
								.selectPromotionByPlatformPromotion(platformPromotion);
						if (promotionEntity != null && platformDeduction != null) {
							/** 营销活动platformRatio。 */
							Double platformRatio = promotionEntity.getPlatformRatio();
							/** 9.营销活动平台扣补金额 */
							Double promPlatformSum = platformDeduction * platformRatio;
							if (promPlatformSum != null) {
								/** 8. 营销活动商家扣补金额 */
								Double promStoreSum = platformDeduction - promPlatformSum;
								promPlatformSums = promPlatformSums + promPlatformSum;
								promStoreSums = promStoreSums + promStoreSum;
							}
						}
						/** 10.分销平台商品获得的金额 */
						if(storeSettlementDtos.getOrderType()==1 ) {
							Long skuId = storeSettlementDtos.getSkuId();
							/** 根据店铺id,skuId查询分销平台 商品的每一个分销金额 */
							Double storedistriPrice = goodsDistributionLogDao
									.getStoreDisPriceByStoreAndSkuId(storeId, skuId);
							if (storedistriPrice != null) {
								storedistriPrices = storedistriPrice;
							}
						}
					}
					/** 开始时间 */
					storeSettlementEntity.setSettleStartDate(new Date());
					/** 截止时间当前时间的后30天 */
					storeSettlementEntity.setSettleEndDate(this.getNextDay(new Date()));
					/** 开始时间 */
					storeSettlementEntity2.setSettleStartDate(new Date());
					/** 截止时间当前时间的后30天 */
					storeSettlementEntity2.setSettleEndDate(this.getNextDay(new Date()));
					/** 订单中使用现金支付的金额 */
					storeSettlementEntity.setCashSum(payPrices);
					storeSettlementEntity2.setCashSum(payPrices);
					/** 订单中使用积分支付的金额 */
					storeSettlementEntity.setPointSum(scorePrices);
					storeSettlementEntity2.setPointSum(scorePrices);
					/** 订单中使用美丽卡支付的金额 */
					storeSettlementEntity.setCardSum(cardPrices);
					storeSettlementEntity2.setCardSum(cardPrices);
					/** 销售金额 (订单金额)*/
					storeSettlementEntity.setSaleSum(sanPrices);
					storeSettlementEntity2.setSaleSum(sanPrices);
					/** 分销平台商品获得的金额。 */
					storeSettlementEntity.setDistSum(distSums);
					storeSettlementEntity2.setDistSum(distSums);
					/** 会员分销店铺商品得的金额，从店铺账户里减去。 */ /** 分销员佣金 */
					storeSettlementEntity.setMemberDistPrice(distributionPriceAmounts);
					storeSettlementEntity2.setMemberDistPrice(distributionPriceAmounts);
					/** 会员分销平台商品得的金额 */
					/** 营销活动商家扣补金额。 */
					storeSettlementEntity.setPromStoreSum(promStoreSums);
					storeSettlementEntity2.setPromStoreSum(promStoreSums);
					/** 营销活动平台扣补金额。+ */
					storeSettlementEntity.setPromPlatformSum(promPlatformSums);
					storeSettlementEntity2.setPromPlatformSum(promPlatformSums);
					/** 计算美礼卡扣补金额 */
					storeSettlementEntity.setCardStoreSubsidy(cardStoreSubsidys);
					storeSettlementEntity2.setCardStoreSubsidy(cardStoreSubsidys);
					/** 计算美丽卡对应平台补的金额 */
					storeSettlementEntity.setCardPlatformSubsidy(cardPlatformSubsidys);
					storeSettlementEntity2.setCardPlatformSubsidy(cardPlatformSubsidys);
					/** 平台佣金 */
					storeSettlementEntity.setPlatformCommission(platformCommissions);
					storeSettlementEntity2.setPlatformCommission(platformCommissions);
					/** 平台抽佣率*/
					storeSettlementEntity.setPlatformRate(brokerageRatio);
					storeSettlementEntity2.setPlatformRate(brokerageRatio);
					/** 支付渠道 */
					storeSettlementEntity.setChannelDisbursement(payType);
					storeSettlementEntity2.setChannelDisbursement(payType);
					/** 支付渠道佣金 */
					storeSettlementEntity.setPaymentChannelCommission(paymentChannelCommissions);
					storeSettlementEntity2.setPaymentChannelCommission(paymentChannelCommissions);
					/** 三方支付流水号 */
					storeSettlementEntity.setTripartitePaymentFloat(payCallbackNo);
					storeSettlementEntity2.setTripartitePaymentFloat(payCallbackNo);
					/**支付渠道费率*/
				//	storeSettlementEntity.setPaymentChannelRate(paymentChannelRate);
					/** 11.计算销售金额 */
					/** 公式：销售金额-营销活动商家扣补金额-美丽卡扣补金额-会员分销地阿奴商品的金额-平台佣金-支付渠道佣金 */
					storeSettlementEntity.setSettlePrice(storeSettlementEntity.getSaleSum()
							- storeSettlementEntity.getPromPlatformSum() - storeSettlementEntity.getCardStoreSubsidy()
							- storeSettlementEntity.getMemberDistPrice() - storeSettlementEntity.getPlatformCommission()
							- storeSettlementEntity.getPaymentChannelCommission());
					/** 分销平台商品获得的金额 */// distSum
					storeSettlementEntity2.setDistSum(storedistriPrices);
					/** 结算类型 */
					storeSettlementEntity.setSettleType(1);
					storeSettlementEntity2.setSettleType(2);
					storeSettlementEntity.setState(1);
					storeSettlementEntity2.setState(1);
					//storeSettlementEntity2.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
					//storeSettlementEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
					i = storeSettlementDao.insert(storeSettlementEntity);
					i = storeSettlementDao.insert(storeSettlementEntity2);
					i = storeSettlementCycleDao.insert(storeSettlementCycleEntits);
					i = storeSettlementCycleDao.insert(storeSettlementCycleEntits2);
				}
			}
		}
		return i;
	}
	
	@Override
	public List<StoreSettlementDto> getStoreSettlementDetailsList() {
		/**根据店铺Id查询店铺订单详情具体的商品*/
		List<StoreSettlementDto> storeOrederList = storeSettlementDao.selectStoreOrderList();
		List<StoreSettlementDto> storeSettlementList = new ArrayList<StoreSettlementDto>();
		if(!(storeOrederList==null ||storeOrederList.size()==0)) {
			for (StoreSettlementDto storeSettlementDto : storeOrederList) {
				//StoreSettlementDto  storeSettlementDtos = new StoreSettlementDto(); 
				/**根据店铺id查询电偶订单详情中找到具体的商品信息 */
				Long storeId = storeSettlementDto.getStoreId();
				List<StoreSettlementDto> storeSettlementDtoList = storeSettlementDao.selectTotalOrderByStoreId(storeId);
				//比例值1
				Double proportionPrice =0.0;
				//比例值2
				Double storeRatio = 0.0;
				//平台抽佣率
				Double brokerageRatio=0.0;
				//平台佣金
				Double goodsplatformCommission=0.0;
				//支付渠道
				String goodspayType=null;
				//三方支付流水号
				String goodspayCallbackNo =null;
				//支付渠道费率
				Double paymentChannelRate=0.0;
				//支付渠道佣金
				Double paymentChannelCommission = 0.0;
				//比例三
				Double cardRatio = 0.0;
				//具体商品营销活动平台扣补金额
				Double goodsPromPlatformSum =0.0;
				//具体商品营销活动店铺扣补金额
				Double goodsPromStoreSum=0.0;
				//美礼卡商家店铺扣补
				Double goodscardStoreSubsidy=0.0;
				//美礼卡平台扣补
				Double goodscardPlatformSubsidy=0.0;
				//会员分销金额
				Double goodsDitriPrice=0.0;
				//店铺分销比例
				Double goodsStoredistriRatio=0.0;
				if (!(storeSettlementDtoList == null || storeSettlementDtoList.size() == 0)) {
					for (StoreSettlementDto storeSettlementDto2 : storeSettlementDtoList) {
					StoreSettlementDto storeSettlementDtos = new StoreSettlementDto();
				//	storeSettlementDtos.setPaymentChannelCommission(paymentChannelCommission);
					/**1.计算比例值*/
						/**店铺订单优惠后金额（实际金额）*/
						Double lastPrice = storeSettlementDto2.getLastPrice();
						/**总订单（实际支付金额）*/
						Double payPrice = storeSettlementDto2.getPayPrice();
						proportionPrice=lastPrice/payPrice;
				        /**总订单美礼卡支付金额*/
						Double cardPrice = storeSettlementDto2.getCardPrice();
						/**计算店铺中使用美礼卡支付金额*/
					    /**总订单中积分支付金额*/
						Double scorePrice = storeSettlementDto2.getScorePrice();
				     	/**计算比例值2*/
						/**商品价格*/
						Double salePrices = storeSettlementDto2.getSalePrices();
						storeRatio=salePrices / lastPrice;
						/**计算具体某件商品使用美丽卡支付金额*/
						Double  goodsCardPrice = (cardPrice * proportionPrice) * storeRatio;
				    	/**计算具体某件商品使用积分字符金额*/
						Double  goodsscorePrice = (scorePrice * proportionPrice) *storeRatio;
					   /**计算具体某年商品现金支付金额*/
						Double  goodsPayPrice=(payPrice * proportionPrice) * storeRatio;
						/**1.计算具体商品现金营业额*/
						/**2.计算具体商品积分营业额*/
						/**3.计算具体商品美丽卡营业额*/
						//取出对应商品的优惠金额deduction
						Long usedplatformPromotion = storeSettlementDto2.getUsedplatformPromotion();
						if(usedplatformPromotion !=null) {
							PromotionEntity promotionEntity = storeSettlementDao
									.selectPromotionByPlatformPromotion(usedplatformPromotion);
							if(promotionEntity != null) {
								//取出对应的平台补贴比例    storeSettlementDto2.getDeduction()
								Double platformRatio = promotionEntity.getPlatformRatio();
								/**4.计算具体商品营销活动平台扣补金额*/
							 goodsPromPlatformSum = platformRatio * storeSettlementDto2.getDeduction();
								/**5.计算具体商品营销活动店铺扣补金额*/
							 goodsPromStoreSum=storeSettlementDto2.getDeduction()-goodsPromPlatformSum;
							}
						}
						Long goodsId = storeSettlementDto2.getSkuId();
						/**会员id*/
						Long memberId = storeSettlementDto2.getMemberId();
						/** 根据goodsId查询6.计算具体每个商品平台抽佣率 */
						brokerageRatio = storeSettlementDao.selectBrokerageRatioListByStoreId(goodsId);
						/**7.计算具体每个商品平台佣金*/
						if(brokerageRatio != null) {
							/** 平台佣金=平台抽佣率*商品优惠后的金额 */
						goodsplatformCommission = salePrices * brokerageRatio;
						}
						/**9.计算具体每个支付交易号 */
						goodspayCallbackNo = storeSettlementDto2.getPayCallbackNo();
						/**8. 计算具体每个支付类型编码  支付渠道*/
						goodspayType = storeSettlementDto2.getPayType();
						/**支付渠道佣金*/
						if (goodspayType.equals("1")) {
							/**10.计算具体每个商品支付渠道费率*/
							paymentChannelRate = 0.06;
							paymentChannelCommission = (payPrice * paymentChannelRate) *storeRatio;
						}
						if (goodspayType.equals("2")) {
							/**计算具体每个商品支付渠道费 率*/
						    paymentChannelRate = 0.05;
							paymentChannelCommission = (payPrice * paymentChannelRate)*storeRatio ;
						}
						/**11.计算具体每个商品分销员佣金*/
						goodsDitriPrice= memberDistributionLogDao.selectMemberDistibutionDisPriceByStoreIdAndSkuId(memberId,goodsId);
						//比例三
						cardRatio=salePrices/payPrice;
						/** 根据总订单id查询订单使用美礼卡情况 */
						Long totalOrderId = storeSettlementDto2.getTotalOrderId();
						if(totalOrderId!= null) {
							List<StoreSettlementDto> storeSettlementDtoLists = storeSettlementDao
									.selectCardInfoByTotalOrederId(totalOrderId);
							if(!(storeSettlementDtoLists== null || storeSettlementDtoLists.size()==0)) {
								for (StoreSettlementDto storeSettlementDto3 : storeSettlementDtoLists) {
									/**美礼卡的平台补差*/
									Double platformPrice = storeSettlementDto3.getPlatformPrice();
									/**美礼卡的店铺补差*/
									Double storePrice = storeSettlementDto3.getStorePrice();
									/**12.计算具体每个商品的店铺美礼卡扣补金额*/
									 goodscardStoreSubsidy=goodscardStoreSubsidy+cardRatio * storePrice;
									/**13.计算具体每个商品的平台美礼卡扣补金额*/
									 goodscardPlatformSubsidy = goodscardPlatformSubsidy+cardRatio *platformPrice;
								}
							}
						}
						/** 根据店铺id查询平台订单的id与关联详情表的信息获取具体的SkuId */
						List<StoreSettlementDto> platStoreorderInfoList = storeSettlementDao
								.selectPlatStoreorderInfoByStoreId(storeId);
						if (!(platStoreorderInfoList == null || platStoreorderInfoList.size() == 0)) {
							for (StoreSettlementDto storeSettlementDtosss : platStoreorderInfoList) {
								Long skuId = storeSettlementDtosss.getSkuId();
								/** 根据店铺id,skuId查询分销平台 商品的每一个分销金额 */
								Double storedistriRatio = goodsDistributionLogDao
										.getStoreDisRatioByStoreAndSkuId(storeId, skuId);
								if (storedistriRatio != null) {
									/**14.计算具体商品分销平台商品获得的金额*/
									 goodsStoredistriRatio = storedistriRatio;
								}
							}
						}
						//订单编号     totalOrderId
						storeSettlementDtos.setTotalOrderId(storeSettlementDto2.getTotalOrderId());
						//付款时间
						storeSettlementDtos.setPayTime(storeSettlementDto2.getPayTime());
						//订单时间  
						storeSettlementDtos.setOrderTime(storeSettlementDto2.getOrderTime());
						//会员名称 
						storeSettlementDtos.setMemberName(storeSettlementDto2.getMemberName());
						//商品编号
						storeSettlementDtos.setSkuId(storeSettlementDto2.getSkuId());
						//商品名称
						storeSettlementDtos.setItemName(storeSettlementDto2.getItemName());
						//商品数量
						storeSettlementDtos.setGoodsNum(storeSettlementDto2.getGoodsNum());
						//商品售价
						storeSettlementDtos.setUnitPrice(storeSettlementDto2.getUnitPrice());
						//美礼卡
						//现金
						//积分
						//销售金额
						//平台营销补扣  promPlatformSum
						storeSettlementDtos.setPromPlatformSum(goodsPromPlatformSum);
						//店铺营销补扣   promStoreSum
						storeSettlementDtos.setPromStoreSum(goodsPromStoreSum);
						//联名卡平台补扣 cardPlatformSubsidy
						storeSettlementDtos.setCardPlatformSubsidy(goodscardPlatformSubsidy);
						//联名卡商家补扣 cardStoreSubsidy  
						storeSettlementDtos.setCardStoreSubsidy(goodscardStoreSubsidy);
						//平台抽佣率  platformRate
						storeSettlementDtos.setPlatformRate(brokerageRatio);
						//平台佣金   platformCommission
						storeSettlementDtos.setPlatformCommission(goodsplatformCommission);
						//支付渠道  channelDisbursement
						storeSettlementDtos.setChannelDisbursement(goodspayType);
						//支付渠道佣金   paymentChannelCommission
						storeSettlementDtos.setPaymentChannelCommission(paymentChannelCommission);
						// 支付渠道费率       paymentChannelRate
						storeSettlementDtos.setPaymentChannelRate(paymentChannelRate);
						//三方支付流水号  tripartitePaymentFloat
						storeSettlementDtos.setTripartitePaymentFloat(goodspayCallbackNo);
						//分销员佣金  memberDistPrice
						storeSettlementDtos.setMemberDistPrice(goodsDitriPrice);
						//店铺分润比例   
						storeSettlementDtos.setStoredistriRatio(goodsStoredistriRatio);
						/** 11.计算销售金额 */
						/** 公式：销售金额-营销活动商家扣补 金额-美丽卡扣补金额
						 * -会员分销的商品的金额-平台佣金-支付渠道佣金 */
						/*storeSettlementDtos.setSettlePrice(100.00-storeSettlementDtos.getPromStoreSum()-storeSettlementDtos.getMemberDistPrice()
								-storeSettlementDtos.getPlatformCommission()-storeSettlementDtos.getPaymentChannelCommission());*/
						//storeSettlementList.add(storeSettlementDtos);
						storeSettlementList.add(storeSettlementDtos);
					}
				}
			}
		}
		return storeSettlementList;
	}
	/**
	 * Description：获取30天后的时间
	 * 
	 * Author Harry
	 * 
	 * @param date
	 *            当天时间
	 * @return
	 */
	public Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, +30);
		date = calendar.getTime();
		return date;

	}

	@Override
	public Integer updateStoreSettlementState(Long id) {
		return storeSettlementDao.updateStoreSettlementState(id);
	}

	

}
