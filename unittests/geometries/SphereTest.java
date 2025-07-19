package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    /**
     * Tests {@link Sphere#getNormal(Point)} with equivalence partition TC 01: all cases are the same.
     */
    @Test
    void testGetNormal() {
        Sphere s = new Sphere(3, new Point(1, 2, 3));
        Point p= new Point(3,3,5);
        Vector normal = new Vector( 2/3d, 1/3d,2/3d);

        // ============ Equivalence Partitions Tests ==============
        //TC 01 all cases are the same
        // ensure there are no exceptions
        assertDoesNotThrow(() -> s.getNormal(p), "");
        // testing
        assertEquals(normal, s.getNormal(p), "calculated the wrong normal for a Sphere");
    }
    /** A point used in some tests */
    private final Point p001 = new Point(0, 0, 1);
    /** A point used in some tests */
    private final Point p100 = new Point(1, 0, 0);
    /** A vector used in some tests */
    private final Vector v001 = new Vector(0, 0, 1);
    /**
     * Tests {@link Sphere#findIntersections(Ray)} with all equivalence partitions and boundary value test cases.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(1d, p100);
        final Point p000 = new Point(0,0,0);
        final Point p010 = new Point(0,1,0);
        final Point p110 = new Point(1, 1, 0);
        final Point p200 = new Point(2,0,0);
        final Point p_100 = new Point(-1, 0, 0);
        final Point p0_500 = new Point(0.5,0,0);
        final Point p0_60_50 = new Point(0.6,0.5,0);
        final Point gp1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        final Point gp2 = new Point(1.53484692283495, 0.844948974278318, 0);
        final Point gp3 = new Point(1.866025403784439, 0.5, 0);
        final Point gp4 = new Point(0.5, 0, 0.8660254037844386);
        final var exp2 = List.of(gp1, gp2);
        final var exp3 = List.of(gp3);
        final var exp11 = List.of(p110);
        final var exp21 = List.of(p000,p200);
        final var exp22 = List.of(p200);
        final var exp23 = List.of(p200);
        final var exp24 = List.of(p200);
        final var exp42 = List.of(gp4);
        final Vector v310 = new Vector(3, 1, 0);
        final Vector v110 = new Vector(1, 1, 0);
        final Vector v100 = new Vector(1,0,0);
        final Vector v_100 = new Vector(-1,0,0);
        final Vector v_10_1 = new Vector(-1,0,-1);


        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(p_100, v110)), "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        final var result2 = sphere.findIntersections(new Ray(p_100, v310));
            assertNotNull(result2, "Can't be empty list");
            assertEquals(2, result2.size(), "Wrong number of points");
            assertEquals(exp2, result2, "Ray intersects sphere twice");

        // TC03: Ray starts inside the sphere (1 point)
        final var result3 = sphere.findIntersections(new Ray(p0_60_50,v100));
            assertNotNull(result3, "Can't be empty list");
            assertEquals(1, result3.size(), "Wrong number of points");
            assertEquals(exp3, result3, "Ray starts in and intersects sphere once");

        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(p_100, v_10_1)), "Ray's line out of sphere");


        // =============== Boundary Values Tests ==================
        // **** Group 1: Ray's line crosses the sphere (but not the center)

        // TC11: Ray starts at sphere and goes inside (1 points)
        final var result11 = sphere.findIntersections(new Ray(p000,v110));
            assertNotNull(result11, "Can't be empty list");
            assertEquals(1, result11.size(), "Wrong number of points");
            assertEquals(exp11, result11, "Ray starts on and intersects sphere once");

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(p110, v310)), "Ray's line out of sphere");

        // **** Group 2: Ray's line goes through the center
        // TC21: Ray starts before the sphere (2 points)
        final var result21 = sphere.findIntersections(new Ray(p_100, v100));
            assertNotNull(result21, "Can't be empty list");
            assertEquals(2, result21.size(), "Wrong number of points");
            assertEquals(exp21, result21, "Ray starts before, goes through the center and intersects the sphere twice");

        // TC22: Ray starts at sphere and goes inside (1 points)
        final var result22 = sphere.findIntersections(new Ray(p000,v100));
            assertNotNull(result22, "Can't be empty list");
            assertEquals(1, result22.size(), "Wrong number of points");
            assertEquals(exp22, result22, "Ray starts on and goes through the center and intersects sphere once");

        // TC23: Ray starts inside (1 points)
        final var result23 = sphere.findIntersections(new Ray(p0_500,v100));
            assertNotNull(result23, "Can't be empty list");
            assertEquals(1, result23.size(), "Wrong number of points");
            assertEquals(exp23, result23, "Ray starts inside, goes through the center and intersects sphere once");

        // TC24: Ray starts at the center (1 points)
        final var result24 = sphere.findIntersections(new Ray(p100,v100));
            assertNotNull(result24, "Can't be empty list");
            assertEquals(1, result24.size(), "Wrong number of points");
            assertEquals(exp24, result24, "Ray starts at the center and intersects sphere once");

        // TC25: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(p000, v_100)), "Ray's line starts on and goes out of sphere (reverse vector would go through center");

        // TC26: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(p_100, v_100)), "Ray's line starts and goes out of sphere (reverse vector would go through center");

        // **** Group 3: Ray's line is tangent to the sphere (all tests 0 points)
        // TC31: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray(p010, v100)), "Ray tangents the sphere and starts before it");

        // TC32: Ray starts at the tangent point
        assertNull(sphere.findIntersections(new Ray(p110, v100)), "Ray tangents the sphere and starts on it");

        // TC33: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(p001, v001)), "Ray doesn't go near sphere (reverse vector would tangent the sphere)");

        // **** Group 4: Special cases
        // TC41: Ray's line is outside sphere, ray is orthogonal to ray start to sphere's center line
        assertNull(sphere.findIntersections(new Ray(p_100, v001)), "Ray is orthogonal to the sphere and starts out of it");

        // TC42: Ray's starts inside, ray is orthogonal to ray start to sphere's center line
        assertEquals(exp42, sphere.findIntersections(new Ray(p0_500, v001)), "Ray is orthogonal to the sphere and starts in it");


    }
}