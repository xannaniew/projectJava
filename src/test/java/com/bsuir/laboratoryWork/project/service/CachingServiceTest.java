package com.bsuir.laboratoryWork.project.service;

import com.bsuir.laboratoryWork.project.model.CalculationResult;
import com.bsuir.laboratoryWork.project.model.ParametersKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CachingServiceTest {

    @Test
    void containsAddedResultAndKey() {
        CachingService cachingService = new CachingService();
        ParametersKey parametersKey = new ParametersKey("5","10");
        CalculationResult calculationResult = new CalculationResult("30","50");
        ParametersKey newParametersKey = new ParametersKey("5","10");
        cachingService.addResult(parametersKey,calculationResult);
        assertTrue(cachingService.contains(newParametersKey));
    }
    @Test
    void getResultByKeyCheckHash(){
        CachingService cachingService = new CachingService();
        ParametersKey parametersKey = new ParametersKey("5","10");
        CalculationResult calculationResult = new CalculationResult("30","50");
        ParametersKey newParametersKey = new ParametersKey("5","10");
        cachingService.addResult(parametersKey,calculationResult);
        assertEquals(calculationResult,cachingService.getResultByKey(newParametersKey));
    }
}