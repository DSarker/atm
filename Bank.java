import java.util.ArrayList;
import java.util.Random;

public class Bank {

	// Name of the bank
	private String name;

	// List of users
	private ArrayList<User> users;

	// List of accounts
	private ArrayList<Account> accounts;

	/**
	 * Create a new Bank object with empty lists of users and accounts
	 * 
	 * @param name
	 *            The name of the bank
	 */
	public Bank(String name) {

		this.name = name;
		this.users = new ArrayList<User>();
		this.accounts = new ArrayList<Account>();
	}

	/**
	 * Generate a new universally unique ID for a user.
	 * 
	 * @return The new UUID
	 */
	public String getNewUserUUID() {

		// Initialize
		String uuid;
		Random rng = new Random();
		int length = 6;

		boolean nonUnique;

		// Continue generating new UUID till it's unique
		do {
			// Generate the number
			uuid = "";
			for (int c = 0; c < length; c++) {
				uuid += ((Integer) rng.nextInt(10)).toString();
			}

			// Check UUID to make sure it's unique
			nonUnique = false;
			for (User u : this.users) {
				if (uuid.compareTo(u.getUUID()) == 0) {
					nonUnique = true;
					break;
				}
			}
		} while (nonUnique);

		return uuid;
	}

	/**
	 * Gets a new universally unique identifier
	 * 
	 * @return The new UUID
	 */
	public String getNewAccountUUID() {

		// Initialize
		String uuid;
		Random rng = new Random();
		int length = 10;

		boolean nonUnique;

		// Continue generating new UUID till it's unique
		do {
			// Generate the number
			uuid = "";
			for (int c = 0; c < length; c++) {
				uuid += ((Integer) rng.nextInt(10)).toString();
			}

			// Check UUID to make sure it's unique
			nonUnique = false;
			for (Account a : this.accounts) {
				if (uuid.compareTo(a.getUUID()) == 0) {
					nonUnique = true;
					break;
				}
			}
		} while (nonUnique);

		return uuid;
	}

	/**
	 * Add an account
	 * 
	 * @param account
	 *            The account to add
	 */
	public void addAccount(Account account) {
		this.accounts.add(account);
	}

	/**
	 * Create a new user of the bank
	 * 
	 * @param firstName
	 *            The user's first name
	 * @param lastName
	 *            The user's last name
	 * @param pin
	 *            The user's PIN
	 * @return The new User object
	 */
	public User addUser(String firstName, String lastName, String pin) {

		// Create a new User object and add it to our list
		User newUser = new User(firstName, lastName, pin, this);
		this.users.add(newUser);

		// Create a savings account for the user and add to User and Bank
		// account lists
		Account newAccount = new Account("Savings", newUser, this);
		newUser.addAccount(newAccount);
		this.addAccount(newAccount);

		return newUser;
	}

	/**
	 * Get the User object associated with a particular userID and PIN if they
	 * are valid
	 * 
	 * @param userID
	 *            The UUID of the user to log in
	 * @param pin
	 *            The PIN of the user
	 * @return The User object, if the login is successful, or null otherwise
	 */
	public User userLogin(String userID, String pin) {

		// Search through the list of users
		for (User u : this.users) {

			// Check if user ID is correct
			if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
				return u;
			}
		}
		// If the user wasn't found or the PIN was incorrect
		return null;
	}

	/**
	 * Returns the name of the bank
	 * 
	 * @return The name of the bank
	 */
	public String getName() {
		return this.name;
	}
}