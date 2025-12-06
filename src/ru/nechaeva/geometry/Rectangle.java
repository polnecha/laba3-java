package ru.nechaeva.geometry;

public class Rectangle extends Figure {

    private Point topLeft;
    private double width;
    private double height;

    public Rectangle(Point topLeft, double width, double height) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public String toString() {
        return "Прямоугольник: точка=" + topLeft + ", ширина=" + width + ", высота=" + height;
    }
}
