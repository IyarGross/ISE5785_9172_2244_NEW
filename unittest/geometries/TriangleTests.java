//triangleTest.java
package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    private static final double DELTA = 0.00001;

    /**
     * Test for getNormal(Point) in a Triangle.
     * <p>
     * ============ Equivalence Partitions Tests ==============
     * Case 1: Construct a triangle using three non-collinear points and verify
     * that the computed normal is a unit vector and orthogonal to two independent edges.
     * <p>
     * Note: The point passed to getNormal is chosen as one of the vertices, ensuring it lies on the triangle.
     */
    @Test
    void testGetNormal() {
        // Create three non-collinear points for a triangle
        final Point p1 = new Point(0, 0, 1);
        final Point p2 = new Point(1, 0, 0);
        final Point p3 = new Point(0, 1, 0);
        // Construct the triangle (Triangle extends Polygon and uses its 3-point constructor)
        Triangle triangle = new Triangle(p1, p2, p3);

        // Retrieve the normal; using p1 (which lies on the triangle) as argument.
        Vector normal = triangle.getNormal(p1);

        // Equivalence Case: Check that the normal is a unit vector.
        assertEquals(1, normal.length(), DELTA, "ERROR: Triangle's normal is not a unit vector");

        // Equivalence Case: Check that the normal is orthogonal to the triangle's edges.
        Vector edge1 = p2.subtract(p1);
        Vector edge2 = p3.subtract(p1);
        assertEquals(0, normal.dotProduct(edge1), DELTA, "ERROR: Triangle's normal is not orthogonal to edge1");
        assertEquals(0, normal.dotProduct(edge2), DELTA, "ERROR: Triangle's normal is not orthogonal to edge2");
    }

    /**
     * Test method for {@link geometries.Triangle#findIntersections(Ray)}
     */
    @Test
    void testFindIntersections() {
        Point[] points =
                {new Point(0, 0, 1),
                        new Point(1, 0, 0),
                        new Point(0, 1, 0)
                };
        Triangle triangle = new Triangle(points[0], points[1], points[2]);

        // ============ Equivalence Partitions Tests ==============
        //TC01: Ray crosses the triangle inside the triangle (1 point)
        Point[] points2 =
                {new Point(0, 2, 0),
                        new Point(-3, 0, 0),
                        new Point(0, -4, 0)
                };
        Triangle triangle2 = new Triangle(points2[0], points2[1], points2[2]);
        var exp01 = List.of(new Point(-2, 0, 0));
        assertEquals(
                exp01,
                triangle2.findIntersections(
                        new Ray(
                                new Point(1.87, -4.37, 1),
                                new Vector(-3.87, 4.37, -1))),
                "ERROR: Ray crosses the triangle inside the triangle returns a wrong point");
        //TC02: Ray pass out of the triangle opposite to a rib (0 points)
        assertNull(
                triangle.findIntersections(
                        new Ray(
                                new Point(0, 0, 4),
                                new Vector(0, -1, 1))),
                "ERROR: Ray pass out of the triangle opposite to a rib returns a point");
        //TC02: Ray pass out of the triangle opposite to a vertex (0 points)
        assertNull(
                triangle.findIntersections(
                        new Ray(
                                new Point(-1, -1, 2),
                                new Vector(-1, 1, 0))),
                "ERROR: Ray pass out of the triangle opposite to a vertex returns a point");
        // =============== Boundary Values Tests ==================
        //TC11: Ray crosses the triangle on a rib of the triangle (0 points)
        assertNull(
                triangle.findIntersections(
                        new Ray(
                                new Point(2, 0, 0.5),
                                new Vector(2, 0, 0))),
                "ERROR: Ray crosses the triangle on a rib of the triangle returns a point");
        //TC12: Ray crosses the triangle on a vertex of the triangle (0 points)
        assertNull(
                triangle.findIntersections(
                        new Ray(
                                new Point(0, 0, 1),
                                new Vector(-1, 1, 0))),
                "ERROR: Ray crosses the triangle on a vertex of the triangle returns a point");
        //TC13: Ray crosses the continuation of a rib of the triangle (0 points)
        assertNull(
                triangle.findIntersections(
                        new Ray(
                                new Point(4, 4, 0),
                                new Vector(2, 0, 0))),
                "ERROR: Ray crosses the continuation of a rib of the triangle returns a point");

    }
}