package com.delivery.service;

import com.delivery.entity.PointOfInterest;
import com.delivery.entity.Worker;
import com.delivery.utils.LngLatRange;

import java.util.List;

/**
 * @author Administrator
 * @ClassName: InitService
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/23 21:58
 */
public interface InitService {
    List<Worker> initWorkers(LngLatRange llRange);

    List<PointOfInterest> initPointOfInterests(LngLatRange llRange);
}
