package com.bsuir.laboratoryWork.project.model;

import com.bsuir.laboratoryWork.project.exceptions.InvalidParametersException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class CalculationProcessor {
    private String length;
    private String height;
    public String calcPerimeter(){
        int perimeter = 2 * Integer.parseInt(this.length) + 2 * Integer.parseInt(this.height);
        return Integer.toString(perimeter);
    }
    public String calcSquare(){
        int square = Integer.parseInt(this.length) * Integer.parseInt(this.height);
        return Integer.toString(square);
    }
    public void setLength(String length) throws NumberFormatException{
        if (Integer.parseInt(length) < 0)
            throw new InvalidParametersException("length/height cannot be lower than zero");
        this.length = length;
    }
    public void setHeight(String height) throws NumberFormatException{
        if(Integer.parseInt(height) < 0)
            throw new InvalidParametersException("height/length cannot be lower than zero");
        this.height = height;
    }
    public String getLength(){
        return this.length;
    }
    public String getHeight(){
        return this.height;
    }
}
