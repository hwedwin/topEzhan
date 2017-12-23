package com.topaiebiz.transaction.order.merchant.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.topaiebiz.transaction.order.merchant.entity.OrderDetailsEntity;

/**
 * 
 * Description 平台商管理订单列表需要展示dto  
 * 
 * 
 * Author:zhushuyong 
 *    
 * Date 2017年9月2日 下午3:27:50 
 * 
 * Copyright:Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class OrderListDto{
	
	/** 订单的id*/
	@NotNull(message = "{6000201}")
	private Long id;
	
	/** 订单编号*/
	private Long orderId;
	
	/** 订单时间*/
	private Date orderTime;
	
	/** 订单状态*/
	private Integer orderState;
	
	/** 商品总运费*/
	private Double totalFreight;
	
	/** 优惠后金额（实际金额）*/
	private Double lastPrice;
	
	/** 下单会员名称*/
	private String userName;
	
	/** 订单金额*/
	private Double productTotal;
	
	/** 订单总金额*/
	private Double orderTotal;
	
	/** 所使用的营销活动。*/
	private Long usedPromotion;
	
	/** 店铺优惠金额*/
	private Double storeDeduction;
	
	/**平台优惠金额*/
	private Double platformDeduction;
	
	/** 配送方式（1：配送2：自提）*/
	private Short deliveryType;
	
	/** 订单备注。用于备注其他信息。*/
	private String memo;

	/** 店铺id*/
	private Long storeId;
	
	/** 店铺名称*/
	private String storeName;
	
	/** 收货人姓名*/
	private String consigneeName;
	
	/** 收货人手机号*/
	private String telephone;
	
	/** 收货人座机号*/
	private String landline;
	
	/** 发票类型。 1 普通 2电子 3增值税*/
	private Short invoiceType;
	
	/**会员主键ID*/
	private Long memberId;
    
	/** 发票抬头。*/
	private String title;
    
	/** 纳税人识别号。*/
	private String taxpayerNo;
    
	/** 发票内容。*/
	private String text;
    
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
	
	/**交易订单号*/
	private String payCallbackNo;
    
	/** 订单发票的状态。1 已开 2未开*/
	private Integer state;
    
	/** 订单发票的备注*/
	private String invoiceNemo;
	
	/**实际支付*/
	private Double actualPayMent;
	
	/**订单完成时间*/
	private Date lastModifiedTime;
	
	/**实际物流费用。*/
	private Double actualFreight;
	
	/**支付方式*/
	private String paymentWay;
	
	/**订单总运费*/
	private Double sumTotalFreight;
	
	/**商品小计*/
	private Double sumPrice;
	
	/**订单的总优惠金额*/
	private Double sumDeduction;
	
	/**商品名称*/
	private String goodsName;
	
	private Long goodsNum;
	
	private String goodsImage;
	
	private Double unitPrice;
	
	private Double salePrice;
	
	private String logisticsType;
	
	private String logisticsNo;
	
	private Long totalOrderNo;
	
	/**订单类型。1为平台订单， 2为店铺订单*/
	private Integer orderType;
	
	private String merchantName;
	
	/**订单中商品的总数*/
	private Long sumNum;
	
	
	public Long getSumNum() {
		return sumNum;
	}

	public void setSumNum(Long sumNum) {
		this.sumNum = sumNum;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public Long getTotalOrderNo() {
		return totalOrderNo;
	}

	public void setTotalOrderNo(Long totalOrderNo) {
		this.totalOrderNo = totalOrderNo;
	}

	public String getLogisticsType() {
		return logisticsType;
	}

	public void setLogisticsType(String logisticsType) {
		this.logisticsType = logisticsType;
	}

	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}

	/** 订单下面所含的订单详情*/
	private List<OrderDetailsEntity> orderDetail;
	
	
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Double getSumTotalFreight() {
		return sumTotalFreight;
	}

	public void setSumTotalFreight(Double sumTotalFreight) {
		this.sumTotalFreight = sumTotalFreight;
	}

	public Double getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}

	public Double getSumDeduction() {
		return sumDeduction;
	}

	public void setSumDeduction(Double sumDeduction) {
		this.sumDeduction = sumDeduction;
	}

	public String getPayCallbackNo() {
		return payCallbackNo;
	}

	public void setPayCallbackNo(String payCallbackNo) {
		this.payCallbackNo = payCallbackNo;
	}

	public String getPaymentWay() {
		return paymentWay;
	}

	public void setPaymentWay(String paymentWay) {
		this.paymentWay = paymentWay;
	}

	public Double getPlatformDeduction() {
		return platformDeduction;
	}

	public void setPlatformDeduction(Double platformDeduction) {
		this.platformDeduction = platformDeduction;
	}

	public Double getActualFreight() {
		return actualFreight;
	}

	public void setActualFreight(Double actualFreight) {
		this.actualFreight = actualFreight;
	}

	public Double getActualPayMent() {
		return actualPayMent;
	}

	public void setActualPayMent(Double actualPayMent) {
		this.actualPayMent = actualPayMent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId= orderId;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	public Double getProductTotal() {
		return productTotal;
	}

	public void setProductTotal(Double productTotal) {
		this.productTotal = productTotal;
	}

	public Double getStoreDeduction() {
		return storeDeduction;
	}

	public void setStoreDeduction(Double storeDeduction) {
		this.storeDeduction = storeDeduction;
	}

	public Double getTotalFreight() {
		return totalFreight;
	}

	public void setTotalFreight(Double totalFreight) {
		this.totalFreight = totalFreight;
	}

	public Double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public Short getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(Short deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
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

	public Long getUsedPromotion() {
		return usedPromotion;
	}

	public void setUsedPromotion(Long usedPromotion) {
		this.usedPromotion = usedPromotion;
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

	public String getInvoiceNemo() {
		return invoiceNemo;
	}

	public void setInvoiceNemo(String invoiceNemo) {
		this.invoiceNemo = invoiceNemo;
	}

	public List<OrderDetailsEntity> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<OrderDetailsEntity> orderDetail) {
		this.orderDetail = orderDetail;
	}

	public Long getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Long goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getGoodsImage() {
		return goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	
}
