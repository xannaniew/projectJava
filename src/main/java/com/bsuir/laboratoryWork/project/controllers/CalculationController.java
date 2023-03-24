package com.bsuir.laboratoryWork.project.controllers;

import com.bsuir.laboratoryWork.project.model.ParametersKey;
import com.bsuir.laboratoryWork.project.service.CachingService;
import com.bsuir.laboratoryWork.project.service.CalculationService;
import com.bsuir.laboratoryWork.project.model.CalculationResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.logging.Logger;

@RestController
public class CalculationController {
    private static final Logger logger = Logger.getLogger(CalculationController.class.getName());
    private final CalculationService calculationService;
    private final CachingService cachingService;
    public CalculationController(CalculationService calculationService,CachingService cachingService){
        this.calculationService = calculationService;
        this.cachingService = cachingService;
    }
    @GetMapping("/{length}:{height}") // parameters on the same level, divided by :
    public CalculationResult Calculation(@PathVariable String length, @PathVariable String height) {
        logger.info("Received parameters: length = " + length + " height = " + height);

        ParametersKey parametersKey = new ParametersKey(length,height);
        if(cachingService.contains(parametersKey)){
            logger.info("received result from cache");
            return cachingService.getResultByKey(parametersKey);
        }
        else if(cachingService.contains(new ParametersKey(parametersKey.getHeight(),parametersKey.getLength()))) { // check that cache does not contain equivalent
            logger.info("received result from cache");                                                        // params pairs: for example
                                                                                                                   // key = [2,5] and key = [5,2]
                                                                                                                   // their results are same: [14,10]
            return cachingService.getResultByKey(new ParametersKey(parametersKey.getHeight(), parametersKey.getLength()));
        }
        else {
            logger.info("No such key in cache");
        }

        CalculationResult calculationResult = new CalculationResult(calculationService.calcPerimeter(parametersKey),calculationService.calcSquare(parametersKey));

        logger.info("Calculation results: perimeter = " + calculationResult.getPerimeter() + " square = " + calculationResult.getSquare());

        cachingService.addResult(parametersKey,calculationResult);

        return calculationResult;
    }

}