package com.topaiebiz.nebula.ssm.demo.user.dto;

import javax.validation.constraints.NotNull;

public class UserDto {

	/** 用户的名称。 */
	@NotNull(message = "{validation.user.userName}")
	private String userName;

	/** 用户的年龄。 */
	private Short userAge;

	private String memo;

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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
