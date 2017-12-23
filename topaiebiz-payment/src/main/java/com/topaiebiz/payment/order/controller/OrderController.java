package com.topaiebiz.payment.order.controller;

import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.payment.order.moble.dto.OrderParamsDto;
import com.topaiebiz.payment.order.moble.exception.MobleOrderExceptionEnum;
import com.topaiebiz.payment.order.moble.service.MobleOrderService;
import com.topaiebiz.payment.order.moble.service.MobleOrderStatisticsService;
import com.topaiebiz.payment.order.service.OrderService;
import com.topaiebiz.transaction.order.merchant.exception.StoreOrderExceptionEnum;
import com.topaiebiz.transaction.order.total.dto.NewOldCustomerOrderRangeDto;
import com.topaiebiz.transaction.order.total.dto.TotalOrderParamDto;
import com.topaiebiz.transaction.order.total.dto.TotalOrderPriceRangeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Description 支付订单处理器 
 * 
 * Author Aaron.Xue 
 *    
 * Date 2017年10月14日 下午5:19:10 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@RestController
@RequestMapping("/payment/order")
public class OrderController{
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private MobleOrderStatisticsService mobleOrderStatisticsService;

	@Autowired
	private MobleOrderService mobleOrderService;
	
	/**
	 *
	 * Description： 交易分析请求接口
	 *
	 * Author hxpeng
	 *
	 * @param totalOrderParamDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(path = "/statistics", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo statistics(TotalOrderParamDto totalOrderParamDto) throws Exception {
		if (totalOrderParamDto == null || totalOrderParamDto.getOrderStroeType() == 0) {
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_STATISTICS_PARAMETER_ILLEGAL);
		}
		int days = totalOrderParamDto.getDays();
		if ((days == 0) && (null == totalOrderParamDto.getBeforDate() || null == totalOrderParamDto.getAfterDate())) {
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_STATISTICS_PARAMETER_ILLEGAL);
		}
		return new ResponseInfo(mobleOrderStatisticsService.orderStatistics(totalOrderParamDto));
	}

	/**
	 *
	 * Description： 订单价格区间统计
	 *
	 * Author hxpeng
	 *
	 * @param totalOrderParamDto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/totalOrderRange", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo totalOrderRange(TotalOrderParamDto totalOrderParamDto) throws GlobalException {
		if (totalOrderParamDto == null || totalOrderParamDto.getOrderStroeType() == 0) {
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_STATISTICS_PARAMETER_ILLEGAL);
		}
		if (totalOrderParamDto.getDays() == 0
				&& (null == totalOrderParamDto.getBeforDate() || null == totalOrderParamDto.getAfterDate())) {
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_STATISTICS_PARAMETER_ILLEGAL);
		}
		List<TotalOrderPriceRangeDto> totalOrderPriceRange = new ArrayList<>();
		try {
			totalOrderPriceRange = mobleOrderStatisticsService.getTotalOrderRange(totalOrderParamDto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_STATISTICS_FAILURE);
		}
		return new ResponseInfo(totalOrderPriceRange);
	}

	/**
	 *
	 * Description： 新旧客户 时间段 交易对比
	 *
	 * Author hxpeng
	 *
	 * @param totalOrderParamDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(path = "/newOldCustomerCompare", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo newOldCustomerCompare(TotalOrderParamDto totalOrderParamDto) throws Exception {
		if (totalOrderParamDto == null || totalOrderParamDto.getOrderStroeType() == 0) {
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_STATISTICS_PARAMETER_ILLEGAL);
		}
		if (null == totalOrderParamDto.getBeforDate() || null == totalOrderParamDto.getAfterDate()) {
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_STATISTICS_PARAMETER_ILLEGAL);
		}
		NewOldCustomerOrderRangeDto newOldCustomerOrderRangeDto = mobleOrderStatisticsService
				.newOldCustomerCompare(totalOrderParamDto);
		return new ResponseInfo(newOldCustomerOrderRangeDto);
	}


	/**
	*
	* Description: 获取待发货的订单详情集合
	*
	* Author: hxpeng
	* createTime: 2017/12/1
	*
	* @param:
	**/
	@RequestMapping(path = "/getWaitToDeliverGoods",method=RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getDownList(Long orderId) throws GlobalException{
		if(orderId==null) {
			throw new GlobalException(StoreOrderExceptionEnum.STOREORDER_ID_NULL);
		}
		return new ResponseInfo(mobleOrderService.getWaitToDeliverGoods(orderId));
	}


	/**
	*
	* Description: 已发货
	*
	* Author: hxpeng
	* createTime: 2017/12/1
	*
	* @param:
	**/
	@RequestMapping(path = "/deliverGoods", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo deliverGoods(OrderParamsDto orderParamsDto) throws GlobalException {
		if (null == orderParamsDto) {
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_PARAM_ILLEGAL);
		}
		List<Long> storeOrderDetailIds = orderParamsDto.getStoreOrderDetailIds();
		Long storeOrderId = orderParamsDto.getStoreOrderId();
		String logisticsNo = orderParamsDto.getLogisticsNo();
		String logisticsType = orderParamsDto.getLogisticsType();
		if (null == storeOrderDetailIds || storeOrderDetailIds.size() < 1 || null == storeOrderId ||
				StringUtils.isEmpty(logisticsNo) || StringUtils.isEmpty(logisticsType)) {
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_PARAM_ILLEGAL);
		}
		mobleOrderService.deliverGoods(storeOrderDetailIds, storeOrderId, logisticsNo, logisticsType);
		return new ResponseInfo("SUCCESS");
	}


	/**
	*
	* Description: 平台中心，个人工作台，订单情况
	*
	* Author: hxpeng
	* createTime: 2017/11/22
	*
	* @param:
	**/
	@RequestMapping(path = "/getTotalOrderSituation", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getTotalOrderSituation() throws GlobalException {
		return new ResponseInfo(orderService.getTodayOrderSituation());
	}




}
