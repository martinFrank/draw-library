package de.elite.games.drawlib;

import com.github.martinfrank.drawlib.Line;
import com.github.martinfrank.drawlib.Lines;
import com.github.martinfrank.drawlib.Point;
import org.junit.Assert;
import org.junit.Test;

public class LinesTest {

    @Test
    public void equalTest() {
        Point a = new Point(1, 1);
        Point b = new Point(2, 2);
        Line ab = new Line(a, b);
        Line ba = new Line(b, a);

        //test subject
        Lines lines = new Lines();

        Line testeeAb = lines.get(ab);
        Assert.assertEquals(testeeAb, ab);

        Line testeeBa = lines.get(ba);
        Assert.assertEquals(testeeBa, ba);

        Assert.assertEquals(ab, ba);
        Assert.assertEquals(testeeAb, testeeBa);
    }

    @Test
    public void hashTest() {
        Point a = new Point(0, 0);
        Point b = new Point(2, 2);
        Line ab = new Line(a, b);
        Line ba = new Line(b, a);
        Assert.assertEquals(ab.hashCode(), ba.hashCode());
    }
}
