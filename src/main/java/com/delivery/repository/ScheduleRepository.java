package com.delivery.repository;

import com.delivery.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import java.util.List;

/**
 * @author Administrator
 * @ClassName: ScheduleRepository
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/23 16:19
 */
public interface ScheduleRepository extends JpaRepository<Schedule, Integer>{
    List<Schedule> findByLngBetweenAndLatBetween(double lngStart, double lngEnd, double latStart, double latEnd);

    @Modifying
    @Query(value="insert into schedule values(?1, ?2, ?3, ?4)", nativeQuery = true)
    void insertSchedule(int workerId, double lng, double lat, byte[] events);
}
