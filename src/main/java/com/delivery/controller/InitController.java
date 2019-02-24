package com.delivery.controller;

import com.delivery.entity.PointOfInterest;
import com.delivery.entity.Worker;
import com.delivery.service.InitService;
import com.delivery.service.WorkerService;
import com.delivery.utils.LngLatRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    // 初始化地图界面
    private final InitService initService;

    @Autowired
    public InitController(InitService initService) {
        this.initService = initService;
    }

    @RequestMapping(value="/workers", method = RequestMethod.GET)
    @ResponseBody
    public List<Worker> getWorkers(@RequestBody LngLatRange llRange){
        return initService.initWorkers(llRange);

    }

    @RequestMapping(value="/pointOfInterests", method = RequestMethod.GET)
    @ResponseBody
    public List<PointOfInterest> getPointOfInterest(@RequestBody LngLatRange llRange){
        return initService.initPointOfInterests(llRange);
    }
}
