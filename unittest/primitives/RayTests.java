//rayTest.java
package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    private static final double DELTA = 0.000001;
    /**
     * Test method for {@link primitives.Ray#getPoint(double)}
     */
    @Test
    void getPoint() {

        // ============ Equivalence Partitions Tests ==============
        //TC01: positive distance between head and point
        Ray ray1=new Ray(new Point(0,0,1),new Vector(0,0,3).normalize());
        Point point1=new Point(0,0,2);
        assertEquals(
                0,
                ray1.getPoint(1).distance(point1),
                "ERROR: getPoint returns wrong point when the distance between head and point is positive"
        );


        //TC02: negative distance between head and point
        Ray ray2=new Ray(new Point(0,2,0),new Vector(0,1,0).normalize());
        Point point2=new Point(0,1,0);
        assertEquals(
                0,
                ray2.getPoint(-1).distance(point2),
                DELTA,
                "ERROR: getPoint returns wrong point when the distance between head and point is negative"
        );

        // =============== Boundary Values Tests ==================
        //TC03: distance between head and point = 0
        Ray ray3=new Ray(new Point(1,2,3),new Vector(4,5,6));
        assertEquals(
                0,
                ray3.getPoint(0).distance(ray3.getHead()),
                "ERROR: getPoint returns wrong point when the distance between head and point is zero"
        );
    }
    @Test
    void testFindClosestPoint() {

        Point p100 = new Point(1, 0, 0);
        Point p200 = new Point(2, 0, 0);
        Point p300 = new Point(3, 0, 0);
        Vector v100 = new Vector(1,0,0);
        List<Point> list = List.of(p100, p200, p300);
        // ============ Equivalence Partitions Tests ==============
        // EP01: closest point is in the middle of the list

        assertEquals(p200,new Ray(new Point(2.1,0,0),v100).findClosestPoint(list),"closest point is in the middle of the list" );


        // =============== Boundary Values Tests ==================

        // BV01: empty list
        assertNull(new Ray(new Point(2.1,0,0),v100).findClosestPoint(null),"empty list" );


        // BV02:  first point in the list
        assertEquals(p100,new Ray(new Point(1.1,0,0),v100).findClosestPoint(list),"first point in the list" );


        // BV03: last point in the list
        assertEquals(p300,new Ray(new Point(3.1,0,0),v100).findClosestPoint(list),"last point in the list" );

    }

}