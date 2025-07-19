package geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;


public class CylinderTest {

    private final Vector v1 = new Vector(0, -1, 0);  // updated for new axis direction
    private final Vector v2 = new Vector(0, 1, 0);
    private final Cylinder cylinder = new Cylinder(2, new Ray(new Point(1, 2, 3), v2), 3);

    /** Test method for {@link geometries.Cylinder#getNormal(Point)} */
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: the point is on the top of the outer surface of the cylinder
        assertEquals(new Vector(1, 0, 0), cylinder.getNormal(new Point(3, 5, 3)), "Bad normal to cylinder point on top (TC01)");

        // TC02: the point is on the bottom outer surface of the cylinder
        assertEquals(new Vector(1, 0, 0), cylinder.getNormal(new Point(3, 2, 3)), "Bad normal to cylinder point on bottom (TC02)");

        // TC03: the point is on the side outer surface of the cylinder
        assertEquals(new Vector(0, 0, 1), cylinder.getNormal(new Point(1, 3, 5)), "Bad normal to cylinder point on side (TC03)");


        // =============== Boundary Values Tests ==================
        // TC11: the point is on the bottom edge of the cylinder
        assertEquals(v1, cylinder.getNormal(new Point(1, 2, 3)), "Bad normal to cylinder point on bottom edge (TC11)");

        // TC12: the point is in the middle bottom outer surface of the cylinder
        assertEquals(v1, cylinder.getNormal(new Point(1, 2, 5)), "Bad normal to cylinder point on middle bottom edge (TC12)");

        // TC13: the point is on the top edge of the cylinder;
        assertEquals(v2, cylinder.getNormal(new Point(1, 5, 3)), "Bad normal to cylinder point on top edge (TC13)");

        // TC14: the point is in the middle top edge of the cylinder
        assertEquals(v2, cylinder.getNormal(new Point(1, 5, 5)), "Bad normal to cylinder point on middle top edge (TC14)");
    }
}
