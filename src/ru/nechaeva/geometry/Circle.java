package ru.nechaeva.geometry;

public class Circle extends Figure {

    private Point center;
    private double radius;

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "Круг: центр=" + center + ", радиус=" + radius;
    }
}
