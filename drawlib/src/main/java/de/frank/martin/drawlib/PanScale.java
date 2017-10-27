package de.frank.martin.drawlib;

/**
 * this interface provides a simple drawing method. Drawing is platform
 * dependent and therefore its hard to write platform independent software. This
 * interface describes a simple approach to handle different implementations.
 * 
 * @author martinFrank
 *
 */
public interface PanScale {

	/**
	 * Draws this object onto the graphics object at the given position. the
	 * graphics can be anything, a Canvas or a Graphics or whatever. The
	 * developer is responsible to cast the object in a proper class.
	 * 
	 * @param graphics - an graphic object on which can be drawn
	 * @param xOff position where on the graphics is drawn
	 * @param yOff position where on the graphics is drawn
	 */
	void draw(Object graphics, int xOff, int yOff);

	/**
	 * scales this object according to the factor
	 * 
	 * @param scale factor
	 */
	void scale(float scale);

	/**
	 * moves this object (changes the draw position) to the destination
	 * 
	 * @param dx destination
	 * @param dy destination
	 */
	void pan(int dx, int dy);
}