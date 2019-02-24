package com.delivery.service;

import com.delivery.entity.Worker;
import com.delivery.utils.LngLatRange;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Administrator
 * @ClassName: WorkerService
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/23 21:58
 */
public interface WorkerService {
    void addWorker(Worker worker);

    List<Worker> getWorkersInRange(LngLatRange llRange);
}
