package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for the {@link Plane} class
 *
 * this test class tests:
 * 1. correct construction of a plane from three points
 * 2. normal vector computation
 * 3. intersection points between a ray and a plane under various configurations
 */
class PlaneTest {

    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in assertEquals
     * One for checking if a vector is normalize (length == 1)
     */
    private static final double DELTA = 0.000001;
    private static final double ONE = 1.0;

    /**
     * Tests {@link Plane#Plane(Point, Point, Point)} constructor for equivalence and boundary cases.
     */
    @Test
    void testConstructor() {
        Point a = new Point (1, 2, 3);
        Point b = new Point (4, 5, 6);
        Point c = new Point (3, 7, 5);
        Point aPointOnTheLineOf_AB = new Point((1-4)*3, (2-5)*3, (3-6)*3).add(a); // 3*(a-b)+a = 3*ba+a - a point on the line ab
        Plane plane = new Plane(a, b, c);

        // ============ Equivalence Partitions Tests ==============
        // TC01: normal case, three points which form a plane
        assertEquals(plane.getNormal(), plane.getNormal().normalize(), "Calculated normal is not normalize");
        assertEquals(0, a.subtract(b).dotProduct(plane.getNormal()), DELTA, "Calculated normal is not orthogonal to the vector ba");
        assertEquals(0, c.subtract(b).dotProduct(plane.getNormal()), DELTA, "Calculated normal is not orthogonal to the vector bc");


        // =============== Boundary Values Tests ==================
        //TC11: two of the points are the same (first and second)
        assertThrows(IllegalArgumentException.class, () -> new Plane(a,a,c), "PLane() does not throw an exception when needed when a = b");
        //TC12: two of the points are the same (first and third)
        assertThrows(IllegalArgumentException.class, () -> new Plane(a,b,a), "PLane() does not throw an exception when needed when a = c");
        //TC13: two of the points are the same (second and third)
        assertThrows(IllegalArgumentException.class, () -> new Plane(a,b,b), "PLane() does not throw an exception when needed when b = c");
        //TC14: all the points are the same
        assertThrows(IllegalArgumentException.class, () -> new Plane(a,a,a), "PLane() does not throw an exception when needed when a = b = c");
        //TC15: all the points are on the same line and therefor can not form a plane
        assertThrows(IllegalArgumentException.class, () -> new Plane(a,b,aPointOnTheLineOf_AB), "PLane() does not throw an exception when needed when a, b and c are all on the same line");

    }

    /**
     * Tests {@link Plane#getNormal(Point)} to verify correct normalized normal vector is returned.
     */
    @Test
    void testGetNormal(){
        Point a = new Point (1, 2, 3);
        Point b = new Point (4, 5, 6);
        Point c = new Point (3, 7, 5);
        Point p = new Point (4, 8, 6);
        Vector normal1 = new Vector(0.7071067811865475,0.0,-0.7071067811865475);
        Vector normal2 = new Vector(-0.7071067811865475,0.0,0.7071067811865475);
        Plane plane = new Plane(a, b, c);

        // ============ Equivalence Partitions Tests ==============
        // TC 01
        assertTrue(plane.getNormal(p).equals(normal1) ||
                            plane.getNormal(p).equals(normal2),
                    "calculated wrong normal\n expected: " + normal1.toString() +
                            "or " + normal2.toString() + "but got: " + plane.getNormal().toString());
        assertEquals(ONE, plane.getNormal(p).length(), DELTA, "the result of getNormal is not a normalized vector");
    }

    /**
     * Tests {@link Plane#findIntersections(Ray)} for various ray-plane intersection scenarios including equivalence and boundary tests.
     */
    @Test
    void testFindIntersections() {
        final Point p000 = new Point(0,0,0);
        final Point p001 = new Point(0,0,1);
        final Point p00_1 = new Point(0,0,-1);
        final Point p_1_10 = new Point(-1,-1,0);
        final Point p112 = new Point(1,1,2);
        final Vector v001 = new Vector(0, 0, 1);
        final Vector v00_1 = new Vector(0, 0, -1);
        final Vector v100 = new Vector(1, 0, 0);
        final Vector v111 = new Vector(1, 1, 1);
        final Vector v_1_1_1 = new Vector(-1, -1, -1);

        final var exp1 = List.of(p_1_10);
        final var exp23 = List.of(p000);


        Plane plane = new Plane(p000,v001);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray intersects the plane once (non-right-angle) (1 point)
        final var result1 = plane.findIntersections(new Ray(p112, v_1_1_1));
        assertNotNull(result1, "Can't be empty list");
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(exp1, result1, "Ray intersects plane once");

        // TC02: Ray doesn't intersect the plane (wouldn't be a right-angle) (0 point)
        assertNull(plane.findIntersections(new Ray(p112, v111)), "Ray's line doesn't intersect plane");

        // =============== Boundary Values Tests ==================
        // **** Group 1: Ray's line is parallel to the plane
        // TC11: Ray doesn't coincides with the plane (0 points)
        assertNull(plane.findIntersections(new Ray(p001, v100)), "Ray's line is parallel and doesn't coincide with the plane");

        // TC12: Ray coincides with the plane (0 points)
        assertNull(plane.findIntersections(new Ray(p_1_10, v100)), "Ray's line is parallel and coincides with the plane");

        // **** Group 2: Ray's line is orthogonal to the plane
        // TC21: Ray after the plane (0 points)
        assertNull(plane.findIntersections(new Ray(p001, v001)), "Ray's line starts after and is orthogonal to the plane");
        assertNull(plane.findIntersections(new Ray(p00_1, v00_1)), "Ray's line starts after and is orthogonal to the plane");

        // TC22: Ray on the plane (0 points)
        assertNull(plane.findIntersections(new Ray(p_1_10, v001)), "Ray's line starts on and is orthogonal to the plane");
        assertNull(plane.findIntersections(new Ray(p_1_10, v00_1)), "Ray's line starts on and is orthogonal to the plane");

        // TC23: Ray before the plane (1 point)
        final var result23 = plane.findIntersections(new Ray(p00_1, v001));
        assertNotNull(result23, "Can't be empty list");
        assertEquals(1, result23.size(), "Wrong number of points");
        assertEquals(exp23, result23, "Ray intersects plane once and is orthogonal to the plane");

        // **** Group 3: Ray's line is neither parallel nor orthogonal to the plane yet starts on it
        // TC31: Ray starts at not the seeding point (0 point)
        assertNull(plane.findIntersections(new Ray(p_1_10, v111)), "Ray's line is neither parallel nor orthogonal to the plane yet starts on it (but not the seed point)");

        // TC32: Ray starts at the seeding point (0 point)
        assertNull(plane.findIntersections(new Ray(p000, v111)), "Ray's line is neither parallel nor orthogonal to the plane yet starts on its seed point");


    }
}