package com.topaiebiz.transaction.order.merchant.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.transaction.order.merchant.dto.*;
import com.topaiebiz.transaction.order.merchant.entity.OrderAddressEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * Description 订单的业务接口  
 * 
 * 
 * Author:zhushuyong 
 *    
 * Date 2017年8月31日 下午7:49:42 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public interface StoreOrderService {

	/**
	 *
	 * Description: PC：平台运营中心：获取所有商家或平台 订单
	 *
	 * Author: hxpeng
	 * createTime: 2017/11/28
	 *
	 * @param: orderListParamsDto 商家/平台 订单列表查询参数DTO
	 **/
	Page<PCOrderListResultDto> getOrderListInPC(Page<PCOrderListResultDto> page, OrderListParamsDto orderListParamsDto);

	/**
	*
	* Description: PC：获取订单的详情
	*
	* Author: hxpeng
	* createTime: 2017/11/28
	*
	* @param:
	**/
	OrderDetailResultDto getOrderDetailInPC(long storeOrderId);


	/**
	 *
	 * Description: APP：获取订单列表
	 *
	 * Author: hxpeng
	 * createTime: 2017/11/29
	 *
	 * @param: orderStateArray 订单状态类型
	 **/
	List<APPOrderListResultDto> getOrderListInAPP(List<Integer> orderStateArray, Long memberId);

	/**
	*
	* Description: 获取订单详情
	*
	* Author: hxpeng
	* createTime: 2017/12/2
	*
	* @param:
	**/
	StoreOrderDto getStoreOrderInfo(Long storeOrderId);

	/**
	*
	* Description: 获取订单中的商品订单详情
	*
	* Author: hxpeng
	* createTime: 2017/12/7
	*
	* @param:
	**/
	OrderDetailsDto getOrderInsideDetailInfo(Long storeOrder, Long goodsId);


	/**
	*
	* Description: 删除订单
	*
	* Author: hxpeng
	* createTime: 2017/12/4
	*
	* @param:
	**/
	void deleteOrder(Long storeOrderId);
	/*=================================================================================*/
	/*=================================================================================*/



	/**
	 * Description 根据id查询订单对象  
	 * 
	 * Author zhushuyong   
	 * 
	 * @param id 订单id
	 * @return 订单对象
	 * @throws GlobalException 
	 */
	OrderListDto findById(Long id) throws GlobalException;
	
	/**
	 * 
	 * Description 后台订单展示，可查询  
	 * 
	 * Author zhushuyong   
	 * 
	 * @param page
	 * 			翻页对象，可以作为 xml 参数直接使用，传递参数 Page 即自动分页
	 * @param orderListDto
	 * @return
	 */
	Page<OrderListDto> findByPageAdminList(Page<OrderListDto> page,OrderListDto orderListDto);
	
	/**
	 * 
	 * Description 根据订单id修改订单详情中的收货地址跟发票类型  
	 * 
	 * Author zhushuyong   
	 * 
	 * @param consigneeName
	 * 			传入的收货人姓名
	 * @param address
	 * 			传入的收货人地址
	 * @param addressMemo
	 * 			传入的收货人备注
	 * @param invoiceType
	 * 			传入的发票类型
	 * @param orderId
	 * 			订单id
	 * @return
	 * @throws GlobalException 
	 */
	void modifyAddressInvoice(OrderAddressInvoiceDto orderAddressInvoiceDto) throws GlobalException;
	
	/**
	 * 
	 * Description 批量修改逻辑删除标识。 
	 * 
	 * Author zhushuyong   
	 * 
	 * @param ids
	 * 			订单id数组
	 * @return
	 * @throws GlobalException 
	 */
	void removeDeletedFlag(Long[] ids) throws GlobalException;

	/***
	 * 
	 * Description：根据订单编号查看配送地址。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 * @return
	 * @throws GlobalException 
	 */
	OrderAddressEntity getOrderAddressByOrderId(Long orderId) throws GlobalException;

	/**
	 * 
	 * Description： 根据订单编号查询需要标记发货的订单的信息。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 * @return
	 * @throws GlobalException 
	 */ 
	OrderAddressEntity getDelivery(Long orderId) throws GlobalException;

	/**
	 * 
	 * Description：删除订单。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param orderId
	 * @param memberId 
	 * @throws GlobalException 
	 */
	void removeOrderByorderId(Long orderId, Long memberId) throws GlobalException;

	/**
	 * 
	 * Description： 每天晚上十二点定时查看数据库已发货订单是否已经达到七天。 
	 * 
	 * Author Murray.Li 
	 *
	 */
	void changeOrderFinish();
 
	/***
	 * 
	 * Description：平台系统的商家订单管理。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param page
	 * @param orderListDto
	 * @return
	 */
	Page<OrderListDto> findStoreOrderList(Page<OrderListDto> page, OrderListDto orderListDto);

	/**
	 * 
	 * Description：根据订单状态查询相应订单的数量。
	 * 
	 * Author Murray.Li 
	 * 
	 * @param storeId
	 * @return
	 */
	Map<String, Object> getOrderCountByOrderState(Long storeId);


} 
