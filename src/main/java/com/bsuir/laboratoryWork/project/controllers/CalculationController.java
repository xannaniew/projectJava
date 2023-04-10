package com.bsuir.laboratoryWork.project.controllers;

import com.bsuir.laboratoryWork.project.model.ParametersKey;
import com.bsuir.laboratoryWork.project.service.CachingService;
import com.bsuir.laboratoryWork.project.service.CalculationService;
import com.bsuir.laboratoryWork.project.model.CalculationResult;
import com.bsuir.laboratoryWork.project.service.CallCountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

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
        callCountService.incrementCountAndReturnValue();

        log.info("Received parameters: length = " + length + " height = " + height);

        ParametersKey parametersKey = new ParametersKey(length,height);
        if(cachingService.contains(parametersKey)){
            log.trace("received result from cache");
            return cachingService.getResultByKey(parametersKey);
        }
        else {
            log.trace("No such key in cache");
        }

        CalculationResult calculationResult = calculationService.calcAndBuildResult(parametersKey);
        log.info("Calculation results: perimeter = " + calculationResult.getPerimeter() + " square = " + calculationResult.getSquare());

        cachingService.addResult(parametersKey,calculationResult);

        return calculationResult;
    }
    @GetMapping("/count")
    public int count(){
        return callCountService.incrementCountAndReturnValue();
    }
    @PostMapping("/calculatorBulk")
    public ResponseEntity<?> calculateBulk(@RequestBody List<ParametersKey> requestList) {

        requestList.forEach((currentElement) -> log.info("Received pair { " + currentElement.getLength() + " ; " + currentElement.getHeight() + " }"));

        List<CalculationResult> respondList = new ArrayList<>();

        requestList.forEach((currentElement) ->
        {
            if(cachingService.contains(currentElement)){
                log.debug("received result from cache");
                respondList.add(cachingService.getResultByKey(currentElement));
            }
            else{
                respondList.add(calculationService.calcAndBuildResult(currentElement));
            }
        });

        return new ResponseEntity<>(respondList,HttpStatus.OK);
    }
}