package com.bsuir.laboratoryWork.project.controllers;

import com.bsuir.laboratoryWork.project.model.ParametersKey;
import com.bsuir.laboratoryWork.project.service.CachingService;
import com.bsuir.laboratoryWork.project.service.CalculationService;
import com.bsuir.laboratoryWork.project.model.CalculationResult;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{length}/{height}")
    public CalculationResult Calculation(@PathVariable String length, @PathVariable String height) {
        logger.info("Received parameters: length = " + length + " height = " + height);

        ParametersKey parametersKey = new ParametersKey(length,height);
        if(cachingService.contains(parametersKey)){
            logger.info("received result from cache");
            return cachingService.getResultByKey(parametersKey);
        }
        else if(cachingService.contains(new ParametersKey(parametersKey.getHeight(),parametersKey.getLength()))) {
            logger.info("received result from cache");
            return cachingService.getResultByKey(new ParametersKey(parametersKey.getHeight(), parametersKey.getLength()));
        }
        else
            logger.info("No such key in cache");

        String perimeter = calculationService.calcPerimeter(parametersKey);
        String square = calculationService.calcSquare(parametersKey);

        logger.info("Calculation results: perimeter = " + perimeter + " square = " + square);

        CalculationResult calculationResult = new CalculationResult(perimeter,square);

        cachingService.addResult(parametersKey,calculationResult);

        System.out.println(CachingService.getCalculationHashMap());

        return calculationResult;
    }

}