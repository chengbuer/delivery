package com.delivery.service.impl;

import com.delivery.entity.PointOfInterest;
import com.delivery.entity.Schedule;
import com.delivery.repository.PointOfInterestRepository;
import com.delivery.repository.ScheduleRepository;
import com.delivery.service.PointOfInterestService;
import com.delivery.service.ScheduleService;
import com.delivery.utils.Event;
import com.delivery.utils.Task;
import com.delivery.utils.TaskAllocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Administrator
 * @ClassName: ScheduleServiceImpl
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/26 16:08
 */


@Service
public class ScheduleServiceImpl implements ScheduleService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PointOfInterestRepository pointOfInterestRepository;


    @Override
    public void removeAllSchedule() {
        scheduleRepository.deleteAll();
    }

    @Override
    public Schedule arrangeTaskToBestSchedule(Task task) {
        double lngStart = task.getLng() - 0.5;
        double lngEnd = task.getLng() + 0.5;
        double latStart = task.getLat() - 0.5;
        double latEnd = task.getLat() + 0.5;
        List<PointOfInterest> poIs = pointOfInterestRepository.findByLngBetweenAndLatBetween(lngStart, lngEnd,
                latStart, latEnd);
        List<Schedule> schedules = scheduleRepository.findByLngBetweenAndLatBetween(lngStart, lngEnd,
                latStart, latEnd);

        for(Schedule schedule : schedules){
            schedule.bytesToSchedule();
        }

        TaskAllocation ta = new TaskAllocation(schedules, poIs, task);



        Schedule schedule = ta.getBestPair();
        logger.info(String.valueOf(schedule));

        if(schedule != null){
            schedule.scheduleToBytes();
            scheduleRepository.save(schedule);
        }

        return schedule;
    }
}
