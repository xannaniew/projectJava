package com.bsuir.laboratoryWork.project.service;

import com.bsuir.laboratoryWork.project.exceptions.InvalidParametersException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Service
@RequestScope
public class CalculationService {
    public String calcPerimeter(String length, String height) throws NumberFormatException{
        if(Integer.parseInt(length) <= 0 || Integer.parseInt(height) <=0)
            throw new InvalidParametersException("length/height cannot be lower or equal to zero");
        int perimeter = 2 * Integer.parseInt(length) + 2 * Integer.parseInt(height);
        return Integer.toString(perimeter);
    }
    public String calcSquare(String length, String height) throws NumberFormatException{
        if(Integer.parseInt(length) <= 0 || Integer.parseInt(height) <=0)
            throw new InvalidParametersException("length/height cannot be lower or equal to zero");
        int square = Integer.parseInt(length) * Integer.parseInt(height);
        return Integer.toString(square);
    }
}
