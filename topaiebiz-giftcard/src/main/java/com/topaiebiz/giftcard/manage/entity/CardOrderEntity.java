package com.topaiebiz.giftcard.manage.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * 
 * Description 礼卡订单实体类
 * 
 * Author Murray.Li
 * 
 * Date 2017年8月26日 下午3:46:52
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_gif_giftcard_order")
public class CardOrderEntity extends BaseBizEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5245849194010542935L;

	/** 全局唯一主键标识符。本字段是业务无关性的，仅用于关联。 */
	@TableId("id")
	private Long id;

	/** 会员编号。 */
	@NotNull
	private Long memberId;

	/** 订单时间 */
	private Date orderTime;

	/** 订单状态。1、代付款，2、代发货，3、待收货，4、待评价,5 */
	@NotNull
	private Integer state;

	/** 总价。 */
	@NotNull
	private Double totalPrice;

	/** 数量。 */
	@NotNull
	private Long number;

	/** 是否需要发货 */
	private Integer needSend;

	/** 支付单据号。 */
	private Long payOrderId;

	/** 批删的ID集合 */
	@TableField(exist = false)
	private List<Long> list;

	/** 支付方式，默认为在线支付 */
	@TableField(exist = false)
	private String paymentWay;
	
	/**交易单号*/
	@TableField(exist = false)
	private String payCallbackNo;
	
	/**物流编号*/
	private String logisticsNo;
	
	/**物流类型*/
	private String logisticsType;

	/**
	 * Description 订单实体类所有属性的set,get方法。
	 * 
	 * Author Murray
	 * 
	 * return
	 */

	
	public Long getId() {
		return id;
	}

	public String getPayCallbackNo() {
		return payCallbackNo;
	}

	public void setPayCallbackNo(String payCallbackNo) {
		this.payCallbackNo = payCallbackNo;
	}

	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}

	public String getLogisticsType() {
		return logisticsType;
	}

	public void setLogisticsType(String logisticsType) {
		this.logisticsType = logisticsType;
	}

	public String getPaymentWay() {
		return paymentWay;
	}

	public void setPaymentWay(String paymentWay) {
		this.paymentWay = paymentWay;
	}

	public List<Long> getList() {
		return list;
	}

	public void setList(List<Long> list) {
		this.list = list;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Integer getNeedSend() {
		return needSend;
	}

	public void setNeedSend(Integer needSend) {
		this.needSend = needSend;
	}

	public Long getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(Long payOrderId) {
		this.payOrderId = payOrderId;
	}

	@Override
	public String toString() {
		return "CardOrder [id=" + id + ", memberId=" + memberId + ", orderTime=" + orderTime
				+ ", state=" + state + ", totalPrice=" + totalPrice + ", number=" + number + ", needSend=" + needSend
				+ ", payOrderId=" + payOrderId + "]";
	}

}
