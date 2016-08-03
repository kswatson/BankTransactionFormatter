package bank.transaction.formatter.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bank.transaction.formatter.main.domain.PcfTransactionFile;
import bank.transaction.formatter.main.transactions.PcfTransaction;

public class BankTransactionFormatter {

	public static void main(String[] args) throws IOException {
		String inputFileName = args[0];
		
		PcfTransactionFile transactionFile = parsePcfTransactionCsv(inputFileName);

		List<String> outputLines = buildOutputLines(transactionFile);

		File outputFile = createOutputFile(inputFileName);
		Files.write(outputFile.toPath(), outputLines);
	}
	
	private static PcfTransactionFile parsePcfTransactionCsv(String fileName) throws IOException {
		List<String> inputLines = getLinesFromFileNamed(fileName);
		String header = inputLines.get(0);
		inputLines.remove(0);

		return new PcfTransactionFile(header, inputLines);		
	}

	private static List<String> buildOutputLines(PcfTransactionFile transactionFile) {

		Map<String, PcfTransaction> transactions = transactionFile.summarizeTransactions();

		List<String> outputLines = new ArrayList<String>();
		outputLines.add(transactionFile.getHeader());
		for (PcfTransaction transaction : transactions.values()) {
			outputLines.add(transaction.toString());
		}
		return outputLines;
	}

	private static File createOutputFile(String inputFileName) throws IOException {
		File outputFile = new File(buildOutputFileName(inputFileName));
		outputFile.createNewFile();

		return outputFile;
	}

	private static String buildOutputFileName(String inputFileName) {
		String extension = ".csv";
		String suffix = "_formatted";
		String prefix = inputFileName.substring(0, inputFileName.lastIndexOf(extension));

		return prefix + suffix + extension;
	}

	private static List<String> getLinesFromFileNamed(String fileName) throws IOException {
		File file = new File(fileName);
		List<String> lines = Files.readAllLines(file.toPath());
		return lines;
	}
}
