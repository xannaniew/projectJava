package com.bsuir.laboratoryWork.project.service;

import com.bsuir.laboratoryWork.project.exceptions.InvalidParametersException;
import com.bsuir.laboratoryWork.project.model.CalculationResult;
import com.bsuir.laboratoryWork.project.model.ParametersKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CalculationService {
    public CalculationResult calcAndBuildResult(ParametersKey parametersKey) throws NumberFormatException{
        int length = Integer.parseInt(parametersKey.getLength());
        int height = Integer.parseInt(parametersKey.getHeight());
        if (length <= 0 || height <= 0) {
            throw new InvalidParametersException("length/height cannot be lower or equal to zero");
        }
        return new CalculationResult(calcPerimeter(length,height),calcSquare(length,height));
    }
    public String calcPerimeter(int length, int height){
        int perimeter = 2 * length + 2 * height;
        return Integer.toString(perimeter);
    }
    public String calcSquare(int length, int height){
        int square = length * height;
        return Integer.toString(square);
    }
}
