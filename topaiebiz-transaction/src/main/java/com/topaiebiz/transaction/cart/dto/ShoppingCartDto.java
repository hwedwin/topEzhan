package com.topaiebiz.transaction.cart.dto;

import java.util.Date;

/**
 * 
 * Description 购物车&商品主信息&商品sku&会员信息的dto  
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月8日 下午9:35:19 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public class ShoppingCartDto{

	/** 全局唯一主键id*/
	private Long id;
	
	/** 会员id*/
	private Long memberId;
	
	/** 商品sku表的id*/
	private Long goodsSkuId;
	
	/** 数量*/
	private Long goodsNum;
	
	/** 备注*/
	private String memo;
	
	/** 创建人编号。取值为创建人的全局唯一主键标识符。*/
	private Long creatorId;
	
	/** 创建时间。取值为系统的当前时间。*/
	private Date createdTime;	
	
	/** 逻辑删除标识。仅且仅有0和1两个值，1表示已经被逻辑删除，0表示正常可用。*/
	private Integer deletedFlag;
	
	/** 信息版本号。乐观锁机制的辅助字段，用于控制信息的一致性。*/
	private Long version;
	
	
	
	
	
	/** 商品主表id */
	private Long goodsMainId;

	/** 唯一编码 (本字段是从业务角度考虑的，相当于全局的唯一业务主键)。 */
	private String itemCode;

	/** 商品名称(标题显示的名称)。 */
	private String name;

	/** 引用SPU商品。 */
	private Long spuId;

	/** 默认价格（页面刚打开的价格）。 */
	private double defaultPrice;

	/** 所属店铺。 */
	private Long belongStore;

	/** 所属品牌。 */
	private Long belongBrand;

	/** 所属类目。 */
	private Long belongCategory;

	/** 商品状态（1 新录入 2 已上架 3 下架 4 违规下架）。 */
	private Integer status;

	/** 选用物流模版。 */
	private Long logisticsId;

	/** 商品描述。 */
	private String description;
	
	
	
	/** 商品SKU属性的全局唯一主键标识符。本字段是业务无关性的，仅用于关联。*/
	private Long skuId;
	
	/** 所属商品id*/
	private Long productId;
	
	/** 属性集合以键值对形式存放。key:value,key1:value1。*/
	private String baseFieldValue;
	
	/** 销售属性集合以键值对形式存放。key:value,key1:value1。*/
	private String saleFieldValue;
	
	/** SKU商品图片。*/
	private String saleImage;
	
	/** 销售价格,最多两位小数。*/
	private Double price;
	
	/** 库存数量。*/
	private Long stockNumber;
	
	/** 货号。*/
	private String articleNumber;
	
	/** 商品条形码。*/
	private String barCode;
	
	
	
	
	/** 会员类型。*/
	private Long typeId;
	
	/** 会员等级。*/
	private Long gradeId;
	
	/** 显示用户名。*/
	private String userName;
	
	/** 昵称。*/
	private String nickName;
	
	/** 所在地区编码。*/
	private Long districtId;
	
	/** 会员的详细地址。*/
	private String address;
	
	/** 会员等级成长分值。*/
	private Long upgradeScore;
	
	/** 账户状态（1 锁定，0 正常）。*/
	private Integer accountState;
	
	
	
	
	/** 所属商家id*/
	private Long merchantId;
	
	/** 店铺名称*/
	private String storeName;
	
	/** 如果指定区域运费模板下，会员收货地址不匹配的话，显示无货特定标识*/
	private String noGoods;
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
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

	public Integer getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(Integer deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSpuId() {
		return spuId;
	}

	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}

	public double getDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(double defaultPrice) {
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

	public Long getBelongCategory() {
		return belongCategory;
	}

	public void setBelongCategory(Long belongCategory) {
		this.belongCategory = belongCategory;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getBaseFieldValue() {
		return baseFieldValue;
	}

	public void setBaseFieldValue(String baseFieldValue) {
		this.baseFieldValue = baseFieldValue;
	}

	public String getSaleFieldValue() {
		return saleFieldValue;
	}

	public void setSaleFieldValue(String saleFieldValue) {
		this.saleFieldValue = saleFieldValue;
	}

	public String getSaleImage() {
		return saleImage;
	}

	public void setSaleImage(String saleImage) {
		this.saleImage = saleImage;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(Long stockNumber) {
		this.stockNumber = stockNumber;
	}

	public String getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public Long getUpgradeScore() {
		return upgradeScore;
	}

	public void setUpgradeScore(Long upgradeScore) {
		this.upgradeScore = upgradeScore;
	}

	public Integer getAccountState() {
		return accountState;
	}

	public void setAccountState(Integer accountState) {
		this.accountState = accountState;
	}

	public String getNoGoods() {
		return noGoods;
	}

	public void setNoGoods(String noGoods) {
		this.noGoods = noGoods;
	}

	public Long getGoodsSkuId() {
		return goodsSkuId;
	}

	public void setGoodsSkuId(Long goodsSkuId) {
		this.goodsSkuId = goodsSkuId;
	}

	public Long getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Long goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Long getGoodsMainId() {
		return goodsMainId;
	}

	public void setGoodsMainId(Long goodsMainId) {
		this.goodsMainId = goodsMainId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

}
