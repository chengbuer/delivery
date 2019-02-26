package com.delivery.service;

import com.delivery.entity.Schedule;
import com.delivery.utils.Task;
import org.springframework.stereotype.Service;

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
}
