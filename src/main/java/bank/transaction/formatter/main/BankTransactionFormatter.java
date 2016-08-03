package bank.transaction.formatter.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bank.transaction.formatter.main.transactions.PcfTransaction;

public class BankTransactionFormatter {

	public static void main(String[] args) throws IOException {
		String inputFileName = args[0];
		List<String> inputLines = getLinesFromFileNamed(inputFileName);

		List<String> outputLines = buildOutputLines(inputLines);

		File outputFile = createOutputFile(inputFileName);
		Files.write(outputFile.toPath(), outputLines);
	}

	private static List<String> buildOutputLines(List<String> inputLines) {
		String header = inputLines.get(0);
		inputLines.remove(0);

		Map<String, PcfTransaction> transactions = new HashMap<String, PcfTransaction>();
		for (String line : inputLines) {
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

		List<String> outputLines = new ArrayList<String>();
		outputLines.add(header);
		for (PcfTransaction transaction : transactions.values()) {
			outputLines.add(transaction.toString());
		}
		return outputLines;
	}

	private static double sumFundsIn(Map<String, PcfTransaction> transactions, String transactionDetails,
			String fundsIn) {
		double fundsInSum = Double.parseDouble(fundsIn);
		if (transactions.containsKey(transactionDetails)) {
			fundsInSum += transactions.get(transactionDetails).getFundsIn();
		}
		return fundsInSum;
	}

	private static double sumFundsOut(Map<String, PcfTransaction> transactions, String transactionDetails,
			String fundsOut) {
		double fundsOutSum = Double.parseDouble(fundsOut);
		if (transactions.containsKey(transactionDetails)) {
			fundsOutSum += transactions.get(transactionDetails).getFundsOut();
		}
		return fundsOutSum;
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
