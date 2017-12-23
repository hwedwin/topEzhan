package com.topaiebiz.decorate.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topaiebiz.system.security.util.SecurityContextUtils;
import com.topaiebiz.decorate.dao.CollocateGoodsDao;
import com.topaiebiz.decorate.dao.CollocateInfoDao;
import com.topaiebiz.decorate.dao.CollocateTypeDao;
import com.topaiebiz.decorate.dao.StoreTemeplateDao;
import com.topaiebiz.decorate.dao.TemeplateInfoDao;
import com.topaiebiz.decorate.dto.CollocateDetailDto;
import com.topaiebiz.decorate.dto.CollocateDto;
import com.topaiebiz.decorate.dto.CollocateGoodsDto;
import com.topaiebiz.decorate.dto.CollocateInfoDto;
import com.topaiebiz.decorate.dto.TemeplateInfoDto;
import com.topaiebiz.decorate.entity.CollocateGoodsEntity;
import com.topaiebiz.decorate.entity.CollocateInfoEntity;
import com.topaiebiz.decorate.entity.CollocateTypeEntity;
import com.topaiebiz.decorate.entity.StoreTemeplateEntity;
import com.topaiebiz.decorate.entity.TemeplateInfoEntity;
import com.topaiebiz.decorate.service.CollocateService;
import com.topaiebiz.goods.brand.dao.BrandDao;
import com.topaiebiz.goods.category.frontend.dao.FrontendCategoryDao;
import com.topaiebiz.goods.sku.dao.ItemDao;

@Service
public class CollocateServiceImpl implements CollocateService {

	@Autowired
	private CollocateInfoDao collocateInfoDao;

	@Autowired
	private CollocateTypeDao collocateTypeDao;
	
	@Autowired
	private TemeplateInfoDao temeplateInfoDao;
	
	@Autowired
	private CollocateGoodsDao collocateGoodsDao;
	
	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private FrontendCategoryDao frontendCategoryDao;
	
	@Autowired
	private BrandDao brandDao;
	
	@Autowired
	private StoreTemeplateDao storeTemeplateDao;

	@Override
	public CollocateDto getListCollocate(CollocateDto collocateDto) {
		CollocateDto collocate = collocateTypeDao.selectListCollocate(collocateDto);
		List<CollocateInfoDto> infoList = collocateInfoDao.selectListByType(collocate.getId());
		List<CollocateGoodsEntity> goodsList = collocateGoodsDao.selectListByType(collocate.getId());
		collocate.setInfoList(infoList);
		collocate.setGoodsList(goodsList);
		return collocate;
	}

	@Override
	public void addCollocateType(CollocateDto collocateDto) {
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		CollocateTypeEntity collocateEntity = new CollocateTypeEntity();
		BeanUtils.copyProperties(collocateDto, collocateEntity);
		collocateEntity.setStoreId(storeId);
		// 查看表中是否存在该数据
		CollocateTypeEntity collocateType = collocateTypeDao.selectBydecTypeAndTeleplate(storeId,
				collocateEntity.getDecType(), collocateEntity.getTemplateId());
		if (null == collocateType) {
			collocateTypeDao.insert(collocateEntity);
		} else {
			collocateType.setTitle(collocateEntity.getTitle());
			collocateTypeDao.updateById(collocateType);
		}
	}

	@Override
	public void addCollocateInfo(CollocateInfoDto collocateInfoDto) {
		CollocateInfoEntity collocateInfoEntity = new CollocateInfoEntity();
		BeanUtils.copyProperties(collocateInfoDto, collocateInfoEntity);
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		// 选用类型
		String decType = collocateInfoDto.getDecType();
		// 选用模版
		Long templateId = collocateInfoDto.getTemplateId();
		CollocateTypeEntity collocateType = collocateTypeDao.selectBydecTypeAndTeleplate(storeId, decType, templateId);
		// 模版的id
		Long id = collocateType.getId();
		collocateInfoEntity.setCollocateType(id);
		// 判断该图片是否存在
		CollocateInfoEntity collocateInfo = collocateInfoDao.selectByCollocateTypeAndSortNo(collocateInfoEntity.getCollocateType(), collocateInfoEntity.getSortNo());
		if (null != collocateInfo) {
			collocateInfo.setImage(collocateInfoEntity.getImage());
			collocateInfo.setJumpType(collocateInfoEntity.getJumpType());
			collocateInfo.setJumpId(collocateInfoEntity.getJumpId());
			collocateInfo.setJumpUrl(collocateInfoEntity.getJumpUrl());
			collocateInfoDao.updateById(collocateInfo);
		} else {
			collocateInfoDao.insert(collocateInfoEntity);
		}
	}

	@Override
	public CollocateDetailDto getCollocateDetail(CollocateDto collocateDto) {
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		Long templateId = collocateDto.getTemplateId();
		String decType = collocateDto.getDecType();
		CollocateTypeEntity collocateType = collocateTypeDao.selectBydecTypeAndTeleplate(storeId, decType, templateId);
		if (null == collocateType) {
			return null;
		}
		List<CollocateInfoDto> infoList = collocateInfoDao.selectListByType(collocateType.getId());
		if (null != infoList && infoList.size() > 0) {
			for (CollocateInfoDto collocateInfoDto : infoList) {
				//1商品 2类目 3品牌 4 自定义url
				try {
					switch (collocateInfoDto.getJumpType()) {
					case 1:
						collocateInfoDto.setName(itemDao.selectById(collocateInfoDto.getJumpId()).getName());
						break;
					case 2:
						collocateInfoDto.setName(frontendCategoryDao.selectById(collocateInfoDto.getJumpId()).getName());
						break;
					case 3:
						collocateInfoDto.setName(brandDao.selectById(collocateInfoDto.getJumpId()).getName());
						break;
					default:
						collocateInfoDto.setName("");
						break;
					}
				} catch (Exception e) {
					collocateInfoDto.setName("");
					e.printStackTrace();
				}
			}
		}

		CollocateDetailDto collocateDetailDto = new CollocateDetailDto();
		BeanUtils.copyProperties(collocateType, collocateDetailDto);
		collocateDetailDto.setInfoList(infoList);
		return collocateDetailDto;
	}

	@Override
	public void modifyTemeplateInfo(TemeplateInfoDto temeplateInfoDto) {
		TemeplateInfoEntity entity = temeplateInfoDao.selectById(temeplateInfoDto.getId());
		entity.setIsOpen(temeplateInfoDto.getIsOpen());
		entity.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		entity.setLastModifiedTime(new Date());
		temeplateInfoDao.updateById(entity);
	}

	@Override
	public void saveStoreTemeplate(Long temeplateId) {
		//当前用户的所属店铺
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		//先把表里该店铺的删除掉，然后插入新的一条
		StoreTemeplateEntity storeTemeplateEntity = storeTemeplateDao.selectByStoreIdAndTemeplateId(storeId, temeplateId);
		//删除
		if(null != storeTemeplateEntity) {
			storeTemeplateDao.deleteStoreTemeplate(storeTemeplateEntity.getId());
		}
		StoreTemeplateEntity entity = new StoreTemeplateEntity();
		entity.setStoreId(storeId);
		entity.setTemplateId(temeplateId);
		entity.setCreatedTime(new Date());
		entity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		storeTemeplateDao.insert(entity);
	}

	@Override
	public StoreTemeplateEntity getUsedTemeplate(Long sotreId) {
		return storeTemeplateDao.selectByStoreId(sotreId);
	}

	@Override
	public List<CollocateGoodsEntity> getCollocateGoods(Long id) {
		List<CollocateGoodsEntity> goodsList = collocateGoodsDao.selectAllListByType(id);
		return goodsList;
	}

	@Override
	public void addCollocateGoods(List<CollocateGoodsDto> collocateGoodsList) {
		for (CollocateGoodsDto collocateGoodsDto : collocateGoodsList) {
			CollocateGoodsEntity entity = new CollocateGoodsEntity();
			BeanUtils.copyProperties(collocateGoodsDto, entity);
			entity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
			entity.setCreatedTime(new Date());
			collocateGoodsDao.insert(entity);
		}
	}

	@Override
	public void addGiftCardCollocateInfo(CollocateInfoDto collocateInfoDto) {
		CollocateInfoEntity collocateInfoEntity = collocateInfoDao.selectByCollocateTypeAndSortNo(collocateInfoDto.getCollocateType(), collocateInfoDto.getSortNo());
		if (null != collocateInfoEntity) {
			collocateInfoEntity.setCollocateType(collocateInfoDto.getCollocateType());
			collocateInfoEntity.setSortNo(collocateInfoDto.getSortNo());
			collocateInfoEntity.setImage(collocateInfoDto.getImage());
			collocateInfoEntity.setJumpType(collocateInfoDto.getJumpType());
			collocateInfoEntity.setJumpId(collocateInfoDto.getJumpId());
			collocateInfoEntity.setJumpUrl(collocateInfoDto.getJumpUrl());
			collocateInfoDao.updateById(collocateInfoEntity);
		} else {
			collocateInfoEntity = new CollocateInfoEntity();
			collocateInfoEntity.setCollocateType(collocateInfoDto.getCollocateType());
			collocateInfoEntity.setSortNo(collocateInfoDto.getSortNo());
			collocateInfoEntity.setImage(collocateInfoDto.getImage());
			collocateInfoEntity.setJumpType(collocateInfoDto.getJumpType());
			collocateInfoEntity.setJumpId(collocateInfoDto.getJumpId());
			collocateInfoEntity.setJumpUrl(collocateInfoDto.getJumpUrl());
			collocateInfoDao.insert(collocateInfoEntity);
		}
	}

	@Override
	public List<CollocateInfoDto> getGiftCollocateDetail(CollocateDto collocateDto) {
		return collocateInfoDao.selectListByType(collocateDto.getCollocateType());
	}


}
