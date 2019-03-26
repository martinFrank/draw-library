package de.elite.games.drawlib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Point implements Transformer, Transformed<Point>, Comparable<Point> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Point.class);
    private final Transformation<Point> transformation;
    private double x;
    private double y;

    public Point() {
        this(0, 0);
    }

    public Point(Point point) {
        this(point.getX(), point.getY());
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        transformation = createTransformation();
    }

    private Transformation<Point> createTransformation() {

        return new Transformation<Point>() {

            private boolean isDirty = true;
            private Point transformed;

            @Override
            public Point getTransformed() {
                if (isDirty) {
                    double xTransformed = transformation.getPanX() + (x * transformation.getScale());
                    double yTransformed = transformation.getPanY() + (y * transformation.getScale());
//                    LOGGER.debug("transformed x/y:{}/{} -> {}/{}", x, y, xTransformed, yTransformed);
                    transformed = new Point(xTransformed, yTransformed);
                }
                isDirty = false;
                return transformed;
            }

            @Override
            public void scale(double scale) {
                super.scale(scale);
                isDirty = true;
            }

            @Override
            public void pan(double dx, double dy) {
                super.pan(dx, dy);
                isDirty = true;
            }

            @Override
            public void rotate(double rot, double x, double y) {
                super.rotate(rot, x, y);
                isDirty = true;
            }
        };
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public Point getTransformed() {
        return transformation.getTransformed();
    }

    @Override
    public void scale(double scale) {
        transformation.scale(scale);
    }

    @Override
    public void pan(double dx, double dy) {
        transformation.pan(dx, dy);
    }

    @Override
    public void rotate(double rot, double x, double y) {
        transformation.rotate(rot, x, y);
    }

    @Override
    public String toString() {
        return "Point [" + String.format("%3.1f", x) + "/" + String.format("%3.1f", y) + "]";
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Point that = (Point) o;
//
//        if (Double.compare(that.x, x) != 0) return false;
//        return Double.compare(that.y, y) == 0;
//    }
//
//    @Override
//    public int hashCode() {
//        int result;
//        long temp;
//        temp = Double.doubleToLongBits(x);
//        result = (int) (temp ^ (temp >>> 32));
//        temp = Double.doubleToLongBits(y);
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
//        return result;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (Double.compare(point.x, x) != 0) return false;
        return Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
//        return Objects.hash(x,y);
//        return super.hashCode();
        return ("" + x + ":" + y).hashCode();
    }

    @Override
    public int compareTo(Point that) {
        if (x == that.x) {
            return Double.compare(y, that.y);
        }
        return Double.compare(x, that.x);
    }
}
