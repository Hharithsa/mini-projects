import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[] site;
    private WeightedQuickUnionUF uf;
    private int n;
    private int count;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();


        uf = new WeightedQuickUnionUF((n * n) + 2);
        site = new boolean[(n * n) + 2];

        this.n = n;
        count = 0;
    }

    private int getPosition(int row, int col) {
        return ((row - 1) * n) + col;
    }

    public boolean isOpen(int row, int col) {
        checkValid(row, col);
        return site[getPosition(row, col)];
    }

    public void open(int row, int col) {
        checkValid(row, col);
        if (!isOpen(row, col)) {
            site[getPosition(row, col)] = true;
            count++;

            if (row == 1) {
                uf.union(0, getPosition(row, col));
                if (isOpen(row + 1, col)) {
                    uf.union(getPosition(row, col), getPosition(row + 1, col));
                }
                return;
            }

            if (row == this.n) {
                uf.union(n * n + 1, getPosition(row, col));
                if (isOpen(row - 1, col)) {
                    uf.union(getPosition(row, col), getPosition(row - 1, col));
                }
                return;
            }

            if (isOpen(row - 1, col)) {
                uf.union(getPosition(row - 1, col), getPosition(row, col));
            }

            if (isOpen(row + 1, col)) {
                uf.union(getPosition(row + 1, col), getPosition(row, col));
            }

            if (col != this.n && isOpen(row, col + 1)) {
                uf.union(getPosition(row, col + 1), getPosition(row, col));
            }

            if (col != 1 && isOpen(row, col - 1)) {
                uf.union(getPosition(row, col - 1), getPosition(row, col));
            }
        }

    }

    public boolean isFull(int row, int col) {
        checkValid(row, col);
        if (!isOpen(row, col)) {
            return false;
        }
        return uf.find(0) == uf.find(getPosition(row, col));//connected(0, getPosition(row, col));

    }

    public int numberOfOpenSites() {
        return count;

    }

    public boolean percolates() {
        return uf.find(0) == uf.find(n * n + 1);//connected(0, n * n + 1);

    }

    private void checkValid(int row, int col) {
        if (row > n || col > n || row < 1 || col < 1) {
            throw new IllegalArgumentException();
        }
    }

}

