package de.elite.games.drawlib;

public interface Transformer {

    void scale(double scale);

    void pan(double dx, double dy);

    void rotate(double rot, double x, double y);


}
