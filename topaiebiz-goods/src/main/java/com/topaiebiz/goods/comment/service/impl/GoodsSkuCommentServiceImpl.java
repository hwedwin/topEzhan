package com.topaiebiz.goods.comment.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.topaiebiz.system.security.util.SecurityContextUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.goods.category.backend.dao.BackendCategoryAttrDao;
import com.topaiebiz.goods.category.backend.entity.BackendCategoryAttrEntity;
import com.topaiebiz.goods.comment.dao.GoodsSkuCommentDao;
import com.topaiebiz.goods.comment.dao.GoodsSkuCommentPictureDao;
import com.topaiebiz.goods.comment.dto.GoodsSkuCommentDto;
import com.topaiebiz.goods.comment.dto.GoodsSkuCommentPictureDto;
import com.topaiebiz.goods.comment.entity.GoodsSkuCommentEntity;
import com.topaiebiz.goods.comment.entity.GoodsSkuCommentPictureEntity;
import com.topaiebiz.goods.comment.exception.GoodsSkuCommentExceptionEnum;
import com.topaiebiz.goods.comment.service.GoodsSkuCommentService;
import com.topaiebiz.goods.sku.dao.GoodsSkuDao;
import com.topaiebiz.goods.sku.dto.StoreOrderDetailDto;
import com.topaiebiz.goods.sku.entity.GoodsSkuEntity;
import com.topaiebiz.goods.sku.exception.GoodsSkuExceptionEnum;

/**
 * Description 商品评价实现类 
 * 
 * Author Hedda 
 *    
 * Date 2017年10月2日 下午8:10:06 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
@Service
public class GoodsSkuCommentServiceImpl implements GoodsSkuCommentService{

	@Autowired
	private GoodsSkuCommentDao goodsSkuComentDao;
	
	@Autowired
	private GoodsSkuCommentPictureDao goodsSkuCommentPictureDao;
	
	@Autowired
	private GoodsSkuDao goodsSkuDao;
	
	@Autowired
	private BackendCategoryAttrDao backendCategoryAttrDao;

	@Override
	public Page<GoodsSkuCommentDto> getNoReplyListGoodsSkuComment(Page<GoodsSkuCommentDto> page,
			GoodsSkuCommentDto goodsSkuCommentDto) throws GlobalException {
		//获得到店铺的id
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		if(storeId != null) {
			goodsSkuCommentDto.setBelongStore(storeId);
		}
		List<GoodsSkuCommentDto> listGoodsSkuComment = goodsSkuComentDao.selectNoReplyListGoodsSkuComment(page,goodsSkuCommentDto);
		for (GoodsSkuCommentDto goodsSkuCommentDto2 : listGoodsSkuComment) {
			//查询评价列表中的评价图片
			List<GoodsSkuCommentPictureDto> selectGoodsSkuCommentPicture = goodsSkuCommentPictureDao.selectGoodsSkuCommentPictureDto(goodsSkuCommentDto2.getId());
			if(!(selectGoodsSkuCommentPicture == null || selectGoodsSkuCommentPicture.size() == 0)) {
				goodsSkuCommentDto2.setGoodsSkuCommentPictureDtos(selectGoodsSkuCommentPicture);
			}
		}
		page.setRecords(listGoodsSkuComment);
		return page;
	}
	
	@Override
	public Page<GoodsSkuCommentDto> getReplyListGoodsSkuComment(Page<GoodsSkuCommentDto> page,
			GoodsSkuCommentDto goodsSkuCommentDto) {
		//获得到店铺的id
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		if(storeId != null) {
			goodsSkuCommentDto.setBelongStore(storeId);
		}
		List<GoodsSkuCommentDto> listGoodsSkuComment = goodsSkuComentDao.selectReplyListGoodsSkuComment(page,goodsSkuCommentDto);
		for (GoodsSkuCommentDto goodsSkuCommentDto2 : listGoodsSkuComment) {
			//查询评价列表中的评价图片
			List<GoodsSkuCommentPictureDto> selectGoodsSkuCommentPicture = goodsSkuCommentPictureDao.selectGoodsSkuCommentPictureDto(goodsSkuCommentDto2.getId());
			if(!(selectGoodsSkuCommentPicture == null || selectGoodsSkuCommentPicture.size() == 0)) {
				goodsSkuCommentDto2.setGoodsSkuCommentPictureDtos(selectGoodsSkuCommentPicture);
			}
		}
		page.setRecords(listGoodsSkuComment);
		return page;
	}

	@Override
	public Integer removeGoodsSkuComment(Long id) throws GlobalException {
		Integer i = 0;
		/**判断id是非空*/
		if(id == null) {
			throw new GlobalException(GoodsSkuCommentExceptionEnum.GOODSSKUCOMMENT_ID_NOT_NULL);
		}
		GoodsSkuCommentEntity goodsSkuComment = goodsSkuComentDao.selectById(id);
		/**判断id是非存在*/
		if(goodsSkuComment == null) {
			throw new GlobalException(GoodsSkuCommentExceptionEnum.GOODSSKUCOMMENT_ID_NOT_EXIST);
		}
		List<GoodsSkuCommentPictureEntity> goodsSkuCommentPictures = goodsSkuCommentPictureDao.selectGoodsSkuCommentPicture(id);
		/**删除评价下面对应的图片*/
		if(goodsSkuCommentPictures !=null) {
			for (GoodsSkuCommentPictureEntity goodsSkuCommentPictureEntity : goodsSkuCommentPictures) {
				i = goodsSkuCommentPictureDao.deleteGoodsSkuCommentPicture(goodsSkuCommentPictureEntity.getId());
			}
		}
		i = goodsSkuComentDao.deleteGoodsSkuComment(id);
		return i;
	}

	@Override
	public Integer saveGoodsSkuCommentReply(Long id, String replyText) throws GlobalException {
		/**判断id是非为空*/
		if(id == null) {
			throw new GlobalException(GoodsSkuCommentExceptionEnum.GOODSSKUCOMMENT_ID_NOT_NULL);
		}
		GoodsSkuCommentEntity goodsSkuComment = goodsSkuComentDao.selectById(id);
		/**判断id是否存在*/
		if(goodsSkuComment == null) {
			throw new GlobalException(GoodsSkuCommentExceptionEnum.GOODSSKUCOMMENT_ID_NOT_EXIST);
		}
		goodsSkuComment.setReplyText(replyText);
		goodsSkuComment.setReplyTime(new Date());
		goodsSkuComment.setLastModifiedTime(new Date());
		goodsSkuComment.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		return goodsSkuComentDao.updateById(goodsSkuComment);
	}

	@Override
	public Integer saveGoodsSkuCommentDto(GoodsSkuCommentEntity goodsSkuComment,
			List<GoodsSkuCommentPictureEntity> goodsSkuCommentPictureEntities,Long memberId) {
		GoodsSkuEntity goodsSkuEntity = goodsSkuDao.selectById(goodsSkuComment.getSkuId());
		if(goodsSkuEntity == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSKU_ID_NOT_EXIST);
		}
		Integer i = 0;
		/**添加商品评价信息*/
		goodsSkuComment.setCreatedTime(new Date());
		goodsSkuComment.setCreatorId(memberId);
		i = goodsSkuComentDao.insert(goodsSkuComment);
		/**评价完毕修改评价状态*/
		i = goodsSkuComentDao.updateOrderBySkuId(goodsSkuComment.getOrderId(),goodsSkuComment.getSkuId());
		Long commentId = goodsSkuComment.getId();
		/**添加商品评价图片信息*/
		if(!(goodsSkuCommentPictureEntities == null || goodsSkuCommentPictureEntities.size() == 0)) {
			for (GoodsSkuCommentPictureEntity goodsSkuCommentPictureEntity : goodsSkuCommentPictureEntities) {
				goodsSkuCommentPictureEntity.setCommentId(commentId);
				goodsSkuCommentPictureEntity.setCreatedTime(new Date());
				goodsSkuCommentPictureEntity.setCreatorId(memberId);
				i = goodsSkuCommentPictureDao.insert(goodsSkuCommentPictureEntity);
			}
		}
		return i;
	}

	@Override
	public GoodsSkuCommentDto findGoodsSkuCommentById(Long id) throws GlobalException {
		/**判断id是非为空*/
		if(id == null) {
			throw new GlobalException(GoodsSkuCommentExceptionEnum.GOODSSKUCOMMENT_ID_NOT_NULL);
		}
		GoodsSkuCommentDto goodsSkuCommentDto = goodsSkuComentDao.selectGoodsSkuComentById(id);
		/**判断id是否存在*/
		if(goodsSkuCommentDto == null) {
			throw new GlobalException(GoodsSkuCommentExceptionEnum.GOODSSKUCOMMENT_ID_NOT_EXIST);
		}
		if(goodsSkuCommentDto != null) {
			//查询评价列表中的评价图片
			List<GoodsSkuCommentPictureDto> selectGoodsSkuCommentPicture = goodsSkuCommentPictureDao.selectGoodsSkuCommentPictureDto(goodsSkuCommentDto.getId());
			if(!(selectGoodsSkuCommentPicture == null || selectGoodsSkuCommentPicture.size() == 0)) {
				goodsSkuCommentDto.setGoodsSkuCommentPictureDtos(selectGoodsSkuCommentPicture);
			}
		}
		return goodsSkuCommentDto;
	}

	@Override
	public Page<GoodsSkuCommentDto> getListGoodsSkuComment(Page<GoodsSkuCommentDto> page,
			GoodsSkuCommentDto goodsSkuCommentDto) {
		List<GoodsSkuCommentDto> listGoodsSkuComment = goodsSkuComentDao.selectListGoodsSkuComment(page,goodsSkuCommentDto);
		for (GoodsSkuCommentDto goodsSkuCommentDto2 : listGoodsSkuComment) {
			//查询评价列表中的评价图片
			List<GoodsSkuCommentPictureDto> selectGoodsSkuCommentPicture = goodsSkuCommentPictureDao.selectGoodsSkuCommentPictureDto(goodsSkuCommentDto2.getId());
			if(!(selectGoodsSkuCommentPicture == null || selectGoodsSkuCommentPicture.size() == 0)) {
				goodsSkuCommentDto2.setGoodsSkuCommentPictureDtos(selectGoodsSkuCommentPicture);
			}
		}
		page.setRecords(listGoodsSkuComment);
		return page;
	}

	@Override
	public List<GoodsSkuCommentDto> getGoodsSkuCommentListBySkuId(Long skuId) {
		List<GoodsSkuCommentDto> goodsSkuCommentDtos = goodsSkuComentDao.selectGoodsSkuCommentListBySkuId(skuId);
		for (GoodsSkuCommentDto goodsSkuCommentDto2 : goodsSkuCommentDtos) {
			//查询评价列表中的评价图片
			List<GoodsSkuCommentPictureDto> selectGoodsSkuCommentPicture = goodsSkuCommentPictureDao.selectGoodsSkuCommentPictureDto(goodsSkuCommentDto2.getId());
			if(!(selectGoodsSkuCommentPicture == null || selectGoodsSkuCommentPicture.size() == 0)) {
				goodsSkuCommentDto2.setGoodsSkuCommentPictureDtos(selectGoodsSkuCommentPicture);
			}
		}
		return goodsSkuCommentDtos;
	}

	@Override
	public List<StoreOrderDetailDto> getStoreOrderDetailList(Long orderId) {
		List<StoreOrderDetailDto> storeOrderDetailDtos = goodsSkuComentDao.selectStoreOrderDetailList(orderId);
		if(!(storeOrderDetailDtos == null || storeOrderDetailDtos.size() == 0)) {
			for (StoreOrderDetailDto storeOrderDetailDto : storeOrderDetailDtos) {
				String fieldValue = storeOrderDetailDto.getFieldValue();
				String saleFieldValue1 = "";
				String[] strss = fieldValue.split(",");
				for (int i = 0; i < strss.length; i++) {
					String[] strs1 = strss[i].split(":");
					BackendCategoryAttrEntity selectById = backendCategoryAttrDao.selectById(strs1[0]);
					String name = selectById.getName();
					String value = strs1[1];
					saleFieldValue1 = name + ":" + value + "  " + saleFieldValue1;
					storeOrderDetailDto.setSaleValue(saleFieldValue1);
				}
			}
		}
		return storeOrderDetailDtos;
	}

	@Override
	public GoodsSkuCommentDto getGoodsSkuCommentBySkuIdAndOrderId(Long skuId, Long orderId) {
		return goodsSkuComentDao.selectGoodsSkuCommentBySkuIdAndOrderId(skuId,orderId);
	}

}
