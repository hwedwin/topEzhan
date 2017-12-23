package com.topaiebiz.giftcard.type.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;


/**
 * 
 * Description： 添加cardMedia，cardType时的 dto类。
 * 
 * 
 * Author Murray.Li
 *    
 * Date 2017年9月23日 下午7:14:53 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class CardTypeDto{

	private Long id;
	
	@NotNull(message = "{validation.save.name}")  
	private String name;
	
	private String description;
	
	private String image;
	
	private Long creatorId;
	
	private Date lastModifiedTime;
	
	private Long lastModifierId;
	
	private Date createdTime;
	
    private List<Long>list;
    
    
	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public Long getLastModifierId() {
		return lastModifierId;
	}

	public void setLastModifierId(Long lastModifierId) {
		this.lastModifierId = lastModifierId;
	}

	public List<Long> getList() {
		return list;
	}

	public void setList(List<Long> list) {
		this.list = list;
	}

	public Long getId() {
		return id;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	

	
}
