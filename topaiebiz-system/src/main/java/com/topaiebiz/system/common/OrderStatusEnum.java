package com.topaiebiz.system.common;

/**
 * Description 订单状态枚举类 
 * 
 * Author Aaron.Xue 
 *    
 * Date 2017年10月23日 下午3:45:37 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public enum OrderStatusEnum {

	/**
	 * 所有状态的订单（虚拟状态，不更新到表里去，仅用于业务判断）
	 */
	ALL_ORDER_STATE(0,"所有状态的订单"),

	/**
	 * 待支付
	 */
	CANCELLATION_OF_PAYMENT(1, "待支付"),

	/**
	 * 待发货
	 */
	PENDING_DELIVERY(2, "待发货"),

	/**
	 * 待收货
	 */
	PENDING_RECEIVED(3, "待收货"),

	/**
	 * 已收货
	 */
	HAVE_BEEN_RECEIVED(4, "已收货"),

	/**
	 * 订单取消
	 */
	ORDER_CANCELLATION(5, "订单取消"),

	/**
	 * 订单已完成
	 */
	ORDER_COMPLETION(6, "订单已完成"),

	/**
	 * 退款成功之后的订单状态
	 */
	ORDER_CLOSE(7, "订单已关闭"),

	/**
	 * 待评价（虚拟状态，不更新到表里去，仅用于业务判断）
	 */
	DELETE_ORDER(48, "删除订单"),
	/**
	 * 待评价（虚拟状态，不更新到表里去，仅用于业务判断）
	 */
	TO_BE_EVALUATED(49, "待评价"),

	/*
	========================================================================
	分割线========================================================================
	========================================================================
	 */

	/**
	 * 售后状态（虚拟状态，不更新到表里去，仅用于业务判断）
	 */
	CUSTOMER_SERVICE(50 ,"退货/退款"),


	/**
	 * 申请退款
	 */
	APPLY_FOR_RETURN_MONEY(51, "申请退款"),

	/**
	 * 申请退货
	 */
	APPLY_FOR_RETURN_GOODS(52, "申请退货"),

	/**
	 * 退款审核失败
	 */
	FAILURE_OF_RETURN_MONEY_AUDIT(53, "退款审核失败"),

	/**
	 * 退货审核失败
	 */
	FAILURE_OF_REFUND_GOODS_AUDIT(54, "退货审核失败"),

	/**
	 * 待寄回退换货
	 */
	RETURN_THE_REFUND_GOODS(55, "待寄回退换货"),

	/**
	 * 待签收退换货
	 */
	RECEIVE_THE_REFUND_GOODS(56, "待签收退换货"),

	/**
	 * 退款已取消
	 */
	REFUND_CANCELLATION(57, "退款已取消"),

	/**
	 * 退款已完成
	 */
	REFUND_COMPLETION(58, "退款已完成");


	/**
	 * 状态码
	 */
	private Integer code;
	
	/**
	 * 释意
	 */
	private String desc;

	OrderStatusEnum(Integer code, String desc) {
		this.code = code;
	}

	/**
	 * Description 获取状态码
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @return
	 */
	public Integer getCode() {
		return code;
	}


	/**
	 * Description 获取释意
	 * 
	 * Author Aaron.Xue   
	 * 
	 * @return
	 */
	public String getDesc() {
		return desc;
	}


	/**
	*
	* Description: 查看状态码是否在枚举中
	*
	* Author: hxpeng
	* createTime: 2017/12/1
	*
	* @param:
	**/
	public static boolean isCodeInEnums(Integer code){
		for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()){
			if(orderStatusEnum.getCode().equals(code)){
				return true;
			}
		}
		return false;
	}


	/**
	 *
	 * Description: 是否可以修改修改订单状态
	 *
	 * Author: hxpeng
	 * createTime: 2017/11/30
	 *
	 * @param:oldOrderState 订单此时状态
	 * @param:newOrderState 订单待修改的状态
	 **/
	public static boolean changeOrderStateFlag(Integer oldOrderState, Integer newOrderState){
		switch (newOrderState){
			case 2:
				return oldOrderState == 1;
			case 3:
				return oldOrderState == 2;
			case 4:
				return oldOrderState == 3;
			case 5:
				return oldOrderState == 1;
			case 6:
				return oldOrderState == 4 || oldOrderState == 53 || oldOrderState == 54 || oldOrderState == 57 || oldOrderState == 58;

			case 48:
				return oldOrderState == 5 || oldOrderState == 6 || oldOrderState == 7;
			case 51:
				return oldOrderState == 2 || oldOrderState == 3 || oldOrderState == 4;
			case 52:
				return oldOrderState == 3 || oldOrderState == 4;
			case 55:
				return oldOrderState == 52;
			case 56:
				return oldOrderState == 55;
			case 57:
				return oldOrderState == 51 || oldOrderState == 52 || oldOrderState == 55;
			case 58:
				return oldOrderState == 51 || oldOrderState == 56;
			default:
				return false;
		}
	}

	
	/**
	*
	* Description: 是否在售后中？
	*
	* Author: hxpeng
	* createTime: 2017/12/7
	*
	* @param: 
	**/
	public static boolean isInRefunding(Integer orderState){
		return orderState == APPLY_FOR_RETURN_MONEY.getCode() || orderState == APPLY_FOR_RETURN_GOODS.getCode()
				|| orderState == RETURN_THE_REFUND_GOODS.getCode() || orderState == RECEIVE_THE_REFUND_GOODS.getCode();
	}

	/**
	*
	* Description: 是否能过进行售后申请
	*
	* Author: hxpeng
	* createTime: 2017/12/8
	*
	* @param:
	**/
	public static boolean canPostRefundReq(Integer orderState){
		return orderState == FAILURE_OF_RETURN_MONEY_AUDIT.getCode() || orderState == FAILURE_OF_REFUND_GOODS_AUDIT.getCode()
				|| orderState == REFUND_CANCELLATION.getCode() || orderState == APPLY_FOR_RETURN_MONEY.getCode()
				|| orderState == APPLY_FOR_RETURN_GOODS.getCode();
	}

}
