package bank.transaction.formatter.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import bank.transaction.formatter.main.domain.PcfTransactionFile;
import bank.transaction.formatter.main.parser.PcfTransactionCsvParser;

public class BankTransactionFormatter {

	public static void main(String[] args) throws IOException {
		
		String inputFileName = args[0];
		
		PcfTransactionFile transactionFile = PcfTransactionCsvParser.parsePcfTransactionCsv(inputFileName);

		List<String> outputLines = PcfTransactionCsvParser.buildOutputLines(transactionFile);

		File outputFile = createOutputFile(inputFileName);
		Files.write(outputFile.toPath(), outputLines);
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
}
