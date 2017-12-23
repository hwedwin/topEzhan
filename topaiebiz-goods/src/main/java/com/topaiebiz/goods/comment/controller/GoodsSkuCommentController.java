package com.topaiebiz.goods.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.goods.comment.dto.GoodsSkuCommentDto;
import com.topaiebiz.goods.comment.service.GoodsSkuCommentService;

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
@RequestMapping("/goods/skucomment")
public class GoodsSkuCommentController {

	@Autowired
	private GoodsSkuCommentService goodsSkuCommentService;

	/**
	 * Description 商家端商品评价未回复列表
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param goodsSkuCommentDto
	 *            商品sku评价dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getMerchentNoReplyListGoodsSkuComment", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getNoReplyListGoodsSkuComment(Page<GoodsSkuCommentDto> page, GoodsSkuCommentDto goodsSkuCommentDto)
			throws GlobalException {
		Page<GoodsSkuCommentDto> list = goodsSkuCommentService.getNoReplyListGoodsSkuComment(page, goodsSkuCommentDto);
		return new ResponseInfo(list);

	}
	
	/**
	 * Description 商家端商品评价已回复列表
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param goodsSkuCommentDto
	 *            商品sku评价dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getMerchentReplyListGoodsSkuComment", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getReplyListGoodsSkuComment(Page<GoodsSkuCommentDto> page, GoodsSkuCommentDto goodsSkuCommentDto)
			throws GlobalException {
		Page<GoodsSkuCommentDto> list = goodsSkuCommentService.getReplyListGoodsSkuComment(page, goodsSkuCommentDto);
		return new ResponseInfo(list);

	}
	
	/**
	 * Description 平台商品评价列表
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param goodsSkuCommentDto
	 *            商品sku评价dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getListGoodsSkuComment", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getListGoodsSkuComment(Page<GoodsSkuCommentDto> page, GoodsSkuCommentDto goodsSkuCommentDto)
			throws GlobalException {
		Page<GoodsSkuCommentDto> goodsSkuCommentList = goodsSkuCommentService.getListGoodsSkuComment(page, goodsSkuCommentDto);
		return new ResponseInfo(goodsSkuCommentList);

	}

	/**
	 * Description 删除商品sku评价
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品评价id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/cancelGoodsSkuComment", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelGoodsSkuComment(Long id) throws GlobalException {
		return new ResponseInfo(goodsSkuCommentService.removeGoodsSkuComment(id));
	}

	/**
	 * Description 添加商品回复评价
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品评价id
	 * @param replyText
	 *            商品回复
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/addGoodsSkuCommentReply", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addGoodsSkuCommentReply(Long id, String replyText) throws GlobalException {
		return new ResponseInfo(goodsSkuCommentService.saveGoodsSkuCommentReply(id, replyText));
	}
	
	/**
	 * Description 查看商品详细评价
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品评价id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/findGoodsSkuCommentById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo findGoodsSkuCommentById(Long id) throws GlobalException {
		GoodsSkuCommentDto goodsSkuCommentDto = goodsSkuCommentService.findGoodsSkuCommentById(id);
		return new ResponseInfo(goodsSkuCommentDto);
	}

}
