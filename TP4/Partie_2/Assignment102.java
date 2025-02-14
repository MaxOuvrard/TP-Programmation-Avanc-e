// Estimate the value of Pi using Monte-Carlo Method, using parallel program
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
class PiMonteCarlo {
	AtomicInteger nAtomSuccess;
	int nProcessors;
	int nThrows;
	double value;
	class MonteCarlo implements Runnable {
		@Override
		public void run() {
			double x = Math.random();
			double y = Math.random();
			if (x * x + y * y <= 1)
				nAtomSuccess.incrementAndGet();
		}
	}
	public PiMonteCarlo(int i, int processors) {
		this.nAtomSuccess = new AtomicInteger(0);
		this.nThrows = i;
		this.nProcessors = processors;
		this.value = 0;
	}
	public double getPi() {
		// int nProcessors = Runtime.getRuntime().availableProcessors();
		ExecutorService executor = Executors.newWorkStealingPool(nProcessors);
		for (int i = 1; i <= nThrows; i++) {
			Runnable worker = new MonteCarlo();
			executor.execute(worker);
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
		value = 4.0 * nAtomSuccess.get() / nThrows;
		return value;
	}
}
public class Assignment102 {
	public static void main(String[] args) {
		int nThrows = 12000000;
		int nProcessors = 12;
		PiMonteCarlo PiVal = new PiMonteCarlo(nThrows,nProcessors);
		long startTime = System.currentTimeMillis();
		double value = PiVal.getPi();
		long stopTime = System.currentTimeMillis();
		System.out.println("Approx value:" + value);
		System.out.println("Difference to exact value of pi: " + (value - Math.PI));
		System.out.println("Error: " + (value - Math.PI) / Math.PI * 100 + " %");
		System.out.println("Available processors: " + nProcessors);
		System.out.println("Time Duration: " + (stopTime - startTime) + "ms");


		try {
			FileWriter writer = new FileWriter("out-assignements102.txt", true);
			writer.write(
					"" + PiVal.nThrows + " " +
					(stopTime - startTime) + " " +
					(Math.abs((PiVal.value - Math.PI)) / Math.PI) + " " + 
					nProcessors + "\n");
			writer.close();
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}
	}
}