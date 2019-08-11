package com.rcronald.ms.jsmproject.domain;

public class CustomerStatistic {

    private double average;
    private double count;
    private double min;
    private double max;
    private double standardDeviation;
    private double sum;

    public CustomerStatistic(double average, double count, double min, double max, 
        double standardDeviation, double sum) {
        this.setAverage(average);
        this.setCount(count);
        this.setMin(min);
        this.setMax(max);
        this.setStandardDeviation(standardDeviation);
        this.setSum(sum);
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

}