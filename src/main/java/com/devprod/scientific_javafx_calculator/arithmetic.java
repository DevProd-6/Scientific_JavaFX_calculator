package com.devprod.scientific_javafx_calculator;

public class arithmetic {
    static String calculateEquation (double a, double b, String operator) {
        switch (operator) {
            case "＋":
                return "" + (a + b);
            case "−":
                return "" + (a - b);
            case "÷":
                return "" + (a / b);
            case "×":
                return "" + (a * b);
        }
        return "";
    }
}
