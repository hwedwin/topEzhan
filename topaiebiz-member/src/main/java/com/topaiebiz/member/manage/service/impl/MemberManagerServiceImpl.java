package com.topaiebiz.member.manage.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.member.manage.dao.MemberBindingRecordDao;
import com.topaiebiz.member.manage.dao.MemberManagerDao;
import com.topaiebiz.member.manage.dto.MemberInfoDto;
import com.topaiebiz.member.manage.dto.ModifyChiefDto;
import com.topaiebiz.member.manage.entity.MemberBindingRecordEntity;
import com.topaiebiz.member.manage.entity.MemberInfoEntity;
import com.topaiebiz.member.manage.exception.MemberManagerExceptionEnum;
import com.topaiebiz.member.manage.service.IMemberManagerService;
import com.topaiebiz.system.security.util.SecurityContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Luoqy on 2017/12/20.
 * desc:
 */
@Service
@Slf4j
public class MemberManagerServiceImpl implements IMemberManagerService {

    @Resource
    private MemberManagerDao memberManagerDao;

    @Resource
    private MemberBindingRecordDao memberBindingRecordDao;

    // 普通会员身份typeId
    private static final Long COMMONMEMBERTYPEID = 100L;

    // 店主身份会员身份typeId
    private static final Long SHOPKEEPERMEMBERTYPEID = 101L;


    @Override
    public Page<MemberInfoDto> getMembersInfoList(Page<MemberInfoDto> page, MemberInfoDto memberInfoDto) {
        // 设置会员类型为普通会员
        memberInfoDto.setTypeId(COMMONMEMBERTYPEID);
        List<MemberInfoDto> memberInfoDtoList = new ArrayList<>();
        if (StringUtils.isBlank(memberInfoDto.getParentTelephone())) {
            memberInfoDtoList = memberManagerDao.selectMembersInfoList(page, memberInfoDto);
        } else {
            // 根据上级手机号查询
            MemberInfoDto selectMembersInfoByPhone = memberManagerDao.selectMembersInfoByPhone(memberInfoDto);
            if (selectMembersInfoByPhone == null) {
                return page;
            }
            memberInfoDtoList = memberManagerDao.selectMembersInfoListById(page, selectMembersInfoByPhone);
        }
        if (CollectionUtils.isEmpty(memberInfoDtoList)) {
            return page;
        }
        for (MemberInfoDto dto : memberInfoDtoList) {

            if (dto.getParentId() == null || dto.getParentId() == 0) {
                continue;
            }
            // 获取上级的姓名和电话
            MemberInfoEntity memberInfoEntity = memberManagerDao.selectById(dto.getParentId());
            dto.setParentName(memberInfoEntity.getRealName());
            dto.setParentTelephone(memberInfoEntity.getTelephone());
        }
        page.setRecords(memberInfoDtoList);
        return page;
    }


    @Override
    public MemberInfoDto getMembersInfoById(String id) {
        MemberInfoEntity memberInfoEntity = memberManagerDao.selectById(id);
        if (memberInfoEntity == null) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_ID_NOT_EXIST);
        }
        MemberInfoDto memberInfoDto = new MemberInfoDto();
        BeanUtils.copyProperties(memberInfoEntity, memberInfoDto);
        MemberInfoEntity parentMemberInfo = memberManagerDao.selectById(memberInfoEntity.getParentId());
        if (parentMemberInfo == null) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_ID_NOT_EXIST);
        }
        memberInfoDto.setParentName(parentMemberInfo.getRealName());
        memberInfoDto.setParentTelephone(parentMemberInfo.getTelephone());
        return memberInfoDto;
    }


    @Override
    public List<MemberInfoDto> getMembersInfoByName(String realName) {
        return memberManagerDao.selectMembersInfoByRealName(realName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean modifyChief(ModifyChiefDto modifyChiefDto) {
        MemberInfoEntity memberInfo = memberManagerDao.selectById(modifyChiefDto.getMemberId());
        if (memberInfo == null) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_ID_NOT_EXIST);
        }
        MemberInfoEntity oldMemberInfo = memberManagerDao.selectById(modifyChiefDto.getOldParentId());
        if (oldMemberInfo == null) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_ID_NOT_EXIST);
        }
        MemberInfoEntity newMemberInfo = memberManagerDao.selectById(modifyChiefDto.getNewParentId());
        if (newMemberInfo == null) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_ID_NOT_EXIST);
        }
        Integer updateParentId = memberManagerDao.updateParentId(modifyChiefDto);
        // 绑定记录新增一条记录
        MemberBindingRecordEntity bindingRecordEntity = new MemberBindingRecordEntity();
        bindingRecordEntity.setCreatedTime(new Date());
        bindingRecordEntity.setMemberId(modifyChiefDto.getMemberId());
        bindingRecordEntity.setParentId(modifyChiefDto.getNewParentId());
        // 操作状态.1 绑定 2解绑
        bindingRecordEntity.setState((byte) 1);
        bindingRecordEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
        memberBindingRecordDao.insert(bindingRecordEntity);
        return updateParentId > 0;
    }

    @Override
    public boolean freezeMember(String id, String opt) {
        MemberInfoEntity memberInfoEntity = memberManagerDao.selectById(id);
        if (memberInfoEntity == null) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_ID_NOT_EXIST);
        }
        return memberManagerDao.updateMemberStatusById(Long.valueOf(id), Integer.valueOf(opt)) > 0;
    }
}
