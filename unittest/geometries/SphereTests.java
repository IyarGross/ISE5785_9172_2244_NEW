package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTests {
    /**
     * Test method for geometries.Sphere.GetNormal
     */
    @Test
    void testGetNormal() {
        Sphere s = new Sphere(1, new Point(0,0,0));
        Point p =new Point(1,0,0);
        // ============ 1 Equivalence Partition ==============
        assertDoesNotThrow(() -> s.getNormal(p));
        Vector normal = s.getNormal(p);
        assertEquals((p),normal,"Sphere- getNormal- equivalence partition has been failed ");
    }
}