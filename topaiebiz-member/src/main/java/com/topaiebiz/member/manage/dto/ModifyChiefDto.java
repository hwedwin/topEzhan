package com.topaiebiz.member.manage.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Luoqy on 2017/12/21.
 * desc:
 */
@Data
public class ModifyChiefDto implements Serializable {
    private static final long serialVersionUID = 8568884240321096141L;

    // 用户id
    @NotNull(message = "{validation.membermanage.member.id}")
    private Long memberId;

    // 修改前的parentId
    @NotNull(message = "{validation.membermanage.member.id}")
    private Long oldParentId;

    // 修改后的parentId
    @NotNull(message = "{validation.membermanage.member.id}")
    private Long newParentId;

}
