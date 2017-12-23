package com.topaiebiz.goods.comment.mobile.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.goods.comment.dto.GoodsSkuCommentDto;
import com.topaiebiz.goods.comment.dto.GoodsSkuCommentPictureDto;
import com.topaiebiz.goods.comment.entity.GoodsSkuCommentEntity;
import com.topaiebiz.goods.comment.entity.GoodsSkuCommentPictureEntity;
import com.topaiebiz.goods.comment.exception.GoodsSkuCommentExceptionEnum;
import com.topaiebiz.goods.comment.service.GoodsSkuCommentService;
import com.topaiebiz.goods.sku.dto.StoreOrderDetailDto;
import com.topaiebiz.system.moble.security.dto.TokenDto;
import com.topaiebiz.system.moble.security.util.TokenUtil;

/**
 * Description 商品spu评价
 * 
 * Author Hedda
 * 
 * Date 2017年10月2日 下午8:06:24
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
@RestController
@RequestMapping("/app/goods/skucomment")
public class GoodsSkuCommentMobileController {

	@Autowired
	private GoodsSkuCommentService goodsSkuCommentService;

	/**
	 * Description 添加商品评价
	 * 
	 * Author Hedda
	 * 
	 * @param goodsSkuCommentDto
	 *            商品评价dto
	 * @param goodsSkuCommentPictureDtos
	 *            商品评价图片
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/addGoodsSkuCommentDto", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addGoodsSkuCommentDto(String token, String goodsSkuCommentDtostr) throws GlobalException {
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (tokenDetail == null) {
			throw new GlobalException(GoodsSkuCommentExceptionEnum.PLEASE_LOG_IN_FIRST);
		}
		GoodsSkuCommentDto goodsSkuCommentDto = null;
		if (!StringUtils.isEmpty(goodsSkuCommentDtostr)) {
			goodsSkuCommentDto = JSONObject.parseObject(goodsSkuCommentDtostr, GoodsSkuCommentDto.class);
		}
		Long skuId = goodsSkuCommentDto.getSkuId();
		Long orderId = goodsSkuCommentDto.getOrderId();
		GoodsSkuCommentDto goodsSkuCommentDto2 = goodsSkuCommentService.getGoodsSkuCommentBySkuIdAndOrderId(skuId,orderId);
		if(null != goodsSkuCommentDto2) {
			throw new GlobalException(GoodsSkuCommentExceptionEnum.GOODS_EVALUATED);
		}
		Long storeId = tokenDetail.getStoreId();
		goodsSkuCommentDto.setBelongStore(storeId);
		// 判断是否是匿名评价
		Long memberId = tokenDetail.getMemberId();
		if (goodsSkuCommentDto.getMember() == 1) {
			goodsSkuCommentDto.setMemberId(memberId);
		}
		GoodsSkuCommentEntity goodsSkuComment = new GoodsSkuCommentEntity();
		BeanUtils.copyProperties(goodsSkuCommentDto, goodsSkuComment);
		List<GoodsSkuCommentPictureEntity> goodsSkuCommentPictureEntities = new ArrayList<GoodsSkuCommentPictureEntity>();
		List<GoodsSkuCommentPictureDto> goodsSkuCommentPictureDtos = goodsSkuCommentDto.getGoodsSkuCommentPictureDtos();
		if (!(goodsSkuCommentPictureDtos == null || goodsSkuCommentPictureDtos.size() == 0)) {
			for (GoodsSkuCommentPictureDto goodsSkuCommentPictureDto : goodsSkuCommentPictureDtos) {
				GoodsSkuCommentPictureEntity goodsSkuCommentPicture = new GoodsSkuCommentPictureEntity();
				BeanUtils.copyProperties(goodsSkuCommentPictureDto, goodsSkuCommentPicture);
				goodsSkuCommentPictureEntities.add(goodsSkuCommentPicture);
			}
		}
		return new ResponseInfo(goodsSkuCommentService.saveGoodsSkuCommentDto(goodsSkuComment,
				goodsSkuCommentPictureEntities, memberId));
	}

	/**
	 * Description 根据订单id查询商品 详情
	 * 
	 * Author Hedda
	 * 
	 * @param token
	 * @param orderId
	 *            订单id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getStoreOrderDetailList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getStoreOrderDetailList(String token,Long orderId) throws GlobalException {
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		if (tokenDetail == null) {
			throw new GlobalException(GoodsSkuCommentExceptionEnum.PLEASE_LOG_IN_FIRST);
		}
		List<StoreOrderDetailDto> storeOrderDetailDtos = goodsSkuCommentService.getStoreOrderDetailList(orderId);
		return new ResponseInfo(storeOrderDetailDtos);
	}
}
