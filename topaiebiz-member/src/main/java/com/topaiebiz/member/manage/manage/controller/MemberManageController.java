package com.topaiebiz.member.manage.manage.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.nebulapaas.web.util.IllegalParamValidationUtils;
import com.topaiebiz.member.manage.dto.MemberInfoDto;
import com.topaiebiz.member.manage.dto.ModifyChiefDto;
import com.topaiebiz.member.manage.exception.MemberManagerExceptionEnum;
import com.topaiebiz.member.manage.service.IMemberManagerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Luoqy on 2017/12/20.
 * desc: 会员管理
 */
@RestController
@RequestMapping("/manager/member")
public class MemberManageController {

    @Resource
    private IMemberManagerService memberManagerService;


    /**
     * 会员管理--->获取到普通会员信息列表
     *
     * @param page
     * @param memberInfoDto
     * @return
     * @throws GlobalException
     */
    @PostMapping("/getMembersList")
    public ResponseInfo getMembersInfoList(Page<MemberInfoDto> page, MemberInfoDto memberInfoDto) {
        Page<MemberInfoDto> membersInfoList = memberManagerService.getMembersInfoList(page, memberInfoDto);
        return new ResponseInfo(membersInfoList);
    }


    /**
     * 根据用户id获取到对应的信息, 包含上级信息
     *
     * @param memberId
     * @return
     * @throws GlobalException
     */
    @PostMapping("/getMembersInfoById")
    public ResponseInfo getMembersInfoById(String memberId) {
        if (StringUtils.isBlank(memberId)) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_ID_NOT_EXIST);
        }
        MemberInfoDto membersInfo = memberManagerService.getMembersInfoById(memberId);
        return new ResponseInfo(membersInfo);
    }

    /**
     * 根据用户真实姓名搜索用户信息
     *
     * @param name 用户真实姓名
     * @return
     * @throws GlobalException
     */
    @PostMapping("/getMembersInfoByName")
    public ResponseInfo getMembersInfoByName(String name) {
        List<MemberInfoDto> membersInfoList = memberManagerService.getMembersInfoByName(name);
        return new ResponseInfo(membersInfoList);
    }

    /**
     * 会员管理--->冻结/解冻操作
     *
     * @param memberId memberId
     * @param opt 状态（1 锁定，0 正常）
     * @return
     */
    @PostMapping("/freezeMember")
    public ResponseInfo freezeMember(String memberId, String opt) {
        if (StringUtils.isBlank(memberId)) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_ID_NOT_EXIST);
        }
        boolean isSuccess = memberManagerService.freezeMember(memberId, opt);
        return new ResponseInfo(isSuccess);
    }

    /**
     * 会员管理--->修改上级操作
     *
     * @param modifyChiefDto
     * @return
     */
    @PostMapping("/modifyChief")
    public ResponseInfo modifyChief(@Valid ModifyChiefDto modifyChiefDto, BindingResult result) {
        if (result.hasErrors()) {
            /** 初始化非法参数的提示信息。 */
            IllegalParamValidationUtils.initIllegalParamMsg(result);
            /** 获取非法参数异常信息对象，并抛出异常。 */
            throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
        }
        boolean isSuccess = memberManagerService.modifyChief(modifyChiefDto);
        return new ResponseInfo(isSuccess);
    }


}
