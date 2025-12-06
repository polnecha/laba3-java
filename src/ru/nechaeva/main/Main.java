package ru.nechaeva.main;

import ru.nechaeva.geometry.*;
import ru.nechaeva.phonebook.PhoneBook;
import ru.nechaeva.students.Student;
import ru.nechaeva.students.StudentRange;


import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n        МЕНЮ        ");
            System.out.println("1. Работа с диапазоном оценок");
            System.out.println("2. Телефонный справочник");
            System.out.println("3. Работа с трёхмерными точками");
            System.out.println("4. Работа с фигурами");
            System.out.println("5. Общая площадь нескольких фигур");
            System.out.println("6. Замкнутый квадрат");
            System.out.println("7. Проверка сравнения точек (equals)");
            System.out.println("8. Проверка клонирования точки");
            System.out.println("0. Выход");
            int choice = InputUtils.readInt("Выберите пункт меню: ");

            switch (choice) {

                case 1 -> runEncapsulationTask();
                case 2 -> runPhoneBookTask();
                case 3 -> run3DPointsTask();
                case 4 -> runFiguresTask();
                case 5 -> runTotalAreaTask();
                case 6 -> runClosedSquareTask();
                case 7 -> runPointEqualsTest();
                case 8 -> runPointCloneTest();
                case 0 -> {
                    System.out.println("Выход из программы...");
                    return;
                }
                default -> System.out.println("Ошибка! Нет такого пункта меню.");
            }
        }
    }


    private static void printStudentInfo(Student student) {
        System.out.println("\n" + student);
        System.out.printf("Средний балл: %.2f%n", student.average());
        System.out.println("Отличник: " + (student.isExcellent() ? "Да" : "Нет"));
    }
    private static void runEncapsulationTask() {
        System.out.println("\n--- Проверка диапазона оценок (инкапсуляция) ---");

        String name = InputUtils.readName("Введите имя студента: ");
        StudentRange st = new StudentRange(name);

        while (true) {
            try {
                int grade = InputUtils.readInt("Введите оценку (2–5) или 0 для завершения: ");
                if (grade == 0) break;
                st.addGrade(grade); // добавление с проверкой
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        // Используем уже существующий метод для вывода информации
        printStudentInfo(st);
    }
    private static void runPhoneBookTask() {
        System.out.println("\n--- Телефонный справочник ---");
        PhoneBook pb = new PhoneBook();

        while (true) {
            System.out.println("\n1. Добавить контакт");
            System.out.println("2. Удалить контакт");
            System.out.println("3. Найти телефон по имени");
            System.out.println("4. Проверить наличие имени");
            System.out.println("5. Проверить наличие телефона");
            System.out.println("6. Показать все пары");
            System.out.println("7. Найти имена по началу");
            System.out.println("0. Назад");

            int c = InputUtils.readInt("Ваш выбор: ");

            switch (c) {
                case 1 -> {
                    String name = InputUtils.readName("Имя: ");
                    String phone = InputUtils.readString("Телефон: ");
                    String old = pb.add(phone, name);
                    if (old != null)
                        System.out.println("Телефон заменён. Старый: " + old);
                }
                case 2 -> {
                    String name = InputUtils.readName("Имя для удаления: ");
                    pb.remove(name);
                    System.out.println("Удалено (если существовало).");
                }
                case 3 -> {
                    String name = InputUtils.readName("Имя: ");
                    System.out.println("Телефон: " + pb.getPhone(name));
                }
                case 4 -> {
                    String name = InputUtils.readName("Имя: ");
                    System.out.println("Есть?: " + pb.containsName(name));
                }
                case 5 -> {
                    String phone = InputUtils.readString("Телефон: ");
                    System.out.println("Есть?: " + pb.containsPhone(phone));
                }
                case 6 -> System.out.println(pb);
                case 7 -> {
                    String prefix = InputUtils.readString("Введите начало имени: ");
                    System.out.println(Arrays.toString(pb.getNamesByPrefix(prefix)));
                }
                case 0 -> { return; }
                default -> System.out.println("Ошибка!");
            }
        }

    }
    private static void run3DPointsTask() {
        System.out.println("Создание трёхмерных точек:");

        Point3D p1 = new Point3D(
                InputUtils.readInt("Введите X для точки 1: "),
                InputUtils.readInt("Введите Y для точки 1: "),
                InputUtils.readInt("Введите Z для точки 1: ")
        );

        Point3D p2 = new Point3D(
                InputUtils.readInt("Введите X для точки 2: "),
                InputUtils.readInt("Введите Y для точки 2: "),
                InputUtils.readInt("Введите Z для точки 2: ")
        );

        System.out.println("\nТрёхмерные точки:");
        System.out.println(p1);
        System.out.println(p2);
    }
    private static void runFiguresTask() {
        System.out.println("\n--- Работа с фигурами ---");

        System.out.println("1. Создать круг");
        System.out.println("2. Создать квадрат");
        System.out.println("3. Создать прямоугольник");
        System.out.println("4. Создать треугольник");

        int choice = InputUtils.readInt("Выберите фигуру: ");

        Figure fig = null;

        switch (choice) {
            case 1 -> {
                int x = InputUtils.readInt("Введите X центра: ");
                int y = InputUtils.readInt("Введите Y центра: ");
                double r = InputUtils.readDouble("Введите радиус: ");
                fig = new Circle(new Point(x, y), r);
            }
            case 2 -> {
                int x = InputUtils.readInt("Введите X верхней левой точки: ");
                int y = InputUtils.readInt("Введите Y верхней левой точки: ");
                double s = InputUtils.readDouble("Введите длину стороны: ");
                fig = new Square(new Point(x, y), s);
            }
            case 3 -> {
                int x = InputUtils.readInt("Введите X верхней левой точки: ");
                int y = InputUtils.readInt("Введите Y верхней левой точки: ");
                double w = InputUtils.readDouble("Введите ширину: ");
                double h = InputUtils.readDouble("Введите высоту: ");
                fig = new Rectangle(new Point(x, y), w, h);
            }
            case 4 -> {
                System.out.println("Введите 3 точки:");
                Point p1 = new Point(InputUtils.readInt("X1: "), InputUtils.readInt("Y1: "));
                Point p2 = new Point(InputUtils.readInt("X2: "), InputUtils.readInt("Y2: "));
                Point p3 = new Point(InputUtils.readInt("X3: "), InputUtils.readInt("Y3: "));
                fig = new Triangle(p1, p2, p3);
            }
            default -> {
                System.out.println("Нет такого варианта!");
                return;
            }
        }

        System.out.println("\nСоздана фигура:");
        System.out.println(fig);
        System.out.printf("Площадь: %.2f%n", fig.getArea());
    }
    private static void runTotalAreaTask() {
        System.out.println("\n--- Общая площадь фигур ---");

        int count = InputUtils.readInt("Сколько фигур хотите создать? ");
        Figure[] figs = new Figure[count];

        for (int i = 0; i < count; i++) {
            System.out.println("\nФигура #" + (i + 1));
            System.out.println("1. Круг");
            System.out.println("2. Квадрат");
            System.out.println("3. Прямоугольник");
            System.out.println("4. Треугольник");

            int type = InputUtils.readInt("Выберите тип фигуры: ");
            switch (type) {
                case 1 -> {
                    int x = InputUtils.readInt("X центра: ");
                    int y = InputUtils.readInt("Y центра: ");
                    double r = InputUtils.readDouble("Радиус: ");
                    figs[i] = new Circle(new Point(x, y), r);
                }
                case 2 -> {
                    int x = InputUtils.readInt("X верхней левой точки: ");
                    int y = InputUtils.readInt("Y верхней левой точки: ");
                    double s = InputUtils.readDouble("Сторона: ");
                    figs[i] = new Square(new Point(x, y), s);
                }
                case 3 -> {
                    int x = InputUtils.readInt("X верхней левой точки: ");
                    int y = InputUtils.readInt("Y верхней левой точки: ");
                    double w = InputUtils.readDouble("Ширина: ");
                    double h = InputUtils.readDouble("Высота: ");
                    figs[i] = new Rectangle(new Point(x, y), w, h);
                }
                case 4 -> {
                    System.out.println("Введите 3 точки:");
                    Point p1 = new Point(InputUtils.readInt("X1: "), InputUtils.readInt("Y1: "));
                    Point p2 = new Point(InputUtils.readInt("X2: "), InputUtils.readInt("Y2: "));
                    Point p3 = new Point(InputUtils.readInt("X3: "), InputUtils.readInt("Y3: "));
                    figs[i] = new Triangle(p1, p2, p3);
                }
                default -> {
                    System.out.println("Ошибка! Фигура пропущена.");
                    i--; // повторяем текущий индекс
                }
            }
        }

        double sum = AreaCalculator.totalArea(figs); // вызываем метод подсчета общей площади
        System.out.println("\nОбщая площадь всех фигур: " + sum);
    }

    private static void runClosedSquareTask() {
        System.out.println("\n--- Замкнутый квадрат ---");

        int x = InputUtils.readInt("Введите X верхней левой точки квадрата: ");
        int y = InputUtils.readInt("Введите Y верхней левой точки квадрата: ");
        double side = InputUtils.readDouble("Введите длину стороны: ");

        Square square = new Square(new Point(x, y), side);

        Polyline poly = square.getPolyline(); // вызываем тот же метод, но он уже замкнутый

        System.out.println("Замкнутая ломаная квадрата:");
        System.out.println(poly);
    }
    private static void runPointEqualsTest() {
        System.out.println("\n--- Проверка equals для Point ---");

        int x1 = InputUtils.readInt("Введите X первой точки: ");
        int y1 = InputUtils.readInt("Введите Y первой точки: ");
        Point p1 = new Point(x1, y1);

        int x2 = InputUtils.readInt("Введите X второй точки: ");
        int y2 = InputUtils.readInt("Введите Y второй точки: ");
        Point p2 = new Point(x2, y2);

        System.out.println("Точка 1: " + p1);
        System.out.println("Точка 2: " + p2);

        if (p1.equals(p2)) {
            System.out.println("Результат: точки равны!");
        } else {
            System.out.println("Результат: точки НЕ равны.");
        }
    }
    private static void runPointCloneTest() {
        System.out.println("\n--- Проверка клонирования точки ---");

        int x = InputUtils.readInt("Введите X точки: ");
        int y = InputUtils.readInt("Введите Y точки: ");
        PointClone original = new PointClone(x, y);

        PointClone clone = original.clone();  // вызываем метод clone()

        System.out.println("Исходная точка: " + original);
        System.out.println("Клонированная точка: " + clone);
        System.out.println("original и clone это один объект? " + (original == clone));  // false
        System.out.println("original и clone равны по содержимому? " + original.equals(clone)); // true

        // Меняем клона, чтобы проверить независимость
        clone.x = clone.x + 10;
        clone.y = clone.y + 10;
        System.out.println("После изменения клона:");
        System.out.println("Исходная точка: " + original);
        System.out.println("Клон: " + clone);
    }

}
