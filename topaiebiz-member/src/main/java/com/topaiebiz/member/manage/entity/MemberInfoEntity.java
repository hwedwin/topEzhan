package com.topaiebiz.member.manage.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nebulapaas.data.mybatis.common.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * Created by Luoqy on 2017/12/20.
 * desc:
 */
@TableName("t_mem_member")
@Data
public class MemberInfoEntity extends BaseEntity<Long> {

    /**
     * 序列化版本号。
     */
    @TableField(exist = false)
    private static final long serialVersionUID = 5752864700866242226L;

    /*** 会员类型。 */
    private Long typeId;

    /*** 会员等级。 */
    private Long gradeId;

    /*** 与上级的绑定状态（1 绑定，0 解绑）。 */
    private Integer bindingState;

    /*** 显示用户名。 */
    private String userName;

    /*** 密码。 */
    private String password;

    /*** 昵称。 */
    private String nickName;

    /*** 用户邮箱。 */
    private String post;

    /*** 会员真实姓名。 */
    private String realName;

    /*** 支付密码*/
    private String payPassword;

    /*** 会员的身份证。。 */
    private String idCard;

    /*** 性别（1 南 0 女)。 */
    private Integer gender;

    /*** 生日。 */
    private String birthday;

    /*** 所在地区编码。 */
    private Long districtId;

    /*** 会员的详细地址。 */
    private String address;

    /*** 会员手机号。 */
    private String telephone;


    /*** 注册时间。 */
    private Date registerTime;

    /*** 注册IP。 */
    private String registerIp;

    /*** 最后登录时间。 */
    private Date lastLoginTime;

    /*** 最后登录IP。 */
    private String lastLoginIp;

    /*** 登录次数。 */
    private Long loginCount;

    /*** 账户状态（1 锁定，0 正常）。 */
    private Integer accountState;

    /*** 婚姻状况。（1已婚 0未婚 2离异）。 */
    private Integer marriageState;

    /*** 教育程度。 */
    private String educationLevel;

    /*** 所属行业。 */
    private String industry;

    /*** 月收入。 */
    private Double monthlyIncome;

    /*** 小会员头像。 */
    private String smallIcon;

    /*** 大会员头像。 */
    private String bigIcon;

    /*** 上级会员。 */
    private Long parentId;

    /*** 现有积分。 */
    private Long ownScore;

    /*** 已消耗积分。 */
    private Long usedScore;

    /*** 备注 */
    private String memo;

    /**
     * creatorId
     * 创建人编号。取值为创建人的全局唯一主键标识符。
     */
    private Long creatorId;

    /**
     * createdTime
     * 创建时间。取值为系统的当前时间。
     */
    private Date createdTime;

    /**
     * lastModifierId
     * 最后修改人编号。取值为最后修改人的全局唯一主键标识符。
     */
    private Long lastModifierId;

    /**
     * lastModifiedTime
     * 最后修改时间。取值为系统的当前时间。
     */
    private Date lastModifiedTime;


}
