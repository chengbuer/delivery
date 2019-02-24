package com.delivery.controller;

import com.delivery.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
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

@RequestMapping("/testT")
@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test1")
    public String getIndex(){
        testService.print();
        return "test";
    }
}
