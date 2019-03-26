package de.elite.games.drawlib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Line implements Transformer, Transformed<Line> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Point.class);
    private final Transformation<Line> transformation;
    private Point a;
    private Point b;
    private Point center;

    public Line() {
        this(0, 0, 0, 0);
    }

    public Line(Point a, Point b) {
        this(a, b, calculateCenter(a, b));
    }

    private Line(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        center = c;
        transformation = createTransformation();
    }

    public Line(double ax, double ay, double bx, double by) {
        this(new Point(ax, ay), new Point(bx, by));
    }

    private static Point calculateCenter(Point a, Point b) {
        double x = (a.getX() + b.getX()) / 2d;
        double y = (a.getY() + b.getY()) / 2d;
        return new Point(x, y);
    }

    private Transformation<Line> createTransformation() {

        return new Transformation<Line>() {

            private boolean isDirty = true;
            private Line lineTransformed;

            @Override
            public Line getTransformed() {
                if (isDirty) {
                    Point aTransformed = a.getTransformed();
                    Point bTransformed = b.getTransformed();
                    Point cTransformed = center.getTransformed();
                    lineTransformed = new Line(aTransformed, bTransformed, cTransformed);
                }
                isDirty = false;
                return lineTransformed;
            }

            @Override
            public void scale(double scale) {
                a.scale(scale);
                b.scale(scale);
                center.scale(scale);
                super.scale(scale);
                isDirty = true;
            }

            @Override
            public void pan(double dx, double dy) {
                a.pan(dx, dy);
                b.pan(dx, dy);
                center.pan(dx, dy);
                super.pan(dx, dy);
                isDirty = true;
            }

            @Override
            public void rotate(double rot, double x, double y) {
                a.rotate(rot, x, y);
                b.rotate(rot, x, y);
                center.rotate(rot, x, y);
                super.rotate(rot, x, y);
                isDirty = true;
            }
        };
    }

    public boolean isConnectedTo(Line other) {
        if (other == null || this.equals(other)) {
            return false;
        }
        if (a.equals(other.a)) {
            return true;
        }
        if (a.equals(other.b)) {
            return true;
        }
        if (b.equals(other.a)) {
            return true;
        }
        if (b.equals(other.b)) {
            return true;
        }
        return false;
    }

    public boolean isConnectedTo(Point other) {
        if (a.equals(other)) {
            return true;
        }
        if (b.equals(other)) {
            return true;
        }
        return false;
    }

    public void set(Point a, Point b) {
        this.a = a;
        this.b = b;
        center = calculateCenter(a, b);
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public Point getCenter() {
        return center;
    }

    public Line getTransformed() {
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
        return "Line  from " + a + " to " + b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;
        if (a.equals(line.a) && b.equals(line.b)) {
            return true;
        }
        if (b.equals(line.a) && a.equals(line.b)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return a.hashCode() * b.hashCode();
//        return Objects.hash(a,b);
    }
}
