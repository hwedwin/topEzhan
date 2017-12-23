package com.topaiebiz.transaction.order.merchant.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * 
 * Description 订单发票信息表，存储订单需要开具的发票信息实体类  
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月4日 上午11:09:56 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_tsa_order_invoice")
public class OrderInvoiceEntity extends BaseBizEntity<Long> {

	/** 版本化序列号*/
	@TableField(exist = false)
	private static final long serialVersionUID = -7712525922205332738L;
	
	/** 订单id*/
	private Long orderId;
	
	/** 店铺id*/
	private Long storeId;
	
	/** 发票类型。 1 普通 2电子 3增值税*/
	private Short invoiceType;
    
	/** 发票抬头。*/
	private String title;
    
	/** 发票内容。*/
	private String text;
	
	/** 纳税人识别号。*/
	private String taxpayerNo;
    
	/** 增值税发票专用。1 订单完成后开票*/
	private Short modeType;
    
	/** 增值税发票专用。*/
	private String name;
    
	/** 开票金额。*/
	private Double sum;
    
	/** 地址电话。*/
	private String addressTel;
	
	/** 开户行及账号。*/
	private String account;
    
	/** 状态。1 已开 2未开*/
	private Integer state;

	/** 电子发票路径。 */
	private String invoiceImage;

	/** 发票代码。 */
	private String invoiceCode;

	/** 发票号码。 */
	private String invoiceNum;

	/**配送地址的主键ID*/
	@TableField(exist=false)
	private Long addressId;
	
	/**礼卡主键ID*/
	@TableField(exist=false)
	private Long cardId;
	
	/**礼卡数量*/
	@TableField(exist=false)
	private Long number;
	
	/**token令牌*/
	@TableField(exist=false)
	private String token;
	
	
	
	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getInvoiceImage() {
		return invoiceImage;
	}

	public void setInvoiceImage(String invoiceImage) {
		this.invoiceImage = invoiceImage;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getInvoiceNum() {
		return invoiceNum;
	}

	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}

	public OrderInvoiceEntity() {
		super();
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Short getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(Short invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTaxpayerNo() {
		return taxpayerNo;
	}

	public void setTaxpayerNo(String taxpayerNo) {
		this.taxpayerNo = taxpayerNo;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Short getModeType() {
		return modeType;
	}

	public void setModeType(Short modeType) {
		this.modeType = modeType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public String getAddressTel() {
		return addressTel;
	}

	public void setAddressTel(String addressTel) {
		this.addressTel = addressTel;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
