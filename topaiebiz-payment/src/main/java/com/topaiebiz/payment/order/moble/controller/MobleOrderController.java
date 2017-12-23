/**
 * 
 */
package com.topaiebiz.payment.order.moble.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPayUtil;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.payment.order.moble.dto.SettlementGoodParamDto;
import com.topaiebiz.payment.order.moble.dto.SettlementParamDto;
import com.topaiebiz.payment.order.moble.exception.MobleOrderExceptionEnum;
import com.topaiebiz.payment.order.moble.service.MobleOrderService;
import com.topaiebiz.system.common.IpUtil;
import com.topaiebiz.system.moble.security.dto.TokenDto;
import com.topaiebiz.system.moble.security.util.TokenUtil;
import com.topaiebiz.thirdparty.pay.client.ThirdPartyWeChatClient;
import com.topaiebiz.transaction.order.total.dto.TotalOrderParamDto;
import com.topaiebiz.transaction.order.total.entity.TotalOrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Description： 订单类
 * 
 * 
 * Author hxpeng
 * 
 * Date 2017年10月23日 上午10:45:50
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@RestController
@RequestMapping("/app/payment/mobile/order")
public class MobleOrderController {

	@Autowired
	private MobleOrderService mobleOrderService;

	/**
	 * 
	 * Description： 商品结算请求接口
	 * 
	 * Author hxpeng
	 * 
	 * @param paramsJsonStr
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/productSettlement", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo productSettlement(String paramsJsonStr) throws GlobalException {
		if (!StringUtils.isEmpty(paramsJsonStr)) {
			SettlementParamDto settlementParamDto = null;
			List<SettlementGoodParamDto> settlementGoodParamDtos = null;
			try {
				settlementParamDto = JSONObject.parseObject(paramsJsonStr, SettlementParamDto.class);
				if (null == settlementParamDto) {
					throw new GlobalException(MobleOrderExceptionEnum.SETTLEMENT_PARAM_ILLEGAL);
				}
				TokenUtil.verifyToken(settlementParamDto.getToken());
				settlementGoodParamDtos = settlementParamDto.getSettlementGoodParamDtos();
				if (null == settlementGoodParamDtos || settlementGoodParamDtos.size() < 1) {
					throw new GlobalException(MobleOrderExceptionEnum.SETTLEMENT_PARAM_ILLEGAL);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new GlobalException(MobleOrderExceptionEnum.SETTLEMENT_PARAM_ILLEGAL);
			}
			return new ResponseInfo(mobleOrderService.settlementGoods(settlementParamDto));
		}
		throw new GlobalException(MobleOrderExceptionEnum.SETTLEMENT_PARAM_ILLEGAL);
	}

	/**
	*
	* Description: 选择店铺/平台优惠活动动态修改金额
	*
	* Author: hxpeng
	* createTime: 2017/11/19
	*
	* @param:
	**/
	@RequestMapping(path = "/choosePromotionCalculationPrice", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo choosePromotionCalculationPrice(String paramsJsonStr) throws GlobalException {
		SettlementParamDto settlementParamDto = null;
		List<SettlementGoodParamDto> settlementGoodParamDtos = null;
		if (!StringUtils.isEmpty(paramsJsonStr)) {
			try {
				settlementParamDto = JSONObject.parseObject(paramsJsonStr, SettlementParamDto.class);
				if (null == settlementParamDto) {
					throw new GlobalException(MobleOrderExceptionEnum.SETTLEMENT_PARAM_ILLEGAL);
				}
				TokenUtil.verifyToken(settlementParamDto.getToken());
				settlementGoodParamDtos = settlementParamDto.getSettlementGoodParamDtos();
				if (null == settlementGoodParamDtos || settlementGoodParamDtos.size() == 0) {
					throw new GlobalException(MobleOrderExceptionEnum.SETTLEMENT_PARAM_ILLEGAL);
				}
			} catch (Exception e) {
				throw new GlobalException(MobleOrderExceptionEnum.SETTLEMENT_PARAM_ILLEGAL);
			}
			try {
				return new ResponseInfo(mobleOrderService.choosePromotionCalculationPrice(settlementParamDto));
			} catch (Exception e) {
				e.printStackTrace();
				throw new GlobalException(MobleOrderExceptionEnum.ORDER_SUBMIT_FAILURE);
			}
		}
		throw new GlobalException(MobleOrderExceptionEnum.SETTLEMENT_PARAM_ILLEGAL);
	}



	/**
	 * 
	 * Description： 订单创建请求接口
	 * 
	 * Author hxpeng
	 * 
	 * @param paramsJsonStr
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/orderSubmit", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo orderSubmit(String paramsJsonStr) throws GlobalException {
		SettlementParamDto settlementParamDto = null;
		List<SettlementGoodParamDto> settlementGoodParamDtos = null;
		if (!StringUtils.isEmpty(paramsJsonStr)) {
			try {
				settlementParamDto = JSONObject.parseObject(paramsJsonStr, SettlementParamDto.class);
				if (null == settlementParamDto) {
					throw new GlobalException(MobleOrderExceptionEnum.SETTLEMENT_PARAM_ILLEGAL);
				}
				TokenUtil.verifyToken(settlementParamDto.getToken());
				if (null == settlementParamDto.getMemberAddressId()) {
					throw new GlobalException(MobleOrderExceptionEnum.SETTLEMENT_PARAM_ILLEGAL);
				}
				settlementGoodParamDtos = settlementParamDto.getSettlementGoodParamDtos();
				if (null == settlementGoodParamDtos || settlementGoodParamDtos.size() == 0) {
					throw new GlobalException(MobleOrderExceptionEnum.SETTLEMENT_PARAM_ILLEGAL);
				}
			} catch (Exception e) {
				throw new GlobalException(MobleOrderExceptionEnum.SETTLEMENT_PARAM_ILLEGAL);
			}
			try {
				return new ResponseInfo(mobleOrderService.submitOrder(settlementParamDto));
			} catch (Exception e) {
				e.printStackTrace();
				throw new GlobalException(MobleOrderExceptionEnum.ORDER_SUBMIT_FAILURE);
			}
		}
		throw new GlobalException(MobleOrderExceptionEnum.SETTLEMENT_PARAM_ILLEGAL);
	}

	/**
	*
	* Description: 返回总订单信息
	*
	* Author: hxpeng
	* createTime: 2017/11/14
	*
	* @param:
	**/
	@RequestMapping(path = "/getTotalOrderInfo", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getTotalOrderInfo(String totalOrderId, String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		if (!StringUtils.isEmpty(totalOrderId)) {
			TotalOrderEntity totalOrderEntity = mobleOrderService.findTotalOrderById(totalOrderId);
			if(null == totalOrderEntity){
				throw new GlobalException(MobleOrderExceptionEnum.ORDER_IS_NOT_FOUND);
			}
			return new ResponseInfo(totalOrderEntity);
		}
		throw new GlobalException(MobleOrderExceptionEnum.ORDER_PARAM_ILLEGAL);
	}


	/**
	 * 
	 * Description： 取消订单
	 * 
	 * Author hxpeng
	 * 
	 * @param settlementParamDto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/cancelOrder", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelOrder(String paramsJsonStr) throws GlobalException {
		if (!StringUtils.isEmpty(paramsJsonStr)) {
			try {
				SettlementParamDto settlementParamDto = JSONObject.parseObject(paramsJsonStr, SettlementParamDto.class);
				if (null == settlementParamDto) {
					throw new GlobalException(MobleOrderExceptionEnum.SETTLEMENT_PARAM_ILLEGAL);
				}
				TokenUtil.verifyToken(settlementParamDto.getToken());
				if (null == settlementParamDto.getStoreOrderId()) {
					throw new GlobalException(MobleOrderExceptionEnum.SETTLEMENT_PARAM_ILLEGAL);
				}
				mobleOrderService.cancelOrder(settlementParamDto);
				return new ResponseInfo(1);
			} catch (Exception e) {
				throw new GlobalException(MobleOrderExceptionEnum.SETTLEMENT_PARAM_ILLEGAL);
			}
		}
		throw new GlobalException(MobleOrderExceptionEnum.SETTLEMENT_PARAM_ILLEGAL);
	}

	/**
	*
	* Description: 确认收货
	*
	* Author: hxpeng
	* createTime: 2017/12/1
	*
	* @param:
	**/
	@RequestMapping(path = "/confirmReceipt", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo confirmReceipt(TotalOrderParamDto totalOrderParamDto) throws GlobalException {
		if (null == totalOrderParamDto) {
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_PARAM_ILLEGAL);
		}
		String token = totalOrderParamDto.getToken();
		TokenUtil.verifyToken(token);
		TokenDto tokenDto = TokenUtil.getTokenDetail(token);
		if(null == tokenDto || null == tokenDto.getMemberId()){
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_PARAM_ILLEGAL);
		}
		Long memberId = tokenDto.getMemberId();
		Long storeOrderId = totalOrderParamDto.getStoreOrderId();
		List<Long> storeOrderDetailIds = totalOrderParamDto.getStoreOrderDetailIds();
		if (null == memberId || null == storeOrderId || null == storeOrderDetailIds || storeOrderDetailIds.size() < 1) {
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_PARAM_ILLEGAL);
		}
		mobleOrderService.confirmReceipt(storeOrderDetailIds, storeOrderId, memberId);
		return new ResponseInfo("SUCCESS");
	}


	/**
	*
	* Description: 延长收货(默认三天)
	*
	* Author: hxpeng
	* createTime: 2017/12/1
	*
	* @param:  token, storeOrderId, storeOrderDetailIds
	**/
	@RequestMapping(path = "/extendedGoodsReceipt", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo extendedGoodsReceipt(TotalOrderParamDto totalOrderParamDto) throws GlobalException {
		if (null == totalOrderParamDto) {
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_PARAM_ILLEGAL);
		}
		String token = totalOrderParamDto.getToken();
		TokenUtil.verifyToken(token);
		TokenDto tokenDto = TokenUtil.getTokenDetail(token);
		if(null == tokenDto || null == tokenDto.getMemberId()){
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_PARAM_ILLEGAL);
		}
		Long memberId = tokenDto.getMemberId();
		Long storeOrderId = totalOrderParamDto.getStoreOrderId();
		List<Long> storeOrderDetailIds = totalOrderParamDto.getStoreOrderDetailIds();
		if (null == memberId || null == storeOrderId || null == storeOrderDetailIds || storeOrderDetailIds.size() < 1) {
			throw new GlobalException(MobleOrderExceptionEnum.ORDER_PARAM_ILLEGAL);
		}
		mobleOrderService.extendedGoodsReceipt(storeOrderDetailIds, storeOrderId, memberId);
		return new ResponseInfo("SUCCESS");
	}

	/**
	 * 
	 * Description： 支付宝支付
	 * 
	 * Author hxpeng
	 * 
	 * @param totalOrderId
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/aliPayment", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo aliPayment(String totalOrderId, String token, String returnUrl) throws GlobalException {
		TokenUtil.verifyToken(token);
		return new ResponseInfo(mobleOrderService.alipayPaymentOrder(totalOrderId,returnUrl));
	}

	/**
	 *
	 * Description： 支付宝同步回调接口
	 *
	 * Author hxpeng
	 *
	 * @param request
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/alipaySynchroNotic", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo alipaySynchroNotic(HttpServletRequest request) throws GlobalException {
		return new ResponseInfo(mobleOrderService.alipaySynchroNotic(request.getParameterMap()));
	}

	/**
	 * 
	 * Description： 支付宝异步回调接口
	 * 
	 * Author hxpeng
	 * 
	 * @param request
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/asyncNotic", method = RequestMethod.POST)
	@ResponseBody
	public String asyncNotic(HttpServletRequest request) throws GlobalException {
		String s = "failure!";
		int i = mobleOrderService.alipayAsyncNotic(request.getParameterMap());
		if(i > 0){
			s = "success";
		}
		return s;
	}

	/**
	 *
	 * Description： 微信支付
	 *
	 * Author hxpeng
	 *
	 * @param totalOrderId
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/wechatPay", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo wechatPay(HttpServletRequest request) throws GlobalException {
		String totalOrderId = request.getParameter("totalOrderId");
		String token = request.getParameter("token");
		TokenUtil.verifyToken(token);
		return new ResponseInfo(mobleOrderService.wechatPaymentOrder(totalOrderId, IpUtil.getIpAddr(request), token));
	}

	/**
	 *
	 * Description： 接收微信支付异步通知
	 *
	 * Author hxpeng
	 *
	 * @param totalOrderId
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/wechatPayAsyncNotic", method = RequestMethod.POST)
	public String wechatPayAsyncNotic(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String inputLine;
		String notityXml = "";
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		//微信给返回的东西
		try {
			while ((inputLine = request.getReader().readLine()) != null) {
				notityXml += inputLine;
			}
			request.getReader().close();

			int i = mobleOrderService.wechatPayAsyncNotic(WXPayUtil.xmlToMap(notityXml));
			if(i > 0){
				return ThirdPartyWeChatClient.getPayResultXmlStr(true);
			}else{
				return ThirdPartyWeChatClient.getPayResultXmlStr(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}


}
