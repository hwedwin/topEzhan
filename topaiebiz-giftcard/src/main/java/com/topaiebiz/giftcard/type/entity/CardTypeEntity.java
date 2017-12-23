package com.topaiebiz.giftcard.type.entity;

import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * 
 * Description 礼卡经营类型的类
 * 
 * Author Murray.Li
 * 
 * Date 2017年8月25日 上午10:43:55
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_gif_giftcard_type")
public class CardTypeEntity extends BaseBizEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1870442064448786210L;

	/** 礼卡经营类型的唯一标示，非自增 */
	@TableId("id")
	private Long id;

	/** 礼卡经营类型的名称 */
	private String name;

	/** 礼卡经营类型的描述 */
	private String description;

	/** 类型的编码 */
	private String typeCode;

	@TableField(exist = false)
	private String memo;

	/** 批删的ID串 */
	@TableField(exist = false)
	private List<Long> list;

	/**
	 * 
	 * Description 礼卡类型的所有属性的构造方法
	 * 
	 * Author Murray
	 * 
	 * return
	 */

	public Long getId() {
		return id;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public List<Long> getList() {
		return list;
	}

	public void setList(List<Long> list) {
		this.list = list;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "礼卡类型： [id=" + id + ", name=" + name + ", description=" + description + ", creatorId=" + "]";
	}

}
