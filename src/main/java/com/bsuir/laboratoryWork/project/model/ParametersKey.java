package com.bsuir.laboratoryWork.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
@Embeddable
public class ParametersKey implements Serializable {
    @Column(name = "rectangleLength", nullable = false)
    private int rectangleLength;
    @Column(name = "rectangleHeight", nullable = false)
    private int rectangleHeight;
    public ParametersKey(int length, int height){
        this.rectangleHeight = height;
        this.rectangleLength = length;
    }
    public ParametersKey(){
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ParametersKey key = (ParametersKey) o;
        return ((Objects.equals(rectangleLength, key.rectangleLength) && Objects.equals(rectangleHeight, key.rectangleHeight)) ||
                (Objects.equals(rectangleLength,key.rectangleHeight) && Objects.equals(rectangleHeight,key.rectangleLength)));
    }

    @Override
    public int hashCode() {
        int sum = rectangleLength + rectangleHeight;
        int diff = rectangleLength ^ rectangleHeight; // симметрическая разница
        return sum * diff;
    }

    @Override
    public String toString() {
        return "[ " + rectangleLength + " " + rectangleHeight + " ]";
    }

    public int getRectangleHeight() {
        return rectangleHeight;
    }
    public int getRectangleLength() {
        return rectangleLength;
    }
    public void setRectangleHeight(int height) {
        this.rectangleHeight = height;
    }
    public void setRectangleLength(int length) {
        this.rectangleLength = length;
    }
}
