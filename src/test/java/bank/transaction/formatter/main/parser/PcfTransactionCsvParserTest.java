package bank.transaction.formatter.main.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import bank.transaction.formatter.main.domain.PcfTransactionFile;

public class PcfTransactionCsvParserTest {

	private static final String TEST_PC_FINANCIAL_INPUT_FILE_NAME = "PCF.csv";
	
	@Test
	public void parsePcfTransactionCsvReturnsTransactionFile() throws IOException {
		String expectedHeader = "Date, Transaction Details, Funds Out, Funds In";
		List<String> expectedTransactions = new ArrayList<String>();
		expectedTransactions.add("02/01/2016, Test funds in transaction,,225.72");
		expectedTransactions.add("02/02/2016, Test funds out transaction,10.50,");
		
		PcfTransactionFile actual = PcfTransactionCsvParser.parsePcfTransactionCsv(TEST_PC_FINANCIAL_INPUT_FILE_NAME);
		
		assertNotNull(actual);
		assertEquals(expectedHeader, actual.getHeader());
		assertEquals(expectedTransactions, actual.getTransactions());
	}
}
