package com.topaiebiz.distribution.member.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description： 会员分销实体类。
 * 
 * Author Harry
 * 
 * Date 2017年10月5日 下午3:40:32
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_dis_member_distribution_allocation")
public class MemberDistributionAllocationEntity extends BaseBizEntity<Long> implements Serializable {

	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = -1855039830202666316L;

	/** 所属店铺 */
	private Long storeId;

	/** 分销级别 */
	private String distriLevel;

	/** 商品skuId */
	private Long skuId;

	/** 分销比例 */
	private Double ratio;

	/** 开始时间 */
	private Date startTime;

	/** 结束时间 */
	private Date endTime;

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
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

	public String getDistriLevel() {
		return distriLevel;
	}

	public void setDistriLevel(String distriLevel) {
		this.distriLevel = distriLevel;
	}

}
