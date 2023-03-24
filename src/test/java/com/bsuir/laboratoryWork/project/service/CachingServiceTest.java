package com.bsuir.laboratoryWork.project.service;

import com.bsuir.laboratoryWork.project.model.CalculationResult;
import com.bsuir.laboratoryWork.project.model.ParametersKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CachingServiceTest {

    @Test
    void containsAddedResultAndKey() {
        CachingService cachingService = new CachingService(new ParametersKey("5","10"),new CalculationResult("30","50"));
        ParametersKey newParametersKey = new ParametersKey("5","10");
        assertTrue(cachingService.contains(newParametersKey));
    }
    @Test
    void getResultByKeyReturnsAddedValueWithNewEqualKey(){
        CalculationResult calculationResult = new CalculationResult("30","50");
        CachingService cachingService = new CachingService(new ParametersKey("5","10"),calculationResult);
        ParametersKey newParametersKey = new ParametersKey("5","10");
        assertEquals(calculationResult,cachingService.getResultByKey(newParametersKey));
    }
}