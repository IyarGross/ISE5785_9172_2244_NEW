package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTests {
    /**
     * Test method for primitives.Point.Subtract
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(5, 4, 3);
        Point p2 = new Point(3, 2, 0);
        assertDoesNotThrow(() -> p1.subtract(p2));
        Vector result = p1.subtract(p2);
        Vector expected = new Vector(2, 2, 3);

        assertEquals(expected, result, "ERROR-subtract");

        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class, () -> p1.subtract(new Point(5, 4, 3)));//subtract 2 identical point, will give zero Vector which is not allowed on this project

    }

    /**
     * Test method for primitives.Point.Add
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        Point P = new Point(5, 4, 3);
        Vector v = new Vector(4, 3, 0);
        assertDoesNotThrow(() -> P.add(v));
        Point result = P.add(v);
        Point expected = new Point(9, 7, 3);
        assertEquals(expected, result, "ERROR-add");
    }

    /**
     * Test method for primitives.Point.DistanceSquared
     */
    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(4, 6, 4);
        Point p2 = new Point(2, 0, 1);
        assertDoesNotThrow(() -> p1.distanceSquared(p2));
        double result = p1.distanceSquared(p2);
        double expected = 49;
        assertEquals(expected, result, "ERROR-distanceSquared");
    }

    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(6, 4, 4);
        Point p2 = new Point(0, 1, 2);
        assertDoesNotThrow(() -> p1.distance(p2));
        double result = p1.distance(p2);
        double expected = 7;
        assertEquals(expected, result, "ERROR-distance");
    }
}