package com.bsuir.laboratoryWork.project.service;

import com.bsuir.laboratoryWork.project.model.CalculationData;
import com.bsuir.laboratoryWork.project.model.CalculationResult;
import com.bsuir.laboratoryWork.project.model.ParametersKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class AsyncService {
    @Autowired
    private CalculationService calculationService;
    @Autowired
    private CalculationDataService calculationDataService;
    @Async
    public CompletableFuture<CalculationData> calcResAsync(ParametersKey key) throws InterruptedException {
        Thread.sleep(10000);
        CalculationResult calculationResult = calculationService.calcAndBuildResult(key);

        return new CompletableFuture<CalculationData>().completedFuture(new CalculationData(key,calculationResult.getPerimeter(),calculationResult.getSquare()));
    }
    @Async
    public void calcAndAddToRepAsync(ParametersKey key) throws InterruptedException, ExecutionException {
        CompletableFuture<CalculationData> completableFuture = calcResAsync(key);
        calculationDataService.addNewCalculationData(completableFuture.get());
    }
}
