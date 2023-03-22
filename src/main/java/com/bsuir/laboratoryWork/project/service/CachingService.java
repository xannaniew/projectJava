package com.bsuir.laboratoryWork.project.service;

import com.bsuir.laboratoryWork.project.model.CalculationResult;
import com.bsuir.laboratoryWork.project.model.ParametersKey;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.HashMap;
@Service
@ApplicationScope
public class CachingService {
    private static final HashMap<ParametersKey, CalculationResult> calculationHashMap = new HashMap<>();
    public boolean contains(ParametersKey parametersKey) {
        return calculationHashMap.containsKey(parametersKey);
    }
    public CalculationResult getResultByKey(ParametersKey parametersKey){
        return calculationHashMap.get(parametersKey);
    }
    public void addResult(ParametersKey parametersKey, CalculationResult calculationResult){
        calculationHashMap.put(parametersKey,calculationResult);
    }
    public static HashMap<ParametersKey, CalculationResult> getCalculationHashMap() {
        return calculationHashMap;
    }
}
