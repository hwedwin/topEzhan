package com.topaiebiz.member.mgmt.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.member.mgmt.dto.MemberDto;
import com.topaiebiz.member.mgmt.dto.MemberMgmtDto;
import com.topaiebiz.member.mgmt.dto.MemberRegisterDto;
import com.topaiebiz.member.mgmt.dto.MemberStatisticsDto;
import com.topaiebiz.member.mgmt.entity.MemberEntity;
import com.topaiebiz.member.reserve.entity.MemberBindAccountEntity;
import com.topaiebiz.member.reserve.entity.MemberTypeEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface MemberMgmtService {
	
	/**
	 * Description： 会员等级分页加列表加查询   
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param page
	 * 		分页参数
	 * @param memberDto
	 * 		会员信息Dto
	 * @return
	 */
	Page<MemberDto> listMemberMgmt(Page<MemberDto> page, MemberDto memberDto);
	/**
	 * Description：  获取会员类型列表 
	 * 
	 * Author Scott.Yang   
	 * 
	 * @return
	 */
	List<MemberTypeEntity> getListOfMemberType();
	/**
	 * Description：添加会员信息  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param entity
	 * 		会员信息Entity
	 * @return
	 * @throws GlobalException 
	 */
	Integer saveMemberMgmt(MemberEntity entity) throws GlobalException;
	/**
	 * Description： 修改会员信息  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberDto
	 * 		会员信息Entity
	 * @return
	 * @throws GlobalException 
	 */
	Integer modifyMemberMgmt(MemberDto memberDto) throws GlobalException;
	/**
	 * Description： 根据id查询会员信息  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param id
	 * 		会员编号ID
	 * @return
	 * @throws GlobalException 
	 */
	MemberEntity findMemberMgmtById(Long id) throws GlobalException;
	/**
	 * Description： 根据id删除会员信息(修改状态)  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param id
	 * 		会员编号ID
	 * @throws GlobalException 
	 */
	void removeMemberMgmts(Long[] id) throws GlobalException;
	/**
	 * Description： 根据id冻结会员状态  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param id
	 * 		会员编号ID
	 * @throws GlobalException 
	 */
	void congelationMemberMgmt(Long id) throws GlobalException;
	/**
	 *
	 * Description： App端登录
	 *
	 * Author Scott.Yang
	 *
	 * @param memberDto
	 * @return
	 * @throws GlobalException
	 */
	Map<String,Object> getMemberMgmt(MemberDto memberDto) throws GlobalException;
	/**
	 *
	 * Description：快速登录会员信息   
	 *
	 * Author Scott.Yang   
	 *
	 * @param memberRegisterDto
	 * @return
	 * @throws GlobalException
	 */
	Map<String, Object> quickLoginMemberMgmt(String captcha,MemberRegisterDto memberRegisterDto,HttpServletRequest request) throws GlobalException;
	/**
	 * 
	 * Description： 会员管理分页加列表加查询(商家端)   
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param page
	 * @param memberDto
	 * @return
	 */
	Page<MemberDto> listMemberMgmtForSeller(Page<MemberDto> page, MemberDto memberDto);
	/**
	 * 
	 * Description： 添加注册会员信息   
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberRegisterDto
	 * @return
	 * @throws GlobalException
	 */
	Map<String, Object> registerMemberMgmt(MemberRegisterDto memberRegisterDto) throws GlobalException;
	/**
	 * 
	 * Description： 找回密码 
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param captcha
	 * @param memberRegisterDto
	 * @param request
	 * @return
	 * @throws GlobalException
	 */
	Map<String, Object> findMemberMgmtByTelphone(String captcha, MemberRegisterDto memberRegisterDto,
			HttpServletRequest request) throws GlobalException;
	/**
	 * 
	 * Description： 重设会员密码  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberRegisterDto
	 * @return
	 * @throws GlobalException
	 */
	Map<String, Object> resetMemberMgmtForPassword(MemberRegisterDto memberRegisterDto) throws GlobalException;
	/**
	 * 
	 * Description： 修改会员信息(C端)   
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberMgmtDto
	 * @return
	 */
	Integer modifyMemberInfo(MemberMgmtDto memberMgmtDto);
	/**
	 * Description： 根据id解除冻结会员状态  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param id
	 * 		会员编号ID
	 * @throws GlobalException 
	 */
	void relieveMemberMgmt(Long id) throws GlobalException;
	/**
	 * 
	 * Description：  会员增值情况视图(按年展示)
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberStatisticsDto
	 * @return
	 * @throws Exception
	 */
	List<MemberStatisticsDto> listMemberRecordByYear(MemberStatisticsDto memberStatisticsDto)  throws Exception;
	/**
	 * 
	 * Description： 会员增值情况视图(按月展示)
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberStatisticsDto
	 * @return
	 * @throws Exception
	 */
	List<MemberStatisticsDto> listMemberRecordByMonths(MemberStatisticsDto memberStatisticsDto) throws Exception;
	/**
	 * 
	 * Description：验证手机验证码 
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param captcha
	 * @param request
	 * @return
	 * @throws GlobalException
	 */
	Integer checkTelphoneIdentifyingCode(String telephone,String captcha, HttpServletRequest request) throws GlobalException;
	/**
	 * 
	 * Description：更改手机验证  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param captcha
	 * @param memberRegisterDto
	 * @param request
	 * @return
	 * @throws GlobalException
	 */
	Integer modifyMemberTelphone(String captcha, MemberRegisterDto memberRegisterDto, HttpServletRequest request) throws GlobalException;
	/**
	 * 
	 * Description： 更改登录密码  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param captcha
	 * @param memberRegisterDto
	 * @param request
	 * @return
	 * @throws GlobalException
	 */
	Integer modifyMemberPassword(String captcha, MemberRegisterDto memberRegisterDto, HttpServletRequest request) throws GlobalException;
	
	MemberEntity getMemberByuserTelephone(String telephone);
	/**
	 * 
	 * Description： 设置支付密码   
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberRegisterDto
	 * @return
	 */
	Integer settingMemberPayPassword(MemberRegisterDto memberRegisterDto);
	/**
	 * 
	 * Description： 更改支付密码  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param captcha
	 * @param memberRegisterDto
	 * @param request
	 * @return
	 */
	Integer modifyMemberPayPassword(String captcha, MemberRegisterDto memberRegisterDto, HttpServletRequest request);
	/**
	 * 
	 * Description： 会员增值情况视图(按年展示商家端) 
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberStatisticsDto
	 * @return
	 * @throws Exception
	 */
	List<MemberStatisticsDto> listMemberRecordByYearOfBusiness(MemberStatisticsDto memberStatisticsDto) throws Exception;
	/**
	 * 
	 * Description： 会员增值情况视图(按月展示商家端)  
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberStatisticsDto
	 * @return
	 * @throws Exception
	 */
	List<MemberStatisticsDto> listMemberRecordByMonthsOfBusiness(MemberStatisticsDto memberStatisticsDto) throws Exception;
	/**
	 * 
	 * Description： 根据id查询会员可用积分 
	 * 
	 * Author Scott.Yang   
	 * 
	 * @param memberId
	 * @return
	 */
	Long findMemberMgmtByIdSum(Long memberId);


	/**
	*
	* Description: 绑定微信用户
	*
	* Author: hxpeng
	* createTime: 2017/11/18
	*
	* @param:
	**/
	void bindWechat(String openId, Long memberId, String ip);

	/**
	*
	* Description: 根据微信openId查询用户
	*
	* Author: hxpeng
	* createTime: 2017/11/18
	*
	* @param:
	**/
	MemberBindAccountEntity findOneByOpenIdInWechat(String openId);

	/**
	*
	* Description: 根据用户ID查询微信的绑定信息
	*
	* Author: hxpeng
	* createTime: 2017/11/18
	*
	* @param:
	**/
	MemberBindAccountEntity findOneByMemberInWechat(Long memberId);
	
	/**
	 * Description：创建管理人员
	 * 
	 * Author Aaron.Xue  
	 * 
	 * @param memberId
	 * @param type
	 */
	void addUser(Long memberId, Integer type);
}
