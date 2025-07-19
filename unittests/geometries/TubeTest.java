package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for {@link Tube} class focusing on normal vector calculation.
 */
class TubeTest {
    /**
     * Tests {@link Tube#getNormal(Point)} for correctness in both general and boundary cases.
     */
    @Test
    void testGetNormal() {


        Tube tube = new Tube(new Ray(new Point(2,3,1), new Vector(0,0,1)),5);

        //
        // ============ Equivalence Partitions Tests ==============
        // TC01: almost every point, when the point is not on the circle of the head

        Point p1 = new Point(5,7,2);
        Vector normal1 = new Vector(3/5d,4/5d,0);

        assertDoesNotThrow(()-> tube.getNormal(p1)); // ensure there are no exceptions
        assertEquals(normal1, tube.getNormal(p1), "calculated the wrong normal for a Tube EP");

        // =============== Boundary Values Tests ==================
        // TC02 when the point is on the circle of the head

        Point p2 = new Point(7,3,1);
        Vector normal2 = new Vector(1,0,0);

        assertDoesNotThrow(()-> tube.getNormal(p2)); // ensure there are no exceptions
        assertEquals(normal2, tube.getNormal(p2), "calculated the wrong normal for a Tube BVA");
    }
}