package com.topaiebiz.distribution.merchant.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description： 商品分销实体类。
 * 
 * Author Harry
 * 
 * Date 2017年10月3日 下午3:10:53
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_dis_goods_distribution_allocation")
public class GoodsDistributionEntity extends BaseBizEntity<Long> implements Serializable {

	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = -7979536011456477819L;

	/** 商品SPU主键 */
	private Long skuId;

	/** 店铺等级 */
	private String storeGradeId;

	/** 分润比例 */
	private Double ratio;

	/** 开始时间 */
	private Date startTime;

	/** 截止时间 */
	private Date endTime;

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getStoreGradeId() {
		return storeGradeId;
	}

	public void setStoreGradeId(String storeGradeId) {
		this.storeGradeId = storeGradeId;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
