package ru.nechaeva.students;

import java.util.Arrays;

public class StudentRange extends Student {

    public StudentRange(String name, int... grades) {
        super(name); // вызываем конструктор родителя, чтобы имя сохранилось
        setGradesChecked(grades); // задаём оценки с проверкой диапазона
    }

    // Метод для добавления одной новой оценки
    public void addGrade(int grade) {
        checkGrade(grade);
        int[] oldGrades = getGrades();
        int[] newGrades = Arrays.copyOf(oldGrades, oldGrades.length + 1);
        newGrades[newGrades.length - 1] = grade;
        super.setGrades(newGrades);
    }

    // Метод для установки сразу нескольких оценок с проверкой диапазона
    public void setGradesChecked(int[] grades) {
        for (int g : grades) {
            checkGrade(g); // проверяем каждую оценку
        }
        super.setGrades(grades.clone()); // сохраняем копию массива, чтобы нельзя было изменить его снаружи
    }

    private void checkGrade(int grade) {
        if (grade < 2 || grade > 5) {
            throw new IllegalArgumentException("Оценка должна быть в диапазоне от 2 до 5!");
        }
    }

    // toString() наследуем из ru.nechaeva.students.Student
}
