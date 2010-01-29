package net.cheney.benchmark;

import java.util.Map;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;

public class BenchmarkResult {

	private final String title;
	private Map<String, DescriptiveStatistics> stats;

	public BenchmarkResult(String title, Map<String, DescriptiveStatistics> stats) {
		this.title = title;
		this.stats = stats;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%s: iterations: %d, repetitions: %d\n", title, -1, -1));
		for(Map.Entry<String, DescriptiveStatistics> entry : stats.entrySet()) {
			sb.append(String.format("%s [min: %f, max: %f, mean: %f, stddev: %f]\n", 
					entry.getKey(),
					entry.getValue().getMin(),
					entry.getValue().getMax(),
					entry.getValue().getMean(),
					entry.getValue().getStandardDeviation()));
		}
		return sb.toString();
		
	}

}
