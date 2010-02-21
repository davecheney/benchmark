package net.cheney.benchmark;

public abstract class Benchmarkable {

	public void setup() throws Exception {
		
	}
	
	public abstract void benchmark();
	
	public void teardown() {
		
	}

}
