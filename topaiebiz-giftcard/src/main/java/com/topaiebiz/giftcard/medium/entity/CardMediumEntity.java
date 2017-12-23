package com.topaiebiz.giftcard.medium.entity;

import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * 
 * Description 描述礼卡介质的类
 * 
 * 
 * Author Murray
 * 
 * String 2017年8月25日 上午10:31:51
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_gif_giftcard_medium")
public class CardMediumEntity extends BaseBizEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 884617976014500832L;

	/** 礼卡介质的唯一标示，非自增 */
	@TableId("id")
	private Long id;

	/** 礼卡介质的名称 */
	private String name; 
    
	/** 礼卡介质的描述 */
	private String description;
    
	@TableField(exist=false)
	private String memo;
	
	/**批删的ID串*/
	@TableField(exist=false) 
	private List<Long> list;
	/**
	 * 
	 * Description 礼卡介质类所有属性的构造方法
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "礼卡介质： [id=" + id + ", name=" + name + ", description=" + description + 
				 "]";
	}

}
