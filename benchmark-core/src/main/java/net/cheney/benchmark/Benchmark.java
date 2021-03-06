package net.cheney.benchmark;

import static java.lang.System.currentTimeMillis;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;

public final class Benchmark {

	private final BenchmarkComponent[] benchmarks;
	private final BenchmarkParameters parameters;
	private final String title;

	protected Benchmark(String title, List<BenchmarkComponent> benchmarks, BenchmarkParameters parameters) {
		this.title = title;
		this.benchmarks = benchmarks.toArray(new BenchmarkComponent[] {} );
		this.parameters = parameters;
	}

	public static Benchmark.Builder newBenchmark(String title) {
		return new Benchmark.Builder(title);
	}
	
	public BenchmarkResult run() {
		warmup();
		return benchmark();
	}
	
	private BenchmarkResult benchmark() {
		final int benchmarkIterations = parameters.benchmarkIterations();
		for(int i = 0 ; ++i < benchmarkIterations ; ) {
			benchmarkInnerLoop();
		}
		return new BenchmarkResult(title, benchmarkComponentsToMap());
	}

	private Map<String, DescriptiveStatistics> benchmarkComponentsToMap() {
		Map<String, DescriptiveStatistics> m = new LinkedHashMap<String, DescriptiveStatistics>();
		for(BenchmarkComponent item : benchmarks) {
			m.put(item.title, item.statistics);
		}
		return m;
	}

	protected void benchmarkInnerLoop() {
		final int repetitions = parameters.repetitions();
		for(BenchmarkComponent component : benchmarks) {
			long duration = benchmarkComponent(component, repetitions);
			component.recordBenchmarkDuration(duration);
		}
	}

	private long benchmarkComponent(BenchmarkComponent component, int repetitions) {
		final Benchmarkable benchmarkable = component.benchmarkable();
		setup(benchmarkable);
		long start = currentTimeMillis();
		benchmark(benchmarkable, repetitions);
		long duration = currentTimeMillis() - start;
		benchmarkable.teardown();
		return duration;
	}

	private void benchmark(Benchmarkable benchmarkable, int repetitions) {
		for(int i = 0 ; i < repetitions ; ++i) 
			benchmarkable.benchmark();
	}

	private void setup(Benchmarkable benchmarkable) {
		try {
			benchmarkable.setup();
		} catch (Exception e) {
			throw new Error(e);
		}
	}

	private void warmup() {
		final int warmupIterations = parameters.warmupIterations();
		for(int i = 0 ; i < warmupIterations ; ++i) {
			benchmarkInnerLoop();
		}
	}

	public static class Builder {

		private final String title;
		private final List<BenchmarkComponent> benchmarks = new ArrayList<BenchmarkComponent>();
		private final BenchmarkParameters parameters = new BenchmarkParameters();

		public Builder(String title) {
			this.title = title;
		}

		public Builder of(String title, Benchmarkable benchmarkable) {
			benchmarks.add(new BenchmarkComponent(title, benchmarkable));
			return this;
		}

		public Builder and(String title, Benchmarkable benchmarkable) {
			return of(title, benchmarkable);
		}
		
		public Benchmark build() {
			return new Benchmark(title, benchmarks, parameters);
		}
		
		public BenchmarkResult run() {
			return build().run();
		}

		public Builder setIterations(int iterations) {
			parameters.setIterations(iterations);
			return this;
		}

		public Builder setRepetitions(int repetitions) {
			parameters.setRepeitions(repetitions);
			return this;
		}
		
	}

}
