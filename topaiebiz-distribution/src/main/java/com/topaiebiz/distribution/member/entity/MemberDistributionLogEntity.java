package com.topaiebiz.distribution.member.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description： 会员分销记录实体类。
 * 
 * Author Harry
 * 
 * Date 2017年10月7日 下午7:28:28
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_dis_member_distribution_log")
public class MemberDistributionLogEntity extends BaseBizEntity<Long> implements Serializable {

	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = 911274299971363123L;

	/** 所属店铺ID */
	private Long storeId;

	/** 会员编号 */
	private Long memberId;

	/** 商品SKU */
	private Long skuId;

	/** 分期日期 */
	private Date distriDate;

	/** 分销级别 */
	private String  distriLevel;

	/** 分销比例 */
	private Double ratio;

	/** 下级会员购买人数 */
	private Integer underlingMemberNum;

	/** 购买商品数 */
	private Long goodsNum;

	/** 总金额 */
	private Double totalPrice;

	/** 分销金额 */
	private Double distriPrice;

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Date getDistriDate() {
		return distriDate;
	}

	public void setDistriDate(Date distriDate) {
		this.distriDate = distriDate;
	}

	public String getDistriLevel() {
		return distriLevel;
	}

	public void setDistriLevel(String distriLevel) {
		this.distriLevel = distriLevel;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	public Integer getUnderlingMemberNum() {
		return underlingMemberNum;
	}

	public void setUnderlingMemberNum(Integer underlingMemberNum) {
		this.underlingMemberNum = underlingMemberNum;
	}

	public Long getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Long goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getDistriPrice() {
		return distriPrice;
	}

	public void setDistriPrice(Double distriPrice) {
		this.distriPrice = distriPrice;
	}

}
