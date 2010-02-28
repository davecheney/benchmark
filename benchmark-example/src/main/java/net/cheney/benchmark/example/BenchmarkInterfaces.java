package net.cheney.benchmark.example;

import java.util.ArrayList;
import java.util.List;

import net.cheney.benchmark.Benchmark;
import net.cheney.benchmark.BenchmarkResult;
import net.cheney.benchmark.Benchmarkable;

public class BenchmarkInterfaces {
	
	public static class ArrayListBenchmarkable extends Benchmarkable {
		
		public static ArrayList<Object> a;

		public void benchmark() {
			final Object o = new Object();
			final int size = 10000;
			ArrayList<Object> l = new ArrayList<Object>(size);
			for(int i = 0; ++i < size ; ) {
				l.add(o);
			}
			
			a = l;
		}
		
	}
	
	public static class ListBenchmarkable extends Benchmarkable {

		public static List<Object> a;
		
		public void benchmark() {
			final Object o = new Object();
			final int size = 10000;
			List<Object> l = new ArrayList<Object>(size);
			for(int i = 0; ++i < size ; ) {
				l.add(o);
			}
			
			a = l;
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
		System.out.println(String.format("%s, %s",arrayListBenchmarkable.a, listBenchmarkable.a));
	}
	
}
