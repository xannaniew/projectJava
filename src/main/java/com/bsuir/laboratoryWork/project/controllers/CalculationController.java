package com.bsuir.laboratoryWork.project.controllers;

import com.bsuir.laboratoryWork.project.service.CalculationService;
import com.bsuir.laboratoryWork.project.model.CalculationResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
public class CalculationController {
    private static final Logger logger = Logger.getLogger(CalculationController.class.getName());
    private final CalculationService calculationService;
    public CalculationController(CalculationService calculationService){
        this.calculationService = calculationService;
    }
    @GetMapping("/{length}/{height}")
    public CalculationResult Calculation(@PathVariable String length, @PathVariable String height) throws JsonProcessingException {
        logger.info("Received parameters: length = " + length + " height = " + height);
        logger.info("begin to process parameters...");
        //calculationService.setLength(length);
        //calculationService.setHeight(height);
        String perimeter = calculationService.calcPerimeter(length,height);
        String square = calculationService.calcSquare(length,height);
        logger.info("Calculation results: perimeter = " + perimeter + " square = " + square);

        return new CalculationResult(perimeter,square);
    }

}