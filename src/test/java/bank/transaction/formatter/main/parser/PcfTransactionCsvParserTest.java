package bank.transaction.formatter.main.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import bank.transaction.formatter.main.domain.PcfTransactionFile;

public class PcfTransactionCsvParserTest {

	private static final String TEST_TRANSACTION_2 = "02/02/2016, Test funds out transaction,10.50,";
	private static final String TEST_TRANSACTION_1 = "02/01/2016, Test funds in transaction,,225.72";
	private static final String TEST_HEADER = "Date, Transaction Details, Funds Out, Funds In";
	private static final String TEST_PC_FINANCIAL_INPUT_FILE_NAME = "PCF.csv";
	
	@Test
	public void parsePcfTransactionCsvReturnsTransactionFile() throws IOException {
		String expectedHeader = TEST_HEADER;
		List<String> expectedTransactions = createTestTransactions();
		
		PcfTransactionFile actual = PcfTransactionCsvParser.parsePcfTransactionCsv(TEST_PC_FINANCIAL_INPUT_FILE_NAME);
		
		assertNotNull(actual);
		assertEquals(expectedHeader, actual.getHeader());
		assertExpectedListEqualsTransactionList(expectedTransactions, actual);
	}

	@Test
	public void buildOutputLinesAddsHeader() {
		String expectedHeader = TEST_HEADER;
		List<String> expectedTransactions = createTestTransactions();
		
		PcfTransactionFile expectedFile = new PcfTransactionFile(expectedHeader, expectedTransactions);
		
		List<String> actual = PcfTransactionCsvParser.buildOutputLines(expectedFile);
		
		assertEquals(expectedHeader, actual.get(0));
	}
	
	@Test
	public void buildOutputLinesAddsTransactions() {
		String expectedHeader = TEST_HEADER;
		List<String> expectedTransactions = createTestTransactions();
		
		PcfTransactionFile expectedFile = new PcfTransactionFile(expectedHeader, expectedTransactions);
		
		List<String> actual = PcfTransactionCsvParser.buildOutputLines(expectedFile);
		actual.remove(0);

		assertEquals(expectedTransactions, actual);
	}
	
	private List<String> createTestTransactions() {
		List<String> expectedTransactions = new ArrayList<String>();
		expectedTransactions.add(TEST_TRANSACTION_1);
		expectedTransactions.add(TEST_TRANSACTION_2);
		return expectedTransactions;
	}
	
	private void assertExpectedListEqualsTransactionList(List<String> expectedTransactions, PcfTransactionFile actual) {
		assertEquals(expectedTransactions.size(), actual.getTransactions().size());
		for(int i = 0; i < expectedTransactions.size(); i++) {
			String expectedTransaction = expectedTransactions.get(i);
			String actualTransaction = actual.getTransactions().get(i).toString();
			
			assertEquals(expectedTransaction, actualTransaction);
		}
	}
}
