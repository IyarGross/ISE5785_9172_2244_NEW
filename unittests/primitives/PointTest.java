package primitives;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link primitives.Point} class methods.
 */
class PointTest {

    /**
     * Tests {@link primitives.Point#subtract(primitives.Point)} including normal and boundary cases.
     */
    @Test
    void subtract() {
        Point P1 = new Point(62.47, 98.64, 22.37);
        Point P2 = new Point(95.43, 58.93, 71.11);
        Vector result = P1.subtract(P2);
        // ============ Equivalence Partitions Tests ==============
        //TC 1.1.01 ...
        assertEquals(new Vector(-32.96, 39.71, -48.74), result, "Subtraction failed");

        // =============== Boundary Values Tests ==================
        //TC 1.1.02 ...
        assertThrows(IllegalArgumentException.class, () -> P1.subtract(P1), "Subtract() does not throw an exception when needed");
    }

    /**
     * Tests {@link primitives.Point#add(primitives.Point)} including normal and boundary cases.
     */
    @Test
    void add() {
        Point P1 = new Point(62.47, 98.64, 22.37);
        Point P2 = new Point(95.43, 58.93, -71.11);
        Point P3 = new Point(-95.43, -58.93, 71.11);
        Point result = P1.add(P2);
        // ============ Equivalence Partitions Tests ==============
        //TC 1.2.01 ...
        assertEquals(new Point(157.9, 157.57, -48.74), result, "addition failed");

        // =============== Boundary Values Tests ==================
        //TC 1.2.02 ...
        assertEquals(Point.ZERO, P2.add(P3), "addition failed when expected result is zero");
    }

    /**
     * Tests {@link primitives.Point#distanceSquared(primitives.Point)} for correctness.
     */
    @Test
    void distanceSquared() {
        Point P1 = new Point(62.47, 98.64, 22.37);
        Point P2 = new Point(95.43, 58.93, 71.11);
        double result = P1.distanceSquared(P2);
        // ============ Equivalence Partitions Tests ==============
        //TC 1.3.01 ...
        assertEquals(5038.8333, result,0.0001, "distanceSquared failed");

        // =============== Boundary Values Tests ==================
        //TC 1.3.02 ...
        assertEquals(0, P1.distanceSquared(P1), 0.0001,"distanceSquared failed at distance zero" );
    }

    /**
     * Tests {@link primitives.Point#distance(primitives.Point)} for correctness.
     */
    @Test
    void distance() {
        Point P1 = new Point(62.47, 98.64, 22.37);
        Point P2 = new Point(95.43, 58.93, 71.11);
        double result = P1.distance(P2);
        // ============ Equivalence Partitions Tests ==============
        //TC 1.4.01 ...
        assertEquals(70.98473990936363, result,0.0001, "distance failed");

        // =============== Boundary Values Tests ==================
        //TC 1.4.02 ...
        assertEquals(0, P1.distance(P1), 0.0001,"distance failed at distance zero" );

    }

}