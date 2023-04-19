package com.bsuir.laboratoryWork.project.service;

import com.bsuir.laboratoryWork.project.exceptions.InvalidParametersException;
import org.springframework.stereotype.Service;

@Service
public class ParametersProcessingService {
    public int convertToInt(String parameter) throws NumberFormatException{
        int convertedParameter = Integer.parseInt(parameter);
        if(convertedParameter <= 0){
            throw new InvalidParametersException("length/height cannot be lower or equal to zero");
        }
        return convertedParameter;
    }
}
