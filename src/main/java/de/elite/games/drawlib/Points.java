package de.elite.games.drawlib;

import java.util.HashMap;

public class Points {

    private java.util.Map<Integer, Point> internalPointsRepresentation = new HashMap<>();

    public Point get(Point point) {
        Point p = internalPointsRepresentation.get(point.hashCode());
        if (p == null) {
            internalPointsRepresentation.put(point.hashCode(), point);
            return point;
        }
        return p;
    }

    public int size() {
        return internalPointsRepresentation.size();
    }
}
