package com.delivery.service.impl;

import com.delivery.entity.Schedule;
import com.delivery.service.ScheduleService;
import com.delivery.utils.Task;
import com.delivery.utils.WorkerInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Administrator
 * @ClassName: SKTScheduleServiceImpl
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/8/25 21:32
 */
public class SKTScheduleServiceImpl implements ScheduleService {
    @Override
    public void removeAllSchedule() {

    }

    @Override
    public Schedule arrangeTaskToBestSchedule(Task task) {
        return null;
    }

    @Override
    public void updateSchedules(List<WorkerInfo> workersInfo) {

    }

    @Override
    public List<Schedule> arrangeMultiTask(MultipartFile file) {
        return null;
    }
}
