package simpleTest;

import org.testng.Assert;
import org.testng.annotations.*;

public class SimpleTests {

    @Test
    public void SimpleTests() {
    }

    @DataProvider(name = "Task1_Abs_Data")
    public Object[][] data_prov1() {
        return new Object[][]{{Integer.MAX_VALUE}, {Integer.MIN_VALUE}, {Integer.MAX_VALUE - 1}, {Integer.MIN_VALUE + 1}, {0}};
    }

    //Проверка работы включает в себя 0, максимальные и минимальные значения, а также их граничные
    @Test(dataProvider = "Task1_Abs_Data")
    public void Task1_Abs(int n) {
        System.out.println("Тест 1. Позитивный тест ");

        int res = Math.abs(n);
        try {

            System.out.println("n = " + n);
            System.out.println("Модуль n = " + res);
            Assert.assertEquals(res, n = n < 0 ? -n : n);
            Assert.assertFalse(res == -2147483648, "Особая ситуация");
        } catch (AssertionError e) {
            System.out.println("Текущее исключение: " + e);
            Assert.assertEquals(e.toString(), "java.lang.AssertionError: Особая ситуация expected [false] but found [true]");
        }
        System.out.println();
    }


    @DataProvider(name = "Task2_AddExact_Data1")
    public Object[][] data_prov3() {
        double max = Integer.MAX_VALUE - 1;
        double min = Integer.MIN_VALUE + 1;
        int n = (int) (Math.random() * max);
        int m = (int) (Math.random() * min);
        return new Object[][]
                {
                        {n, m}
                };
    }

    //проверка сложения двух значений
    @Test(dataProvider = "Task2_AddExact_Data1")
    public void Task2_AddExact1(int n, int m) {
        System.out.println("Задание 2. Проверка функции Math.addExact");
        //нормальные условия
        int sum = Math.addExact(n, m);
        Assert.assertEquals(sum, n + m);
        System.out.println("Значение с помощью addExact = " + sum);
        System.out.println("Проверочное значение = " + (n + m));
        System.out.println();
    }

    @DataProvider(name = "Task2_AddExact_Data2")
    public Object[][] data_prov4() {
        double max = Integer.MAX_VALUE - 1;
        int n = 1 + (int) (Math.random() * max) + 1;
        return new Object[][]
                {
                        {n}
                };
    }

    //положительное переполнение
    @Test(dataProvider = "Task2_AddExact_Data2")
    public void Task2_AddExact2(int m) {
        System.out.println("Задание 2. Проверка переполнения с положительными числами");
        // проверка на переполнение типа int с положительными числами
        // MAX_VALUE - максимальное значение для типа int
        int n = Integer.MAX_VALUE;
        try {
            int sum = Math.addExact(n, m);
        } catch (RuntimeException e) {
            System.out.println("Ожидаем: integer overflow");
            System.out.println("Исключение: " + e);
            Assert.assertEquals(e.toString(), "java.lang.ArithmeticException: integer overflow");
        }
        System.out.println();
    }


    @DataProvider(name = "Task2_AddExact_Data3")
    public Object[][] data_prov5() {
        double min = Integer.MIN_VALUE + 1;
        int n = (int) (Math.random() * min) - 1;
        return new Object[][]
                {
                        {n}
                };
    }

    //отрицательное переполнение
    @Test(dataProvider = "Task2_AddExact_Data3")
    public void Task2_AddExact3(int m) {
        System.out.println("Задание 2. Проверка переполнения с отрицательными числами");
        // проверка на переполнение типа int с отрицательными числами
        // MIN_VALUE - минимальное значение для типа int
        int n = Integer.MIN_VALUE;
        try {
            int sum = Math.addExact(n, m);
        } catch (RuntimeException e) {
            System.out.println("Ожидаем: integer overflow");
            System.out.println("Исключение: " + e);
            Assert.assertEquals(e.toString(), "java.lang.ArithmeticException: integer overflow");
        }
        System.out.println();
    }


    @DataProvider(name = "Task3.1_FloorDiv_Data")
    public Object[][] data_prov6() {
        double max = Integer.MAX_VALUE - 1;
        int n = 1 + (int) (Math.random() * max) + 1;
        int m = 1 + (int) (Math.random() * max) + 1;
        int result = n / m;
        return new Object[][]
                {
                        {n, m, result}
                };
    }

    //получение корректного остатка
    @Test(dataProvider = "Task3.1_FloorDiv_Data")
    public void Task3_FloorDiv1(int n, int m, int res) {
        System.out.println("Задание 3.1 Проверка функции Math.floorDiv");
        //корректные значения
        int result = Math.floorDiv(n, m);
        System.out.println("Первое число = " + n + "; Второе число = " + m);
        System.out.println("Результат floorDiv: " + result);
        System.out.println("Проверка: " + res);
        Assert.assertEquals(result, res);
        System.out.println();
    }


    @DataProvider(name = "Task3.2_FloorDiv_Data")
    public Object[][] data_prov7() {
        double max = Integer.MAX_VALUE - 1;
        double min = Integer.MIN_VALUE + 1;
        int n = (int) (Math.random() * max);
        int m = (int) (Math.random() * min);
        int s = n + m;
        return new Object[][]
                {
                        {s}
                };
    }

    //тестирование деления на 0
    @Test(dataProvider = "Task3.2_FloorDiv_Data")
    public void Task3_FloorDiv2(int n) {
        System.out.println("Задание 3.2 Проверка функции Math.floorDiv c делением на ноль");

        int m = 0;
        try {
            Math.floorDiv(n, m);
        } catch (Throwable e) {
            System.out.println("Ожидаем: исключение (by zero)");
            System.out.println("Исключение: " + e);
            Assert.assertEquals(e.toString(), "java.lang.ArithmeticException: / by zero");
        }
        System.out.println();

    }
}