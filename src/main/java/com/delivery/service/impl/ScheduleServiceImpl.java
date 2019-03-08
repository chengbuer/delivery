package com.delivery.service.impl;

import com.delivery.entity.PointOfInterest;
import com.delivery.entity.Schedule;
import com.delivery.entity.Worker;
import com.delivery.repository.PointOfInterestRepository;
import com.delivery.repository.ScheduleRepository;
import com.delivery.service.PointOfInterestService;
import com.delivery.service.ScheduleService;
import com.delivery.utils.Event;
import com.delivery.utils.Task;
import com.delivery.utils.TaskAllocation;
import com.delivery.utils.WorkerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


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

        for (Schedule schedule : schedules) {
            schedule.bytesToSchedule();
        }

        TaskAllocation ta = new TaskAllocation(schedules, poIs, task);

        Schedule schedule = ta.getBestPair();
        logger.info(String.valueOf(schedule));

        if (schedule != null) {
            schedule.scheduleToBytes();
            scheduleRepository.save(schedule);
        }

        return schedule;
    }

    @Override
    public void updateSchedules(List<WorkerInfo> workersInfo) {
        List<Integer> ids = new ArrayList<>();
        Map<Integer, WorkerInfo> map = new HashMap<>();
        for(WorkerInfo info : workersInfo){
            logger.info(String.valueOf(info));
            ids.add(info.getWorkerId());
            map.put(info.getWorkerId(), info);
        }

        List<Schedule> schedules = scheduleRepository.findAllById(ids);

        for(Schedule schedule : schedules){
            schedule.bytesToSchedule();
            System.out.println("升级前 " + schedule.getSchedule().size());
            Integer cur = schedule.getWorkerId();
            WorkerInfo info= map.get(cur);

            schedule.updateSchedule(info);

            System.out.println("升级后" + schedule.getSchedule().size());
        }

        scheduleRepository.saveAll(schedules);
    }

    @Override
    public Schedule updateSingleSchedule(WorkerInfo workerInfo) {

        Optional<Schedule> k = scheduleRepository.findById(workerInfo.getWorkerId());
        Schedule schedule = k.get();
        schedule.updateSchedule(workerInfo);
        scheduleRepository.save(schedule);
        return schedule;
    }

    @Override
    public List<Schedule> arrangeMultiTask(MultipartFile file)  {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<PointOfInterest> pointOfInterests = pointOfInterestRepository.findAll();
        for (Schedule schedule : schedules) {
            schedule.bytesToSchedule();
        }
        try {
            List<Task> tasks = GenerateTasks(file.getInputStream());

            for(Task task : tasks){
                TaskAllocation ta = new TaskAllocation(schedules, pointOfInterests, task);
                ta.getBestPair();
            }

        }catch (IOException e) {
            e.printStackTrace();
        }

        // 这里要保存数据

        return schedules;
    }


    public static List<Task> GenerateTasks(InputStream inputStream){
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<Task> tasks = new ArrayList<>();
        String line = null;

        try {
            while ((line = reader.readLine()) != null) {
                String[] task = line.split("\\s+");
                int id = Integer.parseInt(task[0]);
                double lng = Double.parseDouble(task[1]);
                double lat = Double.parseDouble(task[2]);
                double beginTime = Double.parseDouble(task[3]);
                double endTime = Double.parseDouble(task[4]);
                double radius = Double.parseDouble(task[5]);
                double reward = Double.parseDouble(task[6]);
                int type = Integer.parseInt(task[7]);

                Task curTask = new Task(id, lng, lat, beginTime, endTime, radius, reward, type);
                tasks.add(curTask);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return tasks;
    }
}
