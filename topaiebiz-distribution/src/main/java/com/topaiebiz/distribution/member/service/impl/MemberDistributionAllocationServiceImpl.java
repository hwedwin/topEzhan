package com.topaiebiz.distribution.member.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.distribution.member.dao.MemberDistributionAllocationDao;
import com.topaiebiz.distribution.member.dao.MemberDistributionLogDao;
import com.topaiebiz.distribution.member.dto.MemberDistributionAllocationDto;
import com.topaiebiz.distribution.member.dto.MemberDistributionLogDto;
import com.topaiebiz.distribution.member.entity.MemberDistributionAllocationEntity;
import com.topaiebiz.distribution.member.entity.MemberDistributionLogEntity;
import com.topaiebiz.distribution.member.service.MemberDistributionAllocationService;
import com.topaiebiz.goods.sku.dao.GoodsSkuDao;
import com.topaiebiz.goods.sku.dao.ItemDao;
import com.topaiebiz.goods.sku.entity.GoodsSkuEntity;
import com.topaiebiz.goods.sku.entity.ItemEntity;
import com.topaiebiz.goods.sku.exception.GoodsSkuExceptionEnum;
import com.topaiebiz.member.mgmt.entity.MemberEntity;
import com.topaiebiz.system.security.util.SecurityContextUtils;
import com.topaiebiz.transaction.order.merchant.dto.StoreOrderDto;

/**
 * Description： 会员分销的业务实现。
 * 
 * Author Harry
 * 
 * Date 2017年10月5日 下午3:35:31
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
public class MemberDistributionAllocationServiceImpl implements MemberDistributionAllocationService {

	@Autowired
	private MemberDistributionAllocationDao memberDistributionAllocationDao;
	
	@Autowired
	private MemberDistributionLogDao memberDistributionLogDao;
	
	@Autowired
	private GoodsSkuDao goodSkuDao;
	
	@Autowired
	private ItemDao itemDao;
		
//	@Autowired
//	private MemberMgmtDao memberMgmtDao;
	
	@Override
	public Integer saveMemberDistributionAllocation(Long itemId,
			List<MemberDistributionAllocationEntity> memberDistributionAllocationEntitis) throws GlobalException {
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		Integer i = 0;
		/** 判断itemId是否为空 */
		if (itemId == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_NULL);
		}
		/** 判断id是否存在 */
		ItemEntity item = itemDao.selectById(itemId);
		if (item == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_EXIST);
		}
		/** 通过itemId查询具体的Sku集合 */
		List<GoodsSkuEntity> goodsSkuList = goodSkuDao.selectGoodsSku(itemId);
		if (!(goodsSkuList == null || goodsSkuList.size() == 0)) {
			for (GoodsSkuEntity goodsSkuEntity : goodsSkuList) {
				/** 循环遍历集合取出所有skuId */
				Long skuId = goodsSkuEntity.getId();
				List<MemberDistributionAllocationDto> memberDistributionAllocationDto = memberDistributionAllocationDao
						.findMemberDistributionAllocationBySkuId(storeId,skuId);
				if (!(memberDistributionAllocationDto == null || memberDistributionAllocationDto.size() == 0)) {
					for (MemberDistributionAllocationDto memberDistributionAllocationDto2 : memberDistributionAllocationDto) {
						i = memberDistributionAllocationDao.deleteMemberDistributionAllocation(memberDistributionAllocationDto2.getId());
					}
				}
			}
		}
		if (!(goodsSkuList == null || goodsSkuList.size() == 0)) {
			for (GoodsSkuEntity goodsSkuEntitylist : goodsSkuList) {
				/** 循环遍历集合取出所有skuId,storeId */
				Long skuId = goodsSkuEntitylist.getId();
				/** 循环遍历集合取出所有的storeId */
				if (!(memberDistributionAllocationEntitis == null || memberDistributionAllocationEntitis.size() == 0)) {
					for (MemberDistributionAllocationEntity memberDistributionEntity : memberDistributionAllocationEntitis) {
						MemberDistributionAllocationEntity memberDistributionAllocationEntity = new MemberDistributionAllocationEntity();
						/** 将从Sku集合中取出来的SkuId set到 会员分销对象中 */
						memberDistributionAllocationEntity.setDistriLevel(memberDistributionEntity.getDistriLevel());
						memberDistributionAllocationEntity.setRatio(memberDistributionEntity.getRatio());
						memberDistributionAllocationEntity.setSkuId(skuId);
						memberDistributionAllocationEntity.setCreatedTime(new Date());
						memberDistributionAllocationEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
						memberDistributionAllocationEntity.setStoreId(storeId);
						i = memberDistributionAllocationDao.insert(memberDistributionAllocationEntity);
					}
				}
			}
		}
		return i;
	}
	
	@Override
	public List<MemberDistributionAllocationDto> findMemberDistributionAllocationByItemId(Long itemId) throws GlobalException {
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		/** 判断itemId是否为空 */
		if (itemId == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_NULL);
		}
		/** 判断id是否存在 */
		ItemEntity item = itemDao.selectById(itemId);
		if (item == null) {
			throw new GlobalException(GoodsSkuExceptionEnum.GOODSITEM_ID_NOT_EXIST);
		}
		List<MemberDistributionAllocationDto> memberDistributionAllocationDto= null;
		List<GoodsSkuEntity> goodsSkuList = goodSkuDao.selectGoodsSku(itemId);
		if(!(goodsSkuList == null || goodsSkuList.size()==0)) {
			GoodsSkuEntity goodsSkuEntity = goodsSkuList.get(0);
			Long skuId = goodsSkuEntity.getId();
			/**通过skuId查询会员的分销记录*/
			memberDistributionAllocationDto =memberDistributionAllocationDao.findMemberDistributionAllocationBySkuId(storeId,skuId);
		}
		return memberDistributionAllocationDto;
	}
	
	@Override
	public Integer saveMemberDistributionLog() {
		Integer i = 0;
		/**查看当天订单的会员信息*/
		List<StoreOrderDto> storeOrderDtoList = memberDistributionAllocationDao.selectStoreOrderList();
		if (!(storeOrderDtoList == null || storeOrderDtoList.size() == 0)) {
			for (StoreOrderDto storeOrderDto : storeOrderDtoList) {
				/**获取每一个会员id*/
				Long memberId = storeOrderDto.getMemberId();
				/** 所属店铺id*/
				Long storeId = storeOrderDto.getStoreId();
				/**通过memberId查询下级会员集合*/
				List<MemberEntity> resultMemberList = memberDistributionAllocationDao.selectAllMemberById(memberId);
				if (!(resultMemberList == null || resultMemberList.size() == 0)) {
					for (MemberEntity memberEntity : resultMemberList) {
						Long memberId1 = memberEntity.getId();
						/** 查询出下级会员所买商品*/
						List<StoreOrderDto> memberstoreOrderDtoList = memberDistributionAllocationDao
								.findGoodsById(memberId1);
						if (!(memberstoreOrderDtoList == null || memberstoreOrderDtoList.size() == 0)) {
							for (StoreOrderDto storeOrderDto2 : memberstoreOrderDtoList) {
								MemberDistributionLogEntity memberDistributionLogEntity = new MemberDistributionLogEntity();
								// 商品skuId
								Long goodsId = storeOrderDto2.getGoodsId();
								// 下级会员购买人数
								Integer underlingMemberNums = 0;
								// 会员购买商品数
								Long goodsNums = 0L;
								/** 根据会员和商品查询购买人数。*/
								List<StoreOrderDto> storeOrderDto3 = memberDistributionAllocationDao
										.selectUnderlingMemberNums(memberId1, goodsId);
								Double ratio = 0.0;
								MemberDistributionAllocationDto memberDistributionAllocationDto = memberDistributionAllocationDao
										.findMemberDistributionRabioByGoodsId(goodsId);
								if (memberDistributionAllocationDto != null) {
									// 分销级别
									String distriLevel = memberDistributionAllocationDto.getDistriLevel();
									// 分销比例
									ratio = memberDistributionAllocationDto.getRatio();
									memberDistributionLogEntity.setDistriLevel(distriLevel);
									memberDistributionLogEntity.setRatio(ratio);
								}
								if (!(storeOrderDto3 == null || storeOrderDto3.size() == 0)) {
									underlingMemberNums = underlingMemberNums + 1;
									for (StoreOrderDto storeOrderDto4 : storeOrderDto3) {
										/** 购买商品数 */
										Long goodsNum = storeOrderDto4.getGoodsNum();
										goodsNums = goodsNums + goodsNum;
										// 公式：计算总金额=订单数量 * 优惠后的价格
										Double totalPrice = storeOrderDto4.getGoodsNum()
												* storeOrderDto4.getSalePrice();
										// 公式：计算分销金额=总金额 * 分润比例
										Double distriPrice = totalPrice * ratio;
										memberDistributionLogEntity.setTotalPrice(totalPrice);
										memberDistributionLogEntity.setDistriPrice(distriPrice);
									}
								}
								memberDistributionLogEntity.setMemberId(memberId);
								memberDistributionLogEntity.setStoreId(storeId);
								memberDistributionLogEntity.setGoodsNum(goodsNums);
								memberDistributionLogEntity.setSkuId(goodsId);
								/**--分销时间暂定为执行的当前系统时间*/
								memberDistributionLogEntity.setDistriDate(new Date());
								memberDistributionLogEntity.setUnderlingMemberNum(underlingMemberNums);
								i = memberDistributionLogDao.insert(memberDistributionLogEntity);

							}
						}
						/** 通过memberId查询子集会员集合 */
						List<MemberEntity> resultMemberLists = memberDistributionAllocationDao
								.selectAllMemberById(memberId1);
						if (!(resultMemberLists == null || resultMemberLists.size() == 0)) {
							for (MemberEntity memberEntitys : resultMemberLists) {
								Long memberId2 = memberEntitys.getId();
								/** 通过子集的会员id查询会员所购买的商品 */
								List<StoreOrderDto> memberstoreOrderDtoLists = memberDistributionAllocationDao
										.findGoodsById(memberId2);
								for (StoreOrderDto storeOrderDtos : memberstoreOrderDtoLists) {
									MemberDistributionLogEntity memberDistributionLogEntity = new MemberDistributionLogEntity();
									Long goodsId = storeOrderDtos.getGoodsId();
									/** 下级会员购买人数 */
									Integer underlingMemberNums = 0;
									/** 会员购买商品数 */
									Long goodsNumss = 0L;
									/** 通过会员id和goodsId查询 */
									List<StoreOrderDto> storeOrderDto3 = memberDistributionAllocationDao
											.selectUnderlingMemberNums(memberId2, goodsId);
									Double ratio = 0.0;
									/** 根据商品id算出分销金额与分润比例 */
									MemberDistributionAllocationDto memberDistributionAllocationDto = memberDistributionAllocationDao
											.findMembersDistributionRabioByGoodsId(goodsId);
									if (memberDistributionAllocationDto != null) {
										// 分销级别
										String distriLevel = memberDistributionAllocationDto.getDistriLevel();
										// 分销比例
										ratio = memberDistributionAllocationDto.getRatio();
										memberDistributionLogEntity.setDistriLevel(distriLevel);
										memberDistributionLogEntity.setRatio(ratio);
									}
									if (!(storeOrderDto3 == null || storeOrderDto3.size() == 0)) {
										underlingMemberNums = underlingMemberNums + 1;
										for (StoreOrderDto storeOrderDto4 : storeOrderDto3) {
											/** 购买商品数 */
											Long goodsNum = storeOrderDto4.getGoodsNum();
											goodsNumss = goodsNumss + goodsNum;
											// 公式：计算总金额=订单数量 * 优惠后的价格
											Double totalPrice = storeOrderDto4.getGoodsNum()
													* storeOrderDto4.getSalePrice();
											// 公式：计算分销金额=总金额 * 分润比例
											Double distriPrice = totalPrice * ratio;
											memberDistributionLogEntity.setTotalPrice(totalPrice);
											memberDistributionLogEntity.setDistriPrice(distriPrice);
										}
									}
									memberDistributionLogEntity.setMemberId(memberId);
									memberDistributionLogEntity.setStoreId(storeId);
									memberDistributionLogEntity.setGoodsNum(goodsNumss);
									memberDistributionLogEntity.setSkuId(goodsId);
									memberDistributionLogEntity.setDistriDate(new Date());
									memberDistributionLogEntity.setUnderlingMemberNum(underlingMemberNums);
									i = memberDistributionLogDao.insert(memberDistributionLogEntity);
								}
							}
						}
					}
				}

			}
		}
		return i;
	}

	@Override
	public Page<MemberDistributionLogDto> getMemberDistributionLogList(Page<MemberDistributionLogDto> page,
			MemberDistributionLogDto memberDistributionLogDto) {
		/** 查询所有的会员id */
		List<MemberDistributionLogDto> pageMemberDistributionLog = memberDistributionLogDao
				.selectPageMemberDistributionLog(page, memberDistributionLogDto);
		if (!(pageMemberDistributionLog == null || pageMemberDistributionLog.size() == 0)) {
			for (MemberDistributionLogDto memberDistributionLogDtos : pageMemberDistributionLog) {
				Long memberId = memberDistributionLogDtos.getMemberId();
				/**通过会员id查询会员的基本信息*/
				MemberDistributionLogDto memberDistributionLogDtoss = memberDistributionLogDao
						.selectMemberDisbutionLogByMemberId(memberId);
				if (memberDistributionLogDtoss != null) {
					String storeName = memberDistributionLogDtoss.getStoreName();
					if (storeName != null) {
						memberDistributionLogDtos.setStoreName(storeName);
					}
					String memberName = memberDistributionLogDtoss.getMemberName();
					if (memberName != null) {
						memberDistributionLogDtos.setMemberName(memberName);
					}
				}
				/** 根据memberId与分销级别查询该会员的一级分销金额 */
				List<MemberDistributionLogDto> primaryDistributionPriceMemberDistribution = memberDistributionLogDao
						.findMemberDistributionByMemberId(memberId);
				Double primaryDistributionPrice = 0.0;
				Long primarytotalDistributionNum = 0L;
				if (!(primaryDistributionPriceMemberDistribution == null
						|| primaryDistributionPriceMemberDistribution.size() == 0)) {
					for (MemberDistributionLogDto primaryDistributionmemberDistributionLogDto : primaryDistributionPriceMemberDistribution) {
						/** 计算一级分销金额 */
						Double distriPrice = primaryDistributionmemberDistributionLogDto.getDistriPrice();
						Long goodsNum = primaryDistributionmemberDistributionLogDto.getGoodsNum();
						primaryDistributionPrice = distriPrice + primaryDistributionPrice;
						/** 计算一级的分销商品数量 */
						primarytotalDistributionNum = primarytotalDistributionNum + goodsNum;
					}
				}
				memberDistributionLogDtos.setPrimaryDistributionPrice(primaryDistributionPrice);
				memberDistributionLogDtos.setPrimarytotalDistributionNum(primarytotalDistributionNum);
				/** 根据memberId与分销级别为2查询会员的二级分销金额 */
				List<MemberDistributionLogDto> levelDistributionPriceMemberDistribution = memberDistributionLogDao
						.findlevelDistributionPriceMemberDistributionByMemberId(memberId);
				Double levelDistributionPrice = 0.0;
				Long leveltotalDistributionNum = 0L;
				if (!(levelDistributionPriceMemberDistribution == null
						|| levelDistributionPriceMemberDistribution.size() == 0)) {
					for (MemberDistributionLogDto levelDistributionPricememberDistributionLogDto : levelDistributionPriceMemberDistribution) {
						/** 计算二级分销金额 */
						Double distriPrice = levelDistributionPricememberDistributionLogDto.getDistriPrice();
						levelDistributionPrice = levelDistributionPrice + distriPrice;
						Long goodsNums = levelDistributionPricememberDistributionLogDto.getGoodsNum();
						/** 计算二级的分销商品数量 */
						leveltotalDistributionNum = leveltotalDistributionNum + goodsNums;
					}
				}
				memberDistributionLogDtos.setLevelDistributionPrice(levelDistributionPrice);
				memberDistributionLogDtos.setLeveltotalDistributionNum(leveltotalDistributionNum);
				/** 计算总分销数量 */
				memberDistributionLogDtos
						.setTotalDistributionAmount(primarytotalDistributionNum + leveltotalDistributionNum);
				/** 计算总分销金额 */
				memberDistributionLogDtos.setDistriPriceAmount(primaryDistributionPrice + levelDistributionPrice);
			}
		}
		page.setRecords(pageMemberDistributionLog);
		return page;
	}

	@Override
	public Page<MemberDistributionLogDto> getMerchantMemberDistributionLogList(Page<MemberDistributionLogDto> page,
			MemberDistributionLogDto memberDistributionLogDto) {
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		memberDistributionLogDto.setStoreId(storeId);
		List<MemberDistributionLogDto> pageMerchantMemberDistributionLog = memberDistributionLogDao
				.getMerchantMemberDistributionLogList(page, memberDistributionLogDto);
		if (!(pageMerchantMemberDistributionLog == null || pageMerchantMemberDistributionLog.size() == 0)) {
			for (MemberDistributionLogDto memberDistributionLogDtos : pageMerchantMemberDistributionLog) {
				Long memberId = memberDistributionLogDtos.getMemberId();
				/** 根据memberId与分销级别查询该会员的一级分销金额 */
				List<MemberDistributionLogDto> primaryDistributionPriceMemberDistribution = memberDistributionLogDao
						.findMemberDistributionByMemberId(memberId);
				Double primaryDistributionPrice = 0.0;
				Double totalPriceAmounts = 0.0;
				if (!(primaryDistributionPriceMemberDistribution == null
						|| primaryDistributionPriceMemberDistribution.size() == 0)) {
					for (MemberDistributionLogDto primaryDistributionmemberDistributionLogDto : primaryDistributionPriceMemberDistribution) {
						/** 计算一级分销金额 */
						Double distriPrice = primaryDistributionmemberDistributionLogDto.getDistriPrice();
						primaryDistributionPrice = distriPrice + primaryDistributionPrice;
						/** 计算一级商品的总金额 */
						Double totalPrice = primaryDistributionmemberDistributionLogDto.getTotalPrice();
						totalPriceAmounts = totalPriceAmounts + totalPrice;
					}
				}
				memberDistributionLogDtos.setPrimaryDistributionPrice(primaryDistributionPrice);
				/** 根据memberId与分销级别为2查询会员的二级分销金额 */
				List<MemberDistributionLogDto> levelDistributionPriceMemberDistribution = memberDistributionLogDao
						.findlevelDistributionPriceMemberDistributionByMemberId(memberId);
				Double levelDistributionPrice = 0.0;
				Double levelTotalPriceAmount = 0.0;
				if (!(levelDistributionPriceMemberDistribution == null
						|| levelDistributionPriceMemberDistribution.size() == 0)) {
					for (MemberDistributionLogDto levelDistributionPricememberDistributionLogDto : levelDistributionPriceMemberDistribution) {
						/** 计算二级分销金额 */
						Double distriPrice = levelDistributionPricememberDistributionLogDto.getDistriPrice();
						levelDistributionPrice = levelDistributionPrice + distriPrice;
						/** 计算二级商品的总金额 */
						Double totalPrice = levelDistributionPricememberDistributionLogDto.getTotalPrice();
						levelTotalPriceAmount = levelTotalPriceAmount + totalPrice;
					}
				}
				memberDistributionLogDtos.setLevelDistributionPrice(levelDistributionPrice);
				/** 计算总分润金额 */
				memberDistributionLogDtos.setDistriPriceAmount(primaryDistributionPrice + levelDistributionPrice);
				/** 计算总金额 */
				memberDistributionLogDtos.setTotalPriceAmount(levelTotalPriceAmount + totalPriceAmounts);
			}
		}

		page.setRecords(pageMerchantMemberDistributionLog);
		return page;
	}

	@Override
	public Page<MemberDistributionLogDto> findMemberDistributionListByMemberId(Page<MemberDistributionLogDto> page,
			MemberDistributionLogDto memberDistributionLogDto) {
		List<MemberDistributionLogDto> pageMemberDistributionLogByMemberId = memberDistributionLogDao
				.findMemberDistributionListByMemberId(page, memberDistributionLogDto);
		page.setRecords(pageMemberDistributionLogByMemberId);
		return page;
	}

	@Override
	public Page<MemberDistributionLogDto> findMembersDistributionListByMemberId(Page<MemberDistributionLogDto> page,
			MemberDistributionLogDto memberDistributionLogDto) {
		List<MemberDistributionLogDto> membersDistributionListByMemberId = memberDistributionLogDao
				.findMembersDistributionListByMemberId(page, memberDistributionLogDto);
		page.setRecords(membersDistributionListByMemberId);
		return page;
	}

}
