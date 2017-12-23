package com.topaiebiz.member.manage.manage.controller;

import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.member.manage.dto.LabelDto;
import com.topaiebiz.member.manage.exception.MemberManagerExceptionEnum;
import com.topaiebiz.member.manage.service.ILabelService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Luoqy on 2017/12/22.
 * desc:
 */
@RestController
@RequestMapping("/manage/label")
public class LabelManageController {

    @Resource
    private ILabelService labelService;


    /**
     * 标签管理---> 获取所有的标签信息
     *
     * @return
     */
    @PostMapping("/getLabelList")
    public ResponseInfo getLabelList() {
        List<LabelDto> labelList = labelService.getLabelList();
        return new ResponseInfo(labelList);
    }

    /**
     * 标签管理---> 调整标签类型排序
     *
     * @param labelTypeId 标签类型id
     * @param optType     操作类型  1: 升序, 2降序
     * @return
     */
    @PostMapping("/adjustLabelTypeSort")
    public ResponseInfo adjustLabelTypeSortNo(String labelTypeId, String optType) {
        if (StringUtils.isBlank(labelTypeId)) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_LABELTYPE_ID_NOT_EXIST);
        }
        if (StringUtils.isBlank(optType) && !"1".equals(optType) && !"2".equals(optType)) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_LABEL_OPTTYPE_STATUS_ERROR);
        }

        boolean isSuccess = labelService.adjustLabelTypeSortNo(labelTypeId, optType);
        return new ResponseInfo(isSuccess);
    }

    /**
     * 标签管理---> 调整标签排序
     *
     * @param labelId 标签id
     * @param optType 操作类型  1: 升序, 2降序
     * @return
     */
    @PostMapping("/adjustLabelSort")
    public ResponseInfo adjustLabelSortNo(String labelId, String optType) {
        if (StringUtils.isBlank(labelId)) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_LABEL_ID_NOT_EXIST);
        }
        if (StringUtils.isBlank(optType) && !"1".equals(optType) && !"2".equals(optType)) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_LABEL_OPTTYPE_STATUS_ERROR);
        }

        boolean isSuccess = labelService.adjustLabelSortNo(labelId, optType);
        return new ResponseInfo(isSuccess);
    }

    /**
     * 标签管理---> 根据标签类型id获取到下面的所有标签
     *
     * @param labelTypeId 标签类型id
     * @return
     */
    @PostMapping("/getLabelByTypeId")
    public ResponseInfo getLabelInfoByTypeId(String labelTypeId) {
        if (StringUtils.isBlank(labelTypeId)) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_LABELTYPE_ID_NOT_EXIST);
        }
        LabelDto labelDto = labelService.getLabelInfoByTypeId(labelTypeId);
        return new ResponseInfo(labelDto);
    }

    /**
     * 标签管理---> 新增标签类型
     * @param labelTypeName 标签类型名称
     * @return
     */
    @PostMapping("/addLabelType")
    public ResponseInfo addLabelType(String labelTypeName) {
        if (StringUtils.isBlank(labelTypeName)) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_LABELTYPE_NAME_NOT_EXIST);
        }
        boolean isSuc = labelService.addLabelType(labelTypeName);
        return new ResponseInfo(isSuc);
    }

    /**
     * 标签管理---> 判断标签类型名称是否重复
     * @param labelTypeName 标签类型名称
     * @return
     */
    @PostMapping("/judgeLabelTypeName")
    public ResponseInfo judgeLabelTypeName(String labelTypeName) {
        if (StringUtils.isBlank(labelTypeName)) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_LABELTYPE_NAME_NOT_EXIST);
        }
        boolean isSuc = labelService.judgeLabelTypeName(labelTypeName);
        return new ResponseInfo(isSuc);
    }

    /**
     * 标签管理---> 判断标签名称是否重复
     * @param labelName 标签名称
     * @return
     */
    @PostMapping("/judgeLabelName")
    public ResponseInfo judgeLabelName(String labelName) {
        if (StringUtils.isBlank(labelName)) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_LABEL_NAME_NOT_EXIST);
        }
        boolean isSuc = labelService.judgeLabelName(labelName);
        return new ResponseInfo(isSuc);
    }


    /**
     * 标签管理---> 新增标签
     * @return
     */
    @PostMapping("/addLabel")
    public ResponseInfo addLabel(@RequestBody LabelDto labelDto) {
        if (StringUtils.isBlank(labelDto.getMemberLabelType().getTypeName())) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_LABELTYPE_NAME_NOT_EXIST);
        }
        boolean isSuc = labelService.addLabel(labelDto);
        return new ResponseInfo(isSuc);
    }

    /**
     * 标签管理---> 编辑保存标签
     * @return
     */
    @PostMapping("/modifyLabel")
    public ResponseInfo modifyLabel(@RequestBody LabelDto labelDto) {
        boolean isSuc = labelService.modifyLabel(labelDto);
        return new ResponseInfo(isSuc);
    }

    /**
     * 标签管理---> 删除标签
     * @return
     */
    @PostMapping("/delLabel")
    public ResponseInfo delLabel(String labelId) {
        if (StringUtils.isBlank(labelId)) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_LABEL_ID_NOT_EXIST);
        }
        boolean isSuc = labelService.delLabel(Long.valueOf(labelId));
        return new ResponseInfo(isSuc);
    }

    /**
     * 标签管理---> 删除标签类型
     * @return
     */
    @PostMapping("/delLabelType")
    public ResponseInfo delLabelType(String labelTypeId) {
        if (StringUtils.isBlank(labelTypeId)) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_LABELTYPE_ID_NOT_EXIST);
        }
        boolean isSuc = labelService.delLabelType(Long.valueOf(labelTypeId));
        return new ResponseInfo(isSuc);
    }


}
