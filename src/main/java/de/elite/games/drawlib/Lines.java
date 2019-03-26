package de.elite.games.drawlib;

import java.util.HashMap;

public class Lines {

    private java.util.Map<Integer, Line> lines = new HashMap<>();

    public Line get(Line line) {
        Line p = lines.get(line.hashCode());
        if (p == null) {
            lines.put(line.hashCode(), line);
            return line;
        }
        return p;
    }
}
