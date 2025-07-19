package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void getPoint() {
        final Ray ray = new Ray(new Point(1.453,0.718,9.3),new Vector(9.007,3.815,3.499));
        // ============ Equivalence Partitions Tests ==============
        // TC01: t > 0, t = 4.85
        assertEquals(new Point(5.657983959343491,2.499060708881472,10.933533793021304),ray.getPoint(4.85),"failed while the scalar is positive");

        // TC02: t > 0, t = -8.488
        assertEquals(new Point(-5.9061554323520715,-2.3990398550486463,6.441147456667048),ray.getPoint(-8.488),"failed while the scalar is negative");


        // =============== Boundary Values Tests ==================
        // TC11: t = 0, t = +/-0.0
        assertEquals(ray.getHead(), ray.getPoint(0), "failed while t is equal to zero");
    }

    @Test
    void findClosestPoint() {

        Ray ray = new Ray(new Point(1,1,0), new Vector(1,0,0));

        Point p1 = new Point(1,5,0);
        Point p2 = new Point(2,6,0);
        Point p3 = new Point(3,7,0);
        Point p4 = new Point(4,8,0);


        // ============ Equivalence Partitions Tests ==============
        // TC01: A point that is (not necessarily directly) in the middle of the list is the closest
        List<Point> points01 = List.of(p4, p1, p2, p3);
        assertEquals(p1, ray.findClosestPoint(points01), "Closest point should be in the middle (TC01)");


        // =============== Boundary Values Tests ==================
        //TC11: An empty list
        List<Point> points11 = List.of();
        assertNull(ray.findClosestPoint(points11), "Empty list (TC11)");

        //TC12: First point is the closest
        List<Point> points12 = List.of(p1, p2, p3, p4);
        assertEquals(p1, ray.findClosestPoint(points12), "Closest point should be at the beginning (TC12)");

        //TC13: Last point is the closest
        List<Point> points13 = List.of(p4, p2, p3, p1);
        assertEquals(p1, ray.findClosestPoint(points13), "Closest point should be at the end (TC13)");


    }
}