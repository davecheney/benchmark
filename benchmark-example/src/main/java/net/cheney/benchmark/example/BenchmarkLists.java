package net.cheney.benchmark.example;

import java.util.ArrayList;
import java.util.LinkedList;

import net.cheney.benchmark.Benchmark;
import net.cheney.benchmark.BenchmarkResult;
import net.cheney.benchmark.Benchmarkable;

public class BenchmarkLists {
	
	public static class ArrayListBenchmarkable extends Benchmarkable {
		
		final Object o = new Object();
		private ArrayList<Object> list;
		
		@Override
		public void setup() throws Exception {
			list = new ArrayList<Object>(10);
		}
		
		public void benchmark() {
			list.clear();
			for(int i = 0; ++i < 10000 ; ) {
				list.add(o);
			}
		}
		
		public void teardown() {
			list = null;
		}
		
	}
	
	public static class LinkedListBenchmarkable extends Benchmarkable {
		
		final Object o = new Object();
		private LinkedList<Object> list;
		
		@Override
		public void setup() throws Exception {
			list = new LinkedList<Object>();
		}
		
		public void benchmark() {
			list.clear();
			for(int i = 0; ++i < 10000 ; ) {
				list.add(o);
			}
		}
		
		public void teardown() {
			list = null;
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
