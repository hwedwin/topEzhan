package com.topaiebiz.payment.order.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.topaiebiz.payment.order.dto.RefundPriceParamsDto;
import com.topaiebiz.payment.order.dto.RefundPriceResultDto;
import com.topaiebiz.transaction.refundOrder.dto.APPRefundOrderListDto;
import com.topaiebiz.transaction.refundOrder.dto.RefundDto;
import com.topaiebiz.transaction.refundOrder.dto.RefundOrderDto;

import java.util.List;

/**
 * 
 * Description 退货订单
 * 
 * 
 * Author Joe
 * 
 * Date 2017年10月17日 下午1:58:37
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public interface RefundOrderService {

	/**
	*
	* Description: 获取售后订单详情
	*
	* Author: hxpeng
	* createTime: 2017/12/7
	*
	* @param:
	**/
	RefundOrderDto getRefundOrderInfo(Long refundOrderId);

	/**
	 *
	 * Description: APP：获取退款/退货列表
	 *
	 * Author: hxpeng
	 * createTime: 2017/11/29
	 *
	 * @param: orderStateArray 订单状态类型
	 **/
	Page<APPRefundOrderListDto> getRefundOrderListInAPP(Page<APPRefundOrderListDto> page, Long memberId);

	/**
	*
	* Description: APP：计算退货退款金额
	*
	* Author: hxpeng
	* createTime: 2017/11/23
	*
	* @param: refundPriceParamsDto
	* @param: confimRefund 是否为提交退货/退款 申请
	**/
	RefundPriceResultDto calculationRefundPrice(RefundPriceParamsDto refundPriceParamsDto, boolean confimRefund);

	/**
	 *
	 * Description: APP：提交退货/退款请求
	 *
	 * Author: hxpeng
	 * createTime: 2017/11/23
	 *
	 * @param: refundPriceParamsDto
	 * @param: confimRefund 是否为提交退货/退款 申请
	 **/
	void applyRefund(RefundPriceParamsDto refundPriceParamsDto);

	/**
	*
	* Description: 撤销售后申请
	*
	* Author: hxpeng
	* createTime: 2017/12/7
	*
	* @param:
	**/
	void cancelRefund(Long refundOrderId, Long memberId);

	/**
	*
	* Description: PC：审核退款/退货的申请
	*
	* Author: hxpeng
	* createTime: 2017/12/1
	*
	* @param: refundOrderId 退款订单
	* @param: isAuditPass 是否通过
	**/
	void auditingRefundApply(Long refundOrderId, Boolean isAuditPass);

	/**
	*
	* Description: 修改售后申请
	*
	* Author: hxpeng
	* createTime: 2017/12/7
	*
	* @param: 
	**/
	void updateRefundOrder(RefundPriceParamsDto refundPriceParamsDto);
	
	/**
	 *
	 * Description: APP：同意退货：-->寄回商品
	 *
	 * Author: hxpeng
	 * createTime: 2017/12/1
	 *
	 * @param: refundOrderDetailIds 寄回的退货商品详情集合IDS
	 * @param: refundOrderId 退货订单ID
	 * @param: logisticsType 物流公司
	 * @param: logisticsNo 物流编号
	 * @param: memberId 用户ID
	 **/
	void sendBackRefundGoods(List<Long> refundOrderDetailIds, Long refundOrderId, String logisticsType, String logisticsNo, Long currentMemberId);

	/**
	*
	* Description: 审核收到的退换货
	*
	* Author: hxpeng
	* createTime: 2017/12/2
	*
	* @param:
	**/
	void auditingRefundGoods(Long refundOrderId, Boolean isAuditPass);

	/**
	 * 
	 * Description 退货订单列表
	 * 
	 * Author Joe
	 * 
	 * @param page
	 * @param refundOrderDto
	 * @return
	 */
	Page<RefundOrderDto> getRefundOrderList(Page<RefundOrderDto> page, RefundOrderDto refundOrderDto);

	/**
	 * 
	 * Description 详情/处理退货订单
	 * 
	 * Author Joe
	 * 
	 * @param id
	 * @return
	 */
	RefundDto getRefundOrder(Long id);

	/**
	 * 
	 * Description 删除退货订单
	 * 
	 * Author Joe
	 * 
	 * @param ids
	 */
	void removeRefundOrder(Long[] ids);

}
