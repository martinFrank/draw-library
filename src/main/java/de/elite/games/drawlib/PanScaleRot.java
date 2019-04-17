package de.elite.games.drawlib;

public abstract class PanScaleRot<D> implements Transformer, Transformed<D> {

    private final Transformation<D> transformation;

    public PanScaleRot() {
        this.transformation = createTransformation();
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

    abstract Transformation<D> createTransformation();

    public D getTransformed() {
        return transformation.getTransformed();
    }


}
