package bank.transaction.formatter.main;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BankTransactionFormatterTest {

	private static final String TEST_OUTPUT_FILE_NAME_2 = "testInput2_formatted.csv";
	private static final String TEST_INPUT_FILE_NAME_2 = "testInput2.csv";
	private static final String TEST_INPUT_FILE_NAME = "testInput.csv";
	private static final String TEST_OUTPUT_FILE_NAME = "testInput_formatted.csv";

	@Before
	public void setup() {
		deleteFileIfExists(TEST_OUTPUT_FILE_NAME);
		deleteFileIfExists(TEST_OUTPUT_FILE_NAME_2);
	}

	@Test
	public void bankTransactionFormatterShouldReturnCsv() throws IOException {
		String[] args = new String[] { TEST_INPUT_FILE_NAME };
		BankTransactionFormatter.main(args);

		File outputFile = new File(TEST_OUTPUT_FILE_NAME);

		assertTrue(outputFile.exists());
	}

	@Test
	public void bankTransactionFormatterShouldReturnCsvWithData() throws IOException {
		String[] args = new String[] { TEST_INPUT_FILE_NAME };
		BankTransactionFormatter.main(args);

		File outputFile = new File(TEST_OUTPUT_FILE_NAME);

		assertTrue(outputFile.exists());
		List<String> lines = Files.readAllLines(outputFile.toPath());

		assertTrue(!lines.isEmpty());
	}

	@Test
	public void bankTransactionFormatterShouldReturnFileNamedAfterInputFileWithAnyName() throws IOException {
		String[] args = new String[] { TEST_INPUT_FILE_NAME_2 };
		BankTransactionFormatter.main(args);

		File outputFile = new File(TEST_OUTPUT_FILE_NAME_2);

		assertTrue(outputFile.exists());
	}

	private void deleteFileIfExists(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		}
	}

}
