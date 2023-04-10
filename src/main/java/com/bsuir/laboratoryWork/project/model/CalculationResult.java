package com.bsuir.laboratoryWork.project.model;

public class CalculationResult {
    private int perimeter;
    private int square;
    public CalculationResult(int perimeter, int square){
        this.perimeter = perimeter;
        this.square = square;
    }
    public CalculationResult(){
        this.perimeter = this.square = 0;
    }

    @Override
    public String toString() {
        return ("(perimeter = " + perimeter + "; square = " + square + ')');
    }

    public int getPerimeter() {
        return perimeter;
    }

    public int getSquare() {
        return square;
    }

    public void setPerimeter(int perimeter) {
        this.perimeter = perimeter;
    }

    public void setSquare(int square) {
        this.square = square;
    }
}
