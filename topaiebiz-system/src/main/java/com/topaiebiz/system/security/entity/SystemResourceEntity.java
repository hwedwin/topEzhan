package com.topaiebiz.system.security.entity;

import java.util.List;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * 描述：系统资源实体类。
 * 
 * @author Created by Amir Wang on 2017年10月7日。
 * 
 * @since 1.1.1
 */
@TableName(value = "t_sys_system_resource")
public class SystemResourceEntity extends BaseBizEntity<Long> {

	/**
	 * 版本序列号
	 */
	private static final long serialVersionUID = -111273957111661185L;

	private Long id;

	/** '系统资源类型（1为菜单 2 为 按钮）。 */
	private String resourceType;

	/** '系统资源名称（例如会员管理、添加）。' */
	private String name;

	/** '父资源ID（一级菜单则为0）' */
	private Long parentId;

	/** '系统资源排序号。' */
	private String sortNumber;

	/** '需要访问该资源的url。' */
	private String accessUrl;

	/** '点击该资源跳转的url。 */
	private String jumpUrl;

	/** '图标图片。' */
	private String iconImage;

	/** '系统资源描述。' */
	private String description;

	/** '备注' */
	private String memo;

	/**子资源。*/
	private List<SystemResourceEntity> childList;

	public List<SystemResourceEntity> getChildList() {
		return childList;
	}

	public void setChildList(List<SystemResourceEntity> childList) {
		this.childList = childList;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(String sortNumber) {
		this.sortNumber = sortNumber;
	}

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

	public String getIconImage() {
		return iconImage;
	}

	public void setIconImage(String iconImage) {
		this.iconImage = iconImage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SystemResourceEntity))
			return false;
		SystemResourceEntity other = (SystemResourceEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
