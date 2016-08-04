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

	private static final String TEST_PC_FINANCIAL_INPUT_FILE_NAME = "PCF.csv";
	private static final String TEST_INPUT_FILE_NAME_2 = "PCF_2.csv";

	private static final String TEST_OUTPUT_FILE_NAME_2 = "PCF_2_formatted.csv";
	private static final String TEST_PC_FINANCIAL_OUTPUT_FILE_NAME = "PCF_formatted.csv";

	@Before
	public void setup() {
		deleteFileIfExists(TEST_PC_FINANCIAL_OUTPUT_FILE_NAME);
		deleteFileIfExists(TEST_OUTPUT_FILE_NAME_2);
	}

	@Test
	public void bankTransactionFormatterShouldReturnFileNamedAfterInputFileWithAnyName() throws IOException {
		String[] args = new String[] { TEST_INPUT_FILE_NAME_2 };

		BankTransactionFormatter.main(args);

		File outputFile = new File(TEST_OUTPUT_FILE_NAME_2);
		assertTrue(outputFile.exists());
	}

	@Test
	public void bankTransactionFormatterShouldReturnCsvWithData() throws IOException {
		String[] args = new String[] { TEST_PC_FINANCIAL_INPUT_FILE_NAME };

		BankTransactionFormatter.main(args);

		File outputFile = new File(TEST_PC_FINANCIAL_OUTPUT_FILE_NAME);
		assertTrue(outputFile.exists());

		List<String> lines = Files.readAllLines(outputFile.toPath());
		assertTrue(!lines.isEmpty());
	}

	@Test
	public void bankTransactionFormatterShouldReturnPcFinancialTransactionHeader() throws IOException {
		String[] args = new String[] { TEST_PC_FINANCIAL_INPUT_FILE_NAME };

		BankTransactionFormatter.main(args);

		String expectedHeader = getLinesFromFileNamed(TEST_PC_FINANCIAL_INPUT_FILE_NAME).get(0);

		String actualHeader = getLinesFromFileNamed(TEST_PC_FINANCIAL_OUTPUT_FILE_NAME).get(0);

		assertEquals(expectedHeader.trim(), actualHeader);
	}

	@Test
	public void bankTransactionFormatterShouldReturnPcFinancialTransactions() throws IOException {
		String[] args = new String[] { TEST_PC_FINANCIAL_INPUT_FILE_NAME };

		BankTransactionFormatter.main(args);

		List<String> inputLines = getLinesFromFileNamed(TEST_PC_FINANCIAL_INPUT_FILE_NAME);
		List<String> outputLines = getLinesFromFileNamed(TEST_PC_FINANCIAL_OUTPUT_FILE_NAME);

		assertEquals(inputLines.get(1), outputLines.get(1));
		assertEquals(inputLines.get(2), outputLines.get(2));
	}

	@Test
	public void bankTransactionFormatterShouldSummarizeFundsInTransactionsFromSameSource() throws IOException {
		String[] args = new String[] { TEST_INPUT_FILE_NAME_2 };

		BankTransactionFormatter.main(args);

		List<String> outputLines = getLinesFromFileNamed(TEST_OUTPUT_FILE_NAME_2);

		assertEquals("02/04/2016, Same Location Funds In,,236.00", outputLines.get(3));
	}

	@Test
	public void bankTransactionFormatterShouldSummarizeFundsOutTransactionsFromSameSource() throws IOException {
		String[] args = new String[] { TEST_INPUT_FILE_NAME_2 };

		BankTransactionFormatter.main(args);

		List<String> outputLines = getLinesFromFileNamed(TEST_OUTPUT_FILE_NAME_2);

		assertTrue(outputLines.contains("02/02/2016, Same Location Funds Out,236.00,"));
	}

	private void deleteFileIfExists(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		}
	}

	private List<String> getLinesFromFileNamed(String fileName) throws IOException {
		File file = new File(fileName);
		List<String> lines = Files.readAllLines(file.toPath());
		return lines;
	}
}
