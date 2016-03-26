package bank.transaction.formatter.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class BankTransactionFormatter {

	public static final String PC_FINANCIAL_HEADER = "Date, Transaction Details, Funds Out, Funds In ";

	public static void main(String[] args) throws IOException {

		String inputFileName = args[0];
		String outputFileName = createOutputFileName(inputFileName);

		File output = new File(outputFileName);
		output.createNewFile();

		List<String> lines = new ArrayList<String>();
		lines.add(PC_FINANCIAL_HEADER);
		lines.add("02/01/2016, E-TRANSFER RECEIVE Michael Watson,,225.72");
		lines.add("02/01/2016,POS MERCHANDISE FRESH BURGER,10.50,");
		Files.write(output.toPath(), lines);

	}

	private static String createOutputFileName(String inputFileName) {
		String extension = ".csv";
		String suffix = "_formatted";
		String prefix = inputFileName.substring(0, inputFileName.lastIndexOf(extension));

		String outputFileName = prefix + suffix + extension;
		return outputFileName;
	}

}
