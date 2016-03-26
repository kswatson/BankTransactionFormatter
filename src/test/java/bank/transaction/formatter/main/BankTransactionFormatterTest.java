package bank.transaction.formatter.main;

import static org.junit.Assert.assertEquals;
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

	private static final String TEST_PC_FINANCIAL_INPUT_FILE_NAME = "PCF.csv";
	private static final String TEST_PC_FINANCIAL_OUTPUT_FILE_NAME = "PCF_formatted.csv";

	@Before
	public void setup() {
		deleteFileIfExists(TEST_OUTPUT_FILE_NAME);
		deleteFileIfExists(TEST_OUTPUT_FILE_NAME_2);
		deleteFileIfExists(TEST_PC_FINANCIAL_OUTPUT_FILE_NAME);
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
	public void bankTransactionFormatterShouldReturnPcFinancialTransactionHeader() throws IOException {
		String[] args = new String[] { TEST_PC_FINANCIAL_INPUT_FILE_NAME };
		BankTransactionFormatter.main(args);

		File outputFile = new File(TEST_PC_FINANCIAL_OUTPUT_FILE_NAME);
		assertTrue(outputFile.exists());

		File inputFile = new File(TEST_PC_FINANCIAL_INPUT_FILE_NAME);
		List<String> inputLines = Files.readAllLines(inputFile.toPath());

		List<String> lines = Files.readAllLines(outputFile.toPath());

		assertEquals(BankTransactionFormatter.PC_FINANCIAL_HEADER, lines.get(0));
		assertEquals(inputLines.get(0), lines.get(0));
	}

	@Test
	public void bankTransactionFormatterShouldReturnPcFinancialTransactions() throws IOException {
		String[] args = new String[] { TEST_PC_FINANCIAL_INPUT_FILE_NAME };
		BankTransactionFormatter.main(args);

		File outputFile = new File(TEST_PC_FINANCIAL_OUTPUT_FILE_NAME);

		assertTrue(outputFile.exists());
		List<String> lines = Files.readAllLines(outputFile.toPath());

		assertEquals("02/01/2016, E-TRANSFER RECEIVE Michael Watson,,225.72", lines.get(1));
	}

	@Test
	public void bankTransactionFormatterShouldReturnPcFinancialTransactionDetails() throws IOException {
		String[] args = new String[] { TEST_PC_FINANCIAL_INPUT_FILE_NAME };

		BankTransactionFormatter.main(args);

		File outputFile = new File(TEST_PC_FINANCIAL_OUTPUT_FILE_NAME);
		List<String> lines = Files.readAllLines(outputFile.toPath());
		assertTrue(lines.get(1).contains("E-TRANSFER RECEIVE Michael Watson"));
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
