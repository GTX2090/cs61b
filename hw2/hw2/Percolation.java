package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    WeightedQuickUnionUF grid;
    int[][] condi;
    int N;
    int numofOpen;
    public Percolation(int N) {
        grid = new WeightedQuickUnionUF(N * N + 2);
        this.N = N;
        condi = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                condi[i][j] = -1;
            }
        }
        numofOpen = 0;
    }

    public void open(int row, int col) {
        if (condi[row][col] == -1) {
            condi[row][col] = 0;
            numofOpen++;
        }
        else{
            return;
        }
        int next[][] = new int[][]{
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };
        for (int i = 0; i < 4; i++) {
            int mx = row + next[i][0];
            int my = col + next[i][1];
            if (my < 0 || my >= N)
                continue;
            if (mx == -1){
                grid.union(col,N*N);
                continue;
            }
            else if (mx == N){
                grid.union(row * N + col,N*N + 1);
                continue;
            }
            else if (isOpen(mx, my) && !grid.connected(row * N + col, mx * N + my)) {
                grid.union(row * N + col, mx * N + my);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        return condi[row][col] == 0;
    }

    public boolean isFull(int row, int col) {
        return grid.connected(row * N + col, N * N);
    }

    public int numberOfOpenSites() {
        return numofOpen;
    }

    public boolean percolates() {
        return grid.connected(N * N, N * N + 1);
    }

    public static void main(String[] args) {

    }
}
