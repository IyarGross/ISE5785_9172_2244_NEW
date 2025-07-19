package renderer;

import primitives.*;
import scene.Scene;

import java.util.MissingResourceException;

import static java.awt.Color.black;

/**
 * the camera class is a camera model used for rendering 3d scenes
 * it stores the cameras position direction vectors and view plane size and it can create rays that pass through individual pixels
 */
public class Camera implements Cloneable {

    private Point p0; // camera position
    private Vector vUp, vTo, vRight; // orientation vectors
    private double width = 0, height = 0, distance = 0; // view plane parameters
    private Point Pc; // center point of view plane
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;
    private int nX = 1;
    private int nY = 1;

    private Camera() {
    } // private constructor for builder only

    public static Builder getBuilder() {
        return new Builder();
    } // get builder instance

    /**
     * makes a ray through the pixel (j,i)
     *
     * @param nX number of horizontal pixels
     * @param nY number of vertical pixels
     * @param j  horizontal pixel index
     * @param i  vertical pixel index
     * @return the constructed ray from the camera through the pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        // calc vertical pixel offset (centered)
        double Yi = Util.alignZero(-(i - (nY - 1) / 2d) * height / nY);
        // calc horizontal pixel offset (centered)
        double Xj = Util.alignZero((j - (nX - 1) / 2d) * width / nX);

        Point Pij = Pc; // start at view plane center
        if (!Util.isZero(Xj)) // move right if needed
            Pij = Pij.add(vRight.scale(Xj));
        if (!Util.isZero(Yi)) // move up if needed
            Pij = Pij.add(vUp.scale(Yi));

        return new Ray(p0, Pij.subtract(p0)); // ray from camera pos to pixel point
    }

    /** the builder class for Camera */
    public static class Builder {
        private final Camera camera = new Camera();

        /**
         * sets the camera location in the 3d space
         *
         * @param p position of the camera
         * @return the builder object
         */
        public Builder setLocation(Point p) {
            camera.p0 = p; // set position
            return this;
        }

        /**
         * sets the direction of the camera and the upward direction
         *
         * @param vTo the direction the camera is pointing
         * @param vUp the upward direction on an orthogonal angle from where the camera is pointing
         * @return the builder object
         * @throws IllegalArgumentException if vTo and vUp are not orthogonal
         */
        public Builder setDirection(Vector vTo, Vector vUp) {
            if (!Util.isZero(vTo.dotProduct(vUp))) // check orthogonal
                throw new IllegalArgumentException("vTo and tUp need to be orthogonal");
            camera.vTo = vTo.normalize(); // normalize forward vector
            camera.vUp = vUp.normalize(); // normalize up vector
            return this;
        }

        /**
         * sets the direction of the camera using a vector (upward vector) and a (target) point
         *
         * @param target the point the camera should look at
         * @param vUp    the upward direction for the camera
         * @return IllegalArgumentException if the target is the same as the camera position
         */
        public Builder setDirection(Point target, Vector vUp) {
            try {
                camera.vTo = target.subtract(camera.p0).normalize(); // vector from pos to target
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Target point is equal to origin point");
            }

            camera.vRight = camera.vTo.crossProduct(vUp).normalize(); // right vector cross prod
            camera.vUp = camera.vRight.crossProduct(camera.vTo).normalize(); // re-orthogonalize up
            return this;
        }

        /**
         * makes the camera viewing direction using a target point and assumes a default up vector of (0, 1, 0).
         *
         * @param target the point the camera should look at
         * @return the builder object
         */
        public Builder setDirection(Point target) {
            return setDirection(target, new Vector(0, 1, 0)); // default up vec (0,1,0)
        }

        /**
         * sets the view planes size
         *
         * @param width  the width of the view-plane
         * @param height the height of the view-plane
         * @return the builder object
         * @throws IllegalArgumentException if width or height are not positive
         */
        public Builder setVpSize(double width, double height) {
            if (Util.alignZero(width) <= 0 || Util.alignZero(height) <= 0) // check positive size
                throw new IllegalArgumentException("Width and height must be positive");
            camera.width = width; // assign width
            camera.height = height; // assign height
            return this;
        }

        /**
         * sets the view planes distance from camera
         * @param distance the distance from the camera
         * @return the builder object
         */
        public Builder setVpDistance(double distance) {
            if (Util.alignZero(distance) <= 0) // must be positive
                throw new IllegalArgumentException("Distance must be positive");
            camera.distance = distance; // set distance
            return this;
        }

        /**
         * sets the resolution of the output image
         * @param nX number of pixels horizontally
         * @param nY number of pixels vertically
         * @return the builder object for chaining
         */
        public Builder setResolution(int nX, int nY) {
            if (nX <= 0 || nY <= 0)
                throw new IllegalArgumentException("Resolution values must be positive");
            camera.nX = nX;
            camera.nY = nY;
            return this;
        }

        /** builds and validates the Camera object (throws if missing fields), (returns the fully initialized Camera)
         * @return the constructed Camera instance
         * @throws MissingResourceException if required parameters are missing
         */
        public Camera build() {
            final String missing = "Missing a rendering variable";
            final String cls = "Camera";

            // check all mandatory fields set
            if (camera.p0 == null)
                throw new MissingResourceException(missing, cls, "p0 is missing");
            if (camera.vUp == null)
                throw new MissingResourceException(missing, cls, "vUp is missing");
            if (camera.vTo == null)
                throw new MissingResourceException(missing, cls, "vTo is missing");
            if (camera.distance == 0)
                throw new MissingResourceException(missing, cls, "distance is missing");
            if (camera.height == 0)
                throw new MissingResourceException(missing, cls, "height is missing");
            if (camera.width == 0)
                throw new MissingResourceException(missing, cls, "width is missing");

            // check resolution
            if (camera.nX <= 0 || camera.nY <= 0)
                throw new IllegalArgumentException("Image resolution must be positive");

            // initialize ImageWriter
            camera.imageWriter = new ImageWriter(camera.nX, camera.nY);
            // set default ray tracer if not set
            if (camera.rayTracer == null) {
                camera.rayTracer = new SimpleRayTracer(null);
            }

            // compute right vector if missing
            if (camera.vRight == null)
                camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();

            // calc center point of view plane
            camera.Pc = camera.p0.add(camera.vTo.scale(camera.distance));

            // clone and return the built camera
            try {
                return (Camera) camera.clone();
            } catch (CloneNotSupportedException e) {
                // fallback manual copy
                Camera c = new Camera();
                c.p0 = camera.p0;
                c.vUp = camera.vUp;
                c.vTo = camera.vTo;
                c.vRight = camera.vRight;
                c.width = camera.width;
                c.height = camera.height;
                c.distance = camera.distance;
                c.Pc = camera.Pc;
                c.nX = camera.nX;
                c.nY = camera.nY;
                c.imageWriter = camera.imageWriter;
                c.rayTracer = camera.rayTracer;
                return c;
            }
        }

        /**
         * sets the ray tracer for the camera based on scene and type
         * @param scene the scene to render
         * @param type the type of ray tracer to use
         * @return the builder object for chaining
         */
        public Builder setRayTracer(Scene scene, RayTracerType type) {
            if (type == RayTracerType.SIMPLE) {
                camera.rayTracer = new SimpleRayTracer(scene);
            }
            else {
                camera.rayTracer = null;
            }
            return this;
        }
    }

    /**
     * renders the image by casting rays through every pixel
     * @return the camera object for chaining
     */
    public Camera renderImage() {
        for (int i = 0; i< nY; i++) {
            for (int j = 0; j < nX; j++) {
                castRay(j, i);
            }
        }
        return this;
    }

    /**
     * draws a grid on the image with given interval and color
     * @param interval the spacing between grid lines in pixels
     * @param color the color to draw the grid lines
     * @return the camera object for chaining
     */
    public Camera printGrid(int interval, Color color) {
        for (int j = 0; j < nX; j++) {
            for (int i = 0; i < nY; i++) {
                if (j % interval == 0 || i % interval == 0) {
                    imageWriter.writePixel(j, i, color);
                }
            }
        }
        return this;
    }

    /**
     * writes the rendered image to a file
     * @param imageName the name of the output file
     * @return the camera object for chaining
     */
    public Camera writeToImage (String imageName) {
        imageWriter.writeToImage(imageName);
        return this;
    }

    /**
     * casts a ray through pixel (j,i) and writes the color to the image
     * @param j the horizontal pixel index
     * @param i the vertical pixel index
     */
    private void castRay(int j,int i) {
        imageWriter.writePixel(j,i,rayTracer.traceRay(constructRay(nX,nY,j,i)));
    }



}
