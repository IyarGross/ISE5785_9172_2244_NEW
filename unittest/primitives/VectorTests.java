package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTests {
    /**
     * Test method for primitives.Vector.Add
     */
    @Test
    void testAdd() {
        Vector v1 = new Vector(1, 0, 3);
        Vector v2 = new Vector(3, -2, -1);
        // ============ Equivalence Partitions Tests ==============
        assertDoesNotThrow(() ->v1.add(v2));
        assertEquals(new Vector(4, -2, 2),v1.add(v2),"ERROR: add of vector,output not as expected");

        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class,()->v1.add(new Vector(-1,0,-3)));// vec (0,0,0) not acceptable
    }
    /**
     * Test method for primitives.Vector.Scale
     */
    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector (1,0,3);
        assertDoesNotThrow(() ->v1.scale(2));
        assertEquals(new Vector(2,0,6), v1.scale(2), "ERROR-scale output");

        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class,()->v1.scale(0));// vec (0,0,0) not acceptable
    }

    @Test
    void testDotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-1, -2, -3);
        Vector v3 = new Vector(0, 3, -2);
        // ============ Equivalence Partitions Tests ==============
        assertDoesNotThrow(() ->v1.dotProduct(v2));
        assertEquals(-14,v1.dotProduct(v2) ,"ERROR: dotProduct() wrong output");
        // =============== Boundary Values Tests ==================
        assertDoesNotThrow(() ->v1.dotProduct(v3));
        assertEquals(0,v1.dotProduct(v3),"ERROR: dotProduct() for orthogonal vectors can't be zero");

    }
    /**
     * Test method primitives.Vector.CrossProduct
     */
    @Test
    void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(1, 2, 3);
        Vector v3 = new Vector(0, 3, -2);
        // ============ Equivalence Partitions Tests ==============
        assertDoesNotThrow(() ->v1.crossProduct(v3));
        Vector vr = v1.crossProduct(v3);
        assertEquals(v1.length() * v3.length(),vr.length() ,"ERROR: crossProduct() wrong result length");
        assertTrue(isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)),"ERROR: crossProduct() result is not orthogonal to its operands");
        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class,()-> v1.crossProduct(v2));// vec (0,0,0) not acceptable
    }

    /**
     * Test method primitives.Vector.LengthSquared
     */
    @Test
    void testLengthSquared() {

        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1, 2, 3);
        assertDoesNotThrow(() ->v1.lengthSquared());
        assertEquals(14,v1.lengthSquared(),"ERROR: lengthSquared() wrong output");
    }

    /**
     * Test method primitives.Vector.Length
     */
    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 =new Vector(2, 4, 4);
        assertDoesNotThrow(() ->v1.length());
        assertEquals(6,v1.length(),"ERROR: length() wrong value");
    }
    /**
     * Test method primitives.Vector.Normalize
     */
    @Test
    void testNormalize() {
        // ============ Equivalence Partitions Tests ==============
        Vector v = new Vector(1, 2, 3);
        assertDoesNotThrow(() -> v.normalize());
        Vector u = v.normalize();
        assertTrue(isZero(u.length()-1),"ERROR: the normalized vector is not a unit vector"  );//checks if the new length(1)-1==0
        assertThrows(IllegalArgumentException.class,()->v.crossProduct(u));
        assertEquals(0, v.dotProduct(u), "ERROR: the normalized vector is opposite to the original one");

    }
}