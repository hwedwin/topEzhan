package com.topaiebiz.system.security.dto;

import java.util.Date;


public class SecurityMemberDto {

	private Long id;

	/*** 会员信息的全局唯一主键标识符。本字段是业务无关性的，仅用于关联。 */
	private String memberCode;

	/*** 会员类型。 */
	private Long typeId;

	/*** 会员等级。 */
	private Long gradeId;

	/** 所属商家。 */
	private Long merchantId;

	/*** 所属店铺。 */
	private Long storeId;

	/*** 与店铺的绑定状态（1 绑定，0 解绑）。 */
	private Integer bindingState;

	/*** 显示用户名。 */
	private String userName;

	/*** 密码。 */
	private String password;

	/*** 昵称。 */
	private String nickName;

	/*** 用户邮箱。 */
	private String post;

	/*** 会员真实姓名。 */
	private String realName;

	/*** 支付密码 */
	private String payPassword;

	/*** 会员的身份证。。 */
	private String idCard;

	/*** 性别（1 南 0 女)。 */
	private Integer gender;

	/*** 生日。 */
	private String birthday;

	/*** 所在地区编码。 */
	private Long districtId;

	/*** 会员的详细地址。 */
	private String address;

	/*** 会员手机号。 */
	private String telephone;

	/*** 会员等级成长分值。。 */
	private Long upgradeScore;

	/*** 注册时间。 */
	private Date registerTime;

	/*** 注册IP。 */
	private String registerIp;

	/*** 最后登录时间。 */
	private Date lastLoginTime;

	/*** 最后登录IP。 */
	private String lastLoginIp;

	/*** 登录次数。 */
	private Long loginCount;

	/*** 账户状态（1 锁定，0 正常）。 */
	private Integer accountState;

	/*** 婚姻状况。（1已婚 0未婚 2离异）。 */
	private Integer marriageState;

	/*** 教育程度。 */
	private String educationLevel;

	/*** 所属行业。 */
	private String industry;

	/*** 月收入。 */
	private Double monthlyIncome;

	/*** 小会员头像。 */
	private String smallIcon;

	/*** 大会员头像。 */
	private String bigIcon;

	/*** 上级会员。 */
	private Long parentId;

	/*** 现有积分。 */
	private Long ownScore;

	/*** 已消耗积分。 */
	private Long usedScore;

	/*** 备注 */
	private String memo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Integer getBindingState() {
		return bindingState;
	}

	public void setBindingState(Integer bindingState) {
		this.bindingState = bindingState;
	}

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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Long getUpgradeScore() {
		return upgradeScore;
	}

	public void setUpgradeScore(Long upgradeScore) {
		this.upgradeScore = upgradeScore;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Long getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Long loginCount) {
		this.loginCount = loginCount;
	}

	public Integer getAccountState() {
		return accountState;
	}

	public void setAccountState(Integer accountState) {
		this.accountState = accountState;
	}

	public Integer getMarriageState() {
		return marriageState;
	}

	public void setMarriageState(Integer marriageState) {
		this.marriageState = marriageState;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public Double getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(Double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public String getSmallIcon() {
		return smallIcon;
	}

	public void setSmallIcon(String smallIcon) {
		this.smallIcon = smallIcon;
	}

	public String getBigIcon() {
		return bigIcon;
	}

	public void setBigIcon(String bigIcon) {
		this.bigIcon = bigIcon;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getOwnScore() {
		return ownScore;
	}

	public void setOwnScore(Long ownScore) {
		this.ownScore = ownScore;
	}

	public Long getUsedScore() {
		return usedScore;
	}

	public void setUsedScore(Long usedScore) {
		this.usedScore = usedScore;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
