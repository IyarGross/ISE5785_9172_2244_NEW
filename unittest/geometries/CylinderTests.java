//cylinderTest.java
package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;


class CylinderTests {
    private static final double DELTA = 0.00001;

    /**
     * Test for getNormal(Point) in a finite Cylinder.
     * The cylinder is defined with axis Ray((0,0,0), (0,0,1)), radius 1 and height 2.
     * ============ Equivalence Partitions Tests ==============
     * EP1: Lateral surface – for point (1,0,1) expect normal (1,0,0).
     * EP2: Top base – for point (0.5,0.5,2) expect normal (0,0,1).
     * EP3: Bottom base – for point (0.5,-0.5,0) expect normal (0,0,-1).

     * =============== Boundary Values Tests ==================
     * BC1: Center of top base – point (0,0,2) expect normal (0,0,1).
     * BC2: Center of bottom base – point (0,0,0) expect normal (0,0,-1).
     * BC3: Junction of lateral and top base – point (1,0,2); accepted if normal is either (1,0,0) or (0,0,1).
     * BC4: Junction of lateral and bottom base – point (1,0,0); accepted if normal is either (1,0,0) or (0,0,-1).
     */
    @Test
    void testGetNormalCylinder() {
        Cylinder cylinder = new Cylinder(1, new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 2);

        // EP1: Lateral surface
        Point p1 = new Point(1, 0, 1);
        Vector normal1 = cylinder.getNormal(p1);
        Vector expected1 = new Vector(1, 0, 0);
        assertEquals(expected1, normal1, "ERROR: Cylinder normal on lateral surface is not computed correctly");

        // EP2: Top base (excluding edge)
        Point p2 = new Point(0.5, 0.5, 2);
        Vector normal2 = cylinder.getNormal(p2);
        Vector expected2 = new Vector(0, 0, 1);
        assertEquals(expected2, normal2, "ERROR: Cylinder normal on top base is not computed correctly");

        // EP3: Bottom base (excluding edge)
        Point p3 = new Point(0.5, -0.5, 0);
        Vector normal3 = cylinder.getNormal(p3);
        Vector expected3 = new Vector(0, 0, -1);
        assertEquals(expected3, normal3, "ERROR: Cylinder normal on bottom base is not computed correctly");

        // BC1: Center of top base
        Point p4 = new Point(0, 0, 2);
        Vector normal4 = cylinder.getNormal(p4);
        Vector expected4 = new Vector(0, 0, 1);
        assertEquals(expected4, normal4, "ERROR: Cylinder normal at the center of top base is not computed correctly");

        // BC2: Center of bottom base
        Point p5 = new Point(0, 0, 0);
        Vector normal5 = cylinder.getNormal(p5);
        Vector expected5 = new Vector(0, 0, -1);
        assertEquals(expected5, normal5, "ERROR: Cylinder normal at the center of bottom base is not computed correctly");

        // BC3: Junction between lateral surface and top base
        Point p6 = new Point(1, 0, 2);
        Vector normal6 = cylinder.getNormal(p6);
        Vector possible1 = new Vector(1, 0, 0);
        Vector possible2 = new Vector(0, 0, 1);
        boolean valid6 = normal6.equals(possible1) || normal6.equals(possible2);
        assertTrue(valid6, "ERROR: Cylinder normal at the junction between lateral and top base is not computed correctly");

        // BC4: Junction between lateral surface and bottom base
        Point p7 = new Point(1, 0, 0);
        Vector normal7 = cylinder.getNormal(p7);
        Vector possible3 = new Vector(1, 0, 0);
        Vector possible4 = new Vector(0, 0, -1);
        boolean valid7 = normal7.equals(possible3) || normal7.equals(possible4);
        assertTrue(valid7, "ERROR: Cylinder normal at the junction between lateral and bottom base is not computed correctly");
    }


/*    @Test
    void testFindIntersections() {
        Cylinder cylinder = new Cylinder(1, new Ray(new Point(1, 1, 1), new Vector(0, 0, 1)), 2);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the cylinder (0 points)
        assertNull(cylinder.findIntersections(new Ray(new Point(4, 1, 1), new Vector(0, 1, 0))),
                "Ray's line is outside the cylinder");

        // TC02: Ray crosses the cylinder's lateral surface (2 points)
        List<Point> intersections = cylinder.findIntersections(new Ray(new Point(-1, 1, 2), new Vector(1, 0, 0)));
        assertNotNull(intersections, "Intersection list should not be null");
        assertEquals(2, intersections.size(), "Wrong number of intersections");

        // TC03: Ray crosses through the top base (1 point)
        intersections = cylinder.findIntersections(new Ray(new Point(1.5, 1, 4), new Vector(0, 0, -1)));
        assertNotNull(intersections, "Intersection list should not be null");
        assertEquals(1, intersections.size(), "Wrong number of intersections");

        // TC04: Ray crosses through the bottom base (1 point)
        intersections = cylinder.findIntersections(new Ray(new Point(1.5, 1, 0), new Vector(0, 0, 1)));
        assertNotNull(intersections, "Intersection list should not be null");
        assertEquals(1, intersections.size(), "Wrong number of intersections");

        // =============== Boundary Values Tests ==================

        // TC11: Ray starts at the top base and goes outside (0 points)
        assertNull(cylinder.findIntersections(new Ray(new Point(1.5, 1, 3), new Vector(0, 0, 1))),
                "Ray starts at top base and goes outside - should return null");

        // TC12: Ray starts at the bottom base and goes outside (0 points)
        assertNull(cylinder.findIntersections(new Ray(new Point(1.5, 1, 1), new Vector(0, 0, -1))),
                "Ray starts at bottom base and goes outside - should return null");

        // TC13: Ray is tangent to the cylinder (1 point)
        intersections = cylinder.findIntersections(new Ray(new Point(2, 0, 2), new Vector(0, 1, 0)));
        assertNotNull(intersections, "Intersection list should not be null");
        assertEquals(1, intersections.size(), "Wrong number of intersections");
    }*/
}
