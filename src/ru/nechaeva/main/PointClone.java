package ru.nechaeva.main;
import java.util.Objects;

public class PointClone implements Cloneable{
    protected int x;
    protected int y;

    public PointClone(int x, int y) {
        this.x = x;
        this.y = y;
    }
    // Переопределяем equals() — сравниваем координаты
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PointClone other = (PointClone) obj;
        return this.x == other.x && this.y == other.y;
    }

    // Переопределяем hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "{" + x + ";" + y + "}";
    }
    @Override
    public PointClone clone() {
        return new PointClone(this.x, this.y);

}
}
