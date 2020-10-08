import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class FibonacciImplementation {
    // Set the number of iterations or passes
    static int iterations = 20;
    static int runtimes = 1;

    static long startTime;
    static long endTime;
    static long totalTimeIterative;
    static long totalTimeRecursive;

    static FibonacciFunctions fibonacci = new FibonacciFunctions();

    static long[] recursive = new long[iterations];
    static long[] iterative = new long[iterations];

    public static void main(String[] args) throws IOException {
        for (int n = 1; n <= runtimes; n++) {
            for (int i = 0; i < iterations - 1; i++) {
                iterative[i] = executeIterative(i);
                recursive[i] = executeRecursive(i);
            }

            System.out.println("Recursive results");
            System.out.println(Arrays.toString(recursive));

            System.out.println("Iterative results");
            System.out.println(Arrays.toString(iterative));

            exportResults(recursive, iterative, n);
        }
    }

    /**
     * iterative method to calculate the Fibonacci sequence
     */
    private static long executeIterative(int n) {
        startTime = System.nanoTime();
        fibonacci.iterative(n);
        endTime = System.nanoTime();

        long totalTimeIterative = endTime - startTime;
        return totalTimeIterative;
    }

    /**
     * recursive method to calculate the Fibonacci sequence
     */
    private static long executeRecursive(int n) {
        startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            fibonacci.recursive(i);
        }
        endTime = System.nanoTime();

        long totalTimeRecursive = endTime - startTime;
        return totalTimeRecursive;
    }

    /**
     * Outputs the time results to csv for graphing the data
     */
    private static void exportResults(long[] recursive, long[] iterative, int n) throws IOException {
        String recursivefilename = "Fibonacci Results-Recursive-" + n + ".csv";
        String iterativefilename = "Fibonacci Results-Iterative-" + n + ".csv";

        BufferedWriter recursiveResults = new BufferedWriter(new FileWriter(recursivefilename));
        BufferedWriter iterativeResults = new BufferedWriter(new FileWriter(iterativefilename));

        StringBuilder sbR = new StringBuilder();
        StringBuilder sbI = new StringBuilder();

        for (long element : recursive) {
            sbR.append(element);
            sbR.append(",");
        }

        for (long element : iterative) {
            sbI.append(element);
            sbI.append(",");
        }

        recursiveResults.write(sbR.toString());
        recursiveResults.close();

        System.out.println();
        System.out.println(recursivefilename + " was saved.");
        System.out.println(totalTimeRecursive);

        iterativeResults.write(sbI.toString());
        iterativeResults.close();

        System.out.println(iterativefilename + " was saved.");
        System.out.println(totalTimeIterative);
    }
}