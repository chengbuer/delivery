package com.delivery.controller;

import com.delivery.entity.Schedule;
import com.delivery.service.ScheduleService;
import com.delivery.utils.ScheduleUpdate;
import com.delivery.utils.Task;
import com.delivery.utils.WorkerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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
    public Schedule findBestSchedule(@RequestBody Task task){
        logger.info(String.valueOf(task));

        Schedule bestSchedule = scheduleService.arrangeTaskToBestSchedule(task);
        return bestSchedule;
    }

    /**
     * 文件上传具体实现方法;
     *
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public List<Schedule> updateMultiTask(@RequestParam("file") MultipartFile file) {
        logger.info(file.getOriginalFilename());

        List<Schedule> schedules = scheduleService.arrangeMultiTask(file);
        logger.info(String.valueOf(schedules));

        return schedules;
    }


    @RequestMapping(value = "/locationUpdated", method = RequestMethod.POST)
    @ResponseBody
    public Schedule updateLocations(@RequestBody ScheduleUpdate scheduleUpdate){
        // 这里更新完之后没必要返回，直接存储，然后接着下一步就调用 安排task的函数
        List<WorkerInfo> workerInfos = scheduleUpdate.getWorkerInfos();
        scheduleService.updateSchedules(workerInfos);

        Task task = scheduleUpdate.getTask();
        Schedule bestSchedule = scheduleService.arrangeTaskToBestSchedule(task);

        if(bestSchedule != null)
            bestSchedule.setEvents(null);

        // 位置已经能够更新了
        return bestSchedule;
    }


    @RequestMapping(value = "/singleLocationUpdated", method = RequestMethod.POST)
    @ResponseBody
    public Schedule updateSingeLocation(@RequestBody WorkerInfo workerInfo){
        logger.info(String.valueOf(workerInfo));
        return null;
    }
}
