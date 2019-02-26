package com.delivery.controller;

import com.delivery.entity.Schedule;
import com.delivery.repository.ScheduleRepository;
import com.delivery.service.ScheduleService;
import com.delivery.utils.Task;
import com.delivery.utils.TaskAllocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    private ScheduleService scheduleService;

    @RequestMapping(value = "/removeAll", method = RequestMethod.DELETE)
    @ResponseBody
    public String removeAllSchedule(){
        logger.info("重置所有 Schedule");
        scheduleService.removeAllSchedule();
        return "SUCCESS";
    }

    @RequestMapping(value = "/taskQuery", method = RequestMethod.POST)
    @ResponseBody
    public List<Schedule> findBestSchedule(@RequestBody Task task){
        logger.info(String.valueOf(task));

        return null;
    }
}
