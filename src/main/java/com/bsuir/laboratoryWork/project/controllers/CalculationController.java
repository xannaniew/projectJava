package com.bsuir.laboratoryWork.project.controllers;

import com.bsuir.laboratoryWork.project.model.CalculationProcessor;
import com.bsuir.laboratoryWork.project.model.CalculationResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
public class CalculationController {
    private static Logger logger = Logger.getLogger(CalculationController.class.getName());
    private final CalculationProcessor calculationProcessor;
    public CalculationController(CalculationProcessor calculationProcessor){
        this.calculationProcessor = calculationProcessor;
    }
    @GetMapping("/{length}/{height}")
    public CalculationResult Calculation(@PathVariable String length, @PathVariable String height) throws JsonProcessingException {
        logger.info("Received parameters: length = " + length + " height = " + height);
        logger.info("begin to process parameters...");
        calculationProcessor.setLength(length);
        calculationProcessor.setHeight(height);
        String perimeter = calculationProcessor.calcPerimeter();
        String square = calculationProcessor.calcSquare();
        logger.info("Calculation results: perimeter = " + perimeter + " square = " + square);

        CalculationResult calculationResult = new CalculationResult(perimeter,square);

        return calculationResult;
    }

}