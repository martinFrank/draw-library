package de.elite.games.drawlib;

import org.junit.Assert;
import org.junit.Test;

public class PointTest {

    @Test
    public void scaleTest() {
        Point transformPoint = new Point(2, 2);
        transformPoint.scale(2);
        Point transformed = transformPoint.getTransformed();
        Assert.assertEquals(4, transformed.getX(), 0);
        Assert.assertEquals(4, transformed.getY(), 0);
    }

    @Test
    public void panTest() {
        Point transformPoint = new Point(2, 2);
        transformPoint.pan(3, 1);
        Point transformed = transformPoint.getTransformed();
        Assert.assertEquals(5, transformed.getX(), 0);
        Assert.assertEquals(3, transformed.getY(), 0);
    }

    @Test
    public void panScaleTest() {
        Point transformPoint = new Point(2, 2);
        transformPoint.scale(2);
        transformPoint.pan(3, 1);
        Point transformed = transformPoint.getTransformed();
        Assert.assertEquals(7, transformed.getX(), 0);
        Assert.assertEquals(5, transformed.getY(), 0);
    }

    @Test
    public void equalTest() {
        Point a = new Point(1, 1);
        Point b = new Point(1, 1);
        Assert.assertEquals(a, b);
    }

}
