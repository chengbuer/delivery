package com.delivery.controller;

import com.delivery.service.TestService;
import com.delivery.utils.LngLatRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private TestService testService;

    @GetMapping("/test1")
    public String getIndex(){
        testService.print();
        return "test";
    }

    @RequestMapping(value = "/jsonTest", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public LngLatRange jsonTest(@RequestBody LngLatRange llRange){

        System.out.println(llRange);
        return llRange;

    }
}
