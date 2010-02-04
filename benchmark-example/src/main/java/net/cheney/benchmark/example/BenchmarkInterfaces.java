package net.cheney.benchmark.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.cheney.benchmark.Benchmark;
import net.cheney.benchmark.BenchmarkResult;
import net.cheney.benchmark.Benchmarkable;

public class BenchmarkInterfaces {
	
	public static class ArrayListBenchmarkable extends Benchmarkable {

		public void benchmark() {
			final Object o = new Object();
			final int size = 10000;
			ArrayList l = new ArrayList(size);
			for(int i = 0; ++i < size ; ) {
				l.add(o);
			}
			assert l.size() == size;
			LinkedList k = new LinkedList();
			for(int i = 0; ++i < size ; ) {
				k.add(o);
			}
			assert k.size() == size;
		}
		
	}
	
	public static class ListBenchmarkable extends Benchmarkable {

		public void benchmark() {
			final Object o = new Object();
			final int size = 10000;
			List l = new ArrayList(size);
			for(int i = 0; ++i < size ; ) {
				l.add(o);
			}
			assert l.size() == size;
			l = new LinkedList();
			for(int i = 0; ++i < size ; ) {
				l.add(o);
			}
			assert l.size() == size;
		}
		
	}

	public static void main(String[] args) {
		BenchmarkResult result = Benchmark.newBenchmark("List comparison")
			.of("ArrayList", new ArrayListBenchmarkable())
			.and("List", new ListBenchmarkable())
			.setRepetitions(2000)
			.run();
		System.out.println(result.toString());
	}
	
}
