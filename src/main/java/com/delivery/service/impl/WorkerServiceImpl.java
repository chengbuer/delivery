package com.delivery.service.impl;

import com.delivery.entity.Worker;
import com.delivery.repository.WorkerRepository;
import com.delivery.service.WorkerService;
import com.delivery.utils.LngLatRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    @Override
    public void addWorker(Worker worker) {
        workerRepository.save(worker);
    }

    @Override
    public List<Worker> getWorkersInRange(LngLatRange llRange) {
        List<Worker> workers = workerRepository.
                findByLngBetweenAndLatBetween(llRange.getLngStart(),
                        llRange.getLngEnd(),
                        llRange.getLatStart(),
                        llRange.getLngEnd());
        return workers;
    }
}
