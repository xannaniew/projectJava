package com.bsuir.laboratoryWork.project.controllers;

import com.bsuir.laboratoryWork.project.model.ParametersKey;
import com.bsuir.laboratoryWork.project.service.*;
import com.bsuir.laboratoryWork.project.model.CalculationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RestController
@Slf4j
public class CalculationController {
    private final CalculationService calculationService;
    private final CachingService cachingService;
    private final CallCountService callCountService;
    private final BulkCalculationService bulkCalculationService;
    private final ParametersProcessingService parametersProcessingService;
    public CalculationController(CalculationService calculationService,CachingService cachingService, CallCountService callCountService,BulkCalculationService bulkCalculationService,
                                 ParametersProcessingService parametersProcessingService){
        this.calculationService = calculationService;
        this.cachingService = cachingService;
        this.callCountService = callCountService;
        this.bulkCalculationService = bulkCalculationService;
        this.parametersProcessingService = parametersProcessingService;
    }
    @GetMapping("/{length}:{height}") // parameters on the same level, divided by :
    public CalculationResult calculation(@PathVariable String length, @PathVariable String height) {
        callCountService.incrementCountAndReturnValue();

        log.info("Received parameters: length = " + length + " height = " + height);

        ParametersKey parametersKey = new ParametersKey(parametersProcessingService.convertToInt(length),parametersProcessingService.convertToInt(height));
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
        return callCountService.getCounter();
    }
    @PostMapping("/calculatorBulk")
    public ResponseEntity<?> calculateBulk(@RequestBody List<ParametersKey> requestList) throws NoSuchElementException{

        List<CalculationResult> responseList = new ArrayList<>();

        requestList.stream().forEach(currentElement -> log.debug("Received pair { " + currentElement.getLength() + " ; " + currentElement.getHeight() + " }"));

        requestList.stream()
                .filter(currentElement -> cachingService.contains(currentElement))
                .forEach(currentElement ->
                {
                    log.debug("received result from cache");
                    responseList.add(cachingService.getResultByKey(currentElement));
                });

        requestList.stream()
                .filter(currentElement -> !cachingService.contains(currentElement))
                .forEach(currentElement ->
                {
                    log.debug("no such key in cache");
                    CalculationResult calculationResult = calculationService.calcAndBuildResult(currentElement);
                    responseList.add(calculationResult);
                    cachingService.addResult(currentElement,responseList.get(responseList.indexOf(calculationResult)));
                });

        return new ResponseEntity<>("Result: " + responseList + "\nmaxPerimeter: "
                + bulkCalculationService.findMaxByPerimeter(responseList) + "\nminSquare: "
                + bulkCalculationService.findMinBySquare(responseList) + "\naverageResult: " + bulkCalculationService.calcAverageResult(responseList),HttpStatus.OK);
    }
}