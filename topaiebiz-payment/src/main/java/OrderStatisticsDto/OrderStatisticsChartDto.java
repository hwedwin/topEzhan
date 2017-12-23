package OrderStatisticsDto;

import java.util.Date;
import java.util.List;

/**
 * 
 * Description： 交易分析 图表结果集合类，以天为单位。
 * 
 * 
 * Author hxpeng 
 *    
 * Date 2017年11月2日 下午1:26:21 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class OrderStatisticsChartDto {
	/**
	 * 当天日期
	 */
	private Date currentDate;
	
	/**
	 * 下单人数
	 */
	private int orderPeopleNumber;
	
	/**
	 * 订单数
	 */
	private int orderNumber;
	
	/**
	 * 下单件数
	 */
	private int orderProductNumber;
	
	/**
	 * 下单金额
	 */
	private double orderTotalPrice;
	
	/**
	 * 退款金额
	 */
	private double orderRefundPrice;
	
	/**
	 * 付款人数
	 */
	private int payPeopleNumber;
	
	/**
	 * 付款订单数
	 */
	private int payOrderNumber;
	
	/**
	 * 付款件数
	 */
	private int alreadyPayOrderNumber;
	
	/**
	 * 付款总金额
	 */
	private double payTotalPrice;
	
	/**
	 * 下单转化率 
	 */
	private String toOrderTransformationRate;
	
	/**
	 * 付款转化率
	 */
	private String toPayTransformationRate;
	
	/**
	 * 成交转化率
	 */
	private String toDealTransformationRate;
	
	/**
	 * 会员ID集合 ，用于统计人数
	 */
	private List<String> memberIds;

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public int getOrderPeopleNumber() {
		return orderPeopleNumber;
	}

	public void setOrderPeopleNumber(int orderPeopleNumber) {
		this.orderPeopleNumber = orderPeopleNumber;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getOrderProductNumber() {
		return orderProductNumber;
	}

	public void setOrderProductNumber(int orderProductNumber) {
		this.orderProductNumber = orderProductNumber;
	}

	public double getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public double getOrderRefundPrice() {
		return orderRefundPrice;
	}

	public void setOrderRefundPrice(double orderRefundPrice) {
		this.orderRefundPrice = orderRefundPrice;
	}

	public int getPayPeopleNumber() {
		return payPeopleNumber;
	}

	public void setPayPeopleNumber(int payPeopleNumber) {
		this.payPeopleNumber = payPeopleNumber;
	}

	public int getPayOrderNumber() {
		return payOrderNumber;
	}

	public void setPayOrderNumber(int payOrderNumber) {
		this.payOrderNumber = payOrderNumber;
	}

	public int getAlreadyPayOrderNumber() {
		return alreadyPayOrderNumber;
	}

	public void setAlreadyPayOrderNumber(int alreadyPayOrderNumber) {
		this.alreadyPayOrderNumber = alreadyPayOrderNumber;
	}

	public double getPayTotalPrice() {
		return payTotalPrice;
	}

	public void setPayTotalPrice(double payTotalPrice) {
		this.payTotalPrice = payTotalPrice;
	}

	public String getToOrderTransformationRate() {
		return toOrderTransformationRate;
	}

	public void setToOrderTransformationRate(String toOrderTransformationRate) {
		this.toOrderTransformationRate = toOrderTransformationRate;
	}

	public String getToPayTransformationRate() {
		return toPayTransformationRate;
	}

	public void setToPayTransformationRate(String toPayTransformationRate) {
		this.toPayTransformationRate = toPayTransformationRate;
	}

	public String getToDealTransformationRate() {
		return toDealTransformationRate;
	}

	public void setToDealTransformationRate(String toDealTransformationRate) {
		this.toDealTransformationRate = toDealTransformationRate;
	}

	public List<String> getMemberIds() {
		return memberIds;
	}

	public void setMemberIds(List<String> memberIds) {
		this.memberIds = memberIds;
	}
	
}
