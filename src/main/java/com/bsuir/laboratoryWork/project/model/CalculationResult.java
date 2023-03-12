package com.bsuir.laboratoryWork.project.model;

public class CalculationResult {
    private String perimeter;
    private String square;
    public CalculationResult(String perimeter, String square){
        this.perimeter = perimeter;
        this.square = square;
    }

    public String getPerimeter() {
        return perimeter;
    }

    public String getSquare() {
        return square;
    }

    public void setPerimeter(String perimeter) {
        this.perimeter = perimeter;
    }

    public void setSquare(String square) {
        this.square = square;
    }
}
