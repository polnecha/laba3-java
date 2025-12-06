package ru.nechaeva.geometry;

public class Point3D extends Point {

    private int z;

    public Point3D(int x, int y, int z) {
        super(x, y);  // вызываем конструктор родителя
        this.z = z;
    }

    @Override
    public String toString() {
        return "{" + x + ";" + y + ";" + z + "}";
    }
}
