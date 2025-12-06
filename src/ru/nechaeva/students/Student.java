package ru.nechaeva.students;

import java.util.Arrays;

public class Student {
    private String name;
    private int[] grades;

    // Конструктор: имя + оценки
    public Student(String name, int... grades) {
        this.name = name;
        if (grades != null) {
            this.grades = Arrays.copyOf(grades, grades.length);
        } else {
            this.grades = new int[0];
        }
    }

    public String getName() {
        return name;
    }

    public int[] getGrades() {
        return grades;
    }

    public void setGrades(int[] grades) {
        this.grades = grades; // присваиваем сслыыку
    }

    //  копирование оценок (для Андрея)
    public void setGradesCopy(int[] grades) {

        this.grades = Arrays.copyOf(grades, grades.length);
    }
    // Средний балл
    public double average() {
        if (grades.length == 0) return 0.0;
        int sum = 0;
        for (int g : grades) sum += g;
        return (double) sum / grades.length;
    }

    // Проверка отличник ли студент
    public boolean isExcellent() {
        if (grades.length == 0) return false;
        for (int g : grades) {
            if (g < 5) return false; // если есть хотя бы одна не "5"
        }
        return true;
    }
    @Override
    public String toString() {
        if (grades.length == 0) {
            return name + ": оценок нет";
        } else {
            return name + ": " + Arrays.toString(grades);
        }    }
}
