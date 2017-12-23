package com.topaiebiz.member.manage.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Luoqy on 2017/12/22.
 * desc:
 */
@Data
public class LabelDto implements Serializable {

    private MemberLabelTypeDto memberLabelType;

    private List<MemberLabelDto> memberLabelList;

}
