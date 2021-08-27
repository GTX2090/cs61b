package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;

public class KDTreeTest {
    @Test
    public void Test() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        ret.getX(); // evaluates to 3.3
        ret.getY(); // evaluates to 4.4
        assertEquals(ret.getX(), 3.3, 0.0);
        assertEquals(ret.getY(), 4.4, 0.0);
    }

    @Test
    public void Test2() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        KDTree nn = new KDTree(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        ret.getX(); // evaluates to 3.3
        ret.getY(); // evaluates to 4.4
        assertEquals(ret.getX(), 3.3, 0.0);
        assertEquals(ret.getY(), 4.4, 0.0);
    }

    @Test
    public void Test3() {
        Point p1 = new Point(Math.random(), Math.random()); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(Math.random(), Math.random());
        Point p3 = new Point(Math.random(), Math.random());
        Point p4 = new Point(Math.random(), Math.random());
        Point p5 = new Point(Math.random(), Math.random());
        Point p6 = new Point(Math.random(), Math.random());

        Point te = new Point(Math.random(), Math.random());

        KDTree nn = new KDTree(List.of(p1, p2, p3, p4, p5, p6));
        NaivePointSet n2 = new NaivePointSet(List.of(p1, p2, p3, p4, p5, p6));
        Point ret = nn.nearest(te.getX(), te.getY()); // returns p2
        Point ret2 = n2.nearest(te.getX(), te.getY());


        assertEquals(ret.getX(), ret2.getX(), 0.00001);
        assertEquals(ret.getY(), ret2.getY(), 0.00001);
    }


}
