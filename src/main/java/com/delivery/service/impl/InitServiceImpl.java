package com.delivery.service.impl;

import com.delivery.entity.PointOfInterest;
import com.delivery.entity.Worker;
import com.delivery.repository.PointOfInterestRepository;
import com.delivery.repository.WorkerRepository;
import com.delivery.service.InitService;
import com.delivery.utils.LngLatRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @ClassName: InitServiceImpl
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/23 21:59
 */

@Service
public class InitServiceImpl implements InitService {

    @Autowired
    private PointOfInterestRepository pointOfInterestRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Override
    public List<Worker> initWorkers(LngLatRange llRange) {
        List<Worker> workers = workerRepository.findByLngBetweenAndLatBetween(llRange.getLngStart(), llRange.getLngEnd(),
                                                        llRange.getLatStart(), llRange.getLatEnd());

        return workers;
    }

    @Override
    public List<PointOfInterest> initPointOfInterests(LngLatRange llRange) {
        List<PointOfInterest> pointOfInterests = pointOfInterestRepository.findByLngBetweenAndLatBetween(llRange.getLngStart(), llRange.getLngEnd(),
                llRange.getLatStart(), llRange.getLatEnd());

        return pointOfInterests;
    }
}
