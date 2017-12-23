package com.topaiebiz.member.manage.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.topaiebiz.member.manage.dto.MemberApplyDto;
import com.topaiebiz.member.manage.dto.MemberGradeInfoDto;
import com.topaiebiz.member.manage.dto.ShopKeeperInfoDto;

import java.util.List;

/**
 * Created by Luoqy on 2017/12/20.
 * desc:
 */
public interface IShopKeeperService {
    /**
     * 获取店主信息列表
     * @param page
     * @param shopKeeperDto
     * @return
     */
    Page<ShopKeeperInfoDto> getShopKeeperList(Page<ShopKeeperInfoDto> page, ShopKeeperInfoDto shopKeeperDto);

    /**
     * 获取店主等级列表
     * @return
     */
    List<MemberGradeInfoDto> getGradeList();

    /**
     * 根据会员等级编码获取会员等级信息
     * @param id
     * @return
     */
    MemberGradeInfoDto getGradeInfoById(String id);

    /**
     * 更新会员等级信息
     * @param memberGradeInfoDto
     * @return
     */
    Integer editGradeInfo(MemberGradeInfoDto memberGradeInfoDto);

    /**
     * 职位设置
     * @param memberId
     * @param gradeId
     * @return
     */
    boolean editPosition(Long memberId, Long gradeId);

    /**
     * 加盟店主
     * @param memberApplyDto
     * @return
     */
    boolean addDirector(MemberApplyDto memberApplyDto);

    /**
     * 加盟店长
     * @param telePhone
     * @return
     */
    boolean addShopKeeper(String telePhone);

    /**
     * 根据会员id获取到店主申请记录
     * @param memberId
     * @return
     */
    MemberApplyDto getShopKeeperApplyByMemberId(String memberId);

    /**
     * 审核店主申请列表
     * @param applyId
     * @param status
     * @param memo
     * @return
     */
    boolean auditDirector(String applyId, String status, String memo);


}
