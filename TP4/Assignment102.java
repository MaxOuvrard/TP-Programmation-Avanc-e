import java.io.FileWriter;
import java.io.IOException;

public class Assignment102 {

	public static void main(String[] args) {
		PiMonteCarlo PiVal = new PiMonteCarlo(10000000);
		long startTime = System.currentTimeMillis();
		double value = PiVal.getPi();
		long stopTime = System.currentTimeMillis();
		System.out.println("Approx value:" + value);
		System.out.println("Difference to exact value of pi: " + (value - Math.PI));
		System.out.println("Error: " + (value - Math.PI) / Math.PI * 100 + " %");
		System.out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
		System.out.println("Time Duration: " + (stopTime - startTime) + "ms");

		try {
			FileWriter writer = new FileWriter("out-assignements102.txt", true);
			writer.write(
					"" + Runtime.getRuntime().availableProcessors() + "\n" +
					(stopTime - startTime) + "\n" +
					PiVal.nThrows + "\n");
			writer.close();
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}
	}
}
