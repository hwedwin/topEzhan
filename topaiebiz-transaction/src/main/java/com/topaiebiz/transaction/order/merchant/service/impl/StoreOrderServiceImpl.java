package com.topaiebiz.transaction.order.merchant.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.goods.sku.dao.GoodsSkuDao;
import com.topaiebiz.goods.sku.entity.GoodsSkuEntity;
import com.topaiebiz.goods.sku.exception.GoodsSkuExceptionEnum;
import com.topaiebiz.goods.sku.service.ItemService;
import com.topaiebiz.member.mgmt.dao.MemberMgmtDao;
import com.topaiebiz.merchant.enter.dao.StoreInfoDao;
import com.topaiebiz.merchant.enter.dto.StoreInfoDto;
import com.topaiebiz.merchant.info.dao.MerchantInfoDao;
import com.topaiebiz.merchant.info.entity.MerchantInfoEntity;
import com.topaiebiz.system.common.OrderStatusEnum;
import com.topaiebiz.system.district.dao.DistrictDao;
import com.topaiebiz.system.district.entity.DistrictEntity;
import com.topaiebiz.transaction.order.merchant.dao.OrderAddressDao;
import com.topaiebiz.transaction.order.merchant.dao.OrderDetailsDao;
import com.topaiebiz.transaction.order.merchant.dao.OrderInvoiceDao;
import com.topaiebiz.transaction.order.merchant.dao.StoreOrderDao;
import com.topaiebiz.transaction.order.merchant.dto.*;
import com.topaiebiz.transaction.order.merchant.entity.OrderAddressEntity;
import com.topaiebiz.transaction.order.merchant.entity.OrderDetailsEntity;
import com.topaiebiz.transaction.order.merchant.entity.OrderInvoiceEntity;
import com.topaiebiz.transaction.order.merchant.entity.StoreOrderEntity;
import com.topaiebiz.transaction.order.merchant.exception.StoreOrderExceptionEnum;
import com.topaiebiz.transaction.order.merchant.service.StoreOrderService;
import com.topaiebiz.transaction.order.total.dao.TotalOrderDao;
import com.topaiebiz.transaction.order.total.entity.TotalOrderEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 *
 * Description 订单的业务逻辑实现
 *
 *
 * Author：zhushuyong
 *
 * Date 2017年8月31日 下午7:51:25
 *
 * Copyright：Cognieon technology group co.LTD. All rights reserved.
 *
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
@Transactional
public class StoreOrderServiceImpl implements StoreOrderService {

	private final static Logger logger = LoggerFactory.getLogger(StoreOrderServiceImpl.class);

	@Autowired
	private StoreOrderDao storeOrderDao;

	@Autowired
	private OrderDetailsDao orderDetailsDao;

	@Autowired
	private ItemService itemService;

	@Autowired
	private MemberMgmtDao memberMgmtDao;

	@Autowired
	private DistrictDao districtDao;

	@Autowired
	private OrderInvoiceDao orderInvoiceDao;

	@Autowired
	private OrderAddressDao orderAddressDao;

	@Autowired
	private StoreInfoDao storeInfoDao;

	@Autowired
	private TotalOrderDao totalOrderDao;

	@Autowired
	private MerchantInfoDao merchantInfoDao;

	@Autowired
	private GoodsSkuDao goodsSkuDao;

	@Override
	public Page<PCOrderListResultDto> getOrderListInPC(Page<PCOrderListResultDto> page, OrderListParamsDto orderListParamsDto) {
		List<PCOrderListResultDto> pcOrderListResultDtos = storeOrderDao.getOrderListInPc(page, orderListParamsDto);

		// 是否平台中获取商家订单列表
		Integer orderType = orderListParamsDto.getOrderType();
		boolean isBusinessOrderListInPlatform = orderType != null && orderType.equals(2) ;

		// 订单详情列表
		for (PCOrderListResultDto pcOrderListResultDto : pcOrderListResultDtos){
			List<OrderDetailsDto> orderDetailListResultDtos = new ArrayList<>();
			List<OrderDetailsEntity> orderDetailsEntityList = orderDetailsDao.selectOrderDetailByOrderId(pcOrderListResultDto.getOrderId());
			for(OrderDetailsEntity orderDetailsEntity : orderDetailsEntityList){
				OrderDetailsDto orderDetailDto = new OrderDetailsDto();
				BeanUtils.copyProperties(orderDetailsEntity, orderDetailDto);
				orderDetailListResultDtos.add(orderDetailDto);
			}
			pcOrderListResultDto.setOrderDetailsDtoList(orderDetailListResultDtos);

			// 获取商家名称
			if(isBusinessOrderListInPlatform){
				Long merchantId = pcOrderListResultDto.getMerchantId();
				if(null != merchantId){
					MerchantInfoEntity merchantInfoEntity = merchantInfoDao.selectById(merchantId);
					if(null != merchantInfoEntity){
						pcOrderListResultDto.setMerchantName(merchantInfoEntity.getName());
					}
				}
			}
		}
		page.setRecords(pcOrderListResultDtos);
		return page;
	}

	@Override
	public OrderDetailResultDto getOrderDetailInPC(long storeOrderId) {
		StoreOrderEntity storeOrderEntity = storeOrderDao.selectById(storeOrderId);
		if(null == storeOrderEntity){
			throw new GlobalException(StoreOrderExceptionEnum.ORDER_NOT_FOUND);
		}
		OrderDetailResultDto orderDetailResultDto = new OrderDetailResultDto();
		BeanUtils.copyProperties(storeOrderEntity, orderDetailResultDto);
		orderDetailResultDto.setId(storeOrderEntity.getId());
		// 查询发票
		Integer orderInvoiceState = storeOrderEntity.getInvoiceState();
		if(null != orderInvoiceState && orderInvoiceState == 1){
			OrderInvoiceEntity orderInvoiceEntity = orderInvoiceDao.selectOrderInvoice(storeOrderId);
			if(null != orderInvoiceEntity){
				orderDetailResultDto.setOrderInvoiceEntity(orderInvoiceEntity);
			}else{
				logger.error("订单号：" + storeOrderId + ",未查询到发票信息！");
			}
		}
		// 查询收货地址
		OrderAddressEntity orderAddressEntity = orderAddressDao.getOrderAddressByOrderId(storeOrderId);
		if(null != orderAddressEntity){
			orderDetailResultDto.setOrderAddressDto(getOrderAddressDto(orderAddressEntity));
		}else{
			logger.error("订单号：" + storeOrderId + ",未查询到收货地址信息！");
		}
		// 店铺名称
		Long storeId = storeOrderEntity.getStoreId();
		StoreInfoDto storeInfoDto = storeInfoDao.selectStoreInfoById(storeId);
		orderDetailResultDto.setStoreName(storeInfoDto == null ? "" : storeInfoDto.getName());
		// 第三方交易单号
		Long totalOrderId = storeOrderEntity.getTotalOrderNo();
		TotalOrderEntity totalOrderEntity = totalOrderDao.selectById(totalOrderId);
		if(totalOrderEntity == null){
			logger.error("订单号：" + storeOrderId + "的总订单编号查询不到总订单！");
//			throw new GlobalException(StoreOrderExceptionEnum.ORDER_NOT_FOUND);
		}else{
			orderDetailResultDto.setPayCallBackNo(totalOrderEntity.getPayCallbackNo());
			// 平台优惠
			orderDetailResultDto.setPlatformDeduction(totalOrderEntity.getPlatformDeduction());
		}

		List<OrderDetailsEntity> orderDetailsEntityList = orderDetailsDao.selectOrderDetailByOrderId(storeOrderId);
		List<OrderDetailsDto> orderDetailListResultDtos = new ArrayList<>();
		for(OrderDetailsEntity orderDetailsEntity : orderDetailsEntityList){
			OrderDetailsDto orderDetailDto = new OrderDetailsDto();
			BeanUtils.copyProperties(orderDetailsEntity, orderDetailDto);
			orderDetailListResultDtos.add(orderDetailDto);
		}
		orderDetailResultDto.setOrderDetailListResultDtos(orderDetailListResultDtos);
		return orderDetailResultDto;
	}

	/**
	*
	* Description: 获取用户地址DTO
	*
	* Author: hxpeng
	* createTime: 2017/12/12
	*
	* @param:
	**/
	private OrderAddressDto getOrderAddressDto(OrderAddressEntity orderAddressEntity){
		OrderAddressDto orderAddressDto = new OrderAddressDto();
		BeanUtils.copyProperties(orderAddressEntity, orderAddressDto);
		DistrictEntity districtEntity = districtDao.selectById(orderAddressEntity.getDistrictId());
		orderAddressDto.setDistrictName(districtEntity.getSerialName());
		return orderAddressDto;
	}

	@Override
	public List<APPOrderListResultDto> getOrderListInAPP(List<Integer> orderStateArray, Long memberId) {
		List<APPOrderListResultDto> appOrderListDtoList = storeOrderDao.getOrderListInAPP(memberId, orderStateArray);
		for (APPOrderListResultDto appOrderListResultDto : appOrderListDtoList){
			Long goodsNum = 0L;
			List<OrderDetailsDto> orderDetailsDtoList = new ArrayList<>();
			long storeOrderId = appOrderListResultDto.getOrderId();
			List<OrderDetailsEntity> orderDetailsEntityList = storeOrderDao.selectByOrderId(storeOrderId);
			for(OrderDetailsEntity orderDetailsEntity : orderDetailsEntityList){
				OrderDetailsDto orderDetailsDto = new OrderDetailsDto();
				BeanUtils.copyProperties(orderDetailsEntity, orderDetailsDto);
				orderDetailsDto.setId(orderDetailsEntity.getId());
				goodsNum = goodsNum + orderDetailsEntity.getGoodsNum();
				orderDetailsDtoList.add(orderDetailsDto);
			}
			// 订单分摊优惠，实际支付金额
			appOrderListResultDto.setLastPrice(this.getStoreOrderPayPrice(appOrderListResultDto.getTotalOrderNo(), new BigDecimal(appOrderListResultDto.getLastPrice() == null ? 0 : appOrderListResultDto.getLastPrice())));
			appOrderListResultDto.setGoodsNum(goodsNum.intValue());
			appOrderListResultDto.setOrderDetailsDtoList(orderDetailsDtoList);
		}
		return appOrderListDtoList;
	}

	/**
	*
	* Description: 获取订单实付款
	*
	* Author: hxpeng
	* createTime: 2017/12/15
	*
	* @param:
	**/
	private Double getStoreOrderPayPrice(Long totalOrderId, BigDecimal storeOrderLastPrice){
		// 订单分摊优惠，实际支付金额
		try{
			TotalOrderEntity totalOrderEntity = totalOrderDao.selectById(totalOrderId);
			if(null != totalOrderEntity){
				BigDecimal totalPirce = new BigDecimal(totalOrderEntity.getTotalPrice() == null ? 0 : totalOrderEntity.getTotalPrice());
				BigDecimal cardPrice = new BigDecimal(totalOrderEntity.getCardPrice() == null ? 0 : totalOrderEntity.getCardPrice());
				BigDecimal integralPrice = new BigDecimal(totalOrderEntity.getScorePrice() == null ? 0 : totalOrderEntity.getScorePrice());
				BigDecimal rate = storeOrderLastPrice.divide(totalPirce).setScale(5, BigDecimal.ROUND_HALF_UP);
				return storeOrderLastPrice.subtract(integralPrice.multiply(rate)).subtract(cardPrice.multiply(rate)).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
			}
		}catch (Exception e){
			logger.error("订单列表：计算订单分摊优惠金额失败！");
			e.printStackTrace();
		}
		return storeOrderLastPrice.doubleValue();
	}

	@Override
	public StoreOrderDto getStoreOrderInfo(Long storeOrderId) {
		StoreOrderEntity storeOrderEntity = storeOrderDao.selectById(storeOrderId);
		List<OrderDetailsEntity> orderDetailsEntityList = orderDetailsDao.selectOrderDetailByOrderId(storeOrderId);
		if(null == storeOrderEntity || null == orderDetailsEntityList || orderDetailsEntityList.size() < 1){
			throw new GlobalException(StoreOrderExceptionEnum.ORDER_NOT_FOUND);
		}
		StoreOrderDto storeOrderDto = new StoreOrderDto();
		BeanUtils.copyProperties(storeOrderEntity, storeOrderDto);
		storeOrderDto.setId(storeOrderEntity.getId());
		// 填充发票信息
		OrderInvoiceEntity orderInvoiceEntity = orderInvoiceDao.selectOrderInvoice(storeOrderId);
		if(null != orderInvoiceEntity){
			OrderInvoiceDto orderInvoiceDto = new OrderInvoiceDto();
			BeanUtils.copyProperties(orderInvoiceEntity, orderInvoiceDto);
			storeOrderDto.setOrderInvoiceDto(orderInvoiceDto);
		}

		// 收货地址
		OrderAddressEntity orderAddressEntity = orderAddressDao.getOrderAddressByOrderId(storeOrderId);
		if(null != orderAddressEntity){
			storeOrderDto.setOrderAddressDto(getOrderAddressDto(orderAddressEntity));
		}

		// 判断是否显示售后按钮
		Integer refundState = storeOrderEntity.getRefundState();
		Date refundCheckTime = storeOrderEntity.getRefundCheckTime();
		boolean showRefundBtn = true;
		if(null != refundState && (refundState == OrderStatusEnum.FAILURE_OF_REFUND_GOODS_AUDIT.getCode() || refundState == OrderStatusEnum.FAILURE_OF_REFUND_GOODS_AUDIT.getCode())){
			showRefundBtn = false;
		}
		Date earliestTaskTime = null;
		List<OrderDetailsDto> orderDetailsDtoList = new ArrayList<>();
		for (OrderDetailsEntity orderDetailsEntity : orderDetailsEntityList){
			OrderDetailsDto orderDetailsDto = new OrderDetailsDto();
			BeanUtils.copyProperties(orderDetailsEntity, orderDetailsDto);
			orderDetailsDto.setId(orderDetailsEntity.getId());
			GoodsSkuEntity goodsSkuEntity = goodsSkuDao.selectById(orderDetailsEntity.getGoodsId());
			if(null == goodsSkuEntity){
				throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_EXIST);
			}
			orderDetailsDto.setItemId(goodsSkuEntity.getItemId());
			orderDetailsDtoList.add(orderDetailsDto);
			if(null != earliestTaskTime){
				if(null != orderDetailsEntity.getTakeTime() && orderDetailsEntity.getTakeTime().before(earliestTaskTime)){
					earliestTaskTime = orderDetailsEntity.getTakeTime();
				}
			}else{
				earliestTaskTime = orderDetailsEntity.getTakeTime();
			}
		}
		// 最早的收货时间，与收货审核时间比较，判断是否再收货之后进行过售后申请，是则不允许再继续申请
		if(null != refundCheckTime && null != earliestTaskTime){
			if(!refundCheckTime.before(earliestTaskTime)){
				showRefundBtn = false;
			}
		}
		storeOrderDto.setLastPrice(this.getStoreOrderPayPrice(storeOrderEntity.getTotalOrderNo(), new BigDecimal(storeOrderEntity.getLastPrice() == null ? 0 : storeOrderEntity.getLastPrice())));
		storeOrderDto.setShowRefundBtn(showRefundBtn);
		storeOrderDto.setDetailList(orderDetailsDtoList);
		return storeOrderDto;
	}

	@Override
	public OrderDetailsDto getOrderInsideDetailInfo(Long storeOrder, Long goodsId) {
		OrderDetailsEntity orderDetailsEntity = orderDetailsDao.findByOrderIdAndGoodsId(storeOrder, goodsId);
		if(null == orderDetailsEntity){
			logger.error("根据店铺订单ID:" + storeOrder + "和商品SKUID:" + goodsId + "，未查询到订单详情信息!");
			throw new GlobalException(StoreOrderExceptionEnum.ORDER_NOT_FOUND);
		}
		OrderDetailsDto orderDetailsDto = new OrderDetailsDto();
		BeanUtils.copyProperties(orderDetailsEntity, orderDetailsDto);
		orderDetailsDto.setId(orderDetailsEntity.getId());
		return orderDetailsDto;
	}


	@Override
	public void deleteOrder(Long storeOrderId) {
		StoreOrderEntity storeOrderEntity = storeOrderDao.selectById(storeOrderId);
		List<OrderDetailsEntity> orderDetailsEntityList = orderDetailsDao.selectOrderDetailByOrderId(storeOrderId);
		if(null == storeOrderEntity || null == orderDetailsEntityList || orderDetailsEntityList.size() < 1){
			throw new GlobalException(StoreOrderExceptionEnum.ORDER_NOT_FOUND);
		}


	}


	/*
	============================================================================================================
	============================================================================================================
	============================================================================================================
	============================================================================================================
	============================================================================================================
	============================================================================================================
	============================================================================================================
	============================================================================================================
	============================================================================================================
	============================================================================================================
	 */



	@Override
	public OrderListDto findById(Long id) throws GlobalException {
		OrderListDto orderListDto = storeOrderDao.selectByIdDto(id);
		if (orderListDto == null) {
			throw new GlobalException(StoreOrderExceptionEnum.STOREORDER_ID_NOT_EXIST);
		}
		return orderListDto;
	}

	@Override
	public Page<OrderListDto> findByPageAdminList(Page<OrderListDto> page, OrderListDto orderListDto) {
		/** 获取订单信息 */
		List<OrderListDto> orderListDtoList = storeOrderDao.selectByOrderListDtoList(page, orderListDto);

		for (OrderListDto orderDto : orderListDtoList) {
			List<OrderDetailsEntity> selectByOrderId = orderDetailsDao.selectOrderDetailByOrderId(orderDto.getId());
			/** 查询此店铺所属的商家名称 */
			StoreDto storeDto = storeOrderDao.selectStoreByStoreId(orderDto.getStoreId());
			if (storeDto != null) {
				/** 根据商家ID查询商家信息 */
				StoreDto storedto = storeOrderDao.selectMerchantByMerchantId(storeDto.getMerchantId());
				if (storedto != null) {
					orderDto.setMerchantName(storedto.getMerchantName());
				}
			}
			/** 实际物流费用。 */
			Double actualFreight = orderDto.getActualFreight();
			if (actualFreight == null) {
				actualFreight = 0.0;
			}
			orderDto.setOrderDetail(selectByOrderId);
			/** 支付方式默认为在线支付 */
			orderDto.setPaymentWay("在线支付");
		}

		page.setRecords(orderListDtoList);
		return page;
	}

	@Override
	public void modifyAddressInvoice(OrderAddressInvoiceDto orderAddressInvoiceDto) throws GlobalException {
		StoreOrderEntity storeOrderEntity = storeOrderDao.selectById(orderAddressInvoiceDto.getOrderId());
		if (storeOrderEntity == null) {
			throw new GlobalException(StoreOrderExceptionEnum.STOREORDER_ID_NOT_EXIST);
		}
		storeOrderDao.updateAddressInvoice(orderAddressInvoiceDto);
	}

	@Override
	public void removeDeletedFlag(Long[] ids) throws GlobalException {
		for (Long id : ids) {
			StoreOrderEntity storeOrderEntity = storeOrderDao.selectById(id);
			if (storeOrderEntity == null) {
				throw new GlobalException(StoreOrderExceptionEnum.STOREORDER_ID_NOT_EXIST);
			}
		}
		storeOrderDao.updateDeletedFlag(ids);
	}

	@Override
	public OrderAddressEntity getOrderAddressByOrderId(Long orderId) throws GlobalException {
		StoreOrderEntity orderListDto = storeOrderDao.selectById(orderId);
		if (orderListDto == null) {
			throw new GlobalException(StoreOrderExceptionEnum.STOREORDER_ID_NOT_EXIST);
		}
		OrderAddressEntity orderAddress = storeOrderDao.selectAddressByOrderId(orderId);
		/** 根据区的ID查询省名》市名》区名 */
		DistrictEntity districtEntity = districtDao.selectById(orderAddress.getDistrictId());
		/** 存入市的编号 */
		orderAddress.setCityId(districtEntity.getParentDistrictId());
		DistrictEntity selectById = districtDao.selectById(districtEntity.getParentDistrictId());
		/** 存入省的编号 */
		orderAddress.setProvinceId(selectById.getParentDistrictId());
		return orderAddress;
	}

	@Override
	public OrderAddressEntity getDelivery(Long orderId) throws GlobalException {
		/** 判断订单编号是否存在 */
		OrderListDto orderListDto = storeOrderDao.selectByIdDto(orderId);
		if (orderListDto == null) {
			throw new GlobalException(StoreOrderExceptionEnum.STOREORDER_ID_NOT_EXIST);
		}
		OrderAddressEntity orderAddressEntity = storeOrderDao.selectDeliveryByOrderId(orderId);
		return orderAddressEntity;
	}

	@Override
	public void removeOrderByorderId(Long orderId, Long memberId) throws GlobalException {
		/** 判断此订单是否存在 */
		StoreOrderEntity orderEntity = storeOrderDao.selectById(orderId);
		if (orderEntity == null) {
			throw new GlobalException(StoreOrderExceptionEnum.STOREORDER_ID_NOT_EXIST);
		}
		/** 判断该订单是否已完成或已取消 */
		Integer orderState = orderEntity.getOrderState();
		if(!OrderStatusEnum.changeOrderStateFlag(orderState, OrderStatusEnum.DELETE_ORDER.getCode())){
			throw new GlobalException(StoreOrderExceptionEnum.THE_CARDORDER_IS_NOT_FINISH);
		}
		/** 删除此订单 */
		storeOrderDao.deleteStoreOrderByOrderId(orderId);
	}

	@Override
	public void changeOrderFinish() {
		/** 查看七天前所有已收货的订单 */
		List<StoreOrderEntity> list = storeOrderDao.selectReceivedOrder();
		for (StoreOrderEntity storeOrder : list) {
			storeOrder.setOrderState(OrderStatusEnum.ORDER_COMPLETION.getCode());
			storeOrderDao.updateById(storeOrder);
		}
	}

	@Override
	public Page<OrderListDto> findStoreOrderList(Page<OrderListDto> page, OrderListDto orderListDto) {
		/** 获取订单信息 */
		List<OrderListDto> orderListDtoList = storeOrderDao.selecttoreOrderList(page, orderListDto);

		for (OrderListDto orderDto : orderListDtoList) {
			List<OrderDetailsEntity> selectByOrderId = orderDetailsDao.selectOrderDetailByOrderId(orderDto.getId());
			/** 查询此店铺所属的商家名称 */
			StoreDto storeDto = storeOrderDao.selectStoreByStoreId(orderDto.getStoreId());
			if (storeDto != null) {
				/** 根据商家ID查询商家信息 */
				StoreDto storedto = storeOrderDao.selectMerchantByMerchantId(storeDto.getMerchantId());
				if (storedto != null) {
					orderDto.setMerchantName(storedto.getMerchantName());
				}
			}
			/** 实际物流费用。 */
			Double actualFreight = orderDto.getActualFreight();
			if (actualFreight == null) {
				actualFreight = 0.0;
			}
			orderDto.setOrderDetail(selectByOrderId);
			/** 支付方式默认为在线支付 */
			orderDto.setPaymentWay("在线支付");
		}

		page.setRecords(orderListDtoList);
		return page;
	}

	@Override
	public Map<String, Object> getOrderCountByOrderState(Long storeId) {
		HashMap<String, Object> map = new HashMap<>();
		/**查询所有订单的数量*/
		Integer numAll=storeOrderDao.getOrderAllCount(storeId);
		/**查询代付款的订单的数量*/
		Integer numObligation=storeOrderDao.getOrderObligationCount(storeId);
		/**查询代发货的订单的数量*/
		Integer numShipment=storeOrderDao.getOrderShipmentCount(storeId);
		/**查询待收货的订单的数量*/
		Integer numReceived=storeOrderDao.getOrderReceivedCount(storeId);
		map.put("numAll",numAll);
		map.put("numObligation",numObligation);
		map.put("numShipment",numShipment);
		map.put("numReceived",numReceived);
		return map;
	}

}
