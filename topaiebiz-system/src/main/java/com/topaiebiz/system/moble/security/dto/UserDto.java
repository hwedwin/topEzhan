package com.topaiebiz.system.moble.security.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class UserDto {

	/*** 显示用户名。 */
	@NotNull(message = "{validation.moble.security.userName}")
	@Length(min = 6, max = 20)
	private String userName;

	/*** 密码。 */
	@NotNull(message = "{validation.moble.security.password}")
	private String password;
	
	private Date currentTime;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}
	
	

}
