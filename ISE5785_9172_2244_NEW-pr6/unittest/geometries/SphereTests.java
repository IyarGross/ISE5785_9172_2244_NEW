//sphereTest.java
package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SphereTests {
    private static final double DELTA = 0.00001;

    /**
     * Test for getNormal(Point) in a Sphere.
     * ============ Equivalence Partitions Tests ==============
     * Case 1: For a sphere with center (0,0,0) and radius 1, for a point on the surface (0,0,1),
     *         the expected normal is (0,0,1).
     */
    @Test
    void testGetNormalSphere() {
        Sphere sphere = new Sphere(1, new Point(0, 0, 0));
        Point p = new Point(0, 0, 1);  // p is on the sphere surface since distance from center = 1
        Vector normal = sphere.getNormal(p);
        Vector expected = new Vector(0, 0, 1);
        assertEquals(expected, normal, "ERROR: Sphere normal is not computed correctly");
    }


    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntersections() {
        final Point p001 = new Point(0, 0, 1);
        final Point p100 = new Point(1, 0, 0);
        Sphere sphere = new Sphere(1d, p100);
        Sphere sphere2 = new Sphere(2, p001);
        Sphere sphere3 = new Sphere(0.87, p100);
        Sphere sphere4 = new Sphere(2, p100);
        Sphere sphere5 = new Sphere(2, p001);
        final Point gp1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        final Point gp2 = new Point(1.53484692283495, 0.844948974278318, 0);
        final var exp = List.of(gp1, gp2);
        final Vector v310 = new Vector(3, 1, 0);
        final Vector v110 = new Vector(1, 1, 0);
        final Point p01 = new Point(-1, 0, 0);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(p01, v110)), "Ray's line out of sphere");
        // TC02: Ray starts before and crosses the sphere (2 points)
        final var result1 = sphere.findIntersections(new Ray(p01, v310))
                .stream().sorted(Comparator.comparingDouble(p ->p.distance(p01))).toList();
        assertEquals(2, result1.size(), "Wrong number of points");
        assertEquals(exp, result1, "Ray crosses sphere");
        // TC03: Ray starts inside the sphere (1 point)
        var exp3 = List.of(new Point(0, 0, 3));
        assertEquals(
                exp3,
                sphere2.findIntersections(
                        new Ray(
                                new Point(0, 0, 2),
                                new Vector(0, 0, 2))),
                "ERROR: Ray starts inside the sphere returns a wrong point ");
        // TC04: Ray starts after the sphere (0 points)
        assertNull(
                sphere2.findIntersections(
                        new Ray(
                                new Point(1, 3, 0),
                                new Vector(3, -1, 0))),
                "ERROR: Ray starts after the sphere returns a point");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside ( 1 point)
        var exp11 = List.of(new Point(2, 0, 1));
        assertEquals(
                exp11,
                sphere2.findIntersections(
                        new Ray(
                                new Point(0, 0, 3),
                                new Vector(2, 0, -2))),
                "ERROR: Ray starts at sphere and goes inside doesn't return one point ");
        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(
                sphere3.findIntersections(
                        new Ray(
                                new Point(1.65, 0.23, 0.54),
                                new Vector(0.51, 4.15, -0.54))),
                "ERROR: Ray starts at sphere and goes outside returns a point");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        var exp13 = List.of(
                new Point(2, 0, 1),
                new Point(-2, 0, 1));
        assertEquals(
                exp13,
                sphere2.findIntersections(
                        new Ray(
                                new Point(3, 0, 1),
                                new Vector(-3, 0, 0))),
                "ERROR: Ray starts before the sphere and goes throw the middle returns a wrong number of points");

        // TC14: Ray starts at sphere and goes inside (1 point)
        var exp14 = List.of(new Point(-0.6, 0, -1.2));
        assertEquals(
                exp14,
                sphere4.findIntersections(
                        new Ray(
                                new Point(3, 0, 0),
                                new Vector(-3, 0, -1))),
                "ERROR: Ray starts at sphere and goes inside returns a wrong number of points");

        // TC15: Ray starts inside (1 point)
        var exp15 = List.of(new Point(0, 2, 1));
        assertEquals(
                exp15,
                sphere2.findIntersections(
                        new Ray(
                                new Point(0, 0, 0.5),
                                new Vector(0, 2, 0.5))),
                "ERROR: Ray starts inside returns a wrong number of points");
        // TC16: Ray starts at the center (1 point)
        var exp16 = List.of(new Point(0, 2, 1));
        assertEquals(
                exp16,
                sphere2.findIntersections(
                        new Ray(
                                new Point(0, 0, 1),
                                new Vector(0, 2, 0))),
                "ERROR: Ray starts at the center returns a wrong number of points");

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(
                sphere4.findIntersections(
                        new Ray(
                                new Point(0, 3, 0),
                                new Vector(-0.3, 2, 0))),
                "ERROR: Ray starts at sphere and goes outside returns a point");
        // TC18: Ray starts after sphere (0 points)
        assertNull(
                sphere2.findIntersections(
                        new Ray(
                                new Point(1.5, 3, 0),
                                new Vector(0.99, -2.9, 0))),
                "ERROR: Ray starts after sphere and goes outside returns a point");
        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull(
                sphere5.findIntersections(
                        new Ray(
                                new Point(0, -2.45, 3),
                                new Vector(0, 1, 0))),
                "ERROR: Ray starts before the tangent point returns a point");
        // TC20: Ray starts at the tangent point
        assertNull(
                sphere5.findIntersections(
                        new Ray(
                                new Point(0, 0, 3),
                                new Vector(0, 1, 0))),
                "ERROR: Ray starts at the tangent point returns a point");
        // TC21: Ray starts after the tangent point
        assertNull(
                sphere5.findIntersections(
                        new Ray(
                                new Point(0, 1.48, 3),
                                new Vector(0, 1, 0))),
                "ERROR: Ray starts after the tangent point returns a point");
        // **** Group: Special cases
        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(
                sphere5.findIntersections(
                        new Ray(
                                new Point(0, 0, 5),
                                new Vector(0, 1, 0))),
                "ERROR: Ray's line is outside," +
                        " ray is orthogonal to ray start to sphere's center line returns a point");
    }
}
