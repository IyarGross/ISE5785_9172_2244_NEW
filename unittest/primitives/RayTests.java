package primitives;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

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
}
