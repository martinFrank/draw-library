package de.elite.games.drawlib;

import java.util.HashMap;

public class Points {

    private java.util.Map<Integer, Point> points = new HashMap<>();

    public Point get(Point point) {
        Point p = points.get(point.hashCode());
        if (p == null) {
            points.put(point.hashCode(), point);
            return point;
        }
        return p;
    }

    public int size() {
        return points.size();
    }
}
