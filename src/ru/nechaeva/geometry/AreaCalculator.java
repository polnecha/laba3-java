package ru.nechaeva.geometry;

public class AreaCalculator {

    // Метод считает сумму площадей набора фигур
    public static double totalArea(Figure[] figures) {
        double sum = 0;

        for (Figure f : figures) {
            if (f != null) {
                sum += f.getArea();
            }
        }

        return sum;
    }
}
