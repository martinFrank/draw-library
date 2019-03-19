package de.elite.games.drawlib;

public interface Draw {

    /**
     * Draws this object onto the graphics object at the given position. the
     * graphics can be anything, a Canvas or a Graphics or whatever. The
     * developer is responsible to cast the object in a proper class.
     *
     * @param graphics - an graphic object on which can be drawn
     */
    void draw(Object graphics);

}
