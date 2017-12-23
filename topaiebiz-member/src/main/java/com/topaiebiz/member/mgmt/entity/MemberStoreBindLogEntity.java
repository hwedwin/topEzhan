package com.topaiebiz.member.mgmt.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;
/**
 * 
 * Description 会员与店铺绑定记录  
 * 
 * 
 * Author Scott
 *    
 * Date 2017年9月9日 下午1:56:47 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_mem_member_store_bind_log")
public class MemberStoreBindLogEntity extends BaseBizEntity<Long> {
	
	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = -8579593149914446555L;
	
	/** 会员编号。*/
	private Long memberId;
	
	/** 店铺ID。*/
	private Long storeId;
	
	/** 操作类型（1 绑定 2 解绑）。*/
	private Integer type;
	
	/** 绑定状态（1 已完成，2 申请中）。*/
	private Integer stauts;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStauts() {
		return stauts;
	}

	public void setStauts(Integer stauts) {
		this.stauts = stauts;
	}
	
	
}
