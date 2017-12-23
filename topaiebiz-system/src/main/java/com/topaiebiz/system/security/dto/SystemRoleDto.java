package com.topaiebiz.system.security.dto;

import javax.validation.constraints.NotNull;

/**
 * 描述：系统角色数据传输类，用于定义系统角色的数据传输成员属性。
 * 
 * @author Created by Amir Wang on 2017年10月7日。
 * 
 * @since 1.1.1
 */
public class SystemRoleDto {

	/** 系统角色编号。 */
	private Long id;

	/** 系统角色名称。 */
	@NotNull(message = "{nebula.system.security.systemRoleDto.name.notNull}")
	private String name;

	/** 系统角色的父角色编号。 */
	private Long parentId;

	/** 系统角色的父角色名称。 */
	private String parentName;

	/** 系统角色的描述信息。 */
	private String description;

	/** 系统角色的内置标识。仅且仅有0和1两个值，1表示内置角色，0表示非内置角色，默认为0。 注意，内置角色不能被删除。 */
	private Byte inbuiltFlag = 0;

	/**
	 * 描述：获取系统角色的编号。
	 * 
	 * @return 系统角色的编号。
	 * 
	 * @author Created by Amir Wang on 2017年10月7日。
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 描述：设置系统角色的编号。
	 * 
	 * @param id
	 *            系统角色的编号。
	 * 
	 * @author Created by Amir Wang on 2017年10月7日。
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 描述：获取系统角色名称。
	 * 
	 * @return 系统角色名称。
	 * 
	 * @author Created by Amir Wang on 2017年10月7日。
	 */
	public String getName() {
		return name;
	}

	/**
	 * 描述：设置系统角色名称。
	 * 
	 * @param name
	 *            系统角色名称。
	 * 
	 * @author Created by Amir Wang on 2017年10月7日。
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 描述：获取系统角色的父角色编号。
	 * 
	 * @return 系统角色的父角色编号。
	 * 
	 * @author Created by Amir Wang on 2017年10月7日。
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 描述：设置系统角色的父角色编号。
	 * 
	 * @param parentId
	 *            系统角色的父角色编号。
	 * 
	 * @author Created by Amir Wang on 2017年10月7日。
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 描述：获取系统角色的父角色名称。
	 * 
	 * @return 系统角色的父角色名称。
	 * 
	 * @author Created by Amir Wang on 2017年10月7日。
	 */
	public String getParentName() {
		return parentName;
	}

	/**
	 * 描述：设置系统角色的父角色名称。
	 * 
	 * @param parentName
	 *            系统角色的父角色名称。
	 * 
	 * @author Created by Amir Wang on 2017年10月7日。
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	/**
	 * 描述：获取系统角色的描述信息。
	 * 
	 * @return 系统角色的描述信息。
	 * 
	 * @author Created by Amir Wang on 2017年10月7日。
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 描述：设置系统角色的描述信息。
	 * 
	 * @param description
	 *            系统角色的描述信息。
	 * 
	 * @author Created by Amir Wang on 2017年10月7日。
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 描述：获取系统角色的内置标识。仅且仅有0和1两个值，1表示内置角色，0表示非内置角色，默认为0。 注意，内置角色不能被删除。
	 * 
	 * @return 系统角色的内置标识。
	 * 
	 * @author Created by Amir Wang on 2017年10月7日。
	 */
	public Byte getInbuiltFlag() {
		return inbuiltFlag;
	}

	/**
	 * 描述：设置系统角色的内置标识。仅接受0和1两个值，1表示内置角色，0表示非内置角色，默认为0。 注意，内置角色不能被删除。
	 * 
	 * @param inbuiltFlag
	 *            系统角色的内置标识。
	 * 
	 * @author Created by Amir Wang on 2017年10月7日。
	 */
	public void setInbuiltFlag(Byte inbuiltFlag) {
		this.inbuiltFlag = inbuiltFlag;
	}

}
