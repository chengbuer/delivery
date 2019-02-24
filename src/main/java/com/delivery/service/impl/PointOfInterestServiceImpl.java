package com.delivery.service.impl;

import com.delivery.entity.PointOfInterest;
import com.delivery.repository.PointOfInterestRepository;
import com.delivery.service.PointOfInterestService;
import com.delivery.utils.LngLatRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointOfInterestServiceImpl implements PointOfInterestService {

    @Autowired
    private PointOfInterestRepository pointOfInterestRepository;


    public void addPointOfInterest(PointOfInterest pointOfInterest){
        pointOfInterestRepository.save(pointOfInterest);
    }


    public List<PointOfInterest> getPointOfInterestsByRange(LngLatRange llRange){
        List<PointOfInterest> poIs = pointOfInterestRepository.
                findByLngBetweenAndLatBetween(llRange.getLngStart(),
                        llRange.getLngEnd(),
                        llRange.getLatStart(),
                        llRange.getLngEnd());

        return poIs;
    }



}
