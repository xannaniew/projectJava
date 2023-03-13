package com.bsuir.laboratoryWork.project.model;

import com.bsuir.laboratoryWork.project.exceptions.InvalidParametersException;
import com.bsuir.laboratoryWork.project.service.CalculationService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculationServiceTest {
    @Test
    void calcPerimeterFiveLengthFourHeightEqualsTwenty(){
        CalculationService calculationService = new CalculationService();
        assertEquals("18",calculationService.calcPerimeter("5","4"));
    }
    @Test
    void calcPerimeterNegativeOneLengthThrowsInvalidParametersException() {
        CalculationService calculationService = new CalculationService();
        assertThrows(InvalidParametersException.class,() -> calculationService.calcPerimeter("-1", "1"));
    }
    @Test
    void calcPerimeterSymbolHeightThrowsNumberFormatException(){
        CalculationService calculationService = new CalculationService();
        assertThrows(NumberFormatException.class,() -> calculationService.calcPerimeter("1","A"));
    }
    @Test
    void calcPerimeterZeroLengthThrowsInvalidParametersException(){
        CalculationService calculationService = new CalculationService();
        assertThrows(InvalidParametersException.class,() ->calculationService.calcPerimeter("0","1"));
    }
    @Test
    void calcSquareFourLengthTwoHeightEqualsEight(){
        CalculationService calculationService = new CalculationService();
        assertEquals("8",calculationService.calcSquare("4","2"));
    }
    @Test
    void calcSquareNegativeOneHeightThrowsInvalidParametersException() {
        CalculationService calculationService = new CalculationService();
        assertThrows(InvalidParametersException.class,() -> calculationService.calcSquare("1","-1"));
    }
    @Test
    void calcSquareSymbolLengthThrowsNumberFormatException(){
        CalculationService calculationService = new CalculationService();
        assertThrows(NumberFormatException.class,() ->calculationService.calcSquare("A","1"));
    }
    @Test
    void calcSquareZeroHeightThrowsInvalidParametersException(){
        CalculationService calculationService = new CalculationService();
        assertThrows(InvalidParametersException.class,() ->calculationService.calcSquare("1","0"));
    }
}