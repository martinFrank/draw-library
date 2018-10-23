package de.frank.martin.drawlib;

/**
 * this interface provides a simple drawing method. Drawing is platform
 * dependent and therefore its hard to write platform independent software. This
 * interface describes a simple approach to handle different implementations.
 *
 * @author martinFrank
 */
public interface PanScale {

    /**
     * Draws this object onto the graphics object at the given position. the
     * graphics can be anything, a Canvas or a Graphics or whatever. The
     * developer is responsible to cast the object in a proper class.
     *
     * @param graphics - an graphic object on which can be drawn
     */
    void draw(Object graphics);

    /**
     * scales this object according to the factor
     *
     * @param scale factor
     */
    void scale(double scale);

    /**
     * moves this object (changes the draw position) to the destination
     *
     * @param dx destination
     * @param dy destination
     */
    void pan(double dx, double dy);

    /**
     * @return the applied scale factor
     */
    double getScale();

    /**
     * @return the x-component of the pan
     */
    double getPanX();

    /**
     * @return the y-component of the pan
     */
    double getPanY();
}
