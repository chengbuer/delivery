package com.delivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @ClassName: TestController
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/21 20:42
 */

@RequestMapping("/test")
@Controller
public class TestController {

    @GetMapping("/index")
    public String getIndex(){

        return "index";
    }
}
