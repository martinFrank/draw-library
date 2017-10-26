package de.frank.martin.drawlib;

public interface PanScale {

	void draw (Object graphics, int xOff, int yOff);
	void scale (float scale);
	void pan (int dx, int dy);
}
