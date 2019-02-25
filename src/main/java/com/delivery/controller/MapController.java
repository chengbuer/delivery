package com.delivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Administrator
 * @ClassName: MapController
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/25 13:49
 */

@Controller
@RequestMapping("/delivery")
public class MapController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndex(){
        return "index";
    }

    @RequestMapping(value = "/backstage", method = RequestMethod.GET)
    public String getBackStage(){
        return "backstage";
    }
}
