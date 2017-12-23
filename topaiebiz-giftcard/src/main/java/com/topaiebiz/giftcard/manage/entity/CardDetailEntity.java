package com.topaiebiz.giftcard.manage.entity;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description 订单详情实体类。
 * 
 * Author Murray.Li
 * 
 * Date 2017年8月26日 下午3:58:28
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_gif_giftcard_detail")
public class CardDetailEntity extends BaseBizEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7090035565854133724L;

	/** 全局唯一主键标识符。本字段是业务无关性的，仅用于关联。 */
	private Long id;

	/** 所属礼卡 */
	private Long cardId;

	/** 礼卡编号 */
	private String cardNo;

	/** 订单编号 */
	private Long orderId;

	/** 激活密码 */
	private String password;

	/** 可激活时间,虚拟卡一般为出售完后自动生成，实体卡一般为货到付款后自动升成 */
	private Date expirationTime;

	/** 手机号，顾客输入，找回密码用的 */
	private String telephone;

	/** 下载密码,顾客买卡时输入的，用于下载卡号和密码 */
	private String downloadPassword;

	/** 使用订单号。购买商品后回显记录的字段。 */
	private String usedOrder;

	/** 是否为本人使用 */
	private Integer selfState;

	/** 激活会员 */
	private Long memberId;

	/** 激活时间 */
	private Date activeTime;

	/** 关联主卡ID */
	private Long mainCardId;

	/** 礼卡余额 */
	private Double balance;

	/** 卡面值 */
	private Double value;

	/** 卡售价 */
	private Double price;

	/** 美礼卡状态。1 新建 2已销售 3已激活。 */
	private Integer cardState;

	/** 批删的ID串 */
	@TableField(exist = false)
	private List<Long> list;

	/**
	 * Description 订单详情实体类所有属性的set,get方法。
	 * 
	 * Author Murray
	 * 
	 * return
	 */

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public String getUsedOrder() {
		return usedOrder;
	}

	public void setUsedOrder(String usedOrder) {
		this.usedOrder = usedOrder;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public List<Long> getList() {
		return list;
	}

	public void setList(List<Long> list) {
		this.list = list;
	}

	public Long getMainCardId() {
		return mainCardId;
	}

	public void setMainCardId(Long mainCardId) {
		this.mainCardId = mainCardId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCardId() {
		return cardId;
	}

	public Integer getCardState() {
		return cardState;
	}

	public void setCardState(Integer cardState) {
		this.cardState = cardState;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDownloadPassword() {
		return downloadPassword;
	}

	public void setDownloadPassword(String downloadPassword) {
		this.downloadPassword = downloadPassword;
	}

	public Integer getSelfState() {
		return selfState;
	}

	public void setSelfState(Integer selfState) {
		this.selfState = selfState;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Date getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CardDetail [id=" + id + ", cardId=" + cardId + ", cardNo=" + cardNo + ", password=" + password
				+ ", expirationTime=" + expirationTime + ", telephone=" + telephone + ", downloadPassword="
				+ downloadPassword + ", selfState=" + selfState + ", memeberId=" + memberId + ", activeTime="
				+ activeTime + ", value=" + value + ", price=" + price + "]";
	}

}
