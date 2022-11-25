import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

    private Percolation test;
    private int row;
    private int col;
    private double[] countArray;
    private int trials;

    public PercolationStats(int n, int trials) {
        if (n <= 1 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        this.trials = trials;
        countArray = new double[trials];
        for (int i = 0; i < trials; i++) {
            test = new Percolation(n);

            while (!test.percolates()) {
                row = (int) (StdRandom.uniformDouble() * n) + 1;
                col = (int) (StdRandom.uniformDouble() * n) + 1;

                if (!test.isOpen(row, col)) {
                    test.open(row, col);
                }
            }

            countArray[i] = (double) test.numberOfOpenSites() / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(countArray);
    }

    public double stddev() {
        return StdStats.stddev(countArray);
    }

    public double confidenceLo() {
        return mean() - (1.96 * stddev()) / Math.sqrt(trials);
    }

    public double confidenceHi() {
        return mean() + (1.96 * stddev()) / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        int a, b;
        a = StdIn.readInt();
        b = StdIn.readInt();

        try {
            PercolationStats test = new PercolationStats(a, b);
            StdOut.println("mean                    = " + test.mean());
            StdOut.println("stddev                  = " + test.stddev());
            StdOut.println("95% confidence interval = [" + test.confidenceLo() + ", " + test.confidenceHi() + "]");
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
}
