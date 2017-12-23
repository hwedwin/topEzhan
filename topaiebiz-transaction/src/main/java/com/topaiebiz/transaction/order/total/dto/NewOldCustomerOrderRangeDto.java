/**
 * 
 */
package com.topaiebiz.transaction.order.total.dto;

/**  
 * Description： 新老顾客订单分析DTO
 * 
 * 
 * Author hxpeng 
 *    
 * Date 2017年10月30日 下午4:30:12 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

public class NewOldCustomerOrderRangeDto {

	/**
	 * 新客户数量
	 */
	private int newCustomerNumber;

	/**
	 * 新客户付款金额总额
	 */
	private double newCustomerTotalPrice;
	
	/**
	 * 老客户数量
	 */
	private int oldCustomerNumber;
	
	/**
	 * 老客户付款金额总额
	 */
	private double oldCustomerTotalPrice;
	
	/**
	 * 新会员付款人数
	 */
	private int numberOfNewCustomerPayment;

	/**
	 * 老会员付款人数
	 */
	private int numberOfOldCustomerPayment;
	
	/**
	 * 新客户付款金额总额比上次---比率
	 */
	private String newCustomerPayCompareLastTime;
	
	/**
	 * 新客户数量比上次---比率
	 */
	private String newCustomerNumberCompareLastTime;
	
	/**
	 * 老客户付款金额总额比上次---比率
	 */
	private String oldCustomerPayCompareLastTime;
	
	/**
	 * 老客户人数比上次---比率
	 */
	private String oldCustomerNumberCompareLastTime;

	/**
	 * 新客户付款人数上次---比率
	 */
	private String numberOfNewCustomerPaymentCompareLastTime;
	
	/**
	 * 老客户付款人数上次---比率
	 */
	private String numberOfOldCustomerPaymentCompareLastTime;

	public int getNewCustomerNumber() {
		return newCustomerNumber;
	}

	public void setNewCustomerNumber(int newCustomerNumber) {
		this.newCustomerNumber = newCustomerNumber;
	}

	public double getNewCustomerTotalPrice() {
		return newCustomerTotalPrice;
	}

	public void setNewCustomerTotalPrice(double newCustomerTotalPrice) {
		this.newCustomerTotalPrice = newCustomerTotalPrice;
	}

	public int getOldCustomerNumber() {
		return oldCustomerNumber;
	}

	public void setOldCustomerNumber(int oldCustomerNumber) {
		this.oldCustomerNumber = oldCustomerNumber;
	}

	public double getOldCustomerTotalPrice() {
		return oldCustomerTotalPrice;
	}

	public void setOldCustomerTotalPrice(double oldCustomerTotalPrice) {
		this.oldCustomerTotalPrice = oldCustomerTotalPrice;
	}

	public int getNumberOfNewCustomerPayment() {
		return numberOfNewCustomerPayment;
	}

	public void setNumberOfNewCustomerPayment(int numberOfNewCustomerPayment) {
		this.numberOfNewCustomerPayment = numberOfNewCustomerPayment;
	}

	public int getNumberOfOldCustomerPayment() {
		return numberOfOldCustomerPayment;
	}

	public void setNumberOfOldCustomerPayment(int numberOfOldCustomerPayment) {
		this.numberOfOldCustomerPayment = numberOfOldCustomerPayment;
	}

	public String getNewCustomerPayCompareLastTime() {
		return newCustomerPayCompareLastTime;
	}

	public void setNewCustomerPayCompareLastTime(String newCustomerPayCompareLastTime) {
		this.newCustomerPayCompareLastTime = newCustomerPayCompareLastTime;
	}

	public String getNewCustomerNumberCompareLastTime() {
		return newCustomerNumberCompareLastTime;
	}

	public void setNewCustomerNumberCompareLastTime(String newCustomerNumberCompareLastTime) {
		this.newCustomerNumberCompareLastTime = newCustomerNumberCompareLastTime;
	}

	public String getOldCustomerPayCompareLastTime() {
		return oldCustomerPayCompareLastTime;
	}

	public void setOldCustomerPayCompareLastTime(String oldCustomerPayCompareLastTime) {
		this.oldCustomerPayCompareLastTime = oldCustomerPayCompareLastTime;
	}

	public String getOldCustomerNumberCompareLastTime() {
		return oldCustomerNumberCompareLastTime;
	}

	public void setOldCustomerNumberCompareLastTime(String oldCustomerNumberCompareLastTime) {
		this.oldCustomerNumberCompareLastTime = oldCustomerNumberCompareLastTime;
	}

	public String getNumberOfNewCustomerPaymentCompareLastTime() {
		return numberOfNewCustomerPaymentCompareLastTime;
	}

	public void setNumberOfNewCustomerPaymentCompareLastTime(String numberOfNewCustomerPaymentCompareLastTime) {
		this.numberOfNewCustomerPaymentCompareLastTime = numberOfNewCustomerPaymentCompareLastTime;
	}

	public String getNumberOfOldCustomerPaymentCompareLastTime() {
		return numberOfOldCustomerPaymentCompareLastTime;
	}

	public void setNumberOfOldCustomerPaymentCompareLastTime(String numberOfOldCustomerPaymentCompareLastTime) {
		this.numberOfOldCustomerPaymentCompareLastTime = numberOfOldCustomerPaymentCompareLastTime;
	}
	
}
