import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class BruteCollinearPoints {

    private List<LineSegment> lineSegments = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {

        if (points == null) throw new IllegalArgumentException();
        if (points.length < 4) {
            for (Point point : points) {
                if (point == null) throw new IllegalArgumentException();
            }
            return;
        }

        for (int i = 0; i < points.length - 3; i++) {
            if (points[i] == null) throw new IllegalArgumentException();

            for (int j = i + 1; j < points.length - 2; j++) {
                if (points[j] == null
                        || points[j] == points[i]) throw new IllegalArgumentException();

                for (int k = j + 1; k < points.length - 1; k++) {
                    if (points[k] == null
                            || points[k] == points[i]
                            || points[k] == points[j]) throw new IllegalArgumentException();

                    for (int li = k + 1; li < points.length; li++) {
                        if (points[li] == null
                                || points[li] == points[i]
                                || points[li] == points[j]
                                || points[li] == points[k]) throw new IllegalArgumentException();

                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) &&
                                points[i].slopeTo(points[j]) == points[i].slopeTo(points[li])) {
                            lineSegments.add(
                                    new LineSegment(Collections.min(Arrays.asList(points[i], points[j],
                                            points[k], points[li])),
                                            Collections.max(Arrays.asList(points[i], points[j],
                                                    points[k], points[li]))));
                        }
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


}
