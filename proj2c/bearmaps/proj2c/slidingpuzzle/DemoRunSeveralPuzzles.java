package bearmaps.proj2c.slidingpuzzle;

import bearmaps.proj2c.AStarSolver;
import bearmaps.proj2c.LazySolver;
import bearmaps.proj2c.ShortestPathsSolver;
import bearmaps.proj2c.SolutionPrinter;
import edu.princeton.cs.introcs.In;

import java.io.*;
import java.io.FileInputStream;
import java.util.Scanner;

/**
 * Showcases how the AStarSolver can be used for solving sliding puzzles.
 * Runs several puzzles in a row.
 * NOTE: YOU MUST REPLACE LazySolver WITH AStarSolver OR THIS DEMO WON'T WORK!
 * Created by hug.
 */
public class DemoRunSeveralPuzzles {
    private static String[] basicPuzzles = {"BasicPuzzle1.txt", "BasicPuzzle2.txt",
        "BasicPuzzle3.txt", "BasicPuzzle4.txt"};

    private static String[] hardPuzzles = {"HardPuzzle1.txt", "HardPuzzle2.txt",
        "HardPuzzle3.txt"};

    private static String[] elitePuzzles = {"ElitePuzzle1.txt", "ElitePuzzle2.txt",
        "ElitePuzzle3.txt"};

    public static void main(String[] args) {
        String[] puzzleFiles = hardPuzzles;
        System.out.println(puzzleFiles.length + " puzzle files being run.");

        for (int i = 0; i < puzzleFiles.length; i += 1) {
            File file = new File(puzzleFiles[i]);
            Board start = Board.readBoard("D:\\JAVA_WORKPLACE\\cs61b\\proj2c\\bearmaps\\proj2c\\" +puzzleFiles[i]);
            int N = start.size();
            Board goal = Board.solved(N);

            BoardGraph spg = new BoardGraph();
            System.out.println(puzzleFiles[i] + ":");
            ShortestPathsSolver<Board> solver = new AStarSolver<>(spg, start, goal, 30);
            SolutionPrinter.summarizeOutcome(solver);
        }

    }
}
