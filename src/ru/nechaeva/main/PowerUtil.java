package ru.nechaeva.main;

import static java.lang.Integer.parseInt;
import static java.lang.Math.pow;
//Метод принимает строки → переводит в числа → возвращает X^Y.
public class PowerUtil {

    public static double power(String xStr, String yStr) {
        int x = parseInt(xStr);
        int y = parseInt(yStr);

        return pow(x, y);
    }
}
