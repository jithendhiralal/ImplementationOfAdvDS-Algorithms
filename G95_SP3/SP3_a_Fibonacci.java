import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class to represent SP3_a_Fibonacci of n numbers using two different
 * Techniques s
 */
public class SP3_a_Fibonacci {

    /**
     * Method to find SP3_a_Fibonacci sum in Linear time
     *
     * @param n : No of elements to find
     * @param p : Base value to perform module
     * @return
     */
    public static long linearFibonacci(long n, long p) {
        int size = (int) n;
        long[] fib = new long[size + 1];
        fib[0] = 0;
        fib[1] = 1;
        int i;
        for (i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
            fib[i] = fib[i] % p;
        }

        return fib[size];
    }

    /**
     * Method to find SP3_a_Fibonacci sum in Log n Time
     *
     * @param n
     * @param p
     * @return
     */
    public static long logFibonacci(long n, long p) {

        int size = (int) n;
        long[][] fib = new long[][]{{1, 1}, {1, 0}};
        if (size == 0) {
            return 0;
        }
        power(fib, size - 1, p);

        return fib[0][0] % p;
    }

    /**
     * Helper method to calculate power of a matrix multiplication
     *
     * @param fib : Input array
     * @param n : power to be calculated
     * @param p : base value
     */
    private static void power(long[][] fib, long n, long p) {

        if (n == 0 || n == 1) {
            return;
        }
        long[][] temp = new long[][]{{1, 1}, {1, 0}};
        power(fib, n / 2, p);
        matrixMultiplication(fib, fib, p);

        if (n % 2 != 0) {
            matrixMultiplication(fib, temp, p);
        }

    }

    /**
     * Helper method to perform matrix multiplication in Constant time
     *
     * @param fib
     * @param temp
     * @param p
     * @return
     */
    private static long[][] matrixMultiplication(long[][] fib, long[][] temp, long p) {
        long x = (fib[0][0] * temp[0][0]) + (fib[0][1] * temp[1][0]);
        long y = (fib[0][0] * temp[0][1]) + (fib[0][1] * temp[1][1]);
        long z = (fib[1][0] * temp[0][0]) + (fib[1][1] * temp[1][0]);
        long v = (fib[1][0] * temp[0][1]) + (fib[1][1] * temp[1][1]);
        fib[0][0] = x % p;
        fib[0][1] = y % p;
        fib[1][0] = z % p;
        fib[1][1] = v % p;
        return fib;
    }

    /**
     * Driver method to
     *
     * @param args
     */
    public static void main(String[] args) throws FileNotFoundException {
        Timer time = new Timer();
        Scanner in;
        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }
        time.start();
        long n = in.nextLong();
        long base = 999953;
        long result1 = linearFibonacci(n, base);
        System.out.println(time.end());
        System.out.println("Fibonacci of " + n + " in O(n) " + result1);
        time.start();
        long result2 = logFibonacci(n, base);
        System.out.println(time.end());
        System.out.println("Fibonacci of " + n + " in O(Log n) " + result2);
    }

}
