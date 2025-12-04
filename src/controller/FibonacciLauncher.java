package controller;

import model.Fibonacci;

/**
 * @author Vincent Velthuizen
 * Purpose for the class
 */
public class FibonacciLauncher {

    public static void main(String[] args) {
        for (int i = 15; i < 55; i++) {
            long startTimeNano = System.nanoTime();
            long result = Fibonacci.fibonacciIterative(i);
            long endTimeNano = System.nanoTime();
            long duration = endTimeNano - startTimeNano;

            System.out.printf("fib %2d: %6d in: %8.3f\n", i, result, duration / 1000.0);
        }
    }

}
