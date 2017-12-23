package com.topaiebiz.payment.order.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.util.common.math.MathCountUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.giftcard.manage.service.GiftCardDetailService;
import com.topaiebiz.member.point.service.MemberPointService;
import com.topaiebiz.payment.order.dto.RefundPriceParamsDto;
import com.topaiebiz.payment.order.dto.RefundPriceResultDto;
import com.topaiebiz.payment.order.exception.PaymentOrderExceptionEnum;
import com.topaiebiz.payment.order.moble.exception.MobleOrderExceptionEnum;
import com.topaiebiz.payment.order.service.RefundOrderService;
import com.topaiebiz.system.common.OrderStatusEnum;
import com.topaiebiz.system.moble.security.dto.TokenDto;
import com.topaiebiz.system.moble.security.util.TokenUtil;
import com.topaiebiz.system.security.util.SecurityContextUtils;
import com.topaiebiz.transaction.order.merchant.dao.OrderAddressDao;
import com.topaiebiz.transaction.order.merchant.dao.OrderDetailsDao;
import com.topaiebiz.transaction.order.merchant.dao.StoreOrderDao;
import com.topaiebiz.transaction.order.merchant.dto.OrderDetailsDto;
import com.topaiebiz.transaction.order.merchant.entity.OrderAddressEntity;
import com.topaiebiz.transaction.order.merchant.entity.OrderDetailsEntity;
import com.topaiebiz.transaction.order.merchant.entity.StoreOrderEntity;
import com.topaiebiz.transaction.order.total.dao.TotalOrderDao;
import com.topaiebiz.transaction.order.total.entity.TotalOrderEntity;
import com.topaiebiz.transaction.refundOrder.dao.RefundOrderDao;
import com.topaiebiz.transaction.refundOrder.dao.RefundOrderDetailDao;
import com.topaiebiz.transaction.refundOrder.dto.APPRefundOrderListDto;
import com.topaiebiz.transaction.refundOrder.dto.RefundDto;
import com.topaiebiz.transaction.refundOrder.dto.RefundOrderDetailDto;
import com.topaiebiz.transaction.refundOrder.dto.RefundOrderDto;
import com.topaiebiz.transaction.refundOrder.entity.RefundOrderDetailEntity;
import com.topaiebiz.transaction.refundOrder.entity.RefundOrderEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * Description 退货订单
 * 
 * 
 * Author Joe
 * 
 * Date 2017年10月17日 下午2:11:11
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
@Transactional
public class RefundOrderServiceImpl implements RefundOrderService {

	private final static Logger logger = LoggerFactory.getLogger(RefundOrderServiceImpl.class);

	// 售后申请数量限制
	private final static int REFUND_COUNT = 5;

	// 总订单
	@Autowired
	private TotalOrderDao totalOrderDao;

	// 店铺订单
	@Autowired
	private StoreOrderDao storeOrderDao;

	// 店铺订单商品详情
	@Autowired
	private OrderDetailsDao orderDetailsDao;

	// 退货订单
	@Autowired
	private RefundOrderDao refundOrderDao;

	// 退货订单详情
	@Autowired
	private RefundOrderDetailDao refundOrderDetailDao;

	// 美礼卡
	@Autowired
	private GiftCardDetailService giftCardDetailService;

	// 会员积分
	@Autowired
	private MemberPointService memberPointService;

	// 配送地址
	@Autowired
	private OrderAddressDao orderAddressDao;

	@Override
	public RefundOrderDto getRefundOrderInfo(Long refundOrderId) {
		RefundOrderEntity refundOrderEntity = refundOrderDao.selectById(refundOrderId);
		List<RefundOrderDetailEntity> refundOrderDetailEntityList = refundOrderDetailDao.selectOrderDetailByRefundOrderId(refundOrderId);
		if(null == refundOrderEntity || null == refundOrderDetailEntityList || refundOrderDetailEntityList.size() < 1){
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_IS_NOT_FOUND);
		}
		long storeOrderId = refundOrderEntity.getStoreOrderId();
		RefundOrderDto refundOrderDto = new RefundOrderDto();
		BeanUtils.copyProperties(refundOrderEntity, refundOrderDto);
		int refundGoodNum = 0;
		List<RefundOrderDetailDto> refundOrderDetailList = new ArrayList<>();
		for (RefundOrderDetailEntity refundOrderDetailEntity : refundOrderDetailEntityList){
			RefundOrderDetailDto refundOrderDetailDto = new RefundOrderDetailDto();
			BeanUtils.copyProperties(refundOrderDetailEntity, refundOrderDetailDto);
			OrderDetailsEntity orderDetailsEntity = orderDetailsDao.findByOrderIdAndGoodsId(storeOrderId, refundOrderDetailEntity.getGoodsId());
			if(null == orderDetailsEntity){
				logger.error("未根据支付店铺订单ID:"+ storeOrderId +"和商品SKUid"+refundOrderDetailEntity.getGoodsId()+"查询到订单详情！");
				throw new GlobalException(MobleOrderExceptionEnum.ORDER_IS_NOT_FOUND);
			}
			refundOrderDetailDto.setOrderDetailId(orderDetailsEntity.getId());
			refundOrderDetailDto.setStoreOrderDetailState(orderDetailsEntity.getState());
			refundOrderDetailList.add(refundOrderDetailDto);
			refundGoodNum += refundOrderDetailEntity.getGoodsNum();
		}
		refundOrderDto.setRefundOrderDetailList(refundOrderDetailList);
		refundOrderDto.setRefundGoodsNum((long)refundGoodNum);
		return refundOrderDto;
	}

	@Override
	public Page<APPRefundOrderListDto> getRefundOrderListInAPP(Page<APPRefundOrderListDto> page, Long memberId) {
		List<APPRefundOrderListDto> appOrderListResultDtos = refundOrderDao.getRefundOrderListInAPP(page,memberId);
		for (APPRefundOrderListDto appRefundOrderListDto : appOrderListResultDtos){
			List<RefundOrderDetailDto> refundOrderDetailDtoList = new ArrayList<>();
			long goodsNum = 0L;
			long refundOrderId = appRefundOrderListDto.getId();
			List<RefundOrderDetailEntity> refundOrderDetailEntityList = refundOrderDetailDao.selectOrderDetailByRefundOrderId(refundOrderId);
			for (RefundOrderDetailEntity refundOrderDetailEntity : refundOrderDetailEntityList){
				RefundOrderDetailDto refundOrderDetailDto = new RefundOrderDetailDto();
				BeanUtils.copyProperties(refundOrderDetailEntity, refundOrderDetailDto);
				refundOrderDetailDto.setId(refundOrderDetailEntity.getId());
				goodsNum = goodsNum + refundOrderDetailEntity.getGoodsNum();

				OrderDetailsEntity orderDetailsEntity = orderDetailsDao.selectById(refundOrderDetailEntity.getStoreOrderDetailId());
				if(null != orderDetailsEntity){
					refundOrderDetailDto.setStoreOrderDetailState(orderDetailsEntity.getState());
				}
				refundOrderDetailDtoList.add(refundOrderDetailDto);
			}
			appRefundOrderListDto.setGoodsNum((int)goodsNum);
			appRefundOrderListDto.setRefundOrderDetailDtoList(refundOrderDetailDtoList);
		}
		page.setRecords(appOrderListResultDtos);
		return page;
	}

	/**
	 *
	 * Description: 计算退货退款 金额
	 *
	 * Author: hxpeng
	 * createTime: 2017/11/23
	 *
	 * @param:refundPriceParamsDto 退款金额技术参数
	 * @param:confimRefund 是否为提交退款申请的请求
	 **/
	public RefundPriceResultDto calculationRefundPrice(RefundPriceParamsDto refundPriceParamsDto, boolean confimRefund) throws GlobalException {

		// 用户手动输入的金额
		double refundPrice = refundPriceParamsDto.getRefundPrice();
		// 退款订单详情ID集合(传入参数)
		List<Long> storeOrderDetailIds = refundPriceParamsDto.getStoreOrderDetailIds();
		if(null == storeOrderDetailIds || storeOrderDetailIds.size() < 1){
			throw new GlobalException(PaymentOrderExceptionEnum.CALCULATION_PARAMS_ILLEGAL);
		}
		// 店铺订单
		Long storeOrderId = refundPriceParamsDto.getStoreOrderId();
		StoreOrderEntity refundStoreOrderEntity = storeOrderDao.selectById(storeOrderId);
		if(null == refundStoreOrderEntity){
			throw new GlobalException(PaymentOrderExceptionEnum.CALCULATION_PARAMS_ILLEGAL);
		}
		// 判断申请售后的订单详情 是否符合条件
		List<OrderDetailsEntity> orderDetailsEntityList = orderDetailsDao.getOrderDetailByIds(storeOrderDetailIds);
		if(orderDetailsEntityList.size() < 1){
			logger.error("异常：[未查询到退款订单中的订单详情集合！！]");
			throw new GlobalException(PaymentOrderExceptionEnum.CALCULATION_REFUND_PRICE_FAIL);
		}
		for (OrderDetailsEntity orderDetailsEntity : orderDetailsEntityList){
			Integer orderDetailsEntityRefundState = orderDetailsEntity.getRefundState();
			if(null != orderDetailsEntityRefundState && !OrderStatusEnum.canPostRefundReq(orderDetailsEntityRefundState)) {
				logger.error("=========异常信息：该店铺订单状态下 不可申请退货退款");
				throw new GlobalException(PaymentOrderExceptionEnum.CALCULATION_PARAMS_ILLEGAL);
			}
		}
		// 退款订单总金额(未包含运费，已发货则不退返运费，未发货，在最后面加上运费)
		BigDecimal refundOrderTotalPrice = new BigDecimal(refundStoreOrderEntity.getProductTotal());

		// 总订单（传入参）
		long totalOrderId = refundStoreOrderEntity.getTotalOrderNo();
		TotalOrderEntity totalOrderEntity = totalOrderDao.selectById(totalOrderId);
		if(null == totalOrderEntity){
			throw new GlobalException(PaymentOrderExceptionEnum.CALCULATION_PARAMS_ILLEGAL);
		}
		// 实际支付价格
		BigDecimal payPrice = new BigDecimal(totalOrderEntity.getPayPrice() == null ? 0 : totalOrderEntity.getPayPrice());
		// 美礼卡抵扣金额
		BigDecimal payCardPrice = new BigDecimal(totalOrderEntity.getCardPrice() == null ? 0 : totalOrderEntity.getCardPrice());
		// 积分抵扣金额
		BigDecimal payIntegralPrice = new BigDecimal(totalOrderEntity.getScorePrice() == null ? 0 : totalOrderEntity.getScorePrice());

		List<StoreOrderEntity> storeOrderEntityList = storeOrderDao.selectByTotalOrderId(totalOrderEntity.getId());
		if(storeOrderEntityList.size() < 1){
			throw new GlobalException(PaymentOrderExceptionEnum.CALCULATION_PARAMS_ILLEGAL);
		}
		// 店铺+平台店铺订单总价
		BigDecimal storeOrderTotalPrice = new BigDecimal(0);
		BigDecimal storeOrderTotalFreight = new BigDecimal(0);
		for(StoreOrderEntity storeOrderEntity : storeOrderEntityList){
			storeOrderTotalPrice = storeOrderTotalPrice.add(new BigDecimal(storeOrderEntity.getProductTotal()));
			storeOrderTotalFreight = storeOrderTotalFreight.add(new BigDecimal(storeOrderEntity.getActualFreight()));
		}
		payPrice = payPrice.subtract(storeOrderTotalFreight);
		// ====NO.1:实际支付价格 除以 店铺+平台订单总价（不包含运费），获得店铺和平台订单共同使用店铺/平台优惠后，实际金额的比率,
		// 单品在店铺订单中的比率也是如此
		BigDecimal orderReturnRate = payPrice.divide(storeOrderTotalPrice, 5, BigDecimal.ROUND_HALF_DOWN);
		// ====NO.2:进行退款订单明细计算
		// 待退款总金额
		BigDecimal refundTotalPrice = new BigDecimal(0);
		// 待退款总运费
		BigDecimal refundTotalFreight = new BigDecimal(0);
		// 是否为提交订单
		List<RefundPriceResultDto.RefundGoodsInfo> refundGoodsInfoList = null;
		if(confimRefund){
			refundGoodsInfoList = new ArrayList<>();
		}
		// 计算待退款金额
		// 计算单个订单在总订单价格中的占比
		BigDecimal singleOrderRate = refundOrderTotalPrice.divide(storeOrderTotalPrice, 5, BigDecimal.ROUND_HALF_DOWN);
		if(singleOrderRate.compareTo(BigDecimal.ONE) == 1){
			logger.error("数据异常：单个订单占比超过百分之百！");
			throw new GlobalException(PaymentOrderExceptionEnum.CALCULATION_PARAMS_ILLEGAL);
		}
		// 计算单个退款订单美礼卡抵扣金额
		payCardPrice = payCardPrice.multiply(singleOrderRate);
		// 计算单个退款订单积分抵扣金额
		payIntegralPrice = payIntegralPrice.multiply(singleOrderRate);

		try{
			for(OrderDetailsEntity orderDetailsEntity : orderDetailsEntityList){
				// 单品优惠后价格
				BigDecimal singleTotalPrice = new BigDecimal(orderDetailsEntity.getTotalPrice());
				// 单品退款总价
				refundTotalPrice = refundTotalPrice.add(singleTotalPrice);
				// 判断是否发货
				if(null == orderDetailsEntity.getShipmentsTime()){
					refundTotalFreight = refundTotalFreight.add(new BigDecimal(orderDetailsEntity.getTotalFreight()));
				}
				if(confimRefund){
					RefundPriceResultDto.RefundGoodsInfo refundGoodsInfo = new RefundPriceResultDto.RefundGoodsInfo();
					BeanUtils.copyProperties(orderDetailsEntity, refundGoodsInfo);
					refundGoodsInfo.setRefundPrice(singleTotalPrice.doubleValue());
					refundGoodsInfo.setOrderDetailId(orderDetailsEntity.getId());
					refundGoodsInfoList.add(refundGoodsInfo);
				}
			}
			// 除去运费现金实际支付为0，则为美礼卡，积分等其他支付
			boolean noUseOfCash = false;
			if(orderReturnRate.compareTo(new BigDecimal(0)) == 0){
				orderReturnRate = new BigDecimal(1);
				noUseOfCash = true;
			}
			// NO.3:退款订单价格 除以 店铺加平台订单总价（不含运费），获得积分/美礼卡的比率,
			BigDecimal cardOrIntegralReturnRate = refundTotalPrice.divide(refundOrderTotalPrice, 5, BigDecimal.ROUND_HALF_DOWN);
			BigDecimal refundTotalIntegralPrice = payIntegralPrice.multiply(cardOrIntegralReturnRate).setScale(2, BigDecimal.ROUND_HALF_DOWN);
			// 待退款总积分
			int refundTotalIntegral = refundTotalIntegralPrice.multiply(new BigDecimal(100)).intValue();
			// 待退款美礼卡面值
			BigDecimal refundTotalCardPrice = payCardPrice.multiply(cardOrIntegralReturnRate).setScale(2, BigDecimal.ROUND_HALF_DOWN);
			refundTotalPrice = refundTotalPrice.multiply(orderReturnRate).setScale(2, BigDecimal.ROUND_HALF_DOWN);
			// 加上没有发货的商品运费
			refundTotalPrice = refundTotalPrice.add(refundTotalFreight);
			// 实际退款金额
			if(noUseOfCash){
				refundTotalPrice = refundTotalPrice.subtract(refundTotalIntegralPrice).subtract(refundTotalCardPrice).setScale(2, BigDecimal.ROUND_HALF_DOWN);
			}
			double refundTotalPriceDouble = refundTotalPrice.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
			if(refundPrice > 0 && refundTotalPriceDouble >= refundPrice){
				refundTotalPriceDouble = refundPrice;
			}
			RefundPriceResultDto refundPriceResultDto = new RefundPriceResultDto();
			refundPriceResultDto.setTotalOrderId(totalOrderId);
			refundPriceResultDto.setStoreOrderId(storeOrderId);
			refundPriceResultDto.setRefundCardPrice(refundTotalCardPrice.doubleValue());
			refundPriceResultDto.setRefundIntegral(refundTotalIntegral);
			refundPriceResultDto.setRefundIntegralPrice(refundTotalIntegralPrice.doubleValue());
			refundPriceResultDto.setRefundPrice(refundTotalPriceDouble);
			refundPriceResultDto.setRefundGoodsInfoList(refundGoodsInfoList);
			return refundPriceResultDto;
		} catch (Exception e){
			e.printStackTrace();
			throw new GlobalException(MobleOrderExceptionEnum.FAILURE_TO_CALCULATE_REFUND_AMOUNT);
		}
	}


	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void applyRefund(RefundPriceParamsDto refundPriceParamsDto){
		applyRefundMethod(refundPriceParamsDto, true);
	}

	private void applyRefundMethod(RefundPriceParamsDto refundPriceParamsDto, Boolean isCreat){
		TokenDto tokenDto = TokenUtil.getTokenDetail(refundPriceParamsDto.getToken());
		long currentMemberId = tokenDto.getMemberId();
		long storeId = tokenDto.getStoreId();
		Date currentDate = new Date();
		// 退款类型
		int orderState = refundPriceParamsDto.getRefundType().equals("1") ? OrderStatusEnum.APPLY_FOR_RETURN_MONEY.getCode(): OrderStatusEnum.APPLY_FOR_RETURN_GOODS.getCode();

		long storeOrderId = refundPriceParamsDto.getStoreOrderId();
		StoreOrderEntity storeOrderEntity = storeOrderDao.selectById(storeOrderId);
		if(null == storeOrderEntity){
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_IS_NOT_FOUND);
		}

		List<Long> storeOrderDetailIds = refundPriceParamsDto.getStoreOrderDetailIds();
		// 新增售后申请则先校验能否申请
		if(isCreat){
			for (Long orderDetailId : storeOrderDetailIds){
				List<RefundOrderDetailEntity> refundOrderDetailEntityList = refundOrderDetailDao.findRefundOrderByOrderDetailId(orderDetailId);
				if(null != refundOrderDetailEntityList){
					if(refundOrderDetailEntityList.size() >= REFUND_COUNT){
						logger.error("订单：" + orderDetailId + "已经超过售后次数申请限制！");
						throw new GlobalException(PaymentOrderExceptionEnum.CANT_POST_THE_REFUND_MORE_TIME);
					}
					for (RefundOrderDetailEntity refundOrderDetailEntity : refundOrderDetailEntityList){
						// 判断是否有已存在且未完成的售后申请！
						if(OrderStatusEnum.isInRefunding(refundOrderDetailEntity.getState())){
							throw new GlobalException(PaymentOrderExceptionEnum.ORDER_CANT_POST_THE_REFUND_TEMPORARY);
						}
					}
				}
			}
		}else{
			Long refundOrderId = refundPriceParamsDto.getRefundOrderId();
			RefundOrderEntity refundOrderEntity = refundOrderDao.selectById(refundOrderId);
			long refundOrderState = refundOrderEntity.getState();
			// 审核成功过的，不给予重新申请
			if(refundOrderState == OrderStatusEnum.REFUND_COMPLETION.getCode() || refundOrderState == OrderStatusEnum.RECEIVE_THE_REFUND_GOODS.getCode()){
				throw new GlobalException(PaymentOrderExceptionEnum.CANT_POST_THE_REFUND_MORE_TIME);
			}
			List<RefundOrderDetailEntity> refundOrderDetailEntityList = refundOrderDetailDao.selectOrderDetailByRefundOrderId(refundOrderId);
			if(null != refundOrderEntity){
				refundOrderEntity.setDeleteFlag((byte) 1);
				refundOrderEntity.setLastModifierId(currentMemberId);
				refundOrderEntity.setLastModifiedTime(currentDate);
				refundOrderDao.updateById(refundOrderEntity);
			}
			for (RefundOrderDetailEntity refundOrderDetailEntity : refundOrderDetailEntityList){
				refundOrderDetailEntity.setDeleteFlag((byte) 1);
				refundOrderDetailDao.updateById(refundOrderDetailEntity);
			}
		}
		// 计算退款金额明细
		RefundPriceResultDto refundPriceResultDto = calculationRefundPrice(refundPriceParamsDto, true);
		// 退货订单详情集合
		List<RefundOrderDetailEntity> refundOrderDetailList = new ArrayList<>();
		// 订单 单品退款明细
		List<RefundPriceResultDto.RefundGoodsInfo> refundOrderDetailsEntityList = refundPriceResultDto.getRefundGoodsInfoList();
		if(null == refundOrderDetailsEntityList || refundOrderDetailsEntityList.size() < 1){
			logger.error("退款订单明细集合为空！");
			throw new GlobalException(PaymentOrderExceptionEnum.CALCULATION_REFUND_PRICE_FAIL);
		}
		for(RefundPriceResultDto.RefundGoodsInfo refundGoodsInfo : refundOrderDetailsEntityList){
			RefundOrderDetailEntity refundOrderDetailEntity = new RefundOrderDetailEntity();
			BeanUtils.copyProperties(refundGoodsInfo, refundOrderDetailEntity);
			refundOrderDetailEntity.setTotalPrice(refundGoodsInfo.getRefundPrice());/** 退款金额 */
			refundOrderDetailEntity.setState(orderState);/** 状态 */
			refundOrderDetailEntity.setCreatorId(currentMemberId);/** 创建人编号 */
			refundOrderDetailEntity.setCreatedTime(currentDate);
			refundOrderDetailEntity.setStoreOrderDetailId(refundGoodsInfo.getOrderDetailId());
			refundOrderDetailList.add(refundOrderDetailEntity);
		}
		long totalOrderId = refundPriceResultDto.getTotalOrderId();
		TotalOrderEntity payTotalOrder = totalOrderDao.selectById(totalOrderId);
		TotalOrderEntity refundTotalOrderEntity = new TotalOrderEntity();
		refundTotalOrderEntity.setOrderType((short) 2);//  单据类型
		refundTotalOrderEntity.setMemberId(currentMemberId);//  会员编号
		refundTotalOrderEntity.setPlatformPromotion(payTotalOrder.getPlatformPromotion());//  所用支付级营销活动
		refundTotalOrderEntity.setPlatformDeduction(payTotalOrder.getPlatformDeduction());//  平台优惠活动金额
		// 总金额
		double refundTotalPrice = refundPriceResultDto.getRefundPrice();
		double refundTotalCardPrice = refundPriceResultDto.getRefundCardPrice();
		int refundTotalIntegral = refundPriceResultDto.getRefundIntegral();
		double refundTotalIntegralPrice = refundPriceResultDto.getRefundIntegralPrice();

		double totalPrice = MathCountUtils.add(MathCountUtils.add(refundTotalPrice, refundTotalCardPrice),refundTotalIntegralPrice);
		refundTotalOrderEntity.setTotalPrice(totalPrice);// 总金额
		refundTotalOrderEntity.setPayPrice(refundTotalPrice);// 实际支付金额
		refundTotalOrderEntity.setCardPrice(refundTotalCardPrice);// 美礼卡金额
		refundTotalOrderEntity.setUsageScore((long) refundTotalIntegral);// 使用积分
		refundTotalOrderEntity.setScorePrice(refundTotalIntegralPrice);// 积分抵扣金额
		refundTotalOrderEntity.setCreatorId(currentMemberId);// 创建人编号
		// 创建总订单
		totalOrderDao.insert(refundTotalOrderEntity);
		Long refundTotalOrderId = refundTotalOrderEntity.getId();
		RefundOrderEntity refundOrderEntity = new RefundOrderEntity();
		BeanUtils.copyProperties(refundPriceParamsDto, refundOrderEntity);
		refundOrderEntity.setStoreId(storeId);
		refundOrderEntity.setMemberId(currentMemberId);
		refundOrderEntity.setStoreOrderId(storeOrderId);
		refundOrderEntity.setTotalPrice(refundTotalPrice);
		refundOrderEntity.setPlatformOrderNo(refundTotalOrderId);
		refundOrderEntity.setState(orderState);
		refundOrderEntity.setCreatorId(currentMemberId);
		refundOrderEntity.setReturnType(refundPriceParamsDto.getRefundType());
		refundOrderDao.insert(refundOrderEntity);
		long refundOrderId = refundOrderEntity.getId();
		for (RefundOrderDetailEntity refundOrderDetailEntity : refundOrderDetailList) {
			refundOrderDetailEntity.setOrderId(refundOrderId);
			refundOrderDetailDao.insert(refundOrderDetailEntity);
		}

		// 修改订单详情售后状态值
		List<OrderDetailsEntity> orderDetailsEntityList = orderDetailsDao.getOrderDetailByIds(storeOrderDetailIds);
		for (OrderDetailsEntity orderDetailsEntity : orderDetailsEntityList){
			orderDetailsEntity.setRefundState(orderState);
			orderDetailsDao.updateById(orderDetailsEntity);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void cancelRefund(Long refundOrderId, Long memberId) {
		RefundOrderEntity refundOrderEntity = refundOrderDao.selectById(refundOrderId);
		List<RefundOrderDetailEntity> refundOrderDetailEntityList = refundOrderDetailDao.selectOrderDetailByRefundOrderId(refundOrderId);
		if(null == refundOrderEntity || null == refundOrderDetailEntityList || refundOrderDetailEntityList.size() < 1){
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_IS_NOT_FOUND);
		}
		updateStoreOrderStateInRefund(refundOrderEntity, refundOrderDetailEntityList, OrderStatusEnum.REFUND_CANCELLATION.getCode(), memberId);
	}

	@Override
	public void auditingRefundApply(Long refundOrderId, Boolean isAuditPass) {
		RefundOrderEntity refundOrderEntity = refundOrderDao.selectById(refundOrderId);
		List<RefundOrderDetailEntity> refundOrderDetailEntityList = refundOrderDetailDao.selectOrderDetailByRefundOrderId(refundOrderId);
		if (null == refundOrderEntity || null == refundOrderDetailEntityList || refundOrderDetailEntityList.size() < 1){
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_IS_NOT_FOUND);
		}
		// 1：仅退款, 0：退货退款
		int orderState = 0;
		String refundType = refundOrderEntity.getReturnType();
		if(isAuditPass){
			orderState = refundType.equals("1") ? OrderStatusEnum.REFUND_COMPLETION.getCode() : OrderStatusEnum.RETURN_THE_REFUND_GOODS.getCode();
			if(orderState == OrderStatusEnum.REFUND_COMPLETION.getCode()){
				payBack(refundOrderEntity);
			}
		}else{
			orderState = refundType.equals("1") ? OrderStatusEnum.FAILURE_OF_RETURN_MONEY_AUDIT.getCode() : OrderStatusEnum.FAILURE_OF_REFUND_GOODS_AUDIT.getCode();
		}
		updateStoreOrderStateInRefund(refundOrderEntity, refundOrderDetailEntityList, orderState, SecurityContextUtils.getCurrentSystemUser().getId());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateRefundOrder(RefundPriceParamsDto refundPriceParamsDto) {
		applyRefundMethod(refundPriceParamsDto, false);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void sendBackRefundGoods(List<Long> refundOrderDetailIds, Long refundOrderId, String logisticsType, String logisticsNo, Long currentMemberId) {
		RefundOrderEntity refundOrderEntity = refundOrderDao.selectById(refundOrderId);
		List<RefundOrderDetailEntity> refundOrderDetailEntityList = refundOrderDetailDao.findRefundOrderDetailList(refundOrderDetailIds, refundOrderId);
		if(null == refundOrderEntity || null == refundOrderDetailEntityList || refundOrderDetailEntityList.size() < 1){
			logger.error("查询不到refundOrderId:" + refundOrderId + "的下的所有售后详情订单");
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_IS_NOT_FOUND);
		}
		try{
			for (RefundOrderDetailEntity refundOrderDetailEntity : refundOrderDetailEntityList){
				int refundOrderState = refundOrderDetailEntity.getState();
				if(OrderStatusEnum.changeOrderStateFlag(refundOrderState, OrderStatusEnum.RECEIVE_THE_REFUND_GOODS.getCode())){
					refundOrderDetailEntity.setLogisticsNo(logisticsNo);
					refundOrderDetailEntity.setLogisticsType(logisticsType);
					refundOrderDetailEntity.setShipmentsTime(new Date());
					refundOrderDetailDao.updateById(refundOrderDetailEntity);
				}else{
					throw new GlobalException(MobleOrderExceptionEnum.ILLEGAL_PROCESS_MODIFY_STATE_FAILURE);
				}
			}
			updateStoreOrderStateInRefund(refundOrderEntity, refundOrderDetailEntityList, OrderStatusEnum.RECEIVE_THE_REFUND_GOODS.getCode(), currentMemberId);
		} catch (Exception e){
			e.printStackTrace();
			throw new GlobalException(MobleOrderExceptionEnum.CHANGE_ORDER_STATE_FAILURE);
		}
	}

	@Override
	public void auditingRefundGoods(Long refundOrderId, Boolean isAuditPass) {
		RefundOrderEntity refundOrderEntity = refundOrderDao.selectById(refundOrderId);
		List<RefundOrderDetailEntity> refundOrderDetailEntityList = refundOrderDetailDao.selectOrderDetailByRefundOrderId(refundOrderId);
		if (null == refundOrderEntity || null == refundOrderDetailEntityList || refundOrderDetailEntityList.size() < 1){
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_IS_NOT_FOUND);
		}
		Date taskDate = new Date();
		for (int i = 0; i < refundOrderDetailEntityList.size(); i++) {
			RefundOrderDetailEntity refundOrderDetailEntities = refundOrderDetailEntityList.get(i);
			refundOrderDetailEntities.setTakeTime(taskDate);
		}
		int orderState = isAuditPass ? OrderStatusEnum.REFUND_COMPLETION.getCode() : OrderStatusEnum.FAILURE_OF_REFUND_GOODS_AUDIT.getCode();
		updateStoreOrderStateInRefund(refundOrderEntity, refundOrderDetailEntityList, orderState, SecurityContextUtils.getCurrentSystemUser().getId());
		if(orderState == OrderStatusEnum.REFUND_COMPLETION.getCode()){
			payBack(refundOrderEntity);
		}
	}

	/**
	*
	* Description: 异步执行退款功能模块
	*
	* Author: hxpeng
	* createTime: 2017/12/3
	*
	* @param:
	**/
	@Async
	public void payBack(RefundOrderEntity refundOrderEntity){
		TotalOrderEntity refundTotalOrder = totalOrderDao.selectById(refundOrderEntity.getPlatformOrderNo());
		Long storeOrderId = refundOrderEntity.getStoreOrderId();
		StoreOrderEntity storeOrderEntity = storeOrderDao.selectById(storeOrderId);
		// 订单总订单
		Long totalOrderNo = storeOrderEntity.getTotalOrderNo();
		TotalOrderEntity totalOrderEntity = totalOrderDao.selectById(totalOrderNo);

		if (refundTotalOrder.getCardPrice() != null && refundTotalOrder.getCardPrice() != 0.0) {
			// 退还美礼卡金额
			giftCardDetailService.refundByOrder(refundOrderEntity.getId());
		}
		if (refundTotalOrder.getUsageScore() != null && refundTotalOrder.getUsageScore() != 0) {
			// 退还积分
			memberPointService.returnMemberPoint(totalOrderNo);
		}
//		String payType = totalOrderEntity.getPayType();
//		String tradeNo = "";
//		boolean refundSuccessFalg = false;
//		switch (payType){
//			// 微信支付
//			case "1":
//				WechatRefundDto wechatRefundDto = new WechatRefundDto();
//				wechatRefundDto.setTransactionId(totalOrderEntity.getPayCallbackNo());
//				wechatRefundDto.setTotalFee(totalOrderEntity.getPayPrice());
//				wechatRefundDto.setRefundFee(refundTotalOrder.getPayPrice());
//				wechatRefundDto.setRefundDesc("-------微信申请退款-------");
//				WechatResultDto wechatResultDto = ThirdPartyWeChatClient.refundOrder(wechatRefundDto);
//				if(wechatResultDto.getResultCode().equals(WecahtEnum.ORDER_REFUND_IS_SUCCESSED.getCode())){
//					refundSuccessFalg = true;
//				}
//				break;
//			// 支付宝支付
//			case "2":
//				AlipayRefundDto alipayRefundDto = new AlipayRefundDto();
//				alipayRefundDto.setTradeNo(totalOrderEntity.getPayCallbackNo());
//				alipayRefundDto.setRefundTotalOrderId(refundTotalOrder.getId().toString());
//				alipayRefundDto.setRefundAmount(refundTotalOrder.getPayPrice());
//				alipayRefundDto.setRefundReason("-------支付宝申请退款-------");
//				AlipayResultDto alipayResultDto = ThirdPartyAlipayClient.refundOrder(alipayRefundDto);
//				break;
//			// 其他支付
//			case "3":
//				break;
//		}
	}


	/**
	 * ================================================================================================
	 * ================================================================================================
	 * ================================================================================================
	 */

	/**
	 * 
	 * Description 退货订单列表
	 *
	 * Author Joe
	 * 
	 * @param page
	 * @param refundOrderDto
	 * @return
	 */
	@Override
	public Page<RefundOrderDto> getRefundOrderList(Page<RefundOrderDto> page, RefundOrderDto refundOrderDto) {
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		refundOrderDto.setStoreId(storeId);
		List<RefundOrderDto> refundOrderDtoList = refundOrderDao.getRefundOrderList(page, refundOrderDto);
		page.setRecords(refundOrderDtoList);
		return page;
	}

	/**
	 * 
	 * Description 详情/处理退货订单
	 * 
	 * Author Joe
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public RefundDto getRefundOrder(Long id) {
		Long refundGoodsNum = 0L;
		RefundDto refundDto = new RefundDto();
		RefundOrderDto refundOrderDto = new RefundOrderDto();
		RefundOrderEntity refundOrder = refundOrderDao.selectById(id);
		BeanUtils.copyProperties(refundOrder, refundOrderDto);
		List<RefundOrderDetailDto> refundOrderDetailList = refundOrderDetailDao.selectRefundOrderDetailByOrderId(id);
		/** 计算订单金额 */
		if (refundOrderDto.getStoreOrderId() != null) {
			StoreOrderEntity storeOrder = storeOrderDao.selectById(refundOrderDto.getStoreOrderId());
			if (storeOrder != null) {
				OrderAddressEntity orderAddress = orderAddressDao.getOrderAddressByOrderId(storeOrder.getId());
				if (orderAddress != null) {
					refundOrderDto.setRefundAddress(orderAddress.getAddress());
					refundOrderDto.setRefundName(orderAddress.getName());
					refundOrderDto.setRefundTelephone(orderAddress.getTelephone());
				}
				TotalOrderEntity totalOrder = totalOrderDao.selectById(storeOrder.getTotalOrderNo());
				if (null != totalOrder) {
					refundOrderDto.setOrderPrice(totalOrder.getPayPrice());
				}
				/** 计算退货数量 */
				if (refundOrderDetailList.size() != 0) {
					for (RefundOrderDetailDto refundOrderDetailDto : refundOrderDetailList) {
						OrderDetailsDto orderDetailsDto = orderDetailsDao.selectOrderDetailsByOrderIdAndSkuId(storeOrder.getId(), refundOrderDetailDto.getGoodsId());
						if (null != orderDetailsDto) {
							refundOrderDetailDto.setGoodsPrice(orderDetailsDto.getSalePrice());
						}
						refundGoodsNum += refundOrderDetailDto.getGoodsNum();
					}
				}
			}
		}
		refundOrderDto.setRefundGoodsNum(refundGoodsNum);
		refundDto.setRefundOrderDto(refundOrderDto);
		refundDto.setRefundOrderDetailList(refundOrderDetailList);
		return refundDto;
	}

	/**
	 * 
	 * Description 删除退货订单
	 * 
	 * Author Joe
	 * 
	 * @param id
	 */
	@Override
	public void removeRefundOrder(Long[] ids) {
		for (Long id : ids) {
			RefundOrderEntity refundOrder = refundOrderDao.selectById(id);
			refundOrder.setDeleteFlag((byte) 1);
			refundOrderDao.updateById(refundOrder);
		}
	}

	/**
	 *
	 * Description: 修改(售后订单ID下全部的)售后订单详情状态
	 *
	 * Author: hxpeng
	 * createTime: 2017/12/01
	 *
	 * @param:storeOrderId：店铺订单状态
	 * @param:orderState：待修改的状态
	 * @pa+ram:memberId：操作人
	 **/
	private void updateAllRefundOrderDetailState(Long refundOrderId, Integer orderState, Long currentMemberId){
		if(null != refundOrderId){
			RefundOrderEntity refundOrderEntity = refundOrderDao.selectById(refundOrderId);
			List<RefundOrderDetailEntity> refundOrderDetailEntityList = refundOrderDetailDao.selectOrderDetailByRefundOrderId(refundOrderId);
			if(null == refundOrderEntity){
				logger.error("查询不到refundOrderId:" + refundOrderId + "的售后订单");
				throw new GlobalException(MobleOrderExceptionEnum.ORDER_IS_NOT_FOUND);
			}
			if(null == refundOrderDetailEntityList || refundOrderDetailEntityList.size() < 1){
				logger.error("查询不到refundOrderId:" + refundOrderId + "的下的所有售后详情订单");
				throw new GlobalException(MobleOrderExceptionEnum.ORDER_IS_NOT_FOUND);
			}
			updateStoreOrderStateInRefund(refundOrderEntity, refundOrderDetailEntityList, orderState, currentMemberId);
		}
	}

	/**
	*
	* Description: 抽离方法---->修改售后订单，同时修改支付订单的售后状态
	*
	* Author: hxpeng
	* createTime: 2017/12/2
	*
	* @param:
	**/
	private void updateStoreOrderStateInRefund(RefundOrderEntity refundOrderEntity,List<RefundOrderDetailEntity> refundOrderDetailEntityList, int orderState, Long currentMemberId){
		Date currentDate = new Date();
		refundOrderEntity.setState(orderState);
		refundOrderEntity.setLastModifiedTime(currentDate);
		refundOrderEntity.setLastModifierId(currentMemberId);
		refundOrderDao.updateById(refundOrderEntity);
		List<Long> storeOrderDetailIds = new ArrayList<>();
		if(null != refundOrderDetailEntityList && refundOrderDetailEntityList.size() > 0){
			for(RefundOrderDetailEntity refundOrderDetailEntity : refundOrderDetailEntityList){
				storeOrderDetailIds.add(refundOrderDetailEntity.getStoreOrderDetailId());
				refundOrderDetailEntity.setState(orderState);
				refundOrderDetailDao.updateById(refundOrderDetailEntity);
			}
		}
		// 退款失败之后还可以申请退货，售后取消也可以再申请
		List<OrderDetailsEntity> orderDetailsEntityList = orderDetailsDao.selectOrderDetailByOrderId(refundOrderEntity.getStoreOrderId());
		// 是否所有商品都在售后状态中
		boolean isAllRefund = true;
		for (OrderDetailsEntity orderDetailsEntity : orderDetailsEntityList){
			if(storeOrderDetailIds.contains(orderDetailsEntity.getId())){
				if(orderState == OrderStatusEnum.REFUND_CANCELLATION.getCode()){
					orderDetailsEntity.setRefundState(null);
				}else{
					orderDetailsEntity.setRefundState(orderState);
				}
			}
			if(isAllRefund){
				if(null == orderDetailsEntity.getRefundState()){
					isAllRefund = false;
				}
			}
			orderDetailsDao.updateAllColumnById(orderDetailsEntity);
		}
		// 退款完成，更新订单状态（交易关闭）
		if(isAllRefund && orderState == OrderStatusEnum.REFUND_COMPLETION.getCode()){
			StoreOrderEntity storeOrderEntity = storeOrderDao.selectById(refundOrderEntity.getStoreOrderId());
			if(null != storeOrderEntity){
				storeOrderEntity.setOrderState(OrderStatusEnum.ORDER_CLOSE.getCode());
				storeOrderEntity.setLastModifierId(currentMemberId);
				storeOrderEntity.setLastModifiedTime(currentDate);
				storeOrderDao.updateById(storeOrderEntity);
			}
		}


	}
}
