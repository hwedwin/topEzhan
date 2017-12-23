package com.topaiebiz.goods.sku.entity;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * Description 商品基本信息表，存储商品的信息。
 * 
 * Author Hedda
 * 
 * Date 2017年8月23日 下午5:23:11
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_goo_item")
public class ItemEntity extends BaseBizEntity<Long> {

	/** 序列化版本号。 */
	@TableField(exist = false)
	private static final long serialVersionUID = 8956575417742055766L;
	
	/** 唯一编码 (本字段是从业务角度考虑的，相当于全局的唯一业务主键)。 */
	private String itemCode;

	/** 商品名称(标题显示的名称)。 */
	private String name;
	
	/** 佣金比例。小数形式。平台收取商家的佣金。*/
	private Double brokerageRatio;

	/** 引用SPU商品。 */
	private Long spuId;
	
	/** 市场价。*/
	private Double marketPrice;

	/** 默认价格（页面刚打开的价格）。 */
	private Double defaultPrice;

	/** 所属店铺。 */
	private Long belongStore;

	/** 所属品牌。 */
	private Long belongBrand;
	
	/** 适用年龄段。*/
	private Long ageId;

	/** 所属类目。 */
	private Long belongCategory;
	
	/** 图片所属类目属性。*/
	private Long imageField;

	/** 商品状态（1 新录入 2 已上架 3 下架 4 违规下架）。 */
	private Integer status;

	/** 选用物流模版。 */
	private Long logisticsId;
	
	/** 物流模版的体积、重量（体积默认为m3，重量默认为kg）。*/
	private Double weightBulk;

	/** 商品描述。 */
	private String description;
	
	/** 备注。用于备注其他信息。 */
	private String memo;

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBrokerageRatio() {
		return brokerageRatio;
	}

	public void setBrokerageRatio(Double brokerageRatio) {
		this.brokerageRatio = brokerageRatio;
	}

	public Long getSpuId() {
		return spuId;
	}

	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Double getDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(Double defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	public Long getBelongStore() {
		return belongStore;
	}

	public void setBelongStore(Long belongStore) {
		this.belongStore = belongStore;
	}

	public Long getBelongBrand() {
		return belongBrand;
	}

	public void setBelongBrand(Long belongBrand) {
		this.belongBrand = belongBrand;
	}

	public Long getAgeId() {
		return ageId;
	}

	public void setAgeId(Long ageId) {
		this.ageId = ageId;
	}

	public Long getBelongCategory() {
		return belongCategory;
	}

	public void setBelongCategory(Long belongCategory) {
		this.belongCategory = belongCategory;
	}

	public Long getImageField() {
		return imageField;
	}

	public void setImageField(Long imageField) {
		this.imageField = imageField;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(Long logisticsId) {
		this.logisticsId = logisticsId;
	}

	public Double getWeightBulk() {
		return weightBulk;
	}

	public void setWeightBulk(Double weightBulk) {
		this.weightBulk = weightBulk;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
