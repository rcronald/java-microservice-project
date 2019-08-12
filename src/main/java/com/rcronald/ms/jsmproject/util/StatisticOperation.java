package com.rcronald.ms.jsmproject.util;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.DoubleSummaryStatistics;

import org.springframework.stereotype.Component;

@Component
public class StatisticOperation {

    public StatisticOperation(){

    }

	public double computeStandardDeviation(Collection<Integer> numbers) {
		DoubleSummaryStatistics stats = numbers.stream()
            .mapToDouble(value -> value)
            .summaryStatistics();
        
        double rawSum = numbers.stream()
            .mapToDouble((x) -> Math.pow(x - stats.getAverage(), 2))
            .sum();

        double sd = Math.sqrt(rawSum / (numbers.size() - 1));

        return formatDecimals(sd);
	}

    public DoubleSummaryStatistics computeStatistics(Collection<Integer> numbers) {
		DoubleSummaryStatistics stats = numbers.stream()
            .mapToDouble(value -> value)
			.summaryStatistics();
			
        return stats;
    }
    
	public double computeAverage(Collection<Integer> numbers) {
		double avg = numbers.stream()
            .mapToDouble(value -> value)
            .summaryStatistics()
            .getAverage();
			
        return avg;
	}

	public double formatDecimals(double value){
        DecimalFormat df = new DecimalFormat("#.##");
        String formatted = df.format(value);
        return Double.parseDouble(formatted);
    }
}