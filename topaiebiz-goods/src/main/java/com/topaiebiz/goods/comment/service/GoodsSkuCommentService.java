package com.topaiebiz.goods.comment.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.goods.comment.dto.GoodsSkuCommentDto;
import com.topaiebiz.goods.comment.entity.GoodsSkuCommentEntity;
import com.topaiebiz.goods.comment.entity.GoodsSkuCommentPictureEntity;
import com.topaiebiz.goods.sku.dto.StoreOrderDetailDto;

/**
 * Description 商品评价 接口
 * 
 * Author Hedda
 * 
 * Date 2017年10月2日 下午8:09:45
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
public interface GoodsSkuCommentService {

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
	Page<GoodsSkuCommentDto> getNoReplyListGoodsSkuComment(Page<GoodsSkuCommentDto> page,
			GoodsSkuCommentDto goodsSkuCommentDto) throws GlobalException;

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
	Page<GoodsSkuCommentDto> getReplyListGoodsSkuComment(Page<GoodsSkuCommentDto> page,
			GoodsSkuCommentDto goodsSkuCommentDto);

	/**
	 * Description 商品评价删除
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品评价id
	 * @return
	 * @throws GlobalException
	 */
	Integer removeGoodsSkuComment(Long id) throws GlobalException;

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
	Integer saveGoodsSkuCommentReply(Long id, String replyText) throws GlobalException;

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
	GoodsSkuCommentDto findGoodsSkuCommentById(Long id) throws GlobalException;

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
	 */
	Page<GoodsSkuCommentDto> getListGoodsSkuComment(Page<GoodsSkuCommentDto> page,
			GoodsSkuCommentDto goodsSkuCommentDto);

	/**
	 * Description 添加商品评价
	 * 
	 * Author Hedda
	 * 
	 * @param goodsSkuComment
	 *            商品sku评价
	 * @param goodsSkuCommentPictureEntities
	 *            商品sku评价图片
	 * @return
	 */
	Integer saveGoodsSkuCommentDto(GoodsSkuCommentEntity goodsSkuComment,
			List<GoodsSkuCommentPictureEntity> goodsSkuCommentPictureEntities, Long memberId);

	/**
	 * Description 根据skuId查询商品评价
	 * 
	 * Author Hedda
	 * 
	 * @param skuId
	 * @return
	 */
	List<GoodsSkuCommentDto> getGoodsSkuCommentListBySkuId(Long skuId);

	/**
	 * Description 根据订单id查询商品 详情
	 * 
	 * Author Hedda
	 * 
	 * @param orderId
	 *            订单id
	 * @return
	 */
	List<StoreOrderDetailDto> getStoreOrderDetailList(Long orderId);

	/**
	 * Description 通过商品id和订单id查询该商品是否评价过
	 * 
	 * Author Hedda
	 * 
	 * @param skuId
	 *            商品skuId
	 * @param orderId
	 *            订单id
	 * @return
	 */
	GoodsSkuCommentDto getGoodsSkuCommentBySkuIdAndOrderId(Long skuId, Long orderId);

}
