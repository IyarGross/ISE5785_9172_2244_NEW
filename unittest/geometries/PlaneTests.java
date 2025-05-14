package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class PlaneTests {

    @Test
    void testGetNormal() {
        Point p = new Point (0,0,0);
        Point j = new Point (1,0,0);
        Point k = new Point (0,1,0);
        Point[] pts = { p, j, k };
        Plane plane= new Plane(p,j,k);
        // ============ Equivalence Partitions Tests ==============
        assertDoesNotThrow(() -> plane.getNormal(p));
        Vector result = plane.getNormal(p);
        assertTrue(isZero(1-result.length()),"Plane - getNormal- the normal is not a  unit vector");
        // ensure the result is orthogonal to all the edges
        for (int i = 0; i < 3; ++i)
            assertTrue(isZero(result.dotProduct(pts[i].subtract(pts[i == 0 ? 2 : i - 1]))),
                    "Plane's normal is not orthogonal to one of the edges");

    }

}