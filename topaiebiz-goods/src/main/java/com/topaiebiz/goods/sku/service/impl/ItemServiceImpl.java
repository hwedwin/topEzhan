package com.topaiebiz.goods.sku.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.topaiebiz.system.security.util.SecurityContextUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.goods.category.backend.dao.BackendCategoryAttrDao;
import com.topaiebiz.goods.category.backend.dao.BackendCategoryDao;
import com.topaiebiz.goods.category.backend.dto.BackendCategoryDto;
import com.topaiebiz.goods.category.backend.dto.BackendCategorysDto;
import com.topaiebiz.goods.category.backend.entity.BackendCategoryAttrEntity;
import com.topaiebiz.goods.category.frontend.dao.FrontBackCategoryDao;
import com.topaiebiz.goods.category.frontend.dao.FrontendCategoryDao;
import com.topaiebiz.goods.category.frontend.dto.FrontBackCategoryDto;
import com.topaiebiz.goods.category.frontend.entity.FrontBackCategoryEntity;
import com.topaiebiz.goods.category.frontend.entity.FrontendCategoryEntity;
import com.topaiebiz.goods.comment.dao.GoodsSkuCommentDao;
import com.topaiebiz.goods.comment.dao.GoodsSkuCommentPictureDao;
import com.topaiebiz.goods.comment.dto.GoodsSkuCommentDto;
import com.topaiebiz.goods.comment.dto.GoodsSkuCommentPictureDto;
import com.topaiebiz.goods.sku.dao.GoodsSkuDao;
import com.topaiebiz.goods.sku.dao.ItemDao;
import com.topaiebiz.goods.sku.dao.ItemPictureDao;
import com.topaiebiz.goods.sku.dao.ItemUpdownLogDao;
import com.topaiebiz.goods.sku.dto.GoodsSkuBaseDto;
import com.topaiebiz.goods.sku.dto.GoodsSkuDto;
import com.topaiebiz.goods.sku.dto.GoodsSkuSaleDto;
import com.topaiebiz.goods.sku.dto.GoodsSkuSaleKeyAndValueDto;
import com.topaiebiz.goods.sku.dto.GoodsSkuSaleKeyDto;
import com.topaiebiz.goods.sku.dto.GoodsSkuSaleValueDto;
import com.topaiebiz.goods.sku.dto.ItemAppDto;
import com.topaiebiz.goods.sku.dto.ItemDto;
import com.topaiebiz.goods.sku.dto.ItemPictureDto;
import com.topaiebiz.goods.sku.dto.ItemSellDto;
import com.topaiebiz.goods.sku.dto.PromoTionGoodsDto;
import com.topaiebiz.goods.sku.dto.StoreDistributionGoodsSkuDto;
import com.topaiebiz.goods.sku.entity.FreightTempleteDetailSkuEntity;
import com.topaiebiz.goods.sku.entity.GoodsSkuEntity;
import com.topaiebiz.goods.sku.entity.ItemEntity;
import com.topaiebiz.goods.sku.entity.ItemPictureEntity;
import com.topaiebiz.goods.sku.entity.ItemUpdownLogEntity;
import com.topaiebiz.goods.sku.exception.GoodsSkuExceptionEnum;
import com.topaiebiz.goods.sku.service.ItemService;
import com.topaiebiz.system.moble.security.dto.TokenDto;
import com.topaiebiz.system.moble.security.util.TokenUtil;

/**
 * Description 商品sku实现类
 * 
 * Author Hedda
 * 
 * Date 2017年10月3日 下午7:05:58
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDao itemDao;

	/** 商品item图片。 */
	@Autowired
	private ItemPictureDao itemPictureDao;

	/** 商品item记录上下架。 */
	@Autowired
	private ItemUpdownLogDao itemUpdownLogDao;

	/** 商品item属性，sku。 */
	@Autowired
	private GoodsSkuDao goodsSkuDao;

	/** 后台类目。 */
	@Autowired
	private BackendCategoryDao backendCategoryDao;

	/** 类目属性。 */
	@Autowired
	private BackendCategoryAttrDao backendCategoryAttrDao;

	/** 前后台绑定。 */
	@Autowired
	private FrontBackCategoryDao frontBackCategoryDao;

	/** 前台类目。 */
	@Autowired
	private FrontendCategoryDao frontendCategoryDao;

	/** 评价。 */
	@Autowired
	private GoodsSkuCommentDao goodsSkuCommentDao;

	/** 评价图片。 */
	@Autowired
	private GoodsSkuCommentPictureDao goodsSkuCommentPictureDao;

	@Override
	public Page<ItemDto> getListItemDto(Page<ItemDto> page, ItemDto itemDto) {
		// 商品出售中的商品列表
		List<ItemDto> listItemDto = itemDao.selectListItemDto(page, itemDto);
		if (listItemDto != null) {
			for (ItemDto itemDto2 : listItemDto) {
				// 查询每件商品的销售量
				Long id = itemDto2.getId();
				Long salesVolome = itemDao.selectSalesVolomeById(id);
				if (salesVolome != null) {
					itemDto2.setSalesVolome(salesVolome);
				}
				else {
					itemDto2.setSalesVolome(0L);
				}
				// 查询每件商品的库存
				Long stockNumber = itemDao.selecStockNumbertById(id);
				if (stockNumber != null) {
					itemDto2.setStockNumber(stockNumber);
				}
			}
		}
		page.setRecords(listItemDto);
		return page;
	}

	@Override
	public Page<ItemDto> getMerchantListItemDto(Page<ItemDto> page, ItemDto itemDto) {
		// 获得到店铺的id
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		itemDto.setBelongStore(storeId);
		List<ItemDto> listItemDto = itemDao.selectListItemDto(page, itemDto);
		if (listItemDto != null) {
			for (ItemDto itemDto2 : listItemDto) {
				// 查询每件商品的销售量
				Long id = itemDto2.getId();
				Long salesVolome = itemDao.selectSalesVolomeById(id);
				if (salesVolome != null) {
					itemDto2.setSalesVolome(salesVolome);
				}
				else {
					itemDto2.setSalesVolome(0L);
				}
				// 查询每件商品的库存
				Long stockNumber = itemDao.selecStockNumbertById(id);
				if (stockNumber != null) {
					itemDto2.setStockNumber(stockNumber);
				}
			}
		}
		page.setRecords(listItemDto);
		return page;
	}

	@Override
	public Page<ItemDto> getListStoreItemDto(Page<ItemDto> page, ItemDto itemDto) {
		List<ItemDto> selectListStoreItemDto = itemDao.selectListStoreItemDto(page, itemDto);
		if (selectListStoreItemDto != null) {
			for (ItemDto itemDto2 : selectListStoreItemDto) {
				// 查询每件商品的销售量
				Long id = itemDto2.getId();
				Long salesVolome = itemDao.selectSalesVolomeById(id);
				if (salesVolome != null) {
					itemDto2.setSalesVolome(salesVolome);
				}
				else {
					itemDto2.setSalesVolome(0L);
				}
				// 查询每件商品的库存
				Long stockNumber = itemDao.selecStockNumbertById(id);
				if (stockNumber != null) {
					itemDto2.setStockNumber(stockNumber);
				}

			}
		}
		page.setRecords(selectListStoreItemDto);
		return page;
	}

	@Override
	public Page<ItemDto> getMerchantListStoreItemDto(Page<ItemDto> page, ItemDto itemDto) {
		// 获得到店铺的id
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		itemDto.setBelongStore(storeId);
		List<ItemDto> selectListStoreItemDto = itemDao.selectListStoreItemDto(page, itemDto);
		if (selectListStoreItemDto != null) {
			for (ItemDto itemDto2 : selectListStoreItemDto) {
				// 查询每件商品的销售量
				Long id = itemDto2.getId();
				Long salesVolome = itemDao.selectSalesVolomeById(id);
				if (salesVolome != null) {
					itemDto2.setSalesVolome(salesVolome);
				}
				else {
					itemDto2.setSalesVolome(0L);
				}
				// 查询每件商品的库存
				Long stockNumber = itemDao.selecStockNumbertById(id);
				if (stockNumber != null) {
					itemDto2.setStockNumber(stockNumber);
				}
			}
		}
		page.setRecords(selectListStoreItemDto);
		return page;
	}

	@Override
	public Integer removeItems(Long[] id) throws GlobalException {
		Integer i = 0;
		/** 判断id是否为空 */
		if (id == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_NULL);
		}
		/** 判断id是否存在 */
		for (Long long1 : id) {
			ItemDto item = itemDao.selectItemByItemId(long1);
			if (item == null) {
				throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_EXIST);
			}
			List<ItemPictureEntity> itemPictures = itemPictureDao.selectItemPicture(long1);
			if (!(itemPictures == null || itemPictures.size() == 0)) {
				for (ItemPictureEntity itemPicture : itemPictures) {
					i = itemPictureDao.deleteItemPicture(itemPicture.getId());
				}
			}
			List<GoodsSkuEntity> goodsSkus = goodsSkuDao.selectGoodsSku(long1);
			if (!(goodsSkus == null || goodsSkus.size() == 0)) {
				for (GoodsSkuEntity goodsSku : goodsSkus) {
					i = goodsSkuDao.deleteGoodsSku(goodsSku.getId());
				}
			}
		}
		i = itemDao.deleteItem(id);
		return i;
	}

	@Override
	public Integer putItems(Long[] id) throws GlobalException {
		/** 判断id是否为空 */
		if (id == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_NULL);
		}
		/** 判断id是否存在 */
		for (Long itemId : id) {
			ItemEntity item = itemDao.selectById(itemId);
			if (item == null) {
				throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_EXIST);
			}
			/** 添加上架到商品上下架记录表 */
			ItemUpdownLogEntity itemUpdownLog = new ItemUpdownLogEntity();
			itemUpdownLog.setItemId(itemId);
			itemUpdownLog.setStatus(item.getStatus());
			itemUpdownLog.setCreatedTime(new Date());
			itemUpdownLog.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
			itemUpdownLogDao.insert(itemUpdownLog);
		}
		Integer i = itemDao.updatePutItem(id);
		return i;
	}

	@Override
	public Integer outItems(Long[] id) throws GlobalException {
		Integer i = 0;
		/** 判断id是否为空 */
		if (id == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_NULL);
		}
		/** 判断id是否存在 */
		for (Long itemId : id) {
			ItemEntity item = itemDao.selectById(itemId);
			if (item == null) {
				throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_EXIST);
			}
			//查询下架的商品是否有分销的商品
			List<StoreDistributionGoodsSkuDto> storeDistributionGoodsSkuDtos = itemDao.selectStoreDistributionGoodsDto(itemId);
			if(!(storeDistributionGoodsSkuDtos == null || storeDistributionGoodsSkuDtos.size() == 0)) {
				for (StoreDistributionGoodsSkuDto storeDistributionGoodsSkuDto : storeDistributionGoodsSkuDtos) {
					//删除分销商品
					i = itemDao.deleteStoreDistributionGoodsSkuDtos(storeDistributionGoodsSkuDto.getId());
				}
			}
			/** 添加下架到商品上下架记录表 */
			ItemUpdownLogEntity itemUpdownLog = new ItemUpdownLogEntity();
			itemUpdownLog.setItemId(itemId);
			itemUpdownLog.setStatus(item.getStatus());
			itemUpdownLog.setCreatedTime(new Date());
			itemUpdownLog.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
			i = itemUpdownLogDao.insert(itemUpdownLog);
		}
	    i = itemDao.updateOutItem(id);
		return i;
	}

	@Override
	public Integer saveItem(ItemEntity item, List<ItemPictureEntity> itemPictureEntities,
			List<GoodsSkuEntity> goodsSkuEntities) throws GlobalException {
		Integer i = 0;
		item.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		item.setCreatedTime(new Date());
		item.setStatus(1);
		i = itemDao.insert(item);
		Long itemId = item.getId();
		/** 添加商品上下架记录表 */
		ItemUpdownLogEntity itemUpdownLog = new ItemUpdownLogEntity();
		itemUpdownLog.setItemId(itemId);
		itemUpdownLog.setStatus(item.getStatus());
		itemUpdownLog.setCreatedTime(new Date());
		itemUpdownLog.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		itemUpdownLogDao.insert(itemUpdownLog);
		if (!(goodsSkuEntities == null || goodsSkuEntities.size() == 0)) {
			for (GoodsSkuEntity goodsSkuEntity : goodsSkuEntities) {
				/** 添加商品sku信息 */
				goodsSkuEntity.setItemId(itemId);
				goodsSkuEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
				goodsSkuEntity.setCreatedTime(new Date());
				i = goodsSkuDao.insert(goodsSkuEntity);
			}
		}
		if (!(itemPictureEntities == null || itemPictureEntities.size() == 0)) {
			for (ItemPictureEntity itemPictureEntity : itemPictureEntities) {
				/** 添加商品图片信息 */
				itemPictureEntity.setItemId(itemId);
				itemPictureEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
				itemPictureEntity.setCreatedTime(new Date());
				i = itemPictureDao.insert(itemPictureEntity);
			}
		}
		return i;
	}

	@Override
	public Integer saveMerchantItem(ItemEntity item, List<ItemPictureEntity> itemPictureEntities,
			List<GoodsSkuEntity> goodsSkuEntities) throws GlobalException {
		Integer i = 0;
		// 获得到店铺的id
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		item.setBelongStore(storeId);
		item.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		item.setCreatedTime(new Date());
		item.setStatus(1);
		i = itemDao.insert(item);
		Long itemId = item.getId();
		/** 添加商品上下架记录表 */
		ItemUpdownLogEntity itemUpdownLog = new ItemUpdownLogEntity();
		itemUpdownLog.setItemId(itemId);
		itemUpdownLog.setStatus(item.getStatus());
		itemUpdownLog.setCreatedTime(new Date());
		itemUpdownLog.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		itemUpdownLogDao.insert(itemUpdownLog);
		if (!(goodsSkuEntities == null || goodsSkuEntities.size() == 0)) {
			for (GoodsSkuEntity goodsSkuEntity : goodsSkuEntities) {
				/** 添加商品sku信息 */
				goodsSkuEntity.setItemId(itemId);
				goodsSkuEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
				goodsSkuEntity.setCreatedTime(new Date());
				i = goodsSkuDao.insert(goodsSkuEntity);
			}
		}
		if (!(itemPictureEntities == null || itemPictureEntities.size() == 0)) {
			for (ItemPictureEntity itemPictureEntity : itemPictureEntities) {
				/** 添加商品图片信息 */
				itemPictureEntity.setItemId(itemId);
				itemPictureEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
				itemPictureEntity.setCreatedTime(new Date());
				i = itemPictureDao.insert(itemPictureEntity);
			}
		}
		return i;
	}

	@Override
	public Integer modifyItem(ItemDto itemDto, List<ItemPictureDto> itemPictureDtos, List<GoodsSkuDto> goodsSkuDtos)
			throws GlobalException {
		Integer i = 0;
		ItemEntity item = itemDao.selectById(itemDto.getId());
		itemDto.setStatus(item.getStatus());
		BeanUtils.copyProperties(itemDto, item);
		item.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		item.setLastModifiedTime(new Date());
		i = itemDao.updateById(item);
		Long itemId = item.getId();
		/** 添加商品上下架记录表 */
		ItemUpdownLogEntity itemUpdownLog = new ItemUpdownLogEntity();
		itemUpdownLog.setItemId(item.getId());
		itemUpdownLog.setStatus(item.getStatus());
		itemUpdownLog.setCreatedTime(new Date());
		itemUpdownLog.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		itemUpdownLogDao.insert(itemUpdownLog);
		/** 删除商品图片信息 */
		List<ItemPictureDto> itemPictureById = itemPictureDao.selectItemPictureById(item.getId());
		if (!(itemPictureById == null || itemPictureById.size() == 0)) {
			for (ItemPictureDto itemPictureDto : itemPictureById) {
				itemPictureDao.deleteItemPicture(itemPictureDto.getId());
			}
		}
		/** 删除商品属性信息 */
		List<GoodsSkuDto> goodsSkuById = goodsSkuDao.selectGoodsSkuById(item.getId());
		if (!(goodsSkuById == null || goodsSkuById.size() == 0)) {
			for (GoodsSkuDto goodsSkuDto : goodsSkuById) {
				goodsSkuDao.deleteGoodsSku(goodsSkuDto.getId());
			}
		}
		if (!(itemPictureDtos == null || itemPictureDtos.size() == 0)) {
			if (itemPictureDtos.size() < 1) {
				throw new GlobalException(GoodsSkuExceptionEnum.GOODSPICTURE_NAME_NOT_NULL);
			}
			for (ItemPictureDto itemPictureDto : itemPictureDtos) {
				/** 修改商品图片信息 */
				ItemPictureEntity itemPictureEntity = new ItemPictureEntity();
				BeanUtils.copyProperties(itemPictureDto, itemPictureEntity);
				itemPictureEntity.setItemId(itemId);
				itemPictureEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
				itemPictureEntity.setCreatedTime(new Date());
				i = itemPictureDao.insert(itemPictureEntity);
			}
		}
		if (!(goodsSkuDtos == null || goodsSkuDtos.size() == 0)) {
			for (GoodsSkuDto goodsSkuDto : goodsSkuDtos) {
				if (goodsSkuDto.getPrice() == 0) {
					throw new GlobalException(GoodsSkuExceptionEnum.GOODSSKU_PRICE_NOT_NULL);
				}
				/** 修改商品sku信息 */
				GoodsSkuEntity goodsSkuEntity = new GoodsSkuEntity();
				BeanUtils.copyProperties(goodsSkuDto, goodsSkuEntity);
				goodsSkuEntity.setItemId(itemId);
				goodsSkuEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
				goodsSkuEntity.setCreatedTime(new Date());
				i = goodsSkuDao.insert(goodsSkuEntity);
			}
		}
		return i;
	}

	@Override
	public ItemDto findItemById(Long id) throws GlobalException {
		/** 判断id是否为空 */
		if (id == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_NULL);
		}
		/** 判断id是否存在 */
		ItemEntity item = itemDao.selectById(id);
		if (item == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_EXIST);
		}
		ItemDto itemDto = itemDao.selectItemById(id);
		List<BackendCategoryDto> backendCategoryDtos = new ArrayList<BackendCategoryDto>();
		// 返回三级类目
		if (itemDto != null) {
			if (itemDto.getBelongCategory() != null) {
				BackendCategoryDto backendCategory3 = backendCategoryDao
						.selectBackendCategoryById(itemDto.getBelongCategory());
				BackendCategoryDto backendCategory2 = backendCategoryDao
						.selectBackendCategoryById(backendCategory3.getParentId());
				BackendCategoryDto backendCategory1 = backendCategoryDao
						.selectBackendCategoryById(backendCategory2.getParentId());
				backendCategoryDtos.add(backendCategory1);
				backendCategoryDtos.add(backendCategory2);
				backendCategoryDtos.add(backendCategory3);
			}
		}
		itemDto.setBackendCategoryDtos(backendCategoryDtos);
		return itemDto;
	}

	@Override
	public List<GoodsSkuDto> findGoodsSkuById(Long id) throws GlobalException {
		/** 判断id是否为空 */
		if (id == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_NULL);
		}
		/** 判断id是否存在 */
		ItemEntity item = itemDao.selectById(id);
		if (item == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_EXIST);
		}
		ItemDto itemDto = itemDao.selectItemById(id);
		List<GoodsSkuDto> goodsSkuDtos = goodsSkuDao.selectGoodsSkuById(id);
		if (!(goodsSkuDtos == null || goodsSkuDtos.size() == 0)) {
			for (GoodsSkuDto goodsSkuDto : goodsSkuDtos) {
				// 取出每个销售属性
				String saleFieldValue = goodsSkuDto.getSaleFieldValue();
				if (saleFieldValue != null) {
					String[] strs = saleFieldValue.split(",");
					// 创建销售属性集合
					List<GoodsSkuSaleDto> goodsSkuSaleDtos = new ArrayList<GoodsSkuSaleDto>();
					// 创建销售属性key集合
					List<GoodsSkuSaleKeyDto> goodsSkuSaleKeyDtos = new ArrayList<GoodsSkuSaleKeyDto>();
					// 创建销售属性value集合
					List<GoodsSkuSaleValueDto> goodsSkuSaleValueDtos = new ArrayList<GoodsSkuSaleValueDto>();
					// 创建销售属性中的键与值得集合
					List<GoodsSkuSaleKeyAndValueDto> goodsSkuSaleKeyAndValueDtos = new ArrayList<GoodsSkuSaleKeyAndValueDto>();
					for (int i = 0; i < strs.length; i++) {
						String[] strs1 = strs[i].split(":");
						// 创建销售属性对象
						GoodsSkuSaleDto goodsSkuSaleDto = new GoodsSkuSaleDto();
						// 创建销售属性key对象
						GoodsSkuSaleKeyDto goodsSkuSaleKeyDto = new GoodsSkuSaleKeyDto();
						// 创建销售属性value对象
						GoodsSkuSaleValueDto goodsSkuSaleValueDto = new GoodsSkuSaleValueDto();
						// 创建销售属性中的键与值得对象
						GoodsSkuSaleKeyAndValueDto goodsSkuSaleKeyAndValueDto = new GoodsSkuSaleKeyAndValueDto();
						// 创建销售属性中的值得集合
						List<GoodsSkuSaleValueDto> goodsSkuSaleValueDtos2 = new ArrayList<GoodsSkuSaleValueDto>();
						// 创建销售属性中的值得对象
						GoodsSkuSaleValueDto goodsSkuSaleValueDto2 = new GoodsSkuSaleValueDto();
						// 保存key值
						goodsSkuSaleDto.setId(strs1[0]);
						goodsSkuSaleKeyDto.setKeyName(strs1[0]);
						goodsSkuSaleKeyAndValueDto.setSaleId(strs1[0]);
						Long imageField = itemDto.getImageField();
						// 判断是否上传sku图片
						if (imageField != null) {
							String imageFileld = Long.toString(imageField);
							if (imageFileld.equals(goodsSkuSaleKeyAndValueDto.getSaleId())) {
								goodsSkuSaleValueDto2.setImageurl(goodsSkuDto.getSaleImage());
								goodsSkuSaleKeyAndValueDto.setImageField(imageField);
							}
						}
						// 保存value值
						goodsSkuSaleDto.setSaleName(strs1[1]);
						goodsSkuSaleValueDto.setValueName(strs1[1]);
						goodsSkuSaleValueDto2.setValueName(strs1[1]);
						// 保存对象
						goodsSkuSaleDtos.add(goodsSkuSaleDto);
						goodsSkuSaleKeyDtos.add(goodsSkuSaleKeyDto);
						goodsSkuSaleValueDtos.add(goodsSkuSaleValueDto);
						goodsSkuSaleValueDtos2.add(goodsSkuSaleValueDto2);
						goodsSkuSaleKeyAndValueDto.setGoodsSkuSaleValueDto(goodsSkuSaleValueDtos2);
						goodsSkuSaleKeyAndValueDtos.add(goodsSkuSaleKeyAndValueDto);
						// 保存goodsSkuDto集合
						goodsSkuDto.setGoodsSkuSaleDto(goodsSkuSaleDtos);
						goodsSkuDto.setGoodsSkuSaleKeyDto(goodsSkuSaleKeyDtos);
						goodsSkuDto.setGoodsSkuSaleValueDto(goodsSkuSaleValueDtos);
						goodsSkuDto.setGoodsSkuSaleKeyAndValueDtos(goodsSkuSaleKeyAndValueDtos);
					}
				}
				// 取出每个基本属性
				String baseFieldValue = goodsSkuDto.getBaseFieldValue();
				if (baseFieldValue != null) {
					String[] strss = baseFieldValue.split(",");
					// 创建基本属性集合
					List<GoodsSkuBaseDto> goodsSkuBaseDtos = new ArrayList<GoodsSkuBaseDto>();
					for (int i = 0; i < strss.length; i++) {
						String[] strss1 = strss[i].split(":");
						GoodsSkuBaseDto goodsSkuBaseDto = new GoodsSkuBaseDto();
						goodsSkuBaseDto.setId(strss1[0]);
						goodsSkuBaseDto.setBaseName(strss1[1]);
						goodsSkuBaseDtos.add(goodsSkuBaseDto);
						goodsSkuDto.setGoodsSkuBaseDto(goodsSkuBaseDtos);
					}
				}
			}
		}
		return goodsSkuDtos;
	}

	@Override
	public List<ItemPictureDto> findItemPictureById(Long id) throws GlobalException {
		/** 判断id是否为空 */
		if (id == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_NULL);
		}
		/** 判断id是否存在 */
		ItemEntity item = itemDao.selectById(id);
		if (item == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_EXIST);
		}
		List<ItemPictureDto> itemPictureDtos = itemPictureDao.selectItemPictureById(id);
		return itemPictureDtos;
	}

	@Override
	public Integer freezeItem(Long id) throws GlobalException {
		/** 判断id是否为空 */
		if (id == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_NULL);
		}
		/** 判断id是否存在 */
		ItemEntity item = itemDao.selectById(id);
		if (item == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_EXIST);
		}
		return itemDao.freezeItem(id);
	}

	@Override
	public Page<ItemDto> getListMerchantItemDto(Page<ItemDto> page, ItemDto itemDto) {
		List<ItemDto> selectListMerchantItemDto = itemDao.selectListMerchantItemDto(page, itemDto);
		if (selectListMerchantItemDto != null) {
			for (ItemDto itemDto2 : selectListMerchantItemDto) {
				// 查询每件商品的销售量
				Long id = itemDto2.getId();
				Long salesVolome = itemDao.selectSalesVolomeById(id);
				if (salesVolome != null) {
					itemDto2.setSalesVolome(salesVolome);
				}
				else {
					itemDto2.setSalesVolome(0L);
				}
				// 查询每件商品的库存
				Long stockNumber = itemDao.selecStockNumbertById(id);
				if (stockNumber != null) {
					itemDto2.setStockNumber(stockNumber);
				}
			}
		}
		page.setRecords(selectListMerchantItemDto);
		return page;
	}

	public List<BackendCategorysDto> getItemRecentlyCategoryList() {
		List<BackendCategorysDto> backendCategorysListDto = new ArrayList<BackendCategorysDto>();
		/** 创建一个新list集合。 */
		List<BackendCategoryDto> resultList = new ArrayList<BackendCategoryDto>();
		/** 查询出最近商品使用后台类目。 */
		List<BackendCategoryDto> itemRecentlyCategoryList = backendCategoryDao.selectItemRecentlyCategoryList();
		if (!(itemRecentlyCategoryList == null || itemRecentlyCategoryList.size() == 0)) {
			for (BackendCategoryDto backendCategoryDto : itemRecentlyCategoryList) {
				/** 判断是否包含本后台类目。 */
				if (!isContain(resultList, backendCategoryDto)) {
					resultList.add(backendCategoryDto);
				}
				/** 判断resultList长度是否为10 */
				if (resultList.size() == 10) {
					break;
				}
			}
		}
		// 查询出第三级类目的前两级类目
		if (!(resultList == null || resultList.size() == 0)) {
			for (BackendCategoryDto backendCategoryDto : resultList) {
				// 创建一个BackendCategoryDto集合
				List<BackendCategoryDto> reaultMsg = new ArrayList<BackendCategoryDto>();
				// 第三级parentId查询第二级类目
				BackendCategoryDto twobackendCategoryDto = backendCategoryDao
						.selectTwoBackendCategoryDtoByParentId(backendCategoryDto.getParentId());
				// 第二级parentId查询第一级类目
				BackendCategoryDto onebackendCategoryDto = backendCategoryDao
						.selectOneBackendCategoryDtoByParentId(twobackendCategoryDto.getParentId());
				// 将一二三级类目添加到BackendCategoryDto集合
				reaultMsg.add(onebackendCategoryDto);
				reaultMsg.add(twobackendCategoryDto);
				reaultMsg.add(backendCategoryDto);
				// 创建backendCategorysDto对象
				BackendCategorysDto backendCategorysDto = new BackendCategorysDto();
				backendCategorysDto.setBackendCategoryDto(reaultMsg);
				backendCategorysListDto.add(backendCategorysDto);
			}
		}
		return backendCategorysListDto;
	}

	@Override
	public List<BackendCategorysDto> getMerchantItemRecentlyCategoryList() {
		List<BackendCategorysDto> backendCategorysListDto = new ArrayList<BackendCategorysDto>();
		/** 创建一个新list集合。 */
		List<BackendCategoryDto> resultList = new ArrayList<BackendCategoryDto>();
		// 获得到店铺的id
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		/** 查询出最近商家商品使用后台类目。 */
		List<BackendCategoryDto> itemRecentlyCategoryList = backendCategoryDao
				.selectMerchantItemRecentlyCategoryList(storeId);
		if (!(itemRecentlyCategoryList == null || itemRecentlyCategoryList.size() == 0)) {
			for (BackendCategoryDto backendCategoryDto : itemRecentlyCategoryList) {
				/** 判断是否包含本后台类目。 */
				if (!isContain(resultList, backendCategoryDto)) {
					resultList.add(backendCategoryDto);
				}
				/** 判断resultList长度是否为10 */
				if (resultList.size() == 10) {
					break;
				}
			}
		}
		// 查询出第三级类目的前两级类目
		if (!(resultList == null || resultList.size() == 0)) {
			for (BackendCategoryDto backendCategoryDto : resultList) {
				// 创建一个BackendCategoryDto集合
				List<BackendCategoryDto> reaultMsg = new ArrayList<BackendCategoryDto>();
				// 第三级parentId查询第二级类目
				BackendCategoryDto twobackendCategoryDto = backendCategoryDao
						.selectTwoBackendCategoryDtoByParentId(backendCategoryDto.getParentId());
				// 第二级parentId查询第一级类目
				BackendCategoryDto onebackendCategoryDto = backendCategoryDao
						.selectOneBackendCategoryDtoByParentId(twobackendCategoryDto.getParentId());
				// 将一二三级类目添加到BackendCategoryDto集合
				reaultMsg.add(onebackendCategoryDto);
				reaultMsg.add(twobackendCategoryDto);
				reaultMsg.add(backendCategoryDto);
				// 创建backendCategorysDto对象
				BackendCategorysDto backendCategorysDto = new BackendCategorysDto();
				backendCategorysDto.setBackendCategoryDto(reaultMsg);
				backendCategorysListDto.add(backendCategorysDto);
			}
		}
		return backendCategorysListDto;
	}

	@Override
	public Page<ItemDto> getListMarketItemDto(Page<ItemDto> page, ItemDto itemDto) {
		page.setRecords(itemDao.selectListMarketItemDto(page, itemDto));
		return page;
	}

	@Override
	public Integer modifyGoodsSkuStockNumber(Long skuId, Long salesNumber) throws GlobalException {
		/** 判断商品skuId是否为空 */
		if (skuId == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_NULL);
		}
		/** 判断商品skuId是否存在 */
		GoodsSkuEntity goodsSku = goodsSkuDao.selectGoodsSkuBySkuId(skuId);
		if (goodsSku == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_EXIST);
		}
		/** 判断库存是否充足 */
		if (goodsSku.getStockNumber() < salesNumber) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_STOCKNUMBER_DEFICIENCY);
		}
		/** 计算库存 */
		goodsSku.setStockNumber(goodsSku.getStockNumber() - salesNumber);
		goodsSku.setLastModifiedTime(new Date());
		goodsSku.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		return goodsSkuDao.updateById(goodsSku);
	}

	@Override
	public Integer addGoodsSkuStockNumber(Long memberId, Long skuId, Long salesNumber) throws GlobalException {
		/** 判断商品skuId是否为空 */
		if (skuId == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_NULL);
		}
		/** 判断商品skuId是否存在 */
		GoodsSkuEntity goodsSku = goodsSkuDao.selectGoodsSkuBySkuId(skuId);
		if (goodsSku == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_EXIST);
		}
		/** 计算库存 */
		goodsSku.setStockNumber(goodsSku.getStockNumber() + salesNumber);
		goodsSku.setLastModifiedTime(new Date());
		goodsSku.setLastModifierId(memberId);
		return goodsSkuDao.updateById(goodsSku);
	}

	@Override
	public ItemDto findMerchantItemById(Long id) throws GlobalException {
		/** 判断id是否为空 */
		if (id == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_NULL);
		}
		/** 判断id是否存在 */
		ItemEntity item = itemDao.selectById(id);
		if (item == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_EXIST);
		}
		ItemDto itemDto = itemDao.selectMerchantItemById(id);
		return itemDto;
	}

	/**
	 * Description 查询前台类目的子集类目
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            前台类目的id
	 * @return
	 */
	private List<FrontendCategoryEntity> getAllFrontendCategory(Long id) {
		/** 创建一个resultList集合 */
		List<FrontendCategoryEntity> resultList = new ArrayList<FrontendCategoryEntity>();
		/** 通过前台类目id查询出对应前台类目 */
		FrontendCategoryEntity selectFrontendCategoryById = frontendCategoryDao.selectFrontendCategoryEntityById(id);
		resultList.add(selectFrontendCategoryById);
		List<FrontendCategoryEntity> listFrontendCategory = frontendCategoryDao.selectAllFrontendCategoryById(id);
		if (!(listFrontendCategory == null || listFrontendCategory.size() == 0)) {
			resultList.addAll(listFrontendCategory);
			for (FrontendCategoryEntity frontendCategoryEntity : listFrontendCategory) {
				List<FrontendCategoryEntity> allFrontendCategory = this
						.getAllFrontendCategory(frontendCategoryEntity.getId());
				resultList.addAll(allFrontendCategory);
			}
			return resultList;
		} else {
			return resultList;
		}
	}

	@Override
	public Page<ItemDto> getItemListByItem(Page<ItemDto> page, ItemDto itemDto) throws GlobalException {
		// 根据前台类目id查询出对应的下级类目
		if (itemDto.getFrontendCategory() != null) {
			List<FrontendCategoryEntity> allFrontendCategory = this
					.getAllFrontendCategory(itemDto.getFrontendCategory());
			if (!(allFrontendCategory == null || allFrontendCategory.size() == 0)) {
				for (FrontendCategoryEntity frontendCategoryEntity : allFrontendCategory) {
					// 通过前台第三级类目id查询绑定表
					List<FrontBackCategoryEntity> allFrontBackCategoryById = frontBackCategoryDao
							.selectAllFrontBackCategoryById(frontendCategoryEntity.getId());
					if (!(allFrontBackCategoryById == null || allFrontBackCategoryById.size() == 0)) {
						List<Long> backendCategoryIds = new ArrayList<Long>();
						// 通过后台类目backId查询对应的后台类目
						for (FrontBackCategoryEntity frontBackCategoryEntity : allFrontBackCategoryById) {
							Long id = frontBackCategoryEntity.getBackId();
							backendCategoryIds.add(id);
						}
						itemDto.setBelongCategoryIds(backendCategoryIds);
					}
				}
			}
		}
		List<ItemDto> itemDtoList = itemDao.selectItemListByItem(itemDto);
		if (!(itemDtoList == null || itemDtoList.size() == 0)) {
			for (ItemDto itemDto2 : itemDtoList) {
				// 查询每件商品的销售量
				Long salesVolome = itemDao.selectSalesVolomeById(itemDto2.getId());
				if (salesVolome != null) {
					itemDto2.setSalesVolome(salesVolome);
				} else {
					itemDto2.setSalesVolome(0L);
				}
				// 查询每件商品的库存
				Long stockNumber = itemDao.selecStockNumbertById(itemDto2.getId());
				if (stockNumber != null) {
					itemDto2.setStockNumber(stockNumber);
				} else {
					itemDto2.setStockNumber(0L);
				}
				// 商品图片
				List<ItemPictureEntity> itemPictureEntity = itemDao.selectPictureNameById(itemDto2.getId());
				if (!(itemPictureEntity == null || itemPictureEntity.size() == 0)) {
					itemDto2.setPictureName(itemPictureEntity.get(0).getName());
				}
				// 营销活动
				List<PromoTionGoodsDto> typeIds = itemDao.selectPromotionByItemId(itemDto2.getId());
				if(!(typeIds == null || typeIds.size() == 0)) {
					itemDto2.setPromoTionGoodsDtos(typeIds);
				}
			}
		}
		if (itemDto.getSales() != null) {
			// 为倒叙排列
			if (itemDto.getSales() == 0) {
				Collections.sort(itemDtoList, Collections.reverseOrder());
			}
			// 为正叙排列
			if (itemDto.getSales() == 1) {
				Collections.sort(itemDtoList);
			}
		}
		// 获得分页数据在list集合中的索引
		int firstIndex = (page.getCurrent() - 1) * page.getSize();
		int toIndex = page.getCurrent() * page.getSize();
		if (toIndex > itemDtoList.size()) {
			toIndex = itemDtoList.size();
		}
		if (firstIndex > toIndex) {
			firstIndex = 0;
		}
		page.setTotal(itemDtoList.size());
		List<ItemDto> objects = itemDtoList.subList(firstIndex, toIndex);
		page.setRecords(objects);
		return page;
	}

	@Override
	public ItemAppDto findAppItemById(String token, Long id) throws GlobalException {
		/** 判断id是否为空 */
		if (id == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_NULL);
		}
		/** 判断id是否存在 */
		ItemEntity item = itemDao.selectAppItemByItemId(id);
		if (item == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_EXIST);
		}
		ItemAppDto itemAppDto = itemDao.selectAppItemById(id);
		if (token != null) {
			TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
			Long memberId = tokenDetail.getMemberId();
			Long favId = itemDao.selectFavoriteByMemberAndGoodsId(id, memberId);
			itemAppDto.setFavId(favId);
		}
		if (itemAppDto != null) {
			// 运费模板的id
			Long logisticsId = itemAppDto.getLogisticsId();
			List<FreightTempleteDetailSkuEntity> freightTempleteDetailEntity = itemDao.selectOneByFreightId(logisticsId);
			if (!(freightTempleteDetailEntity == null || freightTempleteDetailEntity.size() == 0)) {
				double firstPrice = freightTempleteDetailEntity.get(0).getFirstPrice();
				if (firstPrice != 0.0) {
					itemAppDto.setFirstPrice(firstPrice);
				}
			}
			// 商品sku
			List<GoodsSkuDto> goodsSkuDtos = goodsSkuDao.selectGoodsSkuById(id);
			if (!(goodsSkuDtos == null || goodsSkuDtos.size() == 0)) {
				// 取到第一条sku对象
				GoodsSkuDto goodsSkuDto2 = goodsSkuDtos.get(0);
				// 销售属性
				String saleFieldValue = goodsSkuDto2.getSaleFieldValue();
				String[] strs = saleFieldValue.split(",");
				// 创建销售属性集合
				List<GoodsSkuSaleDto> goodsSkuSaleKeyDtos = new ArrayList<GoodsSkuSaleDto>();
				for (int i = 0; i < strs.length; i++) {
					String[] strs1 = strs[i].split(":");
					GoodsSkuSaleDto goodsSkuSaleDto = new GoodsSkuSaleDto();
					goodsSkuSaleDto.setId(strs1[0]);
					BackendCategoryAttrEntity selectById = backendCategoryAttrDao.selectById(strs1[0]);
					goodsSkuSaleDto.setKeyName(selectById.getName());
					goodsSkuSaleKeyDtos.add(goodsSkuSaleDto);
				}
				itemAppDto.setGoodsSkuSaleKeyDtos(goodsSkuSaleKeyDtos);
				// 创建销售属性集合
				List<GoodsSkuSaleDto> goodsSkuSaleDtos = new ArrayList<GoodsSkuSaleDto>();
				// 属性值，加图片
				List<GoodsSkuSaleValueDto> goodsSkuSaleValueDtos = new ArrayList<GoodsSkuSaleValueDto>();
				GoodsSkuSaleKeyAndValueDto goodsSkuSaleKeyAndValueDto = new GoodsSkuSaleKeyAndValueDto();
				// 取出每一个销售属性
				for (GoodsSkuDto goodsSkuDto : goodsSkuDtos) {
					// 创建销售属性集合
					List<GoodsSkuSaleDto> goodsSkuSaleDtolist = new ArrayList<GoodsSkuSaleDto>();
					// 取出每个销售属性
					String saleFieldValue2 = goodsSkuDto.getSaleFieldValue();
					if (saleFieldValue2 != null) {
						String[] strs2 = saleFieldValue2.split(",");
						for (int i = 0; i < strs2.length; i++) {
							String[] strs22 = strs2[i].split(":");
							// 创建销售属性对象
							GoodsSkuSaleDto goodsSkuSaleDto = new GoodsSkuSaleDto();
							GoodsSkuSaleValueDto goodsSkuSaleValueDto = new GoodsSkuSaleValueDto();
							// 创建销售属性对象
							GoodsSkuSaleDto goodsSkuSaleDtoss = new GoodsSkuSaleDto();
							// 保存key值
							goodsSkuSaleDto.setId(strs22[0]);
							goodsSkuSaleDtoss.setId(strs22[0]);
							goodsSkuSaleDto.setSaleName(strs22[1]);
							goodsSkuSaleDtoss.setSaleName(strs22[1]);
							goodsSkuSaleDtos.add(goodsSkuSaleDto);
							Long imageField = itemAppDto.getImageField();
							goodsSkuSaleDtolist.add(goodsSkuSaleDtoss);
							// 判断是否上传sku图片
							if (imageField != null) {
								String imageFileld = Long.toString(imageField);
								goodsSkuSaleKeyAndValueDto.setSaleId(imageFileld);
								BackendCategoryAttrEntity selectById = backendCategoryAttrDao.selectById(imageFileld);
								goodsSkuSaleKeyAndValueDto.setSaleName(selectById.getName());
								if (imageFileld.equals(goodsSkuSaleDto.getId())) {
									goodsSkuSaleValueDto.setImageurl(goodsSkuDto.getSaleImage());
									goodsSkuSaleValueDto.setValueName(strs22[1]);
									goodsSkuSaleValueDtos.add(goodsSkuSaleValueDto);
								}
							}
							goodsSkuDto.setGoodsSkuSaleDto(goodsSkuSaleDtolist);
							goodsSkuSaleKeyAndValueDto.setGoodsSkuSaleValueDto(goodsSkuSaleValueDtos);
							itemAppDto.setGoodsSkuSaleDtos(goodsSkuSaleDtos);
							itemAppDto.setGoodsSkuSaleKeyAndValueDto(goodsSkuSaleKeyAndValueDto);

						}

					}
				}
				itemAppDto.setGoodsSkuDtos(goodsSkuDtos);
				// 销量百分比。
				Double salesRatio = 0.0;
				// 该商品的评价
				List<GoodsSkuCommentDto> goodsSkuCommentDtos = new ArrayList<GoodsSkuCommentDto>();
				for (GoodsSkuDto goodsSkuDto : goodsSkuDtos) {
					// 商品属性id
					Long skuId = goodsSkuDto.getId();
					List<PromoTionGoodsDto> promoTionGoodsDto = itemDao.selectPromotionDtoBySkuId(skuId);
					if (!(promoTionGoodsDto == null || promoTionGoodsDto.size() == 0)) {
						if (promoTionGoodsDto.get(0).getPromotionPrice() != null) {
							// 通过营销id和skuId查询商品销售数量
							Long goodsNum = itemDao.selectKillSales(promoTionGoodsDto.get(0).getPromotionId(), skuId);
							Long promotionNum = promoTionGoodsDto.get(0).getPromotionNum();
							if (goodsNum != null) {
								Double goodsNumD = (double) goodsNum;
								Double promotionNumD = (double) promotionNum;
								salesRatio = (double) (goodsNumD / promotionNumD) * 100;
								goodsSkuDto.setPrice(promoTionGoodsDto.get(0).getPromotionPrice());
							}
							itemAppDto.setSalesRatio(salesRatio);
							itemAppDto.setDefaultPrice(promoTionGoodsDto.get(0).getPromotionPrice());
							itemAppDto.setEndTime(promoTionGoodsDto.get(0).getEndTime());
							itemAppDto.setPromotionId(promoTionGoodsDto.get(0).getPromotionId());
						}
					}
					List<GoodsSkuCommentDto> goodsSkuCommentDto = goodsSkuCommentDao
							.selectGoodsSkuCommentListBySkuId(skuId);
					if (!(goodsSkuCommentDto == null || goodsSkuCommentDto.size() == 0)) {
						for (GoodsSkuCommentDto goodsSkuCommentDto2 : goodsSkuCommentDto) {
							String saleFieldValue2 = goodsSkuCommentDto2.getSaleFieldValue();
							String saleFieldValue1 = "";
							String[] strss = saleFieldValue2.split(",");
							for (int i = 0; i < strss.length; i++) {
								String[] strs1 = strss[i].split(":");
								BackendCategoryAttrEntity selectById = backendCategoryAttrDao.selectById(strs1[0]);
								String name = selectById.getName();
								String value = strs1[1];
								saleFieldValue1 = name + ":" + value + "  " + saleFieldValue1;
							}
							goodsSkuCommentDto2.setSaleFieldValue(saleFieldValue1);
							// 商品评价图片
							List<GoodsSkuCommentPictureDto> goodsSkuCommentPictureDto = goodsSkuCommentPictureDao
									.selectGoodsSkuCommentPictureDto(goodsSkuCommentDto2.getId());
							if (!(goodsSkuCommentPictureDto == null || goodsSkuCommentPictureDto.size() == 0)) {
								goodsSkuCommentDto2.setGoodsSkuCommentPictureDtos(goodsSkuCommentPictureDto);
							}
						}
					}
					goodsSkuCommentDtos.addAll(goodsSkuCommentDto);
				}
				if (!(goodsSkuCommentDtos == null || goodsSkuCommentDtos.size() == 0)) {
					itemAppDto.setGoodsSkuCommentDtos(goodsSkuCommentDtos);
				}
			}
			// 商品图片
			List<ItemPictureDto> itemPictureDtos = itemPictureDao.selectItemPictureById(id);
			if (!(itemPictureDtos == null || itemPictureDtos.size() == 0)) {
				itemAppDto.setItemPictureDtos(itemPictureDtos);
			}
			// 前台类目id
			Long belongCategory = itemAppDto.getBelongCategory();
			if (belongCategory != null) {
				List<FrontBackCategoryDto> frontBackCategoryDto = frontBackCategoryDao
						.selectFrontBackCategoryByBackId(belongCategory);
				if (!(frontBackCategoryDto == null || frontBackCategoryDto.size() == 0)) {
					Long frontId = frontBackCategoryDto.get(0).getFrontId();
					itemAppDto.setFrontendCategory(frontId);
				}
			}
		}
		return itemAppDto;
	}

	@Override
	public Page<ItemDto> getDistributionMerchantListItemDto(Page<ItemDto> page, ItemDto itemDto) {
		// 获得到店铺的id
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		itemDto.setBelongStore(storeId);
		List<ItemDto> listItemDto = itemDao.selectDistributionMerchantListItemDto(page, itemDto);
		if (listItemDto != null) {
			for (ItemDto itemDto2 : listItemDto) {
				// 查询每件商品的销售量
				Long id = itemDto2.getId();
				Long salesVolome = itemDao.selectSalesVolomeById(id);
				if (salesVolome != null) {
					itemDto2.setSalesVolome(salesVolome);
				}
				else {
					itemDto2.setSalesVolome(0L);
				}
				// 查询每件商品的库存
				Long stockNumber = itemDao.selecStockNumbertById(id);
				if (stockNumber != null) {
					itemDto2.setStockNumber(stockNumber);
				}
			}
		}
		page.setRecords(listItemDto);
		return page;
	}

	@Override
	public List<GoodsSkuDto> findPromotionByItemId(Long itemId, Long promotionId) throws GlobalException {
		/** 判断id是否为空 */
		if (itemId == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_NULL);
		}
		/** 判断id是否存在 */
		ItemDto item = itemDao.selectItemByItemId(itemId);
		if (item == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_EXIST);
		}
		List<GoodsSkuDto> goodsSkuDtos = goodsSkuDao.selectPromotionByItemId(itemId, promotionId);
		// 查询出每个销售属性
		if (!(goodsSkuDtos == null || goodsSkuDtos.size() == 0)) {
			for (GoodsSkuDto goodsSkuDto : goodsSkuDtos) {
				String saleFieldValue = goodsSkuDto.getSaleFieldValue();
				String saleFieldValue1 = "";
				String[] strss = saleFieldValue.split(",");
				for (int i = 0; i < strss.length; i++) {
					String[] strs1 = strss[i].split(":");
					BackendCategoryAttrEntity selectById = backendCategoryAttrDao.selectById(strs1[0]);
					String name = selectById.getName();
					String value = strs1[1];
					saleFieldValue1 = name + ":" + value + "  " + saleFieldValue1;

				}
				goodsSkuDto.setSaleFieldValue(saleFieldValue1);
			}
		}
		return goodsSkuDtos;
	}

	@Override
	public Page<ItemSellDto> getSellGoodsList(Page<ItemSellDto> page, ItemSellDto itemSellDto) {
		List<ItemSellDto> sellGoodsList = itemDao.selectSellGoodsList(page, itemSellDto);
		if (!(sellGoodsList == null || sellGoodsList.size() == 0)) {
			for (ItemSellDto itemSellDto2 : sellGoodsList) {
				Long id = itemSellDto2.getId();
				List<GoodsSkuDto> selectGoodsSkuById = goodsSkuDao.selectGoodsSkuById(id);
				if (!(selectGoodsSkuById == null || selectGoodsSkuById.size() == 0)) {
					// 总销售数量
					Integer saleNumbers = 0;
					// 总销售金额
					Double salesAmounts = 0.0;
					// 付款人数
					Integer paymentPeoples = 0;
					for (GoodsSkuDto goodsSkuDto : selectGoodsSkuById) {
						// 付款人数
						Integer paymentPeople = itemDao.selectPaymentPeoples(goodsSkuDto.getId());
						paymentPeoples = paymentPeoples + paymentPeople;
						List<ItemSellDto> itemSellDto3 = itemDao.selectSaleNumber(goodsSkuDto.getId());
						if (!(itemSellDto3.size() == 0 || itemSellDto3 == null)) {
							for (ItemSellDto itemSellDto4 : itemSellDto3) {
								Integer saleNumber = itemSellDto4.getSaleNumber();
								Double salesAmount = itemSellDto4.getSalesAmount();
								saleNumbers = saleNumbers + saleNumber;
								salesAmounts = salesAmounts + salesAmount;
							}
						}
					}
					itemSellDto2.setSaleNumber(saleNumbers);
					itemSellDto2.setSalesAmount(salesAmounts);
					itemSellDto2.setPaymentPeople(paymentPeoples);
				}
			}
		}
		page.setRecords(sellGoodsList);
		return page;
	}

	@Override
	public Page<ItemSellDto> getMerchantSellGoodsList(Page<ItemSellDto> page, ItemSellDto itemSellDto) {
		// 获得到店铺的id
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		itemSellDto.setStoreId(storeId);
		List<ItemSellDto> sellGoodsList = itemDao.selectMerchantSellGoodsList(page, itemSellDto);
		if (!(sellGoodsList == null || sellGoodsList.size() == 0)) {
			for (ItemSellDto itemSellDto2 : sellGoodsList) {
				Long id = itemSellDto2.getId();
				List<GoodsSkuDto> selectGoodsSkuById = goodsSkuDao.selectGoodsSkuById(id);
				if (!(selectGoodsSkuById.size() == 0 || selectGoodsSkuById == null)) {
					// 总销售数量
					Integer saleNumbers = 0;
					// 总销售金额
					Double salesAmounts = 0.0;
					// 付款人数
					Integer paymentPeoples = 0;
					for (GoodsSkuDto goodsSkuDto : selectGoodsSkuById) {
						// 付款人数
						Integer paymentPeople = itemDao.selectPaymentPeoples(goodsSkuDto.getId());
						paymentPeoples = paymentPeoples + paymentPeople;
						List<ItemSellDto> itemSellDto3 = itemDao.selectSaleNumber(goodsSkuDto.getId());
						if (!(itemSellDto3.size() == 0 || itemSellDto3 == null)) {
							for (ItemSellDto itemSellDto4 : itemSellDto3) {
								Integer saleNumber = itemSellDto4.getSaleNumber();
								Double salesAmount = itemSellDto4.getSalesAmount();
								saleNumbers = saleNumbers + saleNumber;
								salesAmounts = salesAmounts + salesAmount;
							}
						}
					}
					itemSellDto2.setSaleNumber(saleNumbers);
					itemSellDto2.setSalesAmount(salesAmounts);
					itemSellDto2.setPaymentPeople(paymentPeoples);
				}
			}
		}
		page.setRecords(sellGoodsList);
		return page;
	}

	@Override
	public Page<ItemSellDto> getSellGoodsCategoryList(Page<ItemSellDto> page, ItemSellDto itemSellDto) {
		List<ItemSellDto> sellGoodsList = itemDao.selectSellGoodsCategoryList(page, itemSellDto);
		if (!(sellGoodsList == null || sellGoodsList.size() == 0)) {
			for (ItemSellDto itemSellDto2 : sellGoodsList) {
				// 第三级类目id
				Long categoryIdt = itemSellDto2.getCategoryIdt();
				BackendCategoryDto selectBackendCategoryById = backendCategoryDao
						.selectBackendCategoryById(categoryIdt);
				// 通过第三级parentId查询第二级类目
				BackendCategoryDto twobackendCategoryDto = backendCategoryDao
						.selectTwoBackendCategoryDtoByParentId(selectBackendCategoryById.getParentId());
				// 通过第二级parentId查询第一级类目
				BackendCategoryDto onebackendCategoryDto = backendCategoryDao
						.selectOneBackendCategoryDtoByParentId(twobackendCategoryDto.getParentId());
				itemSellDto2.setCategoryId(onebackendCategoryDto.getId());
				itemSellDto2.setCategoryName(onebackendCategoryDto.getName());
			}
		}
		List<ItemSellDto> msgList = new ArrayList<ItemSellDto>();
		// 去重
		for (int i = 0; i < sellGoodsList.size() - 1; i++) {
			for (int j = sellGoodsList.size() - 1; j > i; j--) {
				if (sellGoodsList.get(j).getCategoryId().equals(sellGoodsList.get(i).getCategoryId())) {
					Integer count = sellGoodsList.get(i).getCount();
					Integer count2 = sellGoodsList.get(j).getCount();
					sellGoodsList.get(i).setCount(count2 + count);
					msgList.add(sellGoodsList.get(j));
				}
			}
		}
		sellGoodsList.removeAll(msgList);
		page.setRecords(sellGoodsList);
		return page;
	}

	@Override
	public Page<ItemDto> getItemAndDisList(Page<ItemDto> page, ItemDto itemDto) {
		// 获得到店铺的id
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		itemDto.setBelongStore(storeId);
		List<ItemDto> itemDtos = itemDao.selectItemAndDisList(page, itemDto);
		if (itemDtos != null) {
			for (ItemDto itemDto2 : itemDtos) {
				// 查询每件商品的销售量
				Long id = itemDto2.getId();
				Long salesVolome = itemDao.selectSalesVolomeById(id);
				if (salesVolome != null) {
					itemDto2.setSalesVolome(salesVolome);
				}
				else {
					itemDto2.setSalesVolome(0L);
				}
				// 查询每件商品的库存
				Long stockNumber = itemDao.selecStockNumbertById(id);
				if (stockNumber != null) {
					itemDto2.setStockNumber(stockNumber);
				}
			}
		}
		page.setRecords(itemDtos);
		return page;
	}

	@Override
	public Page<ItemDto> getStoreDistributionGoods(Page<ItemDto> page, ItemDto itemDto) {
		// 获得到店铺的id
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		// 获得商家的id
		Long merchantId = SecurityContextUtils.getCurrentSystemUser().getMerchantId();
		itemDto.setBelongStore(storeId);
		itemDto.setMerchantId(merchantId);
		List<ItemDto> listItemDtos = itemDao.selectStoreDistributionGoods(page, itemDto);
		if (listItemDtos != null) {
			for (ItemDto itemDto2 : listItemDtos) {
				// 查询每件商品的销售量
				Long id = itemDto2.getId();
				Long salesVolome = itemDao.selectSalesVolomeById(id);
				if (salesVolome != null) {
					itemDto2.setSalesVolome(salesVolome);
				}
				else {
					itemDto2.setSalesVolome(0L);
				}
				// 查询每件商品的库存
				Long stockNumber = itemDao.selecStockNumbertById(id);
				if (stockNumber != null) {
					itemDto2.setStockNumber(stockNumber);
				}
			}
		}
		page.setRecords(listItemDtos);
		return page;
	}

	@Override
	public Page<ItemSellDto> getStoreSellGoodsCategoryList(Page<ItemSellDto> page, ItemSellDto itemSellDto) {
		// 获得到店铺的id
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		itemSellDto.setStoreId(storeId);
		List<ItemSellDto> sellGoodsList = itemDao.selectStoreSellGoodsCategoryList(page, itemSellDto);
		List<ItemSellDto> msgList = new ArrayList<ItemSellDto>();
		if (!(sellGoodsList == null || sellGoodsList.size() == 0)) {
			for (ItemSellDto itemSellDto2 : sellGoodsList) {
				// 第三级类目id
				Long categoryIdt = itemSellDto2.getCategoryIdt();
				BackendCategoryDto selectBackendCategoryById = backendCategoryDao
						.selectBackendCategoryById(categoryIdt);
				// 通过第三级parentId查询第二级类目
				BackendCategoryDto twobackendCategoryDto = backendCategoryDao
						.selectTwoBackendCategoryDtoByParentId(selectBackendCategoryById.getParentId());
				// 通过第二级parentId查询第一级类目
				BackendCategoryDto onebackendCategoryDto = backendCategoryDao
						.selectOneBackendCategoryDtoByParentId(twobackendCategoryDto.getParentId());
				itemSellDto2.setCategoryId(onebackendCategoryDto.getId());
				itemSellDto2.setCategoryName(onebackendCategoryDto.getName());
			}
		}
		// 去重
		for (int i = 0; i < sellGoodsList.size() - 1; i++) {
			for (int j = sellGoodsList.size() - 1; j > i; j--) {
				if (sellGoodsList.get(j).getCategoryId().equals(sellGoodsList.get(i).getCategoryId())) {
					Integer count = sellGoodsList.get(i).getCount();
					Integer count2 = sellGoodsList.get(j).getCount();
					sellGoodsList.get(i).setCount(count2 + count);
					msgList.add(sellGoodsList.get(j));
				}
			}
		}
		sellGoodsList.removeAll(msgList);
		page.setRecords(sellGoodsList);
		return page;
	}

	/** 判断是否包含。 */
	private boolean isContain(List<BackendCategoryDto> list, BackendCategoryDto backendCategoryDto) {
		for (BackendCategoryDto backDto : list) {
			if (backendCategoryDto.getId().equals(backDto.getId())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Integer modifyItemCategory(Long itemId, Long belongCategory) throws GlobalException {
		/** 判断id是否为空 */
		if (itemId == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_NULL);
		}
		/** 判断id是否存在 */
		ItemEntity item = itemDao.selectById(itemId);
		if (item == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_EXIST);
		}
		item.setBelongCategory(belongCategory);
		item.setLastModifiedTime(new Date());
		item.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		return itemDao.updateById(item);
	}

	@Override
	public List<GoodsSkuCommentDto> findAppSkuCommentById(Long id, Integer type) {
		// 根据商品id查询商品sku
		List<GoodsSkuDto> goodsSkuDtos = goodsSkuDao.selectGoodsSkuById(id);
		//全部
		List<GoodsSkuCommentDto> allGoodsSkuComment = new ArrayList<GoodsSkuCommentDto>();
		//有图
		List<GoodsSkuCommentDto> allPictureGoodsSkuComment = new ArrayList<GoodsSkuCommentDto>();
		if (!(goodsSkuDtos == null || goodsSkuDtos.size() == 0)) {
			for (GoodsSkuDto goodsSkuDto : goodsSkuDtos) {
				List<GoodsSkuCommentDto> goodsSkuCommentDto = goodsSkuCommentDao
						.selectGoodsSkuCommentListBySkuId(goodsSkuDto.getId());
				if (!(goodsSkuCommentDto == null || goodsSkuCommentDto.size() == 0)) {
					for (GoodsSkuCommentDto goodsSkuCommentDto2 : goodsSkuCommentDto) {
						String saleFieldValue2 = goodsSkuCommentDto2.getSaleFieldValue();
						String saleFieldValue1 = "";
						String[] strss = saleFieldValue2.split(",");
						for (int i = 0; i < strss.length; i++) {
							String[] strs1 = strss[i].split(":");
							BackendCategoryAttrEntity selectById = backendCategoryAttrDao.selectById(strs1[0]);
							String name = selectById.getName();
							String value = strs1[1];
							saleFieldValue1 = name + ":" + value + "  " + saleFieldValue1;
						}
						goodsSkuCommentDto2.setSaleFieldValue(saleFieldValue1);
						// 商品评价图片
						List<GoodsSkuCommentPictureDto> goodsSkuCommentPictureDto = goodsSkuCommentPictureDao
								.selectGoodsSkuCommentPictureDto(goodsSkuCommentDto2.getId());
						if (!(goodsSkuCommentPictureDto == null || goodsSkuCommentPictureDto.size() == 0)) {
							goodsSkuCommentDto2.setGoodsSkuCommentPictureDtos(goodsSkuCommentPictureDto);
							allPictureGoodsSkuComment.add(goodsSkuCommentDto2);
						}
					}
				}
				allGoodsSkuComment.addAll(goodsSkuCommentDto);
			}
		}
		if(type != null) {
			if(type == 1) {
				return allPictureGoodsSkuComment;
			}
		}
		return allGoodsSkuComment;
	}

}
