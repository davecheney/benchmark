package net.cheney.benchmark;

public class BenchmarkParameters {

	private int repetitions = 10000;
	private int iterations = 25;

	public int warmupIterations() {
		return 1;
	}

	public int repetitions() {
		return repetitions;
	}

	public int benchmarkIterations() {
		return iterations;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	public void setRepeitions(int repetitions) {
		this.repetitions = repetitions;
	}

}
