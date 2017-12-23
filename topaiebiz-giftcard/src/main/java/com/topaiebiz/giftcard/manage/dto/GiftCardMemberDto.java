package com.topaiebiz.giftcard.manage.dto;

public class GiftCardMemberDto {

	/**礼卡编号*/
	private Long id;
	
	/** 礼卡的名称 */
	private String infoName;

	/** 每种礼卡的面值 */
	private Double value;

	/** 每种礼卡的数量 */
	private Long number;

	/** 相应的所有礼卡的总余额 */
	private Double sumBalance;

	/** 每种礼卡的余额 */
	private Double balance;

	/** 经营类型 */
	private String typeName;

	/** 店铺名 */
	private String storeName;

	/** 品牌名 */
	private String brandName;

	/**礼卡的类型*/
	private Long cardType;
	
	
	
	public Long getCardType() {
		return cardType;
	}

	public void setCardType(Long cardType) {
		this.cardType = cardType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getInfoName() {
		return infoName;
	}

	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Double getSumBalance() {
		return sumBalance;
	}

	public void setSumBalance(Double sumBalance) {
		this.sumBalance = sumBalance;
	}

}
