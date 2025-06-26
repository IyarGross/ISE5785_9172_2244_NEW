package renderer;

import primitives.*;
import scene.Scene;

import java.util.MissingResourceException;


/**
 * Camera class represents a camera in the 3D space
 */
public class Camera implements Cloneable {
    private Point p0;
    private Vector vUp;
    private Vector vTo;
    private Vector vRight;

    private double width = 0d;
    private double height = 0d;
    private double distance = 0d;

    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;
    private int nX = 1;
    private int nY = 1;

    /**
     * Camera getter
     * @return the location of the camera
     */
    public Point getP0() { return p0; }

    /**
     * Camera getter
     * @return the up direction of the camera
     */
    public Vector getVUp() { return vUp; }

    /**
     * Camera getter
     * @return the direction of the camera
     */
    public Vector getVTo() { return vTo; }

    /**
     * Camera getter
     * @return the right direction of the camera
     */
    public Vector getVRight() { return vRight; }

    /**
     * Camera getter
     * @return the width of the view plane
     */
    public double getWidth() { return width; }

    /**
     * Camera getter
     * @return the height of the view plane
     */
    public double getHeight() { return height; }

    /**
     * Camera getter
     * @return the distance between the camera and the view plane
     */
    public double getDistance() { return distance; }


    private void castRay(int nX, int nY, int j, int i) {
        Ray ray=constructRay(nX, nY, j, i);
        Color color = rayTracer.traceRay(ray);
        imageWriter.writePixel(j,i,color);
    }

    /**
     * Render the image by casting rays through all pixels.
     * @return this camera
     */
    public Camera renderImage() {
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                Ray ray = constructRay(nX, nY, j, i);
                Color color = rayTracer.traceRay(ray);
                imageWriter.writePixel(j, i, color);
            }
        }
        return this;
    }

    /**
     * Print a grid of the given color and interval.
     * @param interval interval between grid lines
     * @param color    color of the grid lines
     * @return this camera
     */
    public Camera printGrid(int interval, Color color) {
        for (int j = 0; j < nX; j++) {
            for (int i = 0; i < nY; i++) {
                if (j % interval == 0 || i % interval == 0)
                    imageWriter.writePixel(j, i, color);
            }
        }
        return this;
    }

    /**
     * Write the image to file.
     * @param filename name of the file (without extension)
     * @return this camera
     */
    public Camera writeToImage(String filename) {
        imageWriter.writeToImage();
        return this;
    }

    /**
     * Camera builder
     */
    public static class Builder {
        private final Camera camera = new Camera();

        public Builder setLocation(Point p0) {
            camera.p0 = p0;
            return this;
        }

        public Builder setDirection(Vector vTo, Vector vUp) {
            if (!Util.isZero(vTo.dotProduct(vUp)))
                throw new IllegalArgumentException("vTo and vUp must be orthogonal");
            camera.vTo = vTo.normalize();
            camera.vUp = vUp.normalize();
            return this;
        }

        public Builder setVpSize(double width, double height) {
            if (width <= 0 || height <= 0)
                throw new IllegalArgumentException("width and height must be positive");
            camera.width = width;
            camera.height = height;
            return this;
        }

        public Builder setVpDistance(double distance) {
            if (distance <= 0)
                throw new IllegalArgumentException("distance from camera to view must be positive");
            camera.distance = distance;
            return this;
        }

        public Builder setResolution(int nX, int nY) {
            if (nX <= 0 || nY <= 0)
                throw new IllegalArgumentException("Resolution must be positive");
            camera.nX = nX;
            camera.nY = nY;
            camera.imageWriter = new ImageWriter("image", nX, nY);
            return this;
        }

        public Builder setRayTracer(Scene scene, RayTracerType type) {
            switch (type) {
                case SIMPLE -> camera.rayTracer = new SimpleRayTracer(scene);
                default -> camera.rayTracer = null;
            }
            return this;
        }

        public Camera build() {
            final String className = "Camera";
            final String description = "values not set: ";

            if (camera.p0 == null) throw new MissingResourceException(description, className, "p0");
            if (camera.vUp == null) throw new MissingResourceException(description, className, "vUp");
            if (camera.vTo == null) throw new MissingResourceException(description, className, "vTo");
            if (camera.width == 0d) throw new MissingResourceException(description, className, "width");
            if (camera.height == 0d) throw new MissingResourceException(description, className, "height");
            if (camera.distance == 0d) throw new MissingResourceException(description, className, "distance");

            camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();

            if (!Util.isZero(camera.vTo.dotProduct(camera.vRight)) ||
                    !Util.isZero(camera.vTo.dotProduct(camera.vUp)) ||
                    !Util.isZero(camera.vRight.dotProduct(camera.vUp)))
                throw new IllegalArgumentException("vTo, vUp and vRight must be orthogonal");

            if (camera.vTo.length() != 1 || camera.vUp.length() != 1 || camera.vRight.length() != 1)
                throw new IllegalArgumentException("vTo, vUp and vRight must be normalized");

            if (camera.imageWriter == null)
                camera.imageWriter = new ImageWriter("default", camera.nX, camera.nY);

            if (camera.rayTracer == null)
                camera.rayTracer = new SimpleRayTracer(null);

            try {
                return (Camera) camera.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Camera() {}

    public static Builder getBuilder() {
        return new Builder();
    }

    public Ray constructRay(int nX, int nY, int j, int i) {
        Point pIJ = p0;
        double yI = -(i - (nY - 1) / 2d) * height / nY;
        double xJ = (j - (nX - 1) / 2d) * width / nX;

        if (!Util.isZero(xJ)) pIJ = pIJ.add(vRight.scale(xJ));
        if (!Util.isZero(yI)) pIJ = pIJ.add(vUp.scale(yI));

        pIJ = pIJ.add(vTo.scale(distance));

        return new Ray(p0, pIJ.subtract(p0).normalize());
    }

}
