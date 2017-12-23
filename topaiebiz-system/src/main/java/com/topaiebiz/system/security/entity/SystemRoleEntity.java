package com.topaiebiz.system.security.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * 描述：系统角色实体类，用于定义系统角色的成员属性。
 * 
 * @author Created by Amir Wang on 2017年10月7日。
 * 
 * @since 1.1.1
 */
@TableName(value = "t_sys_system_role")
public class SystemRoleEntity extends BaseBizEntity<Long> {

	/** 序列化版本号。 */
	private static final long serialVersionUID = 1932646107308528876L;

	/** 系统角色名称。 */
	private String name;

	/** 系统角色的父角色编号。 */
	private Long parentId;

	/** 系统角色的描述信息。 */
	private String description;

	/** 系统角色的内置标识。仅且仅有0和1两个值，1表示内置角色，0表示非内置角色，默认为0。 注意，内置角色不能被删除。 */
	private Byte inbuiltFlag = 0;

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
