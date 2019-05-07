package de.elite.games.drawlib;

import com.github.martinfrank.drawlib.Point;
import com.github.martinfrank.drawlib.Points;
import org.junit.Assert;
import org.junit.Test;

public class PointsTest {

    @Test
    public void equalTest() {
        Point a = new Point(1, 1);
        Point b = new Point(1, 1);
        Point c = new Point(0, 0);
        Assert.assertEquals(a, b);
        Assert.assertNotEquals(a, c);

        Points points = new Points();
        Point testeeA = points.get(a);

        Assert.assertEquals(testeeA, a);
        Assert.assertEquals(testeeA, b);

        Point testeeB = points.get(b);
        Assert.assertEquals(testeeB, a);
        Assert.assertEquals(testeeB, b);

        Point testeeC = points.get(c);
        Assert.assertEquals(testeeC, c);
        Assert.assertNotEquals(testeeC, b);
    }

    @Test
    public void rangeTest() {
        Points points = new Points();
        for (int dy = -100; dy < 100; dy++) {
            for (int dx = -100; dx < 100; dx++) {
                Point point = new Point(dx, dy);
                Point testee = points.get(point);
                Assert.assertEquals(point, testee);
            }
        }

        Assert.assertEquals(200 * 200, points.size());
    }

}
