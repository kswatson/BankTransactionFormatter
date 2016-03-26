package bank.transaction.formatter.main;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BankTransactionFormatterTest {

	@Before
	public void setup() {
		File outputFile = new File("output.csv");
		if (outputFile.exists()) {
			outputFile.delete();
		}

	}

	@Test
	public void bankTransactionFormatterShouldReturnCsv() throws IOException {
		String[] args = new String[] { "testInput.csv" };
		BankTransactionFormatter.main(args);

		File outputFile = new File("output.csv");

		assertTrue(outputFile.exists());
	}

	@Test
	public void bankTransactionFormatterShouldReturnCsvWithData() throws IOException {
		String[] args = new String[] { "testInput.csv" };
		BankTransactionFormatter.main(args);

		File outputFile = new File("output.csv");

		assertTrue(outputFile.exists());
		List<String> lines = Files.readAllLines(outputFile.toPath());

		assertTrue(!lines.isEmpty());
	}

}
