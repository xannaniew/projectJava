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

import java.util.*;

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
        return callCountService.getCounter();
    }
    @PostMapping("/calculatorBulk")
    public ResponseEntity<?> calculateBulk(@RequestBody List<ParametersKey> requestList) throws NoSuchElementException{

        List<CalculationResult> responseList = new ArrayList<>();

        requestList.stream().forEach(currentElement -> log.debug("Received pair { " + currentElement.getLength() + " ; " + currentElement.getHeight() + " }"));
        requestList.stream().forEach(currentElement -> {
            if(cachingService.contains(currentElement)){
                log.debug("received result from cache");
                responseList.add(cachingService.getResultByKey(currentElement));
            }
            else{
                log.debug("no such key in cache");
                CalculationResult calculationResult = calculationService.calcAndBuildResult(currentElement);
                responseList.add(calculationResult);
                cachingService.addResult(currentElement,responseList.get(responseList.indexOf(calculationResult)));
            }
        });

        Comparator<CalculationResult> perimeterComparator = (left, right) -> left.getPerimeter() - right.getPerimeter();
        Optional<CalculationResult> maxPerimeterObject = responseList.stream().max(perimeterComparator);
        int maxPerimeter = maxPerimeterObject.get().getPerimeter();

        Comparator<CalculationResult> squareComparator = (left, right) -> left.getSquare() - right.getSquare();
        Optional<CalculationResult> minSquareObject = responseList.stream().min(squareComparator);
        int minSquare = minSquareObject.get().getSquare();

        OptionalDouble averageResultObject = responseList.stream().mapToInt(obj -> obj.getPerimeter() + obj.getSquare()).average();

        double averageResult = averageResultObject.getAsDouble();

        return new ResponseEntity<>("Result: " + responseList + "\nmaxPerimeter: " + maxPerimeter + "\nminSquare: " + minSquare + "\naverageResult: " + averageResult,HttpStatus.OK);
    }
}