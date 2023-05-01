package com.bsuir.laboratoryWork.project.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class CalculationData implements Serializable/*, Persistable<ParametersKey>*/ {
    @EmbeddedId
    private ParametersKey id;

    public CalculationData(){}
    public CalculationData(ParametersKey id, int rectanglePerimeter, int rectangleSquare){
        this.id = id;
        this.rectanglePerimeter = rectanglePerimeter;
        this.rectangleSquare = rectangleSquare;
    }
    @Column(name = "rectanglePerimeter", nullable = false)
    private int rectanglePerimeter;
    @Column(name = "rectangleSquare", nullable = false)
    private int rectangleSquare;
    public void setRectanglePerimeter(int perimeter) {
        this.rectanglePerimeter = perimeter;
    }
    public void setRectangleSquare(int square) {
        this.rectangleSquare = square;
    }
    public int getRectanglePerimeter() {
        return rectanglePerimeter;
    }
    public int getRectangleSquare() {
        return rectangleSquare;
    }
    public ParametersKey getId(){
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalculationData data = (CalculationData) o;
        return Objects.equals(id,((CalculationData) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
