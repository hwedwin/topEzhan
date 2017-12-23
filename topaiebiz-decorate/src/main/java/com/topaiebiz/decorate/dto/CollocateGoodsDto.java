package com.topaiebiz.decorate.dto;

public class CollocateGoodsDto {

	/** 主键ID。 */
	private Long id;

	/** '配置信息类型ID。' */
	private Long collocateTypeId;

	/** '商品ID。' */
	private Long goodsId;

	/** '显示顺序。' */
	private Integer sortNo;

	/** '备注。' */
	private String memo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
