package bearmaps.proj2c;
import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.introcs.Stopwatch;

import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    SolverOutcome solveoutcome;
    List<Vertex> solution;
    int numStatesExplored = 0;
    double explorationTime;
    DoubleMapPQ<Vertex> pq = new DoubleMapPQ<>();
    HashMap<Vertex, Double> distTo = new HashMap<Vertex, Double>();
    double solutionweight;
    HashMap<Vertex, Vertex> to = new HashMap<Vertex, Vertex>();

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        solution = new ArrayList<>();
        Stopwatch sw = new Stopwatch();
        pq.add(start, 0);
        distTo.put(start, 0.0);
        while (pq.size() > 0) {
            if (pq.getSmallest().equals(end)) {
                solutionweight = distTo.get(end);
                break; }
            Vertex temp = pq.removeSmallest();
            numStatesExplored++;
            for (WeightedEdge<Vertex> e : input.neighbors(temp)) {
                Vertex p = e.from(), q = e.to();
                double w = e.weight();
                if (!distTo.containsKey(q)) {
                    to.put(q, p);
                    distTo.put(q, distTo.get(p) + w);
                    pq.add(q, distTo.get(q) + input.estimatedDistanceToGoal(q, end));
                }
                else if (distTo.get(p) + w < distTo.get(q)) {
                    distTo.put(q, distTo.get(p) + w);
                    to.put(q, p);
                    if (pq.contains(q)) {
                        pq.changePriority(q, distTo.get(q) + input.estimatedDistanceToGoal(q, end));
                    }
                    else {
                        pq.add(q, distTo.get(q) + input.estimatedDistanceToGoal(q, end));
                    }
                }
            }
            explorationTime = sw.elapsedTime();
            if (explorationTime >= timeout) {
                solveoutcome = SolverOutcome.TIMEOUT;
                break;
            }
        }
        if (solveoutcome == SolverOutcome.TIMEOUT) {
            return;
        }
        else if (pq.size() == 0) {solveoutcome = SolverOutcome.UNSOLVABLE; }
        else {
            solveoutcome = SolverOutcome.SOLVED;
            solution = new ArrayList<Vertex>();
            Vertex xx = end;
            solution.add(end);
            while (!xx.equals(start)) {
                xx = to.get(xx);
                solution.add(xx);
            }
            Collections.reverse(solution);
        }
    }


    @Override
    public SolverOutcome outcome() {
        return this.solveoutcome;
    }

    @Override
    public List<Vertex> solution() {
        if (solveoutcome.equals(SolverOutcome.SOLVED)) {
            return solution;
        }
        return new ArrayList<Vertex>();
    }

    @Override
    public double solutionWeight() {
        if (solveoutcome.equals(SolverOutcome.SOLVED)) {
            return solutionweight;
        }
        return 0;
    }

    @Override
    public int numStatesExplored() {
        return this.numStatesExplored;
    }

    @Override
    public double explorationTime() {
        return this.explorationTime;
    }

}