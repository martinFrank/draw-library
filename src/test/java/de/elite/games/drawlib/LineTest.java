package de.elite.games.drawlib;

import com.github.martinfrank.drawlib.Line;
import com.github.martinfrank.drawlib.Point;
import org.junit.Assert;
import org.junit.Test;

public class LineTest {

    @Test
    public void equalTest() {
        Point a = new Point(1, 1);
        Point b = new Point(2, 2);
        Line ab = new Line(a, b);
        Line ba = new Line(b, a);
        Assert.assertEquals(ab, ba);
    }

}
