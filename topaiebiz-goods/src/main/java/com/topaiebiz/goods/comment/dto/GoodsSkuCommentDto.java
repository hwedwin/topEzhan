package com.topaiebiz.goods.comment.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * Description 商品评价表，存储商品的评价信息 。
 * 
 * Author Hedda
 * 
 * Date 2017年8月23日 下午5:24:55
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class GoodsSkuCommentDto {

	/** 全局唯一主键标识符 （本字段是业务无关性的，仅用于关联）。 */
	private Long id;
	
	/** 商品属性表中的主键ID。 */
	@NotNull(message = "{validation.goodsSkuComment.skuId}")
	private Long skuId;
	
	/** 店铺名称。*/
	private String storeName;
	
	/** 所属店铺。*/
	private Long belongStore;
	
	/** 商品名称*/
	private String skuName;
	
	/** 商品图片。*/
	private String pictureName;

	/** 评价会员ID（如果此项为空 ，则为匿名评价）。 */
	private Long memberId;
	
	/** 判断是否为匿名评价。*/
	private Integer member;
	
	/** 会员名称。*/
	private String memberName;
	
	/** 会员头像。*/
	private String smallIcon;

	/** 评价类型（1 好评 2 中评 3 差评）。 */
	@NotNull(message = "{validation.goodsSkuComment.type}")
	private Integer type;

	/** 商品评价星级（1，2，3，4，5 颗心）。 */
	private Integer goodsLevel;
	
	/**物流服务星级（1，2，3，4，5 颗心）。*/
	private Integer logisticsLevel;
	
	/**服务态度星级（1，2，3，4，5 颗心）。*/
	private String serveLevel;

	/** 是否包含图片(1 有图 0 无图）。 */
	@NotNull(message = "{validation.goodsSkuComment.isImage}")
	private Integer isImage;

	/** 评价内容。 */
	@NotNull(message = "{validation.goodsSkuComment.description}")
	private String description;
	
	/**sku属性。*/
	private String saleFieldValue;

	/** 相关订单号。 */
	private Long orderId;
	
	/** 回评内容。*/
	private String replyText;
	
	/** 是否回复评价。*/
	private Integer noReply;

	/** 回评时间。 */
	private Date replyTime;
	
	/**开始时间*/
	private Date bTime;
	
	/**结束时间*/
	private Date eTime;
	
	/**开始时间*/
	private String beganTime;
	
	/**结束时间*/
	private String endTime;
	
	/** 创建时间。默认取值为系统的当前时间。 */
	private Date createdTime = new Date();
	
	/** 商品sku评价图片集合。*/
	private List<GoodsSkuCommentPictureDto> goodsSkuCommentPictureDtos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Long getBelongStore() {
		return belongStore;
	}

	public void setBelongStore(Long belongStore) {
		this.belongStore = belongStore;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	public Integer getMember() {
		return member;
	}

	public void setMember(Integer member) {
		this.member = member;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getSmallIcon() {
		return smallIcon;
	}

	public void setSmallIcon(String smallIcon) {
		this.smallIcon = smallIcon;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getGoodsLevel() {
		return goodsLevel;
	}

	public void setGoodsLevel(Integer goodsLevel) {
		this.goodsLevel = goodsLevel;
	}

	public Integer getLogisticsLevel() {
		return logisticsLevel;
	}

	public void setLogisticsLevel(Integer logisticsLevel) {
		this.logisticsLevel = logisticsLevel;
	}

	public String getServeLevel() {
		return serveLevel;
	}

	public void setServeLevel(String serveLevel) {
		this.serveLevel = serveLevel;
	}

	public Integer getIsImage() {
		return isImage;
	}

	public void setIsImage(Integer isImage) {
		this.isImage = isImage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSaleFieldValue() {
		return saleFieldValue;
	}

	public void setSaleFieldValue(String saleFieldValue) {
		this.saleFieldValue = saleFieldValue;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getReplyText() {
		return replyText;
	}

	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public Date getbTime() {
		return bTime;
	}

	public void setbTime(Date bTime) {
		this.bTime = bTime;
	}

	public Date geteTime() {
		return eTime;
	}

	public void seteTime(Date eTime) {
		this.eTime = eTime;
	}

	public String getBeganTime() {
		return beganTime;
	}

	public void setBeganTime(String beganTime) {
		this.beganTime = beganTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public Integer getNoReply() {
		return noReply;
	}

	public void setNoReply(Integer noReply) {
		this.noReply = noReply;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public List<GoodsSkuCommentPictureDto> getGoodsSkuCommentPictureDtos() {
		return goodsSkuCommentPictureDtos;
	}

	public void setGoodsSkuCommentPictureDtos(List<GoodsSkuCommentPictureDto> goodsSkuCommentPictureDtos) {
		this.goodsSkuCommentPictureDtos = goodsSkuCommentPictureDtos;
	}
	
}
