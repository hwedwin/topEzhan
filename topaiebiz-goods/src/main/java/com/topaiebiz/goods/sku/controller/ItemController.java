package com.topaiebiz.goods.sku.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.nebulapaas.web.util.IllegalParamValidationUtils;
import com.topaiebiz.goods.category.backend.dto.BackendCategorysDto;
import com.topaiebiz.goods.sku.dto.GoodsSkuDto;
import com.topaiebiz.goods.sku.dto.ItemDto;
import com.topaiebiz.goods.sku.dto.ItemPictureDto;
import com.topaiebiz.goods.sku.dto.ItemSellDto;
import com.topaiebiz.goods.sku.entity.GoodsSkuEntity;
import com.topaiebiz.goods.sku.entity.ItemEntity;
import com.topaiebiz.goods.sku.entity.ItemPictureEntity;
import com.topaiebiz.goods.sku.exception.GoodsSkuExceptionEnum;
import com.topaiebiz.goods.sku.service.ItemService;

/**
 * Description 商品sku控制层
 * 
 * Author Hedda
 * 
 * Date 2017年10月3日 下午2:35:46
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 *
 */
@Controller
@RestController
@RequestMapping("/goods/item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	/**
	 * Description 平台商品信息列表分页检索出售中的商品
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemDto
	 *            商品信息dto
	 * @return
	 * @throws GlobalException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/getListItemDto", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getListItemDto(Page<ItemDto> page, ItemDto itemDto) throws GlobalException {
		Page<ItemDto> listItemDto = itemService.getListItemDto(page, itemDto);
		return new ResponseInfo(listItemDto);
	}

	/**
	 * Description 商家商品信息列表分页检索出售中的商品
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemDto
	 *            商品信息dto
	 * @return
	 * @throws GlobalException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/getMerchantListItemDto", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantListItemDto(Page<ItemDto> page, ItemDto itemDto) throws GlobalException {
		Page<ItemDto> listItemDto = itemService.getMerchantListItemDto(page, itemDto);
		return new ResponseInfo(listItemDto);
	}

	/**
	 * Description 商家端商品信息中的平台池商品管理列表
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemDto
	 *            商品信息dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/getStoreDistributionGoods", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getStoreDistributionGoods(Page<ItemDto> page, ItemDto itemDto) throws GlobalException {
		Page<ItemDto> listItemDto = itemService.getStoreDistributionGoods(page, itemDto);
		return new ResponseInfo(listItemDto);
	}

	/**
	 * Description 平台商品信息列表分页检索仓库中的商品
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemDto
	 *            商品信息dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/getListStoreItemDto", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getListStoreItemDto(Page<ItemDto> page, ItemDto itemDto) throws GlobalException {
		Page<ItemDto> listItemDto = itemService.getListStoreItemDto(page, itemDto);
		return new ResponseInfo(listItemDto);
	}

	/**
	 * Description 商家商品信息列表分页检索仓库中的商品
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemDto
	 *            商品信息dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/getMerchantListStoreItemDto", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantListStoreItemDto(Page<ItemDto> page, ItemDto itemDto) throws GlobalException {
		Page<ItemDto> listItemDto = itemService.getMerchantListStoreItemDto(page, itemDto);
		return new ResponseInfo(listItemDto);
	}

	/**
	 * Description 商家端分销商品管理平台商品
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemDto
	 *            商品信息dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/getDistributionMerchantListItemDto", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getDistributionMerchantListItemDto(Page<ItemDto> page, ItemDto itemDto) throws GlobalException {
		Page<ItemDto> listItemDto = itemService.getDistributionMerchantListItemDto(page, itemDto);
		return new ResponseInfo(listItemDto);
	}

	/**
	 * Description 营销活动查询商家商品和平台商品信息列表分页检索
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemDto
	 *            商品信息dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/getListMarketItemDto", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getListMarketItemDto(Page<ItemDto> page, ItemDto itemDto) throws GlobalException {
		Page<ItemDto> listItemDto = itemService.getListMarketItemDto(page, itemDto);
		return new ResponseInfo(listItemDto);
	}
	
	/**
	 * Description 平台端商家商品信息列表分页检索
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemDto
	 *            商品信息dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/getListMerchantItemDto", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getListMerchantItemDto(Page<ItemDto> page, ItemDto itemDto) throws GlobalException {
		Page<ItemDto> listItemDto = itemService.getListMerchantItemDto(page, itemDto);
		return new ResponseInfo(listItemDto);
	}

	/**
	 * Description 平台端查询最近使用类目
	 * 
	 * Author Hedda
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/getItemRecentlyCategoryList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getItemRecentlyCategoryList() throws GlobalException {
		List<BackendCategorysDto> backendCategorysDto = itemService.getItemRecentlyCategoryList();
		return new ResponseInfo(backendCategorysDto);
	}

	/**
	 * Description 商家端查询最近使用类目
	 * 
	 * Author Hedda
	 * 
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/getMerchantItemRecentlyCategoryList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantItemRecentlyCategoryList() throws GlobalException {
		List<BackendCategorysDto> backendCategorysDto = itemService.getMerchantItemRecentlyCategoryList();
		return new ResponseInfo(backendCategorysDto);
	}

	/**
	 * Description 商品item批量逻辑删除
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/cancelItems", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo cancelItems(Long[] id) throws GlobalException {
		itemService.removeItems(id);
		return new ResponseInfo("删除成功！");
	}

	/**
	 * Description 商品item批量上架
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/putItems", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo putItems(Long[] id) throws GlobalException {
		itemService.putItems(id);
		return new ResponseInfo("上架成功！");
	}

	/**
	 * Description 商品item批量下架
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/outItems", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo outItems(Long[] id) throws GlobalException {
		itemService.outItems(id);
		return new ResponseInfo("下架成功！");
	}

	/**
	 * Description 商品item冻结
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(value = "/freezeItem", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo freezeItem(Long id) throws GlobalException {
		itemService.freezeItem(id);
		return new ResponseInfo("冻结成功！");
	}

	/**
	 * Description 平台商品item信息添加
	 * 
	 * Author Hedda
	 * 
	 * @param itemDto
	 *            商品item信息
	 * @param itemPictureDtos
	 *            商品item图片信息
	 * @param goodsSkuDtos
	 *            商品sku信息
	 * @param result
	 *            错误结果
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/addItem", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addItem(@Valid @RequestBody ItemDto itemDto, BindingResult result) throws GlobalException {
		/** 对商品字段进行校验 */
		if (result.hasErrors()) {
			/** 初始化非法参数的提示信息。 */
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/** 获取非法参数异常信息对象，并抛出异常。 */
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		ItemEntity item = new ItemEntity();
		BeanUtils.copyProperties(itemDto, item);
		List<ItemPictureDto> itemPictureDtos = itemDto.getItemPictureDtos();
		List<ItemPictureEntity> itemPictureEntities = new ArrayList<ItemPictureEntity>();
		/** 复制商品图片信息 */
		if (!(itemPictureDtos == null || itemPictureDtos.size() == 0)) {
			if(itemPictureDtos.size()<1) {
				throw new GlobalException(GoodsSkuExceptionEnum.GOODSPICTURE_NAME_NOT_NULL);
			}
			for (ItemPictureDto itemPictureDto : itemPictureDtos) {
				ItemPictureEntity itemPicture = new ItemPictureEntity();
				BeanUtils.copyProperties(itemPictureDto, itemPicture);
				itemPictureEntities.add(itemPicture);
			}
		}
		List<GoodsSkuDto> goodsSkuDtos = itemDto.getGoodsSkuDtos();
		List<GoodsSkuEntity> goodsSkuEntities = new ArrayList<GoodsSkuEntity>();
		/** 复制商品sku信息 */
		if (!(goodsSkuDtos == null || goodsSkuDtos.size() == 0)) {
			for (GoodsSkuDto goodsSkuDto : goodsSkuDtos) {
				if(goodsSkuDto.getPrice() == 0) {
					throw new GlobalException(GoodsSkuExceptionEnum.GOODSSKU_PRICE_NOT_NULL);
				}
				GoodsSkuEntity goodsSku = new GoodsSkuEntity();
				BeanUtils.copyProperties(goodsSkuDto, goodsSku);
				goodsSkuEntities.add(goodsSku);
			}
		}
		return new ResponseInfo(itemService.saveItem(item, itemPictureEntities, goodsSkuEntities));
	}

	/**
	 * Description 商家商品item信息添加
	 * 
	 * Author Hedda
	 * 
	 * @param itemDto
	 *            商品item信息
	 * @param itemPictureDtos
	 *            商品item图片信息
	 * @param goodsSkuDtos
	 *            商品sku信息
	 * @param result
	 *            错误结果
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/addMerchantItem", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo addMerchantItem(@Valid @RequestBody ItemDto itemDto, BindingResult result)
			throws GlobalException {
		/** 对商品字段进行校验 */
		if (result.hasErrors()) {
			/** 初始化非法参数的提示信息。 */
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/** 获取非法参数异常信息对象，并抛出异常。 */
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		ItemEntity item = new ItemEntity();
		BeanUtils.copyProperties(itemDto, item);
		List<ItemPictureDto> itemPictureDtos = itemDto.getItemPictureDtos();
		List<ItemPictureEntity> itemPictureEntities = new ArrayList<ItemPictureEntity>();
		/** 复制商品图片信息 */
		if (!(itemPictureDtos == null || itemPictureDtos.size() == 0)) {
			if(itemPictureDtos.size()<1) {
				throw new GlobalException(GoodsSkuExceptionEnum.GOODSPICTURE_NAME_NOT_NULL);
			}
			for (ItemPictureDto itemPictureDto : itemPictureDtos) {
				ItemPictureEntity itemPicture = new ItemPictureEntity();
				BeanUtils.copyProperties(itemPictureDto, itemPicture);
				itemPictureEntities.add(itemPicture);
			}
		}
		List<GoodsSkuDto> goodsSkuDtos = itemDto.getGoodsSkuDtos();
		List<GoodsSkuEntity> goodsSkuEntities = new ArrayList<GoodsSkuEntity>();
		/** 复制商品sku信息 */
		if (!(goodsSkuDtos == null || goodsSkuDtos.size() == 0)) {
			for (GoodsSkuDto goodsSkuDto : goodsSkuDtos) {
				if(goodsSkuDto.getPrice() == 0) {
					throw new GlobalException(GoodsSkuExceptionEnum.GOODSSKU_PRICE_NOT_NULL);
				}
				GoodsSkuEntity goodsSku = new GoodsSkuEntity();
				BeanUtils.copyProperties(goodsSkuDto, goodsSku);
				goodsSkuEntities.add(goodsSku);
			}
		}
		return new ResponseInfo(itemService.saveMerchantItem(item, itemPictureEntities, goodsSkuEntities));
	}

	/**
	 * Description 商品item信息修改
	 * 
	 * Author Hedda
	 * 
	 * @param itemDto
	 *            商品item信息
	 * @param itemPictureDtos
	 *            商品item图片信息
	 * @param goodsSkuDtos
	 *            商品sku信息
	 * @param result
	 *            错误结果
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/editItem", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editItem(@Valid @RequestBody ItemDto itemDto, BindingResult result) throws GlobalException {
		/** 对商品字段进行校验 */
		if (result.hasErrors()) {
			/** 初始化非法参数的提示信息。 */
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			/** 获取非法参数异常信息对象，并抛出异常。 */
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		List<ItemPictureDto> itemPictureDtos = itemDto.getItemPictureDtos();
		List<GoodsSkuDto> goodsSkuDtos = itemDto.getGoodsSkuDtos();
		return new ResponseInfo(itemService.modifyItem(itemDto, itemPictureDtos, goodsSkuDtos));
	}

	/**
	 * Description 修改商品item的类目
	 * 
	 * Author Hedda
	 * 
	 * @param itemId
	 *            商品的id
	 * @param belongCategory
	 *            商品后台第三级类目id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/editItemCcategory", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editItemCategory(Long itemId, Long belongCategory) throws GlobalException {
		return new ResponseInfo(itemService.modifyItemCategory(itemId, belongCategory));
	}

	/**
	 * Description 根据id查询商品信息回显
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/findItemById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo findItemById(Long id) throws GlobalException {
		ItemDto itemDto = itemService.findItemById(id);
		List<GoodsSkuDto> goodsSkuDtos = itemService.findGoodsSkuById(id);
		List<ItemPictureDto> itemPictureDtos = itemService.findItemPictureById(id);
		if (!(goodsSkuDtos == null || goodsSkuDtos.size() == 0)) {
			itemDto.setGoodsSkuDtos(goodsSkuDtos);
		}
		if (!(itemPictureDtos == null || itemPictureDtos.size() == 0)) {
			itemDto.setItemPictureDtos(itemPictureDtos);
		}
		return new ResponseInfo(itemDto);
	}

	/**
	 * Description 根据itemId查询商品sku信息
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/findSkuById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo findSkuById(Long id) throws GlobalException {
		List<GoodsSkuDto> goodsSkuDtos = itemService.findGoodsSkuById(id);
		return new ResponseInfo(goodsSkuDtos);
	}

	/**
	 * Description 商家根据id查询商品信息
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/findMerchantItemById", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo findMerchantItemById(Long id) throws GlobalException {
		ItemDto itemDto = itemService.findMerchantItemById(id);
		List<GoodsSkuDto> goodsSkuDtos = itemService.findGoodsSkuById(id);
		List<ItemPictureDto> itemPictureDtos = itemService.findItemPictureById(id);
		if (!(goodsSkuDtos == null || goodsSkuDtos.size() == 0)) {
			itemDto.setGoodsSkuDtos(goodsSkuDtos);
		}
		if (!(itemPictureDtos == null || itemPictureDtos.size() == 0)) {
			itemDto.setItemPictureDtos(itemPictureDtos);
		}
		return new ResponseInfo(itemDto);
	}

	/**
	 * Description 根据itemId查询商品sku信息以及营销的信息
	 * 
	 * Author Hedda
	 * 
	 * @param id
	 *            商品item的id
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/findPromotionByItemId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo findPromotionByItemId(Long itemId,Long promotionId) throws GlobalException {
		List<GoodsSkuDto> goodsSkuDtos = itemService.findPromotionByItemId(itemId,promotionId);
		return new ResponseInfo(goodsSkuDtos);
	}

	/**
	 * Description 装修时查询商家商品和分销平台的商品
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemDto
	 *            商品dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getItemAndDisList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getItemAndDisList(Page<ItemDto> page, ItemDto itemDto) throws GlobalException {
		Page<ItemDto> itemDtos = itemService.getItemAndDisList(page, itemDto);
		return new ResponseInfo(itemDtos);
	}

	/**
	 * Description 平台端统计管理商品销售情况
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemSellDto
	 *            商品销售dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getSellGoodsList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getSellGoodsList(Page<ItemSellDto> page, ItemSellDto itemSellDto) throws GlobalException {
		Page<ItemSellDto> itemSellDtos = itemService.getSellGoodsList(page, itemSellDto);
		return new ResponseInfo(itemSellDtos);
	}

	/**
	 * Description 商家端统计管理商品销售情况
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemSellDto
	 *            商品销售dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getMerchantSellGoodsList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getMerchantSellGoodsList(Page<ItemSellDto> page, ItemSellDto itemSellDto)
			throws GlobalException {
		Page<ItemSellDto> itemSellDtos = itemService.getMerchantSellGoodsList(page, itemSellDto);
		return new ResponseInfo(itemSellDtos);
	}

	/**
	 * Description 平台端统计管理商品类目销售分析
	 * 
	 * Author Hedda
	 * 
	 * @param page
	 *            分页单位
	 * @param itemSellDto
	 *            商品销售dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getSellGoodsCategoryList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getSellGoodsCategoryList(Page<ItemSellDto> page, ItemSellDto itemSellDto)
			throws GlobalException {
		Page<ItemSellDto> itemSellDtos = itemService.getSellGoodsCategoryList(page, itemSellDto);
		return new ResponseInfo(itemSellDtos);
	}

	/**
	 * Description 商家端统计管理商品类目销售分析
	 * 
	 * Author Hedda
	 * 
	 * @param itemSellDto
	 *            商品销售dto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/getStoreSellGoodsCategoryList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo getStoreSellGoodsCategoryList(Page<ItemSellDto> page,ItemSellDto itemSellDto)
			throws GlobalException {
		Page<ItemSellDto> itemSellDtos = itemService.getStoreSellGoodsCategoryList(page,itemSellDto);
		return new ResponseInfo(itemSellDtos);
	}

}
