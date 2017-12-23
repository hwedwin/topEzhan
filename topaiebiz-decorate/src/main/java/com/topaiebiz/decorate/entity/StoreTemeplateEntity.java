package com.topaiebiz.decorate.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description 店铺选用装修模版实体类
 * 
 * Author Aaron.Xue
 * 
 * Date 2017年11月1日 下午11:34:10
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_dec_store_temeplate")
public class StoreTemeplateEntity extends BaseBizEntity<Long> {

	/**
	 * 版本序列号
	 */
	private static final long serialVersionUID = 5785105402997856768L;

	/** 店铺ID。 */
	private Long storeId;

	/** '模版ID。' */
	private Long templateId;

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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
