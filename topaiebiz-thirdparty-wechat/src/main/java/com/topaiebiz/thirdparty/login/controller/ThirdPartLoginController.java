package com.topaiebiz.thirdparty.login.controller;

import com.topaiebiz.thirdparty.login.service.ThirdPartLoginService;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Cai on 2017-10-31.
 */
@Controller("thirdPartLogin")
public class ThirdPartLoginController {

    @Autowired
    ThirdPartLoginService thirdPartLoginService;


    /**
     * APP获取code后，用户授权登录获取用户信息
     * @param code
     * @throws JSONException 
     */
    @RequestMapping("wechatLogin")
    public void wechatLogin(String code) throws JSONException{
        thirdPartLoginService.checkLogin(code);
    }
}
