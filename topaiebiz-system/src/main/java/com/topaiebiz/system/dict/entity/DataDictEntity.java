package com.topaiebiz.system.dict.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description: 数据字典信息实体类
 * 
 * Author : Anthony
 * 
 * Date :2017年9月25日 下午4:54:30
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_sys_data_dict")
public class DataDictEntity extends BaseBizEntity<Long> {

	/** 序列化版本号。 */
	private static final long serialVersionUID = -3219238919951987482L;

	/** 数据字典类型 。 */
	private Long typeId;

	/** 数据字典的编码 。 */
	private String dictCode;

	/** 数据字典的值。 */
	private String dictValue;

	/** 说明 */
	private String memo;

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
