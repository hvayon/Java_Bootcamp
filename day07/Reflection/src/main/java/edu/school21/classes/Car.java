package edu.school21.classes;

import java.util.StringJoiner;
public class Car {
    private String brand;
    private String color;
    private int mileage;

    public Car() {
        this.brand = "Default brand";
        this.color = "Default color";
        this.mileage = 0;
    }

    public Car(String brand, String color, int mileage) {
        this.brand = brand;
        this.color = color;
        this.mileage = mileage;
    }

    public int ride(int value) {
        this.mileage += value;
        return mileage;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
                .add("brand='" + brand + "'")
                .add(" color=''" + color + "'")
                .add("mileage=" + mileage)
                .toString();
    }
}

