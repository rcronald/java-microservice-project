package com.rcronald.ms.jsmproject.util;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.DoubleSummaryStatistics;

@RunWith(MockitoJUnitRunner.class)
public class StatisticOperationTest {

    StatisticOperation statisticOperationMock = new StatisticOperation();
    Collection<Integer> numbersCollection = Arrays.asList(33, 33, 29, 33, 33);
    Collection<Integer> blankCollection = Arrays.asList();

    @Test
    public void testAverageCollectionFiveNumbers() {
        double actualAverage = statisticOperationMock.computeAverage(numbersCollection);
        double expectedAverage = 32.2;
        assertEquals(expectedAverage, actualAverage, 0);
    }

    @Test
    public void testAverageBlankCollection() {
        double actualAverage = statisticOperationMock.computeAverage(blankCollection);
        double expectedAverage = 0;
        assertEquals(expectedAverage, actualAverage, 0);
    }

    @Test
    public void testStandardDeviationFiveNumbers() {
        double actualSD = statisticOperationMock.computeStandardDeviation(numbersCollection);
        double expectedSD = 1.79;
        assertEquals(expectedSD, actualSD, 0);
    }

    @Test
    public void testStandardDeviationBlankCollection() {
        double actualSD = statisticOperationMock.computeStandardDeviation(blankCollection);
        double expectedSD = 0;
        assertEquals(expectedSD, actualSD, 0);
    }

    @Test
    public void testStatFiveNumbers() {
        DoubleSummaryStatistics stat = statisticOperationMock.computeStatistics(numbersCollection);
        assertEquals(5, stat.getCount());
        assertEquals(33, stat.getMax(), 0);
        assertEquals(29, stat.getMin(), 0);
        assertEquals(161, stat.getSum(), 0);
    }

    @Test
    public void testStatBlankCollection() {
        DoubleSummaryStatistics stat = statisticOperationMock.computeStatistics(blankCollection);
        assertEquals(0, stat.getCount());
        assertEquals(Double.NEGATIVE_INFINITY, stat.getMax(), 0);
        assertEquals(Double.POSITIVE_INFINITY, stat.getMin(), 0);
        assertEquals(0, stat.getSum(), 0);
    }
}
