package com.topaiebiz.promotion.mgmt.moble.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.goods.sku.dto.ItemDto;
import com.topaiebiz.member.point.dto.MemberCouponDto;
import com.topaiebiz.promotion.mgmt.dto.CommodityMarketingDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionDto;
import com.topaiebiz.promotion.mgmt.dto.PromotionGoodsDto;
import com.topaiebiz.promotion.mgmt.exception.PromotionExceptionEnum;
import com.topaiebiz.promotion.mgmt.service.PromotionGoodsService;
import com.topaiebiz.promotion.mgmt.service.PromotionService;
import com.topaiebiz.system.moble.security.dto.TokenDto;
import com.topaiebiz.system.moble.security.util.TokenUtil;

/**
 * 
 * Description C端营销活动
 * 
 * 
 * Author Joe
 * 
 * Date 2017年10月29日 下午5:14:58
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@RestController
@RequestMapping(path = "/app/promotion/mgmt")
public class AppPromotionController {

	@Autowired
	private PromotionService promotionService;

	@Autowired
	private PromotionGoodsService promotionGoodsService;

	/**
	 * 
	 * Description 查询首页秒杀活动
	 * 
	 * Author Joe
	 * 
	 * @return
	 * @throws GlobalException
	 * @throws ParseException
	 */
	@RequestMapping(path = "/getHomePageSeckill", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getHomePageSeckillList(String token, Page<PromotionGoodsDto> page) throws GlobalException, ParseException {
		Page<PromotionGoodsDto> promotionGoodsDtoList = promotionGoodsService.getHomePageSeckill(token, page);
		return new ResponseInfo(promotionGoodsDtoList);

	}

	/**
	 * 
	 * Description 查询秒杀活动时间集合
	 * 
	 * Author Joe
	 * 
	 * @param token
	 * @return
	 */
	@RequestMapping(path = "/getSeckillStartTimeList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getSeckillStartTimeList(String token) {
		List<PromotionDto> promotionDtoList = promotionService.getSeckillStartTimeList();
		return new ResponseInfo(promotionDtoList);

	}

	/**
	 * 
	 * Description 根据营销活动id查询活动商品分页列表
	 * 
	 * Author Joe
	 * 
	 * @param token
	 * @param page
	 * @param promotionId
	 * @return
	 */
	@RequestMapping(path = "/getPromotionSeckillList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getPromotionSeckillList(String token, Page<PromotionGoodsDto> page, Long promotionId) {
		Page<PromotionGoodsDto> promotionGoodsDtoList = promotionGoodsService.getPromotionSeckillList(token, page, promotionId);
		return new ResponseInfo(promotionGoodsDtoList);
	}

	/**
	 * 
	 * Description 查询优惠券所选商品
	 * 
	 * Author Joe
	 * 
	 * @param token
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getPromotionCouponsGoodsList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getPromotionCouponsGoodsList(String token, Page<PromotionGoodsDto> page, ItemDto itemDto) throws GlobalException {
		// 检验token
		TokenUtil.verifyToken(token);
		/** 判断id是否为空 */
		if (null == itemDto.getPromotionId()) {
			throw new GlobalException(PromotionExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		Page<PromotionGoodsDto> promotionGoodsDtoList = promotionService.getPromotionCouponGoods(page, itemDto, token);
		return new ResponseInfo(promotionGoodsDtoList);
	}

	/**
	 * 
	 * Description 查询商品所对应营销活动
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	@RequestMapping(path = "/getCommodityMarketing", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getCommodityMarketing(String token) {
		// 检验token
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		List<CommodityMarketingDto> commodityMarketinglist = promotionService.getCommodityMarketing(tokenDetail);
		return new ResponseInfo(commodityMarketinglist);
	}

	/**
	 * 
	 * Description 查询商品可用单品级营销活动
	 * 
	 * Author Joe
	 * 
	 * @param token
	 * @param goodsSkuDtoList
	 * @return
	 */
	@RequestMapping(path = "/getSinglePromotionList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getSinglePromotionList(String token, Long[] goodsSkuList) {
		List<Long> goodsSkuDtoList = new ArrayList<Long>();
		// 检验token
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (tokenDetail != null) {
			if (tokenDetail.getMemberId() != null && goodsSkuList != null) {
				for (Long id : goodsSkuList) {
					goodsSkuDtoList.add(id);
				}
				List<CommodityMarketingDto> singlePromotionList = promotionService.getSinglePromotionList(tokenDetail.getStoreId(), tokenDetail.getMemberId(), goodsSkuDtoList);
				return new ResponseInfo(singlePromotionList);
			}
		}
		return new ResponseInfo();
	}

	/**
	 * 
	 * Description C端查询店铺优惠券
	 * 
	 * Author Joe
	 * 
	 * @return
	 */
	@RequestMapping(path = "/getPromotionStoreCouponListByMemberId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getPromotionStoreCouponListByMemberId(String token, Long itemId) {
		List<PromotionDto> promotionList = new ArrayList<PromotionDto>();
		// 检验token
		TokenUtil.verifyToken(token);
		// 商品itemId不得为空
		if (null == itemId) {
			throw new GlobalException(PromotionExceptionEnum.PRODUCT_ITEM_ID_NOT_NULL);
		}
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (tokenDetail != null) {
			promotionList = promotionService.getPromotionStoreCouponListByStoreId(tokenDetail, itemId);
		}
		return new ResponseInfo(promotionList);
	}

	/**
	 * Description： 获取会员优惠券
	 * 
	 * Author Scott.Yang
	 * 
	 * @param id
	 *            会员积分ID
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/saveMemberCoupon", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addMemberCoupon(String token, MemberCouponDto memberCouponDto) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		Long memberId = tokenDetail.getMemberId();
		memberCouponDto.setMemberId(memberId);
		memberCouponDto.setToken(token);
		promotionService.saveMemberCoupon(memberCouponDto);
		return new ResponseInfo();
	}

}
