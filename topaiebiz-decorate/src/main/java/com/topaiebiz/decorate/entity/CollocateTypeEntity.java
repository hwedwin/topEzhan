package com.topaiebiz.decorate.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseEntity;

/**
 * Description 店铺装修配置类型实体类
 * 
 * Author Aaron.Xue
 * 
 * Date 2017年11月1日 下午11:32:45
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_dec_collocate_type")
public class CollocateTypeEntity extends BaseEntity<Long> {

	/**
	 * 版本序列号
	 */
	private static final long serialVersionUID = -6145175483645309008L;

	/** '店铺编号。' */
	private Long storeId;

	/** '所属模版。' */
	private Long templateId;

	/** '装饰类型。' */
	private String decType;

	/** '标题。' */
	private String title;

	/** 图片标图。 */
	private String iconImage;

	/** '备注。' */
	private String memo;

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public String getDecType() {
		return decType;
	}

	public void setDecType(String decType) {
		this.decType = decType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getIconImage() {
		return iconImage;
	}

	public void setIconImage(String iconImage) {
		this.iconImage = iconImage;
	}

}
