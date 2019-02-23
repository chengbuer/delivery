package com.delivery.entity;

import com.delivery.DeliveryApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * @author Administrator
 * @ClassName: ScheduleTest
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/23 14:56
 */


public class ScheduleTest {

    @Test
    public void xuLieHua(){

        Schedule schedule = new Schedule(1, 2, 2, 3);

        schedule.scheduleToBytes();

        for(byte b : schedule.getEvents()){
            System.out.print(b);
        }

        System.out.println();

        schedule.bytesToSchedule();

        System.out.println(schedule.getSchedule());

    }

}