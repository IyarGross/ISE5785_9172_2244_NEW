package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 /**
 * Unit tests for {@link Geometries} class testing findIntersections method with equivalence and boundary cases.
 */
class GeometriesTest {
    private final Geometries geometries = new Geometries(
            new Sphere(1, new Point(0, 0, 1)),
            new Triangle(new Point(1, 0, 0), new Point(1, 1, 0), new Point(0, 1, 0)),
            new Plane(new Point(0, 0, 3), new Vector(0, 0, 1)));

    /**
     * Tests {@link Geometries#findIntersections(Ray)} for various cases including equivalence partitions and boundary value tests.
     */
    @Test
    void findIntersections() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Some but not all geometries intersect
        assertEquals(3, geometries.findIntersections(new Ray(new Point(0,0,-1), new Vector(1,2,6))).size(), "some of the geometries are intersected by the ray");

        // ================= Boundary Values Tests =================
        // TC11: empty geometries list
        assertNull(new Geometries().findIntersections(new Ray(new Point(2,3,4), new Vector(8,20,-2))),"the geometries list is empty");

        // TC12: none of the geometries are intersected
        assertNull(geometries.findIntersections(new Ray(new Point(10,10,10), new Vector(1,0,0))), "no geometries are intersected by the ray");

        // TC13: one of the geometries is intersected
        assertEquals(2,geometries.findIntersections(new Ray(new Point(1,2,1), new Vector(-3,-5,-1))).size(),"one of the geometries is intersected by the ray");

        // TC14: all the geometries are intersected
        assertEquals(4,geometries.findIntersections(new Ray(new Point(0.7,0.7,-0.1), new Vector(0.3,-3.7,4.9))).size(),"all the geometries are intersected by the ray");


    }
}