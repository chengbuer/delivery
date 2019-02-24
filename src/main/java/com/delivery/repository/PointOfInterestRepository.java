package com.delivery.repository;

import com.delivery.entity.PointOfInterest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Administrator
 * @ClassName: PointOfInterestRepository
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/22 20:02
 */
public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, Integer> {
    List<PointOfInterest> findByLngBetweenAndLatBetween(double lngStart, double lngEnd, double latStart, double latEnd);
}
