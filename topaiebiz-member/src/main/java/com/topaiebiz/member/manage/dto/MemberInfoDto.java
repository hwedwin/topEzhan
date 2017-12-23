package com.topaiebiz.member.manage.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Description 会员信息表
 * 
 * 
 * Author scott
 *    
 * Date 2017年8月23日 下午7:49:54 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Data
public class MemberInfoDto implements Serializable{

	private static final long serialVersionUID = -1606503003749072791L;
	/*** 会员信息的全局唯一主键标识符。本字段是业务无关性的，仅用于关联。*/
	private Long id;
	
	/*** 会员类型。*/
	private Long typeId;
	
	/*** 会员等级。*/
	private Long gradeId;

	/*** 与上级的绑定状态（1 绑定，0 解绑）。*/
	private Integer  bindingState;
	
	/*** 显示用户名。*/
	private String userName;

	/*** 密码。*/
	private String password;

	// 上级姓名
	private String parentName;

	// 上级手机号
	private String parentTelephone;

	/*** 昵称。*/
	private String nickName;
	
	/*** 用户邮箱。*/
	private String post;
	
	/*** 会员真实姓名。*/
	private String realName ;
	
	/*** 会员的身份证。。*/
	private String idCard;
	
	/*** 性别（1 南  0 女)。*/
	private Integer gender;
	
	/*** 生日。*/
	private String birthday;
	
	/*** 所在地区编码。*/
	private Long districtId;
	
	/*** 会员的详细地址。*/
	private String address;
	
	/*** 会员手机号。*/
	private String telephone;

	/*** 注册时间。*/
	private Date registerTime;
	
	/*** 注册IP。*/
	private String registerIp;
	
	/*** 最后登录时间。*/
	private Date lastLoginTime;
	
	/*** 最后登录IP。*/
	private String lastLoginIp;
	
	/*** 登录次数。*/
	private Long loginCount;
	
	/*** 账户状态（1 锁定，0 正常）。*/
	private Integer accountState;
	
	/*** 婚姻状况。（1已婚 0未婚 2离异）。*/
	private Integer marriageState;
	
	/*** 教育程度。*/
	private String educationLevel;
	
	/*** 所属行业。*/
	private String industry;
	
	/*** 月收入。*/
	private Double monthlyIncome;
	
	/*** 小会员头像。*/
	private String smallIcon;
	
	/*** 大会员头像。*/
	private String bigIcon;
	
	/*** 上级会员。*/
	private Long parentId;

	/*** 现有积分。*/
	private Long ownScore;
	
	/*** 已消耗积分。*/
	private Long usedScore;
	
	/*** 会员等级名称。*/
	private String  gradeName;
	
	/*** 会员类型表的名称。*/ 
	private String typeName;
	
	/*** 会员等级说明*/
	private String description;
	
	/*** 所属店铺*/
	private String storeName;
	
	/*** 创建时间。*/
	private Date createdTime;

}
