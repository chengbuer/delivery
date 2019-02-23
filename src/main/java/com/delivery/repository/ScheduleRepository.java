package com.delivery.repository;

import com.delivery.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Administrator
 * @ClassName: ScheduleRepository
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/23 16:19
 */
public interface ScheduleRepository extends JpaRepository<Schedule, Integer>{
    public List<Schedule> findByLngBetweenAndLatBetween(double lngStart, double lngEnd, double latStart, double latEnd);
}
