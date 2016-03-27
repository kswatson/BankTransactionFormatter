package bank.transaction.formatter.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		Map<String, BankTransaction> transactions = new HashMap<String, BankTransaction>();
		for (String line : inputLines) {
			String[] splitLine = line.split(",");
			String date = splitLine[0].trim();
			String transactionDetails = splitLine[1].trim();
			String fundsIn = splitLine[2].trim();

			double fundsOutSum = 0;
			if (splitLine.length == 4) {
				String fundsOut = splitLine[3].trim();
				fundsOutSum = sumFundsOut(transactions, transactionDetails, fundsOut);
			}

			BankTransaction transaction = new BankTransaction(date, transactionDetails, fundsIn, fundsOutSum);
			transactions.put(transactionDetails, transaction);
		}

		List<String> outputLines = new ArrayList<String>();
		outputLines.add(header);
		for (BankTransaction transaction : transactions.values()) {
			outputLines.add(transaction.toString());
		}
		return outputLines;
	}

	private static double sumFundsOut(Map<String, BankTransaction> transactions, String transactionDetails,
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
