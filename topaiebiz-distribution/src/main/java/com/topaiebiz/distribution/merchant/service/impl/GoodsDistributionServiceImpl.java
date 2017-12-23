package com.topaiebiz.distribution.merchant.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.distribution.member.dao.MemberDistributionLogDao;
import com.topaiebiz.distribution.member.dto.MemberDistributionLogDto;
import com.topaiebiz.distribution.merchant.dao.GoodsDistributionDao;
import com.topaiebiz.distribution.merchant.dao.GoodsDistributionLogDao;
import com.topaiebiz.distribution.merchant.dao.StoreDistributionGoodsDao;
import com.topaiebiz.distribution.merchant.dto.GoodsDistributionDto;
import com.topaiebiz.distribution.merchant.dto.GoodsDistributionLogDto;
import com.topaiebiz.distribution.merchant.entity.GoodsDistributionEntity;
import com.topaiebiz.distribution.merchant.entity.GoodsDistributionLogEntity;
import com.topaiebiz.distribution.merchant.entity.StoreDistributionGoodsEntity;
import com.topaiebiz.distribution.merchant.service.GoodsDistributionAllocationService;
import com.topaiebiz.goods.sku.dao.GoodsSkuDao;
import com.topaiebiz.goods.sku.dao.ItemDao;
import com.topaiebiz.goods.sku.entity.GoodsSkuEntity;
import com.topaiebiz.goods.sku.entity.ItemEntity;
import com.topaiebiz.goods.sku.exception.GoodsSkuExceptionEnum;
import com.topaiebiz.merchant.enter.dto.StoreInfoDto;
import com.topaiebiz.system.security.util.SecurityContextUtils;
import com.topaiebiz.transaction.order.merchant.dto.StoreOrderDto;

/**
 * Description： 商品分销的业务实现。
 * 
 * Author Harry
 * 
 * Date 2017年10月3日 下午3:15:08
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
public class GoodsDistributionServiceImpl implements GoodsDistributionAllocationService {

	@Autowired
	private GoodsDistributionDao goodsDistributionDao;

	@Autowired
	private GoodsDistributionLogDao goodsDistributionLogDao;

	@Autowired
	private GoodsSkuDao goodSkuDao;

	@Autowired
	private ItemDao itemDao;

	@Autowired
	private MemberDistributionLogDao memberDistributionLogDao;
	
	@Autowired
	private StoreDistributionGoodsDao storeDistributionGoodsDao;
	
	@Override
	public Integer saveGoodsDistributionAllocation(Long[] itemId,
			List<GoodsDistributionEntity> goodsDistributionAllocationEntitis) throws GlobalException {
		Integer i = 0;
		/** 判断itemId是否为空 */
		if (itemId == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_NULL);
		}
		/** 判断itemId是否存在 */
		for (Long long1 : itemId) {
			ItemEntity itemEntity = itemDao.selectById(long1);
			if (itemEntity == null) {
				throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_EXIST);
			}
		}
		for (Long long1 : itemId) {
			/** 通过itemId查询具体的Sku集合 */
			List<GoodsSkuEntity> goodsSkuList = goodSkuDao.selectGoodsSku(long1);
			if (!(goodsSkuList == null || goodsSkuList.size() == 0)) {
				for (GoodsSkuEntity goodsSkuEntitylist : goodsSkuList) {
					/** 循环遍历集合取出所有skuId */
					Long skuId = goodsSkuEntitylist.getId();
					List<GoodsDistributionDto> goodsDistributionDtos = goodsDistributionDao
							.findGoodsDistributionAllocationBySkuId(skuId);
					if (!(goodsDistributionDtos == null || goodsDistributionDtos.size() == 0)) {
						for (GoodsDistributionDto goodsDistributionDto : goodsDistributionDtos) {
							i = goodsDistributionDao.deleteGoodsDistribution(goodsDistributionDto.getId());
						}
					}
				}
			}
		}
		for (Long long1 : itemId) {
			/** 通过itemId查询具体的Sku集合 */
			List<GoodsSkuEntity> goodsSkuList = goodSkuDao.selectGoodsSku(long1);
			if (!(goodsSkuList == null || goodsSkuList.size() == 0)) {
				for (GoodsSkuEntity goodsSkuEntitylist : goodsSkuList) {
					/** 循环遍历集合取出所有skuId */
					Long skuId = goodsSkuEntitylist.getId();
					if (!(goodsDistributionAllocationEntitis == null
							|| goodsDistributionAllocationEntitis.size() == 0)) {
						for (GoodsDistributionEntity goodsDistributionAllocationEntity : goodsDistributionAllocationEntitis) {
							GoodsDistributionEntity goodsDistributionEntity = new GoodsDistributionEntity();
							/** 将从Sku集合中取出来的SkuId set到 商品分销对象中 */
							goodsDistributionEntity.setRatio(goodsDistributionAllocationEntity.getRatio());
							goodsDistributionEntity
									.setStoreGradeId(goodsDistributionAllocationEntity.getStoreGradeId());
							goodsDistributionEntity.setSkuId(skuId);
							goodsDistributionEntity.setCreatedTime(new Date());
							goodsDistributionEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
							i = goodsDistributionDao.insert(goodsDistributionEntity);
						}
					}
				}
			}
		}
		return i;
	}

	@Override
	public List<GoodsDistributionDto> findGoodsDistributionAllocationByItemId(Long itemId) throws GlobalException {
		/** 判断itemId是否为空 */
		if (itemId == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_NULL);
		}
		/** 判断itemId是否存在 */
		ItemEntity itemEntity = itemDao.selectById(itemId);
		if (itemEntity == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_EXIST);
		}
		List<GoodsDistributionDto> goodsDistributionDto = null;
		List<GoodsSkuEntity> goodsSkuList = goodSkuDao.selectGoodsSku(itemId);
		if (!(goodsSkuList == null || goodsSkuList.size() == 0)) {
			GoodsSkuEntity goodsSkuEntity = goodsSkuList.get(0);
			Long skuId = goodsSkuEntity.getId();
			/** 通过skuId查询分销记录 */
			goodsDistributionDto = goodsDistributionDao.findGoodsDistributionAllocationBySkuId(skuId);
		}
		return goodsDistributionDto;
	}

	@Override
	public Page<MemberDistributionLogDto> findMemberDistributionListByStoreId(Page<MemberDistributionLogDto> page,
			MemberDistributionLogDto memberDistributionLogDto) {
		/** 根据storeId查询会员分销信息 */
		List<MemberDistributionLogDto> memberDistributionLogInfoList = memberDistributionLogDao
				.findMemberDistributionInfoByStoreId(page, memberDistributionLogDto);
		if (!(memberDistributionLogInfoList == null || memberDistributionLogInfoList.size() == 0)) {
			for (MemberDistributionLogDto memberDistributionLogDtos : memberDistributionLogInfoList) {
				Long memberId = memberDistributionLogDtos.getMemberId();
				/** 根据memberId与分销级别查询该会员的一级分销金额 */
				List<MemberDistributionLogDto> primaryDistributionPriceMemberDistribution = memberDistributionLogDao
						.findMemberDistributionByMemberId(memberId);
				Double primaryDistributionPrice = 0.0;
				Double totalPriceAmounts = 0.0;
				Long num1 = 0L;
				if (!(primaryDistributionPriceMemberDistribution == null
						|| primaryDistributionPriceMemberDistribution.size() == 0)) {
					for (MemberDistributionLogDto primaryDistributionmemberDistributionLogDto : primaryDistributionPriceMemberDistribution) {
						/** 计算一级分销金额 */
						Double distriPrice = primaryDistributionmemberDistributionLogDto.getDistriPrice();
						primaryDistributionPrice = distriPrice + primaryDistributionPrice;
						/** 计算一级商品的总金额 */
						Double totalPrice = primaryDistributionmemberDistributionLogDto.getTotalPrice();
						totalPriceAmounts = totalPriceAmounts + totalPrice;
						/** 计算一级商品数量 */
						Long goodsNum = primaryDistributionmemberDistributionLogDto.getGoodsNum();
						num1 = num1 + goodsNum;
					}
				}
				memberDistributionLogDtos.setPrimaryDistributionPrice(primaryDistributionPrice);
				/** 根据memberId与分销级别为2查询会员的二级分销金额 */
				List<MemberDistributionLogDto> levelDistributionPriceMemberDistribution = memberDistributionLogDao
						.findlevelDistributionPriceMemberDistributionByMemberId(memberId);
				Double levelDistributionPrice = 0.0;
				Double levelTotalPriceAmount = 0.0;
				Long num2 = 0L;
				if (!(levelDistributionPriceMemberDistribution == null
						|| levelDistributionPriceMemberDistribution.size() == 0)) {
					for (MemberDistributionLogDto levelDistributionPricememberDistributionLogDto : levelDistributionPriceMemberDistribution) {
						/** 计算二级分销金额 */
						Double distriPrice = levelDistributionPricememberDistributionLogDto.getDistriPrice();
						levelDistributionPrice = levelDistributionPrice + distriPrice;
						/** 计算二级商品的总金额 */
						Double totalPrice = levelDistributionPricememberDistributionLogDto.getTotalPrice();
						levelTotalPriceAmount = levelTotalPriceAmount + totalPrice;
						/** 计算一级商品数量 */
						Long goodsNum = levelDistributionPricememberDistributionLogDto.getGoodsNum();
						num2 = num2 + goodsNum;
					}
				}
				memberDistributionLogDtos.setLevelDistributionPrice(levelDistributionPrice);
				/** 计算总分润金额 */
				memberDistributionLogDtos.setDistriPriceAmount(primaryDistributionPrice + levelDistributionPrice);
				/** 计算总金额 */
				memberDistributionLogDtos.setTotalPriceAmount(levelTotalPriceAmount + totalPriceAmounts);
				memberDistributionLogDtos.setGoodsNum(num1 + num2);
			}
		}
		page.setRecords(memberDistributionLogInfoList);
		return page;
	}

	@Override
	public Page<GoodsDistributionLogDto> getPlatformGoodsDistributionList(Page<GoodsDistributionLogDto> page,
			GoodsDistributionLogDto goodsDistributionLogDto) {
		List<GoodsDistributionLogDto> goodsDistributionList = goodsDistributionLogDao
				.getPlatformGoodsDistributionList(page, goodsDistributionLogDto);
		if (!(goodsDistributionList == null || goodsDistributionList.size() == 0)) {
			for (GoodsDistributionLogDto goodsDistributionLogDto2 : goodsDistributionList) {
				Long storeId = goodsDistributionLogDto2.getStoreId();
				if (storeId != null) {
					Double distributionPriceAmounts = 0.0;
					List<GoodsDistributionLogEntity> distributionLogEntities = goodsDistributionLogDao
							.selectGoodsDistributionLogByStoreId(storeId);
					if (!(distributionLogEntities == null || distributionLogEntities.size() == 0)) {
						for (GoodsDistributionLogEntity goodsDistributionLogEntity : distributionLogEntities) {
							distributionPriceAmounts = distributionPriceAmounts
									+ goodsDistributionLogEntity.getDistriPrice();
						}
					}
					goodsDistributionLogDto2.setDistributionPriceAmount(distributionPriceAmounts);
				}
			}
		}
		page.setRecords(goodsDistributionList);
		return page;
	}

	@Override
	public Page<GoodsDistributionLogDto> getMerchantGoodsDistributionLogList(Page<GoodsDistributionLogDto> page,
			GoodsDistributionLogDto goodsDistributionLogDto) {
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		goodsDistributionLogDto.setStoreId(storeId);
		List<GoodsDistributionLogDto> MerchantgoodsDistributionList = goodsDistributionLogDao
				.getMerchantGoodsDistributionLogList(page, goodsDistributionLogDto);
		page.setRecords(MerchantgoodsDistributionList);
		return page;
	}

	public Integer saveGoodsDistributionLog() throws GlobalException {
		Integer i = 0;
		List<StoreOrderDto> storeOrderDtoList = goodsDistributionDao.selectStoreOrderList();
		if (!(storeOrderDtoList == null || storeOrderDtoList.size() == 0)) {
			for (StoreOrderDto storeOrderDtos : storeOrderDtoList) {
				GoodsDistributionLogEntity goodsDistributionLogEntitis = new GoodsDistributionLogEntity();
				/** 通过订单表中获取每一个店铺id */
				Long storeId = storeOrderDtos.getStoreId();
				/**根据店铺id查询店铺信息*/
				StoreInfoDto storeInfoDto = goodsDistributionDao.selectStoreInfoByStoreId(storeId);
				Long merchantGradeId = storeInfoDto.getMerchantGradeId();
				Long merchantId = storeInfoDto.getMerchantId();
				Long id = storeInfoDto.getId();
				/** 获取每一个goodsId */
				Long goodsId = storeOrderDtos.getGoodsId();
				goodsDistributionLogEntitis.setMerchantId(merchantId);
				goodsDistributionLogEntitis.setStoreId(id);
				goodsDistributionLogEntitis.setStoreGrade(merchantGradeId);
				goodsDistributionLogEntitis.setSkuId(goodsId);
				GoodsDistributionEntity goodsDistributionEntity = goodsDistributionDao.findStoreRatioByGoodsId(goodsId,
						merchantGradeId);
				/** 求出分润比例 */
				Double ratio = goodsDistributionEntity.getRatio();
				if (ratio != null) {
					goodsDistributionLogEntitis.setRatio(ratio);
					Long goodsNum = storeOrderDtos.getGoodsNum();
					Double salePrice = storeOrderDtos.getSalePrice();
					if (goodsNum != null && salePrice != null) {
						Double totalPrice = goodsNum * salePrice;
						Double disPrice = ratio * totalPrice;
						goodsDistributionLogEntitis.setSaleNum(storeOrderDtos.getGoodsNum());
						goodsDistributionLogEntitis.setPrice(salePrice);
						goodsDistributionLogEntitis.setTotalPrice(totalPrice);
						goodsDistributionLogEntitis.setDistriPrice(disPrice);
						goodsDistributionLogEntitis.setSettleState(0);
						/**分销时间暂定为当前系统时间*/
						goodsDistributionLogEntitis.setDistriDate(new Date());
						i = goodsDistributionLogDao.insert(goodsDistributionLogEntitis);
					}
				}
			}
		}
		return i;
	}

	@Override
	public Page<GoodsDistributionLogDto> findMemberDistributionListByskuId(Page<GoodsDistributionLogDto> page,
			GoodsDistributionLogDto goodsDistributionLogDto) {
		List<GoodsDistributionLogDto> merchantgoodsDistributionList = goodsDistributionLogDao
				.findMemberDistributionListByskuId(page, goodsDistributionLogDto);
		page.setRecords(merchantgoodsDistributionList);
		return page;
	}

	@Override
	public Page<MemberDistributionLogDto> findGoodsMemberDistributionListByMemberId(Page<MemberDistributionLogDto> page,
			MemberDistributionLogDto memberDistributionLogDto) {
		List<MemberDistributionLogDto> memberGoodsDistributionLogDto = memberDistributionLogDao
				.findGoodsMemberDistributionListByMemberId(page, memberDistributionLogDto);
		page.setRecords(memberGoodsDistributionLogDto);
		return page;
	}

	@Override
	public Integer saveStoreDistributionGoods(Long[] id) throws GlobalException {
		Integer i = 0;
		if(id == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_NULL);
		}
		for (Long long1 : id) {
			ItemEntity itemEntity = itemDao.selectById(long1);
			if(itemEntity == null) {
				throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_EXIST);
			}
			Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
			StoreDistributionGoodsEntity storeDistributionGoodsEntity = new StoreDistributionGoodsEntity();
			storeDistributionGoodsEntity.setCreatedTime(new Date());
			/**所属店铺*/
			storeDistributionGoodsEntity.setStore(storeId);
			/**itemid*/
			storeDistributionGoodsEntity.setItemId(long1);
			storeDistributionGoodsEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
			i = storeDistributionGoodsDao.insert(storeDistributionGoodsEntity);
		}
		return i;
	}

	@Override
	public Integer removeStoreDistributionGoods(Long id) throws GlobalException {
		if(id == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_NULL);
		}
		ItemEntity itemEntity = itemDao.selectById(id);
		if(itemEntity == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_EXIST);
		}
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		return storeDistributionGoodsDao.deleteStoreDistributionGoods(storeId,id);
	}

}
