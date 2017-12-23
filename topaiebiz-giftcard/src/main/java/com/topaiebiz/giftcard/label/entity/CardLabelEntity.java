package com.topaiebiz.giftcard.label.entity;

import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * 
 * Description 美礼卡标签类
 * 
 * Author Murray
 * 
 * String 2017年8月25日 上午10:17:17
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_gif_giftcard_label")
public class CardLabelEntity extends BaseBizEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2821015254033626039L;

	/** 礼卡标签的唯一主键ID，非自增 */
	@TableId("id")
	private Long id;

	/** 礼卡标签名称 */
	private String name;

	/** 礼卡的样板图片 */
	private String image;

	/** 礼卡标签描述 */
	private String description;

	/** id串 */
	@TableField(exist = false)
	private List<Long> list;

	@TableField(exist = false)
	private String memo;

	/**
	 * 
	 * Description 实体类所有属性的构造方法
	 * 
	 * Author Murray
	 * 
	 * return
	 */

	public Long getId() {
		return id;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "礼卡标签： [id=" + id + ", name=" + name + ", image=" + image + ", description=" + description + "]";
	}

}
