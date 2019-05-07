package com.github.martinfrank.drawlib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Aggregation extends PanScaleRot<Aggregation> implements Size {


    private List<Shape> shapes;
    private double width;
    private double height;

    public Aggregation(List<Shape> shapes) {
        super();
        this.shapes = shapes;
        calculateSize();
    }

    public Aggregation() {
        this(new ArrayList<Shape>());
    }

    @Override
    Transformation<Aggregation> createTransformation() {

        return new Transformation<Aggregation>() {

            private boolean isDirty = true;
            private Aggregation transformedAggregation;

            @Override
            public Aggregation getTransformed() {
                if (isDirty) {
                    List<Shape> transformed = new ArrayList<>();
                    for (Shape shape : shapes) {
                        transformed.add(shape.getTransformed());
                    }
                    transformedAggregation = new Aggregation(transformed);
                    transformedAggregation.calculateSize();
                }
                isDirty = false;
                return transformedAggregation;
            }

            @Override
            public void scale(double scale) {
                for (Shape shape : shapes) {
                    shape.scale(scale);
                }
                super.scale(scale);
                isDirty = true;
            }

            @Override
            public void pan(double dx, double dy) {
                for (Shape shape : shapes) {
                    shape.pan(dx, dy);
                }
                super.pan(dx, dy);
                isDirty = true;
            }

            @Override
            public void rotate(double rot, double x, double y) {
                for (Shape shape : shapes) {
                    shape.rotate(rot, x, y);
                }
                super.rotate(rot, x, y);
                isDirty = true;
            }
        };
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
