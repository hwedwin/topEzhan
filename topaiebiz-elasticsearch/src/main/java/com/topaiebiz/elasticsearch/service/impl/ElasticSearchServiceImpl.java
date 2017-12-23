/**
 * 
 */
package com.topaiebiz.elasticsearch.service.impl;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.elasticsearch.dto.SearchPageDto;
import com.topaiebiz.elasticsearch.exception.SearchExceptionEnum;
import com.topaiebiz.elasticsearch.search.ElasticSearchClient;
import com.topaiebiz.elasticsearch.service.ElasticSearchService;
import com.topaiebiz.goods.sku.dao.ItemDao;
import com.topaiebiz.goods.sku.dto.ItemDto;
import com.topaiebiz.goods.sku.dto.PromoTionGoodsDto;
import com.topaiebiz.goods.sku.entity.ItemPictureEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description： 商品搜索service 接口实现类
 * 
 * 
 * Author hxpeng
 * 
 * Date 2017年11月4日 下午3:08:53
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {
	
	private final static Logger logger = LoggerFactory.getLogger(ElasticSearchServiceImpl.class);
	
	@Autowired
	private ItemDao itemDao;

	@Override
	public List<ItemDto> searchItemByParams(String queryParams,ItemDto itemDto, SearchPageDto searchPageDto) throws GlobalException {
		List<ItemDto> itemLst = new ArrayList<>();
		long currentTime = System.currentTimeMillis();
		try {
			List<Long> itemIds = ElasticSearchClient.search(queryParams, searchPageDto);
			if(null == itemIds || itemIds.size()==0) {
				return null;
			}
			itemDto.setIds(itemIds);
			itemLst = itemDao.selectItemsByIds(itemDto);
			if(!(null == itemLst || itemLst.size() == 0)) {
				for (ItemDto itemDto2 : itemLst) {
					// 查询每件商品的销售量
					Long salesVolome = itemDao.selectSalesVolomeById(itemDto2.getId());
					if (salesVolome != null) {
						itemDto2.setSalesVolome(salesVolome);
					} else {
						itemDto2.setSalesVolome(0L);
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
					Collections.sort(itemLst, Collections.reverseOrder());
				}
				// 为正叙排列
				if (itemDto.getSales() == 1) {
					Collections.sort(itemLst);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new GlobalException(SearchExceptionEnum.SEARCH_FAILED);
		}
		long endTime = System.currentTimeMillis();
		logger.info("查询耗时：" + (endTime - currentTime));
		return itemLst;
	}

}
