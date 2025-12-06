package ru.nechaeva.geometry;

public class Square extends Figure {

    private Point topLeft;
    private double side;

    public Square(Point topLeft, double side) {
        this.topLeft = topLeft;
        this.side = side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

     //Для 5 задания
     //Возвращает ЗАМКНУТУЮ ломаную линию квадрата

    public Polyline getPolyline() {
        Polyline p = new Polyline();
        Point p1 = topLeft;
        Point p2 = new Point(topLeft.x + (int)side, topLeft.y);
        Point p3 = new Point(topLeft.x + (int)side, topLeft.y + (int)side);
        Point p4 = new Point(topLeft.x, topLeft.y + (int)side);

        p.addPoint(p1);
        p.addPoint(p2);
        p.addPoint(p3);
        p.addPoint(p4);

        p.addPoint(p1);   // ЗАМЫКАНИЕ ЛОМАНОЙ

        return p;
    }

    @Override
    public String toString() {
        return "Квадрат: верхняя левая точка=" + topLeft + ", сторона=" + side;
    }
}
