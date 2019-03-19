package de.elite.games.drawlib;

public interface Pan {

    /**
     * moves this object (changes the draw position) to the destination
     *
     * @param panx destination
     * @param pany destination
     */
    void pan(double panx, double pany);

    /**
     * @return the x-component of the pan
     */
    double getPanX();

    /**
     * @return the y-component of the pan
     */
    double getPanY();
}
