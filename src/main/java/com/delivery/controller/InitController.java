package com.delivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Administrator
 * @ClassName: InitController
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/23 17:16
 */

@Controller
@RequestMapping("/map")
public class InitController {
    // 初始化地图界面
    @RequestMapping(value = "/index", method= RequestMethod.GET)
    public String init(){
        return "index";
    }
}
