package com.topaiebiz.goods.spu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.topaiebiz.system.security.util.SecurityContextUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.goods.category.backend.dao.BackendCategoryDao;
import com.topaiebiz.goods.category.backend.dto.BackendCategoryDto;
import com.topaiebiz.goods.category.backend.dto.BackendCategorysDto;
import com.topaiebiz.goods.category.backend.entity.BackendCategoryEntity;
import com.topaiebiz.goods.category.backend.exception.BackendCategoryExceptionEnum;
import com.topaiebiz.goods.spu.dao.GoodsSpuAttrDao;
import com.topaiebiz.goods.spu.dao.GoodsSpuDao;
import com.topaiebiz.goods.spu.dao.GoodsSpuPictureDao;
import com.topaiebiz.goods.spu.dto.GoodsSpuAttrBaseDto;
import com.topaiebiz.goods.spu.dto.GoodsSpuAttrDto;
import com.topaiebiz.goods.spu.dto.GoodsSpuAttrSaleDto;
import com.topaiebiz.goods.spu.dto.GoodsSpuAttrSaleKeyAndValueDto;
import com.topaiebiz.goods.spu.dto.GoodsSpuAttrSaleKeyDto;
import com.topaiebiz.goods.spu.dto.GoodsSpuAttrSaleValueDto;
import com.topaiebiz.goods.spu.dto.GoodsSpuDto;
import com.topaiebiz.goods.spu.dto.GoodsSpuPictureDto;
import com.topaiebiz.goods.spu.entity.GoodsSpuAttrEntity;
import com.topaiebiz.goods.spu.entity.GoodsSpuEntity;
import com.topaiebiz.goods.spu.entity.GoodsSpuPictureEntity;
import com.topaiebiz.goods.spu.exception.GoodsSpuExceptionEnum;
import com.topaiebiz.goods.spu.service.GoodsSpuService;

/**
 * Description 商品spu实现类 
 * 
 * Author Hedda 
 *    
 * Date 2017年9月29日 下午8:26:16 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
@Service
public class GoodsSpuServiceImpl implements GoodsSpuService{
	
	@Autowired
	private GoodsSpuDao goodsSpuDao;
	
	/** 商品spu属性。*/
	@Autowired
	private GoodsSpuAttrDao goodsSpuAttrDao;
	
	/** 商品spu图片。*/
	@Autowired
	private GoodsSpuPictureDao goodsSpuPictureDao;
	
	/** 后台类目。*/
	@Autowired
	private BackendCategoryDao backendCategoryDao;

	@Override
	public Page<GoodsSpuDto> getListGoodsSpuDto(Page<GoodsSpuDto> page, GoodsSpuDto goodsSpuDto) {
		page.setRecords(goodsSpuDao.selectListGoodsSpuDto(page,goodsSpuDto));
		return page;
	}

	@Override
	public void removeGoodsSpu(Long[] ids) throws GlobalException {
		/**判断ids是非为空*/
		if(ids == null) {
			throw new GlobalException(GoodsSpuExceptionEnum.GOODSSPU_ID_NOT_NULL); 
		}
		/**判断ids是非存在*/
		for (Long long1 : ids) {
			GoodsSpuEntity selectById = goodsSpuDao.selectById(long1);
			if(selectById == null) {
				throw new GlobalException(GoodsSpuExceptionEnum.GOODSSPU_ID_NOT_EXIST); 
			}
		}
		for (Long spuId : ids) {
		  /**查询商品spu所对应的商品spu属性*/
	      List<GoodsSpuAttrEntity> goodsSpuAttr = goodsSpuAttrDao.selectGoodsSpuAttrBySpuId(spuId);
	      if(!(goodsSpuAttr == null || goodsSpuAttr.size() == 0)) {
	    	  for (GoodsSpuAttrEntity goodsSpuAttrEntity : goodsSpuAttr) {
		    	  goodsSpuAttrDao.deleteGoodsSpuAttr(goodsSpuAttrEntity.getId());
			  }
	      }
	      /**查询商品spu所对应的商品spu图片*/
	      List<GoodsSpuPictureEntity> goodsSpuPicture = goodsSpuPictureDao.selectGoodsSpuPictureBySpuId(spuId);
	      if(!(goodsSpuPicture == null || goodsSpuPicture.size() == 0)) {
	    	  for (GoodsSpuPictureEntity goodsSpuPictureEntity : goodsSpuPicture) {
		    	  goodsSpuPictureDao.deleteGoodsSpuPicture(goodsSpuPictureEntity.getId());
		      }
	      }
	      goodsSpuDao.deleteGoodsSpu(spuId);
		}
	}

	@Override
	public List<BackendCategorysDto> getRecentlyCategoryList() {
		List<BackendCategorysDto> backendCategorysListDto = new ArrayList<BackendCategorysDto>();
		/** 查询出最近商品使用后台类目。*/
		List<BackendCategoryDto> selectRecentlyCategoryList = backendCategoryDao.selectRecentlyCategoryList();
		/** 创建一个新list集合。*/
		List<BackendCategoryDto> resultList = new ArrayList<BackendCategoryDto>();
		if(!(selectRecentlyCategoryList == null || selectRecentlyCategoryList.size() == 0)) {
			for (BackendCategoryDto backendCategoryDto : selectRecentlyCategoryList) {
				/** 判断是否包含本后台类目。*/
				if(!isContain(resultList, backendCategoryDto)) {
					resultList.add(backendCategoryDto);
				}
				/** 判断resultList长度是否为10*/
				if(resultList.size() == 10) {
					break;
				}
			}
		}
		//查询出第三级类目的前两级类目
		if(!(resultList == null || resultList.size() == 0)) {
			for (BackendCategoryDto backendCategoryDto : resultList) {
				//创建一个BackendCategoryDto集合
				List<BackendCategoryDto> reaultMsg = new ArrayList<BackendCategoryDto>();
				//第三级parentId查询第二级类目
				BackendCategoryDto twobackendCategoryDto = backendCategoryDao.selectTwoBackendCategoryDtoByParentId(backendCategoryDto.getParentId());
				//第二级parentId查询第一级类目
				BackendCategoryDto onebackendCategoryDto = backendCategoryDao.selectOneBackendCategoryDtoByParentId(twobackendCategoryDto.getParentId());
				//将一二三级类目添加到BackendCategoryDto集合
				reaultMsg.add(onebackendCategoryDto);
				reaultMsg.add(twobackendCategoryDto);
				reaultMsg.add(backendCategoryDto);
				//创建backendCategorysDto对象
				BackendCategorysDto backendCategorysDto = new BackendCategorysDto();
				backendCategorysDto.setBackendCategoryDto(reaultMsg);
				backendCategorysListDto.add(backendCategorysDto);
			}
		}
		return backendCategorysListDto;
	}

	/** 判断是否包含。*/
	private boolean isContain (List<BackendCategoryDto> list, BackendCategoryDto backendCategoryDto) {
		for (BackendCategoryDto backDto : list) {
			if(backendCategoryDto.getId().equals(backDto.getId())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Integer saveGoodsSpu(GoodsSpuEntity goodsSpu, List<GoodsSpuAttrEntity> goodsSpuAttrEntities,
			List<GoodsSpuPictureEntity> goodsSpuPictureEntities) throws GlobalException{
		Integer i = 0;
		/**判断商品spu名称是否重复*/
		GoodsSpuEntity goodsSpuByName = goodsSpuDao.selectGoodsSpuByName(goodsSpu.getName());
		if(goodsSpuByName != null) {
			throw new GlobalException(GoodsSpuExceptionEnum.GOODSSPU_NAME_NOT_REPETITION);
		}
		/**添加商品spu信息*/
		goodsSpu.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		goodsSpu.setCreatedTime(new Date());
		i = goodsSpuDao.insert(goodsSpu);
		Long spuId = goodsSpu.getId();
		/**添加商品spu图片信息*/
		if(!(goodsSpuPictureEntities == null || goodsSpuPictureEntities.size() == 0)) {
			for (GoodsSpuPictureEntity goodsSpuPictureEntity : goodsSpuPictureEntities) {
				goodsSpuPictureEntity.setSpuId(spuId);
				goodsSpuPictureEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
				goodsSpuPictureEntity.setCreatedTime(new Date());
				i = goodsSpuPictureDao.insert(goodsSpuPictureEntity);
			}
		}
		/**添加商品spu属性信息*/
		if(!(goodsSpuAttrEntities == null || goodsSpuAttrEntities.size() == 0)) {
			for (GoodsSpuAttrEntity goodsSpuAttrEntity : goodsSpuAttrEntities) {
				goodsSpuAttrEntity.setSpuId(spuId);
				goodsSpuAttrEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
				goodsSpuAttrEntity.setCreatedTime(new Date());
				i = goodsSpuAttrDao.insert(goodsSpuAttrEntity);
			}
		}
		
		return i;
	}

	@Override
	public Integer modifyGoodsSpu(GoodsSpuDto goodsSpuDto, List<GoodsSpuAttrDto> goodsSpuAttrDtos,
			List<GoodsSpuPictureDto> goodsSpuPictureDtos) throws GlobalException {
		Integer i = 0;
		/**判断商品spu名称是否重复*/
		GoodsSpuDto goodsSpuByName = goodsSpuDao.selectGoodsSpuByNameAndId(goodsSpuDto);
		if(goodsSpuByName != null) {
			throw new GlobalException(GoodsSpuExceptionEnum.GOODSSPU_NAME_NOT_REPETITION);
		}
		/**修改商品spu信息*/
		GoodsSpuEntity goodsSpu = goodsSpuDao.selectById(goodsSpuDto.getId());
		BeanUtils.copyProperties(goodsSpuDto, goodsSpu);
		goodsSpu.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		goodsSpu.setLastModifiedTime(new Date());
		i = goodsSpuDao.updateById(goodsSpu);
		/**商品spu的id*/
		Long spuId = goodsSpu.getId();
		/**删除当前商品spu的属性*/
		List<GoodsSpuAttrEntity> goodsSpuAttrsBySpuId = goodsSpuAttrDao.selectGoodsSpuAttrBySpuId(goodsSpuDto.getId());
		if(!(goodsSpuAttrsBySpuId == null || goodsSpuAttrsBySpuId.size() == 0)) {
			for (GoodsSpuAttrEntity goodsSpuAttrEntity : goodsSpuAttrsBySpuId) {
				goodsSpuAttrDao.deleteGoodsSpuAttr(goodsSpuAttrEntity.getId());
			}
		}
		/**删除当前商品spu的图片*/
		List<GoodsSpuPictureEntity> goodsSpuPictureBySpuId = goodsSpuPictureDao.selectGoodsSpuPictureBySpuId(goodsSpuDto.getId());
		if(!(goodsSpuPictureBySpuId == null || goodsSpuPictureBySpuId.size() == 0)) {
			for (GoodsSpuPictureEntity goodsSpuPictureEntity : goodsSpuPictureBySpuId) {
				goodsSpuPictureDao.deleteGoodsSpuPicture(goodsSpuPictureEntity.getId());
			}
		}
		/**添加商品spu图片信息*/
		if(!(goodsSpuPictureDtos == null || goodsSpuPictureDtos.size() == 0)) {
			for (GoodsSpuPictureDto goodsSpuPictureDto : goodsSpuPictureDtos) {
				if(goodsSpuPictureDto.getName() == null) {
					throw new GlobalException(GoodsSpuExceptionEnum.GOODSSPU_PICTURE_NAME_NOT_NULL);
				}
				GoodsSpuPictureEntity goodsSpuPicture = new GoodsSpuPictureEntity();
				BeanUtils.copyProperties(goodsSpuPictureDto, goodsSpuPicture);
				goodsSpuPicture.setSpuId(spuId);
				goodsSpuPicture.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
				goodsSpuPicture.setCreatedTime(new Date());
				i = goodsSpuPictureDao.insert(goodsSpuPicture);
			}
		}
		/**添加商品spu属性信息*/
		if(!(goodsSpuAttrDtos == null || goodsSpuAttrDtos.size() == 0)) {
			for (GoodsSpuAttrDto goodsSpuAttrDto : goodsSpuAttrDtos) {
				if(goodsSpuAttrDto.getPrice() == null) {
					throw new GlobalException(GoodsSpuExceptionEnum.GOODSSPU_ATTR_PRICE_NOT_NULL);
				}
				GoodsSpuAttrEntity goodsSpuAttr = new GoodsSpuAttrEntity();
				BeanUtils.copyProperties(goodsSpuAttrDto, goodsSpuAttr);
				goodsSpuAttr.setSpuId(spuId);
				goodsSpuAttr.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
				goodsSpuAttr.setCreatedTime(new Date());
				i = goodsSpuAttrDao.insert(goodsSpuAttr);
			}
		}
		return i;
	}

	@Override
	public GoodsSpuDto findGoodsSpuById(Long id) throws GlobalException{
		/**判断商品spu的id是否为空*/
		if(id == null) {
			throw new GlobalException(GoodsSpuExceptionEnum.GOODSSPU_ID_NOT_NULL);
		}
		GoodsSpuDto goodsSpu = goodsSpuDao.selectGoodsSpuById(id);
		/**判断商品spu的id是否存在*/
		if(goodsSpu == null) {
			throw new GlobalException(GoodsSpuExceptionEnum.GOODSSPU_ID_NOT_EXIST);
		}
		List<BackendCategoryDto> backendCategoryDtos = new ArrayList<BackendCategoryDto>();
		if(goodsSpu.getBelongCategory()!=null) {
			BackendCategoryDto backendCategory3 = backendCategoryDao.selectBackendCategoryById(goodsSpu.getBelongCategory());
			BackendCategoryDto backendCategory2 = backendCategoryDao.selectBackendCategoryById(backendCategory3.getParentId());
			BackendCategoryDto backendCategory1 = backendCategoryDao.selectBackendCategoryById(backendCategory2.getParentId());
			backendCategoryDtos.add(backendCategory1);
			backendCategoryDtos.add(backendCategory2);
			backendCategoryDtos.add(backendCategory3);
		}
		goodsSpu.setBackendCategoryDtos(backendCategoryDtos);
		return goodsSpu;
	}

	@Override
	public List<GoodsSpuAttrDto> findGoodsSpuAttrById(Long spuId) throws GlobalException {
		/**判断商品spu的id是否为空*/
    	if(spuId == null) {
			throw new GlobalException(GoodsSpuExceptionEnum.GOODSSPU_ID_NOT_NULL);
		}
		GoodsSpuEntity goodsSpu = goodsSpuDao.selectById(spuId);
		/**判断商品spu的id是否存在*/
		if(goodsSpu == null) {
			throw new GlobalException(GoodsSpuExceptionEnum.GOODSSPU_ID_NOT_EXIST);
		}
		GoodsSpuDto goodsSpuDto = goodsSpuDao.selectGoodsSpuById(spuId);
		//查询商品spu对象的属性
		List<GoodsSpuAttrDto> goodsSpuAttrs = goodsSpuAttrDao.selectListGoodsSpuAttrs(spuId);
		if(!(goodsSpuAttrs == null || goodsSpuAttrs.size() == 0)) {
			for (GoodsSpuAttrDto goodsSpuAttrDto : goodsSpuAttrs) {
				//取出每个销售属性
				String saleFieldValue = goodsSpuAttrDto.getSaleFieldValue();
				if(saleFieldValue != null) {
					String[]  strs=saleFieldValue.split(",");
					//创建销售属性集合
					List<GoodsSpuAttrSaleDto> goodsSpuAttrSaleDtos = new ArrayList<GoodsSpuAttrSaleDto>();
					//创建销售属性中值的集合
					List<GoodsSpuAttrSaleValueDto> goodsSpuAttrSaleValueDtos = new ArrayList<GoodsSpuAttrSaleValueDto>();
					//创建销售属性中键的集合
					List<GoodsSpuAttrSaleKeyDto> goodsSpuAttrSaleKeyDtos = new ArrayList<GoodsSpuAttrSaleKeyDto>();
 					//创建销售属性中键与值得集合
					List<GoodsSpuAttrSaleKeyAndValueDto> goodsSpuAttrSaleKeyAndValueDtos = new ArrayList<GoodsSpuAttrSaleKeyAndValueDto>();
					for(int i=0;i<strs.length;i++){
					    String[] strs1=strs[i].split(":");
					    //创建销售属性对象
					    GoodsSpuAttrSaleDto goodsSpuAttrSaleDto = new GoodsSpuAttrSaleDto();
					    //创建销售属性value对象
					    GoodsSpuAttrSaleValueDto goodsSpuAttrSaleValueDto = new GoodsSpuAttrSaleValueDto();
					    //创建销售属性key对象
					    GoodsSpuAttrSaleKeyDto goodsSpuAttrSaleKeyDto = new GoodsSpuAttrSaleKeyDto();
					    //创建销售属性中键与值得集合
						GoodsSpuAttrSaleKeyAndValueDto goodsSpuAttrSaleKeyAndValueDto = new GoodsSpuAttrSaleKeyAndValueDto();
					    //创建销售属性中的值得集合
						List<GoodsSpuAttrSaleValueDto> goodsSpuAttrSaleValueDtos2 = new ArrayList<GoodsSpuAttrSaleValueDto>();
						//创建销售属性中的值得对象
						GoodsSpuAttrSaleValueDto goodsSpuAttrSaleValueDto2 = new GoodsSpuAttrSaleValueDto();
						goodsSpuAttrSaleDto.setId(strs1[0]);
					    //保存销售属性中的键值
					    goodsSpuAttrSaleKeyAndValueDto.setSaleId(strs1[0]);
					    Long imageField = goodsSpuDto.getImageField();
					    //判断是否上传sku图片
					    if(imageField != null) {
					    	String imageFileld = Long.toString(imageField);
						    if(imageFileld.equals(goodsSpuAttrSaleKeyAndValueDto.getSaleId())) {
						    	goodsSpuAttrSaleValueDto2.setImageurl(goodsSpuAttrDto.getSaleImage());
						    	goodsSpuAttrSaleKeyAndValueDto.setImageField(imageField);
						    }
					    }
					    //保存key值
					    goodsSpuAttrSaleKeyDto.setKeyName(strs1[0]);
					    goodsSpuAttrSaleDto.setSaleName(strs1[1]);
					    //保存value值
					    goodsSpuAttrSaleValueDto.setValueName(strs1[1]);
					    goodsSpuAttrSaleValueDto2.setValueName(strs1[1]);
					    //添加到集合中
					    goodsSpuAttrSaleDtos.add(goodsSpuAttrSaleDto);
					    goodsSpuAttrSaleValueDtos.add(goodsSpuAttrSaleValueDto);
					    goodsSpuAttrSaleKeyDtos.add(goodsSpuAttrSaleKeyDto);
					    goodsSpuAttrSaleKeyAndValueDtos.add(goodsSpuAttrSaleKeyAndValueDto);
					    goodsSpuAttrSaleValueDtos2.add(goodsSpuAttrSaleValueDto2);
					    goodsSpuAttrSaleKeyAndValueDto.setGoodsSpuAttrSaleValueDtos(goodsSpuAttrSaleValueDtos2);
					    //添加到属性集合中
					    goodsSpuAttrDto.setGoodsSpuAttrSaleDtos(goodsSpuAttrSaleDtos);
					    goodsSpuAttrDto.setGoodsSpuAttrSaleKeyDto(goodsSpuAttrSaleKeyDtos);
					    goodsSpuAttrDto.setGoodsSpuAttrSaleValueDto(goodsSpuAttrSaleValueDtos);
					    goodsSpuAttrDto.setGoodsSpuAttrSaleKeyAndValueDtos(goodsSpuAttrSaleKeyAndValueDtos);
					    goodsSpuAttrDto.setGoodsSpuAttrSaleKeyAndValueDtos(goodsSpuAttrSaleKeyAndValueDtos);
					}
				}
				//取出每个基本属性
				String baseFieldValue = goodsSpuAttrDto.getBaseFieldValue();
				if(baseFieldValue !=null) {
					String[]  strss=baseFieldValue.split(",");
					//创建基本属性集合
					List<GoodsSpuAttrBaseDto> goodsSpuAttrBaseDtos = new ArrayList<GoodsSpuAttrBaseDto>();
					for(int i=0;i<strss.length;i++){
						String[]  strss1=strss[i].split(":");
					    GoodsSpuAttrBaseDto goodsSpuAttrBaseDto = new GoodsSpuAttrBaseDto();
					    goodsSpuAttrBaseDto.setId(strss1[0]);
					    goodsSpuAttrBaseDto.setBaseName(strss1[1]);
					    goodsSpuAttrBaseDtos.add(goodsSpuAttrBaseDto);
					    goodsSpuAttrDto.setGoodsSpuAttrBaseDtos(goodsSpuAttrBaseDtos);
					}
				}
			}
		}
		return goodsSpuAttrs;
	}

	@Override
	public List<GoodsSpuPictureDto> findGoodsSpuPictureById(Long spuId) throws GlobalException {
		/**判断商品spu的id是否为空*/
		if(spuId == null) {
			throw new GlobalException(GoodsSpuExceptionEnum.GOODSSPU_ID_NOT_NULL);
		}
		GoodsSpuEntity goodsSpu = goodsSpuDao.selectById(spuId);
		/**判断商品spu的id是否存在*/
		if(goodsSpu == null) {
			throw new GlobalException(GoodsSpuExceptionEnum.GOODSSPU_ID_NOT_EXIST);
		}
		return goodsSpuPictureDao.selectGoodsSpuPictures(spuId);
	}

	@Override
	public GoodsSpuDto getGoodsSpuByCode(GoodsSpuDto goodsSpuDto) throws GlobalException {
		GoodsSpuDto goodsSpu= goodsSpuDao.selectGoodsSpuBySpuCodeAndId(goodsSpuDto);
		if(goodsSpu != null) {
			throw new GlobalException(GoodsSpuExceptionEnum.GOODSSPU_SPUCODE_NOT_REPETITION);
		}
		return goodsSpu;
	}

	@Override
	public GoodsSpuDto getGoodsSpuByName(GoodsSpuDto goodsSpuDto) throws GlobalException {
		GoodsSpuDto goodsSpu= goodsSpuDao.selectGoodsSpuByNameAndId(goodsSpuDto);
		if(goodsSpu != null) {
			throw new GlobalException(GoodsSpuExceptionEnum.GOODSSPU_SPUCODE_NOT_REPETITION);
		}
		return goodsSpu;
	}

	@Override
	public Page<GoodsSpuDto> getGoodsSpuListByBelongCategory(Page<GoodsSpuDto> page, GoodsSpuDto goodsSpuDto) {
		if(goodsSpuDto.getBelongCategory() == null) {
			throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORY_ID_NOT_NULL);
		}
		BackendCategoryEntity selectById = backendCategoryDao.selectById(goodsSpuDto.getBelongCategory());
		if(selectById == null) {
			throw new GlobalException(BackendCategoryExceptionEnum.BACKENDCATEGORY_ID_NOT_EXIST);
		}
		page.setRecords(goodsSpuDao.selectGoodsSpuListByBelongCategory(page,goodsSpuDto));
		return page;
	}

	@Override
	public Integer modifyGoodsSpuCategory(Long spuId, Long belongCategory) throws GlobalException {
		/**判断商品spu的id是否为空*/
		if(spuId == null) {
			throw new GlobalException(GoodsSpuExceptionEnum.GOODSSPU_ID_NOT_NULL);
		}
		GoodsSpuEntity goodsSpu = goodsSpuDao.selectById(spuId);
		/**判断商品spu的id是否存在*/
		if(goodsSpu == null) {
			throw new GlobalException(GoodsSpuExceptionEnum.GOODSSPU_ID_NOT_EXIST);
		}
		goodsSpu.setBelongCategory(belongCategory);
		goodsSpu.setLastModifiedTime(new Date());
		goodsSpu.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		return goodsSpuDao.updateById(goodsSpu);
	}

}
