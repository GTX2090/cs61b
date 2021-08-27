package bearmaps;
import java.util.*;

public class KDTree implements PointSet {
    Node head = new Node();

    private class Node {
        Node left;
        Node right;
        Point val;
        boolean x;
        public Node() {};
        public Node(Point k, boolean x) {
            val = k;
            this.x = x;
        }
    }


    public KDTree(List<Point> points) {
        head.val = points.get(0);
        head.x = true;
        int signrl = 0;
        boolean sign = false;
        for (int i = 1; i < points.size(); i++) {
            Node temp = head;
            Node sentinel = head;
            while (temp != null) {
                sentinel = temp;
                if (temp.x) {
                    sign = false;
                    if (points.get(i).getX() <= temp.val.getX()) {
                        temp = temp.left;
                        signrl = 0;
                    }
                    else {temp = temp.right;
                        signrl = 1;}
                }
                else {
                    sign = true;
                    if (points.get(i).getY() <= temp.val.getY()) {
                        temp = temp.left;
                        signrl = 0;
                    }
                    else {temp = temp.right;
                        signrl = 1;}
                }
            }
            if (signrl == 0) {
                    sentinel.left = new Node(points.get(i), sign);
                }
            else {
                    sentinel.right = new Node(points.get(i), sign);
            }

        }
    }



    @Override
    public Point nearest(double x, double y) {
        Point p = new Point(x, y);
        double dis = Point.distance(p, head.val);
        Point ans = head.val;
        Queue<Node> good = new ArrayDeque<Node>();
        good.add(head);
        while ((!good.isEmpty())) {
            Node temp = good.poll();
            if (Point.distance(p, temp.val) < dis) {
                dis = Point.distance(p, temp.val);
                ans = temp.val;
            }
            if (temp.x) {
                if (p.getX() <= temp.val.getX()) {
                    if (temp.left != null) {
                        good.add(temp.left);
                    }
                    if (temp.right != null && Math.sqrt(dis) > Math.abs(temp.val.getX() - x)) {
                            good.add(temp.right);
                    }
                }
                else {
                    if (temp.left != null && Math.sqrt(dis) > Math.abs(temp.val.getX() - x)) {
                            good.add(temp.left);
                    }
                    if (temp.right != null) {
                        good.add(temp.right);
                    }
                }
            }
            else {
                if (p.getY() <= temp.val.getY()) {
                    if (temp.left != null) {
                        good.add(temp.left);
                    }
                    if (temp.right != null && Math.sqrt(dis) > Math.abs(temp.val.getY() - y)) {
                            good.add(temp.right);
                    }
                }
                else {
                    if (temp.right != null) {
                        good.add(temp.right);
                    }
                    if (temp.left != null && Math.sqrt(dis) > Math.abs(temp.val.getY() - y)) {
                        good.add(temp.left);
                    }
                }
            }
        }


        return ans;
    }
}
