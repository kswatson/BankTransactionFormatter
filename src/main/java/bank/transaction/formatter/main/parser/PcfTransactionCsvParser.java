package bank.transaction.formatter.main.parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bank.transaction.formatter.main.domain.PcfTransactionFile;
import bank.transaction.formatter.main.transactions.PcfTransaction;

public class PcfTransactionCsvParser {

	public static List<String> buildOutputLines(PcfTransactionFile transactionFile) {

		Map<String, PcfTransaction> transactions = transactionFile.summarizeTransactions();

		List<String> outputLines = new ArrayList<String>();
		outputLines.add(transactionFile.getHeader());
		for (PcfTransaction transaction : transactions.values()) {
			outputLines.add(transaction.toString());
		}
		return outputLines;
	}
	
	public static PcfTransactionFile parsePcfTransactionCsv(String fileName) throws IOException {
		List<String> inputLines = getLinesFromFileNamed(fileName);
		String header = inputLines.get(0);
		inputLines.remove(0);

		return new PcfTransactionFile(header, inputLines);		
	}
	
	private static List<String> getLinesFromFileNamed(String fileName) throws IOException {
		File file = new File(fileName);
		List<String> lines = Files.readAllLines(file.toPath());
		return lines;
	}
}
