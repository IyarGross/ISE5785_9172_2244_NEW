package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;
class CylinderTests {
    /**
     * Test method for {@link .geometries.Cylinder.GetNormal(.geometries.Cylinder)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point p = new Point(0, 0, 0);
        Vector dir = new Vector(0, 0, 1);
        Ray axisRay = new Ray(p, dir);
        double radius = 1;
        double height = 2;
        Cylinder cylinder = new Cylinder(axisRay, radius, height);
        Point pointOnCylinder = new Point(1, 0, 1);
        Vector normal = cylinder.getNormal(pointOnCylinder);
        assertTrue(isZero(normal.length() - 1), "Cylinder - getNormal - the returned vector is not a unit vector");
        assertDoesNotThrow(() -> cylinder.getNormal(new Point(0, 1, 1)));
        // ============ Orthogonality Tests ==============
        assertTrue(isZero(normal.dotProduct(axisRay.getDir())), "Cylinder's normal is not orthogonal to the axis ray.");
    }
}