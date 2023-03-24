package com.bsuir.laboratoryWork.project.service;

import com.bsuir.laboratoryWork.project.exceptions.InvalidParametersException;
import com.bsuir.laboratoryWork.project.model.ParametersKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculationServiceTest {
    @Test
    void calcPerimeterFiveLengthFourHeightEqualsTwenty(){
        CalculationService calculationService = new CalculationService();
        ParametersKey parametersKey = new ParametersKey("5","4");
        assertEquals("18",calculationService.calcPerimeter(parametersKey));
    }
    @Test
    void calcPerimeterNegativeOneLengthThrowsInvalidParametersException() {
        CalculationService calculationService = new CalculationService();
        ParametersKey parametersKey = new ParametersKey("-1","1");
        assertThrows(InvalidParametersException.class,() -> calculationService.calcPerimeter(parametersKey));
    }
    @Test
    void calcPerimeterSymbolHeightThrowsNumberFormatException(){
        CalculationService calculationService = new CalculationService();
        ParametersKey parametersKey = new ParametersKey("1","A");
        assertThrows(NumberFormatException.class,() -> calculationService.calcPerimeter(parametersKey));
    }
    @Test
    void calcPerimeterZeroLengthThrowsInvalidParametersException(){
        CalculationService calculationService = new CalculationService();
        ParametersKey parametersKey = new ParametersKey("0","1");
        assertThrows(InvalidParametersException.class,() ->calculationService.calcPerimeter(parametersKey));
    }
    @Test
    void calcSquareFourLengthTwoHeightEqualsEight(){
        CalculationService calculationService = new CalculationService();
        ParametersKey parametersKey = new ParametersKey("4","2");
        assertEquals("8",calculationService.calcSquare(parametersKey));
    }
    @Test
    void calcSquareNegativeOneHeightThrowsInvalidParametersException() {
        CalculationService calculationService = new CalculationService();
        ParametersKey parametersKey = new ParametersKey("1","-1");
        assertThrows(InvalidParametersException.class,() -> calculationService.calcSquare(parametersKey));
    }
    @Test
    void calcSquareSymbolLengthThrowsNumberFormatException(){
        CalculationService calculationService = new CalculationService();
        ParametersKey parametersKey = new ParametersKey("A","1");
        assertThrows(NumberFormatException.class,() ->calculationService.calcSquare(parametersKey));
    }
    @Test
    void calcSquareZeroHeightThrowsInvalidParametersException(){
        CalculationService calculationService = new CalculationService();
        ParametersKey parametersKey = new ParametersKey("1","0");
        assertThrows(InvalidParametersException.class,() ->calculationService.calcSquare(parametersKey));
    }
}