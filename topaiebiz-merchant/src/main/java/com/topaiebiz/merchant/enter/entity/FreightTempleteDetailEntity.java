package com.topaiebiz.merchant.enter.entity;

import java.util.List;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;
import com.topaiebiz.merchant.enter.dto.DistrictInfoDto;

/**
 * Description: 运费模板详情实体类
 * 
 * Author : Anthony
 * 
 * Date :2017年10月13日 上午10:14:52
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice: 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_mer_freight_templete_detail")
public class FreightTempleteDetailEntity extends BaseBizEntity<Long> {

	/** 版本序列化 */
	private static final long serialVersionUID = 5099866494757262801L;

	/** 关联的运费模板ID */
	private Long freightId;

	/** 配送方式 */
	private Integer type;

	/** 首次价格 */
	private double firstPrice;

	/** 首次件数 */
	private double firstNum;

	/** 续件价格 */
	private double addPrice;

	/** 续件件数 */
	private double addNum;

	/** 是否为默认运费 */
	private Integer isDefault;

	private String districtIdList;

	private String nameListStr;
	
	private List<DistrictInfoDto> districtDtoList;
	
	public List<DistrictInfoDto> getDistrictDtoList() {
		return districtDtoList;
	}

	public void setDistrictDtoList(List<DistrictInfoDto> districtDtoList) {
		this.districtDtoList = districtDtoList;
	}

	public String getNameListStr() {
		return nameListStr;
	}

	public void setNameListStr(String nameListStr) {
		this.nameListStr = nameListStr;
	}

	public void setDistrictIdList(String districtIdList) {
		this.districtIdList = districtIdList;
	}

	public String getDistrictIdList() {
		return districtIdList;
	}

	public Long getFreightId() {
		return freightId;
	}

	public void setFreightId(Long freightId) {
		this.freightId = freightId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public double getFirstPrice() {
		return firstPrice;
	}

	public void setFirstPrice(double firstPrice) {
		this.firstPrice = firstPrice;
	}

	public double getFirstNum() {
		return firstNum;
	}

	public void setFirstNum(double firstNum) {
		this.firstNum = firstNum;
	}

	public double getAddPrice() {
		return addPrice;
	}

	public void setAddPrice(double addPrice) {
		this.addPrice = addPrice;
	}

	public double getAddNum() {
		return addNum;
	}

	public void setAddNum(double addNum) {
		this.addNum = addNum;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

}
