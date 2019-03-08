package com.delivery.service;

import com.delivery.entity.Schedule;
import com.delivery.utils.Event;
import com.delivery.utils.Task;
import com.delivery.utils.WorkerInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Administrator
 * @ClassName: ScheduleService
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/26 16:07
 */

public interface ScheduleService {
    void removeAllSchedule();
    Schedule arrangeTaskToBestSchedule(Task task);
    void updateSchedules(List<WorkerInfo> workersInfo);
    Schedule updateSingleSchedule(WorkerInfo workerInfo);
    List<Schedule> arrangeMultiTask(MultipartFile file);
}
