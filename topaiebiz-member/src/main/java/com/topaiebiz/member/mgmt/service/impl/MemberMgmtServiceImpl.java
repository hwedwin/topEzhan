package com.topaiebiz.member.mgmt.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.plugins.Page;
import com.topaiebiz.system.security.dao.SystemUserDao;
import com.topaiebiz.system.security.entity.SystemUserEntity;
import com.topaiebiz.system.security.util.SecurityContextUtils;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.member.mgmt.dao.MemberMgmtDao;
import com.topaiebiz.member.mgmt.dto.MemberDto;
import com.topaiebiz.member.mgmt.dto.MemberMgmtDto;
import com.topaiebiz.member.mgmt.dto.MemberRegisterDto;
import com.topaiebiz.member.mgmt.dto.MemberStatisticsDto;
import com.topaiebiz.member.mgmt.entity.MemberEntity;
import com.topaiebiz.member.mgmt.entity.MemberStoreBindLogEntity;
import com.topaiebiz.member.mgmt.exception.MemberMgmtExceptionEnum;
import com.topaiebiz.member.mgmt.service.MemberMgmtService;
import com.topaiebiz.member.reserve.dao.MemberBindAccountDao;
import com.topaiebiz.member.reserve.entity.MemberBindAccountEntity;
import com.topaiebiz.member.reserve.entity.MemberTypeEntity;
import com.topaiebiz.sms.dahantc.service.MessageService;
import com.topaiebiz.system.moble.security.dto.TokenDto;
import com.topaiebiz.system.moble.security.service.TokenService;
import com.topaiebiz.system.moble.security.util.MD5Util;
import com.topaiebiz.system.moble.security.util.TokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Description： 会员信息实现类
 * 
 * 
 * Author Scott.Yang
 * 
 * Date 2017年9月26日 下午8:11:51
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Service
@Transactional
public class MemberMgmtServiceImpl implements MemberMgmtService {

	@Autowired
	private MemberMgmtDao memberMgmtDao;
	/** 引入Token验证*/
	@Autowired
	private TokenService tokenService;
	/** 系统权限用户持久层接口*/
	@Autowired
	private SystemUserDao systemUserDao;
	
	@Autowired
	private MessageService messageService;

	@Autowired
	private MemberBindAccountDao memberBindAccountDao;
	
	@Override
	public Page<MemberDto> listMemberMgmt(Page<MemberDto> page, MemberDto memberDto) {
		page.setRecords(memberMgmtDao.selectMemberMgmt(page, memberDto));
		return page;
	}

	@Override
	public List<MemberTypeEntity> getListOfMemberType() {
		return memberMgmtDao.selectListOfMemberType();
	}

	@Override
	public Integer saveMemberMgmt(MemberEntity member) throws GlobalException {
		MemberEntity findMemberByCode = memberMgmtDao.selectMemberByCode(member.getMemberCode());
		/** 对会员编号进行重复验证 */
		if (null != findMemberByCode) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_MEMBERCODE_NOT_REPETITION);
		}
		MemberEntity findMemberByuserName = memberMgmtDao.selectMemberByuserName(member.getUserName());
		/** 对会员用户名进行重复验证 */
		if (null != findMemberByuserName) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_USERNAME_NOT_REPETITION);
		}
		member.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
		member.setCreatedTime(new Date());
		memberMgmtDao.insert(member);
		/** 判断是否存在店铺Id */
		if (member.getStoreId() != null) {
			MemberStoreBindLogEntity memberStoreBindLogEntity = new MemberStoreBindLogEntity();
			memberStoreBindLogEntity.setId(member.getId());
			memberStoreBindLogEntity.setMemberId(member.getId());
			memberStoreBindLogEntity.setStoreId(member.getStoreId());
			memberStoreBindLogEntity.setType(member.getBindingState());
			memberStoreBindLogEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
			memberStoreBindLogEntity.setCreatedTime(new Date());
			return memberMgmtDao.insertMemberStoreBindLogEntity(memberStoreBindLogEntity);
		}
		return memberMgmtDao.insert(member);

	}

	@Override
	public Integer modifyMemberMgmt(MemberDto member) throws GlobalException {
		MemberEntity findMemberByCode = memberMgmtDao.selectMemberByCode(member.getMemberCode());
		/** 对会员编号进行重复验证 */
		if (null != findMemberByCode) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_MEMBERCODE_NOT_REPETITION);
		}
		MemberEntity findMemberByuserName = memberMgmtDao.selectMemberByuserName(member.getUserName());
		/** 对会员用户名进行重复验证 */
		if (null != findMemberByuserName) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_USERNAME_NOT_REPETITION);
		}
		MemberEntity memberEntity = memberMgmtDao.selectById(member.getId());
		BeanUtils.copyProperties(member, memberEntity);
		memberEntity.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
		memberEntity.setLastModifiedTime(new Date());
		return memberMgmtDao.updateById(memberEntity);
	}

	@Override
	public MemberEntity findMemberMgmtById(Long id) throws GlobalException {
		MemberEntity memberEntity = memberMgmtDao.selectById(id);
		/** 判断会员是否存在 */
		if (null == memberEntity) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_EXIST);
		}
		return memberEntity;
	}

	@Override
	public void removeMemberMgmts(Long[] id) throws GlobalException {
		for (Long longs : id) {
			MemberEntity memberEntity = memberMgmtDao.selectById(longs);
			/** 判断会员是否存在 */
			if (null == memberEntity) {
				throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_EXIST);
			}
		}
		memberMgmtDao.deleteMemberMgmts(id);
		//同步删除用户表
		for (Long id1 : id) {
			MemberEntity member = memberMgmtDao.selectById(id1);
			this.deleteSystemUser(member);
		}
	}

	@Override
	public void congelationMemberMgmt(Long id) throws GlobalException {
		MemberEntity memberEntity = memberMgmtDao.selectById(id);
		/** 判断会员是否存在 */
		if (null == memberEntity) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_EXIST);
		}
		memberMgmtDao.congelationMemberMgmt(id);

	}

	@Override
	public Map<String,Object> quickLoginMemberMgmt(String captcha,MemberRegisterDto memberRegisterDto,HttpServletRequest request) throws GlobalException {
		if(StringUtils.isEmpty(captcha)) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_CAPTCHA_NOT_NULL);
		}
		Integer state = null;
		/** 判断验证码是否一致*/
		messageService.verifyCaptcha(memberRegisterDto.getTelephone(), captcha);
		//校验手机号
		MemberEntity findMemberByuserTelephone = memberMgmtDao.selectMemberByuserTelephone(memberRegisterDto.getTelephone());
		Map<String,Object> map = new HashMap<String,Object>();
		/** 用户存在,跳转主页*/
		if(findMemberByuserTelephone != null) {
			String telephone = findMemberByuserTelephone.getTelephone();
			String token = tokenService.login(telephone);
			map.put("token",token);
			state = 1;
		}
		/** 用户不存在,跳转填写密码*/
		if(findMemberByuserTelephone == null) {
			String telephone = memberRegisterDto.getTelephone();
			String token = tokenService.login(telephone);
			map.put("token",token);
			state = 2;
		}
		map.put("state",state);
		return map;
	}
	
	
	private static String getUsername(int len) {
		// 字符源，可以根据需要删减
		String generateSource = "0123456789abcdefghigklmnopqrstuvwxyz_";
		String rtnStr = "";
		for (int i = 0; i < len; i++) {
			// 循环随机获得当次字符，并移走选出的字符
			String nowStr = String
					.valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
			rtnStr += nowStr;
			generateSource = generateSource.replaceAll(nowStr, "");
		}
		return rtnStr;
	}

	@Override
	public Map<String,Object> getMemberMgmt(MemberDto memberDto) throws GlobalException {
		/** 验证用户名是否为空 */
		if (null == memberDto.getUserName()) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_USERNAME_NOT_NULL);
		}
		/** 验证密码是否为空 */
		if (null == memberDto.getPassword()) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_PASSWORD_NOT_NULL);
		}
		
		String password = MD5Util.encode(memberDto.getPassword());
		memberDto.setPassword(password);
		MemberEntity member = null;
		Map<String,Object> map = new HashMap<String,Object>();
		//手机号查用户
		member = memberMgmtDao.selectMemberByuserTelephone(memberDto.getUserName());
		/** 验证用户是否存在 */
		if(null == member) {
			member = memberMgmtDao.selectMemberByuserName(memberDto.getUserName());
		}
		if(null == member) {
			//用户不存在
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_USER_NO_EXISTENCE);
		}
		if (member.getPassword().equals(memberDto.getPassword())) {
			String token = tokenService.login(member.getTelephone());
			map.put("token",token);
			return map;
		}else{
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_PASSWORD_ERROR);
		}
	}

	@Override
	public Page<MemberDto> listMemberMgmtForSeller(Page<MemberDto> page, MemberDto memberDto) {
		//根据电话查到会员，获取所属店铺
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		memberDto.setStoreId(storeId);
		page.setRecords(memberMgmtDao.selectMemberMgmtForSeller(page, memberDto));
		return page;
	}

	@Override
	public Map<String, Object> registerMemberMgmt(MemberRegisterDto memberRegisterDto) throws GlobalException {
		
		if(StringUtils.isEmpty(memberRegisterDto.getTelephone())) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_PRICE_NOT_NULL);
		}
		if(StringUtils.isEmpty(memberRegisterDto.getPassword())) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_PASSWORD_NOT_NULL);
		}
		/** 验证手机号用户是否存在*/
		MemberEntity findMemberByuserTelephone = memberMgmtDao.getMemberByuserTelephone(memberRegisterDto.getTelephone());
		if (null != findMemberByuserTelephone) {
			if(findMemberByuserTelephone.getAccountState() == 1) {
				throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_PHONENUMBER_EXIST);
			}
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_PHONENUMBER_EXIST);
		}
		String password = MD5Util.encode(memberRegisterDto.getPassword());
		String username = getUsername(8);
		/** 对会员用户名进行重复验证 */
		while(true) {
			MemberEntity findMemberByuserName = memberMgmtDao.selectMemberByuserName(username);
			if(null != findMemberByuserName) {
				username = getUsername(8);
			}else {
				break;
			}
		}
		memberRegisterDto.setUserName(username);
		memberRegisterDto.setPassword(password);
		MemberEntity memberEntity = new MemberEntity();
		memberEntity.setRegisterTime(new Date());
		memberEntity.setCreatedTime(new Date());
		memberEntity.setTypeId(1L);
		memberEntity.setGradeId(1L);
		memberEntity.setStoreId(1L);
		memberEntity.setUpgradeScore(0L);
		memberEntity.setAccountState(0);
		BeanUtils.copyProperties(memberRegisterDto, memberEntity);
		Integer state = memberMgmtDao.insert(memberEntity);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("state", state);
		return map;
	}

	@Override
	public Map<String, Object> findMemberMgmtByTelphone(String captcha, MemberRegisterDto memberRegisterDto,
			HttpServletRequest request) throws GlobalException {
		if(StringUtils.isEmpty(captcha)) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_CAPTCHA_NOT_NULL);
		} 
		Integer state = null;
		/** 判断验证码是否一致*/
		messageService.verifyCaptcha(memberRegisterDto.getTelephone(), captcha);
		MemberEntity findMemberByuserTelephone = memberMgmtDao.selectMemberByuserTelephone(memberRegisterDto.getTelephone());
		Map<String,Object> map = new HashMap<String,Object>();
		/** 用户存在,跳转填写重设新密码**/
		if(findMemberByuserTelephone != null) {
			String token = tokenService.login(findMemberByuserTelephone.getTelephone());
			map.put("token",token);
			state = 3;
		}
		/** 用户不存在,提示账号不存在*/
		if(findMemberByuserTelephone == null) {
			state = 4;
		}
		map.put("state",state);
		return map;
	}
	
	@Override
	public Map<String, Object> resetMemberMgmtForPassword(MemberRegisterDto memberRegisterDto) throws GlobalException {
		TokenUtil.verifyToken(memberRegisterDto.getToken());
		TokenDto tokenDetail = TokenUtil.getTokenDetail(memberRegisterDto.getToken());
		Long memberId = tokenDetail.getMemberId();
		if(StringUtils.isEmpty(memberRegisterDto.getTelephone())) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_USERNAME_NOT_NULL);
		}
		if(StringUtils.isEmpty(memberRegisterDto.getPassword())) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_PASSWORD_NOT_NULL);
		}
		/** 验证手机号用户是否存在*/
		MemberEntity findMemberByuserTelephone = memberMgmtDao.selectMemberByuserTelephone(memberRegisterDto.getTelephone());
		if (null == findMemberByuserTelephone) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_USER_NO_EXISTENCE);
		}
		String password = MD5Util.encode(memberRegisterDto.getPassword());
		findMemberByuserTelephone.setPassword(password);
		findMemberByuserTelephone.setLastModifierId(memberId);
		findMemberByuserTelephone.setLastLoginTime(new Date());
		memberMgmtDao.updateById(findMemberByuserTelephone);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("state", 1);
		
		//重设会员密码的时候同步到
		this.modifySystemUser(findMemberByuserTelephone);
		
		return map;
	}

	@Override
	public Integer modifyMemberInfo(MemberMgmtDto memberMgmtDto) {
		TokenUtil.verifyToken(memberMgmtDto.getToken());
		TokenDto tokenDetail = TokenUtil.getTokenDetail(memberMgmtDto.getToken());
		Long memberId = tokenDetail.getMemberId();
		MemberEntity memberEntity = memberMgmtDao.selectById(memberMgmtDto.getId());
		BeanUtils.copyProperties(memberMgmtDto, memberEntity);
		memberEntity.setLastModifierId(memberId);
		memberEntity.setLastModifiedTime(new Date());
		return memberMgmtDao.updateById(memberEntity);
	}
	
	/**
	 * 
	 * Description 给系统用户表保存一条数据
	 * 
	 * Author Aaron.Xue   
	 */
//	private void saveSystemUser(MemberEntity memberEntity) {
//		SystemUserEntity user = new SystemUserEntity();
//		user.setUsername(memberEntity.getUserName());
//		user.setPassword(memberEntity.getPassword());
//		user.setMobilePhone(memberEntity.getTelephone());
//		//4 为会员，只可以登录入驻的
//		user.setType(4);
//		systemUserDao.insert(user);
//	}
	
	/**
	 * 
	 * Description 给系统用户表同步密码
	 * 
	 * Author Aaron.Xue   
	 */
	private void modifySystemUser(MemberEntity memberEntity) {
		SystemUserEntity systemUser = systemUserDao.selectUserByTelephone(memberEntity.getTelephone());
		if(null != systemUser) {
			systemUser.setPassword(memberEntity.getPassword());
			systemUserDao.updateById(systemUser);
		}
	}
	
	/**
	 * 
	 * Description 给系统用户表同步删除
	 * 
	 * Author Aaron.Xue   
	 */
	private void deleteSystemUser(MemberEntity memberEntity) {
		SystemUserEntity systemUser = systemUserDao.selectUserByTelephone(memberEntity.getTelephone());
		if(null != systemUser) {
			systemUser.setDeleteFlag((byte) 1);
			systemUserDao.updateById(systemUser);
		}
	}

	@Override
	public void relieveMemberMgmt(Long id) throws GlobalException {
		MemberEntity memberEntity = memberMgmtDao.selectById(id);
		/** 判断会员是否存在 */
		if (null == memberEntity) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_EXIST);
		}
		memberMgmtDao.relieveMemberMgmt(id);
	}

	@Override
	public List<MemberStatisticsDto> listMemberRecordByYear(MemberStatisticsDto memberStatisticsDto) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(memberStatisticsDto.getRegisterTimeStr()==null) {
			memberStatisticsDto.setRegisterTime(sdf.parse("2017-01-01"));
		}else {
			memberStatisticsDto.setRegisterTime(sdf.parse(memberStatisticsDto.getRegisterTimeStr()));
		}
		return memberMgmtDao.selectMemberRecordByYear(memberStatisticsDto);
	}

	@Override
	public List<MemberStatisticsDto> listMemberRecordByMonths(MemberStatisticsDto memberStatisticsDto) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		memberStatisticsDto.setRegisterTime(sdf.parse(memberStatisticsDto.getRegisterTimeStr()));
		return memberMgmtDao.selectMemberRecordByMonths(memberStatisticsDto);
	}
	
	@Override
	public Integer checkTelphoneIdentifyingCode(String telephone, String captcha, HttpServletRequest request) throws GlobalException {
		if(StringUtils.isEmpty(captcha)) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_CAPTCHA_NOT_NULL);
		}
		/** 判断验证码是否一致*/
		messageService.verifyCaptcha(telephone, captcha);
		Integer checkTelphoneIdentifyingCode = 1;
		return checkTelphoneIdentifyingCode;
	}


	@Override
	public Integer modifyMemberTelphone(String captcha, MemberRegisterDto memberRegisterDto,
			HttpServletRequest request) throws GlobalException {
		TokenUtil.verifyToken(memberRegisterDto.getToken());
		TokenDto tokenDetail = TokenUtil.getTokenDetail(memberRegisterDto.getToken());
		Long memberId = tokenDetail.getMemberId();
		if(StringUtils.isEmpty(captcha)) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_CAPTCHA_NOT_NULL);
		}
		/** 判断验证码是否一致*/
		messageService.verifyCaptcha(memberRegisterDto.getTelephone(), captcha);
		MemberEntity findMemberByuserTelephone = memberMgmtDao.selectMemberByuserTelephone(memberRegisterDto.getTelephone());
		/** 用户存在,提示更换手机号*/
		if(findMemberByuserTelephone != null) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_PHONENUMBER_EXISTS);
		}
		MemberEntity memberEntity = memberMgmtDao.selectById(memberRegisterDto.getId());
		BeanUtils.copyProperties(memberRegisterDto, memberEntity);
		memberEntity.setLastModifierId(memberId);
		memberEntity.setLastModifiedTime(new Date());
		memberMgmtDao.updateById(memberEntity);
		
		//更换完手机号 需要同步到用户
		SystemUserEntity selectUserByTelephone = systemUserDao.selectUserByTelephone(tokenDetail.getTelephone());
		selectUserByTelephone.setMobilePhone(memberEntity.getTelephone());
		return systemUserDao.updateById(selectUserByTelephone);
	}

	@Override
	public Integer modifyMemberPassword(String captcha, MemberRegisterDto memberRegisterDto,
			HttpServletRequest request) throws GlobalException {
		TokenUtil.verifyToken(memberRegisterDto.getToken());
		TokenDto tokenDetail = TokenUtil.getTokenDetail(memberRegisterDto.getToken());
		Long memberId = tokenDetail.getMemberId();
		if(StringUtils.isEmpty(captcha)) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_CAPTCHA_NOT_NULL);
		}
		/** 判断验证码是否一致*/
		messageService.verifyCaptcha(memberRegisterDto.getTelephone(), captcha);
		String password = memberRegisterDto.getPassword();
		if(StringUtils.isEmpty(password)) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_PASSWORD_NOT_NULL);
		}
		String encode = MD5Util.encode(password);
		memberRegisterDto.setPassword(encode);
		MemberEntity memberEntity = memberMgmtDao.selectById(memberRegisterDto.getId());
		BeanUtils.copyProperties(memberRegisterDto, memberEntity);
		memberEntity.setLastModifierId(memberId);
		memberEntity.setLastModifiedTime(new Date());
		//更改用户表密码
		this.modifySystemUser(memberEntity);
		
		return memberMgmtDao.updateById(memberEntity);
	}

	@Override
	public MemberEntity getMemberByuserTelephone(String telephone) {
		return memberMgmtDao.getMemberByuserTelephone(telephone);
	}

	@Override
	public Integer settingMemberPayPassword(MemberRegisterDto memberRegisterDto) {
		TokenUtil.verifyToken(memberRegisterDto.getToken());
		TokenDto tokenDetail = TokenUtil.getTokenDetail(memberRegisterDto.getToken());
		Long memberId = tokenDetail.getMemberId();
		String payPassword = memberRegisterDto.getPayPassword();
		if(StringUtils.isEmpty(payPassword)) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_PAYPASSWORD_NOT_NULL);
		}
		String encode = MD5Util.encode(payPassword);
		memberRegisterDto.setPayPassword(encode);
		MemberEntity memberEntity = memberMgmtDao.selectById(memberRegisterDto.getId());
		BeanUtils.copyProperties(memberRegisterDto, memberEntity);
		memberEntity.setLastModifierId(memberId);
		memberEntity.setLastModifiedTime(new Date());
		return memberMgmtDao.updateById(memberEntity);
	}

	@Override
	public Integer modifyMemberPayPassword(String captcha, MemberRegisterDto memberRegisterDto,
			HttpServletRequest request) {
		TokenUtil.verifyToken(memberRegisterDto.getToken());
		TokenDto tokenDetail = TokenUtil.getTokenDetail(memberRegisterDto.getToken());
		Long memberId = tokenDetail.getMemberId();
		if(StringUtils.isEmpty(captcha)) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_CAPTCHA_NOT_NULL);
		}
		/** 判断验证码是否一致*/
		messageService.verifyCaptcha(memberRegisterDto.getTelephone(), captcha);
		String payPassword = memberRegisterDto.getPayPassword();
		if(StringUtils.isEmpty(payPassword)) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_PAYPASSWORD_NOT_NULL);
		}
		String encode = MD5Util.encode(payPassword);
		memberRegisterDto.setPayPassword(encode);;
		MemberEntity memberEntity = memberMgmtDao.selectById(memberRegisterDto.getId());
		BeanUtils.copyProperties(memberRegisterDto, memberEntity);
		memberEntity.setLastModifierId(memberId);
		memberEntity.setLastModifiedTime(new Date());
		return memberMgmtDao.updateById(memberEntity);
	}

	@Override
	public List<MemberStatisticsDto> listMemberRecordByYearOfBusiness(MemberStatisticsDto memberStatisticsDto) throws Exception {
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		memberStatisticsDto.setStoreId(storeId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(memberStatisticsDto.getRegisterTimeStr()==null) {
			memberStatisticsDto.setRegisterTime(sdf.parse("2017-01-01"));
		}else {
			memberStatisticsDto.setRegisterTime(sdf.parse(memberStatisticsDto.getRegisterTimeStr()));
		}
		return memberMgmtDao.selectMemberRecordByYearOfBusiness(memberStatisticsDto);
	}

	@Override
	public List<MemberStatisticsDto> listMemberRecordByMonthsOfBusiness(MemberStatisticsDto memberStatisticsDto) throws Exception {
		Long storeId = SecurityContextUtils.getCurrentSystemUser().getStoreId();
		memberStatisticsDto.setStoreId(storeId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		memberStatisticsDto.setRegisterTime(sdf.parse(memberStatisticsDto.getRegisterTimeStr()));
		return memberMgmtDao.selectMemberRecordByMonthsOfBusiness(memberStatisticsDto);
	}

	@Override
	public Long findMemberMgmtByIdSum(Long memberId) {
		return memberMgmtDao.selectMemberMgmtByIdSum(memberId);
	}

	@Override
	public void bindWechat(String openId, Long memberId, String ip) {
		MemberEntity memberEntity = memberMgmtDao.selectById(memberId);
		if(memberEntity == null){
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_USER_NO_EXISTENCE);
		}
		MemberBindAccountEntity memberBindAccountEntity = new MemberBindAccountEntity();
		memberBindAccountEntity.setMemberId(memberId);
		memberBindAccountEntity.setAccountType(1);
		memberBindAccountEntity.setPlatformAccount(openId);
		memberBindAccountEntity.setTelephone(memberEntity.getTelephone());
		memberBindAccountEntity.setBindingTime(new Date());
		memberBindAccountEntity.setBindingIp(ip);
		memberBindAccountDao.insert(memberBindAccountEntity);

	}

	@Override
	public MemberBindAccountEntity findOneByOpenIdInWechat(String openId) {
		return memberBindAccountDao.findOneByOpenIdInWechat(openId);
	}

	@Override
	public MemberBindAccountEntity findOneByMemberInWechat(Long memberId) {
		return memberBindAccountDao.findOneByMemberInWechat(memberId);
	}

	@Override
	public void addUser(Long memberId, Integer type) {
		MemberEntity member = memberMgmtDao.selectById(memberId);
		//增加到用户表
		SystemUserEntity user = new SystemUserEntity();
		user.setUsername(member.getUserName());
		user.setPassword(member.getPassword());
		user.setType(type);
		user.setMobilePhone(member.getTelephone());
		systemUserDao.insert(user);
	}


}
