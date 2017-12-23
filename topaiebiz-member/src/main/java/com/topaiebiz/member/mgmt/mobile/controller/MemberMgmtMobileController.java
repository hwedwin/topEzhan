package com.topaiebiz.member.mgmt.mobile.controller;

import com.alibaba.fastjson.JSONObject;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.nebulapaas.web.util.IllegalParamValidationUtils;
import com.topaiebiz.member.mgmt.dto.MemberDto;
import com.topaiebiz.member.mgmt.dto.MemberMgmtDto;
import com.topaiebiz.member.mgmt.dto.MemberRegisterDto;
import com.topaiebiz.member.mgmt.entity.MemberEntity;
import com.topaiebiz.member.mgmt.exception.MemberMgmtExceptionEnum;
import com.topaiebiz.member.mgmt.service.MemberMgmtService;
import com.topaiebiz.member.reserve.entity.MemberBindAccountEntity;
import com.topaiebiz.system.common.IpUtil;
import com.topaiebiz.system.moble.security.dto.TokenDto;
import com.topaiebiz.system.moble.security.service.TokenService;
import com.topaiebiz.system.moble.security.util.TokenUtil;
import com.topaiebiz.thirdparty.config.WeChatConfig;
import com.topaiebiz.thirdparty.util.WeChatMethodUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * Description：会员管理控制层
 * 
 * Author Scott.Yang
 * 
 * Date 2017年9月26日 下午4:53:39
 * 
 * Copyright Cognieon technology group co.LTD. All rights reserved.
 * 
 * Notice 本内容仅限于授权后使用，禁止非授权传阅以及私自用于其他商业目的。
 */
@Controller
@RequestMapping("/app/member/mgmt")
public class MemberMgmtMobileController {

	private final static Logger logger = LoggerFactory.getLogger(MemberMgmtMobileController.class);

	@Autowired
	private MemberMgmtService memberMgmtService;

	@Autowired
	private TokenService tokenService;

	/**
	 * Description： 快速登录会员信息
	 * 
	 * Author Scott.Yang
	 * 
	 * @param memberRegisterDto
	 *            会员信息Dto
	 * @param result
	 *            错误结果
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/quickLoginMemberMgmt", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo quickLoginMemberMgmt(HttpServletRequest request, @Valid String captcha,
			MemberRegisterDto memberRegisterDto, BindingResult result) throws GlobalException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。

		Map<String, Object> map = memberMgmtService.quickLoginMemberMgmt(captcha, memberRegisterDto, request);
		return new ResponseInfo(map);
	}

	/**
	 * Description： 添加注册会员信息
	 * 
	 * Author Scott.Yang
	 * 
	 * @param memberRegisterDto
	 *            会员信息Dto
	 * @param result
	 *            错误结果
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/registerMemberMgmt", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo registerMemberMgmt(@Valid MemberRegisterDto memberRegisterDto, BindingResult result,
			String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。

		Map<String, Object> map = memberMgmtService.registerMemberMgmt(memberRegisterDto);
		return new ResponseInfo(map);
	}

	/**
	 * 
	 * Description：App端登录
	 * 
	 * Author Scott.Yang
	 * 
	 * @param memberDto
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/loginMemberMgmt", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo getMemberMgmt(MemberDto memberDto) throws GlobalException {
		Map<String, Object> map = memberMgmtService.getMemberMgmt(memberDto);
		return new ResponseInfo(map);
	}

	/**
	 * Description：找回密码
	 * 
	 * Author Scott.Yang
	 * 
	 * @param memberRegisterDto
	 *            会员信息Dto
	 * @param result
	 *            错误结果
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/findMemberMgmtByTelphone", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo findMemberMgmtByTelphone(HttpServletRequest request, @Valid String captcha,
			MemberRegisterDto memberRegisterDto, BindingResult result) throws GlobalException {
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。

		Map<String, Object> map = memberMgmtService.findMemberMgmtByTelphone(captcha, memberRegisterDto, request);
		return new ResponseInfo(map);
	}

	/**
	 * Description： 重设会员密码
	 * 
	 * Author Scott.Yang
	 * 
	 * @param memberRegisterDto
	 *            会员信息Dto
	 * @param result
	 *            错误结果
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/resetMemberMgmtForPassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo resetMemberMgmtForPassword(@Valid MemberRegisterDto memberRegisterDto, BindingResult result,
			String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		memberRegisterDto.setToken(token);
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。

		Map<String, Object> map = memberMgmtService.resetMemberMgmtForPassword(memberRegisterDto);
		return new ResponseInfo(map);
	}

	/**
	 * Description： 根据id查询会员信息
	 * 
	 * Author Scott.Yang
	 * 
	 * @param id
	 *            会员编号ID
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/findMemberMgmtById")
	@ResponseBody
	public ResponseInfo findMemberMgmtById(String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		Long memberId = tokenDetail.getMemberId();
		MemberDto memberDto = new MemberDto();
		if (null == memberId) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		MemberEntity member = memberMgmtService.findMemberMgmtById(memberId);
		BeanUtils.copyProperties(member, memberDto);
		memberDto.setPassword("******");
		return new ResponseInfo(memberDto);
	}

	/**
	 * Description： 根据id查询会员可用积分
	 * 
	 * Author Scott.Yang
	 * 
	 * @param id
	 *            会员编号ID
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/findMemberMgmtByIdFroSum")
	@ResponseBody
	public ResponseInfo findMemberMgmtByIdFroSum(String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		Long memberId = tokenDetail.getMemberId();
		if (null == memberId) {
			throw new GlobalException(MemberMgmtExceptionEnum.MEMBERMGMT_ID_NOT_NULL);
		}
		Long pointSum = memberMgmtService.findMemberMgmtByIdSum(memberId);
		return new ResponseInfo(pointSum);
	}

	/**
	 * Description： 修改会员信息(C端)
	 * 
	 * Author Scott.Yang
	 * 
	 * @param memberDto
	 *            会员信息Dto
	 * @param result
	 *            错误结果
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/updateMemberInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editMemberInfo(String token, MemberMgmtDto memberMgmtDto, BindingResult result)
			throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		Long memberId = tokenDetail.getMemberId();
		memberMgmtDto.setId(memberId);
		memberMgmtDto.setToken(token);
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。
		Integer updateInteger = memberMgmtService.modifyMemberInfo(memberMgmtDto);
		return new ResponseInfo(updateInteger);
	}

	/**
	 * Description：验证手机验证码
	 * 
	 * Author Scott.Yang
	 * 
	 * @param memberRegisterDto
	 *            会员信息Dto
	 * @param result
	 *            错误结果
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/checkTelphoneIdentifyingCode", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo checkTelphoneIdentifyingCode(HttpServletRequest request, String captcha, String token)
			throws GlobalException {
		TokenUtil.verifyToken(token);
		String telephone = TokenUtil.getTokenDetail(token).getTelephone();
		Integer checkTelphoneIdentifyingCode = memberMgmtService.checkTelphoneIdentifyingCode(telephone, captcha,
				request);
		return new ResponseInfo(checkTelphoneIdentifyingCode);
	}

	/**
	 * Description：更改手机验证
	 * 
	 * Author Scott.Yang
	 * 
	 * @param memberRegisterDto
	 *            会员信息Dto
	 * @param result
	 *            错误结果
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/updateMemberTelphone", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editMemberTelphone(HttpServletRequest request, @Valid String captcha,
			MemberRegisterDto memberRegisterDto, BindingResult result, String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		Long memberId = tokenDetail.getMemberId();
		memberRegisterDto.setId(memberId);
		memberRegisterDto.setToken(token);
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。

		Integer updateMember = memberMgmtService.modifyMemberTelphone(captcha, memberRegisterDto, request);
		return new ResponseInfo(updateMember);
	}

	/**
	 * Description：更改登录密码
	 * 
	 * Author Scott.Yang
	 * 
	 * @param memberRegisterDto
	 *            会员信息Dto
	 * @param result
	 *            错误结果
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/updateMemberPassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editMemberPassword(HttpServletRequest request, @Valid String captcha,
			MemberRegisterDto memberRegisterDto, BindingResult result, String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		Long memberId = tokenDetail.getMemberId();
		memberRegisterDto.setId(memberId);
		memberRegisterDto.setToken(token);
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。

		Integer updateMember = memberMgmtService.modifyMemberPassword(captcha, memberRegisterDto, request);
		return new ResponseInfo(updateMember);
	}

	/**
	 * Description：设置支付密码
	 * 
	 * Author Scott.Yang
	 * 
	 * @param memberRegisterDto
	 *            会员信息Dto
	 * @param result
	 *            错误结果
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/settingMemberPayPassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo settingMemberPayPassword(MemberRegisterDto memberRegisterDto, String token)
			throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		Long memberId = tokenDetail.getMemberId();
		memberRegisterDto.setId(memberId);
		memberRegisterDto.setToken(token);
		Integer SettingMemberPayPassword = memberMgmtService.settingMemberPayPassword(memberRegisterDto);
		return new ResponseInfo(SettingMemberPayPassword);
	}

	/**
	 * Description：更改支付密码
	 * 
	 * Author Scott.Yang
	 * 
	 * @param memberRegisterDto
	 *            会员信息Dto
	 * @param result
	 *            错误结果
	 * @return
	 * @throws GlobalException
	 */
	@RequestMapping(path = "/updateMemberPayPassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo editMemberPayPassword(HttpServletRequest request, @Valid String captcha,
			MemberRegisterDto memberRegisterDto, BindingResult result, String token) throws GlobalException {
		TokenUtil.verifyToken(token);
		TokenDto tokenDetail = TokenUtil.getTokenDetail(token);
		Long memberId = tokenDetail.getMemberId();
		memberRegisterDto.setId(memberId);
		memberRegisterDto.setToken(token);
		if (result.hasErrors()) {
			// 初始化非法参数的提示信息。
			IllegalParamValidationUtils.initIllegalParamMsg(result);
			// 获取非法参数异常信息对象，并抛出异常。
			throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
		}
		// 成功时，直接分发数据并调用业务逻辑方法，并返回响应信息对象。

		Integer updateMember = memberMgmtService.modifyMemberPayPassword(captcha, memberRegisterDto, request);
		return new ResponseInfo(updateMember);
	}

	/**
	 * Description: 获取微信的授权页面
	 *
	 * Author: hxpeng createTime: 2017/11/12
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getWeChatAuthUrl", method = RequestMethod.GET)
	@ResponseBody
	public String getWeChatAuthUrl(HttpServletRequest request) {
		return WeChatConfig.getUserinfoAccessCode(WeChatConfig.AUTH_URL, true);
	}

	/**
	 * Description: 微信验证回调url/微信拦截器
	 *
	 * Author: hxpeng createTime: 2017/11/12
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public String auth(HttpServletRequest request) {
		logger.info("进入授权方法==================================");
		String openId;
		// 用户同意授权，获取code
		String code = request.getParameter("code");
		logger.info("===================获取code" + code);
		if (!StringUtils.isEmpty(code)) {
			// 获取code，调用接口获取openId;
			String resultStr = WeChatMethodUtil.getAccessTokenString(code);
			logger.info(
					"============================================================================================================");
			logger.info("resultStr:" + resultStr);
			JSONObject objjson = JSONObject.parseObject(resultStr);
			openId = objjson.getString("openid");
			logger.info("===================获取openId" + openId);
			if (!StringUtils.isEmpty(openId)) {
				MemberBindAccountEntity memberBindAccountEntity = memberMgmtService.findOneByOpenIdInWechat(openId);
				if (null == memberBindAccountEntity) {
					// 未绑定用户，跳完登陆页面
					logger.info("===================未绑定");
					return "redirect:http://m.qinziezhan.com/#/main/home?openId=" + openId;
				} else {
					logger.info("===================已绑定");
					// 找到绑定信息，直接登陆
					String token = tokenService.login(memberBindAccountEntity.getTelephone());
					return "redirect:http://m.qinziezhan.com/#/main/home?token=" + token;
				}
			}
			logger.info("===================错误跳转");
			// 获取微信用户资料失败
			return "redirect:http://m.qinziezhan.com/#/main/home";
		}
		logger.info("===================错误跳转");
		// 获取微信用户资料失败
		return "redirect:http://m.qinziezhan.com/#/main/home";
	}

	/**
	 * Description: 微信绑定
	 *
	 * Author: hxpeng createTime: 2017/11/12
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/bindWechat", method = RequestMethod.GET)
	@ResponseBody
	public ResponseInfo bindWechat(HttpServletRequest request) {
		String token = request.getParameter("token");
		TokenUtil.verifyToken(token);
		Long memberId = TokenUtil.getTokenDetail(token).getMemberId();
		String openId = request.getParameter("openId");
		if (StringUtils.isEmpty(memberId) || StringUtils.isEmpty(openId)) {
			/**
			 * TODO 抛一个错误
			 */
		}
		if (!StringUtils.isEmpty(memberId) && !StringUtils.isEmpty(openId)) {
			MemberBindAccountEntity memberBindAccountEntity = memberMgmtService.findOneByOpenIdInWechat(openId);
			if (null == memberBindAccountEntity) {
				MemberBindAccountEntity memberBindAccountEntity1 = memberMgmtService.findOneByMemberInWechat(memberId);
				if (null == memberBindAccountEntity1) {
					memberBindAccountEntity1 = new MemberBindAccountEntity();
					memberMgmtService.bindWechat(openId, memberId, IpUtil.getIpAddr(request));
					return new ResponseInfo(1);
				}
			}
			/**
			 * TODO 抛一个已经绑定的错误
			 */
		}
		/**
		 * TODO 抛一个参数不对的异常
		 */
		return null;
	}
}
