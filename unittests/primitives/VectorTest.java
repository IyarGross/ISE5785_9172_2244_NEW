package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class VectorTest {

    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in
     * assertEquals
     */
    private static final double DELTA = 0.000001;

    /** making sure that the constructor will not allow vector zero */
    @Test
    void testConstructor() {

        // =============== Boundary Values Tests ==================
        // TC01 zero vector in the constructor with 3 parameters
        assertThrows(IllegalArgumentException.class, () -> new Vector(0,0,0), "3 double constructor allowed vector to be zero");
        // TC01 zero vector in the constructor with Double3
        assertThrows(IllegalArgumentException.class, () -> new Vector(new Double3(0, 0, 0)), "Double3 constructor allowed vector to be zero");
    }
    @Test
    void testLengthSquared() {
        Vector V1 = new Vector(62.47, 98.64, 22.37);
        double result = V1.lengthSquared();
        // ============ Equivalence Partitions Tests ==============
        //TC 2.1.01 same test for all vectors
        assertEquals(14132.7674, result,DELTA, "lengthSquared failed");
    }

    @Test
    void testLength() {
        Vector V1 = new Vector(62.47, 98.64, 22.37);
        double result = V1.length();
        // ============ Equivalence Partitions Tests ==============
        //TC 2.2.01 same test for all vectors
        assertEquals(118.8813164, result,DELTA, "length failed");
    }

    @Test
    void testAdd() {
        Vector V1 = new Vector(62.47, 98.64, 22.37);
        Vector V2 = new Vector(95.43, 58.93, -71.11);
        Vector V3 = new Vector(-95.43, -58.93, 71.11);

        Vector result = V1.add(V2);
        // ============ Equivalence Partitions Tests ==============
        //TC 2.3.01 almost every case
        assertEquals(new Vector(157.9, 157.57, -48.74), result, "addition failed");

        // =============== Boundary Values Tests ==================
        //TC 2.3.02 the add result is zero
        assertThrows(IllegalArgumentException.class, () -> V2.add(V3), "add() does not throw an exception when needed");
    }

    /**
     * Test method for {@link primitives.Vector#subtract(primitives.Point)}.
     */
    @Test
    void testSubtract() {
        Point P1 = new Point(62.47, 98.64, 22.37);
        Point P2 = new Point(95.43, 58.93, 71.11);
        Vector result = P1.subtract(P2);
        // ============ Equivalence Partitions Tests ==============
        //TC 1.1.01 almost every case
        assertEquals(new Vector(-32.96, 39.71, -48.74), result, "Subtraction failed");

        // =============== Boundary Values Tests ==================
        //TC 1.1.02 if the result of subtract is zero
        assertThrows(IllegalArgumentException.class, () -> P1.subtract(P1), "Subtract() does not throw an exception when needed");
    }

    @Test
    void testScale() {
        Vector v1 = new Vector(6.47, 9.64, 2.37);
        double positiveScalar = 2.57;
        double negativeScalar = -2.57;
        Vector expectedResultPositive  = new Vector(16.6279,24.7748 ,6.0909 );
        Vector expectedResultNegative  = new Vector(-16.6279,-24.7748 ,-6.0909 );

        // ============ Equivalence Partitions Tests ==============

        //TC 2.4.01 Positive Scalar
        assertEquals(expectedResultPositive, v1.scale(positiveScalar), "multiplication by a positive scalar has failed");

        // TC 2.4.02 Negative Scalar
        assertEquals(expectedResultNegative, v1.scale(negativeScalar), "multiplication by a negative scalar has failed");

        // =============== Boundary Values Tests ==================
        //TC 2.4.02 multiplying by zero
        assertThrows(IllegalArgumentException.class, () -> v1.scale(0), "scale() does not throw an exception when multiplying by zero");
    }

    @Test
    void dotProduct() {
        Vector v = new Vector(0,1,0);

        // ============ Equivalence Partitions Tests ==============
        //TC01 45 degrees rotation, cos(45) = sqrt(2)/2, 1 * sqrt(2) * sqrt(2)/2 = 1
        assertEquals(1, v.dotProduct(new Vector(1,1,0)), DELTA, "dotProduct failed with a rotation of 45 degrees");
        //TC02 135 degrees rotation, cos(135) = -sqrt(2)/2, 1 * sqrt(2) * -sqrt(2)/2 = -1
        assertEquals(-1, v.dotProduct(new Vector(1,-1,0)), DELTA, "dotProduct failed with a rotation of 135 degrees");
        //TC03 225 degrees rotation, cos(225) = -sqrt(2)/2, 1 * sqrt(2) * -sqrt(2)/2 = -1
        assertEquals(-1, v.dotProduct(new Vector(-1,-1,0)), DELTA, "dotProduct failed with a rotation of 225 degrees");
        //TC04 degrees rotation, cos(315) = sqrt(2)/2, 1 * sqrt(2) * sqrt(2)/2 = 1
        assertEquals(1, v.dotProduct(new Vector(-1,1,0)), DELTA, "dotProduct failed with a rotation of 315 degrees");
        //TC05 45 check that v.dotProduct(v) == |v|^2
        assertEquals(17636.2619,new Vector(95.43, 58.93, -71.11).dotProduct(new Vector(95.43, 58.93, -71.11)), DELTA, "dotProduct of a vector with himself does not  does not equal to the length square");


        // =============== Boundary Values Tests ==================
        //TC11 360 degrees rotation, cos(360) = 1, 2*1*1 = 2
        assertEquals(2, v.dotProduct(new Vector(0,2,0)), DELTA, "dotProduct failed with a rotation of 360 degrees");
        //TC12 2.5.02 90 degrees rotation, cos(90) = 0, 1*1*0 = 0
        assertEquals(0, v.dotProduct(new Vector(1,0,0)), DELTA, "dotProduct failed with a rotation of 90 degrees");
        //TC13 2.5.02 180 degrees rotation, cos(180) = -1, 1*1*-1 = -1
        assertEquals(-1, v.dotProduct(new Vector(0,-1,0)), DELTA, "dotProduct failed with a rotation of 180 degrees");
        //TC14 2.5.02 270 degrees rotation, cos(270) = 0, 1*1*0 = 0
        assertEquals(0, v.dotProduct(new Vector(-1,0,0)), DELTA, "dotProduct failed with a rotation of 270 degrees");
        //TC15 check that when |v| = 1, v.dotProduct(v) == 1
        assertEquals(1, new Vector(0.267261241912,0.534522483825,0.801783725737).dotProduct(new Vector(0.267261241912,0.534522483825,0.801783725737)), DELTA, "dotProduct of the unit vector with himself does not equal 1");
    }

    @Test
    void crossProduct() {
        Vector v = new Vector(0,1,0);

        // ============ Equivalence Partitions Tests ==============

        //TC01 45 degrees rotation, checking in both direction
        assertEquals(new Vector(0,0,-1), v.crossProduct(new Vector(1,1,0)), "crossProduct failed with a rotation of 45 degrees");
        assertEquals(new Vector(0,0,1), new Vector(1,1,0).crossProduct(v), "crossProduct failed with a rotation of 45 degrees");

        //TC02 135 degrees rotation, checking in both direction
        assertEquals(new Vector(0,0,-1), v.crossProduct(new Vector(1,-1,0)), "crossProduct failed with a rotation of 135 degrees");
        assertEquals(new Vector(0,0,1), new Vector(1,-1,0).crossProduct(v), "crossProduct failed with a rotation of 135 degrees");

        //TC03 225 degrees rotation, checking in both direction
        assertEquals(new Vector(0,0,1), v.crossProduct(new Vector(-1,-1,0)), "crossProduct failed with a rotation of 225 degrees");
        assertEquals(new Vector(0,0,-1),  new Vector(-1,-1,0).crossProduct(v), "crossProduct failed with a rotation of 225 degrees");

        //TC04 315 degrees rotation, checking in both direction
        assertEquals(new Vector(0,0,1), v.crossProduct(new Vector(-1,1,0)), "crossProduct failed with a rotation of 315 degrees");
        assertEquals(new Vector(0,0,-1),  new Vector(-1,1,0).crossProduct(v), "crossProduct failed with a rotation of 315 degrees");

        // =============== Boundary Values Tests ==================
        //TC11 parallel vectors angle of 0 degrees
        assertThrows(IllegalArgumentException.class, () -> new Vector(9.0, 4.0, 3.0).crossProduct(new Vector(4.50,2.0 ,1.5)), "crossProduct() does not throw an exception when needed");

        //TC12 2.5.02 90 degrees rotation, checking in both direction
        assertEquals(new Vector(0,0,-1), v.crossProduct(new Vector(1,0,0)), "crossProduct failed with a rotation of 90 degrees");
        assertEquals(new Vector(0,0,1), new Vector(1,0,0).crossProduct(v), "crossProduct failed with a rotation of 90 degrees");

        //TC13 parallel vectors angle of 180 degrees
        assertThrows(IllegalArgumentException.class, () -> new Vector(9.0, 4.0, 3.0).crossProduct(new Vector(-4.50,-2.0 ,-1.5)), "crossProduct() does not throw an exception when needed");

        //TC14  270 degrees rotation, checking in both direction
        assertEquals(new Vector(0,0,1), v.crossProduct(new Vector(-1,0,0)), "crossProduct failed with a rotation of 270 degrees");
        assertEquals(new Vector(0,0,-1), new Vector(-1,0,0).crossProduct(v), "crossProduct failed with a rotation of 270 degrees");

    }

    @Test
    void normalize() {
        Vector V1 = new Vector(64,48,60);
        Vector result = V1.normalize();

        // ============ Equivalence Partitions Tests ==============
        //TC01 normalizing almost any vector
        assertEquals(new Vector(0.64,0.48,0.6), result, "normalize failed");

        // =============== Boundary Values Tests ==================
        //TC02 normalizing a unit vector
        assertEquals(new Vector(0.64,0.48,0.6), new Vector(0.64,0.48,0.6).normalize(), "normalize failed to normalize a unit vector");

//        \\\\                ////\\\\                ////      |||||||||||||||      \\\\                ////\\\\                ////
//          \\\\            ////    \\\\            ////        |||         |||        \\\\            ////    \\\\            ////
//            \\\\        ////        \\\\        ////          |||         |||          \\\\        ////        \\\\        ////
//              \\\\    ////            \\\\    ////            |||         |||            \\\\    ////            \\\\    ////
//                \\\\////                \\\\////              |||||||||||||||              \\\\////                \\\\////
    }
}