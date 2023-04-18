package com.bsuir.laboratoryWork.project.service;

import com.bsuir.laboratoryWork.project.model.CalculationResult;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;
@Service
public class BulkCalculationService {

    private static final Supplier<CalculationResult> defaultCalculationResult = () -> new CalculationResult(0,0);
    private static final Comparator<CalculationResult> perimeterComparator = (left, right) -> left.getPerimeter() - right.getPerimeter();
    private static final Comparator<CalculationResult> squareComparator = (left, right) -> left.getSquare() - right.getSquare();
    private static final DoubleSupplier defaultAverageResult = () -> 0.0;
    public int findMinBySquare(List<CalculationResult> responseList){
        return responseList.stream()
                .min(squareComparator)
                .orElseGet(defaultCalculationResult)
                .getSquare();
    }
    public int findMaxByPerimeter(List<CalculationResult> responseList){
        return responseList.stream()
                .max(perimeterComparator)
                .orElseGet(defaultCalculationResult)
                .getPerimeter();
    }
    public double calcAverageResult(List<CalculationResult> responseList){
        return responseList.stream()
                .mapToInt(obj -> obj.getPerimeter() + obj.getSquare())
                .average()
                .orElseGet(defaultAverageResult);
    }
}
