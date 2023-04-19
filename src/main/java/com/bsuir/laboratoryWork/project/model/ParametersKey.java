package com.bsuir.laboratoryWork.project.model;

import java.util.Objects;

public class ParametersKey {
    private int length;
    private int height;
    public ParametersKey(int length, int height){
        this.height = height;
        this.length = length;
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
        return ((Objects.equals(length, key.length) && Objects.equals(height, key.height)) ||
                (Objects.equals(length,key.height) && Objects.equals(height,key.length)));
    }

    @Override
    public int hashCode() {
        int sum = length + height;
        int diff = length ^ height; // симметрическая разница
        return sum * diff;
    }

    @Override
    public String toString() {
//        return ("KEY =[length = " + length + "; height = " + height + ']');
        return "HashCode[ " + this.hashCode() + " ]";
    }

    public int getHeight() {
        return height;
    }
    public int getLength() {
        return length;
    }
}
