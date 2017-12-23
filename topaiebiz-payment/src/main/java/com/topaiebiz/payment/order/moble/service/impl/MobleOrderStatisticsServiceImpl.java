/**
 * 
 */
package com.topaiebiz.payment.order.moble.service.impl;

import OrderStatisticsDto.OrderStatisticsChartDto;
import com.nebulapaas.util.common.math.MathCountUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.member.mgmt.dao.MemberMgmtDao;
import com.topaiebiz.payment.order.moble.dao.MobleStoreOrderDao;
import com.topaiebiz.payment.order.moble.dao.MobleStoreOrderDetailDao;
import com.topaiebiz.payment.order.moble.dto.OrderStatisticsDto;
import com.topaiebiz.payment.order.moble.entity.StoreOrderDetailEntity;
import com.topaiebiz.payment.order.moble.exception.MobleOrderExceptionEnum;
import com.topaiebiz.payment.order.moble.service.MobleOrderStatisticsService;
import com.topaiebiz.system.common.OrderStatusEnum;
import com.topaiebiz.transaction.order.merchant.entity.StoreOrderEntity;
import com.topaiebiz.transaction.order.total.dao.TotalOrderDao;
import com.topaiebiz.transaction.order.total.dto.NewOldCustomerOrderRangeDto;
import com.topaiebiz.transaction.order.total.dto.TotalOrderParamDto;
import com.topaiebiz.transaction.order.total.dto.TotalOrderPriceRangeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Description： 订单统计报表接口实现类
 * 
 * 
 * Author hxpeng
 * 
 * Date 2017年10月29日 下午4:01:45
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@Service
public class MobleOrderStatisticsServiceImpl implements MobleOrderStatisticsService {

	Logger logger = LoggerFactory.getLogger(MobleOrderStatisticsServiceImpl.class);

	@Autowired
	private TotalOrderDao totalOrderDao;
	
	@Autowired
	private MobleStoreOrderDao mobleStoreOrderDao;

	@Autowired
	private MobleStoreOrderDetailDao mobleStoreOrderDetailDao;

	@Autowired
	private MemberMgmtDao memberMgmtDao;

	@Override
	public OrderStatisticsDto orderStatistics(TotalOrderParamDto totalOrderParamDto) throws GlobalException,Exception {
		long startTime = System.currentTimeMillis();
		// 获取查询时间段范围(根据时间段/天数，创建一个天数长度的对象数组)
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		OrderStatisticsChartDto[] orderStatisticsChartArray = null;
		int arraySize = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		if(totalOrderParamDto.getDays() > 0){
			arraySize = totalOrderParamDto.getDays();
			calendar.add(Calendar.DATE, -arraySize);
		}else{
			Date beforDate = simpleDateFormat.parse(totalOrderParamDto.getBeforDate());
			Date afterDate = simpleDateFormat.parse(totalOrderParamDto.getAfterDate());
			if(afterDate.before(beforDate)){
				throw new GlobalException(MobleOrderExceptionEnum.ORDER_STATISTICS_PARAMETER_ILLEGAL);
			}
			calendar.setTime(beforDate);
			arraySize = new Long((afterDate.getTime()-beforDate.getTime())/(1000*3600*24)).intValue();
			if(arraySize > 30){
				throw new GlobalException(MobleOrderExceptionEnum.CHOOSE_TIME_CANT_EXCEED_30_DAYS);
			}
		}
		
		// 实例化对象数组中的对象，并叠加插入日期值
		orderStatisticsChartArray = new OrderStatisticsChartDto[arraySize];
		for(int i = 0; i < arraySize; i++){
			orderStatisticsChartArray[i] = new OrderStatisticsChartDto();
			orderStatisticsChartArray[i].setCurrentDate(calendar.getTime());
			calendar.add(Calendar.DATE, 1);
		}
		
		// 开始查询迭代订单集合
		OrderStatisticsDto orderStatisticsDto = new OrderStatisticsDto();
		try {
			List<StoreOrderEntity> storeOrderList = mobleStoreOrderDao.selectStoreOrderByStatistics(totalOrderParamDto);
			if (null != storeOrderList && storeOrderList.size() > 0) {
				// 总订单数
				int numberOfTotalOrder = storeOrderList.size();
				// 下单人数集合(用于获取下单人数，去重复)
				List<String> numberOfSubmitOrderCustomer = new ArrayList<String>();
				// 总订单商品总件数
				int numberOfOrderProduct = 0;
				// 总订单金额
				double totalOrderSumPrice = 0;
				
				// 付款人数集合(用于获取下单人数，去重复)
				List<Long> numberOfPaymentCustomer = new ArrayList<Long>();
				// 付款总订单数
				int numberOfPaymentOrder = 0;
				// 付款订单总商品件数
				int numberOfPaymentProduct = 0;
				// 付款总金额
				double totalPaymentSumPrice = 0;
				
				// 退款总金额
				double totalRefundSumPrice = 0.0;

				// 开始统计=========
				for (StoreOrderEntity storeOrderEntity : storeOrderList) {
					long orderId = storeOrderEntity.getId();
					// 单个订单总价
					double oneOfOrderSumPrice = 0;
					// 单个订单的商品件数
					int oneOfOrderProductCount = 0;
					
					// 单个订单已完成付款的商品数
					int oneOfPaymentOrderProductCount = 0;
					// 单个订单已完成付款的总金额
					double oneOfPaymentOrderSumPrice = 0.0;
					
					// 单个订单完成退款的总金额
					double oneOfRefundSumPrice = 0.0;
					
					// 是否有退款
//					boolean hasRefundFalg = false;
					// 是否有付款
					boolean hasPaymentFalg = false;
					
					// 订单价格还是以详情表中为准
					List<StoreOrderDetailEntity> storeOrderDetailList = mobleStoreOrderDetailDao.findMobleStoreOrderDetailByOrderId(orderId);
					for(StoreOrderDetailEntity storeOrderDetailEntity : storeOrderDetailList){
						int storeOrderDetailGoodsNum = storeOrderDetailEntity.getGoodsNum().intValue();
						double storeOrderDetailTotalPrice = storeOrderDetailEntity.getTotalPrice();
						oneOfOrderSumPrice = MathCountUtils.add(oneOfOrderSumPrice, storeOrderDetailTotalPrice);
						oneOfOrderProductCount += storeOrderDetailGoodsNum;
						// 已付款
						if(storeOrderDetailEntity.getState().equals(OrderStatusEnum.PENDING_DELIVERY.getCode())){
							numberOfPaymentOrder++;
							oneOfPaymentOrderProductCount += storeOrderDetailGoodsNum;
							oneOfPaymentOrderSumPrice = MathCountUtils.add(oneOfPaymentOrderSumPrice, storeOrderDetailTotalPrice);
							hasPaymentFalg = true;
						}
						// 退款
						if(storeOrderDetailEntity.getState().equals(OrderStatusEnum.REFUND_COMPLETION.getCode())){
							oneOfRefundSumPrice = MathCountUtils.add(oneOfRefundSumPrice, storeOrderDetailTotalPrice);
//							hasRefundFalg = true;
						}
					}
					
					String memberId = storeOrderEntity.getMemberId();
					if(!numberOfSubmitOrderCustomer.contains(memberId)){
						numberOfSubmitOrderCustomer.add(memberId);
					}
					
					// 迭代出与订单日期相同的实体类，操作后break
					for(int i = 0; i < orderStatisticsChartArray.length; i++){
						OrderStatisticsChartDto orderStatisticsChartDto = orderStatisticsChartArray[i];
						LocalDate localDate1 = ZonedDateTime.ofInstant(orderStatisticsChartDto.getCurrentDate().toInstant(), ZoneId.systemDefault()).toLocalDate();  
					    LocalDate localDate2 = ZonedDateTime.ofInstant(storeOrderEntity.getOrderTime().toInstant(), ZoneId.systemDefault()).toLocalDate();  
					    if(localDate1.isEqual(localDate2)){
					    	List<String> memberIds = orderStatisticsChartDto.getMemberIds();
					    	if(null == memberIds){
					    		memberIds = new ArrayList<String>();
					    	}
					    	if(!memberIds.contains(memberId)){
					    		memberIds.add(memberId);
								orderStatisticsChartDto.setOrderPeopleNumber(orderStatisticsChartDto.getOrderPeopleNumber()+1);
								if(hasPaymentFalg){
									orderStatisticsChartDto.setPayPeopleNumber(orderStatisticsChartDto.getPayPeopleNumber() + 1);
								}
					    	}
					    	orderStatisticsChartDto.setMemberIds(memberIds);
							orderStatisticsChartDto.setOrderProductNumber(orderStatisticsChartDto.getOrderProductNumber() + oneOfOrderProductCount);
							orderStatisticsChartDto.setOrderNumber(orderStatisticsChartDto.getOrderNumber()+1);
							orderStatisticsChartDto.setOrderTotalPrice(oneOfOrderSumPrice);
							orderStatisticsChartDto.setOrderRefundPrice(oneOfRefundSumPrice);
							if(hasPaymentFalg){
								orderStatisticsChartDto.setPayOrderNumber(orderStatisticsChartDto.getPayOrderNumber() + 1);
								orderStatisticsChartDto.setAlreadyPayOrderNumber(orderStatisticsChartDto.getAlreadyPayOrderNumber() + oneOfOrderProductCount);
								orderStatisticsChartDto.setPayTotalPrice(MathCountUtils.add(orderStatisticsChartDto.getPayTotalPrice(), oneOfOrderSumPrice));
							}
							// 当天下的订单数
							int orederNumber = orderStatisticsChartDto.getOrderNumber();
							// 当天的付款订单数
							int payOrderNumber = orderStatisticsChartDto.getPayOrderNumber();
							if (orederNumber == 0) {
								orderStatisticsDto.setToPayTransformationRate("0%");
							} else {
								NumberFormat numberFormat = NumberFormat.getInstance();
								numberFormat.setMaximumFractionDigits(2);
								String transformationRate = numberFormat.format((float) payOrderNumber / (float) orederNumber * 100);
								orderStatisticsChartDto.setToPayTransformationRate(transformationRate + "%");
							}
							orderStatisticsChartDto.setToDealTransformationRate("0%");
							orderStatisticsChartDto.setToOrderTransformationRate("0%");
							break;
						}
					}
					
					totalOrderSumPrice = MathCountUtils.add(totalOrderSumPrice, oneOfOrderSumPrice) ;
					numberOfOrderProduct += oneOfOrderProductCount;
					numberOfPaymentProduct += oneOfPaymentOrderProductCount;
					totalPaymentSumPrice = MathCountUtils.add(totalPaymentSumPrice, oneOfPaymentOrderSumPrice);
					totalRefundSumPrice = MathCountUtils.add(totalRefundSumPrice, oneOfRefundSumPrice);
					
					orderStatisticsDto.setBeforDays(totalOrderParamDto.getDays());
					orderStatisticsDto.setBeforDate(totalOrderParamDto.getBeforDate());
					orderStatisticsDto.setAfterDate(totalOrderParamDto.getAfterDate());
					orderStatisticsDto.setOrderPeopleNumber(numberOfSubmitOrderCustomer.size());
					orderStatisticsDto.setOrderNumber(numberOfTotalOrder);
					orderStatisticsDto.setOrderProductNumber(numberOfOrderProduct);
					orderStatisticsDto.setOrderTotalPrice(totalOrderSumPrice);
					orderStatisticsDto.setOrderRefundPrice(totalRefundSumPrice);
					orderStatisticsDto.setPayPeopleNumber(numberOfPaymentCustomer.size());
					orderStatisticsDto.setPayOrderNumber(numberOfPaymentOrder);
					orderStatisticsDto.setAlreadyPayOrderNumber(numberOfPaymentProduct);
					orderStatisticsDto.setPayTotalPrice(totalPaymentSumPrice);
					orderStatisticsDto.setOrderStatisticsChartList(orderStatisticsChartArray);

					if (numberOfTotalOrder == 0) {
						orderStatisticsDto.setToPayTransformationRate("0%");
					} else {
						NumberFormat numberFormat = NumberFormat.getInstance();
						numberFormat.setMaximumFractionDigits(2);
						String transformationRate = numberFormat.format((float) numberOfPaymentOrder / (float) numberOfTotalOrder * 100);
						orderStatisticsDto.setToPayTransformationRate(transformationRate + "%");
					}
					orderStatisticsDto.setToDealTransformationRate("0%");
					orderStatisticsDto.setToOrderTransformationRate("0%");
				}
			}
			long endTime = System.currentTimeMillis();
			logger.info("报表方法执行时间：" + (endTime - startTime));
		} catch (Exception e) {
			e.printStackTrace();
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_STATISTICS_FAILURE);
		}
		return orderStatisticsDto;
	}

	@Override
	public List<TotalOrderPriceRangeDto> getTotalOrderRange(TotalOrderParamDto totalOrderParamDto) {
		return totalOrderDao.getTotalPriceRange(totalOrderParamDto);
	}

	@Override
	public NewOldCustomerOrderRangeDto newOldCustomerCompare(TotalOrderParamDto totalOrderParamDto) throws GlobalException {
		String beforDateStr = totalOrderParamDto.getBeforDate();
		String afterDateStr = totalOrderParamDto.getAfterDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date beforDate = null;
		Date afterDate = null;
		try {
			beforDate = sdf.parse(beforDateStr);
			afterDate = sdf.parse(afterDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_STATISTICS_PARAMETER_ILLEGAL);
		}
		if(beforDate.after(afterDate)){
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_STATISTICS_PARAMETER_ILLEGAL);
		}
		long arraySize = new Long((afterDate.getTime()-beforDate.getTime())/(1000*3600*24)).intValue();
		if(arraySize > 30){
			throw new GlobalException(MobleOrderExceptionEnum.CHOOSE_TIME_CANT_EXCEED_30_DAYS);
		}

		NewOldCustomerOrderRangeDto currentOrderRangeDto = null;
		try {
			currentOrderRangeDto = newOldCustomerDataStatistics(totalOrderParamDto);
			// 将时间调整为上一个月
			Calendar beforCalendar = Calendar.getInstance();
			beforCalendar.setTime(beforDate);
			beforCalendar.add(Calendar.MONTH, -1);
			totalOrderParamDto.setBeforDate(sdf.format(beforCalendar.getTime()));

			Calendar afterCalendar = Calendar.getInstance();
			afterCalendar.setTime(afterDate);
			afterCalendar.add(Calendar.MONTH, -1);
			totalOrderParamDto.setAfterDate(sdf.format(afterCalendar.getTime()));
			
			NewOldCustomerOrderRangeDto lastOrderRangeDto = newOldCustomerDataStatistics(totalOrderParamDto);
			// 计算转率
			NumberFormat numberFormat = NumberFormat.getInstance();
			numberFormat.setMaximumFractionDigits(2);

			// 新客户付款金额总额比上次---比率
			double currentNewCustomerTotalPrice = currentOrderRangeDto.getNewCustomerTotalPrice();
			double lastNewCustomerTotalPrice = lastOrderRangeDto.getNewCustomerTotalPrice();
			if (currentNewCustomerTotalPrice == lastNewCustomerTotalPrice) {
				currentOrderRangeDto.setNewCustomerPayCompareLastTime("0%");
			}
			if(lastNewCustomerTotalPrice == 0){
				if(currentNewCustomerTotalPrice > 0){
					currentOrderRangeDto.setNewCustomerPayCompareLastTime("100%");
				}
			}else{
				String newCustomerPayCompareLastTime = numberFormat
						.format((float) currentNewCustomerTotalPrice / (float) lastNewCustomerTotalPrice * 100);
				if (currentNewCustomerTotalPrice < lastNewCustomerTotalPrice) {
					currentOrderRangeDto.setNewCustomerPayCompareLastTime("-" + newCustomerPayCompareLastTime + "%");
				} else {
					currentOrderRangeDto.setNewCustomerPayCompareLastTime(newCustomerPayCompareLastTime + "%");
				}
			}
			// 老客户付款金额总额比上次---比率
			double currentOldCustomerTotalPrice = currentOrderRangeDto.getOldCustomerTotalPrice();
			double lastOldCustomerTotalPrice = lastOrderRangeDto.getOldCustomerTotalPrice();
			if (currentOldCustomerTotalPrice == lastOldCustomerTotalPrice) {
				currentOrderRangeDto.setOldCustomerPayCompareLastTime("0%");
			}
			if(lastOldCustomerTotalPrice == 0){
				if(currentOldCustomerTotalPrice > 0){
					currentOrderRangeDto.setOldCustomerPayCompareLastTime("100%");
				}
			}else{
				String oldCustomerPayCompareLastTime = numberFormat
						.format((float) currentOldCustomerTotalPrice / (float) lastOldCustomerTotalPrice * 100);
				if (currentOldCustomerTotalPrice < lastOldCustomerTotalPrice) {
					currentOrderRangeDto.setOldCustomerPayCompareLastTime("-" + oldCustomerPayCompareLastTime + "%");
				} else {
					currentOrderRangeDto.setOldCustomerPayCompareLastTime(oldCustomerPayCompareLastTime + "%");
				}
			}

			// 新客户数量比上次---比率
			int currentNewCustomerNumber = currentOrderRangeDto.getNewCustomerNumber();
			int lastNewCustomerNumber = lastOrderRangeDto.getNewCustomerNumber();
			if (currentNewCustomerNumber == lastNewCustomerNumber) {
				currentOrderRangeDto.setNewCustomerNumberCompareLastTime("0%");
			}
			if(lastNewCustomerNumber == 0){
				if(currentNewCustomerNumber > 0){
					currentOrderRangeDto.setNewCustomerNumberCompareLastTime("100%");
				}
			}else{
				String newCustomerNumberCompareLastTime = numberFormat
						.format((float) currentNewCustomerNumber / (float) lastNewCustomerNumber * 100);
				if (currentNewCustomerNumber < lastNewCustomerNumber) {
					currentOrderRangeDto.setNewCustomerNumberCompareLastTime("-" + newCustomerNumberCompareLastTime + "%");
				} else {
					currentOrderRangeDto.setNewCustomerNumberCompareLastTime(newCustomerNumberCompareLastTime + "%");
				}
			}
			
			// 老客户人数比上次---比率
			double currentOldCustomerNumber = currentOrderRangeDto.getOldCustomerNumber();
			double lastOldCustomerNumber = lastOrderRangeDto.getOldCustomerNumber();
			if (currentOldCustomerNumber == lastOldCustomerNumber) {
				currentOrderRangeDto.setOldCustomerNumberCompareLastTime("0%");
			}
			if(lastOldCustomerNumber == 0){
				if(currentOldCustomerNumber > 0){
					currentOrderRangeDto.setOldCustomerNumberCompareLastTime("100%");
				}
			}else{
				String oldCustomerNumberCompareLastTime = numberFormat
						.format((float) currentOldCustomerNumber / (float) lastOldCustomerNumber * 100);
				if (currentOldCustomerNumber < lastOldCustomerNumber) {
					currentOrderRangeDto.setOldCustomerNumberCompareLastTime("-" + oldCustomerNumberCompareLastTime + "%");
				} else {
					currentOrderRangeDto.setOldCustomerNumberCompareLastTime(oldCustomerNumberCompareLastTime + "%");
				}
			}

			// 新客户付款人数---比率
			double currentNumberOfNewCustomerPayment = currentOrderRangeDto.getNumberOfNewCustomerPayment();
			double lastNumberOfNewCustomerPayment = lastOrderRangeDto.getNumberOfNewCustomerPayment();
			if (currentNumberOfNewCustomerPayment == lastNumberOfNewCustomerPayment) {
				currentOrderRangeDto.setNumberOfNewCustomerPaymentCompareLastTime("0%");
			}
			if(lastNumberOfNewCustomerPayment == 0){
				if(currentNumberOfNewCustomerPayment > 0){
					currentOrderRangeDto.setNumberOfNewCustomerPaymentCompareLastTime("100%");
				}
			}else{
				String numberOfNewCustomerPaymentLastTime = numberFormat
						.format((float) currentNumberOfNewCustomerPayment / (float) lastNumberOfNewCustomerPayment * 100);
				if (currentNumberOfNewCustomerPayment < lastNumberOfNewCustomerPayment) {
					currentOrderRangeDto.setNumberOfNewCustomerPaymentCompareLastTime("-" + numberOfNewCustomerPaymentLastTime + "%");
				} else {
					currentOrderRangeDto.setNumberOfNewCustomerPaymentCompareLastTime(numberOfNewCustomerPaymentLastTime + "%");
				}
			}

			// 老客户付款人数---比率
			double currentNumberOfOldCustomerPayment = currentOrderRangeDto.getNumberOfOldCustomerPayment();
			double lastNumberOfOldCustomerPayment = lastOrderRangeDto.getNumberOfOldCustomerPayment();
			if (currentNumberOfOldCustomerPayment == lastNumberOfOldCustomerPayment) {
				currentOrderRangeDto.setNumberOfOldCustomerPaymentCompareLastTime("0%");
			}
			if(lastNumberOfOldCustomerPayment == 0){
				if(currentNumberOfOldCustomerPayment > 0){
					currentOrderRangeDto.setNumberOfOldCustomerPaymentCompareLastTime("100%");
				}
			}else{
				String numberOfOldCustomerPaymentCompareLastTime = numberFormat
						.format((float) currentNumberOfOldCustomerPayment / (float) lastNumberOfOldCustomerPayment * 100);
				if (currentNumberOfOldCustomerPayment < lastNumberOfOldCustomerPayment) {
					currentOrderRangeDto.setNumberOfOldCustomerPaymentCompareLastTime("-" + numberOfOldCustomerPaymentCompareLastTime + "%");
				} else {
					currentOrderRangeDto.setNumberOfOldCustomerPaymentCompareLastTime(numberOfOldCustomerPaymentCompareLastTime + "%");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_STATISTICS_FAILURE);
		}
		
		return currentOrderRangeDto;
	}

	public NewOldCustomerOrderRangeDto newOldCustomerDataStatistics(TotalOrderParamDto totalOrderParamDto) {
		NewOldCustomerOrderRangeDto newOldCustomerOrderRangeDto = new NewOldCustomerOrderRangeDto();
		// 新注册的会员的数量
		int newCustomerNumber = 0;
		// 新注册的会员付款总额
		double newCustomerTotalPrice = 0;
		// 老客户数量
		int oldCustomerNumber = 0;
		// 老客户付款金额总额
		double oldCustomerTotalPrice = 0;
		// 新会员付款人数
		int numberOfNewCustomerPayment = 0;
		// 老会员付款人数
		int numberOfOldCustomerPayment = 0;
		
		// 时间范围内新注册的会员
		List<Long> newRegisterMemberList = memberMgmtDao.getNewRegisterByTimeFrame(totalOrderParamDto.getBeforDate(),totalOrderParamDto.getAfterDate());
		// 时间范围内所有订单
		List<StoreOrderEntity> storeOrderList = mobleStoreOrderDao.selectStoreOrderByStatistics(totalOrderParamDto);
		for(StoreOrderEntity storeOrderEntity : storeOrderList){
			Long memberId = Long.valueOf(storeOrderEntity.getMemberId());
			if (newRegisterMemberList.contains(memberId)) {
				newCustomerNumber++;
				newCustomerTotalPrice = MathCountUtils.add(newCustomerTotalPrice, storeOrderEntity.getProductTotal());
				if(storeOrderEntity.getOrderState().equals(OrderStatusEnum.PENDING_RECEIVED.getCode())){
					numberOfNewCustomerPayment++;
				}
			} else {
				oldCustomerNumber++;
				oldCustomerTotalPrice = MathCountUtils.add(oldCustomerTotalPrice, storeOrderEntity.getProductTotal());
				if(storeOrderEntity.getOrderState().equals(OrderStatusEnum.PENDING_RECEIVED.getCode())){
					numberOfOldCustomerPayment++;
				}
			}
		}
		newOldCustomerOrderRangeDto.setNewCustomerNumber(newCustomerNumber);
		newOldCustomerOrderRangeDto.setNewCustomerTotalPrice(newCustomerTotalPrice);
		newOldCustomerOrderRangeDto.setOldCustomerNumber(oldCustomerNumber);
		newOldCustomerOrderRangeDto.setOldCustomerTotalPrice(oldCustomerTotalPrice);
		newOldCustomerOrderRangeDto.setNumberOfNewCustomerPayment(numberOfNewCustomerPayment);
		newOldCustomerOrderRangeDto.setNumberOfOldCustomerPayment(numberOfOldCustomerPayment);
		return newOldCustomerOrderRangeDto;
	}
 
	
}
