package com.topaiebiz.transaction.order.merchant.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.nebulapaas.web.util.IllegalParamValidationUtils;
import com.topaiebiz.system.security.util.SecurityContextUtils;
import com.topaiebiz.transaction.order.merchant.dto.OrderAddressInvoiceDto;
import com.topaiebiz.transaction.order.merchant.dto.OrderListDto;
import com.topaiebiz.transaction.order.merchant.dto.OrderListParamsDto;
import com.topaiebiz.transaction.order.merchant.dto.PCOrderListResultDto;
import com.topaiebiz.transaction.order.merchant.entity.OrderAddressEntity;
import com.topaiebiz.transaction.order.merchant.entity.OrderInvoiceEntity;
import com.topaiebiz.transaction.order.merchant.exception.StoreOrderExceptionEnum;
import com.topaiebiz.transaction.order.merchant.service.OrderDetailsService;
import com.topaiebiz.transaction.order.merchant.service.OrderInvoiceService;
import com.topaiebiz.transaction.order.merchant.service.StoreOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * 
 * Description 订单的展现层
 * 
 * 
 * Author：zhushuyong
 * 
 * Date 2017年8月31日 下午8:27:58
 * 
 * Copyright：Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@RestController
@RequestMapping("/order/merchant/storeOrder")
public class StoreOrderController {

	@Autowired
	private OrderInvoiceService orderInvoiceService;
	
	@Autowired
	private OrderDetailsService orderDetailsService;
	
	@Autowired
	private StoreOrderService storeOrderService;

	/**
	*
	* Description: 平台运营中心：获取所有商家订单
	*
	* Author: hxpeng
	* createTime: 2017/11/28
	*
	* @param: page 分页
	* @param: orderListParamsDto 查询参数
	**/
	@RequestMapping(value = "/getBusinessOrderListInPlatform", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getBusinessOrderListInPlatform(Page<PCOrderListResultDto> page, OrderListParamsDto orderListParamsDto){
		if(null == page){
			page = new Page<>(1,10);
		}
		if(null != orderListParamsDto){
			orderListParamsDto.setOrderType(2);
		}else{
			throw new GlobalException(StoreOrderExceptionEnum.ORDER_QUERY_PARAMETERS_ARE_ILLEGAL);
		}
		return new ResponseInfo(storeOrderService.getOrderListInPC(page, orderListParamsDto));
	}

	/**
	 *
	 * Description: 平台运营中心：获取所有平台订单
	 *
	 * Author: hxpeng
	 * createTime: 2017/11/28
	 *
	 * @param:
	 **/
	@RequestMapping(value = "/getPlatformOrderListInPlatform", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getPlatformOrderListInPlatform(Page<PCOrderListResultDto> page, OrderListParamsDto orderListParamsDto){
		if(null == page){
			page = new Page<>(1,10);
		}
		if(null != orderListParamsDto){
			orderListParamsDto.setOrderType(1);
		}else{
			throw new GlobalException(StoreOrderExceptionEnum.ORDER_QUERY_PARAMETERS_ARE_ILLEGAL);
		}
		return new ResponseInfo(storeOrderService.getOrderListInPC(page, orderListParamsDto));
	}

	/**
	*
	* Description: 平台/商家运营中心：获取订单的详情
	*
	* Author: hxpeng
	* createTime: 2017/11/28
	*
	* @param:
	**/
	@RequestMapping(value = "/getOrderDetail", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getOrderDetail(Long orderId){
		if(null == orderId){
			throw new GlobalException(StoreOrderExceptionEnum.ORDER_QUERY_PARAMETERS_ARE_ILLEGAL);
		}
		return new ResponseInfo(storeOrderService.getOrderDetailInPC(orderId));
	}


	/**
	*
	* Description: 商家运营中心：获取订单列表
	*
	* Author: hxpeng
	* createTime: 2017/11/29
	*
	* @param:
	**/
	@RequestMapping(value = "/getOrderListInBusiness", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getOrderListInBusiness(Page<PCOrderListResultDto> page, OrderListParamsDto orderListParamsDto){
		if(null == page){
			page = new Page<>(1,10);
		}
		Long storeId = null;
		try{
			storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		} catch (NullPointerException e){
			throw new GlobalException(StoreOrderExceptionEnum.ORDER_QUERY_PARAMETERS_ARE_ILLEGAL);
		}
		if(null != orderListParamsDto && null != storeId){
			orderListParamsDto.setOrderType(null);
			orderListParamsDto.setBelongStore(storeId);
		}else{
			throw new GlobalException(StoreOrderExceptionEnum.ORDER_QUERY_PARAMETERS_ARE_ILLEGAL);
		}
		return new ResponseInfo(storeOrderService.getOrderListInPC(page, orderListParamsDto));
	}




	/*=================================================================================*/
	/*=================================================================================*/
	/*=================================================================================*/
	/*=================================================================================*/
	/*=================================================================================*/
	/*=================================================================================*/


	/**
	 * 
	 * Description 根据id查询订单对象
	 * 
	 * Author：zhushuyong
	 * 
	 * @param id
	 *            订单id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/queryById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo queryById(Long id) throws GlobalException {
		if (StringUtils.isEmpty(id)) {
			throw new GlobalException(StoreOrderExceptionEnum.STOREORDER_ID_NULL);
		}
		OrderListDto orderListDto = storeOrderService.findById(id);
		return new ResponseInfo(orderListDto);
	}

	/**
	 * 
	 * Description 商家后台订单展示，可查询 ，可分页接口
	 * 
	 * Author：zhushuyong
	 * 
	 * @param orderListDto
	 * @return
	 */
	@RequestMapping(value = "/queryByPageAdminList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo queryByPageAdminList(Page<OrderListDto> page, OrderListDto orderListDto)
			throws GlobalException {
		/** 获取当前的店铺ID */
		//根据电话查到会员，获取所属店铺
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		orderListDto.setStoreId(storeId);
		Page<OrderListDto> list = storeOrderService.findByPageAdminList(page, orderListDto);
		return new ResponseInfo(list);
	}

	/**
	 * 
	 * Description 平台后台订单展示，可查询 ，可分页接口
	 * 
	 * Author：zhushuyong
	 * 
	 * @param orderListDto
	 * @return
	 */
	@RequestMapping(value = "/queryByPageList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo queryByPageList(Page<OrderListDto> page, OrderListDto orderListDto)
			throws GlobalException {
		/** 获取当前的店铺ID */
		Page<OrderListDto> list = storeOrderService.findByPageAdminList(page, orderListDto);
		return new ResponseInfo(list);
	}
	
	/**
	 * 
	 * Description： 平台系统的商家订单管理。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param page
	 * @param orderListDto
	 * @return
	 */
	@RequestMapping(value = "/getStoreOrderList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getStoreOrderList(Page<OrderListDto> page, OrderListDto orderListDto) throws GlobalException{
		/** 获取当前的店铺ID */
		Page<OrderListDto> list = storeOrderService.findStoreOrderList(page, orderListDto);
		return new ResponseInfo(list);
	}
	/**
	 * 
	 * Description 根据订单id修改订单详情中的收货地址.
	 * 
	 * Author：zhushuyong
	 * 
	 * @param consigneeName
	 *            传入的收货人姓名
	 * @param address
	 *            传入的收货人地址
	 * @param addressMemo
	 *            传入的收货人备注
	 * @param invoiceType
	 *            传入的发票类型
	 * @param orderId
	 *            订单id
	 * @return
	 * @throws GlobalException
	 */ 
	@RequestMapping(value = "/updateAddressInvoice", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo updateAddressInvoice(@Valid OrderAddressInvoiceDto orderAddressInvoiceDto, BindingResult result)
			throws GlobalException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		 storeOrderService.modifyAddressInvoice(orderAddressInvoiceDto);
		return new ResponseInfo();
	}

	/**
	 * 
	 * Description 根据订单id查询订单发票
	 * 
	 * Author：zhushuyong
	 * 
	 * @param orderId
	 *            订单id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/queryOrderInvoice", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo queryOrderInvoice(Long orderId) throws GlobalException {
		if (StringUtils.isEmpty(orderId)) {
			throw new GlobalException(StoreOrderExceptionEnum.STOREORDER_ID_NULL);
		}
		OrderInvoiceEntity orderInvoice = orderInvoiceService.queryOrderInvoice(orderId);
		return new ResponseInfo(orderInvoice);
	}

	/**
	 * 
	 * Description 删除订单。
	 * 
	 * Author：Murray.Li
	 * 
	 * @param ids
	 * 订单id数组
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/updateDeletedFlag", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo updateDeletedFlag(Long[] ids) throws GlobalException {
		if (StringUtils.isEmpty(ids) || ids.length < 0) {
			throw new GlobalException(StoreOrderExceptionEnum.STOREORDER_ID_NULL);
		}
		storeOrderService.removeDeletedFlag(ids);
		return new ResponseInfo();
	}

	/**
	 * 
	 * Description：根据订单编号查询订单地址。
	 * 
	 * Author Murray.Li
	 * 
	 * @param orderId
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getAddressByOrderId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getAddressByOrderId(Long orderId) throws GlobalException {
		if (null == orderId) {
			throw new GlobalException(StoreOrderExceptionEnum.STOREORDER_ID_NULL);
		}
		OrderAddressEntity orderAddress = storeOrderService.getOrderAddressByOrderId(orderId);
		
		return new ResponseInfo(orderAddress);
	}
	
	/**
	 * 
	 * Description： 根据订单编号查询需要标记发货
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getDelivery",method=RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getDelivery(Long orderId) throws GlobalException{
		if (null == orderId) {
			throw new GlobalException(StoreOrderExceptionEnum.STOREORDER_ID_NULL);
		}
		OrderAddressEntity orderAddressEntity=storeOrderService.getDelivery(orderId); 
		return new ResponseInfo(orderAddressEntity); 
	}
	

	/**
	 * 
	 * Description：查询商家端的各状态订单的数量。
	 * 
	 * Author Murray.Li 
	 * 
	 * @return
	 */
	@RequestMapping(path = "/getOrderCountByOrderState",method=RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getOrderCountByOrderState() {
		//根据电话查到会员，获取所属店铺
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		Map<String,Object> map=storeOrderService.getOrderCountByOrderState(storeId);
		return new ResponseInfo(map);
	}
}
