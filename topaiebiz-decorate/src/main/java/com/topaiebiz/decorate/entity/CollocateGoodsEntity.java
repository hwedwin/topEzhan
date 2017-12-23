package com.topaiebiz.decorate.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description 店铺选用装修模版实体类
 * 
 * Author Aaron.Xue
 * 
 * Date 2017年11月1日 下午11:34:10
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_dec_collocate_goods")
public class CollocateGoodsEntity extends BaseBizEntity<Long> {

	/**
	 * 版本序列号
	 */
	private static final long serialVersionUID = 1055577791672766298L;

	/** '配置信息类型ID。' */
	private Long collocateTypeId;

	/** '商品ID。' */
	private Long goodsId;

	/** '显示顺序。' */
	private Integer sortNo;

	/** '备注。' */
	private String memo;

	public Long getCollocateTypeId() {
		return collocateTypeId;
	}

	public void setCollocateTypeId(Long collocateTypeId) {
		this.collocateTypeId = collocateTypeId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
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
