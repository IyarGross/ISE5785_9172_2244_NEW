package geometries;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;
import org.junit.jupiter.api.Test;
import static primitives.Util.isZero;
import static org.junit.jupiter.api.Assertions.*;

class TubeTests {
    /**
     * Test method for {@link .geometries.Tube.GetNormal(.geometries.Tube)}.
     */
    @Test
    void testGetNormal() {
        Point p = new Point(0, 0, 0);
        Vector dir = new Vector(0, 0, 1);
        Ray axisRay = new Ray(p, dir);
        double radius = 1;
        Tube tube = new Tube(radius, axisRay);
        // ============ Equivalence Partitions Tests ==============
        Point pointOnTube = new Point(1, 0, 0);
        Vector normal = tube.getNormal(pointOnTube);
        assertTrue(isZero(normal.length() - 1), "Tube - getNormal - the returned vector is not a unit vector");
        assertDoesNotThrow(() -> tube.getNormal(new Point(0, 1, 0)));
        assertTrue(isZero(normal.dotProduct(axisRay.getDir())), "Tube's normal is not orthogonal to the axis ray.");
    }
}