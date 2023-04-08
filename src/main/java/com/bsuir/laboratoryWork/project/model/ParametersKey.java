package com.bsuir.laboratoryWork.project.model;

import java.util.Objects;

public class ParametersKey {
    private String length;
    private String height;
    public ParametersKey(String length, String height){
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
        return Objects.hash(length,height);
    }

    @Override
    public String toString() {
//        return ("KEY =[length = " + length + "; height = " + height + ']');
        return "HashCode[ " + this.hashCode() + " ]";
    }

    public String getHeight() {
        return height;
    }
    public String getLength() {
        return length;
    }
}
