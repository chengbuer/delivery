package com.delivery.repository;

import com.delivery.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Administrator
 * @ClassName: WorkerRepository
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/22 15:55
 */
public interface WorkerRepository extends JpaRepository<Worker, Integer>{
    List<Worker> findByLngBetweenAndLatBetween(double lngStart, double lngEnd, double latStart, double latEnd);
}
