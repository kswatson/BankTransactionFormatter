package bank.transaction.formatter.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class BankTransactionFormatter {

	public static void main(String[] args) throws IOException {
		File output = new File("output.csv");

		output.createNewFile();
		List<String> lines = new ArrayList<String>();
		lines.add("Hello,World");
		Files.write(output.toPath(), lines);

	}

}
