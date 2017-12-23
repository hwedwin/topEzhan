package com.topaiebiz.member.manage.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.nebulapaas.web.exception.GlobalException;
import com.topaiebiz.member.manage.dao.*;
import com.topaiebiz.member.manage.dto.MemberApplyDto;
import com.topaiebiz.member.manage.dto.MemberGradeInfoDto;
import com.topaiebiz.member.manage.dto.ShopKeeperInfoDto;
import com.topaiebiz.member.manage.entity.*;
import com.topaiebiz.member.manage.exception.MemberManagerExceptionEnum;
import com.topaiebiz.member.manage.service.IShopKeeperService;
import com.topaiebiz.member.manage.util.MemberUtil;
import com.topaiebiz.system.moble.security.util.MD5Util;
import com.topaiebiz.system.security.util.SecurityContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Luoqy on 2017/12/20.
 * desc:
 */
@Service
@Slf4j
public class ShopKeeperServiceImpl implements IShopKeeperService {


    @Resource
    private MemberManagerDao memberManagerDao;

    @Resource
    private MemberGradeManagerDao memberGradeManagerDao;

    @Resource
    private MemberApplyDao memberApplyDao;

    @Resource
    private MemberGradeRecordDao memberGradeRecordDao;

    @Resource
    private ManagerCheckRecordDao managerCheckRecordDao;

    // 店主身份中最低等级 ---> 店长gradeId
    private static final Long SHOPKEEPERGRADELEVELID = 100L;

    // 店主身份中主管等级 ---> 主管gradeId
    private static final Long DIRECTORGRADELEVELID = 101L;

    // 普通会员等级  ---> gradeId
    private static final Long COMMONMEMBERGRADEID = 99L;

    // 普通会员身份typeId
    private static final Long COMMONMEMBERTYPEID = 100L;

    // 店主身份会员身份typeId
    private static final Long SHOPKEEPERMEMBERTYPEID = 101L;


    @Override
    public Page<ShopKeeperInfoDto> getShopKeeperList(Page<ShopKeeperInfoDto> page, ShopKeeperInfoDto shopKeeperDto) {
        // 设置会员类型为店主会员类型
        shopKeeperDto.setTypeId(SHOPKEEPERMEMBERTYPEID);
        List<ShopKeeperInfoDto> keeperInfoDtoList = memberManagerDao.selectShopKeeperList(page, shopKeeperDto);
        if (CollectionUtils.isEmpty(keeperInfoDtoList)) {
            return page;
        }

        // 遍历获取上级的姓名和电话以及获取审核状态
        for (ShopKeeperInfoDto shopKeeperInfoDto : keeperInfoDtoList) {
            if (shopKeeperInfoDto.getParentId() == null || shopKeeperInfoDto.getParentId() == 0) {
                continue;
            }
            // 获取上级的姓名和电话
            MemberInfoEntity memberInfoEntity = memberManagerDao.selectById(shopKeeperInfoDto.getParentId());
            shopKeeperInfoDto.setParentName(memberInfoEntity.getRealName());
            shopKeeperInfoDto.setParentTelephone(memberInfoEntity.getTelephone());
            // 获取审核状态
            List<MemberApplyDto> applyDtoList = memberApplyDao.selectByMemberId(shopKeeperInfoDto.getId());
            if (CollectionUtils.isEmpty(applyDtoList)) {
                // 店长级别 设为已审核状态
                shopKeeperInfoDto.setStatus(1);
            } else {
                shopKeeperInfoDto.setStatus(Integer.valueOf(applyDtoList.get(0).getState()));
            }
            // 设置人数
            Long firstNum = memberManagerDao.selectFirstNum(shopKeeperInfoDto.getId());
            Long secondtNum = memberManagerDao.selectSecondtNum(shopKeeperInfoDto.getId());
            shopKeeperInfoDto.setFirstPerson(firstNum);
            shopKeeperInfoDto.setSecondPerson(secondtNum);

            // TODO 培训奖励


            // TODO: 2017/12/20  账户体系


        }

        page.setRecords(keeperInfoDtoList);
        return page;
    }

    @Override
    public List<MemberGradeInfoDto> getGradeList() {
        return memberGradeManagerDao.selectGradeList();
    }

    @Override
    public MemberGradeInfoDto getGradeInfoById(String id) {
        MemberGradeInfoDto memberGradeInfoDto = new MemberGradeInfoDto();
        MemberGradeInfoEntity memberGradeInfoEntity = memberGradeManagerDao.selectById(id);
        BeanUtils.copyProperties(memberGradeInfoEntity, memberGradeInfoDto);
        return memberGradeInfoDto;
    }

    @Override
    public Integer editGradeInfo(MemberGradeInfoDto memberGradeInfoDto) {
        MemberGradeInfoEntity entity = new MemberGradeInfoEntity();
        BeanUtils.copyProperties(memberGradeInfoDto, entity);
        entity.setLastModifiedTime(new Date());
        entity.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
        return memberGradeManagerDao.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean editPosition(Long memberId, Long gradeId) {
        MemberInfoEntity memberInfo = memberManagerDao.selectById(memberId);
        if (gradeId != memberInfo.getGradeId()) {
            MemberGradeRecordEntity gradeRecord = new MemberGradeRecordEntity();
            gradeRecord.setCreatedTime(new Date());
            gradeRecord.setMemberId(memberId);
            gradeRecord.setBeforeGrade(memberInfo.getGradeId());
            gradeRecord.setAfterGrade(gradeId);
            gradeRecord.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
            memberGradeRecordDao.insert(gradeRecord);
            memberInfo.setGradeId(gradeId);
            memberInfo.setLastModifiedTime(new Date());
            memberInfo.setLastModifierId(SecurityContextUtils.getCurrentSystemUser().getId());
            return memberManagerDao.updateById(memberInfo) > 0;
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addDirector(MemberApplyDto memberApplyDto) {
        MemberInfoEntity memberInfoEntity = memberManagerDao.selectMembersInfoByTelePhone(memberApplyDto.getTelephone());

        /**
         * 小白用户, 先注册在申请, 审核通过后绑定邀请人为上级, 变更职位
         *      1. 自己注册申请, 无上级绑定
         *      2. 邀请链接申请, 绑定邀请人上级
         */

        MemberApplyEntity applyEntity = new MemberApplyEntity();
        BeanUtils.copyProperties(memberApplyDto, applyEntity);
        if (memberInfoEntity == null) {
            memberInfoEntity = new MemberInfoEntity();
            memberInfoEntity.setTelephone(memberApplyDto.getTelephone());
            memberInfoEntity.setTypeId(COMMONMEMBERTYPEID);
            memberInfoEntity.setGradeId(COMMONMEMBERGRADEID);
            memberInfoEntity.setUserName(MemberUtil.getUsername(8));
            memberInfoEntity.setPassword(MD5Util.encode("mamaezhan"));
            memberInfoEntity.setRegisterTime(new Date());
            memberInfoEntity.setAccountState(0);
            memberInfoEntity.setCreatedTime(new Date());
            memberInfoEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
            memberManagerDao.insert(memberInfoEntity);
            if (!StringUtils.isEmpty(memberApplyDto.getParentMemberId())) {
                // 查询上级会员id用户是否存在
                MemberInfoEntity parentMemberInfo = memberManagerDao.selectById(memberApplyDto.getParentMemberId());
                if (parentMemberInfo == null) {
                    log.error("上级会员id对应的会员信息不存在!...memberApplyDto = {}", JSON.toJSONString(memberApplyDto));
                    throw new GlobalException(MemberManagerExceptionEnum.MEMBER_PARENT_MEMBERID_NOT_EXIST);
                }
                // 绑定上级会员id
                applyEntity.setParentMemberId(memberApplyDto.getParentMemberId());
            }

            // memberId
            applyEntity.setMemberId(memberInfoEntity.getId());
            // 申请会员类型( 0: 小白用户, 1: 普通会员 2: 店长 )
            applyEntity.setType("0");
            applyEntity.setCreatedTime(new Date());
            // 状态(0: 未审核, 1: 审核通过, 2: 未通过)
            applyEntity.setState((byte) 0);
            applyEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
            return memberApplyDao.insert(applyEntity) > 0;
        }

        /**
         *
         * 普通会员身份申请流程
         *  1.  存在上级, 上级不变, 职位变更
         *  2.  无上级
         *     2.1. 自己注册申请, 无上级绑定
         *     2.2. 邀请链接申请, 绑定邀请人上级
         *
         */
        if (COMMONMEMBERTYPEID == memberInfoEntity.getTypeId() && COMMONMEMBERGRADEID == memberInfoEntity.getGradeId()) {
            if (memberInfoEntity.getParentId() != null && memberInfoEntity.getParentId() > 0) {
                // 1.  存在上级, 上级不变, 职位变更
                applyEntity.setParentMemberId(memberInfoEntity.getParentId());
            } else {
                // 2.  无上级  2.2. 邀请链接申请, 绑定邀请人上级
                if (!StringUtils.isEmpty(memberApplyDto.getParentMemberId())) {
                    applyEntity.setParentMemberId(memberApplyDto.getParentMemberId());
                }
            }
            applyEntity.setMemberId(memberInfoEntity.getId());
            // 申请会员类型( 0: 小白用户, 1: 普通会员 2: 店长 )
            applyEntity.setType("1");
            applyEntity.setCreatedTime(new Date());
            // 状态(0: 未审核, 1: 审核通过, 2: 未通过)
            applyEntity.setState((byte) 0);
            applyEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
            return memberApplyDao.insert(applyEntity) > 0;

        }

        /**
         * 店主身份
         *  1, 无上级, 不绑定上级, 只变更职位
         *  2, 有上级, 上级不变, 变更职位
         */
        if (SHOPKEEPERMEMBERTYPEID == memberInfoEntity.getTypeId() && SHOPKEEPERGRADELEVELID == memberInfoEntity.getGradeId()) {
            if (!StringUtils.isEmpty(memberInfoEntity.getParentId())) {
                applyEntity.setParentMemberId(memberInfoEntity.getParentId());
            }
            applyEntity.setMemberId(memberInfoEntity.getId());
            // 申请会员类型( 0: 小白用户, 1: 普通会员 2: 店长 )
            applyEntity.setType("2");
            applyEntity.setCreatedTime(new Date());
            // 状态(0: 未审核, 1: 审核通过, 2: 未通过)
            applyEntity.setState((byte) 0);
            applyEntity.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
            return memberApplyDao.insert(applyEntity) > 0;
        }
        // 不允许
        log.error("你已是主管以上级别, 不可再次申请加盟!...memberApplyDto = {}", JSON.toJSONString(memberApplyDto));
        throw new GlobalException(MemberManagerExceptionEnum.MEMBER_ADDDIRECTOR_IDENTITY_STATUS_ERROR);
    }

    @Override
    public boolean addShopKeeper(String telePhone) {
        MemberInfoEntity memberInfoEntity = memberManagerDao.selectMembersInfoByTelePhone(telePhone);
        if (memberInfoEntity == null) {
            log.error("非平台用户不能进行操作! 申请用户手机号telephone = {}", telePhone);
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_ID_NOT_EXIST);
        }
        if (memberInfoEntity.getTypeId() != COMMONMEMBERTYPEID) {
            log.error("你已是店长以上级别, 不可再次申请! telePhone = {}", telePhone);
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_ADDSHOPKEEPER_STATUS_ERROR);
        }
        // TODO: 2017/12/22  判断资格


        // TODO: 2017/12/22  优惠券发放


        return false;
    }

    @Override
    public MemberApplyDto getShopKeeperApplyByMemberId(String memberId) {
        List<MemberApplyDto> applyDtoList = memberApplyDao.selectByMemberId(Long.valueOf(memberId));
        if (CollectionUtils.isEmpty(applyDtoList)) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_APPLY_ID_NOT_EXIST);
        }
        return applyDtoList.get(0);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean auditDirector(String applyId, String status, String memo) {
        MemberApplyEntity applyEntity = memberApplyDao.selectById(applyId);
        if (applyEntity == null) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_APPLY_ID_NOT_EXIST);
        }
        if (status.equals(applyEntity.getState())) {
            return true;
        }
        // 状态(0: 未审核, 1: 审核通过, 2: 未通过)
        applyEntity.setState(Byte.valueOf(status));
        applyEntity.setCheckTime(new Date());
        applyEntity.setCheckId(SecurityContextUtils.getCurrentSystemUser().getId());
        applyEntity.setMemo(memo);
        // 审核通过修改会员信息状态
        if ("1".equals(status)) {
            MemberInfoEntity memberInfoEntity = memberManagerDao.selectById(applyEntity.getMemberId());
            if (memberInfoEntity == null) {
                throw new GlobalException(MemberManagerExceptionEnum.MEMBER_ID_NOT_EXIST);
            }
            // 设置上级
            memberInfoEntity.setParentId(applyEntity.getParentMemberId());
            memberInfoEntity.setTypeId(SHOPKEEPERMEMBERTYPEID); // 店主类型
            memberInfoEntity.setGradeId(DIRECTORGRADELEVELID);  // 主管等级
            memberInfoEntity.setIdCard(applyEntity.getIdNum());
            memberInfoEntity.setRealName(applyEntity.getRealName());
            memberManagerDao.updateById(memberInfoEntity);

            // TODO: 2017/12/22  发放优惠券


        }

        ManagerCheckRecordEntity checkRecord = new ManagerCheckRecordEntity();
        checkRecord.setApplyId(Long.valueOf(applyId));
        checkRecord.setCheckTime(new Date());
        checkRecord.setCreatedTime(new Date());
        checkRecord.setCreatorId(SecurityContextUtils.getCurrentSystemUser().getId());
        checkRecord.setCheckId(SecurityContextUtils.getCurrentSystemUser().getId());
        checkRecord.setResultState(Byte.valueOf(status));
        checkRecord.setMemo(memo);
        managerCheckRecordDao.insert(checkRecord);
        return memberApplyDao.updateById(applyEntity) > 0;
    }
}
