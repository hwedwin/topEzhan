package com.topaiebiz.giftcard.manage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.giftcard.manage.entity.CardUseLogEntity;

@Mapper
public interface GiftCardUseLogDao extends BaseDao<CardUseLogEntity> {
    
	/**
	 * 
	 * Description：根据总订单编号查询礼卡使用记录表中所对应的礼卡. 
	 * 
	 * Author Murray.Li 
	 * 
	 * @param platformOrderNo
	 * @return
	 */
	List<CardUseLogEntity> selectbyOrderId(Long platformOrderNo);



}
