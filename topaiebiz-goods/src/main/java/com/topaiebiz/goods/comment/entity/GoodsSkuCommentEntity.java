package com.topaiebiz.goods.comment.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

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
@TableName("t_goo_goods_sku_comment")
public class GoodsSkuCommentEntity extends BaseBizEntity<Long>{

	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = -7561251338983651707L;
	
	/** 商品属性表中的主键ID。 */
	private Long skuId;

	/** 评价会员ID（如果此项为空 ，则为匿名评价）。 */
	private Long memberId;

	/** 评价类型（1 好评 2 中评 3 差评）。 */
	private Integer type;

	/** 商品评价星级（1，2，3，4，5 颗心）。 */
	private Integer goodsLevel;
	
	/**物流服务星级（1，2，3，4，5 颗心）。*/
	private Integer logisticsLevel;
	
	/**服务态度星级（1，2，3，4，5 颗心）。*/
	private String serveLevel;

	/** 是否包含图片(1 有图 0 无图）。 */
	private Integer isImage;

	/** 评价内容。 */
	private String description;

	/** 相关订单号。 */
	private Long orderId;
	
	/** 回评内容。*/
	private String replyText;

	/** 回评时间。 */
	private Date replyTime;
	
	/** 追评内容。*/
	private String appendText;
	
	/** 追评时间。*/
	private Date appendTime;
	
	/** 追评回评内容。*/
	private String replyAppendText;
	
	/** 追评回评时间。*/
	private Date replyAppendTime;
	
	/** 备注。用于备注其他信息。 */
	private String memo;

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
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

	public String getAppendText() {
		return appendText;
	}

	public void setAppendText(String appendText) {
		this.appendText = appendText;
	}

	public Date getAppendTime() {
		return appendTime;
	}

	public void setAppendTime(Date appendTime) {
		this.appendTime = appendTime;
	}

	public String getReplyAppendText() {
		return replyAppendText;
	}

	public void setReplyAppendText(String replyAppendText) {
		this.replyAppendText = replyAppendText;
	}

	public Date getReplyAppendTime() {
		return replyAppendTime;
	}

	public void setReplyAppendTime(Date replyAppendTime) {
		this.replyAppendTime = replyAppendTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}