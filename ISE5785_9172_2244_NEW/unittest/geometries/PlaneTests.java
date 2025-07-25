package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;

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

    @Test
    void testFindIntsersections() {
        Plane plane=new Plane(new Point(-1,0,0),new Point(0,-1,0),new Point(0,0,1));
        // ============ first Equivalence Partitions Tests ============== ray intersect the plane
        assertEquals(
                new ArrayList() {{add(new Point(0, -1, 0));}},
                plane.findIntersections(new Ray(new Point (0, 2, 0),new Vector(0,-2,0)))
                ,"plane findIntserctions doesn't work"
        );
        // ============ second Equivalence Partitions Tests ============== ray  doesn't intersect the plane
        assertEquals(
                null,
                plane.findIntersections(new Ray(new Point (3, 0, 0),new Vector(1,0,0)))
                ,"plane findIntserctions doesn't work"
        );
        plane = new Plane(new Point(-1,0,0),new Point(0,0,2),new Point(0,-2,0));
        // ===============1: Boundary Values Tests ================== ray parallel to the plane
        assertEquals(
                null,
                plane.findIntersections(new Ray(new Point (0, -3, 0),new Vector(0,3,3)))
                ,"plane findIntserctions doesn't work"
        );
        // ===============2: Boundary Values Tests ================== ray on the plane
        assertEquals(
                null,
                plane.findIntersections(new Ray(new Point (-0.815106357697045, 1.953086621757318, 2.322873906363228) ,new Vector(-0.669149185841515,-0.634578074144652,-1.972876445827683)))
                ,"plane findIntserctions doesn't work"
        );
        // ===============3: Boundary Values Tests ================== ray orthogonal and no intersect
        assertEquals(
                null,
                plane.findIntersections(new Ray(new Point (0, 0, 1),new Vector(2,1,-2)))
                ,"plane findIntserctions doesn't work"
        );
        plane = new Plane(new Point(0,0,1),new Point(0,4,0),new Point(-1,0,0));

        // ===============4: Boundary Values Tests ================== ray orthogonal and po on the plane
        assertEquals(
                null,
                plane.findIntersections(new Ray(new Point (0, 0, 1),new Vector(-4,1,4)))
                ,"plane findIntserctions doesn't work"
        );
        // ===============5: Boundary Values Tests ================== ray  orthogonal and intersect the plane
        assertEquals(
                new ArrayList() {{add(new Point(-1, 0, 0));}},
                plane.findIntersections(new Ray(new Point  (-0.159242969024383, 0.280252343658539, -0.280252343658539),new Vector(-2.007914248156397,-0.669304749385466,0.669304749385466)))
                ,"plane findIntserctions doesn't work"
        );
        // ===============6: Boundary Values Tests ================== po on the plane
        assertEquals(
                null,
                plane.findIntersections(new Ray(new Point (0, 0, 1),new Vector(0,0,1)))
                ,"plane findIntserctions doesn't work"
        );
        // ===============7: Boundary Values Tests ================== po of the ray and the po of the plane are identical
        assertEquals(
                null,
                plane.findIntersections(new Ray(new Point(0,0,1),new Vector(0,0,1)))
                ,"plane findIntserctions doesn't work"
        );


    }

}