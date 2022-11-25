import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        if (this.x == that.x && this.y == that.y) return Double.NEGATIVE_INFINITY;
        if (this.y == that.y) return 0;
        if (this.x == that.x) return Double.POSITIVE_INFINITY;
        return (double) (that.y - this.y) / (that.x - this.x);
    }

    public int compareTo(Point that) {
        if (this.y < that.y) return -1;
        if (this.y == that.y) {
            return Integer.compare(this.x, that.x);
        }
        return 1;
    }

    public Comparator<Point> slopeOrder() {
        return new Comparator<Point>() {
            public int compare(Point o1, Point o2) {
                return Double.compare(slopeTo(o1), slopeTo(o2));
            }
        };
    }


    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}