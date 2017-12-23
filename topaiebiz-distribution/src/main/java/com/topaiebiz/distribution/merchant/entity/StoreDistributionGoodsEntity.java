package com.topaiebiz.distribution.merchant.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import com.nebulapaas.data.mybatis.common.IdEntity;

/**
 * Description： 店铺分销商品Entity。
 * 
 * Author Harry
 *    
 * Date 2017年10月29日 下午4:39:31 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_dis_store_distribution_goods")
public class StoreDistributionGoodsEntity extends IdEntity<Long> implements Serializable {

	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = -5595764752992476286L;
	
	/**商品ID*/
	private Long itemId;
	
	/**店铺ID*/
	private Long store;
	
	/**备注*/
	private String memo;
	
	/**创建人编号。取值为创建人的全局唯一主键标识符。*/
	private Long creatorId;
	
	/**创建时间。取值为系统的当前时间。*/
	private Date createdTime = new Date();
	
	/**逻辑删除标识。仅且仅有0和1两个值，1表示已经被逻辑删除，0表示正常可用。*/
	private  Byte deletedFlag = 0;
	
	/** 版本号。信息的版本号。乐观锁机制的辅助字段，用于控制信息的一致性。默认取值为1，执行更新操作时，自动加1。 */
	@Version
	private Long version = 1L;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getStore() {
		return store;
	}

	public void setStore(Long store) {
		this.store = store;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Byte getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(Byte deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	
}
