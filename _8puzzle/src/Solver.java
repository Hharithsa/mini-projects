import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;


public class Solver {

    private static class BoardNode implements Comparable<BoardNode> {
        private final Board board;
        public final BoardNode parent;
        public int moves;
        public int prio;

        public BoardNode(Board board, BoardNode parent, int moves) {
            this.board = board;
            this.moves = moves;
            this.parent = parent;
            this.prio = this.board.manhattan() + this.moves;
        }

        @Override
        public int compareTo(BoardNode that) {
            return Integer.compare(this.prio, that.prio);
        }
    }

    public BoardNode solution = null;
    private Board initial = null;

    public ArrayList<Board> solutionPath = null;

    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();

        this.initial = initial;

        //using A* algorithm, finding solution
        if (isSolvable()) {
            MinPQ<BoardNode> boardQ = new MinPQ<>();
            BoardNode searchNode = null;
            Iterable<Board> neighbours;

            boardQ.insert(new BoardNode(initial, null, 0));
            do {
                searchNode = boardQ.delMin();
                solutionPath = new ArrayList<>();
                solutionPath.add(searchNode.board);
                if (!searchNode.board.isGoal()) {
                    neighbours = searchNode.board.neighbors();
                    for (Board n : neighbours) {
                        if (searchNode.parent == null) {
                            boardQ.insert(new BoardNode(n, searchNode, searchNode.moves + 1));
                        }
                        if (searchNode.parent != null && !n.equals(searchNode.parent.board)) {
                            boardQ.insert(new BoardNode(n, searchNode, searchNode.moves + 1));
                        }
                    }
                }
            }
            while (!searchNode.board.isGoal());

            solution = searchNode;
        }
    }

    public boolean isSolvable() {
        Board a = this.initial;
        Board b = this.initial.twin();

        MinPQ<BoardNode> boardQa = new MinPQ<>();
        MinPQ<BoardNode> boardQb = new MinPQ<>();
        BoardNode searchA ;
        BoardNode searchB ;
        Iterable<Board> neighboursA;
        Iterable<Board> neighboursB;

        boardQa.insert(new BoardNode(a, null, 0));
        boardQb.insert(new BoardNode(b, null, 0));

        do {
            searchA = boardQa.delMin();
            if (!searchA.board.isGoal()) {
                neighboursA = searchA.board.neighbors();
                for (Board n : neighboursA) {
                    if (searchA.parent == null) {
                        boardQa.insert(new BoardNode(n, searchA, searchA.moves + 1));
                    }
                    if (searchA.parent != null && !n.equals(searchA.parent.board)) {
                        boardQa.insert(new BoardNode(n, searchA, searchA.moves + 1));
                    }
                }
            }

            searchB = boardQb.delMin();
            if (!searchB.board.isGoal()) {
                neighboursB = searchB.board.neighbors();
                for (Board n : neighboursB) {
                    if (searchB.parent == null) {
                        boardQb.insert(new BoardNode(n, searchB, searchB.moves + 1));
                    }
                    if (searchB.parent != null && !n.equals(searchB.parent.board)) {
                        boardQb.insert(new BoardNode(n, searchB, searchB.moves + 1));
                    }
                }
            }

        }
        while (!searchA.board.isGoal() && !searchB.board.isGoal());
        return searchA.board.isGoal();

    }

    public int moves() {
        if (isSolvable()) return solution.moves;
        else return -1;
    }

    public Iterable<Board> solution() {
        return solutionPath;
    }


    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
