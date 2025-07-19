package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.Intersection;



/**
 * simple implementation of a ray tracer
 * this class extends {@link RayTracerBase} and provides a basic implementation
 * of the ray tracing algorithm It computes the color of a given ray by checking
 * for intersections with geometries in the scene and returns the ambient light color
 * if an intersection is found or the background color if not
 */
public class SimpleRayTracer extends RayTracerBase {
    /*** The delta value used for shadow calculations*/
    private static final double DELTA = 0.1;

    /**
     * /**
     * constructs a simple ray tracer with the given scene
     *
     * @param scene the scene to be used for ray tracing
     */
    SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * calculates the color of a point in the scene
     * at the moment this method returns the scene's ambient light intensity if the point
     * is not {@code null}, and returns the background color otherwise
     *
     * @param intersection the point in the scene
     * @return the calculated color for the point
     */
    private Color calcColor(Intersection intersection, Vector rayDirection) {
        if (intersection == null)
            return scene.background;

        if (!preprocessIntersection(intersection, rayDirection))
            return Color.BLACK;


        return scene.ambientLight.getIntensity().scale(intersection.geometry.getMaterial().kA)
                .add(calcColorLocalEffects(intersection));
    }

    /**
     * traces the given ray and returns the calculated color
     * finds the closest intersection point between the ray and the scene's geometries
     * and calculates the color for that point
     *
     * @param ray the ray to trace
     * @return the calculated color for the ray
     */
    @Override
    public Color traceRay(Ray ray) {
        return calcColor(ray.findClosestIntersection(scene.geometries.calculateIntersections(ray)), ray.getDirection());
    }

    /**
     * prepares intersection data for rendering
     *
     * @param intersection intersection to process
     * @param direction    ray direction
     * @return true if intersection is valid
     */
    public boolean preprocessIntersection(Intersection intersection, Vector direction) {

        intersection.rayDirection = direction;
        intersection.normal = intersection.geometry.getNormal(intersection.point);
        intersection.directionDotN = Util.alignZero(direction.dotProduct(intersection.normal));
        return !Util.isZero(intersection.directionDotN);
    }

    /**
     * sets up light source calculations
     *
     * @param intersection intersection to process
     * @param light        light source to setup
     * @return true if light affects point
     */
    public boolean setLightSource(Intersection intersection, LightSource light) {
        intersection.light = light;
        intersection.l = light.getL(intersection.point);
        intersection.lDotN = Util.alignZero(intersection.l.dotProduct(intersection.normal));
        return intersection.lDotN * intersection.directionDotN > 0;
    }

    /**
     * Calculate the color of the local effects based on the material properties and the light source
     *
     * @param intersection the intersection point
     * @return the color of the local effects
     */
    Color calcColorLocalEffects(Intersection intersection) {
        Color color = intersection.geometry.getEmission();

        for (LightSource lightSource : scene.lights) {
            if (setLightSource(intersection, lightSource) && unshaded(intersection)) { // sign(nl) == sign(nv)
                Color iL = lightSource.getIntensity(intersection.point);
                color = color.add(
                        iL.scale(calcDiffusive(intersection)
                                .add(calcSpecular(intersection))));
            }
        }
        return color;
    }

    /**
     * /**
     * calculates diffuse lighting component
     *
     * @param intersection intersection to calculate for
     * @return diffuse lighting factor
     */
    private Double3 calcDiffusive(Intersection intersection) {
        return intersection.material.kD.scale(Math.abs(intersection.lDotN));
    }

    /**
     * calculates specular lighting component
     *
     * @param intersection intersection to calculate for
     * @return specular lighting factor
     */
    private Double3 calcSpecular(Intersection intersection) {
        return intersection.material.kS.scale(
                Math.max(0d,
                        Math.pow(
                                intersection.l.subtract(intersection.normal.scale(2 * intersection.lDotN)) // l - 2*<l,n>*n = r
                                        .dotProduct(intersection.rayDirection.scale(-1)) // <r,-v>
                                , intersection.material.nSh)) // (<r,-v>)^nsh
        );
    }

    private boolean unshaded(Intersection intersection) {
        Vector pointToLight = intersection.l.scale(-1); // from point to light source
        Vector delta = intersection.normal.scale(intersection.lDotN < 0 ? DELTA : -DELTA);
        Ray shadowRay = new Ray(intersection.point.add(delta), pointToLight);

        var intersections = scene.geometries.findIntersections(shadowRay);
        if (intersections == null) {
            return true;
        }
        if (intersections.isEmpty()) {
            return true;
        }
        for (Point i : intersections) {
            if (i.distance(shadowRay.getHead()) < intersection.light.getDistance(shadowRay.getHead())) {
                return false;
            }
        }
        return true;
    }

}