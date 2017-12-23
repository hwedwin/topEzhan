package com.topaiebiz.giftcard.manage.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableField;

public class SaveInfoDto {

	private Long id;

	/** 礼卡介质的id */
	@NotNull(message = "{validation.info.cardMedia}")
	private Long cardMedium;

	/** 礼卡主题的ID */
	@NotNull(message = "{validation.info.cardLabel}")
	private Long cardLabel;

	/** 礼卡类型的ID */
	@NotNull(message = "{validation.info.cardType}")
	private Long cardType;

	/** 是否为副卡（1是，0 否） */
	private Integer isVice;

	/** 绑定主卡id.只有副卡的时候填该此字段。 */
	private Long mainCardId;

	/** 是否为自定义卡，1 是 0 否 */
	private Integer isCustom;

	/** 礼卡名称（标题）。 */
	@NotBlank(message = "{validation.info.name}")
	private String name;

	/** 卡号前缀 */
	@NotBlank(message = "{validation.info.prefix}")
	private String prefix;

	/**
	 * 礼卡面值
	 * 
	 * @return
	 */
	@NotNull(message = "{validation.info.value}")
	@Digits(fraction = 2, integer = 4)
	private Double value;

	/** 礼卡售价 */
	@NotNull(message = "{validation.info.price}")
	@Digits(fraction = 2, integer = 4)
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
	@NotNull(message = "{validation.info.totalNum}")
	@Digits(fraction = 0, integer = 6)
	private Long totalNum;

	/** 卡剩余数量 */
	private Long surplusNum;

	/** 到期时间，某一时间点 */
	@NotNull(message="{validation.info.expirationTime}")
	private Date expirationTime;

	/** ID限购数量 */
	private Long banMemberNo;

	/** 礼卡销售时间。(系统时间) */
	private Date saleTime;

	/** 卡号状态。（0新建 1 发布 2 上架 3 下架） */
	private Integer state;

	/** 卡图片。 */
	@NotNull(message= "{validation.info.image}") 
	private String image;

	/** 批删的ID集合 */
	@TableField(exist = false)
	private List<Long> list;

	/** 副卡的卡面值 */
	@TableField(exist = false)
	private Double viceValue;

	/** 副卡的平台补差 */
	private Double vicePlatformPrice;

	/** 副卡的店铺补差 */
	private Double viceStorePrice;

	/** 副卡的品牌补差 */
	private Double viceBrandPrice;

	/** 礼卡信息的描述 */
	private String memo;

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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getId() {
		return id;
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

	public Long getMainCardId() {
		return mainCardId;
	}

	public void setMainCardId(Long mainCardId) {
		this.mainCardId = mainCardId;
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

	public List<Long> getList() {
		return list;
	}

	public void setList(List<Long> list) {
		this.list = list;
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

}
