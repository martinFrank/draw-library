package de.elite.games.drawlib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Shape implements Transformer, Transformed<Shape>, Size {

    private static final Logger LOGGER = LoggerFactory.getLogger(Shape.class);

    private final Transformation<Shape> transformation;
    private Point center;
    private List<Point> points;
    private List<Line> lines;
    private double width;
    private double height;

    public Shape(Point c, List<Point> points, List<Line> lines) {
        transformation = createTransformation();
        center = new Point(c);
        this.points = points;
        this.lines = lines;
        calculateSize();
//        LOGGER.debug("created shape... {} {} {}",center, points, lines);
    }


    public Shape() {
        this(new Point(0, 0), new ArrayList<>(), new ArrayList<>());
    }

    private static Point createIntermediate(Collection<? extends Point> points) {
        double xs = points.stream().mapToDouble(Point::getX).sum();
        double ys = points.stream().mapToDouble(Point::getY).sum();
        int s = points.size();
        return new Point(xs / s, ys / s);
    }

    private Transformation<Shape> createTransformation() {

        return new Transformation<Shape>() {

            private boolean isDirty = true;
            private Shape transformedShape;

            @Override
            public Shape getTransformed() {
                if (isDirty) {
                    List<Point> tPoints = points.stream().map(Point::getTransformed).collect(Collectors.toList());
                    List<Line> tLines = lines.stream().map(Line::getTransformed).collect(Collectors.toList());
                    Point transformedCenter = center.getTransformed();
                    transformedShape = new Shape(transformedCenter, tPoints, tLines);
                    transformedShape.calculateSize();
                }
                isDirty = false;
                return transformedShape;
            }

            @Override
            public void scale(double scale) {
                points.forEach(p -> p.scale(scale));
                lines.forEach(l -> l.scale(scale));
                center.scale(scale);
                super.scale(scale);
                isDirty = true;
            }

            @Override
            public void pan(double dx, double dy) {
                points.forEach(p -> p.pan(dx, dy));
                lines.forEach(l -> l.pan(dx, dy));
                center.pan(dx, dy);
                super.pan(dx, dy);
                isDirty = true;
            }

            @Override
            public void rotate(double rot, double x, double y) {
                points.forEach(p -> p.rotate(rot, x, y));
                lines.forEach(l -> l.rotate(rot, x, y));
                center.rotate(rot, x, y);
                super.rotate(rot, x, y);
                isDirty = true;
            }
        };
    }

    public void addPoint(Point point) {
        if (!points.contains(point)) {
            points.add(point);
            sortPoints();
            calculateSize();
        }
    }

    public Point getCenter() {
        return center;
    }

    public List<Point> getPoints() {
        return Collections.unmodifiableList(points);
    }

    public void setPoints(List<Point> points) {
        this.points = points;
        sortPoints();
        calculateSize();
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public Shape getTransformed() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shape that = (Shape) o;

        if (!center.equals(that.center)) return false;
        return points.equals(that.points);
    }

    @Override
    public int hashCode() {
        int result = center.hashCode();
        result = 31 * result + points.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Shape center@" + center.toString() + " w/h=" + String.format("%3.1f", width) + "/" + String.format("%3.1f", height);
    }

    private void calculateSize() {
        double left = 10000;
        double right = -10000;
        double bottom = 10000;
        double top = -10000;
        for (Point point : getPoints()) {
            left = Math.min(point.getX(), left);
            bottom = Math.min(point.getY(), bottom);
            right = Math.max(point.getX(), right);
            top = Math.max(point.getY(), top);
        }
//        LOGGER.debug("calculateSize: l/r/b/t {}/{}/{}/{}", left, right, bottom, top);
        width = right - left;
        height = top - bottom;
    }

    private void sortPoints() {
        Collections.sort(points);
    }

}