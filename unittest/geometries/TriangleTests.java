package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class TriangleTests {
    /**
     * Test method for {@link .geometries.Triangle.GetNormal(.geometries.Triangle)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point[] pts =
                { new Point(0, 0, 0), new Point(1, 0, 0), new Point(1, 0.5, 0) };
        Triangle triangle = new Triangle(pts);
        Vector normal = triangle.getNormal(new Point(0,0,0));
        assertTrue( isZero(normal.length()-1),"Triangle -getNormal - the returned vector is not a unit vector");
        assertDoesNotThrow(() -> triangle.getNormal(new Point(0, 0, 1))); // ensure there are no exceptions
        // ensure the result is orthogonal to all the edges
        for (int i = 0; i < 3; ++i)
            assertTrue(isZero(normal.dotProduct(pts[i].subtract(pts[i == 0 ? 2 : i - 1]))),
                    "Polygon's normal is not orthogonal to one of the edges");
    }
}