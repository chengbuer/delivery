package com.delivery.service.impl;

import com.delivery.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @ClassName: TestServiceImpl
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/23 20:58
 */

@Service
public class TestServiceImpl implements TestService {

    public void print(){
        System.out.println("Hello World");
    }
}
