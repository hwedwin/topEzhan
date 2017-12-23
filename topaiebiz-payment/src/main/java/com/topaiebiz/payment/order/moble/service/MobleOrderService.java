/**
 * 
 */
package com.topaiebiz.payment.order.moble.service;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.goods.sku.dto.StoreOrderDetailDto;
import com.topaiebiz.payment.order.moble.dto.SettlementParamDto;
import com.topaiebiz.payment.order.moble.dto.SettlementResultDto;
import com.topaiebiz.transaction.order.total.entity.TotalOrderEntity;

import java.util.List;
import java.util.Map;

/**  
 * Description： 订单接口
 * 
 * 
 * Author hxpeng 
 *    
 * Date 2017年10月23日 上午11:13:10 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public interface MobleOrderService {


	/**
	 * 
	 * Description： 商品结算业务逻辑处理
	 * 
	 * Author hxpeng   
	 * 
	 * @param settlementGoodDto
	 */
	SettlementResultDto settlementGoods(SettlementParamDto settlementParamDto) throws GlobalException;
	
	/**
	 * 
	 * Description： 生成订单
	 * 
	 * Author hxpeng   
	 * 
	 * @param settlementParamDto
	 * @throws GlobalException
	 */
	TotalOrderEntity submitOrder(SettlementParamDto settlementParamDto) throws GlobalException;

	/**
	*
	* Description: 选择营销活动动态修改价格
	*
	* Author: hxpeng
	* createTime: 2017/11/19
	*
	* @param:
	**/
	Double choosePromotionCalculationPrice(SettlementParamDto settlementParamDto);

	/**
	*
	* Description: 查找总订单信息根据主键ID
	*
	* Author: hxpeng
	* createTime: 2017/11/14
	*
	* @param:
	**/
	TotalOrderEntity findTotalOrderById(String totalOrderId);

	/**
	 * 
	 * Description：取消订单
	 * 
	 * Author hxpeng   
	 * 
	 * @param settlementParamDto
	 * @throws GlobalException
	 */
	Integer cancelOrder(SettlementParamDto settlementParamDto) throws GlobalException;
	
	/**
	 * 
	 * Description： 修改订单状态
	 * 
	 * Author hxpeng   
	 * 
	 * @param totalOrderParamDto
	 */
//	Integer changeOrderState(TotalOrderParamDto totalOrderParamDto,Integer orderStateCode, String token) throws GlobalException;


	/**
	*
	* Description: 获取待发货的订单详情集合
	*
	* Author: hxpeng
	* createTime: 2017/12/1
	*
	* @param:
	**/
	List<StoreOrderDetailDto> getWaitToDeliverGoods(Long storeOrderId);

	/**
	 *
	 * Description： 发货
	 *
	 * Author hxpeng
	 *
	 * @return
	 */
	void deliverGoods(List<Long> storeOrderDetailIds, Long storeOrderId, String logisticsNo, String logisticsType) throws GlobalException;

	/**
	 * 
	 * Description：订单支付宝支付
	 * 
	 * Author hxpeng   
	 *
	 * @param totalOrderId 总订单ID
	 * @param returnUrl 跳转地址
	 * @return
	 * @throws GlobalException
	 */
	String alipayPaymentOrder(String totalOrderId , String returnUrl) throws GlobalException;

	/**
	 *
	 * Description：支付宝同步回调通知
	 *
	 * Author hxpeng   
	 *
	 * @param requestParams
	 * @return
	 * @throws GlobalException
	 */
	Integer alipaySynchroNotic(Map<String, String[]> requestParams) throws GlobalException;

	/**
	 *
	 * Description：支付宝异步回调通知
	 *
	 * Author hxpeng
	 *
	 * @param requestParams
	 * @return
	 * @throws GlobalException
	 */
	Integer alipayAsyncNotic(Map<String, String[]> requestParams) throws GlobalException;

	/**
	 *
	 * Description：微信支付
	 *
	 * Author hxpeng
	 *
	 * @param totalOrderId
	 * @param ip
	 * @param token
	 * @return
	 * @throws GlobalException
	 */
	String wechatPaymentOrder(String totalOrderId, String ip, String token) throws GlobalException;

	/**
	 *
	 * Description：微信支付异步回调通知
	 *
	 * Author hxpeng
	 *
	 * @param requestParams
	 * @return
	 * @throws GlobalException
	 */
	Integer wechatPayAsyncNotic(Map<String, String> requestParams) throws GlobalException;


	/**
	*
	* Description: 确认收货
	*
	* Author: hxpeng
	* createTime: 2017/12/1
	*
	* @param: storeOrderDetailIds 订单详情集合列表
	* @param: storeOrderId 店铺订单ID
	* @param: memberId 当前操作用户
	**/
	void confirmReceipt(List<Long> storeOrderDetailIds, Long storeOrderId, Long memberId);

	/**
	 *
	 * Description: 延长收货(将收货时间向后推迟三天)
	 *
	 * Author: hxpeng
	 * createTime: 2017/12/1
	 *
	 * @param: storeOrderDetailIds 订单详情集合列表
	 * @param: storeOrderId 店铺订单ID
	 * @param: memberId 当前操作用户
	 **/
	void extendedGoodsReceipt(List<Long> storeOrderDetailIds, Long storeOrderId, Long memberId);


}
