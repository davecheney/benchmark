package net.cheney.benchmark.example;

import java.util.ArrayList;
import java.util.LinkedList;

import net.cheney.benchmark.Benchmark;
import net.cheney.benchmark.BenchmarkResult;
import net.cheney.benchmark.Benchmarkable;

public class BenchmarkLists {
	
	public static class ArrayListBenchmarkable extends Benchmarkable {

		public void benchmark() {
			final Object o = new Object();
			ArrayList l = new ArrayList(10);
			for(int i = 0; i < 10000 ; ++i) {
				l.add(o);
			}
		}
		
	}
	
	public static class LinkedListBenchmarkable extends Benchmarkable {

		public void benchmark() {
			final Object o = new Object();
			LinkedList l = new LinkedList();
			for(int i = 0; i < 10000 ; ++i) {
				l.add(o);
			}
		}
		
	}

	public static void main(String[] args) {
		BenchmarkResult result = Benchmark.newBenchmark("List comparison")
			.of("ArrayList", new ArrayListBenchmarkable())
			.and("LinkedList", new LinkedListBenchmarkable())
			.setRepetitions(1000)
			.run();
		System.out.println(result.toString());
	}
	
}
