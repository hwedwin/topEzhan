package com.topaiebiz.transaction.order.merchant.moblie.controller;

import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.system.common.OrderStatusEnum;
import com.topaiebiz.system.moble.security.dto.TokenDto;
import com.topaiebiz.system.moble.security.util.TokenUtil;
import com.topaiebiz.transaction.order.merchant.exception.StoreOrderExceptionEnum;
import com.topaiebiz.transaction.order.merchant.service.OrderDetailsService;
import com.topaiebiz.transaction.order.merchant.service.StoreOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Description：普通订单的控制层。
 * 
 * 
 * Author Murray.Li
 * 
 * Date 2017年10月28日 下午8:26:30
 * 
 * Copyright:Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Controller
@RequestMapping(path = "/app/order/merchant/storeOrder")
public class StoreOrderMoblieController {

	@Autowired
	private StoreOrderService storeOrderService;
	
	@Autowired
	private OrderDetailsService orderDetailsService;

	/**
	*
	* Description: APP端：用户获取自己的订单列表
	*
	* Author: hxpeng
	* createTime: 2017/11/28
	*
	* @param:orderState 49为待评价状态，50为售后状态
	**/
	@RequestMapping(path = "/getOrderList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getOrderList(String token, Integer orderState) throws GlobalException {
		if (null == token || null == orderState || !OrderStatusEnum.isCodeInEnums(orderState)) {
			throw new GlobalException(StoreOrderExceptionEnum.ORDER_QUERY_PARAMETERS_ARE_ILLEGAL);
		}
		TokenUtil.verifyToken(token);
		TokenDto tokenDto = TokenUtil.getTokenDetail(token);
		if(null == tokenDto || null == tokenDto.getMemberId()){
			throw new GlobalException(StoreOrderExceptionEnum.ORDER_QUERY_PARAMETERS_ARE_ILLEGAL);
		}
		Long memberId = tokenDto.getMemberId();
		List<Integer> orderStateArray = new ArrayList<>();
		switch (orderState){
			case 0:
				orderStateArray = null;
				break;
			case 49:
				orderStateArray.add(OrderStatusEnum.HAVE_BEEN_RECEIVED.getCode());
				orderStateArray.add(OrderStatusEnum.ORDER_COMPLETION.getCode());
				break;
			case 50:
				orderStateArray.add(OrderStatusEnum.APPLY_FOR_RETURN_MONEY.getCode());
				orderStateArray.add(OrderStatusEnum.APPLY_FOR_RETURN_GOODS.getCode());
				orderStateArray.add(OrderStatusEnum.FAILURE_OF_RETURN_MONEY_AUDIT.getCode());
				orderStateArray.add(OrderStatusEnum.FAILURE_OF_REFUND_GOODS_AUDIT.getCode());
				orderStateArray.add(OrderStatusEnum.RETURN_THE_REFUND_GOODS.getCode());
				orderStateArray.add(OrderStatusEnum.RECEIVE_THE_REFUND_GOODS.getCode());
				orderStateArray.add(OrderStatusEnum.REFUND_CANCELLATION.getCode());
				orderStateArray.add(OrderStatusEnum.REFUND_COMPLETION.getCode());
				break;
			default:
				orderStateArray.add(orderState);
				break;
		}
		return new ResponseInfo(storeOrderService.getOrderListInAPP(orderStateArray, memberId));
	}

	/**
	*
	* Description: 获取订单的详情
	*
	* Author: hxpeng
	* createTime: 2017/12/2
	*
	* @param:
	**/
	@RequestMapping(path = "/getOrderDetail", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getOrderDetail(Long orderId, String token){
		TokenUtil.verifyToken(token);
		if(null == orderId){
			throw new GlobalException(StoreOrderExceptionEnum.STOREORDER_ID_NULL);
		}
		return new ResponseInfo(storeOrderService.getStoreOrderInfo(orderId));
	}

	/**
	*
	* Description: 获取订单的商品订单详情
	*
	* Author: hxpeng
	* createTime: 2017/12/7
	*
	* @param:
	**/
	@RequestMapping(path = "/getOrderInsideDetail", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getOrderInsideDetail(Long orderId, Long goodsId, String token){
		TokenUtil.verifyToken(token);
		if(null == orderId || null == goodsId){
			throw new GlobalException(StoreOrderExceptionEnum.STOREORDER_ID_NULL);
		}
		return new ResponseInfo(storeOrderService.getOrderInsideDetailInfo(orderId,goodsId));
	}



	/**
	 * 
	 * Description：删除订单
	 * 
	 * Author Murray.Li
	 * 
	 * @param orderId
	 * 
	 * @return ResponseInfo();
	 * 
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/cancelOrderById", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelOrderById(Long orderId,String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		Long memberId = tokenDetail.getMemberId();
		if (null == tokenDetail || StringUtils.isEmpty(orderId)) {
			throw new GlobalException(StoreOrderExceptionEnum.STOREORDER_ID_NULL);
		}
		storeOrderService.removeOrderByorderId(orderId,memberId);
		return new ResponseInfo();
	}

}
