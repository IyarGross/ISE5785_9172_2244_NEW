package renderer;

import geometries.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

public class IntegrationTests {

    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setVpDistance(1)
            .setVpSize(3,3);

    public int calculationIntersections(Camera camera, Geometry geometry){
        int intersections = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                var result = geometry.findIntersections(camera.constructRay(3, 3, j, i));
                if (result != null)
                    intersections += result.size();
            }
        return intersections;
    }

    private  final Camera camera1 = cameraBuilder.setLocation(Point.ZERO).build();

    @Test
    void testsCameraSphereIntersections()  {

        Camera camera2 = cameraBuilder.setLocation(new Point(0, 0, 0.5)).build();

        //TC01:
        Sphere sphere1 = new Sphere(1, new Point(0, 0, -3));
        assertEquals(2, calculationIntersections(camera1,sphere1), "wrong number of intersections");


        //TC02:
        Sphere sphere2 = new Sphere(2.5, new Point(0, 0, -2.5));
        assertEquals(18, calculationIntersections(camera2,sphere2), "wrong number of intersections");

        //TC03:
        Sphere sphere3 = new Sphere(2, new Point(0, 0, -2));
        assertEquals(10, calculationIntersections(camera2,sphere3), "wrong number of intersections");

        //TC04:
        Sphere sphere4 = new Sphere(4, new Point(0, 0, -1.5));
        assertEquals(9, calculationIntersections(camera1,sphere4), "wrong number of intersections");

        //TC05:
        Sphere sphere5 = new Sphere(0.5, new Point(0, 0, 1));
        assertEquals(0, calculationIntersections(camera1,sphere5), "wrong number of intersections");

    }

    @Test
    void testsCameraPlaneIntersections()  {


        // TC01:
        Plane plane1 = new Plane(new Point(0,0,-2),new Vector(0,0,1));
        assertEquals(9, calculationIntersections(camera1,plane1), "wrong number of intersections");


        // TC02:
        Plane plane2 = new Plane(new Point(0,0,-2),new Vector(0,-1,3));
        assertEquals(9, calculationIntersections(camera1,plane2), "wrong number of intersections");


        // TC03:
        Plane plane3 = new Plane(new Point(0,0,-1.5),new Vector(0,1,-1));
        assertEquals(6, calculationIntersections(camera1,plane3), "wrong number of intersections");
    }

    @Test
    void testsCameraTriangleIntersections()  {

        // TC01:
        Triangle triangle1 = new Triangle(new Point(0,1,-2),new Point(-1,-1,-2),new Point(1,-1,-2));
        assertEquals(1, calculationIntersections(camera1,triangle1), "wrong number of intersections");

        // TC02:
        Triangle triangle2 = new Triangle(new Point(0,20,-2),new Point(-1,-1,-2),new Point(1,-1,-2));
        assertEquals(2, calculationIntersections(camera1,triangle2), "wrong number of intersections");

    }

}
