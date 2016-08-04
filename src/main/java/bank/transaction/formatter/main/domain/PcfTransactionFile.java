package bank.transaction.formatter.main.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bank.transaction.formatter.main.transactions.PcfTransaction;

public class PcfTransactionFile {

	String header;
	
	List<PcfTransaction> transactions;
	
	public PcfTransactionFile(String header, List<String> transactions) {
		this.header = header;

		this.transactions = new ArrayList<PcfTransaction>();
		for(String line : transactions) {
			String[] splitLine = line.split(",");
			String date = splitLine[0].trim();
			String transactionDetails = splitLine[1].trim();

			double fundsOut = 0;
			if(!splitLine[2].trim().isEmpty()) {
				fundsOut = Double.parseDouble(splitLine[2].trim());
			}

			double fundsIn = 0;
			if(splitLine.length == 4) {
				fundsIn = Double.parseDouble(splitLine[3].trim());
			}

			PcfTransaction tran = new PcfTransaction(date, transactionDetails, fundsOut, fundsIn);
			this.transactions.add(tran);
		}
	}
	
	public String getHeader() {
		return header;
	}
	
	public List<PcfTransaction> getTransactions() {
		return transactions;
	}
	
	public Map<String, PcfTransaction> summarizeTransactions() {
		Map<String, PcfTransaction> summedTransactions = new HashMap<String, PcfTransaction>();

		for(PcfTransaction transaction : transactions) {

			double fundsInSum = sumFundsIn(summedTransactions, transaction);

			double fundsOutSum = sumFundsOut(summedTransactions, transaction);

			PcfTransaction summedTransaction = new PcfTransaction(transaction.getDate(), transaction.getTransactionDetails(), fundsOutSum, fundsInSum);
			summedTransactions.put(summedTransaction.getTransactionDetails(), summedTransaction);
		}

		return summedTransactions;
	}
	
	private double sumFundsIn(Map<String, PcfTransaction> transactions, PcfTransaction transaction) {
		String transactionDetails = transaction.getTransactionDetails();
		double fundsInSum = transaction.getFundsIn();

		if (transactions.containsKey(transactionDetails)) {
			fundsInSum += transactions.get(transactionDetails).getFundsIn();
		}
		return fundsInSum;
	}
	
	private double sumFundsOut(Map<String, PcfTransaction> transactions, PcfTransaction transaction) {
		String transactionDetails = transaction.getTransactionDetails();
		double fundsOutSum = transaction.getFundsOut();
		if (transactions.containsKey(transactionDetails)) {
			fundsOutSum += transactions.get(transactionDetails).getFundsOut();
		}
		return fundsOutSum;
	}
}
