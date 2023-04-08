package com.bsuir.laboratoryWork.project.controllers;

import com.bsuir.laboratoryWork.project.model.ParametersKey;
import com.bsuir.laboratoryWork.project.service.CachingService;
import com.bsuir.laboratoryWork.project.service.CalculationService;
import com.bsuir.laboratoryWork.project.model.CalculationResult;
import com.bsuir.laboratoryWork.project.service.CallCountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@Slf4j
public class CalculationController {
    private final CalculationService calculationService;
    private final CachingService cachingService;
    private final CallCountService callCountService;
    public CalculationController(CalculationService calculationService,CachingService cachingService, CallCountService callCountService){
        this.calculationService = calculationService;
        this.cachingService = cachingService;
        this.callCountService = callCountService;
    }
    @GetMapping("/{length}:{height}") // parameters on the same level, divided by :
    public CalculationResult calculation(@PathVariable String length, @PathVariable String height) {
        callCountService.incrementCounter();

        log.info("Received parameters: length = " + length + " height = " + height);

        ParametersKey parametersKey = new ParametersKey(length,height);
        if(cachingService.contains(parametersKey)){
            log.trace("received result from cache");
            return cachingService.getResultByKey(parametersKey);
        }
        else {
            log.trace("No such key in cache");
        }

        CalculationResult calculationResult = new CalculationResult(calculationService.calcPerimeter(parametersKey),calculationService.calcSquare(parametersKey));
        log.info("Calculation results: perimeter = " + calculationResult.getPerimeter() + " square = " + calculationResult.getSquare());

        cachingService.addResult(parametersKey,calculationResult);

        return calculationResult;
    }
    @GetMapping("/count")
    public int count(){
        return callCountService.incrementCounter();
    }
}