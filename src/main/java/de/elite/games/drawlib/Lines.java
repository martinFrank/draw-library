package de.elite.games.drawlib;

import java.util.HashMap;

public class Lines {

    private java.util.Map<Integer, Line> internalLinesRepresentation = new HashMap<>();

    public Line get(Line line) {
        Line p = internalLinesRepresentation.get(line.hashCode());
        if (p == null) {
            internalLinesRepresentation.put(line.hashCode(), line);
            return line;
        }
        return p;
    }
}
