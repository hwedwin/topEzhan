package com.topaiebiz.member.upgrade.service;

import com.nebulapaas.web.exception.GlobalException;

public interface MemberUpgradeService {
	/**
	 * 
	 * Description： 添加会员成长分获取记录  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param id
	 * @param memberId
	 * @param promotionId
	 * @throws GlobalException
	 */
	void saveMemberUpgraderadeLog(Long id, Long memberId,Long promotionId) throws GlobalException;

}
