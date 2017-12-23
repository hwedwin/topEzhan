package com.topaiebiz.member.manage.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.topaiebiz.member.manage.dto.MemberInfoDto;
import com.topaiebiz.member.manage.dto.ModifyChiefDto;

import java.util.List;

/**
 * Created by Luoqy on 2017/12/20.
 * desc:
 */
public interface IMemberManagerService {

    /**
     * 会员管理获取普通会员列表信息
     *
     * @param page
     * @param memberInfoDto
     * @return
     */
    Page<MemberInfoDto> getMembersInfoList(Page<MemberInfoDto> page, MemberInfoDto memberInfoDto);

    /**
     * 根据会员id获取会员信息, 包含上级信息
     *
     * @param id
     * @return
     */
    MemberInfoDto getMembersInfoById(String id);

    /**
     * 根据用户真实姓名搜索用户信息
     *
     * @param name
     * @return
     */
    List<MemberInfoDto> getMembersInfoByName(String name);

    /**
     * 会员管理--->修改上级操作
     *
     * @param modifyChiefDto
     * @return
     */
    boolean modifyChief(ModifyChiefDto modifyChiefDto);

    /**
     * 冻结用户
     * @param id
     * @param opt
     * @return
     */
    boolean freezeMember(String id, String opt);
}
