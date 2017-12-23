package com.topaiebiz.payment.order.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.payment.order.exception.PaymentOrderExceptionEnum;
import com.topaiebiz.payment.order.moble.exception.MobleOrderExceptionEnum;
import com.topaiebiz.payment.order.service.RefundOrderService;
import com.topaiebiz.transaction.refundOrder.dto.RefundDto;
import com.topaiebiz.transaction.refundOrder.dto.RefundOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * Description 商家退货退款
 * 
 * 
 * Author Joe
 * 
 * Date 2017年11月8日 下午5:22:26
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@RestController
@RequestMapping("/payment/storeRefundOrder")
public class StoreRefundOrderController {

	@Autowired
	private RefundOrderService refundOrderService;

	/**
	 * 
	 * Description 获取退货订单列表
	 * 
	 * Author Joe
	 * 
	 * @param page
	 * @param refundOrderDto
	 * @return
	 */
	@RequestMapping(value = "/getRefundOrderList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getRefundOrderList(Page<RefundOrderDto> page, RefundOrderDto refundOrderDto) {
		/** 退货类型不得为空 */
		if (null == refundOrderDto.getReturnType()) {
			throw new GlobalException(PaymentOrderExceptionEnum.RETURNTYPE_NOT_NULL);
		}
		Page<RefundOrderDto> refundOrderList = refundOrderService.getRefundOrderList(page, refundOrderDto);
		return new ResponseInfo(refundOrderList);
	}

	/**
	 * 
	 * Description 详情/处理退货订单
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getRefundOrder", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getRefundOrder(Long id) {
		/** ID不得为空 */
		if (null == id) {
			throw new GlobalException(PaymentOrderExceptionEnum.REFUNDORDER_ID_NOT_NULL);
		}
		RefundDto refundDto = refundOrderService.getRefundOrder(id);
		return new ResponseInfo(refundDto);
	}

	/**
	 * 
	 * Description 删除订单
	 * 
	 * Author Joe
	 * 
	 * @param refundOrderId
	 * @return
	 */
	@RequestMapping(value = "/cancelRefundOrder", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelRefundOrder(Long[] ids) {
		if (null == ids) {
			throw new GlobalException(PaymentOrderExceptionEnum.REFUNDORDER_ID_NOT_NULL);
		}
		refundOrderService.removeRefundOrder(ids);
		return new ResponseInfo();
	}

	/**
	 *
	 * Description: 审核退款/退货的申请 成功
	 *
	 * Author: hxpeng
	 * createTime: 2017/12/5
	 *
	 * @param:
	 **/
	@RequestMapping(value = "/auditingRefundApplySuccess", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo auditingRefundApplySuccess(Long refundOrderId){
		if(null == refundOrderId){
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_PARAM_ILLEGAL);
		}
		refundOrderService.auditingRefundApply(refundOrderId, true);
		return new ResponseInfo("SUCCESS");
	}

	/**
	 *
	 * Description: 审核退款/退货的申请 失败
	 *
	 * Author: hxpeng
	 * createTime: 2017/12/5
	 *
	 * @param:
	 **/
	@RequestMapping(value = "/auditingRefundApplyFail", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo auditingRefundApplyFail(Long refundOrderId){
		if(null == refundOrderId){
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_PARAM_ILLEGAL);
		}
		refundOrderService.auditingRefundApply(refundOrderId, false);
		return new ResponseInfo("SUCCESS");
	}

	/**
	 *
	 * Description: 审核收到的退换货 成功
	 *
	 * Author: hxpeng
	 * createTime: 2017/12/5
	 *
	 * @param:
	 **/
	@RequestMapping(value = "/auditingRefundGoodsSuccess", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo auditingRefundGoodsSuccess(Long refundOrderId){
		if(null == refundOrderId){
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_PARAM_ILLEGAL);
		}
		refundOrderService.auditingRefundGoods(refundOrderId, true);
		return new ResponseInfo("SUCCESS");
	}

	/**
	 *
	 * Description: 审核收到的退换货 失败
	 *
	 * Author: hxpeng
	 * createTime: 2017/12/5
	 *
	 * @param:
	 **/
	@RequestMapping(value = "/auditingRefundGoodFail", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo auditingRefundGoodFail(Long refundOrderId){
		if(null == refundOrderId){
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_PARAM_ILLEGAL);
		}
		refundOrderService.auditingRefundGoods(refundOrderId, false);
		return new ResponseInfo("SUCCESS");
	}


}
