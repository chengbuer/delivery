package com.delivery.controller;

import com.delivery.entity.PointOfInterest;
import com.delivery.entity.Worker;
import com.delivery.service.InitService;
import com.delivery.service.WorkerService;
import com.delivery.utils.LngLatRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Administrator
 * @ClassName: InitController
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/23 17:16
 */



@RequestMapping("/initial")
@RestController
public class InitController {
    Logger logger = LoggerFactory.getLogger(getClass());
    // 初始化地图界面
    private final InitService initService;

    @Autowired
    public InitController(InitService initService) {
        this.initService = initService;
    }

    @RequestMapping(value="/workers", method = RequestMethod.POST)
    @ResponseBody
    public List<Worker> getWorkers(@RequestBody LngLatRange llRange){
        List<Worker> workers = initService.initWorkers(llRange);
        logger.info(String.valueOf(workers));
        return workers;
    }

    @RequestMapping(value="/pointOfInterests", method = RequestMethod.POST)
    @ResponseBody
    public List<PointOfInterest> getPointOfInterest(@RequestBody LngLatRange llRange){
        logger.info(String.valueOf(llRange));
        return initService.initPointOfInterests(llRange);
    }
}
