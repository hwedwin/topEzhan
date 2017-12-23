package com.topaiebiz.distribution.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.distribution.member.dto.MemberDistributionAllocationDto;
import com.topaiebiz.distribution.member.entity.MemberDistributionAllocationEntity;
import com.topaiebiz.member.mgmt.entity.MemberEntity;
import com.topaiebiz.transaction.order.merchant.dto.StoreOrderDto;

/**
 * Description： 会员分销Dao。
 * 
 * Author Harry
 * 
 * Date 2017年10月5日 下午3:38:53
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Mapper
public interface MemberDistributionAllocationDao extends BaseDao<MemberDistributionAllocationEntity> {

	/**
	 * Description： 查询订单表中的会员信息。
	 * 
	 * Author Harry
	 * 
	 * @return
	 */
	List<StoreOrderDto> selectStoreOrderList();

	/**
	 * Description：根据memberId查询会员的下级信息
	 * 
	 * Author Harry
	 * 
	 * @param id
	 * @return
	 */
	List<MemberEntity> selectAllMemberById(Long memberId);

	/**
	 * 
	 * Description： 通过goodsId和分销级别查分销比例
	 * 
	 * Author Harry
	 * 
	 * @param goodsId
	 * @return
	 */
	MemberDistributionAllocationDto findMemberDistributionRabioByGoodsId(Long goodsId);

	/**
	 * Description： 通过会员id查询所购买的商品
	 * 
	 * Author Harry
	 * 
	 * @param id
	 * @return
	 */
	List<StoreOrderDto> findGoodsById(Long id);

	/**
	 * Description： 通过goodsId和分销级别查子集会员分销比例
	 * 
	 * Author Harry
	 * 
	 * @param goodsId
	 * @return
	 */
	MemberDistributionAllocationDto findMembersDistributionRabioByGoodsId(Long goodsId);

	/**
	 * Description： 根据会员和商品查询购买人数。
	 * 
	 * Author Harry
	 * 
	 * @param memberId1
	 *            会员编号
	 * @param goodsId
	 *            商品编号
	 * @return
	 */
	List<StoreOrderDto> selectUnderlingMemberNums(@Param("memberId1")Long memberId1, @Param("goodsId")Long goodsId);
	
	/**
	 * 
	 * Description： 根据skuId查询会员分润比例配置信息。
	 * 
	 * Author Harry  
	 * 
	 * @param skuId
	 * @return
	 */
	List<MemberDistributionAllocationDto> findMemberDistributionAllocationBySkuId(@Param("storeId")Long storeId,@Param("skuId")Long skuId);

	/**
	 * Description： 删除会员分销比例配置 
	 * 
	 * Author Harry  
	 * 
	 * @param id 会员分销比例配置id
	 * @return
	 */
	Integer deleteMemberDistributionAllocation(Long id);

}
