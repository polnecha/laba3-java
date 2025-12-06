# **Нечаева Полина ИТ-3,4**  
## **Лабораторная работа №3**

---

# **Задание 1. Инкапсуляция: private, геттеры и сеттеры, исключения**

## **Задача 9. Диапазон оценок**
### **Текст задачи**
Измените сущность **Студент** из задачи 1.5.6. Необходимо гарантировать, что добавлять студенту можно только оценки в диапазоне **от 2 до 5**, при этом у студента всегда можно узнать список оценок.  
Продемонстрируйте на примерах, что нет способа задать студенту некорректную оценку.

### **Алгоритм решения**

**Класс ru.nechaeva.students.StudentRange (наследник ru.nechaeva.students.Student, проверка оценок 2–5)**

```java
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


```
---

# **Задание 2. Разработка структур данных**

## **Задача 3. Желтые страницы**
### **Текст задачи**
Необходимо разработать сущность **ТелефонныйСправочник**, который будет использоваться для создания справочника со списком телефонов и имен, с целью поиска телефона по имени.  
Будем считать достаточным хранение пар телефон-строка, например “89003337788 - Вася”.  

Состояние сущности описывается набором пар “телефон – имя”, причем и телефон и имя являются строками.  
В целях упрощения задачи будем считать, что одному имени всегда соответствует один телефон, и один телефон соответствует одному имени.  

Инициализация сущности выполняется без аргументов.  

Поведение сущности описывают следующие действия:  
- Добавление новой пары “телефон – имя” (как два отдельных аргумента).  
  Конкретное местоположение добавленной пары не важно.  
  Если добавляемое имя уже есть в списке, то имеющийся телефон заменяется на новый, и затем старый телефон возвращается из метода.  
- Удаление значения. Для удаления необходимо указать имя удаляемого контакта, после чего удаляется вся пара.  
- Получение значений. Для получения необходимо указать имя контакта, тогда возвращается его телефон.  
- Может быть приведен к строке. Строка должна представлять собой полный перечень всех хранимых пар.  
- Можно проверить, есть ли конкретный телефон или конкретное имя в списке.  
- Можно узнать текущее количество контактов.  
- Можно запросить в виде массива:  
  - Все пары  
  - Все телефоны  
  - Все имена  
- Можно получить массив всех имен, указав часть названия (начиная с первого символа).  

Продемонстрируйте работоспособность решения на примерах.

### **Алгоритм решения**
**Класс ru.nechaeva.phonebook.PhoneBook**
```java
import java.util.*;

public class ru.nechaeva.phonebook.PhoneBook {

    private Map<String, String> nameToPhone = new HashMap<>();
    private Map<String, String> phoneToName = new HashMap<>();

    public ru.nechaeva.phonebook.PhoneBook() {}

    public String add(String phone, String name) {
        String oldPhone = nameToPhone.put(name, phone);
        if (oldPhone != null) phoneToName.remove(oldPhone);
        phoneToName.put(phone, name);
        return oldPhone;
    }

    public void remove(String name) {
        String phone = nameToPhone.remove(name);
        if (phone != null) phoneToName.remove(phone);
    }

    public String getPhone(String name) { return nameToPhone.get(name); }

    public boolean containsName(String name) { return nameToPhone.containsKey(name); }
    public boolean containsPhone(String phone) { return phoneToName.containsKey(phone); }
    public int size() { return nameToPhone.size(); }

    public String[] getAllPairs() {
        String[] arr = new String[nameToPhone.size()];
        int i = 0;
        for (var entry : nameToPhone.entrySet()) arr[i++] = entry.getKey() + " - " + entry.getValue();
        return arr;
    }

    public String[] getPhones() { return phoneToName.keySet().toArray(new String[0]); }
    public String[] getNames() { return nameToPhone.keySet().toArray(new String[0]); }

    public String[] getNamesByPrefix(String prefix) {
        List<String> list = new ArrayList<>();
        for (String name : nameToPhone.keySet()) if (name.startsWith(prefix)) list.add(name);
        return list.toArray(new String[0]);
    }

    @Override
    public String toString() { return String.join("\n", getAllPairs()); }
}

```

****
```java
```
---

# **Задание 3. Наследование**

## **Задача 5. Трехмерная точка**
### **Текст задачи**
Создайте такой подвид сущности **Точка** из задачи 1.1.1, которая будет иметь не две, а три координаты на плоскости: **X, Y, Z**.

### **Алгоритм решения**

**Класс ru.nechaeva.geometry.Point (2D)**

```java
public class ru.nechaeva.geometry.Point {
    protected int x;
    protected int y;

    public ru.nechaeva.geometry.Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "{" + x + ";" + y + "}";
    }
}

```
**Класс ru.nechaeva.geometry.Point3D (наследник ru.nechaeva.geometry.Point)**

```java


public class figures.Point3D extends

Point {

    private int z;  // третья координата

    public ru.nechaeva.geometry.Point3D( int x, int y, int z){
        super(x, y);  // вызываем конструктор родителя
        this.z = z;
    }

    @Override
    public String toString () {
        return "{" + x + ";" + y + ";" + z + "}";
    }
}

```
---

# **Задание 4. Создание иерархий (abstract)**

## **Задача 4. Фигуры**
### **Текст задачи**
Необходимо продумать структуру и организацию следующих геометрических фигур:  
1. **Круг** — задается точкой координат центра и радиусом.  
2. **Квадрат** — рекомендуется взять квадрат, полученный в задаче 2.1.3.  
3. **Прямоугольник** — задается точкой координат левого верхнего угла и двумя сторонами.  
4. **Треугольник** — задается тремя точками координат.  

Для каждой фигуры обязательно должна быть возможность рассчитать её площадь.
**Класс Figure**
### **Алгоритм решения**
```java
package ru.nechaeva.geometry;

public abstract class Figure {
    public abstract double getArea();

    @Override
    public abstract String toString();
}
```
**Класс Circle**
```java
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

```
**Класс Rectangle**
```java
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

```
**Класс Triangle**
```java
package ru.nechaeva.geometry;

public class Triangle extends Figure {

    private Point p1, p2, p3;

    public Triangle(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    //Вспомогательный метод: считает расстояние между двумя точками
    private double distance(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }
    //формула Герона
    @Override
    public double getArea() {
        double a = distance(p1, p2);
        double b = distance(p2, p3);
        double c = distance(p3, p1);

        double s = (a + b + c) / 2.0;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public String toString() {
        return "Треугольник: " + p1 + ", " + p2 + ", " + p3;
    }
}

```
**Класс Square**
```java
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

    @Override
    public String toString() {
        return "Квадрат: верхняя левая точка=" + topLeft + ", сторона=" + side;
    }

}
```

---

# **Задание 5. Полиморфизм: виртуальный вызов метода, interface, implements**

## **Задача 3. Общая площадь**
### **Текст задачи**
Разработайте метод, который принимает набор фигур из задачи 2.4.4 и считает их **общую площадь** (без учета возможного перекрытия фигурами друг друга).  
Продемонстрируйте работоспособность метода, передав туда несколько кругов и квадратов.

### **Алгоритм решения**


**AreaCalculator**
```java
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

```
---

## **Задача 6. Замкнутый квадрат**
### **Текст задачи**
Измените сущность **Квадрат** из задачи 2.4.4 таким образом, чтобы она возвращала не обычную ломаную линию, а **замкнутую ломаную линию** из задачи 2.3.2.  
При этом сигнатура метода, возвращающего ломаную, должна остаться без изменений.

### **Алгоритм решения**
**Класс Square**
```java
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

```

**Класс Polyline**
```java
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

```

---

# **Задание 6. Полиморфное сравнение: equals**

## **Задача 2. Сравнение точек**
### **Текст задачи**
Измените сущность **Точка** из задачи 1.4.1.  
Переопределите метод сравнения объектов по состоянию таким образом,
чтобы две точки считались одинаковыми тогда, когда они расположены в одинаковых координатах.

### **Алгоритм решения**

**Класс Point. Переопределение equals()**
```java

package ru.nechaeva.geometry;
import java.util.Objects;

public class Point {
    protected int x;
    protected int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //Для 6 задания
    // Переопределяем equals()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point other = (Point) obj;
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

}

```

---

# **Задание 7. Пакеты**

## **Задача 1. Навести порядок**
### **Текст задачи**
Данная задача предполагает реорганизацию ранее написанных классов.  
Расположите все ранее написанные классы по пакетам таким образом, чтобы логически близкие классы оказались сгруппированы друг с другом.  
Имена пакетов должны иметь как минимум трёхсоставную форму, вида:  
`ru.surname.type`  
Вместо *surname* следует подставить свою фамилию, а вместо *type* — название логического блока.  

Например, классы, описывающие точку, линию, ломаную линию, фигуру, квадрат, треугольник, круг и прямоугольник можно расположить в пакете `ru.surname.geometry`.

### **Алгоритм решения**

**Струтура проекта**
```java
laba3-java/
├─ src/
│  ├─ ru/
│     ├─ nechaeva/
│        ├─ geometry/
│        │  ├─ Point.java
│        │  ├─ Point3D.java
│        │  ├─ Figure.java
│        │  ├─ Circle.java
│        │  ├─ Rectangle.java
│        │  ├─ Square.java
│        │  ├─ Triangle.java
│        │  ├─ Polyline.java
│        │  └─ AreaCalculator.java
│        │
│        │
│        ├─ phonebook/
│        │  └─ PhoneBook.java
│        │
│        └─ students/
│           ├─ Student.java
│           └─ StudentRange.java

```
---

## **Задача 2. Главный метод**
### **Текст задачи**
Создайте пакет `ru.surname.main` (вместо *surname* необходимо подставить собственную фамилию),  
в котором расположить класс с точкой входа в исполнение программы (`public static void main`).  
Также следует проверить, что ни в одном другом пакете нет классов, имеющих точку входа в исполнение программы.  
В этом же пакете необходимо расположить класс (или интерфейс) с методами из задач блока 2.5 и продемонстрировать их работоспособность.

### **Алгоритм решения**

**Структура проекта**
```java
laba3-java/
├─ src/
│  ├─ ru/
│     ├─ nechaeva/
│        ├─ geometry/
│        │  ├─ Point.java
│        │  ├─ Point3D.java
│        │  ├─ Figure.java
│        │  ├─ Circle.java
│        │  ├─ Rectangle.java
│        │  ├─ Square.java
│        │  ├─ Triangle.java
│        │  ├─ Polyline.java
│        │  └─ AreaCalculator.java
│        │
│        ├─ main/
│        │  ├─ Main.java
│        │  ├─ MainPower.java
│        │  ├─ PowerUtil.java
│        │  ├─ PointClone.java
│        │  └─ InputUtils.java
│        │
│        ├─ phonebook/
│        │  └─ PhoneBook.java
│        │
│        └─ students/
│           ├─ Student.java
│           └─ StudentRange.java

```
---

## **Задача 3. Возведение в степень**
### **Текст задачи**
Создайте метод, принимающий две строки, в которых будут записаны числа **X** и **Y**.  
Возвращает метод результат возведения **X** в степень **Y**.  
Для преобразования строки в число следует использовать метод `Integer.parseInt`,  
а для возведения в степень — метод `Math.pow`.  
Вызовите разработанный метод, передав туда параметры командной строки, полученные точкой входа в программу.  
Реализуйте метод так, чтобы для возведения в степень и преобразования строки использовались короткие имена статических методов.

### **Алгоритм решения**

**Класс PowerUtil**
```java
package ru.nechaeva.main;

import static java.lang.Integer.parseInt;
import static java.lang.Math.pow;
public class PowerUtil {

    public static double power(String xStr, String yStr) {
        int x = parseInt(xStr);
        int y = parseInt(yStr);

        return pow(x, y);
    }
}
```
**Класс MainPower**
```java
package ru.nechaeva.main;
public class MainPower {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Ошибка: требуется два аргумента X и Y");
            return;
        }

        try {
            double result = PowerUtil.power(args[0], args[1]);
            System.out.println("Результат: " + result);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: аргументы должны быть числами");
        }
    }
}

```

---

# **Задание 8. Пакетная инкапсуляция: package-private, protected**

## **Задача 4. Клонирование точки**
### **Текст задачи**
Измените сущность **Точка** из 2.6.2.  
Переопределите метод клонирования, унаследованный от класса `Object`, таким образом, 
чтобы при его вызове возвращался новый объект **Точки**, 
значения полей которого будут копиями оригинальной точки.

### **Алгоритм решения**

---

**Класс PointClone**
```java
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

```
