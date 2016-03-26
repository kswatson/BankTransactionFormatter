package bank.transaction.formatter.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class BankTransactionFormatter {

	public static void main(String[] args) throws IOException {
		String inputFileName = args[0];
		List<String> inputLines = getLinesFromFileNamed(inputFileName);

		File outputFile = createOutputFile(inputFileName);

		Files.write(outputFile.toPath(), inputLines);
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
