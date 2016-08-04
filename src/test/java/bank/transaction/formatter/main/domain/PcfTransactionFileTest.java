package bank.transaction.formatter.main.domain;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bank.transaction.formatter.main.transactions.PcfTransaction;

public class PcfTransactionFileTest {

	private static final String TEST_HEADER = "Date, Transaction Details, Funds Out, Funds In";
	
	private PcfTransactionFile pcfTransactionFile;
	
	@Before
	public void setup() {
		pcfTransactionFile = new PcfTransactionFile(TEST_HEADER, createTestTransactions());
	}
	
	@Test
	public void summarizeTransactionsReturnsDetailsToTransactionMap() {
		Map<String, PcfTransaction> actual = pcfTransactionFile.summarizeTransactions();
		
		assertNotNull(actual);
	}
	
	@Test
	public void summarizeTransactionsSummarizesFundsInFromSameSource() {
		Map<String, PcfTransaction> actual = pcfTransactionFile.summarizeTransactions();
		double fundsIn = actual.get("Same Location Funds In").getFundsIn();
		
		
		Assert.assertEquals(236.00, fundsIn,0.0);
	}
	
	@Test
	public void summarizeTransactionsSummarizesFundsOutFromSameSource() {
		Map<String, PcfTransaction> actual = pcfTransactionFile.summarizeTransactions();
		double fundsOut = actual.get("Same Location Funds Out").getFundsOut();
		
		
		Assert.assertEquals(236.00, fundsOut,0.0);
	}

	private List<String> createTestTransactions() {
		List<String> expectedTransactions = new ArrayList<String>();
		expectedTransactions.add("02/01/2016, Same Location Funds Out,225.50,");
		expectedTransactions.add("02/02/2016, Same Location Funds Out,10.50,");
		expectedTransactions.add("02/02/2016, Test funds out transaction,10.50,");
		expectedTransactions.add("02/03/2016, Same Location Funds In,,225.50");
		expectedTransactions.add("02/04/2016, Same Location Funds In,,10.50");

		return expectedTransactions;
	}
	
}
