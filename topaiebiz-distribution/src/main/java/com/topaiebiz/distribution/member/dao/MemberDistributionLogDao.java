package com.topaiebiz.distribution.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.distribution.member.dto.MemberDistributionLogDto;
import com.topaiebiz.distribution.member.entity.MemberDistributionLogEntity;

/**
 * Description： 会员分销记录Dao。
 * 
 * Author Harry
 * 
 * Date 2017年10月7日 下午8:26:33
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface MemberDistributionLogDao extends BaseDao<MemberDistributionLogEntity> {

	/**
	 * Description：会员分销记录列表加分页
	 * 
	 * Author Harry
	 * 
	 * @param page
	 *            分页单位
	 * @param memberDistributionLogDto
	 *            会员分销记录Dto
	 * @return
	 */
	List<MemberDistributionLogDto> selectPageMemberDistributionLog(Page<MemberDistributionLogDto> page,
			MemberDistributionLogDto memberDistributionLogDto);

	/**
	 * Description：根据storeId查询会员分销列表分页加搜索
	 * 
	 * Author Harry
	 * 
	 * @param page
	 *            分页单位
	 * @param memberDistributionLogDto
	 *            会员分销记录Dto
	 * @return
	 */
	List<MemberDistributionLogDto> findMemberDistributionListByStoreId(Page<MemberDistributionLogDto> page,
			MemberDistributionLogDto memberDistributionLogDto);

	/**
	 * Description：商家端会员分销记录列表分页加搜索。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 *            分页单位
	 * @param memberDistributionLogDto
	 *            会员分销记录Dto
	 * @return
	 */
	List<MemberDistributionLogDto> getMerchantMemberDistributionLogList(Page<MemberDistributionLogDto> page,
			MemberDistributionLogDto memberDistributionLogDto);

	/**
	 * Description：商家端根据memberId查询会员分销商品的记录。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 *            分页单位
	 * @param memberDistributionLogDto
	 *            会员分销记录Dto
	 * @return
	 */
	List<MemberDistributionLogDto> findMemberDistributionListByMemberId(Page<MemberDistributionLogDto> page,
			MemberDistributionLogDto memberDistributionLogDto);

	/**
	 * Description：通过MemberId查询商品购买数
	 * 
	 * Author Harry
	 * 
	 * @param memberId
	 *            会员id
	 * @param skuId
	 *            商品SKUID
	 * @return
	 */
	Long findGoodsNumByMemberId(@Param("memberId") Long memberId, @Param("skuId") Long skuId);

	/**
	 * Description： 通过MemberId查询对应的分销记录信息
	 * 
	 * Author Harry
	 * 
	 * @param memberId
	 *            会员id
	 * @return
	 */
	MemberDistributionLogDto selectMemberDisbutionLogByMemberId(Long memberId);

	/**
	 * Description：根据memberId与分销级别查询该会员的一级分销金额
	 * 
	 * Author Harry
	 * 
	 * @param memberId
	 *            会员id
	 * @return
	 */
	List<MemberDistributionLogDto> findMemberDistributionByMemberId(Long memberId);

	/**
	 * Description： 根据memberId与分销级别为2查询会员的二级分销金额。
	 * 
	 * Author Harry
	 * 
	 * @param memberId
	 *            会员ID
	 * @return
	 */
	List<MemberDistributionLogDto> findlevelDistributionPriceMemberDistributionByMemberId(Long memberId);

	/**
	 * Description： 根据会员id查询会员子集与下级分销记录列表分页加搜索。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 *            分页单位
	 * @param memberDistributionLogDto
	 *            会员分销记录Dto
	 * @return
	 */
	List<MemberDistributionLogDto> findMembersDistributionListByMemberId(Page<MemberDistributionLogDto> page,
			MemberDistributionLogDto memberDistributionLogDto);

	/**
	 * 
	 * Description： 通过StoreID查看会员记录分销金额列表加分页。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 *            分页单位
	 * @param memberDistributionLogDto
	 *            会员分销记录Dto
	 * @return
	 */
	List<MemberDistributionLogDto> findMemberDistributionInfoByStoreId(Page<MemberDistributionLogDto> page,
			MemberDistributionLogDto memberDistributionLogDto);

	/**
	 * Description：通过会员id查看会员购买的商品列表。
	 * 
	 * Author Harry
	 * 
	 * @param page
	 *            分页单位
	 * @param memberDistributionLogDto
	 *            会员分销记录Dto
	 * @return
	 */
	List<MemberDistributionLogDto> findGoodsMemberDistributionListByMemberId(Page<MemberDistributionLogDto> page,
			MemberDistributionLogDto memberDistributionLogDto);

	/**
	 * Description：根据StoreId查询会员分销记录的分销金额。
	 * 
	 * Author Harry
	 * 
	 * @param storeId
	 *            店铺id
	 * @return
	 */
	List<MemberDistributionLogDto> getMemberDistibutionDisPriceByStoreId(Long storeId);
	
	/**
	 * Description：查询会员分销记录所有会员，以及会员的分销金额。
	 * 
	 * Author Harry
	 * 
	 * @return
	 */
	List<MemberDistributionLogDto> selectDistributionDistriPrice();
	
	/**
	 * Description：根据会员id查询可提现金额。
	 * 
	 * Author Harry
	 * 
	 * @param memberId
	 * @return
	 */
	Double selectAllowWithdrawalSum(Long memberId);
	
	/**
	 * Description：  根据店铺Id和具体商品Id查询商品的会员分销金额
	 * 
	 * Author Harry
	 * 
	 * @param storeId
	 * @param goodsId
	 * @return
	 */
	Double selectMemberDistibutionDisPriceByStoreIdAndSkuId(@Param("memberId")Long memberId, @Param("goodsId")Long goodsId);


}
