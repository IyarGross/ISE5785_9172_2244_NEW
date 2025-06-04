package renderer;

import geometries.Geometry;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CameraIntersectionsIntegrationTests {
    /**
     * Camera builder for the tests
     */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            //         .setRayTracer(new SimpleRayTracer(new Scene("Test")))
            //       .setImageWriter(new ImageWriter("Test", 1, 1))
            .setLocation(new Point(0, 0, 0.5))
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setVpDistance(1);

    Camera camera1 = cameraBuilder.setVpSize(6, 6).build();
    /**
     * Test method for the integration between camera and sphere
     */
    @Test
    void testCameraSphere() {
        // TC01: small sphere with radius=1 (2 points)
        Sphere sphere1 = new Sphere(1, new Point(0, 0, -3));
        assertEquals(2
                , findIntersectionWithGeometry(camera1, sphere1),
                "ERROR: wrong intersection points when:" +
                        " small sphere with radius=1 (2 points)");

        // TC02: big sphere with radius=2.5 (18 points)
        Sphere sphere2 = new Sphere(9, new Point(0, 0, -9));
        assertEquals(18
                , findIntersectionWithGeometry(camera1, sphere2),
                "ERROR: wrong intersection points when:" +
                        "big sphere with radius=2.5 (18 points)");

        // TC03: medium sphere with radius=2 (10 points)
        Sphere sphere3 = new Sphere(5, new Point(0, 0, -5));
        assertEquals(10
                , findIntersectionWithGeometry(camera1, sphere3),
                "ERROR: wrong intersection points when:" +
                        " medium sphere with radius=2 (10 points)");

        // TC04: big sphere with radius=5 part of the sphere is behind the camera (9 points)
        Sphere sphere4 = new Sphere(5, new Point(0, 0, -1));
        assertEquals(9
                , findIntersectionWithGeometry(camera1, sphere4),
                "ERROR: wrong intersection points when:" +
                        "big sphere with radius=5 part of the sphere is behind the camera (9 points)");

        // TC05: small sphere with radius=0.5 all the sphere is behind the camera (0 points)
        Sphere sphere5 = new Sphere(0.5, new Point(0, 0, 1));
        assertEquals(0
                , findIntersectionWithGeometry(camera1, sphere5),
                "ERROR: wrong intersection points when:" +
                        " small sphere with radius=0.5 all the sphere is behind the camera (0 points)");
    }
    /**
     * Test method for the integration between camera and plane
     */
    @Test
    void testCameraPlane() {
        // TC01: the plane is parallel to the camera (9 points)
        Plane plane1 = new Plane(
                new Point(0, 0, -3),
                new Point(4, 1, -3),
                new Point(3, 2, -3));
        assertEquals(9
                , findIntersectionWithGeometry(camera1, plane1),
                "ERROR: wrong intersection points when:" +
                        "the plane is parallel to the camera (9 points)");

        // TC02: only part of the plane has intersections (6 points)
        Plane plane2 = new Plane(
                new Point(0, 0, -3),
                new Point(4, 1, -9),
                new Point(3, -1, -1));
        assertEquals(5
                , findIntersectionWithGeometry(camera1, plane2),
                "ERROR: wrong intersection points when:" +
                        "only part of the plane has intersections (6 points)");//the expected not sure
    }

    /**
     * Test method for the integration between the camera and triangle
     */
    @Test
    void testCameraTriangle() {
        // TC01: very small triangle  (1 point)
        Triangle triangle1 = new Triangle(
                new Point(1, -1, -2),
                new Point(0, 1, -2),
                new Point(-1, -1, -2));
        assertEquals(1
                , findIntersectionWithGeometry(camera1, triangle1),
                "ERROR: wrong intersection points when:" +
                        "very small triangle  (1 point)");

        // TC02: medium triangle (2 points)
        Triangle triangle2 = new Triangle(
                new Point(0, 20, -2),
                new Point(1, -1, -2),
                new Point(-1, -1, -2));
        assertEquals(2
                , findIntersectionWithGeometry(camera1, triangle2),
                "ERROR: wrong intersection points when:" +
                        "medium triangle (2 points)");

    }

    /**
     * A method to find the intersections between the camera rays and the geometry
     *
     * @param camera   the camera
     * @param geometry the geometry that i check the intersections of the rays with
     * @return the number of intersection points between the camera rays and the geometry
     */
    public int findIntersectionWithGeometry(Camera camera, Geometry geometry) {
        int count = 0;
        Ray ray;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ray = camera.constructRay(3, 3, j, i);
                if (geometry.findIntersections(ray) != null)
                    count += geometry.findIntersections(ray).size();
            }

        }

        return count;
    }

}
