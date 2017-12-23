package com.topaiebiz.member.manage.manage.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.nebulapaas.web.util.IllegalParamValidationUtils;
import com.topaiebiz.member.manage.dto.MemberApplyDto;
import com.topaiebiz.member.manage.dto.MemberGradeInfoDto;
import com.topaiebiz.member.manage.dto.ShopKeeperInfoDto;
import com.topaiebiz.member.manage.exception.MemberManagerExceptionEnum;
import com.topaiebiz.member.manage.service.IShopKeeperService;
import lombok.extern.slf4j.Slf4j;
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
 * desc:
 */
@RestController
@Slf4j
@RequestMapping("/manage/shopkeeper")
public class ShopKeeperManageController {

    @Resource
    private IShopKeeperService shopKeeperService;


    /**
     * 店主管理--->获取到店主信息列表
     *
     * @param page
     * @param shopKeeperDto
     * @return
     */
    @PostMapping("/getShopKeeperList")
    public ResponseInfo getShopKeeperList(Page<ShopKeeperInfoDto> page, ShopKeeperInfoDto shopKeeperDto) {
        Page<ShopKeeperInfoDto> shopKeeperList = shopKeeperService.getShopKeeperList(page, shopKeeperDto);
        return new ResponseInfo(shopKeeperList);
    }


    /**
     * 店主升级--->获取会员等级列表
     *
     * @return
     */
    @PostMapping("/getGradeList")
    public ResponseInfo getGradeList() {
        List<MemberGradeInfoDto> gradeList = shopKeeperService.getGradeList();
        return new ResponseInfo(gradeList);
    }

    /**
     * 店主升级--->根据会员等级编码获取会员信息
     *
     * @param gradeId 会员等级id
     * @return
     */
    @PostMapping("/getGradeInfoById")
    public ResponseInfo getGradeInfoById(String gradeId) {
        if (StringUtils.isBlank(gradeId)) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBERGRADE_GRADEID_NOT_EXIST);
        }
        return new ResponseInfo(shopKeeperService.getGradeInfoById(gradeId));
    }


    /**
     * 店主升级--->编辑会员等级
     *
     * @param memberGradeInfoDto
     * @return
     */
    @PostMapping("/editGradeInfo")
    public ResponseInfo editGradeInfo(@Valid MemberGradeInfoDto memberGradeInfoDto, BindingResult result) {
        if (result.hasErrors()) {
            /** 初始化非法参数的提示信息。 */
            IllegalParamValidationUtils.initIllegalParamMsg(result);
            /** 获取非法参数异常信息对象，并抛出异常。 */
            throw new GlobalException(IllegalParamValidationUtils.getIllegalParamExceptionInfo());
        }
        Integer i = shopKeeperService.editGradeInfo(memberGradeInfoDto);
        return new ResponseInfo(i);
    }

    /**
     * 店主管理--->职位设置
     *
     * @param memberId 会员id
     * @param gradeId  等级id
     * @return
     */
    @PostMapping("/editPosition")
    public ResponseInfo editPosition(String memberId, String gradeId) {
        if (StringUtils.isBlank(memberId)) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_ID_NOT_EXIST);
        }
        if (StringUtils.isBlank(gradeId)) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBERGRADE_GRADEID_NOT_EXIST);
        }
        boolean isSuccess = shopKeeperService.editPosition(Long.valueOf(memberId), Long.valueOf(gradeId));
        return new ResponseInfo(isSuccess);
    }

    /**
     * 根据会员id获取到店主申请记录
     *
     * @param memberId 会员id
     * @return
     */
    @PostMapping("/getShopKeeperApplyByMemberId")
    public ResponseInfo getShopKeeperApplyByMemberId(String memberId) {
        if (StringUtils.isBlank(memberId)) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_ID_NOT_EXIST);
        }
        MemberApplyDto memberApplyDto = shopKeeperService.getShopKeeperApplyByMemberId(memberId);
        return new ResponseInfo(memberApplyDto);
    }


    /**
     * 主管申请审核
     *
     * @param applyId 店主申请列表id
     * @param status  状态(1: 审核通过, 2: 未通过)
     * @param memo    备注信息
     * @return
     */
    @PostMapping("/auditDirector")
    public ResponseInfo auditDirector(String applyId, String status, String memo) {
        if (StringUtils.isBlank(applyId)) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_APPLY_ID_NOT_EXIST);
        }
        if (StringUtils.isBlank(status) && !"1".equals(status) && !"2".equals(status)) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_APPLY_STATUS_ERROR);
        }
        boolean isSuccess = shopKeeperService.auditDirector(applyId, status, memo);
        return new ResponseInfo(isSuccess);
    }


}
