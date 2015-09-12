import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

	// The amount of this transaction
	private double amount;

	private String date;

	private DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

	// A memo for this transaction
	private String memo;

	// The account in which the transaction was performed
	private Account inAccount;

	/**
	 * Create a new transaction
	 * 
	 * @param amount
	 *            The amount transacted
	 * @param inAccount
	 *            The account the transaction belongs to
	 */
	public Transaction(double amount, Account inAccount) {

		this.amount = amount;
		this.inAccount = inAccount;
		this.date = (formatter.format(new Date()));
		this.memo = "";
	}

	/**
	 * Creates a new transaction
	 * 
	 * @param amount
	 *            The amount transacted
	 * @param inAccount
	 *            The account the transaction belongs to
	 * @param memo
	 *            The memo for the transaction
	 */
	public Transaction(double amount, Account inAccount, String memo) {

		// Call the two-arg constructor first
		this(amount, inAccount);

		// Set the memo
		this.memo = memo;
	}

	/**
	 * Get the amount of the transaction
	 * 
	 * @return The amount
	 */
	public double getAmount() {
		return this.amount;
	}

	/**
	 * Get a string summarizing the transaction
	 * 
	 * @return The string summary string
	 */
	public String getSummaryLine() {

		if (this.amount >= 0) {
			return String.format("%s : $%.02f : %s", this.date, this.amount,
					this.memo);

		} else {
			return String.format("%s : $(%.02f): %s", this.date, -this.amount,
					this.memo);
		}
	}
}