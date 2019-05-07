package de.elite.games.drawlib;

import com.github.martinfrank.drawlib.Line;
import com.github.martinfrank.drawlib.Point;
import com.github.martinfrank.drawlib.Shape;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShapeTest {



    @Test
    public void scaleTest() {
        Shape square = createSquare();
        square.scale(2);

        Shape transformed = square.getTransformed();
        Assert.assertEquals(2, transformed.getCenter().getX(), 0);
        Assert.assertEquals(2, transformed.getCenter().getY(), 0);

        Point point = new Point(4, 4);
        Assert.assertTrue(transformed.getPoints().contains(point));

        Assert.assertEquals(4, transformed.getWidth(), 0);
        Assert.assertEquals(4, transformed.getHeight(), 0);
    }

    @Test
    public void panTest() {
        Shape square = createSquare();
        square.pan(3, 1);

        Shape transformed = square.getTransformed();
        Assert.assertEquals(4, transformed.getCenter().getX(), 0);
        Assert.assertEquals(2, transformed.getCenter().getY(), 0);

        Point point = new Point(5, 3);
        Assert.assertTrue(transformed.getPoints().contains(point));

        Assert.assertEquals(2, transformed.getWidth(), 0);
        Assert.assertEquals(2, transformed.getHeight(), 0);
    }

    @Test
    public void panScaleTest() {
        Shape square = createSquare();
        square.scale(2);
        square.pan(3, 1);

        Shape transformed = square.getTransformed();
        Assert.assertEquals(5, transformed.getCenter().getX(), 0);
        Assert.assertEquals(3, transformed.getCenter().getY(), 0);

        Point point = new Point(7, 5);
        Assert.assertTrue(transformed.getPoints().contains(point));

        Assert.assertEquals(4, transformed.getWidth(), 0);
        Assert.assertEquals(4, transformed.getHeight(), 0);
    }

    @Test
    public void constructorTest() {
        try {
            new Shape();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    //used in mapLib
    public static Shape createSquare() {
        Point a = new Point(0, 0);
        Point b = new Point(2, 0);
        Point c = new Point(2, 2);
        Point d = new Point(0, 2);
        Point m = new Point(1, 1);
        List<Point> points = Arrays.asList(a, b, c, d);
        List<Line> lines = createLines(points);
        Shape square = new Shape(m, points, lines);
        Assert.assertEquals(2, square.getWidth(), 0);
        Assert.assertEquals(2, square.getHeight(), 0);
        return square;
    }

    private static Shape createSquareByPoints() {
        Point a = new Point(0, 0);
        Point b = new Point(2, 0);
        Point c = new Point(2, 2);
        Point d = new Point(0, 2);
        Point m = new Point(1, 1);
        List<Point> points = Arrays.asList(a, b, c, d);
        List<Line> lines = createLines(points);
        Shape square = new Shape(m, points, lines);
        Assert.assertEquals(2, square.getWidth(), 0);
        Assert.assertEquals(2, square.getHeight(), 0);
        return square;
    }

    private static List<Line> createLines(List<Point> points) {
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            Point a = points.get(i);
            Point b = points.get(i + 1 == points.size() ? 0 : i + 1);
            lines.add(new Line(a, b));
        }
        return lines;
    }

    @Test
    public void rotateTest() {
        Shape s1 = createSquare();
        s1.rotate(1, 2, 3);
        Shape s2 = createSquare();
        Assert.assertEquals(s1, s2);
    }

    @Test
    public void equalTest() {
        Shape s1 = createSquare();
        Shape s2 = createSquare();
        Assert.assertEquals(s1, s2);
        Assert.assertEquals(s1.hashCode(), s2.hashCode());
    }
}

