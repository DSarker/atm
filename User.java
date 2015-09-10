import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {

	// User's first name
	private String firstName;

	// User's last name
	private String lastName;

	// The ID number of the user
	private String uuid;

	// The MD5 has of the user's pin number
	private byte pinHash[];

	// The list of accounts for this user
	private ArrayList<Account> accounts;

	/**
	 * Create a new user
	 * 
	 * @param firstName
	 *            User's first name
	 * @param lastName
	 *            User's last name
	 * @param pin
	 *            User's account pin number
	 * @param bank
	 *            Bank object that the user is a customer of
	 */
	public User(String firstName, String lastName, String pin, Bank bank) {

		// Set user's name
		this.firstName = firstName;
		this.lastName = lastName;

		// Store the pin's MD5 has, rather than the original value
		// for security purposes
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());

		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught");
			e.printStackTrace();
		}

		// Get a new, unique universal ID for the user
		this.uuid = bank.getNewUserUUID();

		// Create empty list of accounts
		this.accounts = new ArrayList<Account>();

		// Print out a log message
		System.out.printf("New user %s, %s with ID %s created.\n", lastName,
				firstName, this.uuid);
	}

	/**
	 * Add an acocunt for the user
	 * 
	 * @param account
	 *            The account to add
	 */
	public void addAccount(Account account) {
		this.accounts.add(account);
	}

	/**
	 * Return user's UUID
	 * 
	 * @return UUID
	 */
	public String getUUID() {
		return this.uuid;
	}

	/**
	 * Check whether a given pin matches the true USer pin
	 * 
	 * @param pin
	 *            The ping to check
	 * @return Where the pin is valid or not
	 */
	public boolean validatePin(String pin) {

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(pin.getBytes()),
					this.pinHash);

		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught");
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * Return the first name of the user
	 * 
	 * @return The first name
	 */
	public String getFirstName() {
		return this.firstName;
	}

	public void printAccountsSummary() {

		System.out.printf("\n\n%s's accounts summary:\n", this.firstName);
		for (int a = 0; a < this.accounts.size(); a++) {
			System.out.printf("%d) %s\n", a + 1, this.accounts.get(a)
					.getSummaryLine());
		}
		System.out.println();
	}

	/**
	 * Get the number of accounts the user has
	 * 
	 * @return The number of accounts
	 */
	public int numAccounts() {
		return this.accounts.size();
	}

	/**
	 * Print transaction history for a particular account
	 * 
	 * @param accountIdx
	 *            The index of the account to use
	 */
	public void printAccountSummary(int accountIndex) {
		this.accounts.get(accountIndex).printTransHistory();
	}

	/**
	 * Gets the balance for a particular account
	 * 
	 * @param accountIndex
	 *            The index of the account to use
	 * @return The balance of the account
	 */
	public double getAccountBalance(int accountIndex) {
		return this.accounts.get(accountIndex).getBalance();
	}

	/**
	 * Get the UUID of a particular account
	 * 
	 * @param accountIndex
	 *            The index of the account to use
	 * @return The UUID of the account
	 */
	public String getAcctUUID(int accountIndex) {
		return this.accounts.get(accountIndex).getUUID();
	}

	public void addAcctTransaction(int accountIndex, double amount, String memo) {
		this.accounts.get(accountIndex).addTransaction(amount, memo);
	}
}
