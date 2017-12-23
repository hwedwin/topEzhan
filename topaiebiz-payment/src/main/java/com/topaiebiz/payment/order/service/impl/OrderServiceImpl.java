package com.topaiebiz.payment.order.service.impl;

import com.nebulapaas.util.common.math.MathCountUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.goods.sku.dao.GoodsSkuDao;
import com.topaiebiz.goods.sku.dto.GoodsSkuDto;
import com.topaiebiz.goods.sku.entity.GoodsSkuEntity;
import com.topaiebiz.goods.sku.service.ItemService;
import com.topaiebiz.merchant.enter.service.MerchantEnterService;
import com.topaiebiz.payment.order.dto.*;
import com.topaiebiz.payment.order.exception.PaymentOrderExceptionEnum;
import com.topaiebiz.payment.order.service.OrderService;
import com.topaiebiz.promotion.mgmt.dao.PromotionGoodsDao;
import com.topaiebiz.promotion.mgmt.dto.PromotionGoodsDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionPriceDto;
import com.topaiebiz.promotion.mgmt.service.PromotionGoodsService;
import com.topaiebiz.system.security.util.SecurityContextUtils;
import com.topaiebiz.transaction.order.merchant.dao.OrderAddressDao;
import com.topaiebiz.transaction.order.merchant.dao.OrderDetailsDao;
import com.topaiebiz.transaction.order.merchant.dao.OrderInvoiceDao;
import com.topaiebiz.transaction.order.merchant.dao.StoreOrderDao;
import com.topaiebiz.transaction.order.merchant.dto.OrderDetailsDto;
import com.topaiebiz.transaction.order.merchant.entity.OrderAddressEntity;
import com.topaiebiz.transaction.order.merchant.entity.OrderDetailsEntity;
import com.topaiebiz.transaction.order.merchant.entity.OrderInvoiceEntity;
import com.topaiebiz.transaction.order.merchant.entity.StoreOrderEntity;
import com.topaiebiz.transaction.order.total.dao.TotalOrderDao;
import com.topaiebiz.transaction.order.total.entity.TotalOrderEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Description 订单支付业务层实现类 
 * 
 * Author Aaron.Xue 
 *    
 * Date 2017年10月14日 下午7:19:50 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	//模拟积分转换比例。 1积分等于多少钱
//	private Double pointRatio = 0.01; 
	
	@Autowired
	/**支付订单dao。*/
	private TotalOrderDao totalOrderDao;
	
	@Autowired
	/**店铺订单dao。*/
	private StoreOrderDao storeOrderDao;
	
	@Autowired
	/**店铺收货地址dao。*/
	private OrderAddressDao orderAddressDao;
	
	@Autowired
	/**订单发票dao。*/
	private OrderInvoiceDao orderInvoiceDao;
	
	@Autowired
	/**订单详情dao。*/
	private OrderDetailsDao orderDetailsDao;
	
	@Autowired
	/**商品SKUdao。*/
	private GoodsSkuDao goodsSkuDao;
	
	@Autowired
	/**营销活动商品dao。*/
	private PromotionGoodsDao promotionGoodsDao;
	
	@Autowired
	/**营销活动商品service。*/
	private PromotionGoodsService promotionGoodsService;
	
	@Autowired
	/**运费模版service。*/
	private MerchantEnterService merchantEnterService;
	
	@Autowired
	/**商品service。*/
	private ItemService itemService;
	
	
	
	
	
	/**
	 * 提交订单，去支付按钮
	 * @throws GlobalException 
	 *  
	 */
	@Override
	public TotalOrderDto submitOrder(OrderDto orderDto) throws GlobalException {
		//校验订单
		this.verifyOrder(orderDto);
		
		//创建人
//		Long creatorId = SecurityContextUtils.getCurrentSystemUser().getId();
		
		//扣除美礼卡并记录
		
		//扣除积分并记录
		
		//总订单（支付订单）
		TotalOrderDto totalOrderDto = orderDto.getTotalOrderDto();
		TotalOrderEntity totalOrder = new TotalOrderEntity();
		BeanUtils.copyProperties(totalOrderDto, totalOrder);
		totalOrderDao.insert(totalOrder);  //总订单
		
		//收货地址
		OrderAddressDto orderAddressDto = orderDto.getOrderAddressDto();
		//发票地址
		OrderInvoiceDto orderInvoiceDto = orderDto.getOrderInvoiceDto();
		
		//商家订单表集合
		List<StoreOrderDto> storeOrderList = orderDto.getStoreOrderList();
		for (StoreOrderDto storeOrderDto : storeOrderList) {
			StoreOrderEntity storeOrder = new StoreOrderEntity();
			BeanUtils.copyProperties(storeOrderDto, storeOrder);
			storeOrder.setTotalOrderNo(totalOrder.getId());
			storeOrderDao.insert(storeOrder);  //店铺
			
			OrderAddressEntity orderAddress = new OrderAddressEntity();
			BeanUtils.copyProperties(orderAddressDto, orderAddress);
			orderAddress.setOrderId(storeOrder.getId());
			orderAddressDao.insert(orderAddress); //地址
			
			if(storeOrderDto.getInvoiceState() == 1) { //开票
				OrderInvoiceEntity orderInvoice = new OrderInvoiceEntity();
				BeanUtils.copyProperties(orderInvoiceDto, orderInvoice);
				orderInvoice.setOrderId(storeOrder.getId());
				orderInvoiceDao.insert(orderInvoice); //发票
			}
			
			List<OrderDetailsDto> detailList = storeOrderDto.getDetailList();
			for (OrderDetailsDto orderDetailsDto : detailList) {
				OrderDetailsEntity orderDetails = new OrderDetailsEntity();
				BeanUtils.copyProperties(orderDetailsDto, orderDetails);
				orderDetails.setOrderId(storeOrder.getId());
				orderDetailsDao.insert(orderDetails); //详情
			}
		}
		
		return totalOrderDto;
	}
	
	/**
	 * 取消订单
	 * @throws GlobalException 
	 */
	@Override
	public Boolean cancelOrder(Long id) throws GlobalException {
		//查询总订单
		TotalOrderEntity totalOrder = totalOrderDao.selectById(id);
		//不是支付成功
		if(totalOrder.getPayState() != 1) {
			throw new GlobalException(PaymentOrderExceptionEnum.ORDER_CANNOT_BE_CANCELLED);
		}
		//根据总订单查询店铺订单
		List<StoreOrderEntity> StoreOrderList = storeOrderDao.selectByTotalOrderId(id);
		
		//根据店铺订单查询商品详情
		for (StoreOrderEntity storeOrderEntity : StoreOrderList) {
			List<OrderDetailsEntity> OrderDetailsList = orderDetailsDao.selectlistByOrderId(storeOrderEntity.getId());
			for (OrderDetailsEntity orderDetailsEntity : OrderDetailsList) {
				//将状态该为0 已取消
				orderDetailsDao.updateOrderDetailsStatus(0, orderDetailsEntity.getId());
				
				//加库存
				itemService.addGoodsSkuStockNumber(SecurityContextUtils.getCurrentSystemUser().getId(),orderDetailsEntity.getGoodsId(), orderDetailsEntity.getGoodsNum());
//				itemService.addGoodsSkuStockNumber(orderDetailsEntity.getGoodsId(), orderDetailsEntity.getGoodsNum());
//				itemService.addGoodsSkuStockNumber(orderDetailsEntity.getGoodsId(), orderDetailsEntity.getGoodsNum());
			}
			storeOrderDao.updateStoreOrderStatus(0, storeOrderEntity.getId());
		}
		totalOrderDao.updateTotalOrderStatus(3, id);
		
		//将美礼卡扣除的加回去，积分加回去，库存加回去
		// TODO Auto-generated method stub
		
		return true;
	}

	@Override
	public AdminOrderDataDto getTodayOrderSituation() {

		//下单人数
//		Integer orderPeopleNumber = 0;

		//订单总金额
		Double orderTotalPrice = 0.0;

		// 订单数量
		Integer orderNumber = 0;

		//待付款订单数量
		Integer waitToPayOrderNumber = 0;

		//待发货订单数量
		Integer waitToDeliverOrderNumber = 0;

		//待收货订单数量
		Integer waitToReceiptOrderNumber = 0;

		//退款订单数
		Integer refundOrderNumber = 0;

		//等待处理退款订单数
		Integer waitToDispose = 0;

		//已处理退款订单数
		Integer alreadyProcessed = 0;

		//已拒绝退款订单数
		Integer alreadyRejected = 0;

		// 下单人数
		List<String> memberIds = new ArrayList<>();

		List<StoreOrderEntity> storeOrderEntities = storeOrderDao.getTodayOrderList();
		orderNumber = storeOrderEntities.size();
		for(StoreOrderEntity storeOrderEntity : storeOrderEntities){
			// 下单会员ID
			String memberId = storeOrderEntity.getMemberId();
			if(!memberIds.contains(memberId)){
				memberIds.add(memberId);
			}
			// 订单总价
			orderTotalPrice = MathCountUtils.add(orderTotalPrice, storeOrderEntity.getLastPrice());
			// 订单状态
			int orderState = storeOrderEntity.getOrderState();
			switch (orderState) {
				// 待付款
				case 1:
					waitToPayOrderNumber ++;
					break;
				// 待发货
				case 2:
					waitToDeliverOrderNumber ++;
					break;
				// 待收货（已发货）
				case 3:
					waitToReceiptOrderNumber ++;
					break;
				// 待收货（延长发货）
				case 4:
					waitToReceiptOrderNumber ++;
					break;
				// 申请退款
				case 7:
					refundOrderNumber ++;
					waitToDispose ++;
					break;
				// 仅退款成功
				case 8:
					refundOrderNumber ++;
					alreadyProcessed ++;
					break;
				// 申请退货退款
				case 9:
					refundOrderNumber ++;
					waitToDispose ++;
					break;
				// 退货审批失败
				case 10:
					refundOrderNumber ++;
					alreadyProcessed ++;
					alreadyRejected ++;
					break;
				// 待寄回商品
				case 11:
					refundOrderNumber ++;
					alreadyProcessed ++;
					break;
				// 寄回已发货
				case 12:
					refundOrderNumber ++;
					alreadyProcessed ++;
					break;
				// 退款已完成
				case 13:
					refundOrderNumber ++;
					alreadyProcessed ++;
					break;
				default:
					// nothing to do
					break;
			}
		}
		AdminOrderDataDto adminOrderDataDto = new AdminOrderDataDto();
		adminOrderDataDto.setOrderPeopleNumber(memberIds.size());
		adminOrderDataDto.setOrderTotalPrice(orderTotalPrice);
		adminOrderDataDto.setOrderNumber(orderNumber);
		adminOrderDataDto.setWaitToPayOrderNumber(waitToPayOrderNumber);
		adminOrderDataDto.setWaitToDeliverOrderNumber(waitToDeliverOrderNumber);
		adminOrderDataDto.setWaitToReceiptOrderNumber(waitToReceiptOrderNumber);
		adminOrderDataDto.setRefundOrderNumber(refundOrderNumber);
		adminOrderDataDto.setWaitToDispose(waitToDispose);
		adminOrderDataDto.setAlreadyProcessed(alreadyProcessed);
		adminOrderDataDto.setAlreadyRejected(alreadyRejected);
		return adminOrderDataDto;
	}

	/**
	 * 支付订单
	 */
	@Override
	public Boolean payOrder(TotalOrderDto totalOrderDto) {
		// TODO Auto-generated method stub
		//调用支付API
		
		//调用支付记录
		
		//支付成功改变订单状态
		
		
		//成功返回true
		return true;
	}
	
	/**
	 * Description 校验所购买的商品库存是否够，是否为上架状态,并且扣除库存
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param orderDetailsDto
	 * @throws GlobalException 
	 */
	private void goodsIsCanBuy(OrderDetailsDto orderDetailsDto) throws GlobalException {
		GoodsSkuDto goodsSkuDto = goodsSkuDao.selectGoodsSkuDtoBySkuId(orderDetailsDto.getGoodsId());
		//是否为上架
		if(!goodsSkuDto.getStatus().equals(2)) {
			//购买商品不存在
			throw new GlobalException(PaymentOrderExceptionEnum.PURCHASED_GOODS_DO_NOT_EXIST);
		}
		//库存是否购
		if(orderDetailsDto.getGoodsNum() > goodsSkuDto.getStockNumber()) {
			throw new GlobalException(PaymentOrderExceptionEnum.INSUFFICIENT_STOCK_OF_GOODS);
		}
		Integer modifyGoodsSkuStockNumber = itemService.modifyGoodsSkuStockNumber(orderDetailsDto.getGoodsId(), orderDetailsDto.getGoodsNum());
		if(modifyGoodsSkuStockNumber == 0) {
			//购买商品不存在
			throw new GlobalException(PaymentOrderExceptionEnum.INSUFFICIENT_STOCK_OF_GOODS);
		}
	}
	
	/**
	 * Description 校验商品详情是否有错误
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param detailList
	 * @return
	 * @throws GlobalException
	 */
	private List<OrderDetailsDto> verifyOrderDetails(List<OrderDetailsDto> detailList) throws GlobalException {
		for (OrderDetailsDto orderDetailsDto : detailList) {
			//校验商品是否可以购买（状态，库存）
			this.goodsIsCanBuy(orderDetailsDto);
			//校验商品的单品活动是否存在
			if(orderDetailsDto.getUsedPromotion() != null) {
				PromotionGoodsDto promotionGoods = promotionGoodsDao.getPromotionGoods(orderDetailsDto.getUsedPromotion(), orderDetailsDto.getGoodsId());
				//购买数量大于限购数量
				if(orderDetailsDto.getGoodsNum() > promotionGoods.getConfineNum()) {
					throw new GlobalException(PaymentOrderExceptionEnum.INSUFFICIENT_STOCK_OF_GOODS);
				}
				//优惠金额
				orderDetailsDto.setDeduction(promotionGoods.getPreferentialAmount() * orderDetailsDto.getGoodsNum());
				//优惠后金额
				orderDetailsDto.setSalePrice(promotionGoods.getPromotionPrice() * orderDetailsDto.getGoodsNum());
			}
			GoodsSkuEntity goodsSku = goodsSkuDao.selectById(orderDetailsDto.getGoodsId());
			orderDetailsDto.setUnitPrice(goodsSku.getPrice());
			//原单价
			Double unitPrice = orderDetailsDto.getUnitPrice();
			//商品数量，购买数
			Long goodsNum = orderDetailsDto.getGoodsNum();
			//设置原总价
			orderDetailsDto.setTotalPrice(MathCountUtils.multiply(unitPrice, Double.parseDouble(String.valueOf(goodsNum))));
		}
		return detailList;
	}
	
	/**
	 * Description 校验商家订单
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param detailList
	 * @return
	 * @throws GlobalException
	 */
	private StoreOrderDto verifyStoreOrder(StoreOrderDto storeOrderDto, OrderAddressDto orderAddressDto) throws GlobalException {
		//所属店铺
		Long storeId = storeOrderDto.getStoreId();
		//区域ID
		Long districtId = orderAddressDto.getDistrictId();
		//订单详情
		List<OrderDetailsDto> detailList = storeOrderDto.getDetailList();
		
		List<GoodsSkuDto> goodsSkuList = new ArrayList<GoodsSkuDto>();
		//计算运费
		Double totalFreight = 0.0;
		for (OrderDetailsDto orderDetailsDto : detailList) {
			//商品id
			Long goodsId = orderDetailsDto.getGoodsId();
			//商品数量
			Long goodsNum = orderDetailsDto.getGoodsNum();
			//调用运费模版计算运费
			Double freightPrice = merchantEnterService.getGoodsNumberPrice(storeId, goodsId, districtId, Integer.valueOf(goodsNum.toString()));
			orderDetailsDto.setTotalFreight(freightPrice);
			totalFreight = MathCountUtils.add(totalFreight, freightPrice);
			//查询商品
			GoodsSkuDto goodSkuDto = goodsSkuDao.selectGoodsSkuDtoBySkuId(goodsId);
			goodsSkuList.add(goodSkuDto);
		}
		//店铺订单总运费（营销活动是否包邮）
		PromotionPriceDto platformPromotionGoods = promotionGoodsService.getPlatformPromotionGoods(goodsSkuList, storeOrderDto.getUsedPromotion(), null, null);
		if(platformPromotionGoods.isFreeShipping()) { //包邮
			totalFreight = 0.0; 
		}
		storeOrderDto.setTotalFreight(totalFreight); //运费
		platformPromotionGoods = promotionGoodsService.getPlatformPromotionGoods(goodsSkuList, storeOrderDto.getUsedPromotion(), null, null);
		storeOrderDto.setStoreDeduction(platformPromotionGoods.getPreferentialAmount()); //优惠金额
		storeOrderDto.setLastPrice(platformPromotionGoods.getPresentPrice());  //现价
		return storeOrderDto;
	}
	
	/**
	 * Description 校验支付订单
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param totalOrderDto
	 * @param storeOrderList
	 * @param goodsSkuList 
	 * @return
	 */
	private TotalOrderDto verifytotalOrder(TotalOrderDto totalOrderDto, List<StoreOrderDto> storeOrderList, List<GoodsSkuDto> goodsSkuList) {
		Double totalPrice = 0.0;
		for (StoreOrderDto storeOrderDto : storeOrderList) {
			totalPrice = MathCountUtils.add(totalPrice, storeOrderDto.getLastPrice());
		}
		totalOrderDto.setTotalPrice(totalPrice);
		PromotionPriceDto platformPromotionGoods = promotionGoodsService.getPlatformPromotionGoods(goodsSkuList, totalOrderDto.getPlatformPromotion(), null, null);
		totalOrderDto.setPlatformDeduction(platformPromotionGoods.getPreferentialAmount()); //优惠金额
		return totalOrderDto;
	}
	
	/**
	 * Description 校验总订单
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @param orderDto
	 * @return
	 * @throws GlobalException
	 */
	private OrderDto verifyOrder(OrderDto orderDto) throws GlobalException {
		TotalOrderDto totalOrderDto = orderDto.getTotalOrderDto();
		OrderAddressDto orderAddressDto = orderDto.getOrderAddressDto();
		List<StoreOrderDto> storeOrderList = orderDto.getStoreOrderList();
		//购买的所有商品
		List<GoodsSkuDto> goodsSkuList = new ArrayList<GoodsSkuDto>();
		for (StoreOrderDto storeOrderDto : storeOrderList) {
			List<OrderDetailsDto> detailList = storeOrderDto.getDetailList();
			for (OrderDetailsDto orderDetailsDto : detailList) {
				Long goodsId = orderDetailsDto.getGoodsId();
				GoodsSkuDto goodSkuDto = goodsSkuDao.selectGoodsSkuDtoBySkuId(goodsId);
				goodsSkuList.add(goodSkuDto);
			}
		}
		//校验商品详情
		for (StoreOrderDto storeOrderDto : storeOrderList) {
			List<OrderDetailsDto> detailList = storeOrderDto.getDetailList();
			this.verifyOrderDetails(detailList);  //详情校验并计算
			this.verifyStoreOrder(storeOrderDto, orderAddressDto); //店铺订单校验
			this.verifytotalOrder(totalOrderDto, storeOrderList, goodsSkuList); //总订单校验
		}
		return orderDto;
	}
	
}
