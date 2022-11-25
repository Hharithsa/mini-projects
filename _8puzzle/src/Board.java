import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class Board {


    private final int gridSize;

    public int[][] grid;

    public Board(int[][] tiles) {
        gridSize = tiles.length;
        grid = new int[gridSize][gridSize];
        System.arraycopy(tiles, 0, grid, 0, gridSize);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        int n = grid.length;
        s.append(n + "\n");
        for (int[] ints : grid) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", ints[j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public int dimension() {
        return this.gridSize;
    }

    public int hamming() {
        int hammingCount = 0;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (grid[i][j] != gridSize * i + j + 1 && grid[i][j] != 0) {
                    hammingCount++;
                }
            }
        }
        return hammingCount;
    }

    public int manhattan() {
        int manhattanCount = 0;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (grid[i][j] != 0) {
                    manhattanCount += Math.abs((gridSize * i + j + 1) - grid[i][j]);
                }
            }
        }
        return manhattanCount;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null || y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (grid[i][j] != that.grid[i][j]) return false;
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        int[][] newGrid = new int[gridSize][gridSize];
        for (int k = 0; k < gridSize; k++) {
            newGrid[k] = grid[k].clone();
        }
        ArrayList<Board> neighbours = new ArrayList<>();

        for (int a = 0; a < gridSize; a++) {
            for (int b = 0; b < gridSize; b++) {
                if (this.grid[a][b] == 0) {

                    for (int i = -1; i < 2; i++) {
                        if (i == 0) {
                            for (int j = -1; j <= 1; j++) {
                                if (j != 0 && a + j > -1 && a + j < gridSize) {
                                    newGrid[a][b] = newGrid[a + j][b];
                                    newGrid[a + j][b] = 0;
                                    neighbours.add(new Board(newGrid));
                                    for (int k = 0; k < gridSize; k++) {
                                        newGrid[k] = grid[k].clone();
                                    }
                                }
                            }
                        }
                        if (i != 0 && b + i > -1 && b + i < gridSize) {
                            newGrid[a][b] = newGrid[a][b + i];
                            newGrid[a][b + i] = 0;
                            neighbours.add(new Board(newGrid));
                            for (int k = 0; k < gridSize; k++) {
                                newGrid[k] = grid[k].clone();
                            }
                        }
                    }
                    return neighbours;
                }
            }
        }

        return neighbours;
    }

    public Board twin() {
        int[][] newGrid = new int[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            newGrid[i] = grid[i].clone();
        }

        int tmp, rowNum = 0;
        if (newGrid[0][0] == 0 || newGrid[0][1] == 0) {
            rowNum = 1;
        }
        tmp = newGrid[rowNum][0];
        newGrid[rowNum][0] = newGrid[rowNum][1];
        newGrid[rowNum][1] = tmp;

        return new Board(newGrid);
    }


    public static void main(String[] args) {

        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board b = new Board(arr);
        StdOut.println(b.toString());

        Iterable<Board> neighbours = b.neighbors();
        for (Board n : neighbours) {
            StdOut.println(n.toString());
        }
        System.out.println("b.hamming() = " + b.hamming());
        System.out.println("b.manhattan() = " + b.manhattan());
        System.out.println("b.twin().toString() = " + b.twin().toString());
    }
}


