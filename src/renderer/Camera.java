package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.MissingResourceException;

import static primitives.Util.isZero;

/**
 * The {@code Camera} class represents a pinhole camera in 3D space.
 * It defines the position and orientation of the camera and is responsible for constructing rays
 * through a view plane to simulate perspective projection in a 3D scene.
 */
public class Camera implements Cloneable {

    /* === Camera Fields === */

    /**
     * The location of the camera in 3D space
     */
    private Point location;

    /**
     * The direction the camera is pointing to
     */
    private Vector Vto;

    /**
     * The upward direction from the camera
     */
    private Vector Vup;

    /**
     * The rightward direction from the camera, orthogonal to Vto and Vup
     */
    private Vector Vright;

    /**
     * Distance from the camera to the view plane
     */
    private double distance = 0.0;

    /**
     * Width of the view plane
     */
    private double VPwidth = 0.0;

    /**
     * Height of the view plane
     */
    private double VPhight = 0.0;

    /**
     * Horizontal resolution of the view plane
     */
    private int nX = 1;

    /**
     * Vertical resolution of the view plane
     */
    private int nY = 1;

    private ImageWriter imageWriter;

    private RayTracerBase rayTracer;

    /**
     * Default constructor
     */
    public Camera() {
    }

    /**
     * Creates a new {@code Builder} instance for constructing a {@code Camera} object.
     *
     * @return a {@code Builder} for {@code Camera}
     */
    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * Constructs a {@code Ray} through a specific pixel in the view plane.
     *
     * @param nX total number of horizontal pixels
     * @param nY total number of vertical pixels
     * @param j  the column index of the pixel
     * @param i  the row index of the pixel
     * @return the {@code Ray} that passes through the specified pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point Pc = location.add(Vto.scale(distance)); // Image center
        double Ry = VPhight / nY; // Pixel height
        double Rx = VPwidth / nX; // Pixel width
        double yi = -(i - (nY - 1) / 2.0) * Ry;
        double xj = (j - (nX - 1) / 2.0) * Rx;

        Point Pij = Pc;
        if (!isZero(xj)) {
            Pij = Pij.add(Vright.scale(xj));
        }
        if (!isZero(yi)) {
            Pij = Pij.add(Vup.scale(yi));
        }

        Vector dir = Pij.subtract(location);
        return new Ray(location, dir);
    }

    /**
     * Creates and returns a copy of this Camera.
     *
     * @return a clone of this Camera
     * @throws CloneNotSupportedException if the Camera cannot be cloned
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Camera cloned = new Camera();

        Builder builder = cloned.getBuilder()
                .setLocation(this.location)
                .setDirection(this.Vto, this.Vup)
                .setVpDistance(this.distance)
                .setVpSize(this.VPwidth, this.VPhight)
                .setResolution(this.nX, this.nY)
                .setRayTracer(this.rayTracer.scene, RayTracerType.SIMPLE)
                .setImageWriter();

        return builder.camera;
    }

    /**
     * Renders the image by casting rays through each pixel of the view plane.
     *
     * @return the current Camera instance (for method chaining)
     */
    public Camera renderImage() {
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                castRay(i, j); // Cast a ray through pixel (i, j)
            }
        }
        return this;
    }

    /**
     * Draws a grid on the image by coloring pixels at regular intervals.
     *
     * @param interval the spacing between grid lines (in pixels)
     * @param color    the color used for the grid lines
     * @return the current Camera instance (for method chaining)
     */
    public Camera printGrid(int interval, Color color) {
        for (int i = 0; i < nX; i++)
            for (int j = 0; j < nY; j++)
                if (i % interval == 0 || j % interval == 0)
                    this.imageWriter.writePixel(i, j, color); // Paint grid line
        return this;
    }

    /**
     * Writes the rendered image to a file with the given name.
     *
     * @param name the name of the output image file
     * @return the current Camera instance (for method chaining)
     */
    public Camera writeToImage(String name) {
        this.imageWriter.writeToImage(name); // Save image to file
        return this;
    }

    /**
     * Casts a ray through a specific pixel, computes its color using the ray tracer,
     * and writes the color to the image.
     *
     * @param x the horizontal pixel index
     * @param y the vertical pixel index
     */
    private void castRay(int x, int y) {
        Ray r = this.constructRay(nX, nY, x, y); // Construct a ray through the pixel
        Color c = this.rayTracer.traceRay(r); // Trace the ray to determine the color
        this.imageWriter.writePixel(x, y, c); // Write the color to the image
    }

    /**
     * Builder class for constructing a {@code Camera} with a fluent API.
     */
    public static class Builder {

        private final Camera camera;

        /**
         * Default constructor initializing a new Camera instance
         */
        public Builder() {
            this.camera = new Camera();
        }

        /**
         * Constructor using an existing Camera object.
         *
         * @param camera the Camera to initialize the builder with
         */
        public Builder(Camera camera) {
            this.camera = camera;
        }

        /**
         * Sets the camera's location in 3D space.
         *
         * @param loc the camera's position
         * @return this builder instance
         */
        public Builder setLocation(Point loc) {
            this.camera.location = loc;
            return this;
        }

        /**
         * Sets the camera direction vectors directly.
         *
         * @param vto forward direction vector
         * @param vup upward direction vector (must be orthogonal to vto)
         * @return this builder instance
         * @throws IllegalArgumentException if the vectors are not orthogonal
         */
        public Builder setDirection(Vector vto, Vector vup) {
            if (!isZero(vto.dotProduct(vup)))
                throw new IllegalArgumentException("Invalid direction vectors - must be orthogonal");

            this.camera.Vto = vto.normalize();
            this.camera.Vup = vup.normalize();
            this.camera.Vright = vto.crossProduct(vup).normalize();
            return this;
        }

        /**
         * Sets the camera direction based on a target point and an upward vector.
         *
         * @param target the point to aim the camera at
         * @param vup    the upward vector
         * @return this builder instance
         */
        public Builder setDirection(Point target, Vector vup) {
            if (this.camera.location == null)
                throw new IllegalArgumentException("Must set location before trying to implement direction using a point and a vector");

            if (this.camera.location.equals(target))
                throw new IllegalArgumentException("The target point can't be the same as the location of the camera");

            this.camera.Vto = target.subtract(this.camera.location).normalize();
            this.camera.Vup = vup.normalize();
            this.camera.Vright = this.camera.Vto.crossProduct(this.camera.Vup).normalize();

            if (this.camera.Vright.equals(Vector.ZERO))
                throw new IllegalArgumentException("Vectors Vup and Vto can't be parallel");

            this.camera.Vup = this.camera.Vright.crossProduct(this.camera.Vto).normalize();
            return this;
        }

        /**
         * Sets the camera direction based only on a target point.
         * Uses a default upward vector of (0,1,0).
         *
         * @param target the point to aim the camera at
         * @return this builder instance
         */
        public Builder setDirection(Point target) {
            return setDirection(target, new Vector(0, 1, 0));
        }

        /**
         * Sets the view plane size.
         *
         * @param w the width of the view plane
         * @param h the height of the view plane
         * @return this builder instance
         * @throws IllegalArgumentException if width or height is not positive
         */
        public Builder setVpSize(double w, double h) {
            if (w <= 0 || h <= 0)
                throw new IllegalArgumentException("Size of the view plane must be greater than zero");

            this.camera.VPhight = h;
            this.camera.VPwidth = w;
            return this;
        }

        /**
         * Sets the distance from the camera to the view plane.
         *
         * @param d the distance
         * @return this builder instance
         * @throws IllegalArgumentException if distance is not positive
         */
        public Builder setVpDistance(double d) {
            if (d <= 0)
                throw new IllegalArgumentException("Distance from the view plane must be greater than zero");

            this.camera.distance = d;
            return this;
        }

        /**
         * Sets the resolution of the view plane.
         *
         * @param nX number of horizontal pixels
         * @param nY number of vertical pixels
         * @return this builder instance
         * @throws IllegalArgumentException if dimensions are not positive
         */
        public Builder setResolution(int nX, int nY) {
            if (nX <= 0 || nY <= 0)
                throw new IllegalArgumentException("Resolution size must be greater than zero");

            this.camera.nY = nY;
            this.camera.nX = nX;
            return this;
        }

        public Builder setRayTracer(Scene s, RayTracerType type) {
            if (type == RayTracerType.SIMPLE && s != null) {
                this.camera.rayTracer = new SimpleRayTracer(s);
            } else {
                this.camera.rayTracer = new SimpleRayTracer(null);
            }

            return this;
        }


        public Builder setImageWriter() {
            this.camera.imageWriter = new ImageWriter(this.camera.nX, this.camera.nY);
            return this;
        }


        /**
         * Finalizes the camera construction and returns the built {@code Camera} object.
         *
         * @return a new {@code Camera} instance
         * @throws MissingResourceException if any required parameters are missing
         */
        public Camera build() {
            String ERROR_MESSAGE = "Missing rendering data";
            String CLASS_NAME = "Camera";

            if (camera.location == null)
                throw new MissingResourceException(ERROR_MESSAGE, CLASS_NAME, "location");

            if (camera.Vto == null)
                throw new MissingResourceException(ERROR_MESSAGE, CLASS_NAME, "Vto");

            if (camera.Vup == null)
                throw new MissingResourceException(ERROR_MESSAGE, CLASS_NAME, "Vup");

            if (camera.Vright == null) {
                Vector vright = camera.Vto.crossProduct(camera.Vup);
                if (vright.lengthSquared() == 0)
                    throw new IllegalArgumentException("Vto and Vup vectors must not be parallel");
                camera.Vright = vright.normalize();
            }

            if (camera.distance <= 0)
                throw new MissingResourceException(ERROR_MESSAGE, CLASS_NAME, "view plane distance");

            if (camera.VPwidth <= 0)
                throw new MissingResourceException(ERROR_MESSAGE, CLASS_NAME, "view plane width");

            if (camera.VPhight <= 0)
                throw new MissingResourceException(ERROR_MESSAGE, CLASS_NAME, "view plane height");

            if (camera.rayTracer == null)
                //     throw new MissingResourceException(ERROR_MESSAGE, CLASS_NAME, "ray tracer");
                this.camera.rayTracer = new SimpleRayTracer(null);


            try {
                return (Camera) camera.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException("Cloning failed unexpectedly", e);
            }
        }

    }

}