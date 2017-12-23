package com.topaiebiz.payment.order.moble.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.payment.order.dto.RefundPriceParamsDto;
import com.topaiebiz.payment.order.exception.PaymentOrderExceptionEnum;
import com.topaiebiz.payment.order.moble.exception.MobleOrderExceptionEnum;
import com.topaiebiz.payment.order.service.RefundOrderService;
import com.topaiebiz.system.moble.security.dto.TokenDto;
import com.topaiebiz.system.moble.security.util.TokenUtil;
import com.topaiebiz.transaction.refundOrder.dto.APPRefundOrderListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
*
* Description: 售后控制器
*
* Author: hxpeng
* createTime: 2017/12/7
*
* @param:
**/
@RestController
@RequestMapping("/app/payment/refundOrder")
public class RefundOrderController {

	@Autowired
	private RefundOrderService refundOrderService;
	/**
	*
	* Description: APP：获取退货/退款列表
	*
	* Author: hxpeng
	* createTime: 2017/11/29
	*
	* @param:
	**/
	@RequestMapping(value = "/getAppRefundOrderList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getAppRefundOrderList(String token, Page<APPRefundOrderListDto> page) {
		// 检验token
		if(null == page){
			page = new Page<>(1,10);
		}
		TokenUtil.verifyToken(token);
		TokenDto tokenDto = TokenUtil.getTokenDetail(token);
		if(null == tokenDto || null == tokenDto.getMemberId()){
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_PARAM_ILLEGAL);
		}
		return new ResponseInfo(refundOrderService.getRefundOrderListInAPP(page, tokenDto.getMemberId()));
	}


	@RequestMapping(value = "/getAppRefundOrderDetail", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getAppRefundOrderDetail(String token, Long refundOrderId) {
		if(StringUtils.isEmpty(token) || null == refundOrderId){
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_PARAM_ILLEGAL);
		}
		TokenUtil.verifyToken(token);
		return new ResponseInfo(refundOrderService.getRefundOrderInfo(refundOrderId));
	}



//	@RequestMapping(value = "/chooseRefundType", method = RequestMethod.GET)
//	@ResponseBody
//	public ResponseInfo chooseRefundType(String token, Long storeOrderId, String refundType){
//		// 检验token
//		TokenUtil.verifyToken(token);
//		if(null == storeOrderId){
//			throw new GlobalException(PaymentOrderExceptionEnum.CALCULATION_PARAMS_ILLEGAL);
//		}
//		if(StringUtils.isEmpty(refundType)){
//			refundType = "1";
//		}
//		return new ResponseInfo(refundOrderService.chooseRefundType(storeOrderId, refundType));
//	}

	/**
	*
	* Description: 计算退货退款金额（新增）(售后改为商品级)
	*
	* Author: hxpeng
	* createTime: 2017/11/23
	*
	* @param:
	**/
	@RequestMapping(value = "/calculationRefundPrice", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo calculationRefundPrice(RefundPriceParamsDto refundPriceParamsDto) throws GlobalException {
		if(null == refundPriceParamsDto || null == refundPriceParamsDto.getStoreOrderId()
				|| null == refundPriceParamsDto.getStoreOrderDetailIds() || refundPriceParamsDto.getStoreOrderDetailIds().size() != 1){
			throw new GlobalException(PaymentOrderExceptionEnum.CALCULATION_PARAMS_ILLEGAL);
		}
		String token = refundPriceParamsDto.getToken();
		TokenUtil.verifyToken(token);
		return new ResponseInfo(refundOrderService.calculationRefundPrice(refundPriceParamsDto, false));
	}

	@RequestMapping(value = "/getRefundOrderInfo", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getRefundOrderInfo(Long refundOrderId, String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		return new ResponseInfo(refundOrderService.getRefundOrderInfo(refundOrderId));
	}



	/**
	 *
	 * Description: 申请退款/退货 (售后改为商品级)
	 *
	 * Author: hxpeng
	 * createTime: 2017/11/23
	 *
	 * @param:
	 **/
	@RequestMapping(value = "/applyRefund", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo applyRefund(RefundPriceParamsDto refundPriceParamsDto) throws GlobalException {
		if(null == refundPriceParamsDto || StringUtils.isEmpty(refundPriceParamsDto.getRefundType()) || null == refundPriceParamsDto.getStoreOrderId()
				|| null == refundPriceParamsDto.getStoreOrderDetailIds() || refundPriceParamsDto.getStoreOrderDetailIds().size() != 1){
			throw new GlobalException(PaymentOrderExceptionEnum.CALCULATION_PARAMS_ILLEGAL);
		}
		String token = refundPriceParamsDto.getToken();
		TokenUtil.verifyToken(token);
		Integer isUpdate = refundPriceParamsDto.getIsUpdate();
		if(isUpdate != null && isUpdate.equals(1)){
			refundOrderService.updateRefundOrder(refundPriceParamsDto);
		}else{
			refundOrderService.applyRefund(refundPriceParamsDto);
		}
		return new ResponseInfo("SUCCESS");
	}

	/**
	*
	* Description: APP: 寄回退换商品
	*
	* Author: hxpeng
	* createTime: 2017/12/1
	*
	* @param:
	**/
	@RequestMapping(value = "/sendBackRefundGoods", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo sendBackRefundGoods(RefundPriceParamsDto refundPriceParamsDto) throws GlobalException {
		if (null == refundPriceParamsDto) {
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_PARAM_ILLEGAL);
		}
		String token = refundPriceParamsDto.getToken();
		TokenUtil.verifyToken(token);
		TokenDto tokenDto = TokenUtil.getTokenDetail(token);
		if(null == tokenDto || null == tokenDto.getMemberId()){
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_PARAM_ILLEGAL);
		}
		Long refundOrderId = refundPriceParamsDto.getRefundOrderId();
		List<Long> refundOrderDetailIds = refundPriceParamsDto.getStoreOrderDetailIds();
		String logisticsNo = refundPriceParamsDto.getLogisticsNo();
		String logisticsType = refundPriceParamsDto.getLogisticsType();
		if (null == refundOrderId || null == refundOrderDetailIds || refundOrderDetailIds.size() < 1 ||
				StringUtils.isEmpty(logisticsNo) || StringUtils.isEmpty(logisticsNo)) {
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_PARAM_ILLEGAL);
		}
		refundOrderService.sendBackRefundGoods(refundOrderDetailIds, refundOrderId, logisticsType, logisticsNo, tokenDto.getMemberId());
		return new ResponseInfo();
	}




	/**
	*
	* Description: 撤销售后申请
	*
	* Author: hxpeng
	* createTime: 2017/12/7
	*
	* @param:
	**/
	@RequestMapping(value = "/cancelRefund", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelRefund(Long refundOrderId, String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDto = TokenUtil.getTokenDetail(token);
		if(null == refundOrderId || null == tokenDto){
			throw new GlobalException(PaymentOrderExceptionEnum.CALCULATION_PARAMS_ILLEGAL);
		}
		refundOrderService.cancelRefund(refundOrderId, tokenDto.getMemberId());
		return new ResponseInfo("SUCCESS");
	}




}
