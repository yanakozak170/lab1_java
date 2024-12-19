import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Введення розмірів матриці
            int rows = 0, cols = 0;
            while (true) {
                try {
                    System.out.println("Введіть кількість рядків матриці:");
                    rows = scanner.nextInt();
                    if (rows <= 0) throw new IllegalArgumentException("Кількість рядків повинна бути додатнім числом.");
                    break;
                } catch (InputMismatchException e) {
                    System.err.println("Помилка: введіть ціле число для кількості рядків.");
                    scanner.next(); // Очистка буфера
                }
            }

            while (true) {
                try {
                    System.out.println("Введіть кількість стовпців матриці:");
                    cols = scanner.nextInt();
                    if (cols <= 0) throw new IllegalArgumentException("Кількість стовпців повинна бути додатнім числом.");
                    break;
                } catch (InputMismatchException e) {
                    System.err.println("Помилка: введіть ціле число для кількості стовпців.");
                    scanner.next(); // Очистка буфера
                }
            }

            // Введення матриць A і B
            System.out.println("Введіть елементи матриці A:");
            short[][] matrixA = inputMatrix(scanner, rows, cols);

            System.out.println("Введіть елементи матриці B:");
            short[][] matrixB = inputMatrix(scanner, rows, cols);

            // Додавання матриць
            short[][] matrixC = addMatrices(matrixA, matrixB);
            System.out.println("Матриця C (результат додавання A + B):");
            printMatrix(matrixC);

            // Обчислення суми найбільших елементів кожного рядка матриці C
            int sumOfMaxElements = calculateSumOfMaxElements(matrixC);
            System.out.println("Сума найбільших елементів кожного рядка матриці C: " + sumOfMaxElements);

        } catch (IllegalArgumentException e) {
            System.err.println("Помилка: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Несподівана помилка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Метод для введення матриці з консолі
    public static short[][] inputMatrix(Scanner scanner, int rows, int cols) {
        short[][] matrix = new short[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                while (true) {
                    try {
                        System.out.printf("Елемент [%d][%d]: ", i, j);
                        matrix[i][j] = scanner.nextShort();
                        break;
                    } catch (InputMismatchException e) {
                        System.err.println("Помилка: введіть коротке ціле число (short) для елемента матриці.");
                        scanner.next(); // Очистка буфера
                    }
                }
            }
        }
        return matrix;
    }

    // Метод для додавання двох матриць
    public static short[][] addMatrices(short[][] matrixA, short[][] matrixB) {
        if (matrixA.length != matrixB.length || matrixA[0].length != matrixB[0].length) {
            throw new IllegalArgumentException("Розміри матриць не співпадають.");
        }

        int rows = matrixA.length;
        int cols = matrixA[0].length;
        short[][] result = new short[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = (short) (matrixA[i][j] + matrixB[i][j]);
            }
        }
        return result;
    }

    // Метод для обчислення суми найбільших елементів кожного рядка матриці
    public static int calculateSumOfMaxElements(short[][] matrix) {
        int sum = 0;

        for (short[] row : matrix) {
            short max = row[0];
            for (short value : row) {
                if (value > max) {
                    max = value;
                }
            }
            sum += max;
        }
        return sum;
    }

    // Метод для виведення матриці на екран
    public static void printMatrix(short[][] matrix) {
        for (short[] row : matrix) {
            for (short value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}