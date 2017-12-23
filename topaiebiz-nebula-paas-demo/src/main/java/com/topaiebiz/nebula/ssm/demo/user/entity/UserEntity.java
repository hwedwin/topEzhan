package com.topaiebiz.nebula.ssm.demo.user.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

@TableName("t_user")
public class UserEntity extends BaseBizEntity<Long> {

	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = 2614827844718547186L;

	/** 用户的名称。 */
	private String userName;

	/** 用户的年龄。 */
	private Short userAge;

	public UserEntity() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Short getUserAge() {
		return userAge;
	}

	public void setUserAge(Short userAge) {
		this.userAge = userAge;
	}

}
