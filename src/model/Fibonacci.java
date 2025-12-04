package model;

/**
 * @author Vincent Velthuizen
 * Shows recursion and how it can go wrong
 */
public class Fibonacci {

    static public long fibonacci(int n) {
        if (n <= 2) {
            return 1;
        }

        return fibonacci(n - 2) + fibonacci(n - 1);
    }

    static public long fibonacciIterative(int n) {
        long[] fibonacci = new long[n + 1];

        fibonacci[0] = 0;
        fibonacci[1] = 1;

        for (int i = 2; i <= n; i++) {
            fibonacci[i] = fibonacci[i - 2] + fibonacci[i - 1];

            System.out.printf("%s\n", (double) fibonacci[i] / fibonacci[i - 1]);
        }

        return fibonacci[n];
    }
}
