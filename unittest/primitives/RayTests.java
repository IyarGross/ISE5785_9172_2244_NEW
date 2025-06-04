package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTests {

    /**
     * Test method for Ray.getPoint with positive distance
     */
    @Test
    void testGetPointPositive() {
        // ============ Equivalence Partitions Tests ==============
        Ray ray = new Ray(new Point(1, 2, 3), new Vector(1, 0, 0));
        assertDoesNotThrow(() -> ray.getPoint(5.0));
        Point result = ray.getPoint(5.0);
        Point expected = new Point(6, 2, 3);
        assertEquals(expected, result, "ERROR: Positive distance calculation failed");
    }

    /**
     * Test method for Ray.getPoint with negative distance
     */
    @Test
    void testGetPointNegative() {
        // =============== Boundary Values Tests ==================
        Ray ray = new Ray(new Point(1, 2, 3), new Vector(1, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> ray.getPoint(-5.0), "ERROR: Negative distance should throw an exception");
    }

    /**
     * Test method for Ray.getPoint with zero distance
     */
    @Test
    void testGetPointZero() {
        // =============== Boundary Values Tests ==================
        Ray ray = new Ray(new Point(1, 2, 3), new Vector(1, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> ray.getPoint(0.0),
                "ERROR: getPoint(0) should throw exception for zero vector");
    }

    /**
     * Test method for {@link primitives.Ray#findClosestPoint(List)}
     */
    @Test
    void testFindClosestPoint() {
        Ray ray = new Ray(new Point(0, 1, 0), new Vector(0, 1, 0));
        List<Point> list = new LinkedList<Point>();
        Point point020 = new Point(0, 2, 0);
        Point point110 = new Point(1, 1, 0);
        Point point200 = new Point(2, 0, 0);
        Point point364 = new Point(3, 6, 4);
        Point point158 = new Point(1, 5, 8);
        Point point333 = new Point(3, 3, 3);

        // ============ Equivalence Partitions Tests ==============
        //TC01: A point in the middle of the list is the one closest to the beginning of the ray

        list.add(point158);
        list.add(point200);
        list.add(point020);
        list.add(point333);
        list.add(point364);
        assertEquals(
                point020,
                ray.findClosestPoint(list),
                "Error: when sending a list that the point closest " +
                        "of the beginning of the ray is in the middle returns a wrong point");

        // =============== Boundary Values Tests ==================
        //TC02: An empty list (the method should return a null value).

        list.clear();
        assertNull(
                ray.findClosestPoint(list),
                "Error: when sending an empty list doesnt return null");

        //TC03: The first point is closest to the beginning of the horn

        list.clear();
        list.add(point020);
        list.add(point158);
        list.add(point200);
        list.add(point333);
        list.add(point364);
        assertEquals(
                point020,
                ray.findClosestPoint(list),
                "Error: when sending a list that the point closest " +
                        "of the beginning of the ray is the first returns a wrong point");

        //TC04: The last point is closest to the beginning of the horn

        list.clear();
        list.add(point158);
        list.add(point200);
        list.add(point364);
        list.add(point333);
        list.add(point110);
        assertEquals(
                point110,
                ray.findClosestPoint(list),
                "Error: when sending a list that the point closest " +
                        "of the beginning of the ray is the last returns a wrong point");

    }
}
