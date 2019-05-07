package de.elite.games.drawlib;

import com.github.martinfrank.drawlib.Aggregation;
import com.github.martinfrank.drawlib.Shape;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class AggregationTest {

    @Test
    public void scaleTest() {
        Aggregation aggregation = createAggregation();
        aggregation.scale(2);

        Aggregation transformed = aggregation.getTransformed();
        Assert.assertEquals(8, transformed.getWidth(), 0);
        Assert.assertEquals(8, transformed.getHeight(), 0);
    }

    @Test
    public void panTest() {
        Aggregation aggregation = createAggregation();
        aggregation.pan(3, 1);

        Aggregation transformed = aggregation.getTransformed();
        Assert.assertEquals(4, transformed.getWidth(), 0);
        Assert.assertEquals(4, transformed.getHeight(), 0);
    }

    @Test
    public void panScaleTest() {
        Aggregation aggregation = createAggregation();
        aggregation.scale(2);
        aggregation.pan(3, 1);

        Aggregation transformed = aggregation.getTransformed();
        Assert.assertEquals(8, transformed.getWidth(), 0);
        Assert.assertEquals(8, transformed.getHeight(), 0);
    }

    private Aggregation createAggregation() {
        Shape a = createTransSqare(0, 0);
        Shape b = createTransSqare(0, 2);
        Shape c = createTransSqare(2, 0);
        Shape d = createTransSqare(2, 2);

        Aggregation aggregation = new Aggregation(Arrays.asList(a, b, c, d));
        Assert.assertEquals(4, aggregation.getWidth(), 0);
        Assert.assertEquals(4, aggregation.getHeight(), 0);
        return aggregation;
    }

    @Test
    public void constructorTest() {
        try {
            new Aggregation();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    private Shape createTransSqare(int x, int y) {
        Shape shape = ShapeTest.createSquare();
        shape.pan(x, y);
        return shape.getTransformed();
    }
}
