package com.topaiebiz.promotion.mgmt.exception;

import com.nebulapaas.web.exception.ExceptionInfo;

/**
 * 
 * Description 营销活动枚举
 * 
 * 
 * Author Joe
 * 
 * Date 2017年9月22日 下午10:33:46
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
public enum PromotionExceptionEnum implements ExceptionInfo {

	/** 活动ID不能为空 */
	PROMOTION_ID_NOT_NULL("7000001", "Promotion ID not null!"),

	/** 发放数量不得为空 */
	QUANTITY_ISSUED_SHALL_NOT_NULL("7000002", "quantity issued shall not null!"),

	/** 补贴比例不能为空 */
	SUBSIDY_PROPORTION_NOT_NULL("7000003", "subsidy proportion not null!"),

	/** 商品SKU不可为空 */
	PRODUCT_SKU_NOT_NULL("7000004", "product sku not null!"),

	/** 原有库存不得为空 */
	ORIGINAL_STOCK_MUST_NOT_NULL("7000005", "original stock must not null!"),

	/** 活动价格不得为空 */
	PROMOTION_PRICE_CANNOT_BE_EMPTY("7000006", "promotion price cannot be empty!"),

	/** 活动数量不得为空 */
	PROMOTION_QUANTITY_CANNOT_BE_EMPTY("7000007", "promotion quantity cannot be empty!"),

	/** ID限购不得为空 */
	ID_RESTRICTION_CANNOT_BE_EMPTY("7000008", "ID restriction cannot be empty!"),

	/** 活动类型不能为空 */
	PROMOTION_TYPE_NOT_NULL("7000009", "promotion type not null!"),

	/** 活动商品不得为空 */
	PROMOTIONGOODS_NOT_NULL("70000010", "promotion goods not null!"),

	/** 所属店铺不得为空 */
	PROMOTIONGOODS_STORE_OWNED_NOT_NULL("70000011", "promotion goods store owned not null!"),

	/** 商品优惠值不可为空 */
	GOODS_DISCOUNT_NOT_NULL("70000012", "goods discount not null!"),

	/** 商品原价不可为空 */
	GOODS_ORIGINAL_PRICE_NOT_NULL("70000013", "goods original price not null!"),

	/** 条件值不可为空 */
	CONDVALUE_NOT_NULL("70000014", "condvalue not null!"),

	/** 请将活动的开始时间调整到当前时间后一个小时 */
	PLEASE_ADJUST_THE_START_TIME("70000015", "please adjust the start time!"),

	/** 活动数量大于原库存 */
	ACTIVITY_NUMBER_GREATER_THAN_STOCK("70000016", "activity number greater than stock!"),

	/** 活动不存在 */
	ACTIVITY_DOES_NOT_EXIST("70000017", "activity does not exist!"),

	/** 商品itemID不可为空 */
	PRODUCT_ITEM_ID_NOT_NULL("70000018", "product itemId not null!"),

	/** 优惠券不可领取 */
	COUPONS_ARE_NOT_TO_BE_OBTAINED("70000019", "coupons are not to be obtained!"),

	/** 会员ID不可为空 */
	MEMBER_ID_NOT_NULL("70000020", "member id not null!"),

	/** 发布时间过期 */
	TIME_EXPIRED("70000021", "time expired!"),
	
	/** 优惠券已领取*/
	THE_COUPONS_HAVE_BEEN_TAKEN("70000022","the coupons have been taken!"),
	
	/** 报名截止时间应在活动开始时间之前*/
	PLEASE_ADJUST_THE_ENROL_END_TIME("70000023","please adjust the enrol end time!"),
	
	/** 商品审核状态不可为空*/
	PRODUCT_AUDIT_STATE_CANNOT_BE_EMPTY("70000024","product audit state cannot be empty!"),
	
	/** 商品数量不得低于最少报名商品数*/ 
	GOODS_NUMBER_SHALL_NOT_BE_LESS_THAN_MINIMUM("70000025","goods number shall not be less than minimum!"),
	
	/** 商品数量不得大于最大报名商品数*/
	GOODS_NUMBER_SHALL_NOT_BE_GREATER_THAN_MAXIMUM("70000026","goods number shall not be greater than maximum!");

	/** 异常代码。 */
	private String code;

	/** 异常对应的默认提示信息。 */
	private String defaultMessage;

	/** 异常对应的原始提示信息。 */
	private String originalMessage;

	/** 当前请求的URL。 */
	private String requestUrl;

	/** 默认的转向（重定向）的URL，默认为空。 */
	private String defaultRedirectUrl = "";

	/** 异常对应的响应数据。 */
	private Object data;

	/**
	 * Description: 根据异常的代码、默认提示信息构建一个异常信息对象。
	 *
	 * Author: Joe
	 * 
	 * @param code
	 *            异常的代码。
	 * 
	 * @param defaultMessage
	 *            异常的默认提示信息。
	 */
	PromotionExceptionEnum(String code, String defaultMessage) {
		this.code = code;
		this.defaultMessage = defaultMessage;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getDefaultMessage() {
		return defaultMessage;
	}

	@Override
	public String getOriginalMessage() {
		return originalMessage;
	}

	@Override
	public void setOriginalMessage(String originalMessage) {
		this.originalMessage = originalMessage;
	}

	@Override
	public String getRequestUrl() {
		return requestUrl;
	}

	@Override
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	@Override
	public String getDefaultRedirectUrl() {
		return defaultRedirectUrl;
	}

	@Override
	public void setDefaultRedirectUrl(String defaultRedirectUrl) {
		this.defaultRedirectUrl = defaultRedirectUrl;
	}

	@Override
	public Object getData() {
		return data;
	}

	@Override
	public void setData(Object data) {
		this.data = data;
	}

}
