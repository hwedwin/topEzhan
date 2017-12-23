package com.topaiebiz.goods.brand.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseEntity;

/**
 * Description 商品适用年龄段 。 
 * 
 * Author Hedda 
 *    
 * Date 2017年10月6日 下午1:37:42 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
@TableName("t_goo_suitable_age")
public class SuitableAgeEntity extends BaseEntity<Long>{

	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = -1320643017815521466L;
	
	/** 年龄段。*/
	private String ageGroup;
	
	/** 图片。*/
	private String ageImage;
	
	/** 备注。*/
	private String memo;
	
	/** 创建人编号。取值为创建人的全局唯一主键标识符。 */
	private Long creatorId;

	/** 最后修改人编号。取值为最后修改人的全局唯一主键标识符。 */
	private Long lastModifierId;

	/** 最后修改时间。默认取值为null，当发生修改时取系统的当前时间。 */
	private Date lastModifiedTime;

	public String getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Long getLastModifierId() {
		return lastModifierId;
	}

	public void setLastModifierId(Long lastModifierId) {
		this.lastModifierId = lastModifierId;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public String getAgeImage() {
		return ageImage;
	}

	public void setAgeImage(String ageImage) {
		this.ageImage = ageImage;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
