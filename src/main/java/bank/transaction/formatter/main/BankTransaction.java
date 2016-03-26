package bank.transaction.formatter.main;

public class BankTransaction {
	private String date;
	private String transactionDetails;
	private String fundsIn;
	private double fundsOut;

	public BankTransaction(String date, String transactionDetails, String fundsIn, double fundsOut) {
		super();
		this.date = date;
		this.transactionDetails = transactionDetails;
		this.fundsIn = fundsIn;
		this.fundsOut = fundsOut;
	}

	public double getFundsOut() {
		return fundsOut;
	}

	@Override
	public String toString() {
		String formattedFundsOut = String.format("%.2f", fundsOut);
		if (formattedFundsOut.equals("0.00")) {
			formattedFundsOut = "";
		}
		return date + ", " + transactionDetails + "," + fundsIn + "," + formattedFundsOut;
	}
}
