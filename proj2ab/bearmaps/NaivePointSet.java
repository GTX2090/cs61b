package bearmaps;
import java.util.*;

public class NaivePointSet implements PointSet {
    private Point[] pointset;
    public NaivePointSet(List<Point> points) {
        pointset = new Point[points.size()];
        int i = 0;
        for (Point point : points) {
            pointset[i++] = point;
        }
    }

    @Override
    public Point nearest(double x, double y) {
        Point target = new Point(x, y);
        Point ans = new Point(0, 0);
        double dis = Double.POSITIVE_INFINITY;
        for (Point k : pointset) {
            if (Point.distance(k, target) < dis) {
                dis = Point.distance(k, target);
                ans = k;
            }
        }
        return ans;
    }
}
