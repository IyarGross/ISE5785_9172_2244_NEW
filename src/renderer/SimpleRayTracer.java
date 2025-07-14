package renderer;
import geometries.Intersectable.Intersection;
import primitives.*;
import scene.Scene;
import lighting.LightSource;
import primitives.Color;
import primitives.Vector;
import primitives.Ray;
import java.util.List;

import static primitives.Util.isZero;
import static java.lang.Math.*;
import static primitives.Util.alignZero;

/**
 * This class represents a simple ray tracer
 *
 */

public class SimpleRayTracer extends RayTracerBase {

    /**
     * constructor for scene
     *
     * @param scene the scene
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }
    public boolean preprocessIntersection(Intersection intersection, Vector intersectionRay){
        intersection.v1 = intersectionRay;
        intersection.normal = intersection.geometry.getNormal(intersection.point);
        intersection.Dot_Product  = alignZero(intersection.v1
                .dotProduct(intersection.normal));
        return intersection.Dot_Product  != 0;
    }

    private Color calcColorLocalEffects(Intersection intersection) {
        Color color = intersection.geometry.getEmission();

        for (LightSource lightSource : scene.lights) {
            if (setLightSource(intersection, lightSource)) { // sign(nl) == sign(nv)
                Color iL = lightSource.getIntensity(intersection.point);
                color = color.add(
                        iL.scale(calcDiffusive(intersection)
                                .add(calcSpecular(intersection))));
            }
        }
        return color;
    }

    public boolean setLightSource(Intersection intersection, LightSource light) {
        intersection.v2 = light.getL(intersection.point);
        intersection.Dot_Product_light = intersection.normal.dotProduct(intersection.v2);
        intersection.v3 = intersection.v1.subtract(intersection.v2);
        intersection.light = light;
        return !(isZero(intersection.Dot_Product) || isZero(intersection.Dot_Product_light));
    }


    private Double3 calcDiffusive(Intersection intersection) {
        return intersection.geometry.getMaterial().kD.scale(abs(intersection.Dot_Product_light));
    }

    private Double3 calcSpecular(Intersection intersection) {
        Vector r = intersection.v2
                .subtract(intersection.normal.scale(2 * intersection.Dot_Product_light));
        return intersection.geometry.getMaterial().kS
                .scale(
                        Math.pow(
                                max(
                                        0,
                                        -intersection.v1.dotProduct(r)),
                                intersection.material.nShininess));
    }

    @Override
    public Color traceRay(Ray ray) {
        var intersections = scene.geometries.calculateIntersections(ray);
        return intersections == null
                ? scene.background
                : calcColor(ray.findClosestIntersection(intersections), ray);
    }


    /**
     * Calculate the color of the intersection point
     * @param intersection the intersection point
     * @param ray the ray that created the intersection
     * @return the color of the intersection point
     */
    public Color calcColor(Intersection intersection, Ray ray) {
        if(preprocessIntersection(intersection, ray.getDirection())) {
            return scene.ambientLight.getIntensity()
                    .scale(intersection.geometry.getMaterial().kA)
                    .add(calcColorLocalEffects(intersection));
        }
        return Color.BLACK;
    }
}
