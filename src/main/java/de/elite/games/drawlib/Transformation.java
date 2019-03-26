package de.elite.games.drawlib;

public abstract class Transformation<T> implements Transformer, Transformed<T> {

    private double scale = 1;
    private double panx;
    private double pany;
    private double rot;

    @Override
    public void scale(double scale) {
        this.scale = scale;
    }

    @Override
    public void pan(double dx, double dy) {
        this.panx = dx;
        this.pany = dy;
    }

    @Override
    public void rotate(double rot, double x, double y) {

    }

    public abstract T getTransformed();


    double getScale() {
        return scale;
    }

    double getPanX() {
        return panx;
    }

    double getPanY() {
        return pany;
    }
}
