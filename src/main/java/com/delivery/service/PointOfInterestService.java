package com.delivery.service;

import com.delivery.entity.PointOfInterest;
import com.delivery.utils.LngLatRange;

import java.util.List;

/**
 * @author Administrator
 * @ClassName: PointOfInterestService
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/23 21:57
 */
public interface PointOfInterestService {
    void addPointOfInterest(PointOfInterest pointOfInterest);

    List<PointOfInterest> getPointOfInterestsByRange(LngLatRange llRange);

}
