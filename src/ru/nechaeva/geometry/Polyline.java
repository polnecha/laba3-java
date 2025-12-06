package ru.nechaeva.geometry;

import java.util.ArrayList;
import java.util.List;

public class Polyline {
    private List<Point> points = new ArrayList<>();

    public void addPoint(Point p) {
        points.add(p);
    }

    public List<Point> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return points.toString();
    }
}

