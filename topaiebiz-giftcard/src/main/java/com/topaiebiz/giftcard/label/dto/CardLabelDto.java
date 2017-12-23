package com.topaiebiz.giftcard.label.dto;

import java.util.Date;

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
 * Copyright:Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class CardLabelDto {

	/** 礼卡标签的主键ID */
	private Long id;

	/** 礼卡标签的名称 */
	@NotNull(message = "{validation.save.name}")
	private String name;

	/** 礼卡标签的描述 */
	private String description;

	/** 礼卡标签的图片（路径名称） */
	private String image;

	/** 创建人编号 */
	private Long creatorId;

	private Date createdTime;
	
	/** 礼卡信息最后一次修改的时间 */
	private Date lastModifiedTime;

	/** 礼卡最后一次修改的人的编号 */
	private Long lastModifierId;
	
	

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

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

}
