package com.topaiebiz.payment.order.moble.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.mysql.fabric.xmlrpc.base.Data;
import com.nebulapaas.util.common.math.MathCountUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.alipay.client.ThirdPartyAlipayClient;
import com.topaiebiz.alipay.config.AlipayConfig;
import com.topaiebiz.alipay.dto.AlipayPayDto;
import com.topaiebiz.giftcard.manage.service.GiftCardDetailService;
import com.topaiebiz.goods.sku.dao.GoodsSkuDao;
import com.topaiebiz.goods.sku.dao.ItemDao;
import com.topaiebiz.goods.sku.dto.GoodsSkuDto;
import com.topaiebiz.goods.sku.dto.StoreOrderDetailDto;
import com.topaiebiz.goods.sku.entity.GoodsSkuEntity;
import com.topaiebiz.goods.sku.entity.ItemEntity;
import com.topaiebiz.member.address.dao.MemberAddressDao;
import com.topaiebiz.member.address.dto.MemberAddressDto;
import com.topaiebiz.member.address.entity.MemberAddressEntity;
import com.topaiebiz.member.address.exception.MemberAddressExceptionEnum;
import com.topaiebiz.member.mgmt.dao.MemberMgmtDao;
import com.topaiebiz.member.mgmt.entity.MemberEntity;
import com.topaiebiz.member.point.exception.MemberPointExceptionEnum;
import com.topaiebiz.member.point.service.MemberPointService;
import com.topaiebiz.member.reserve.dao.MemberBindAccountDao;
import com.topaiebiz.member.reserve.entity.MemberBindAccountEntity;
import com.topaiebiz.merchant.enter.dao.FreightTempleteDao;
import com.topaiebiz.merchant.enter.dao.FreightTempleteDetailDao;
import com.topaiebiz.merchant.enter.entity.FreightTempleteDetailEntity;
import com.topaiebiz.merchant.enter.entity.FreightTempleteEntity;
import com.topaiebiz.payment.order.dao.OrderPaymentLogDao;
import com.topaiebiz.payment.order.entity.OrderPaymentLogEntity;
import com.topaiebiz.payment.order.moble.dao.MobleStoreOrderDetailDao;
import com.topaiebiz.payment.order.moble.dto.*;
import com.topaiebiz.payment.order.moble.entity.StoreOrderDetailEntity;
import com.topaiebiz.payment.order.moble.exception.MobleOrderExceptionEnum;
import com.topaiebiz.payment.order.moble.service.MobleOrderService;
import com.topaiebiz.promotion.mgmt.dao.PromotionPlatformUsageLogDao;
import com.topaiebiz.promotion.mgmt.dao.PromotionStoreUsageLogDao;
import com.topaiebiz.promotion.mgmt.dto.CommodityMarketingDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionGoodsDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionPriceDto;
import com.topaiebiz.promotion.mgmt.entity.PromotionPlatformUsageLogEntity;
import com.topaiebiz.promotion.mgmt.entity.PromotionStoreUsageLogEntity;
import com.topaiebiz.promotion.mgmt.service.PromotionGoodsService;
import com.topaiebiz.promotion.mgmt.service.PromotionService;
import com.topaiebiz.system.common.OrderStatusEnum;
import com.topaiebiz.system.district.dao.DistrictDao;
import com.topaiebiz.system.district.entity.DistrictEntity;
import com.topaiebiz.system.district.service.DistrictService;
import com.topaiebiz.system.moble.security.dto.TokenDto;
import com.topaiebiz.system.moble.security.exception.MobleSecurityExceptionEnum;
import com.topaiebiz.system.moble.security.util.TokenUtil;
import com.topaiebiz.system.security.util.SecurityContextUtils;
import com.topaiebiz.thirdparty.pay.client.ThirdPartyWeChatClient;
import com.topaiebiz.thirdparty.pay.dto.WeChatPayDto;
import com.topaiebiz.transaction.cart.dao.ShoppingCartDao;
import com.topaiebiz.transaction.cart.entity.ShoppingCartEntity;
import com.topaiebiz.transaction.order.merchant.dao.OrderAddressDao;
import com.topaiebiz.transaction.order.merchant.dao.OrderInvoiceDao;
import com.topaiebiz.transaction.order.merchant.dao.StoreOrderDao;
import com.topaiebiz.transaction.order.merchant.entity.OrderAddressEntity;
import com.topaiebiz.transaction.order.merchant.entity.OrderInvoiceEntity;
import com.topaiebiz.transaction.order.merchant.entity.StoreOrderEntity;
import com.topaiebiz.transaction.order.total.dao.TotalOrderDao;
import com.topaiebiz.transaction.order.total.entity.TotalOrderEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Description： 订单接口实现类
 * <p>
 * <p>
 * Author hxpeng
 * <p>
 * Date 2017年10月23日 上午11:13:45
 * <p>
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * <p>
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@Service
public class MobleOrderServiceImpl implements MobleOrderService {

	private final static Logger logger = LoggerFactory.getLogger(MobleOrderServiceImpl.class);

	// 暂定积分抵扣金额比率为1:100
	private final static double scoreRate = 0.01;

	// 操作类型：提交订单
	private final static String COMMIT_ORDER = "commitOrder";

	@Autowired
	private ItemDao itemDao;

	@Autowired
	private GoodsSkuDao goodsSkuDao;

	@Autowired
	private MemberAddressDao memberAddressDao;

	@Autowired
	private DistrictDao districtDao;

	@Autowired
	private PromotionService promotionService;

	@Autowired
	private PromotionGoodsService promotionGoodsService;

	@Autowired
	private FreightTempleteDetailDao freightTempleteDetailDao;

	@Autowired
	private MemberPointService memberPointService;

	@Autowired
	private StoreOrderDao storeOrderDao;

	@Autowired
	private MobleStoreOrderDetailDao mobleStoreOrderDetailDao;

	@Autowired
	private PromotionStoreUsageLogDao promotionStoreUsageLogDao;

	@Autowired
	private DistrictService districtService;

	@Autowired
	private OrderAddressDao orderAddressDao;

	@Autowired
	private OrderInvoiceDao orderInvoiceDao;

	@Autowired
	private GiftCardDetailService giftCardDetailService;

	@Autowired
	private TotalOrderDao totalOrderDao;

	@Autowired
	private PromotionPlatformUsageLogDao promotionPlatformUsageLogDao;

	@Autowired
	private FreightTempleteDao freightTempleteDao;

	@Autowired
	private ShoppingCartDao shoppingCartDao;

	@Autowired
	private OrderPaymentLogDao paymentLogDao;
	
	@Autowired
	private MemberMgmtDao memberMgmtDao;

	@Autowired
	private MemberBindAccountDao memberBindAccountDao;

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public SettlementResultDto settlementGoods(SettlementParamDto settlementParamDto) throws GlobalException {
		TokenDto tokenDto = TokenUtil.getTokenDetail(settlementParamDto.getToken());
		Long currentUserId = tokenDto.getMemberId();
		Long storeId = tokenDto.getStoreId();
		if(currentUserId == null){
			throw new GlobalException(MobleSecurityExceptionEnum.TOKEN_DOES_NOT_EXIST);
		}
		SettlementResultDto settlementResultDto = null;
		// 迭代订购的商品集合，查询商品基本信息，及计算总价,运费等
		String districtId = "";
		MemberAddressEntity memberAddressEntity = memberAddressDao.selectById(settlementParamDto.getMemberAddressId());
		if(null != memberAddressEntity){
			districtId = memberAddressEntity.getDistrictId().toString();
		}else{
//			try{
//				districtId = memberAddressDao.findDefaultAddressByMemberId(currentUserId).getDistrictId().toString();
//			}catch (NullPointerException e){
//				logger.error("找不到用户的默认收货地址！");
//				throw new GlobalException(MobleOrderExceptionEnum.ORDER_PARAM_ILLEGAL);
//			}
		}
		settlementResultDto = calculatedShopPrice(settlementParamDto.getSettlementGoodParamDtos(), "settlementShop", currentUserId, districtId);
		settlementResultDto.setShopId(storeId);
		// 获取会员默认地址
		settlementResultDto.setMemberAddressDto(getMemberDefaultAddress(currentUserId));

		// ================迭代 营销活动和结算商品，进行数据封装
		List<Long> goodSkuIds = settlementResultDto.getGoodSkuIds();
		if (goodSkuIds.size() < 1) {
			throw new GlobalException(MobleOrderExceptionEnum.SETTLEMENT_GOODS_SKUIDS_IS_NULL);
		}
		List<CommodityMarketingDto> commodityMarketingDtos = promotionService.getSinglePromotionList(storeId,currentUserId, goodSkuIds);
		for (SettlementGoodInfo settlementGoodInfo : settlementResultDto.getSettlementGoodInfoList()) {
			for (CommodityMarketingDto commodityMarketingDto : commodityMarketingDtos) {
				long goodSkuId = settlementGoodInfo.getGoodsSkuEntity().getId();
				long markingDtoSkuId = commodityMarketingDto.getGoodsSkuId();
				if (goodSkuId == markingDtoSkuId) {
					settlementGoodInfo.setGoodsPromotions(commodityMarketingDto.getPromotionSingleList());
				}
			}
		}
		// 查询订单级别的其他优惠活动
		settlementResultDto.setSettlementOtherPromotionList(promotionService.findPromotionByGoods(settlementResultDto.getPromotionGoodsDtos(),currentUserId));


		// 填充美礼卡信息
		int getItemIdSSize = settlementResultDto.getItemIds().size();
		if (getItemIdSSize > 0) {
			Long[] skuIds = settlementResultDto.getItemIds().toArray(new Long[getItemIdSSize]);
			Double balance = giftCardDetailService.getBalance(storeId, currentUserId, skuIds);
			if(balance.compareTo(0.0) == -1){
				balance = 0.0;
			}
			settlementResultDto.setGiftCardValue(balance);
		}

		// 填充用户积分
		MemberEntity memberEntity = memberMgmtDao.selectById(currentUserId);
		settlementResultDto.setTotalIntegral(memberEntity.getOwnScore());
		return settlementResultDto;
	}

	/**
	 * Description： 抽离方法--> 计算结算商品的价格/运费/优惠活动等方面
	 * <p>
	 * Author hxpeng
	 *
	 * @param settlementGoodParamDtos
	 * @param operationType  操作类型 1、commitOrder:提交生成订单操作类型; 2、settlementShop:结算商品操作类型
	 * @return
	 * @throws GlobalException
	 */
	private SettlementResultDto calculatedShopPrice(List<SettlementGoodParamDto> settlementGoodParamDtos, String operationType, Long currentUserId, String districtId) throws GlobalException {
		if (settlementGoodParamDtos == null) {
			return null;
		}
		// 结果容器
		SettlementResultDto settlementResultDto = new SettlementResultDto();
		// 总金额
		double totalPrice = 0;
		// 运费总金额
		double totalFreight = 0;
		// 结算商品的sku id集合
		List<Long> goodSkuIds = new ArrayList<>();
		List<Long> itemIds = new ArrayList<>();
		// 结算商品结果信息集合
		List<SettlementGoodInfo> settlementGoodInfos = new ArrayList<>();
		// 营销活动集合
		List<PromotionGoodsDto> promotionGoodsDtos = new ArrayList<>();

		for (SettlementGoodParamDto settlementGoodParamDto : settlementGoodParamDtos) {
			// 订购商品主键
			Long goodId = settlementGoodParamDto.getGoodId();
			// 订购商品数量
			Integer goodNum = settlementGoodParamDto.getGoodNum();
			// 订购商品sku主键
			Long goodSkuId = settlementGoodParamDto.getGoodSkuId();
			if (null == goodId || goodNum < 1 && null == goodSkuId) {
				throw new GlobalException(MobleOrderExceptionEnum.SETTLEMENT_PARAM_ILLEGAL);
			}
			// 重复的SKU 商品 订单提交
			if(goodSkuIds.contains(goodSkuId)){
				throw new GlobalException(MobleOrderExceptionEnum.ORDER_PARAM_ILLEGAL);
			}
			// 一类商品运费
			double oneShopFreight = 0;
			// 一类商品价格：单价乘以数量
			double oneShopPrice = 0;
			ItemEntity itemEntity = itemDao.selectById(goodId);
			if (null == itemEntity) {
				throw new GlobalException(MobleOrderExceptionEnum.GOODS_IS_NOT_FOUND);
			}
			// 已下架
			if(!itemEntity.getStatus().equals(2)){
				throw new GlobalException(MobleOrderExceptionEnum.THE_GOODS_HAVE_BEEN_LAID_DOWN);
			}
			itemIds.add(itemEntity.getId());
			// 保存结算的各类商品中，各自的价格（包含运费/总价/优惠后过的价格等等）
			SettlementGoodInfo settlementGoodInfo = new SettlementGoodInfo();
			settlementGoodInfo.setGoodsNum((long)goodNum);
			settlementGoodInfo.setItemName(itemEntity.getName());
			// 查询商品基本信息
			GoodsSkuEntity goodsSkuEntity = goodsSkuDao.selectById(goodSkuId);
			if (null != goodsSkuEntity) {
				long stockNumber = goodsSkuEntity.getStockNumber();
				if (stockNumber < 1 || stockNumber < goodNum) {
					logger.error("商品库存不够，退出执行。。。。");
					throw new GlobalException(MobleOrderExceptionEnum.GOODS_INSUFFICIENT_STOCK);
				}
				goodSkuIds.add(goodSkuId);
				settlementGoodInfo.setGoodsSkuEntity(goodsSkuEntity);
				oneShopPrice = MathCountUtils.multiply(goodsSkuEntity.getPrice(), (double)goodNum);
			} else {
				logger.error(new Data() + "查询不到商品的SKU信息！查询参数为 goodSkuId：" + goodSkuId);
				throw new GlobalException(MobleOrderExceptionEnum.GOODS_IS_NOT_FOUND);
			}
			// 商品SKU原价
			Double goodSkuPrice = goodsSkuEntity.getPrice();
			// 物流模板Id
			Long logisticsId = itemEntity.getLogisticsId();
			if (null == logisticsId) {
				throw new GlobalException(MobleOrderExceptionEnum.FREIGHT_INFO_IS_NOT_FOUND);
			}
			// 运费计算规则：((订购商品数量-模板默认件数)/续件件数)X续件价格+首付
			OrderFreightParamsDto orderFreightParamsDto = new OrderFreightParamsDto();
			orderFreightParamsDto.setGoodsNum(goodNum);
			orderFreightParamsDto.setLogisticsId(logisticsId);
			orderFreightParamsDto.setWeightOrBulk(itemEntity.getWeightBulk() == null ? 0 : itemEntity.getWeightBulk());
			oneShopFreight = calculationFreight(orderFreightParamsDto, districtId);
			totalFreight = MathCountUtils.add(totalFreight, oneShopFreight);
			settlementGoodInfo.setTotalFreight(oneShopFreight);
			settlementGoodInfo.setSalePrice(goodsSkuEntity.getPrice());
			//  单品参与营销活动
			Long promotionId = settlementGoodParamDto.getPromotionId();
			if (null != promotionId) {
				PromotionGoodsDto promotionGoodsDto = promotionGoodsService.getPromotionGoods(currentUserId, promotionId, goodSkuId);
				if (null != promotionGoodsDto) {
					Double promotionPrice = promotionGoodsDto.getPromotionPrice();
					// 活动价格为空时 则按原价计算
					if(null != promotionPrice){
						settlementGoodInfo.setDeduction(promotionGoodsDto.getDiscountValue());
						settlementGoodInfo.setUsedPromotion(promotionGoodsDto.getPromotionId());
						settlementGoodInfo.setSalePrice(promotionPrice);
						// 限购数量
						Long confinNum =  promotionGoodsDto.getConfineNum();
						if(null != confinNum){
							if(confinNum < goodNum){
								oneShopPrice = MathCountUtils.add(MathCountUtils.multiply(promotionPrice, (double)confinNum),
										MathCountUtils.multiply(goodSkuPrice, (double)goodNum - confinNum));
							}else{
								oneShopPrice = MathCountUtils.multiply(promotionGoodsDto.getPromotionPrice(), (double)goodNum);
							}
						}else{
							oneShopPrice = MathCountUtils.multiply(promotionGoodsDto.getPromotionPrice(), (double)goodNum);
						}
					}else{
						oneShopPrice = MathCountUtils.multiply(goodSkuPrice, (double)goodNum);
					}
				}
			}
			// 支付订单时，删除购物车中的商品
			if(operationType.equals(COMMIT_ORDER)){
				// 删除购物车中的此商品
				ShoppingCartEntity shoppingCartEntity = shoppingCartDao.getByMemberIdAndSkuId(currentUserId, goodSkuId);
				if(null != shoppingCartEntity){
					shoppingCartEntity.setDeleteFlag((byte) 1);
					shoppingCartDao.updateById(shoppingCartEntity);
				}
			}

			totalPrice = MathCountUtils.add(totalPrice, oneShopPrice);
			settlementGoodInfo.setTotalPrice(oneShopPrice);
			settlementGoodInfos.add(settlementGoodInfo);

			PromotionGoodsDto promotionGoodsDto = new PromotionGoodsDto();
			promotionGoodsDto.setGoodsSkuId(goodSkuId);
			promotionGoodsDto.setPromotionPrice(oneShopPrice);
			promotionGoodsDtos.add(promotionGoodsDto);
		}
		settlementResultDto.setItemIds(itemIds);
		settlementResultDto.setTotalFreight(totalFreight);
		settlementResultDto.setTotalMoney(totalPrice);
		settlementResultDto.setGoodSkuIds(goodSkuIds);
		settlementResultDto.setSettlementGoodInfoList(settlementGoodInfos);
		settlementResultDto.setPromotionGoodsDtos(promotionGoodsDtos);
		return settlementResultDto;
	}


	/**
	 * Description： 计算运费
	 * <p>
	 * Author hxpeng
	 *
	 * @param orderFreightParamsDto 计算运费参数DTO
	 * @param districtId  用户收货地址区域ID
	 * @return
	 */
	private Double calculationFreight(OrderFreightParamsDto orderFreightParamsDto, String districtId){
		// 物流模板ID
		long logisticsId = orderFreightParamsDto.getLogisticsId();
		FreightTempleteDetailEntity freightTempleteDetailEntity = null;
		// 是否特定区域送达
		boolean isOnlyThis = true;
		FreightTempleteEntity freightTempleteEntity = freightTempleteDao.selectById(logisticsId);
		if (null == freightTempleteEntity) {
			throw new GlobalException(MobleOrderExceptionEnum.FREIGHT_INFO_IS_NOT_FOUND);
		}
		Integer onlyThis = freightTempleteEntity.getOnlyThis();
		isOnlyThis = null != onlyThis && onlyThis.equals(1);

		List<FreightTempleteDetailEntity> freightTempleteDetailEntityList = freightTempleteDetailDao.selectOneByFreightId(logisticsId);
		if (null == freightTempleteDetailEntityList || freightTempleteDetailEntityList.size() < 1) {
			throw new GlobalException(MobleOrderExceptionEnum.FREIGHT_INFO_IS_NOT_FOUND);
		}
		for(FreightTempleteDetailEntity freightTempleteDetailEntity1 : freightTempleteDetailEntityList){
			String districtListStr = freightTempleteDetailEntity1.getDistrictIdList();
			if(StringUtils.isEmpty(districtListStr)){
				if(!isOnlyThis){
					freightTempleteDetailEntity = freightTempleteDetailEntity1;
					break;
				}
			}else{
				if(districtListStr.contains(districtId)){
					if(isOnlyThis){
						freightTempleteDetailEntity = freightTempleteDetailEntity1;
						break;
					}
				}
			}
		}
		if((isOnlyThis && null == freightTempleteDetailEntity) || null == freightTempleteDetailEntity){
			throw new GlobalException(MobleOrderExceptionEnum.AREA_CAN_NOT_BE_DELIVERED);
		}
		// 单件商品 重量/体积
		double weightOrBulk = orderFreightParamsDto.getWeightOrBulk();
		// 商品数量
		int goodNum = orderFreightParamsDto.getGoodsNum();
		// 运费
		double freightPrice = 0.0;
		// 件数
		try{
			if (freightTempleteEntity.getPricing() == 1) {
				double freightFirstNum = freightTempleteDetailEntity.getFirstNum();
				if (goodNum > freightFirstNum) {
					// 多出默认件数的 多余件数
					BigDecimal surplusNum = new BigDecimal(goodNum).subtract(new BigDecimal(freightFirstNum));
					// 续件次数：多余件数除以续件件数(向上取整)
					BigDecimal continuedNum = new BigDecimal("0");
					if(freightTempleteDetailEntity.getAddNum() > 0){
						continuedNum = surplusNum.divide(new BigDecimal(freightTempleteDetailEntity.getAddNum()), 0, BigDecimal.ROUND_HALF_UP);
					}
					freightPrice = continuedNum.multiply(new BigDecimal(freightTempleteDetailEntity.getAddPrice()))
							 .add(new BigDecimal(freightTempleteDetailEntity.getFirstPrice())).doubleValue();

				} else {
					freightPrice = freightTempleteDetailEntity.getFirstPrice();
				}
			} else {
				// 体积或者重量
				double freightWeightOrBulk = freightTempleteDetailEntity.getFirstNum();
				if (weightOrBulk > freightWeightOrBulk) {
					double surplusWeightOrBulk = MathCountUtils.subtract(weightOrBulk, freightWeightOrBulk);
					double continuedWeight = MathCountUtils.divide(surplusWeightOrBulk, freightTempleteDetailEntity.getAddNum(), 2);
					freightPrice = MathCountUtils.add(
							MathCountUtils.multiply(continuedWeight, freightTempleteDetailEntity.getAddPrice()),
							freightTempleteDetailEntity.getFirstPrice());
				} else {
					freightPrice = freightTempleteDetailEntity.getFirstPrice();
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			throw new GlobalException(MobleOrderExceptionEnum.FREIGHT_CALCULATION_FAILED);
		}
		return freightPrice;
	}

	/**
	 * Description： 抽离方法-->获取会员默认收货地址
	 * <p>
	 * Author hxpeng
	 *
	 * @param memberId
	 * @return
	 * @throws GlobalException
	 */
	private MemberAddressDto getMemberDefaultAddress(Long memberId) throws GlobalException {
		MemberAddressDto memberAddressDto = new MemberAddressDto();
		try {
			MemberAddressEntity memberAddressEntity = memberAddressDao.findDefaultAddressByMemberId(memberId);
			if (null != memberAddressEntity) {
				BeanUtils.copyProperties(memberAddressEntity, memberAddressDto);
				memberAddressDto.setId(memberAddressEntity.getId());
				Long districtId = memberAddressEntity.getDistrictId();
				if (null != districtId) {
					DistrictEntity districtEntity = districtDao.selectById(districtId);
					if (null != districtEntity) {
						String districtValue = districtEntity.getSerialName();
						if (!StringUtils.isEmpty(districtValue)) {
							districtValue = districtValue.replace("->", "");
							memberAddressDto.setDistrictValue(districtValue);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new GlobalException(MobleOrderExceptionEnum.GET_MEMBERADDRESS_INFO_FAILURE);
		}
		return memberAddressDto;
	}

	@Override
	// 设置回滚异常类为Exception类
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public TotalOrderEntity submitOrder(SettlementParamDto settlementParamDto) throws GlobalException {
		// 当前会员编号 暂时写死
		Date currentDate = new Date();
		TokenDto tokenDto = TokenUtil.getTokenDetail(settlementParamDto.getToken());
		if(null == tokenDto){
			throw new GlobalException(MobleSecurityExceptionEnum.TOKEN_DOES_NOT_EXIST);
		}
		Long storeId = tokenDto.getStoreId();
		Long currentUserId = tokenDto.getMemberId();
		if(null == currentUserId){
			throw new GlobalException(MobleSecurityExceptionEnum.TOKEN_DOES_NOT_EXIST);
		}
		// 商品中店铺的产品数量
		int shopTypeGoodsNum = 0;
		// 商品中平台的产品数量
		int platformTypeGoodsNum = 0;
		// 未使用运费优惠活动的所有商品总物流费用
//		double shopTotalFreight = 0;
		// 未使用店铺/平台优惠的所有商品总价格
//		double shopTotalPrice = 0;
		// 店铺商品总价(单品已优惠)
		double shopProductTotalPrice = 0;
		// 平台商品总价(单品已优惠)
		double platformProductTotalPrice = 0;
		// 所有店铺产品的总运费
		double shopGoodsTotalFreight = 0;
		// 所有平台产品的总运费
		double platformGoodsTotalFreight = 0;
		// 订单时间
		Date orderTime = new Date();
		// 订单备注
		String memo = settlementParamDto.getMemo();
		// 对一笔订单的具体描述信息
//		StringBuffer orderBody = new StringBuffer("");
		// 商品的标题/交易标题/订单标题/订单关键字等
		StringBuilder orderSubject = new StringBuilder("");

		// 调用店铺/平台级营销活动接口 参数
		List<GoodsSkuDto> allGoodsSkuDto = new ArrayList<>();
		// 暂用保存订单ID集合
		List<Long> storeOrderIds = new ArrayList<>();

		// 商品skuID集合 (美礼卡调用参数)
		List<Long> itemsId = new ArrayList<>();

		// 店铺订单详情集合
		List<StoreOrderDetailEntity> shopOrderDetailEntitys = new ArrayList<>();
		// 平台订单详情集合
		List<StoreOrderDetailEntity> platformOrderDetailEntitys = new ArrayList<>();

		// 获取订单中的收获地址ID，并新增订单地址记录
		OrderAddressEntity orderAddressEntity = createOrderAddress(settlementParamDto.getMemberAddressId());

		// 获取订单中的发票ID，并新增订单发票记录
		OrderInvoiceEntity orderInvoiceEntity = createOrderInvoice(settlementParamDto);

		Long memberAddressId = settlementParamDto.getMemberAddressId();
		if(null == memberAddressId){
			throw new GlobalException(MobleOrderExceptionEnum.THE_DELIVERY_ADDRESS_CANNOT_BE_FOUND);
		}
		MemberAddressEntity memberAddressEntity = memberAddressDao.selectById(memberAddressId);
		if(null == memberAddressEntity || null == memberAddressEntity.getDistrictId()){
			throw new GlobalException(MobleOrderExceptionEnum.AREA_CAN_NOT_BE_DELIVERED);
		}
		// 迭代订购的商品集合，查询商品基本信息，及计算总价
		SettlementResultDto settlementResultDto = calculatedShopPrice(settlementParamDto.getSettlementGoodParamDtos(), "commitOrder",
				currentUserId, memberAddressEntity.getDistrictId().toString());
		itemsId = settlementResultDto.getItemIds();
		for (SettlementGoodInfo settlementGoodInfo : settlementResultDto.getSettlementGoodInfoList()) {
			GoodsSkuEntity goodsSkuEntity = settlementGoodInfo.getGoodsSkuEntity();
			ItemEntity itemEntity = itemDao.selectById(goodsSkuEntity.getItemId());
			if (null == itemEntity) {
				throw new GlobalException(MobleOrderExceptionEnum.GOODS_IS_NOT_FOUND);
			}
			orderSubject.append(itemEntity.getName()).append("/");
			if (null == itemEntity.getBelongStore()) {
				platformProductTotalPrice = MathCountUtils.add(platformProductTotalPrice, settlementGoodInfo.getTotalPrice());
				platformGoodsTotalFreight = MathCountUtils.add(platformGoodsTotalFreight, settlementGoodInfo.getTotalFreight());
				platformTypeGoodsNum++;
			} else {
				shopProductTotalPrice = MathCountUtils.add(shopProductTotalPrice, settlementGoodInfo.getTotalPrice());
				shopGoodsTotalFreight = MathCountUtils.add(shopGoodsTotalFreight, settlementGoodInfo.getTotalFreight());
				shopTypeGoodsNum++;
			}
			// 创建订单详情类，放入集合
			StoreOrderDetailEntity storeOrderDetailEntity = new StoreOrderDetailEntity();
			BeanUtils.copyProperties(settlementGoodInfo, storeOrderDetailEntity);
			storeOrderDetailEntity.setGoodsId(goodsSkuEntity.getId());
			storeOrderDetailEntity.setName(itemEntity.getName());
			storeOrderDetailEntity.setFieldValue(goodsSkuEntity.getSaleFieldValue());
			storeOrderDetailEntity.setGoodsImage(goodsSkuEntity.getSaleImage());
			storeOrderDetailEntity.setUnitPrice(goodsSkuEntity.getPrice());
			storeOrderDetailEntity.setState(OrderStatusEnum.CANCELLATION_OF_PAYMENT.getCode());

			// 封装调用店铺/平台接口的参数; 填充店铺/平台 订单详情集合
			GoodsSkuDto goodsSkuDto = new GoodsSkuDto();
			goodsSkuDto.setId(goodsSkuEntity.getId());
			goodsSkuDto.setOrderGoodsNum(settlementGoodInfo.getGoodsNum());
			goodsSkuDto.setPrice(settlementGoodInfo.getTotalPrice());
			if (null != itemEntity.getBelongStore()) {
				shopOrderDetailEntitys.add(storeOrderDetailEntity);
			} else {
				platformOrderDetailEntitys.add(storeOrderDetailEntity);
			}
			allGoodsSkuDto.add(goodsSkuDto);

			// 更新库存
			Integer reduceStock = goodsSkuDao.reduceStock(settlementGoodInfo.getGoodsNum().intValue(), goodsSkuEntity.getId());
			if(reduceStock < 1){
				logger.error("更新库存失败！！！！");
				throw new GlobalException(MobleOrderExceptionEnum.ORDER_SUBMIT_FAILURE);
			}
		}
		// 店铺订单集合（店铺订单+平台订单）
		List<StoreOrderEntity> storeOrderEntitys = new ArrayList<>();
		// 商品总价格（店铺订单+平台订单），不包含运费
		double productTotalPrice = 0.0;
		// 订单是否开设发票
		int storeOrderInvoiceState = orderInvoiceEntity == null ? 0 : 1;
		// 平台商品数量大于零，创建平台订单
		if (platformTypeGoodsNum > 0) {
			double platformTotalPrice = MathCountUtils.add(platformProductTotalPrice, platformGoodsTotalFreight);
			// 创建平台商品订单
			StoreOrderEntity platformOrderEntity = new StoreOrderEntity();
			platformOrderEntity.setOrderType((short) 1);
			platformOrderEntity.setMemberId(String.valueOf(currentUserId));
			platformOrderEntity.setStoreId(storeId);
			platformOrderEntity.setOrderTime(orderTime);
			platformOrderEntity.setOrderState(OrderStatusEnum.CANCELLATION_OF_PAYMENT.getCode());
			platformOrderEntity.setInvoiceState(storeOrderInvoiceState);
			platformOrderEntity.setDeliveryType(settlementParamDto.getDeliveryType());
			platformOrderEntity.setMemo(memo);
			platformOrderEntity.setTotalFreight(platformGoodsTotalFreight);
			platformOrderEntity.setActualFreight(platformGoodsTotalFreight);
			platformOrderEntity.setUsedPromotion(null);
			platformOrderEntity.setStoreDeduction(null);
			platformOrderEntity.setProductTotal(platformProductTotalPrice);
			platformOrderEntity.setOrderTotal(platformTotalPrice);
			platformOrderEntity.setLastPrice(platformTotalPrice);
			storeOrderEntitys.add(platformOrderEntity);
			productTotalPrice = MathCountUtils.add(productTotalPrice, platformProductTotalPrice);
		}
		// 店铺商品数量大于零，创建商品订单
		if (shopTypeGoodsNum > 0) {
			// 创建店铺商品订单
			double shopTotalPrice = MathCountUtils.add(shopProductTotalPrice, shopGoodsTotalFreight);
			StoreOrderEntity storeOrderEntity = new StoreOrderEntity();
			storeOrderEntity.setOrderType((short) 2);
			storeOrderEntity.setMemberId(currentUserId.toString());
			storeOrderEntity.setStoreId(storeId);
			storeOrderEntity.setOrderTime(orderTime);
			storeOrderEntity.setOrderState(OrderStatusEnum.CANCELLATION_OF_PAYMENT.getCode());
			storeOrderEntity.setInvoiceState(storeOrderInvoiceState);
			storeOrderEntity.setDeliveryType(settlementParamDto.getDeliveryType());
			storeOrderEntity.setMemo(memo);
			storeOrderEntity.setTotalFreight(shopGoodsTotalFreight);
			storeOrderEntity.setActualFreight(shopGoodsTotalFreight);
			storeOrderEntity.setUsedPromotion(null);
			storeOrderEntity.setStoreDeduction(null);
			storeOrderEntity.setProductTotal(shopProductTotalPrice);
			storeOrderEntity.setOrderTotal(shopTotalPrice);
			storeOrderEntity.setLastPrice(shopTotalPrice);
			storeOrderEntitys.add(storeOrderEntity);
			productTotalPrice = MathCountUtils.add(productTotalPrice, shopProductTotalPrice);
		}
		// 店铺商品 查询使用店铺的优惠之后的总价
		Long shopPromotionId = settlementParamDto.getShopPromotionId();
		// 物流营销活动ID
		Long logisticsPromotionId = settlementParamDto.getLogisticsPromotionId();
		// 店铺营销活动
		PromotionPriceDto shopPromotionPriceDto = null;
		if (null != shopPromotionId) {
			shopPromotionPriceDto = promotionGoodsService.getStorePromotionGoods(allGoodsSkuDto, shopPromotionId, currentUserId);
		}
		// 先声明营销活动使用记录对象（物流营销活动）
		PromotionPriceDto logisticsPromotionDto = null;
		if (null != logisticsPromotionId) {
			logisticsPromotionDto = promotionGoodsService.getStorePromotionGoods(allGoodsSkuDto, logisticsPromotionId, currentUserId);
		}

		// 总订单金额
		double totalOrderPrice = 0;
		for (StoreOrderEntity storeOrderEntity : storeOrderEntitys) {
			// 物流营销活动使用记录（用店铺订单ID作为记录表中的orderId参数值）
			PromotionStoreUsageLogEntity logisticsPromotionUsageLogEntity = null;
			if(null != logisticsPromotionDto && logisticsPromotionDto.isFreeShipping()){
				storeOrderEntity.setLastPrice(MathCountUtils.subtract(storeOrderEntity.getProductTotal(), storeOrderEntity.getActualFreight()));
				storeOrderEntity.setFreightPromotion(logisticsPromotionDto.getPromotionId());
				storeOrderEntity.setActualFreight(0.0);
				logisticsPromotionUsageLogEntity = new PromotionStoreUsageLogEntity();
				logisticsPromotionUsageLogEntity.setPromotionId(logisticsPromotionDto.getPromotionId());
				logisticsPromotionUsageLogEntity.setMemberId(currentUserId);
				logisticsPromotionUsageLogEntity.setPrice(logisticsPromotionDto.getPreferentialAmount());
				logisticsPromotionUsageLogEntity.setBusinessId(storeId);
				logisticsPromotionUsageLogEntity.setCouponId(null);
			}
			// 店铺优惠活动使用记录
			PromotionStoreUsageLogEntity storePromotionStoreUsageLogEntity = null;
			if(null != shopPromotionPriceDto && storeOrderEntity.getOrderType().equals((short) 2)){
				// 店铺优惠金额
				double promotionDeduction = shopPromotionPriceDto.getPreferentialAmount();
				storeOrderEntity.setUsedPromotion(shopPromotionPriceDto.getPromotionId());
				storeOrderEntity.setStoreDeduction(promotionDeduction);
				storePromotionStoreUsageLogEntity = new PromotionStoreUsageLogEntity();
				storePromotionStoreUsageLogEntity.setPromotionId(shopPromotionPriceDto.getPromotionId());
				storePromotionStoreUsageLogEntity.setMemberId(currentUserId);
				storePromotionStoreUsageLogEntity.setPrice(shopPromotionPriceDto.getPreferentialAmount());
				storePromotionStoreUsageLogEntity.setBusinessId(storeId);
				storePromotionStoreUsageLogEntity.setCouponId(null);
				storeOrderEntity.setLastPrice(MathCountUtils.subtract(storeOrderEntity.getLastPrice(), promotionDeduction));
			}
			storeOrderDao.insert(storeOrderEntity);
			long orderId = storeOrderEntity.getId();
			storeOrderIds.add(orderId);
			// 更新订单详情实体类中的订单ID 并插入数据库
			if(storeOrderEntity.getOrderType().equals((short)1)){
				for (StoreOrderDetailEntity storeOrderDetailEntity : platformOrderDetailEntitys) {
					storeOrderDetailEntity.setOrderId(orderId);
					mobleStoreOrderDetailDao.insert(storeOrderDetailEntity);
				}
			}else{
				if(null != logisticsPromotionUsageLogEntity){
					logisticsPromotionUsageLogEntity.setOrderId(orderId);
					promotionStoreUsageLogDao.insert(logisticsPromotionUsageLogEntity);
				}
				if(null != storePromotionStoreUsageLogEntity){
					storePromotionStoreUsageLogEntity.setOrderId(orderId);
					promotionStoreUsageLogDao.insert(storePromotionStoreUsageLogEntity);
				}
				for (StoreOrderDetailEntity storeOrderDetailEntity : shopOrderDetailEntitys) {
					storeOrderDetailEntity.setOrderId(orderId);
					mobleStoreOrderDetailDao.insert(storeOrderDetailEntity);
				}
			}
			// 更新订单发票实体类中的订单ID,并插入数据库
			if(null != orderInvoiceEntity){
				OrderInvoiceEntity orderInvoiceEntity1 = new OrderInvoiceEntity();
				BeanUtils.copyProperties(orderInvoiceEntity, orderInvoiceEntity1);
				orderInvoiceEntity1.setOrderId(orderId);
				orderInvoiceDao.insert(orderInvoiceEntity1);
			}
			// 更新订单地址实体类中的订单ID,并插入数据库
			OrderAddressEntity orderAddressEntity1 = new OrderAddressEntity();
			BeanUtils.copyProperties(orderAddressEntity, orderAddressEntity1);
			orderAddressEntity1.setOrderId(orderId);
			orderAddressDao.insert(orderAddressEntity1);
			totalOrderPrice = MathCountUtils.add(totalOrderPrice, storeOrderEntity.getLastPrice());
		}

		TotalOrderEntity totalOrderEntity = new TotalOrderEntity();
		totalOrderEntity.setOrderType((short) 1);
		totalOrderEntity.setMemberId(currentUserId);
		totalOrderEntity.setPayState(0);
		if(orderSubject.length() > 100){
			totalOrderEntity.setSubject(orderSubject.substring(0,100));
		}else{
			totalOrderEntity.setSubject(orderSubject.toString());
		}
		totalOrderEntity.setBody("亲子E站:+ " + currentUserId + "的一个订单");
		totalOrderEntity.setTotalPrice(totalOrderPrice);
		totalOrderEntity.setMemo(settlementParamDto.getMemo());

		// 平台优惠逻辑处理
		// 初始总金额（只经历过单品优惠+店铺优惠后的所有商品+运费的价格）
		// afterPromotionTotalPrice经历平台优惠后的价格
		double afterPromotionTotalPrice = totalOrderPrice;
		PromotionPlatformUsageLogEntity promotionPlatformUsageLogEntity = null;
		Double platformDedution = null;
		Long platformPromotionId = settlementParamDto.getPlatformPromotionId();
		if(afterPromotionTotalPrice > 0){
			if (null != platformPromotionId) {
				PromotionPriceDto promotionPriceDto = null;
				if(shopPromotionId == null) {
					promotionPriceDto = promotionGoodsService.getStorePromotionGoods(allGoodsSkuDto, platformPromotionId, currentUserId);
				}else {
					promotionPriceDto = promotionGoodsService.getPlatformPromotionGoods(allGoodsSkuDto, shopPromotionId, platformPromotionId, currentUserId);
				}
				if (null != promotionPriceDto) {
					promotionPlatformUsageLogEntity = new PromotionPlatformUsageLogEntity();
					double preferentialAmount = promotionPriceDto.getPreferentialAmount();
					promotionPlatformUsageLogEntity.setMemberId(currentUserId);
					promotionPlatformUsageLogEntity.setMarketId(promotionPriceDto.getPromotionId());
					promotionPlatformUsageLogEntity.setPrice(preferentialAmount);
					if(afterPromotionTotalPrice < preferentialAmount){
						afterPromotionTotalPrice = 0.0;
					}else{
						afterPromotionTotalPrice = MathCountUtils.subtract(afterPromotionTotalPrice, preferentialAmount);
					}
					totalOrderEntity.setPlatformDeduction(preferentialAmount);
					totalOrderEntity.setPlatformPromotion(platformPromotionId);

					platformDedution = promotionPlatformUsageLogEntity.getPrice();
				}
			}
		}


		/**
		 * 美礼卡优惠逻辑处理
		 */
		// afterPromotionTotalPrice经历美礼卡优惠之后的价格
		double afterGiftCardTotalPrice = afterPromotionTotalPrice;
		boolean useGiftCardFalg = false;
		double giftCardValue = 0.0;
		if(afterGiftCardTotalPrice > 0){
			// 美礼卡面值; 美礼卡面额
			giftCardValue = settlementParamDto.getGiftCardValue();
			if (giftCardValue > 0) {
				if(afterGiftCardTotalPrice < giftCardValue){
					afterGiftCardTotalPrice = 0.0;
				}else{
					afterGiftCardTotalPrice = MathCountUtils.subtract(afterGiftCardTotalPrice, giftCardValue);
				}
				totalOrderEntity.setCardPrice(giftCardValue);
				useGiftCardFalg = true;
			} else {
				totalOrderEntity.setCardPrice(0.0);
			}
		}

		/**
		 * 积分优惠逻辑处理
		 */
		// afterPromotionTotalPrice经历美礼卡优惠之后的价格
		double afterUserScoreTotalPrice = afterGiftCardTotalPrice;
		// 积分抵扣的金额
		double userScorePrice = 0;
		long usageScore = 0L;
		boolean useScoreFalg = false;
		if(afterUserScoreTotalPrice > 0){
			usageScore = settlementParamDto.getUsageScore();
			if (usageScore > 0) {
				// 可使用的积分值
				MemberEntity memberEntity = memberMgmtDao.selectById(currentUserId);
				Long validMemberIntegral = memberEntity.getOwnScore();
				if (null == validMemberIntegral || validMemberIntegral < usageScore) {
					throw new GlobalException(MemberPointExceptionEnum.MEMBERPOINT_USAGESCORE_NOT_MORE);
				}
				if (usageScore > 0) {
					BigDecimal usageScoreBD = new BigDecimal(settlementParamDto.getUsageScore());
					totalOrderEntity.setUsageScore(usageScore);
					// 暂时一分积分等于一毛钱
					userScorePrice = usageScoreBD.multiply(new BigDecimal(scoreRate)).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
					totalOrderEntity.setScorePrice(userScorePrice);
				}
				if(afterUserScoreTotalPrice < userScorePrice){
					afterUserScoreTotalPrice = 0.0;
				}else{
					afterUserScoreTotalPrice = MathCountUtils.subtract(afterUserScoreTotalPrice, userScorePrice);
				}
				useScoreFalg = true;
			} else {
				totalOrderEntity.setUsageScore(0L);
				totalOrderEntity.setScorePrice(0.0);
			}
		}

		// 最后支付金额
		totalOrderEntity.setPayPrice(afterUserScoreTotalPrice);
		totalOrderDao.insert(totalOrderEntity);
		logger.info("创建总订单totalOrderEntity" + JSONObject.toJSONString(totalOrderEntity));
		Long totalOrderId = totalOrderEntity.getId();

		// 店铺订单重新迭代更新平台订单号字段
		for (StoreOrderEntity storeOrderEntity : storeOrderEntitys) {
			// 分摊平台优惠金额
			if(null != platformDedution && null != platformPromotionId){
				BigDecimal productPrice = new BigDecimal(storeOrderEntity.getProductTotal());
				BigDecimal rate = productPrice.divide(new BigDecimal(productTotalPrice), 5);
				storeOrderEntity.setPlatformDeduction(new BigDecimal(platformDedution).multiply(rate).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
				storeOrderEntity.setUsedPlatformPromotion(platformPromotionId);
				storeOrderEntity.setLastPrice(MathCountUtils.subtract(storeOrderEntity.getLastPrice(), storeOrderEntity.getPlatformDeduction()));
			}
			storeOrderEntity.setTotalOrderNo(totalOrderId);
			storeOrderEntity.setLastModifierId(currentUserId);
			storeOrderEntity.setLastModifiedTime(currentDate);
			storeOrderDao.updateById(storeOrderEntity);
		}

		// 保存平台营销活动使用记录
		if (null != promotionPlatformUsageLogEntity) {
			promotionPlatformUsageLogEntity.setOrderId(totalOrderId);
			promotionPlatformUsageLogDao.insert(promotionPlatformUsageLogEntity);
			logger.info("保存平台营销活动使用记录platformPromotionStoreUsageLogEntity" + JSONObject.toJSONString(promotionPlatformUsageLogEntity));
		}

		// 1：插入积分使用记录
		if (useScoreFalg) {
			memberPointService.subtractMemberPoint(currentUserId, totalOrderId, usageScore, userScorePrice);
		}

		// 2：插入美礼卡使用记录
		if (useGiftCardFalg) {
			Long[] itemIds = new Long[itemsId.size()];
			itemsId.toArray(itemIds);
			giftCardDetailService.buyGoodsOfGiftCard(storeId, currentUserId, itemIds, giftCardValue, totalOrderId);
		}
		
		
		// 使用完各种优惠之后 实际支付金额小于等于0 则直接修改状态为待发货
		if(afterUserScoreTotalPrice <= 0){
			for (StoreOrderEntity storeOrderEntity : storeOrderEntitys) {
				updateAllStoreOrderDetailState(storeOrderEntity.getId(), OrderStatusEnum.PENDING_DELIVERY.getCode(), currentUserId);
			}
			totalOrderEntity.setPayState(2);
			totalOrderEntity.setPayType("3");
		}else{
			// 暂修改订单状态，默认已支付，进入待发货状态
			for (Long storeOrderId : storeOrderIds) {
				StoreOrderEntity storeOrderEntity = storeOrderDao.selectById(storeOrderId);
				List<StoreOrderDetailEntity> storeOrderDetailList = mobleStoreOrderDetailDao.findMobleStoreOrderDetailByOrderId(storeOrderId);
				for (StoreOrderDetailEntity storeOrderDetailEntity : storeOrderDetailList) {
					storeOrderDetailEntity.setState(OrderStatusEnum.PENDING_DELIVERY.getCode());
					mobleStoreOrderDetailDao.updateById(storeOrderDetailEntity);
				}
				storeOrderEntity.setOrderState(OrderStatusEnum.PENDING_DELIVERY.getCode());
				storeOrderDao.updateById(storeOrderEntity);
			}
		}
		totalOrderDao.updateById(totalOrderEntity);
		return totalOrderEntity;
	}


	@Override
	public Double choosePromotionCalculationPrice(SettlementParamDto settlementParamDto) {
		TokenDto tokenDto = TokenUtil.getTokenDetail(settlementParamDto.getToken());
		if(null == tokenDto){
			throw new GlobalException(MobleSecurityExceptionEnum.TOKEN_DOES_NOT_EXIST);
		}
//		Long storeId = tokenDto.getStoreId();
		Long currentUserId = tokenDto.getMemberId();
		if(null == currentUserId){
			throw new GlobalException(MobleSecurityExceptionEnum.TOKEN_DOES_NOT_EXIST);
		}

		// 店铺商品 查询使用店铺的优惠之后的总价
		Long shopPromotionId = settlementParamDto.getShopPromotionId();

		Long platformPromotionId = settlementParamDto.getPlatformPromotionId();

		List<GoodsSkuDto> allGoodsSkuDto = new ArrayList<>();

		Double totalPrice = new Double("0.0");

		// 迭代订购的商品集合，查询商品基本信息，及计算总价
		SettlementResultDto settlementResultDto = calculatedShopPrice(settlementParamDto.getSettlementGoodParamDtos(), "", currentUserId, "");
		for (SettlementGoodInfo settlementGoodInfo : settlementResultDto.getSettlementGoodInfoList()) {
			GoodsSkuEntity goodsSkuEntity = settlementGoodInfo.getGoodsSkuEntity();

			totalPrice = MathCountUtils.add(totalPrice, settlementGoodInfo.getTotalPrice());

			// 封装调用店铺/平台接口的参数; 填充店铺/平台 订单详情集合
			GoodsSkuDto goodsSkuDto = new GoodsSkuDto();
			goodsSkuDto.setId(goodsSkuEntity.getId());
			goodsSkuDto.setOrderGoodsNum(settlementGoodInfo.getGoodsNum());
			goodsSkuDto.setPrice(settlementGoodInfo.getTotalPrice());
			allGoodsSkuDto.add(goodsSkuDto);
		}

		if (null != shopPromotionId) {
			try {
				PromotionPriceDto promotionPriceDto = promotionGoodsService.getStorePromotionGoods(allGoodsSkuDto, shopPromotionId, currentUserId);
				if (null != promotionPriceDto) {
//					promotionPriceDto.getPromotionId();
					// 优惠金额
//					promotionPriceDto.getPreferentialAmount();
					// 现价金额
					totalPrice = promotionPriceDto.getPresentPrice();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new GlobalException(MobleOrderExceptionEnum.PROMOTION_PROCESS_FAILURE);
			}
		}

		if(totalPrice > 0){
			if (null != platformPromotionId) {
				try {
					PromotionPriceDto promotionPriceDto = null;
					if(shopPromotionId == null) {
						promotionPriceDto = promotionGoodsService.getStorePromotionGoods(allGoodsSkuDto, platformPromotionId, currentUserId);
					}else {
						promotionPriceDto = promotionGoodsService.getPlatformPromotionGoods(allGoodsSkuDto, shopPromotionId, platformPromotionId, currentUserId);
					}
					if (null != promotionPriceDto) {
//						promotionPriceDto.getPromotionId();
						// 优惠金额
//						promotionPriceDto.getPreferentialAmount();
						// 现价金额
						totalPrice = promotionPriceDto.getPresentPrice();
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new GlobalException(MobleOrderExceptionEnum.PROMOTION_PROCESS_FAILURE);
				}
			}
		}
		return totalPrice;
	}

	@Override
	public TotalOrderEntity findTotalOrderById(String totalOrderId) {
		return totalOrderDao.selectById(totalOrderId);
	}

	/**
	 * Description： 创建订单地址的实体类
	 * <p>
	 * Author hxpeng
	 *
	 * @param memberAddressId
	 * @return
	 * @throws GlobalException
	 */
	private OrderAddressEntity createOrderAddress(Long memberAddressId) throws GlobalException {
		OrderAddressEntity orderAddressEntity = new OrderAddressEntity();
		if (null == memberAddressId) {
			throw new GlobalException(MemberAddressExceptionEnum.MEMBERADDRESS_ID_NOT_NULL);
		}
		MemberAddressEntity memberAddressEntity = memberAddressDao.selectById(memberAddressId);
		if (null == memberAddressEntity) {
			throw new GlobalException(MemberAddressExceptionEnum.MEMBERADDRESS_ID_NOT_EXIST);
		}
		BeanUtils.copyProperties(memberAddressEntity, orderAddressEntity);
		orderAddressEntity.setLandline(memberAddressEntity.getPlaneNumber());
		try {
			DistrictEntity districtEntity = districtService.selectOneById(memberAddressEntity.getDistrictId());
			if (null != districtEntity) {
				// 用户地址必须正确的第三级区域ID，否则数组越界！
				String serialNo = districtEntity.getSerialNo();
				// split. 号需要双斜杠转义
				String[] districtIds = serialNo.split("\\.");
				orderAddressEntity.setProvinceId(new Long(districtIds[0]));
				orderAddressEntity.setCityId(new Long(districtIds[1]));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("插入订单创建订单地址表时，查询区域块异常，操作参数DistrictId:" + memberAddressEntity.getDistrictId());
			orderAddressEntity.setProvinceId(null);
		}
		orderAddressEntity.setId(null);
		return orderAddressEntity;
	}

	/**
	 * Description： 校验订单发票的实体类
	 * <p>
	 * Author hxpeng
	 *
	 * @param settlementParamDto
	 * @return
	 * @throws GlobalException
	 */
	private OrderInvoiceEntity createOrderInvoice(SettlementParamDto settlementParamDto) throws GlobalException {
		OrderInvoiceEntity orderInvoiceEntity = settlementParamDto.getOrderInvoiceEntity();
//		if (null == orderInvoiceEntity) {
//			throw new GlobalException(MobleOrderExceptionEnum.ORDER_INVOICE_ILLEGAL);
//		}else{
//			if (null == orderInvoiceEntity.getInvoiceType() || null == orderInvoiceEntity.getTitle()
//					|| null == orderInvoiceEntity.getText()) {
//				throw new GlobalException(MobleOrderExceptionEnum.ORDER_INVOICE_ILLEGAL);
//			}
//			return orderInvoiceEntity;
//		}
		if (null != orderInvoiceEntity) {
			if (null == orderInvoiceEntity.getInvoiceType() || null == orderInvoiceEntity.getTitle()
					|| null == orderInvoiceEntity.getText()) {
				throw new GlobalException(MobleOrderExceptionEnum.ORDER_INVOICE_ILLEGAL);
			}
			return orderInvoiceEntity;
		}
		return null;
	}

	@Override
	// 设置回滚异常类为Exception类
	@Transactional(rollbackFor = Exception.class)
	public Integer cancelOrder(SettlementParamDto settlementParamDto) throws GlobalException {
		// 当前会员编号 暂时写死
		Date currentDate = new Date();
		Long currentUserId = TokenUtil.getTokenDetail(settlementParamDto.getToken()).getMemberId();
		if(null == currentUserId){
			throw new GlobalException(MobleSecurityExceptionEnum.TOKEN_DOES_NOT_EXIST);
		}
		Long storeOrderId = settlementParamDto.getStoreOrderId();
		StoreOrderEntity storeOrderEntity = storeOrderDao.selectById(storeOrderId);
		List<StoreOrderDetailEntity> storeOrderDetailList = mobleStoreOrderDetailDao.findMobleStoreOrderDetailByOrderId(storeOrderId);
		if(null == storeOrderEntity || null == storeOrderDetailList || storeOrderDetailList.size() < 1){
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_IS_NOT_FOUND);
		}
		try {
			if (storeOrderEntity.getOrderState() == OrderStatusEnum.ORDER_CANCELLATION.getCode()) {
				// 订单已经取消过了！
				return 0;
			}
			for (StoreOrderDetailEntity storeOrderDetailEntity : storeOrderDetailList) {
				GoodsSkuEntity goodsSkuEntity = goodsSkuDao.selectById(storeOrderDetailEntity.getGoodsId());
				if (goodsSkuEntity == null) {
					throw new GlobalException(MobleOrderExceptionEnum.ORDER_CANCEL_FAILURE);
				}
				// 更新库存
				Long stockNumber = goodsSkuEntity.getStockNumber();
				Long orderProductNumber = storeOrderDetailEntity.getGoodsNum();
				goodsSkuEntity.setStockNumber(stockNumber + orderProductNumber);
				goodsSkuEntity.setLastModifierId(currentUserId);
				goodsSkuEntity.setLastModifiedTime(currentDate);
				goodsSkuDao.updateById(goodsSkuEntity);

				storeOrderDetailEntity.setState(OrderStatusEnum.ORDER_CANCELLATION.getCode());
				storeOrderDetailEntity.setLastModifierId(currentUserId);
				storeOrderDetailEntity.setLastModifiedTime(currentDate);
				mobleStoreOrderDetailDao.updateById(storeOrderDetailEntity);
			}
			// 删除店铺订单使用的营销活动的记录
			PromotionStoreUsageLogEntity shopPromotionStoreUsageLogEntity = promotionStoreUsageLogDao.selectOneByOrderId(storeOrderId);
			if (null != shopPromotionStoreUsageLogEntity) {
				shopPromotionStoreUsageLogEntity.setDeleteFlag((byte) 1);
				promotionStoreUsageLogDao.updateById(shopPromotionStoreUsageLogEntity);
			}
			storeOrderEntity.setOrderState(OrderStatusEnum.ORDER_CANCELLATION.getCode());

			// 返回使用积分，使用的美礼卡，营销活动使用次数
			// 返回用户积分
			Long totalOrderId = storeOrderEntity.getTotalOrderNo();

			TotalOrderEntity totalOrderEntity = totalOrderDao.selectById(totalOrderId);
			totalOrderEntity.setPayState(3);
			totalOrderEntity.setLastModifiedTime(currentDate);
			totalOrderEntity.setLastModifierId(currentUserId);
			totalOrderDao.updateById(totalOrderEntity);

			memberPointService.returnMemberPoint(totalOrderId);
			// 返回美礼卡面额
			giftCardDetailService.refundByTotalOrderId(totalOrderId);

			// 删除平台级订单使用的营销活动的记录
			PromotionPlatformUsageLogEntity promotionPlatformUsageLogEntity = promotionPlatformUsageLogDao.selectOneByOrderId(totalOrderId);
			if (null != promotionPlatformUsageLogEntity) {
				promotionPlatformUsageLogEntity.setDeleteFlag(new Byte("1"));
				promotionPlatformUsageLogDao.updateById(promotionPlatformUsageLogEntity);
			}
			storeOrderEntity.setLastModifiedTime(currentDate);
			storeOrderEntity.setLastModifierId(currentUserId);
			return storeOrderDao.updateById(storeOrderEntity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_CANCEL_FAILURE);
		}
	}

	@Override
	public List<StoreOrderDetailDto> getWaitToDeliverGoods(Long storeOrderId) {
		return mobleStoreOrderDetailDao.getWaitToDeliverGoods(storeOrderId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deliverGoods(List<Long> storeOrderDetailIds, Long storeOrderId, String logisticsNo, String logisticsType) throws GlobalException {
		try {
			long currentMemberId = SecurityContextUtils.getCurrentSystemUser().getId();
			Date currentDate = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);
			calendar.add(Calendar.DATE, 7);
			List<StoreOrderDetailEntity> storeOrderDetailEntityList = mobleStoreOrderDetailDao.findOrderDetailList(storeOrderDetailIds, storeOrderId);
			for (StoreOrderDetailEntity storeOrderDetailEntity : storeOrderDetailEntityList){
				if(OrderStatusEnum.changeOrderStateFlag(storeOrderDetailEntity.getState(), OrderStatusEnum.PENDING_RECEIVED.getCode())){
					storeOrderDetailEntity.setLogisticsNo(logisticsNo);
					storeOrderDetailEntity.setLogisticsType(logisticsType);
					storeOrderDetailEntity.setShipmentsTime(currentDate);
					storeOrderDetailEntity.setTakeTime(calendar.getTime());
					storeOrderDetailEntity.setLastModifierId(currentMemberId);
					storeOrderDetailEntity.setLastModifiedTime(currentDate);
					mobleStoreOrderDetailDao.updateById(storeOrderDetailEntity);
				}
			}
			// 更新订单详情集合状态
			updateSomeStoreOrderDetailState(storeOrderDetailIds, storeOrderId, OrderStatusEnum.PENDING_RECEIVED.getCode(), currentMemberId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GlobalException(MobleOrderExceptionEnum.FAILURE_OF_ORDER_DELIVERY);
		}
	}


	@Override
	public String alipayPaymentOrder(String totalOrderId, String returnUrl) throws GlobalException {
		TotalOrderEntity totalOrderEntity = totalOrderDao.selectById(totalOrderId);
		if (null != totalOrderEntity) {
			AlipayPayDto alipayPayDto = new AlipayPayDto();
			alipayPayDto.setBody(totalOrderEntity.getBody());
			alipayPayDto.setSubject(totalOrderEntity.getSubject());
			alipayPayDto.setOut_trade_no(totalOrderId);
			alipayPayDto.setSeller_id(AlipayConfig.PID);
			alipayPayDto.setTotal_amount(new BigDecimal(totalOrderEntity.getPayPrice()));
//			alipayPayDto.setTotal_amount(new BigDecimal("0.01"));
			return ThirdPartyAlipayClient.payParameterSignature(alipayPayDto,returnUrl);
		}else{
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_IS_NOT_FOUND);
		}
	}

	@Override
	public Integer alipaySynchroNotic(Map<String, String[]> requestParams) throws GlobalException {
		logger.info("================================================================================================================");
		logger.info("================================================================================================================");
		logger.info("================================================================================================================");
		logger.info("接收支付包的异步请求============================================================================================");
		logger.info("================================================================================================================");
		logger.info("================================================================================================================");
		logger.info("================================================================================================================");
		try {
			boolean payStatus = ThirdPartyAlipayClient.checkResponseSign(requestParams);
			logger.info("================================================================================================================");
			logger.info("================================================================================================================");
			logger.info("同步通知验签结果：" + payStatus);
			if(payStatus){
				return 1;
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer alipayAsyncNotic(Map<String, String[]> requestParams) throws GlobalException {
		try {
			logger.info("================================================================================================================");
			logger.info("================================================================================================================");
			logger.info("接收支付宝的异步请求============================================================================================");
			logger.info("================================================================================================================");
			logger.info("================================================================================================================");
			boolean checkSign = ThirdPartyAlipayClient.checkResponseSign(requestParams);
			logger.info("================================================================================================================");
			logger.info("================================================================================================================");
			logger.info("异步通知验签结果：" + checkSign);
			Long totalOrderId = Long.parseLong(requestParams.get("out_trade_no")[0]);
			TotalOrderEntity totalOrderEntity = totalOrderDao.selectById(totalOrderId);
			if(null == totalOrderEntity){
				throw new GlobalException(MobleOrderExceptionEnum.ORDER_IS_NOT_FOUND);
			}
			if(checkSign){
				String appId = requestParams.get("app_id")[0];
				if(!AlipayConfig.APP_ID.equals(appId)){
					logger.info("================================================================================================================");
					logger.info("APPID 不对！！！！！！");
					logger.info("================================================================================================================");
					/**
					 * 抛一个金额不对的异常
					 */
				}
				String pid = requestParams.get("seller_id")[0];
				if(!AlipayConfig.PID.equals(pid)){
					logger.info("================================================================================================================");
					logger.info("PID 不对！！！！！！");
					logger.info("================================================================================================================");
					/**
					 * 抛一个金额不对的异常
					 */
				}
				Double totalAmount = Double.parseDouble(requestParams.get("total_amount")[0]);
				// 校验金额一致
				if(totalAmount.compareTo(totalOrderEntity.getPayPrice()) != 0){
					logger.info("================================================================================================================");
					logger.info("金额不对,金额不对！！！！！！");
					logger.info("================================================================================================================");
					/**
					 * 抛一个金额不对的异常
					 */
				}
				String tradeStatus = requestParams.get("trade_status")[0];
				if(tradeStatus.equals("TRADE_SUCCESS")){
					// 支付宝交易号
					String tradeNo = requestParams.get("trade_no")[0];
					// 买方支付宝账号
					String customerId = requestParams.get("buyer_logon_id")[0];

					OrderPaymentLogEntity orderPaymentLogEntity  = new OrderPaymentLogEntity();
					orderPaymentLogEntity.setPayType("2");
					orderPaymentLogEntity.setOtherAccount(customerId);
					orderPaymentLogEntity.setPayOrderId(String.valueOf(totalOrderId));
					// 1：成功，0:失败
					orderPaymentLogEntity.setSuccessState(1);
					orderPaymentLogEntity.setMoney(totalAmount);
					paymentLogDao.insert(orderPaymentLogEntity);

					// 更新支付订单下所有关联订单状态
					List<StoreOrderEntity> storeOrderEntities = storeOrderDao.selectByTotalOrderId(totalOrderId);
					for (StoreOrderEntity storeOrderEntity : storeOrderEntities){
						updateAllStoreOrderDetailState(storeOrderEntity.getId(), OrderStatusEnum.PENDING_DELIVERY.getCode(), null);
					}
					totalOrderEntity.setPayCallbackNo(tradeNo);
					totalOrderEntity.setPayTime(new Date());
					totalOrderEntity.setPayState(2);
				}else{
					// 付款失败
					totalOrderEntity.setPayState(1);
				}
				return totalOrderDao.updateById(totalOrderEntity);
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String wechatPaymentOrder(String totalOrderId, String ip, String token) throws GlobalException {
		TokenDto tokenDto = TokenUtil.getTokenDetail(token);
		TotalOrderEntity totalOrderEntity = totalOrderDao.selectById(totalOrderId);
		if (null != totalOrderEntity) {
			WeChatPayDto weChatPayDto = new WeChatPayDto();
//			weChatPayDto.setBody("这是一个商品的测试数据！");
//			weChatPayDto.setDetail("这是一个商品的标题/交易标题/订单标题/订单关键字等。");
			weChatPayDto.setBody(totalOrderEntity.getBody());
			weChatPayDto.setDetail(totalOrderEntity.getSubject());
			weChatPayDto.setAttach("滨江浦沿分店");
			weChatPayDto.setOut_trade_no(totalOrderId);
			// 分为单位
			int payPrice = new BigDecimal(totalOrderEntity.getPayPrice()).multiply(new BigDecimal(100)).intValue();
//			int payPrice = 1;
			weChatPayDto.setTotal_fee(String.valueOf(payPrice));
			weChatPayDto.setSpbill_create_ip(ip);
			// 暂用我自己的openId
			MemberBindAccountEntity memberBindAccountEntity = memberBindAccountDao.findOneByMemberInWechat(tokenDto.getMemberId());
			if(null != memberBindAccountEntity){
				weChatPayDto.setOpenId(memberBindAccountEntity.getPlatformAccount());
			}else{
				/**
				 * TODO 抛一个异常！
				 */
			}
//			String openId = "";
//			if(null != tokenDto){
//				Long memberId = tokenDto.getMemberId();
//				MemberBindAccountEntity memberBindAccountEntity = memberBindAccountDao.findOneByMemberIdAndAccountType(memberId, 1);
//				if(null != memberBindAccountEntity){
//					openId = memberBindAccountEntity.getPlatformAccount();
//				}
//			}
//			if(StringUtils.isEmpty(openId)){
//				throw new GlobalException(MobleOrderExceptionEnum.EXTENDED_RECEIPT_FAILURE);
//			}
//			weChatPayDto.setOpenId(openId);
			return JSONObject.toJSONString(ThirdPartyWeChatClient.payParameterSignature(weChatPayDto));
		}else{
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_IS_NOT_FOUND);
		}
	}

	@Override
	public Integer wechatPayAsyncNotic(Map<String, String> paramsMap) throws GlobalException {
		try {
			logger.info("================================================================================================================");
			logger.info("================================================================================================================");
			logger.info("================================================================================================================");
			logger.info("接收微信支付的异步请求============================================================================================");
			logger.info("================================================================================================================");
			logger.info("================================================================================================================");
			logger.info("================================================================================================================");
			boolean checkSign = ThirdPartyWeChatClient.checkSign(paramsMap);
			logger.info("================================================================================================================");
			logger.info("================================================================================================================");
			logger.info("异步通知验签结果：" + checkSign);
			if(checkSign){
				// 返回状态码
				String returnCode = paramsMap.get("return_code");
				if(returnCode.equals("SUCCESS")){
					// 业务结果
					String resultCode = paramsMap.get("result_code");
					// 微信支付订单号1
					String transactionId = paramsMap.get("transaction_id");
					// 自身平台订单号
					Long totalOrderId = Long.parseLong(paramsMap.get("out_trade_no"));
					// 订单总金额（分单位）
					Integer totalFee = Integer.parseInt(paramsMap.get("total_fee"));
					// 原订单的信息
					TotalOrderEntity totalOrderEntity = totalOrderDao.selectById(totalOrderId);
					if(null == totalOrderEntity){
						throw new GlobalException(MobleOrderExceptionEnum.ORDER_IS_NOT_FOUND);
					}
					// 付款成功
					if(totalOrderEntity.getPayState() == 2){
						return 1;
					}
					Integer totalPrice = totalOrderEntity.getPayPrice().intValue() * 100;
					if(resultCode.equals("SUCCESS")){
						//校验金额一致
						if(totalFee.compareTo(totalPrice) != 0){
							logger.info("================================================================================================================");
							logger.info("金额不对,金额不对！！！！！！");
							logger.info("================================================================================================================");
							/**
							 * 抛一个金额不对的异常
							 */
						}
						OrderPaymentLogEntity orderPaymentLogEntity  = new OrderPaymentLogEntity();
						orderPaymentLogEntity.setPayType("2");
						// 支付用户的openID
						String openId = paramsMap.get("openId");
						orderPaymentLogEntity.setOtherAccount(openId);
						orderPaymentLogEntity.setPayOrderId(String.valueOf(totalOrderId));
						// 1：成功，0:失败
						orderPaymentLogEntity.setSuccessState(1);
						orderPaymentLogEntity.setMoney(totalFee/100);
						paymentLogDao.insert(orderPaymentLogEntity);

						//更新支付订单下所有关联订单状态
						List<StoreOrderEntity> storeOrderEntities = storeOrderDao.selectByTotalOrderId(totalOrderId);
						for (StoreOrderEntity storeOrderEntity : storeOrderEntities){
							updateAllStoreOrderDetailState(storeOrderEntity.getId(), OrderStatusEnum.PENDING_DELIVERY.getCode(), null);
						}
						totalOrderEntity.setPayCallbackNo(transactionId);
						totalOrderEntity.setPayState(2);
					}else{
						//付款失败
						totalOrderEntity.setPayState(1);
					}
					totalOrderEntity.setPayType("1");
					return totalOrderDao.updateById(totalOrderEntity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void confirmReceipt(List<Long> storeOrderDetailIds,Long storeOrderId, Long memberId) {
		updateSomeStoreOrderDetailState(storeOrderDetailIds, storeOrderId, OrderStatusEnum.HAVE_BEEN_RECEIVED.getCode(), memberId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void extendedGoodsReceipt(List<Long> storeOrderDetailIds, Long storeOrderId, Long memberId) {
		Date currentDate = new Date();
		List<StoreOrderDetailEntity> storeOrderDetailEntityList = mobleStoreOrderDetailDao.findOrderDetailList(storeOrderDetailIds, storeOrderId);
		if(null == storeOrderDetailEntityList || storeOrderDetailEntityList.size() < 1){
			logger.error("查询不到storeOrderId:" + storeOrderId + "的下的所有售后详情订单");
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_IS_NOT_FOUND);
		}
		try{
			for (StoreOrderDetailEntity storeOrderDetailEntity : storeOrderDetailEntityList){
				Integer storeOrderState = storeOrderDetailEntity.getState();
				// 是否已发货
				if(storeOrderState.equals(OrderStatusEnum.PENDING_RECEIVED.getCode())){
					// 时区
					ZoneId zone = ZoneId.systemDefault();
					// 发货时间
					Date shipmentsTime = storeOrderDetailEntity.getShipmentsTime();
					// 收货时间
					Date takeTime = storeOrderDetailEntity.getTakeTime();

					Instant shipmentsTimeInstant = shipmentsTime.toInstant();
					LocalDate shipmentsLocalDate = LocalDateTime.ofInstant(shipmentsTimeInstant, zone).toLocalDate();

					Instant takeTimeInstant = takeTime.toInstant();
					LocalDate takeTimeLocalDate = LocalDateTime.ofInstant(takeTimeInstant, zone).toLocalDate();

					// 相差天数
					long betweenDays = takeTimeLocalDate.toEpochDay() - shipmentsLocalDate.toEpochDay();
					// 默认其他收货
					if(betweenDays > 7){
						throw new GlobalException(MobleOrderExceptionEnum.EXTENDED_RECEIPT_FAILURE);
					}else{
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(takeTime);
						// 收货时间推迟三天
						calendar.add(Calendar.DATE, 3);
						storeOrderDetailEntity.setTakeTime(calendar.getTime());
						storeOrderDetailEntity.setLastModifiedTime(currentDate);
						storeOrderDetailEntity.setLastModifierId(memberId);
						mobleStoreOrderDetailDao.updateById(storeOrderDetailEntity);
					}
				}else {
					throw new GlobalException(MobleOrderExceptionEnum.EXTENDED_RECEIPT_FAILURE);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			throw new GlobalException(MobleOrderExceptionEnum.EXTENDED_RECEIPT_FAILURE);
		}
	}


	/**
	 *
	 * Description: 修改(订单ID下全部的)订单详情状态
	 *
	 * Author: hxpeng
	 * createTime: 2017/11/30
	 *
	 * @param:storeOrderId：店铺订单状态
	 * @param:orderState：待修改的状态
	 * @pa+ram:memberId：操作人
	 **/
	private void updateAllStoreOrderDetailState(Long storeOrderId, Integer orderState, Long memberId){
		Date currentDate = new Date();
		if(null != storeOrderId){
			StoreOrderEntity storeOrderEntity = storeOrderDao.selectById(storeOrderId);
			if(null == storeOrderEntity){
				logger.error("查询不到storeOrderId:" + storeOrderId + "的售后订单");
				throw new GlobalException(MobleOrderExceptionEnum.ORDER_IS_NOT_FOUND);
			}
			List<StoreOrderDetailEntity> orderDetailsEntityList = mobleStoreOrderDetailDao.findMobleStoreOrderDetailByOrderId(storeOrderId);
			if(null != orderDetailsEntityList && orderDetailsEntityList.size() > 0){
				updateOrderDetailState(orderDetailsEntityList, orderState, memberId, currentDate);
				storeOrderEntity.setOrderState(orderState);
				storeOrderEntity.setLastModifiedTime(currentDate);
				storeOrderEntity.setLastModifierId(memberId);
				storeOrderDao.updateById(storeOrderEntity);
			}else{
				logger.error("查询不到storeOrderId:" + storeOrderId + "的下的所有售后详情订单");
				throw new GlobalException(MobleOrderExceptionEnum.ORDER_IS_NOT_FOUND);
			}
		}
	}

	/**
	 *
	 * Description: 修改(部分)订单详情状态，
	 * 例如：PC发货，第一次选某个发货，货物被签收，但其他商品还未发货；这时再去PC发货，选择全部，怕又把收货的商品的状态变成已发货
	 *
	 * Author: hxpeng
	 * createTime: 2017/11/30
	 *
	 * @param:storeOrderDetailIds：店铺订单详情ID集合
	 * @param:storeOrderId：店铺订单ID
	 * @param:orderState：待修改的状态
	 * @param:memberId：操作人
	 **/
	private void updateSomeStoreOrderDetailState(List<Long> storeOrderDetailIds,Long storeOrderId, Integer orderState, Long memberId){
		StoreOrderEntity storeOrderEntity = storeOrderDao.selectById(storeOrderId);
		if(null == storeOrderEntity){
			logger.error("查询不到storeOrderId:" + storeOrderId + "的售后订单");
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_IS_NOT_FOUND);
		}
		if(null != storeOrderDetailIds && storeOrderDetailIds.size() >0){
			Date currentDate = new Date();
			// 修改参数中的订单详情集合的状态
			updateOrderDetailState(mobleStoreOrderDetailDao.findOrderDetailList(storeOrderDetailIds, storeOrderId), orderState, memberId, currentDate);
			// 取所有详情中最小的状态值 为订单状态值
			int lastState = 0;
			List<StoreOrderDetailEntity> orderDetailsEntityList = mobleStoreOrderDetailDao.findMobleStoreOrderDetailByOrderId(storeOrderId);
			for (StoreOrderDetailEntity storeOrderDetailEntity : orderDetailsEntityList) {
				int state = storeOrderDetailEntity.getState();
				if(lastState == 0){
					lastState = state;
				}
				if(lastState > state){
					lastState = state;
				}
			}
			// 售后订单详情集合中最初始的状态值与订单的状态值，判断是否一样，不一样则更新
			if(lastState != storeOrderEntity.getOrderState()){
				storeOrderEntity.setOrderState(lastState);
				storeOrderEntity.setLastModifierId(memberId);
				storeOrderEntity.setLastModifiedTime(currentDate);
				storeOrderDao.updateById(storeOrderEntity);
			}
		}else{
			logger.error("查询不到storeOrderId:" + storeOrderId + "的下的所有售后详情订单");
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_PARAM_ILLEGAL);
		}
	}

	/**
	 *
	 * Description: 修改订单状态--》抽离的公共方法区域
	 *
	 * Author: hxpeng
	 * createTime: 2017/11/30
	 *
	 * @param:
	 **/
	private void updateOrderDetailState(List<StoreOrderDetailEntity> orderDetailsEntityList, Integer orderState, Long memberId, Date currentDate){
		for (StoreOrderDetailEntity storeOrderDetailEntity : orderDetailsEntityList){
			if(OrderStatusEnum.changeOrderStateFlag(storeOrderDetailEntity.getState(), orderState)){
				if(orderState == OrderStatusEnum.HAVE_BEEN_RECEIVED.getCode()){
					storeOrderDetailEntity.setTakeTime(currentDate);
				}
				storeOrderDetailEntity.setState(orderState);
				storeOrderDetailEntity.setLastModifierId(memberId);
				storeOrderDetailEntity.setLastModifiedTime(currentDate);
				mobleStoreOrderDetailDao.updateById(storeOrderDetailEntity);
			}
		}
	}

}
