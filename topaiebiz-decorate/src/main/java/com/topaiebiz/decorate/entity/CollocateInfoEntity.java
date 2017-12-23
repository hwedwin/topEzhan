package com.topaiebiz.decorate.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseEntity;

/**
 * Description 店铺装修模版配置信息实体类
 * 
 * Author Aaron.Xue
 * 
 * Date 2017年11月1日 下午11:34:10
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_dec_collocate_info")
public class CollocateInfoEntity extends BaseEntity<Long> {

	/**
	 * 版本序列号
	 */
	private static final long serialVersionUID = 3673049885456990697L;

	/** '模版类型。' */
	private Long collocateType;

	/** '图片地址。' */
	private String image;

	/** '链接类型。1商品 2类目 3品牌 4 自定义url' */
	private Integer jumpType;

	/** '跳转ID。' */
	private Long jumpId;

	/** '跳转URL。' */
	private String jumpUrl;

	/** '排序号。' */
	private Integer sortNo;

	/** 备注。 */
	private String memo;

	public Long getCollocateType() {
		return collocateType;
	}

	public void setCollocateType(Long collocateType) {
		this.collocateType = collocateType;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getJumpType() {
		return jumpType;
	}

	public void setJumpType(Integer jumpType) {
		this.jumpType = jumpType;
	}

	public Long getJumpId() {
		return jumpId;
	}

	public void setJumpId(Long jumpId) {
		this.jumpId = jumpId;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
