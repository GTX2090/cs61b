package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    double[] ans;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        ans = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                p.open(row, col);
            }
            ans[i] = (double) p.numofOpen / (N * N);
        }
    }

    public double mean() {
        return StdStats.mean(ans);
    }

    public double stddev() {
        return StdStats.stddev(ans);
    }

    public double confidenceLow() {
        return this.mean() - 1.96 * this.stddev()/Math.sqrt(ans.length);
    }

    public double confidenceHigh() {
        return this.mean() + 1.96 * this.stddev()/Math.sqrt(ans.length);
    }

    public static void main (String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(20, 100, pf);
        System.out.println(ps.mean());
    }
}
