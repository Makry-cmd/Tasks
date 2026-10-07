package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class CalculatorTest {

    @Test
    void testAdding() {
        int result = Calculator.add(10, 25);
        assertEquals(35, result, "Сумма должна быть 35");
    }

    @Test
    void testSubtract() {
        int result = Calculator.subtract(15, 5);
        assertEquals(10, result, "Разность должна быть 10");
    }

    @Test
    void testMultiply() {
        int result = Calculator.multiply(3, 5);
        assertEquals(15, result, "Умножение должно быть 15");
    }

    @Test
    void testDivide() {
        double result = Calculator.divide(20, 4);
        assertEquals(5, result, "Деление должно быть 5");
    }

    @Test
    void divisionByZero() {
        assertThrows(ArithmeticException.class, () -> Calculator.divide(10, 0));
    }
}

