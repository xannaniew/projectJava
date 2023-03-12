package com.bsuir.laboratoryWork.project.model;

import com.bsuir.laboratoryWork.project.exceptions.InvalidParametersException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculationProcessorTest {

    @Test
    void setNegativeOneLengthThrowsInvalidParametersException() {
        CalculationProcessor calculationProcessor = new CalculationProcessor();
        assertThrows(InvalidParametersException.class,() ->{
            calculationProcessor.setLength("-1");
        });
    }

    @Test
    void setSymbolHeightThrowsNumberFormatException() {
        CalculationProcessor calculationProcessor = new CalculationProcessor();
        assertThrows(NumberFormatException.class,() ->{
            calculationProcessor.setHeight("A");
        });
    }
}