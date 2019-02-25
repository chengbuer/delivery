package com.delivery.controller;

import com.delivery.entity.PointOfInterest;
import com.delivery.repository.PointOfInterestRepository;
import com.delivery.service.PointOfInterestService;
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
    private PointOfInterestService pointOfInterestService;

    @RequestMapping(value="/add", method= RequestMethod.PUT)
    public PointOfInterest addPointOfInterest(@RequestBody PointOfInterest poI){
        logger.info(String.valueOf(poI));
        pointOfInterestService.addPointOfInterest(poI);
        return poI;
    }

    @RequestMapping(value = "/pointOfInterests", method = RequestMethod.GET)
    @ResponseBody
    public List<PointOfInterest> getPointOfInterestsByRange(@RequestBody LngLatRange llRange) {
        logger.info("query PointOfInterests: "  + String.valueOf(llRange));
        List<PointOfInterest> poIs = pointOfInterestService.getPointOfInterestsByRange(llRange);
        return poIs;
    }
}
