import java.util.ArrayList;
import java.util.List;

/**
 * Class that collects timing information about AList construction.
 */
public class TimeAList {
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
        int[] ti = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};
        ArrayList<Integer> Ns = new ArrayList<>(), opCounts = new ArrayList<>();
        ArrayList<Double> times = new ArrayList<>();
        for (int i : ti) {
            Ns.add(i);
            opCounts.add(i);
            AList<Integer> al_test = new AList<>();
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < i; j++) {
                al_test.addLast(j);
            }
            double timeInSeconds = sw.elapsedTime();
            times.add(timeInSeconds);
        }
        printTimingTable(Ns, times, opCounts);
        return;
    }


}
