package bank.transaction.formatter.main.transactions;

public class PcfTransaction {
	private String date;
	private String transactionDetails;
	private double fundsIn;
	private double fundsOut;

	public PcfTransaction(String date, String transactionDetails, double fundsOut, double fundsIn) {
		super();
		this.date = date;
		this.transactionDetails = transactionDetails;
		this.fundsIn = fundsIn;
		this.fundsOut = fundsOut;
	}

	public double getFundsOut() {
		return fundsOut;
	}

	public double getFundsIn() {
		return fundsIn;
	}

	public String getTransactionDetails() {
		return transactionDetails;
	}

	public String getDate() {
		return date;
	}

	@Override
	public String toString() {
		String formattedFundsIn = String.format("%.2f", fundsIn);
		if (formattedFundsIn.equals("0.00")) {
			formattedFundsIn = "";
		}

		String formattedFundsOut = String.format("%.2f", fundsOut);
		if (formattedFundsOut.equals("0.00")) {
			formattedFundsOut = "";
		}

		return date + ", " + transactionDetails + "," + formattedFundsOut + "," + formattedFundsIn;
	}
}
