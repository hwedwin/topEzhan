package com.topaiebiz.member.manage.business.controller;

import com.nebulapaas.web.exception.GlobalException;
import com.nebulapaas.web.response.ResponseInfo;
import com.topaiebiz.member.manage.dto.MemberApplyDto;
import com.topaiebiz.member.manage.exception.MemberManagerExceptionEnum;
import com.topaiebiz.member.manage.service.IShopKeeperService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by Luoqy on 2017/12/20.
 * desc:
 */
@RestController
@Slf4j
@RequestMapping("/business/shopkeeper")
public class ShopKeeperBusinessController {

    @Resource
    private IShopKeeperService shopKeeperService;


    /**
     * 加盟主管
     *
     * @param memberApplyDto
     * @return
     */
    @PostMapping("/addDirector")
    public ResponseInfo addDirector(@Valid MemberApplyDto memberApplyDto) {
        boolean isSuccess = shopKeeperService.addDirector(memberApplyDto);
        return new ResponseInfo(isSuccess);
    }

    /**
     * 加盟店长
     *
     * @param telePhone 手机号码
     * @return
     */
    @PostMapping("/addShopKeeper")
    public ResponseInfo addShopKeeper(String telePhone) {
        if (StringUtils.isBlank(telePhone)) {
            throw new GlobalException(MemberManagerExceptionEnum.MEMBER_TELEPHONE_NOT_EXIST);
        }
        boolean isSuccess = shopKeeperService.addShopKeeper(telePhone);
        return new ResponseInfo(isSuccess);
    }


}
