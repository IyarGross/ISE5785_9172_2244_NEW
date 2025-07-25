package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GeometriesTest {


    /**
     * Test method for {@link .geometries.Geometries.FindIntersections(.geometries.Geometries)}.
     */
    @Test
    void testFindIntersections() {
        // ============  Equivalence Partitions Tests few of the forms are intersect ==============
        Geometries geometries = new Geometries(
                new Plane(new Point(0, 0, 2), new Point(-1, 0, 0), new Point(0, 1, 0)),
                new Sphere(1, new Point(0, 0, 2)),
                new Triangle(new Point(-2, 0, 0), new Point(0, 3, 0), new Point(0, -3, 0)));
        assertEquals(
                3,
                geometries.findIntersections(new Ray(new Point(3.48912607502429, 2.492278904234482, 0), new Vector(-4.771313818529334, -2.5410518459516, 3.328440304229791))).size(),
                "TwoDGeometries findintersection doesnt work"
        );
        // =============== empty list Boundary Value Test ==================
        assertNull(new Geometries().findIntersections(new Ray(new Point(3.48912607502429, 2.492278904234482, 0), new Vector(-4.771313818529334, -2.5410518459516, 3.328440304229791))),
                "TwoDGeometries findintersection doesnt work");
        // =============== no form is intersect list Boundary Value Test ==================
        geometries = new Geometries(new Plane(new Point(2, 0, 0), new Point(0, -2, 0), new Point(0, -1, 0)),
                new Sphere(1, new Point(2, 0, 0)),
                new Triangle(new Point(2, 0, 0), new Point(0, -2, 0), new Point(0, -1, 0)));
        assertEquals(null,
                geometries.findIntersections(new Ray(new Point(0, 0, 2), new Vector(0, 0, 1))),
                "Expected no intersections (null)");

        geometries = new Geometries(new Plane(new Point(2, 0, 0), new Point(0, -2, 0), new Point(0, -1, 0)), new Sphere(1, new Point(2, 0, 0)), new Triangle(new Point(2, 0, 0), new Point(0, -2, 0), new Point(0, -1, 0)));

        // =============== only one form is intersect list Boundary Value Test ==================
        assertEquals(
                1,
                geometries.findIntersections(new Ray(new Point(0, 0, -1), new Vector(0, 3.020864137250663, 1.510432068625332))).size(),
                "TwoDGeometries findintersection doesnt work"
        );
        // =============== all the forms are intersect list Boundary Value Test ==================
        geometries = new Geometries(new Plane(new Point(2, 0, 0), new Point(0, -2, 0), new Point(0, -1, 0)), new Sphere(1, new Point(0, 1, 0)), new Triangle(new Point(1, 0, 0), new Point(-1, 0, 0), new Point(0, 2, 0)));
        assertEquals(
                4,
                geometries.findIntersections(new Ray(new Point(0, 0, -1), new Vector(0.062848082875715, 1.615620336328068, 1.78553276187449))).size(),
                "TwoDGeometries findintersection doesnt work"
        );
    }
}