package com.bsuir.laboratoryWork.project.service;

import com.bsuir.laboratoryWork.project.exceptions.InvalidParametersException;
import com.bsuir.laboratoryWork.project.model.ParametersKey;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {
    public String calcPerimeter(ParametersKey parametersKey) throws NumberFormatException{
        if(Integer.parseInt(parametersKey.getLength()) <= 0 || Integer.parseInt(parametersKey.getHeight()) <=0)
            throw new InvalidParametersException("length/height cannot be lower or equal to zero");
        int perimeter = 2 * Integer.parseInt(parametersKey.getLength()) + 2 * Integer.parseInt(parametersKey.getHeight());
        return Integer.toString(perimeter);
    }
    public String calcSquare(ParametersKey parametersKey) throws NumberFormatException{
        if(Integer.parseInt(parametersKey.getLength()) <= 0 || Integer.parseInt(parametersKey.getHeight()) <=0)
            throw new InvalidParametersException("length/height cannot be lower or equal to zero");
        int square = Integer.parseInt(parametersKey.getLength()) * Integer.parseInt(parametersKey.getHeight());
        return Integer.toString(square);
    }
}
