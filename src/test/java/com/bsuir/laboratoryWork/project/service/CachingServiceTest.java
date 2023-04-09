package com.bsuir.laboratoryWork.project.service;

import com.bsuir.laboratoryWork.project.model.CalculationResult;
import com.bsuir.laboratoryWork.project.model.ParametersKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CachingServiceTest {
    private CachingService cachingService;
    private ParametersKey parametersKey;
    private CalculationResult calculationResult;

    public void setUp(String length, String height, String perimeter, String square, boolean is_addCache){
        parametersKey = new ParametersKey(length, height);
        calculationResult = new CalculationResult(perimeter, square);
        cachingService = new CachingService();
        if(is_addCache)
            cachingService.addResult(parametersKey,calculationResult);
    }

    @Test
    void containsAddedResultAndKey() {
        setUp("5","10","30","50",true);
        ParametersKey newParametersKey = new ParametersKey("5","10");
        assertTrue(cachingService.contains(newParametersKey));
    }
    @Test
    void getResultByKeyReturnsAddedValueWithNewEqualKey(){
        setUp("5","10","30","50",true);
        ParametersKey newParametersKey = new ParametersKey("5","10");
        assertEquals(calculationResult,cachingService.getResultByKey(newParametersKey));
    }
}