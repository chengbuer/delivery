package com.delivery.repository;

import com.delivery.entity.Schedule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Administrator
 * @ClassName: ScheduleRepositoryTest
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/23 16:21
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleRepositoryTest {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Test
    public void testInsert() throws Exception {
        Schedule schedule = new Schedule(1, 1, 1, 2);
        scheduleRepository.save(schedule);
    }

    @Test
    public void findByLngBetweenAndLatBetween() {
        List<Schedule> schedules = scheduleRepository.findByLngBetweenAndLatBetween(0, 2, 0, 2);
        for(Schedule s : schedules){
            s.bytesToSchedule();
            System.out.println(s);
        }
    }
}