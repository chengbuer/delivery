package com.delivery.controller;

import com.delivery.entity.Worker;
import com.delivery.repository.WorkerRepository;
import com.delivery.utils.LngLatRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.slf4j.LoggerFactory.*;

/**
 * @author Administrator
 * @ClassName: WorkerController
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/22 15:55
 */

@RestController
@RequestMapping("/worker")
public class WorkerController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WorkerRepository workerRepository;

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    @ResponseBody
    public Worker addWorker(@RequestBody Worker worker) {
        Worker k;
        k = workerRepository.save(worker);
        return k;
    }


    @RequestMapping(value = "/workers", method = RequestMethod.GET)
    @ResponseBody
    public List<Worker> getWorkersInRange(@RequestBody LngLatRange llRange) {
        List<Worker> workers = workerRepository.
                findByLngBetweenAndLatBetween(llRange.getLngStart(),
                        llRange.getLngEnd(),
                        llRange.getLatStart(),
                        llRange.getLngEnd());

        return workers;
    }
}
