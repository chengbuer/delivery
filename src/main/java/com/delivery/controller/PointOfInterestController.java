package com.delivery.controller;

import com.delivery.entity.PointOfInterest;
import com.delivery.repository.PointOfInterestRepository;
import com.delivery.utils.LngLatRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Administrator
 * @ClassName: PointOfInterestController
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/22 20:01
 */

@RequestMapping("/poI")
@RestController
public class PointOfInterestController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PointOfInterestRepository pointOfInterestRepository;

    @RequestMapping(value="/add", method= RequestMethod.PUT)
    public PointOfInterest addPointOfInterest(@RequestBody PointOfInterest poI){
        logger.info(String.valueOf(poI));
        pointOfInterestRepository.save(poI);
        return poI;
    }

    @RequestMapping(value = "/workers", method = RequestMethod.GET)
    @ResponseBody
    public List<PointOfInterest> getWorkersInRange(@RequestBody LngLatRange llRange) {
        List<PointOfInterest> poIs = pointOfInterestRepository.
                findByLngBetweenAndLatBetween(llRange.getLngStart(),
                        llRange.getLngEnd(),
                        llRange.getLatStart(),
                        llRange.getLngEnd());

        return poIs;
    }
}
