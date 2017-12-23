package com.topaiebiz.member.manage.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.topaiebiz.member.manage.dto.MemberInfoDto;
import com.topaiebiz.member.manage.dto.ModifyChiefDto;
import com.topaiebiz.member.manage.dto.ShopKeeperInfoDto;
import com.topaiebiz.member.manage.entity.MemberInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Luoqy on 2017/12/20.
 * desc:
 */
@Mapper
public interface MemberManagerDao extends BaseMapper<MemberInfoEntity> {

    /**
     * 查询会员管理中会员列表
     *
     * @param page
     * @param memberInfoDto
     * @return
     */
    List<MemberInfoDto> selectMembersInfoList(Page<MemberInfoDto> page, MemberInfoDto memberInfoDto);

    /**
     * 根据手机号码获取会员信息 (上级手机号码)
     *
     * @param memberInfoDto
     * @return
     */
    MemberInfoDto selectMembersInfoByPhone(MemberInfoDto memberInfoDto);

    /**
     * 查询出指定id下面的下级会员列表
     *
     * @param page
     * @param memberInfoDto
     * @return
     */
    List<MemberInfoDto> selectMembersInfoListById(Page<MemberInfoDto> page, MemberInfoDto memberInfoDto);


    /**
     * 获取店主会员信息列表
     *
     * @param page
     * @param shopKeeperDto
     * @return
     */
    List<ShopKeeperInfoDto> selectShopKeeperList(Page<ShopKeeperInfoDto> page, ShopKeeperInfoDto shopKeeperDto);

    /**
     * 查询指定会员id下面的直属人数
     *
     * @param id
     * @return
     */
    Long selectFirstNum(@Param("id") Long id);

    /**
     * 查询指定会员id下面的二级人数
     *
     * @param id
     * @return
     */
    Long selectSecondtNum(@Param("id") Long id);

    /**
     * 根据用户真实姓名查询用户信息列表
     *
     * @param realName
     * @return
     */
    List<MemberInfoDto> selectMembersInfoByRealName(@Param("realName") String realName);

    /**
     * 更新上级
     *
     * @param modifyChiefDto
     * @return
     */
    Integer updateParentId(ModifyChiefDto modifyChiefDto);

    /**
     * 更新用户的状态
     * @param id
     * @param status 0 正常 1 冻结
     * @return
     */
    Integer updateMemberStatusById(@Param("id") Long id, @Param("status") Integer status);

    /**
     * 根据手机号查询用户信息
     * @param telephone
     * @return
     */
    MemberInfoEntity selectMembersInfoByTelePhone(@Param("telephone") String telephone);
}
