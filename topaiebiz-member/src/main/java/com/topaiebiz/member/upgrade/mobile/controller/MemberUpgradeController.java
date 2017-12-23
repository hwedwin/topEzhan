package com.topaiebiz.member.upgrade.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.member.mgmt.exception.MemberMgmtExceptionEnum;
import com.topaiebiz.member.upgrade.exception.MemberUpgradeExceptionEnum;
import com.topaiebiz.member.upgrade.service.MemberUpgradeService;

/**
 * 
 * Description： 会员成长分控制层  
 * 
 * 
 * Author Scott.Yang
 *    
 * Date 2017年10月30日 下午3:24:00 
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Controller
@RequestMapping("/app/member/upgrade")
public class MemberUpgradeController {

	@Autowired
	private MemberUpgradeService memberUpgradeService;
	
	/**
	 * Description： 添加会员成长分获取记录
	 * 
	 * Author Scott.Yang
	 * 
	 * @param id
	 * 		会员积分ID
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/saveMemberUpgraderadeLog",method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo saveMemberUpgraderadeLog(Long memberId,Long id,Long promotionId) throws GlobalException {
		if (null == id) {
			throw new GlobalException(MemberUpgradeExceptionEnum.MEMBERUPGRADE_ID_NOT_NULL);
		}
		if(null == memberId) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		if(null == promotionId) {
			throw new GlobalException(MemberUpgradeExceptionEnum.PROMOTION_ID_NOT_NULL);
		}
		memberUpgradeService.saveMemberUpgraderadeLog(id,memberId,promotionId);
		return new ResponseInfo();
	}
}
