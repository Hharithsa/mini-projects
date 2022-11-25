import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FastCollinearPoints {

    private List<Point> pointList;

    private List<LineSegment> lineSegments = new ArrayList<>();
    private int count = 0;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();


        for (int i = 0; i < points.length - 1; i++) {
            if (points[i] == null) throw new IllegalArgumentException();

            pointList = new ArrayList<>();
            for (int j = i + 1; j < points.length; j++) {
                if (points[j] == null || points[j] == points[i]) throw new IllegalArgumentException();

                pointList.add(points[j]);
            }
            Collections.sort(pointList);
            pointList.sort(points[i].slopeOrder());
            count = 0;
            for (int j = 1; j < points.length - i - 1; j++) {
                if (pointList.get(j) == pointList.get(j - 1)) {
                    throw new IllegalArgumentException();
                }
                if (points[i].slopeTo(pointList.get(j)) == points[i].slopeTo(pointList.get(j - 1))) {
                    count++;
                }
                if (points[i].slopeTo(pointList.get(j)) != points[i].slopeTo(pointList.get(j - 1))) {
                    if (count > 2) {
                        lineSegments.add(new LineSegment(points[i], pointList.get(j - 1)));
                        count = 0;
                    }
                }
            }
        }

    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        LineSegment[] segments = new LineSegment[numberOfSegments()];
        for (int i = 0; i < numberOfSegments(); i++) {
            segments[i] = lineSegments.get(i);
        }
        return segments;
    }



    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
