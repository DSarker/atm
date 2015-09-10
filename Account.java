import java.util.ArrayList;

public class Account {

	// Name of account
	private String name;

	// Account ID number
	private String uuid;

	// The User object that owns this account
	private User holder;

	// The list of transactions for this account
	private ArrayList<Transaction> transactions;

	/**
	 * Create a new account
	 * 
	 * @param name
	 *            Name of the account
	 * @param holder
	 *            User object that holds this account
	 * @param bank
	 *            Bank that issues the account
	 */
	public Account(String name, User holder, Bank bank) {

		// Set the account name and holder
		this.name = name;
		this.holder = holder;

		// Get the new account UUID
		this.uuid = bank.getNewAccountUUID();

		// Initialize the list of transactions
		this.transactions = new ArrayList<Transaction>();
	}

	/**
	 * Returns account UUID
	 * 
	 * @return UUID
	 */
	public String getUUID() {
		return this.uuid;
	}

	public String getSummaryLine() {

		// Get the account's balance
		double balance = this.getBalance();

		// Format the summary line depending on a negative balance or not
		if (balance >= 0) {
			return String.format("%s : %s : $%.02f", this.uuid, this.name,
					balance);

		} else {
			return String.format("%s : %s : $(%.02f)", this.uuid, this.name,
					balance);
		}
	}

	/**
	 * Get the balance of this account by totaling the transaction amounts
	 * 
	 * @return The balance value
	 */
	public double getBalance() {

		double balance = 0;
		for (Transaction t : this.transactions) {
			balance += t.getAmount();
		}

		return balance;
	}

	/**
	 * Print the transaction history of the account
	 */
	public void printTransHistory() {

		System.out.printf("\nTransaction history for account: %s\n", this.uuid);
		for (int t = this.transactions.size() - 1; t >= 0; t--) {
			System.out.println(this.transactions.get(t).getSummaryLine());
		}
		System.out.println();
	}

	/**
	 * Add a transaction to a particular account
	 * 
	 * @param amount
	 *            The amount transacted
	 * @param memo
	 *            The transaction memo
	 */
	public void addTransaction(double amount, String memo) {

		// Create new Transaction object and add it to our list
		Transaction newTrans = new Transaction(amount, this, memo);
		this.transactions.add(newTrans);
	}

}
