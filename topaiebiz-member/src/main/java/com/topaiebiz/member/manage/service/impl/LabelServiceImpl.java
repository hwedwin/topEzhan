package com.topaiebiz.member.manage.service.impl;

import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.member.manage.dao.MemberLabelDao;
import com.topaiebiz.member.manage.dao.MemberLabelTypeDao;
import com.topaiebiz.member.manage.dto.LabelDto;
import com.topaiebiz.member.manage.dto.MemberLabelDto;
import com.topaiebiz.member.manage.dto.MemberLabelTypeDto;
import com.topaiebiz.member.manage.entity.MemberLabelEntity;
import com.topaiebiz.member.manage.entity.MemberLabelTypeEntity;
import com.topaiebiz.member.manage.exception.MemberManagerExceptionEnum;
import com.topaiebiz.member.manage.service.ILabelService;
import com.topaiebiz.system.security.util.SecurityContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Luoqy on 2017/12/22.
 * desc:
 */
@Service
@Slf4j
public class LabelServiceImpl implements ILabelService {

    @Resource
    private MemberLabelDao memberLabelDao;

    @Resource
    private MemberLabelTypeDao memberLabelTypeDao;

    @Override
    public List<LabelDto> getLabelList() {
        List<MemberLabelTypeDto> labelTypeList = memberLabelTypeDao.getLabelTypeList();
        if (CollectionUtils.isEmpty(labelTypeList)) {
            return null;
        }
        List<LabelDto> labelDtoList = new ArrayList<>();
        for (MemberLabelTypeDto typeDto : labelTypeList) {
            LabelDto labelDto = new LabelDto();
            labelDto.setMemberLabelType(typeDto);
            labelDto.setMemberLabelList(memberLabelDao.selectLabelListByTypeId(typeDto.getId()));
            labelDtoList.add(labelDto);
        }
        return labelDtoList;
    }

    @Override
    public boolean adjustLabelTypeSortNo(String labelTypeId, String optType) {
        MemberLabelTypeEntity labelTypeEntity = memberLabelTypeDao.selectById(labelTypeId);
        if (labelTypeEntity == null) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_LABELTYPE_ID_NOT_EXIST);
        }
        // optType 操作类型  1: 升序, 2降序
        if ("1".equals(optType)) {
            labelTypeEntity.setSortNo(labelTypeEntity.getSortNo() + 1);
        }
        if ("2".equals(optType)) {
            labelTypeEntity.setSortNo(labelTypeEntity.getSortNo() - 1);
        }

        return memberLabelTypeDao.updateById(labelTypeEntity) > 0;
    }

    @Override
    public boolean adjustLabelSortNo(String labelId, String optType) {
        MemberLabelEntity labelEntity = memberLabelDao.selectById(labelId);
        if (labelEntity == null) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_LABEL_ID_NOT_EXIST);
        }
        // optType 操作类型  1: 升序, 2降序
        if ("1".equals(optType)) {
            labelEntity.setSortNo(labelEntity.getSortNo() + 1);
        }
        if ("2".equals(optType)) {
            labelEntity.setSortNo(labelEntity.getSortNo() - 1);
        }

        return memberLabelDao.updateById(labelEntity) > 0;
    }

    @Override
    public LabelDto getLabelInfoByTypeId(String labelTypeId) {
        MemberLabelTypeDto labelTypeDto = memberLabelTypeDao.selectLabelTypeById(labelTypeId);
        if (labelTypeDto == null) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_LABELTYPE_ID_NOT_EXIST);
        }
        LabelDto labelDto = new LabelDto();
        labelDto.setMemberLabelType(labelTypeDto);
        labelDto.setMemberLabelList(memberLabelDao.selectLabelListByTypeId(labelTypeDto.getId()));
        return labelDto;
    }


    @Override
    public boolean addLabelType(String labelTypeName) {
        MemberLabelTypeDto labelTypeDto = memberLabelTypeDao.selectLabelTypeByName(labelTypeName);
        if (labelTypeDto != null) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_LABELTYPE_NAME_HAVE_EXIST);
        }
        MemberLabelTypeEntity typeEntity = new MemberLabelTypeEntity();
        typeEntity.setCreatedTime(new Date());
        typeEntity.setTypeName(labelTypeName);
        typeEntity.setSortNo(memberLabelTypeDao.selectLabelTypeMaxSortNo() + 1);
        typeEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
        return memberLabelTypeDao.insert(typeEntity) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addLabel(LabelDto labelDto) {
        String typeName = labelDto.getMemberLabelType().getTypeName();
        MemberLabelTypeDto labelTypeDto = memberLabelTypeDao.selectLabelTypeByName(typeName);
        if (labelTypeDto != null) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_LABELTYPE_NAME_HAVE_EXIST);
        }
        MemberLabelTypeEntity typeEntity = new MemberLabelTypeEntity();
        typeEntity.setCreatedTime(new Date());
        typeEntity.setTypeName(typeName);
        typeEntity.setSortNo(memberLabelTypeDao.selectLabelTypeMaxSortNo() + 1);
        typeEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
        memberLabelTypeDao.insert(typeEntity);
        List<MemberLabelDto> labelList = labelDto.getMemberLabelList();
        Long maxSortNo = memberLabelDao.selectLabelMaxSortNoByTypeId(typeEntity.getId());
        Long sortNo = (maxSortNo == null || maxSortNo == 0) ? 0 : maxSortNo;
        if (!CollectionUtils.isEmpty(labelList)) {
             // 插入标签
            for (int i = 1; i <= labelList.size(); i++) {
                MemberLabelDto memberLabelDto =  labelList.get(i - 1);
                MemberLabelEntity t = new MemberLabelEntity();
                t.setTypeId(typeEntity.getId());
                t.setCreatedTime(new Date());
                t.setName(memberLabelDto.getName());
                t.setSortNo(sortNo + i);
                memberLabelDao.insert(t);
            }
        }
        return true;
    }

    @Override
    public boolean judgeLabelTypeName(String labelTypeName) {
        MemberLabelTypeDto labelTypeDto = memberLabelTypeDao.selectLabelTypeByName(labelTypeName);
        if (labelTypeDto != null) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_LABELTYPE_NAME_HAVE_EXIST);
        }
        return true;
    }

    @Override
    public boolean judgeLabelName(String labelName) {
        MemberLabelDto labelDto = memberLabelDao.selectLabelByName(labelName);
        if (labelDto != null) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_LABEL_NAME_HAVE_EXIST);
        }
        return true;
    }

    @Override
    public boolean delLabel(Long id) {
        return memberLabelDao.deleteById(id) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delLabelType(Long typeId) {
        memberLabelTypeDao.deleteById(typeId);
        memberLabelDao.deleteLabelByTypeId(typeId);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean modifyLabel(LabelDto labelDto) {
        MemberLabelTypeDto memberLabelType = labelDto.getMemberLabelType();
        MemberLabelTypeEntity typeEntity = new MemberLabelTypeEntity();
        BeanUtils.copyProperties(memberLabelType, typeEntity);
        memberLabelTypeDao.updateById(typeEntity);
        List<MemberLabelDto> labelList = labelDto.getMemberLabelList();
        if (!CollectionUtils.isEmpty(labelList)) {
            // 插入标签
            for (MemberLabelDto memberLabelDto : labelList) {
                MemberLabelEntity t = new MemberLabelEntity();
                BeanUtils.copyProperties(memberLabelDto, t);
                if (memberLabelDto.getId() == null || memberLabelDto.getId() == 0) {
                    t.setCreatedTime(new Date());
                    t.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
                    memberLabelDao.insert(t);
                }
                memberLabelDao.updateById(t);
            }
        }
        return true;
    }
}
