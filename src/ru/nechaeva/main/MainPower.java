package ru.nechaeva.main;
//точка входа, которая получает аргументы от пользователя, вызывает метод и выводит результат.
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
