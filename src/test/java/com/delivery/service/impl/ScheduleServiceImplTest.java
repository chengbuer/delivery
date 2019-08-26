package com.delivery.service.impl;

import com.delivery.constant.LabelEnum;
import com.delivery.entity.PointOfInterest;
import com.delivery.entity.Schedule;
import com.delivery.entity.Worker;
import com.delivery.service.ScheduleService;
import com.delivery.utils.Event;
import com.delivery.utils.GreedyAssign;
import com.delivery.utils.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Administrator
 * @ClassName: ScheduleServiceImplTest
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/26 19:23
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleServiceImplTest {

    @Autowired
    private ScheduleService scheduleService;

    @Test
    public void arrangeTaskToBestSchedule() throws Exception {
        Task task = new Task(1, 116.404, 39.905, 0, 1000, 2, 1, 1);
        Schedule  schedule = scheduleService.arrangeTaskToBestSchedule(task);

        System.out.println(schedule);
    }

    @Test
    public void test() throws Exception {

        List<Event> ls = new ArrayList<>();
        Event l1 = new Event( LabelEnum.Worker.getName(),39.5, 34.2, 0, 0);
        Worker worker = new Worker();

        Schedule es = new Schedule(1, 39.5, 34.2, 0);
        List<Schedule> ss = new ArrayList<>();
        ss.add(es);
        PointOfInterest p = new PointOfInterest(39.5, 34.17, 1, "1 2 3");
        List<PointOfInterest> pList = new ArrayList<>();
        pList.add(p);
        Task t = new Task(1, 39.35, 34.15, 0, 1000, 4, 1, 1);

        GreedyAssign pair = new GreedyAssign(ss, pList, t);
        es = pair.getBestPair();

        System.out.println(es);
    }

    @Test
    public void findSchedulesById(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
    }

}