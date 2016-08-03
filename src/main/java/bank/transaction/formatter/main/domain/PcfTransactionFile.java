package bank.transaction.formatter.main.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bank.transaction.formatter.main.transactions.PcfTransaction;

public class PcfTransactionFile {

	String header;
	
	List<String> transactions;
	
	public PcfTransactionFile(String header, List<String> transactions) {
		this.header = header;
		this.transactions =  transactions;
	}
	
	public String getHeader() {
		return header;
	}
	
	public List<String> getTransactions() {
		return transactions;
	}
	
	public Map<String, PcfTransaction> summarizeTransactions() {
		Map<String, PcfTransaction> transactions = new HashMap<String, PcfTransaction>();
		for (String line : this.transactions) {
			String[] splitLine = line.split(",");
			String date = splitLine[0].trim();
			String transactionDetails = splitLine[1].trim();
			String fundsOut = splitLine[2].trim();

			double fundsInSum = 0;
			if (splitLine.length == 4) {
				fundsInSum = sumFundsIn(transactions, transactionDetails, splitLine[3].trim());
			}

			double fundsOutSum = 0;
			if (!fundsOut.isEmpty()) {
				fundsOutSum = sumFundsOut(transactions, transactionDetails, fundsOut);
			}

			PcfTransaction transaction = new PcfTransaction(date, transactionDetails, fundsOutSum, fundsInSum);
			transactions.put(transactionDetails, transaction);
		}
		return transactions;
	}
	
	private double sumFundsIn(Map<String, PcfTransaction> transactions, String transactionDetails,
			String fundsIn) {
		double fundsInSum = Double.parseDouble(fundsIn);
		if (transactions.containsKey(transactionDetails)) {
			fundsInSum += transactions.get(transactionDetails).getFundsIn();
		}
		return fundsInSum;
	}
	
	private double sumFundsOut(Map<String, PcfTransaction> transactions, String transactionDetails,
			String fundsOut) {
		double fundsOutSum = Double.parseDouble(fundsOut);
		if (transactions.containsKey(transactionDetails)) {
			fundsOutSum += transactions.get(transactionDetails).getFundsOut();
		}
		return fundsOutSum;
	}
}
