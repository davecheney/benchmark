package net.cheney.benchmark.example;

import java.util.ArrayList;
import java.util.List;

import net.cheney.benchmark.Benchmark;
import net.cheney.benchmark.BenchmarkResult;
import net.cheney.benchmark.Benchmarkable;

public class BenchmarkInterfaces {
	
	public static class ArrayListBenchmarkable extends Benchmarkable {
		
		final Object o = new Object();
		final int size = 10000;
		private ArrayList<Object> list;
		
		@Override
		public void setup() throws Exception {
			list = new ArrayList<Object>(size);
		}
		
		public void benchmark() {
			list.clear();
			for(int i = 0; ++i < size ; ) {
				list.add(o);
			}
		}
		
		public void teardown() {
			list = null;
		}

		
	}
	
	public static class ListBenchmarkable extends Benchmarkable {

		final Object o = new Object();
		final int size = 10000;
		private List<Object> list;
		
		@Override
		public void setup() throws Exception {
			list = new ArrayList<Object>(size);
		}
		
		public void benchmark() {
			list.clear();
			for(int i = 0; ++i < size ; ) {
				list.add(o);
			}
		}
		
		public void teardown() {
			list = null;
		}
		
	}

	public static void main(String[] args) {
		ArrayListBenchmarkable arrayListBenchmarkable = new ArrayListBenchmarkable();
		ListBenchmarkable listBenchmarkable = new ListBenchmarkable();
		
		BenchmarkResult result = Benchmark.newBenchmark("List comparison")
			.of("ArrayList", arrayListBenchmarkable)
			.and("List", listBenchmarkable)
			.setRepetitions(2000)
			.run();
		System.out.println(result.toString());
	}
	
}
