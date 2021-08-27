package bearmaps;
import java.util.*;
import edu.princeton.cs.algs4.Stopwatch;

public class TimingTest {
    private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        int[] ti = {31250, 62500, 125000, 250000, 500000, 1000000};
        ArrayList<Integer> Ns = new ArrayList<>(), opCounts = new ArrayList<>();
        ArrayList<Double> times = new ArrayList<>();
        for (int i : ti) {
            Ns.add(i);
            opCounts.add(i);
            Point[] kkk = new Point[i];
            for (int j = 0; j < i; j++) {
                kkk[j] = new Point(Math.random(), Math.random());
            }
            Point te = new Point(Math.random(), Math.random());
            KDTree nn = new KDTree(List.of(kkk));
            Stopwatch sw = new Stopwatch();
            Point ret = nn.nearest(te.getX(), te.getY());
            double timeInSeconds = sw.elapsedTime();
            times.add(timeInSeconds);
        }
        printTimingTable(Ns, times, opCounts);
        return;
    }
}
