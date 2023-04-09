package com.bsuir.laboratoryWork.project.service;

import com.bsuir.laboratoryWork.project.exceptions.InvalidParametersException;
import com.bsuir.laboratoryWork.project.model.ParametersKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculationServiceTest {
    @Test
    void calcPerimeterFiveLengthFourHeightEqualsTwenty(){
        CalculationService calculationService = new CalculationService();
        assertEquals("18",calculationService.calcPerimeter(5,4));
    }
    @Test
    void calcAndBuildResultrNegativeOneLengthThrowsInvalidParametersException() {
        CalculationService calculationService = new CalculationService();
        ParametersKey parametersKey = new ParametersKey("-1","1");
        assertThrows(InvalidParametersException.class,() -> calculationService.calcAndBuildResult(parametersKey));
    }
    @Test
    void calcAndBuildResultSymbolHeightThrowsNumberFormatException(){
        CalculationService calculationService = new CalculationService();
        ParametersKey parametersKey = new ParametersKey("1","A");
        assertThrows(NumberFormatException.class,() -> calculationService.calcAndBuildResult(parametersKey));
    }
    @Test
    void calcAndBuildResultZeroLengthThrowsInvalidParametersException(){
        CalculationService calculationService = new CalculationService();
        ParametersKey parametersKey = new ParametersKey("0","1");
        assertThrows(InvalidParametersException.class,() ->calculationService.calcAndBuildResult(parametersKey));
    }
}