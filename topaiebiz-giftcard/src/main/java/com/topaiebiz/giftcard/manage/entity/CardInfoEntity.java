package com.topaiebiz.giftcard.manage.entity;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * 
 * Description 礼卡信息类
 * 
 * 
 * Author Murray.Li
 * 
 * String 2017年8月25日 上午11:01:40
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_gif_giftcard_info")
public class CardInfoEntity extends BaseBizEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7793408528406743499L;

	/** 礼卡信息的唯一主键ID，非自增 */
	@TableId("id")
	private Long id;

	/** 礼卡介质的id */
	private Long cardMedium;

	/** 礼卡主题的ID */
	private Long cardLabel;

	/** 礼卡类型的ID */
	private Long cardType;

	/** 是否为副卡（1是，0 否） */
	private Integer isVice;

	/** 绑定主卡id.只有副卡的时候填该此字段。 */
	private Long mainCardId;

	/** 是否为自定义卡，1 是 0 否 */
	private Integer isCustom;

	/** 礼卡名称（标题） */
	private String name;

	/** 卡号前缀 */
	private String prefix;

	/** 礼卡面值 */
	private Double value;

	/** 礼卡售价 */
	private Double price;

	/** 平台补差 */
	private Double platformPrice;

	/** 店铺补差 */
	private Double storePrice;

	/** 品牌补差 */
	private Double brandPrice;

	/** 适用范围：1 平台 2店铺 3品牌 */
	private Integer rangeType;

	/** 经营店铺 */
	private Long storeId;

	/** 经营品牌 */
	private Long brandId;

	/** 发行批准人 */
	private String approver;

	/** 自定义卡最小面值。 */
	private Double minPrice;

	/** 自定义卡最大面值。 */
	private Double maxPrice;

	/** 卡总数量 */
	private Long totalNum;

	/** 卡剩余数量 */
	private Long surplusNum;

	/** 到期时间，某一时间点 */
	private Date expirationTime;

	/** ID限购数量 */
	private Long banMemberNo;

	/** 礼卡销售时间。(系统时间) */
	private Date saleTime;

	/** 卡号状态。（0新建 1 发布 2 上架 3 下架） */
	private Integer state;

	/** 卡图片。 */
	private String image;

	/** 批删的ID集合 */
	@TableField(exist = false)
	private List<Long> list;

	/** 副卡的卡面值 */
	@TableField(exist = false)
	private Double viceValue;

	/** 副卡的平台补差 */
	@TableField(exist = false)
	private Double vicePlatformPrice;

	/** 副卡的店铺补差 */
	@TableField(exist = false)
	private Double viceStorePrice;

	/** 副卡的品牌补差 */
	@TableField(exist = false)
	private Double viceBrandPrice;
	
	/**购买的礼卡的数量*/
	@TableField(exist = false)
	private Long number;
	
	/**需要支付的金额*/
	@TableField(exist = false)
	private Double sumPrice;
	
	
	private String memo;

	/**
	 * Description 礼卡信息类所有属性的构造方法
	 * 
	 * Author Murray
	 * 
	 * return
	 */

	
	public Long getId() {
		return id;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Double getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}

	public Double getViceStorePrice() {
		return viceStorePrice;
	}

	public void setViceStorePrice(Double viceStorePrice) {
		this.viceStorePrice = viceStorePrice;
	}

	public Double getViceBrandPrice() {
		return viceBrandPrice;
	}

	public void setViceBrandPrice(Double viceBrandPrice) {
		this.viceBrandPrice = viceBrandPrice;
	}

	public Double getViceValue() {
		return viceValue;
	}

	public void setViceValue(Double viceValue) {
		this.viceValue = viceValue;
	}

	public Double getVicePlatformPrice() {
		return vicePlatformPrice;
	}

	public void setVicePlatformPrice(Double vicePlatformPrice) {
		this.vicePlatformPrice = vicePlatformPrice;
	}

	public List<Long> getList() {
		return list;
	}

	public void setList(List<Long> list) {
		this.list = list;
	}

	public Long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}

	public Long getSurplusNum() {
		return surplusNum;
	}

	public void setSurplusNum(Long surplusNum) {
		this.surplusNum = surplusNum;
	}

	public Long getMainCardId() {
		return mainCardId;
	}

	public void setMainCardId(Long mainCardId) {
		this.mainCardId = mainCardId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCardMedium() {
		return cardMedium;
	}

	public void setCardMedium(Long cardMedium) {
		this.cardMedium = cardMedium;
	}

	public Long getCardLabel() {
		return cardLabel;
	}

	public void setCardLabel(Long cardLabel) {
		this.cardLabel = cardLabel;
	}

	public Long getCardType() {
		return cardType;
	}

	public void setCardType(Long cardType) {
		this.cardType = cardType;
	}

	public Integer getIsVice() {
		return isVice;
	}

	public void setIsVice(Integer isVice) {
		this.isVice = isVice;
	}

	public Integer getIsCustom() {
		return isCustom;
	}

	public void setIsCustom(Integer isCustom) {
		this.isCustom = isCustom;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPlatformPrice() {
		return platformPrice;
	}

	public void setPlatformPrice(Double platformPrice) {
		this.platformPrice = platformPrice;
	}

	public Double getStorePrice() {
		return storePrice;
	}

	public void setStorePrice(Double storePrice) {
		this.storePrice = storePrice;
	}

	public Double getBrandPrice() {
		return brandPrice;
	}

	public void setBrandPrice(Double brandPrice) {
		this.brandPrice = brandPrice;
	}

	public Integer getRangeType() {
		return rangeType;
	}

	public void setRangeType(Integer rangeType) {
		this.rangeType = rangeType;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public Long getBanMemberNo() {
		return banMemberNo;
	}

	public void setBanMemberNo(Long banMemberNo) {
		this.banMemberNo = banMemberNo;
	}

	public Date getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(Date saleTime) {
		this.saleTime = saleTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "礼卡信息：[id=" + id + ", cardMedium=" + cardMedium + ", cardLabel=" + cardLabel + ", cardType=" + cardType
				+ ", isVice=" + isVice + ", isCustom=" + isCustom + ", name=" + name + ", prefix=" + prefix + ", value="
				+ value + ", price=" + price + ", platformPrice=" + platformPrice + ", storePrice=" + storePrice
				+ ", brandPrice=" + brandPrice + ", rangeType=" + rangeType + ", storeId=" + storeId + ", brandId="
				+ brandId + ", approver=" + approver + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice
				+ ", expirationTime=" + expirationTime + ", banMemberNo=" + banMemberNo + ", saleTime=" + saleTime
				+ ", state=" + state + ", image=" + image + "]";
	}

}
