package com.bsuir.laboratoryWork.project.service;

import com.bsuir.laboratoryWork.project.model.CalculationResult;
import com.bsuir.laboratoryWork.project.model.ParametersKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CalculationService {
    public CalculationResult calcAndBuildResult(ParametersKey parametersKey){
        return new CalculationResult(calcPerimeter(parametersKey.getLength(),parametersKey.getHeight()),calcSquare(parametersKey.getLength(),parametersKey.getHeight()));
    }
    public int calcPerimeter(int length, int height){
        return 2 * length + 2 * height;
    }
    public int calcSquare(int length, int height){
        return length * height;
    }
}
