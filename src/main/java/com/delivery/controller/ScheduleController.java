package com.delivery.controller;

import com.delivery.repository.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 * @ClassName: ScheduleController
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/26 8:55
 */

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ScheduleRepository scheduleRepository;

    @RequestMapping(value = "/removeAll", method = RequestMethod.DELETE)
    @ResponseBody
    public String removeAllSchedule(){
        logger.info("重置所有 Schedule");
        scheduleRepository.deleteAll();
        return "SUCCESS";
    }
}
