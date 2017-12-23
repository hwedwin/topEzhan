package com.topaiebiz.member.reserve.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;
/**
 * 
 * Description 会员兴趣爱好表，存储会员的兴趣爱好信息。  
 * 
 * 
 * Author Scott
 *    
 * Date 2017年9月12日 上午11:07:27 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_mem_member_hobby")
public class MemberHobbyEntity extends BaseBizEntity<Long> {
	
	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = 5255343081541980140L;

	/** 会员编号。*/
	private Long memberId;
	
	/** 兴趣键。*/
	private String hobbyKey;
	
	/** 兴趣值。*/
	private String hobbyValue;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getHobbyKey() {
		return hobbyKey;
	}

	public void setHobbyKey(String hobbyKey) {
		this.hobbyKey = hobbyKey;
	}

	public String getHobbyValue() {
		return hobbyValue;
	}

	public void setHobbyValue(String hobbyValue) {
		this.hobbyValue = hobbyValue;
	}
	
	
}
