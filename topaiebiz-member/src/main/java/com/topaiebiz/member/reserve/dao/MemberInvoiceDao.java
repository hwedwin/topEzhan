/**
 * 
 */
package com.topaiebiz.member.reserve.dao;

import org.apache.ibatis.annotations.Mapper;

import com.nebulapaas.data.mybatis.common.BaseDao;
import com.topaiebiz.member.reserve.entity.MemberInvoiceEntity;

/**  
 * Description： 会员发票dao接口
 * 
 * 
 * Author hxpeng 
 *    
 * Date 2017年10月26日 上午9:56:25 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */

@Mapper
public interface MemberInvoiceDao extends BaseDao<MemberInvoiceEntity> {

}
