package com.bsuir.laboratoryWork.project.controllers;

import com.bsuir.laboratoryWork.project.model.CalculationResult;
import com.bsuir.laboratoryWork.project.model.ParametersKey;
import com.bsuir.laboratoryWork.project.service.*;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class CalculationControllerTest {
    private CachingService cachingService;
    private CalculationService calculationService;
    private BulkCalculationService bulkCalculationService;
    private CalculationController controller;
    private ParametersProcessingService parametersProcessingService;
    private CalculationDataService dataService;
    private AsyncService asyncService;
    public void setUp(){
        CallCountService callCountService = mock(CallCountService.class);
        when(callCountService.incrementCountAndReturnValue()).thenReturn(1);
        cachingService = new CachingService();
        calculationService = new CalculationService();
        bulkCalculationService = new BulkCalculationService();
        parametersProcessingService = new ParametersProcessingService();
        dataService = new CalculationDataService();
        asyncService = new AsyncService();
        controller = new CalculationController(calculationService,cachingService,callCountService,bulkCalculationService,parametersProcessingService,dataService,asyncService);
    }


    @Test
    void calculationNormalReturnFromCache() {
        ParametersKey parametersKey = new ParametersKey(5,10);
        CalculationResult calculationResult = new CalculationResult(30,50);

        cachingService.addResult(parametersKey,calculationResult);

        assertEquals(calculationResult,controller.calculation("5","10"));
    }
}