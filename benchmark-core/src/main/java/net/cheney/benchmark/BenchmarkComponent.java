package net.cheney.benchmark;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;

final class BenchmarkComponent {

	final String title;
	private final Benchmarkable benchmarkable;
	final DescriptiveStatistics statistics = new DescriptiveStatistics();
	
	public BenchmarkComponent(String title, Benchmarkable benchmarkable) {
		this.title = title;
		this.benchmarkable = benchmarkable;
	}

	public Benchmarkable benchmarkable() {
		return benchmarkable;
	}

	public void recordBenchmarkDuration(long duration) {
		statistics.addValue(duration);
	}
	
	@Override
	public String toString() {
		return title;
	}

}
