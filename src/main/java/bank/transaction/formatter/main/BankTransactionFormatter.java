package bank.transaction.formatter.main;

import java.io.File;
import java.io.IOException;

public class BankTransactionFormatter {

	public static void main(String[] args) throws IOException {
		File output = new File("output.csv");

		output.createNewFile();

	}

}
