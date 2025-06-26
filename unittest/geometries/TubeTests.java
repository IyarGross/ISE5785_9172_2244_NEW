//tubeTest.java
package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TubeTests {
    private static final double DELTA = 0.00001;

    /**
     * Test for getNormal(Point) in a Tube.
     * ============ Equivalence Partitions Tests ==============
     * Case 1: For a tube with axis Ray((0,0,0), (0,0,1)) and radius 1, for a point on the surface (1,0,1),
     *         the expected normal is (1,0,0) because the projection of (1,0,1) on the axis is (0,0,1).
     */
    @Test
    void testGetNormalTube() {
        Ray axis = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        Tube tube = new Tube(1, axis);
        Point p = new Point(1, 0, 1);  // distance from p to axis equals 1
        Vector normal = tube.getNormal(p);
        Vector expected = new Vector(1, 0, 0);  // since p - (projection onto axis = (0,0,1)) = (1,0,0)
        assertEquals(expected, normal, "ERROR: Tube normal is not computed correctly");
    }


/*    @Test
    void testFindIntersections_Tube() {
        Tube tube = new Tube(1, new Ray(new Point(2, 2, 2), new Vector(0, 0, 1)));

        // TC01: Ray's line is outside the tube (0 points)
        assertNull(tube.findIntersections(new Ray(new Point(5, 5, 2), new Vector(1, 0, 0))),
                "Ray's line is outside the tube");

        // TC02: Ray starts before and crosses the tube (2 points)
        List<Point> intersections = tube.findIntersections(
                new Ray(new Point(0, 2, 2), new Vector(1, 0, 0)));
        assertNotNull(intersections, "Intersection list should not be null");
        assertEquals(2, intersections.size(), "Wrong number of intersections");

        // TC03: Ray starts inside the tube (1 point)
        intersections = tube.findIntersections(
                new Ray(new Point(2.5, 2, 2), new Vector(1, 0, 0)));
        assertNotNull(intersections, "Intersection list should not be null");
        assertEquals(1, intersections.size(), "Wrong number of intersections");

        // TC04: Ray starts after the tube (0 points)
        assertNull(tube.findIntersections(new Ray(new Point(6, 2, 2), new Vector(1, 0, 0))),
                "Ray starts after the tube - should return null");

        // TC11: Ray is tangent to the tube (1 point)
        intersections = tube.findIntersections(new Ray(new Point(3, 1, 2), new Vector(0, 1, 0)));
        assertNotNull(intersections, "Intersection list should not be null");
        assertEquals(1, intersections.size(), "Wrong number of intersections");
    }*/
}

