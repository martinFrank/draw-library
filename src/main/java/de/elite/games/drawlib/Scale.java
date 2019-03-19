package de.elite.games.drawlib;

public interface Scale {

    double getScaledX();

    double getScaledY();

    /**
     * scales this object according to the factor
     *
     * @param scale factor
     */
    void scale(double scale);
}
