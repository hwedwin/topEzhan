package com.topaiebiz.transaction.cart.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseBizEntity;

/**
 * 
 * Description： 商品分享实体类  
 * 
 * 
 * Author zhushuyong 
 *    
 * Date 2017年9月25日 下午2:01:28 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@TableName("t_tsa_goods_share")
public class GoodsShareEntity extends BaseBizEntity<Long> {

	/*** 版本化序列号*/
	private static final long serialVersionUID = 4666662074456430170L;
	
	/** 会员id*/
	private Long memberId;
	
	/** 商品id*/
	private Long goodsId;

	public GoodsShareEntity() {
		super();
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

}
