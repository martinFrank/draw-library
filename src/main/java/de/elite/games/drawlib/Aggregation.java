package de.elite.games.drawlib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Aggregation implements Transformer, Transformed<Aggregation>, Size {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aggregation.class);
    private final Transformation<Aggregation> transformation;
    private List<Shape> shapes;
    private double width;
    private double height;

    public Aggregation(List<Shape> shapes) {
        transformation = createTransformation();
        this.shapes = shapes;
        calculateSize();
    }

    public Aggregation() {
        this(new ArrayList<>());
    }

    private Transformation<Aggregation> createTransformation() {
        return new Transformation<Aggregation>() {

            private boolean isDirty = true;
            private Aggregation transformedAggregation;

            @Override
            public Aggregation getTransformed() {
                LOGGER.debug("getTransformed, isDirty={}", isDirty);
                if (isDirty) {
                    List<Shape> transformed = shapes.stream().
                            map(Shape::getTransformed).collect(Collectors.toList());
                    transformedAggregation = new Aggregation(transformed);
                    transformedAggregation.calculateSize();
                }
                isDirty = false;
                return transformedAggregation;
            }

            @Override
            public void scale(double scale) {
                shapes.forEach(s -> s.scale(scale));
                super.scale(scale);
                isDirty = true;
            }

            @Override
            public void pan(double dx, double dy) {
                shapes.forEach(s -> s.pan(dx, dy));
                super.pan(dx, dy);
                isDirty = true;
            }

            @Override
            public void rotate(double rot, double x, double y) {
                shapes.forEach(s -> s.rotate(rot, x, y));
                super.rotate(rot, x, y);
                isDirty = true;
            }
        };
    }

    @Override
    public Aggregation getTransformed() {
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
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    public List<Shape> getShapes() {
        return Collections.unmodifiableList(shapes);
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
        calculateSize();
    }

    private void calculateSize() {
        double left = 10000;
        double right = -10000;
        double bottom = 10000;
        double top = -10000;
        for (Shape shape : getShapes()) {
            left = Math.min(shape.getCenter().getX(), left);
            bottom = Math.min(shape.getCenter().getY(), bottom);
            right = Math.max(shape.getCenter().getX(), right);
            top = Math.max(shape.getCenter().getY(), top);
        }
        double w = getShapes().isEmpty() ? 0 : getShapes().get(0).getWidth();
        double h = getShapes().isEmpty() ? 0 : getShapes().get(0).getHeight();
        width = right - left + w;
        height = top - bottom + h;
    }
}
