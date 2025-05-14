package primitives;

import java.util.Objects;

/**
 * Ray class represents a ray (=vector that starts from a specific point) in 3D Cartesian coordinates
 */
public class Ray {
    final private Point point;
    final private Vector dir;
    /**
     * Ray construction is made from a start point and a direction vector
     * The normalized direction vector is being saved
     * @param point starting point
     * @param dir direction of the ray
     */
    public Ray(Point point, Vector dir) {
        this.point = point;
        this.dir = dir.normalize();
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;

        return Objects.equals(point, ray.point) && dir.equals(ray.dir);
    }

    /**
     * Gets the starting point
     * @return starting point of the ray
     */
    final public Point getP0() {
        return point;
    }

    /**
     * Gets the normalized direction vector
     * @return normalized direction vector of the ray
     */
    final public Vector getDir() {
        return dir;
    }
}
