import java.util.Scanner;

public class ATM {

	public static void main(String[] args) {

		// Initialize Scanner
		Scanner sc = new Scanner(System.in);

		// / Initialize Bank
		Bank bank = new Bank("Bank of America");

		// Add a user and create savings account
		User user = bank.addUser("John", "Doe", "1234");

		// Add a checking account for our user
		Account newAccount = new Account("Checking", user, bank);
		user.addAccount(newAccount);
		bank.addAccount(newAccount);

		User currentUser;
		while (true) {

			// Stay in the login prompt until successful login
			currentUser = ATM.mainMenuPrompt(bank, sc);

			// Stay in main menu until user quits
			ATM.printUserMenu(currentUser, sc);
		}
	}

	/**
	 * Print the ATM's login menu
	 * 
	 * @param bank
	 *            The Bank object whose accounts to use
	 * @param sc
	 *            The Scanner object to use for use input
	 * @return
	 */
	private static User mainMenuPrompt(Bank bank, Scanner sc) {
		// Initialize
		String userID;
		String pin;
		User authUser;

		// Prompt the user for user ID/pin combo until a correct on is reached
		do {

			System.out.printf("\n\nWelcome to %s.\n", bank.getName());
			System.out.print("Enter user ID: ");
			userID = sc.nextLine();
			System.out.print("Enter PIN: ");
			pin = sc.nextLine();

			// Try to get the user object corresponding to the ID/pin combo
			authUser = bank.userLogin(userID, pin);
			if (authUser == null) {
				System.out.println("Incorrect user ID/PIN combination. "
						+ "Please try again.");
			}

		} while (authUser == null); // Loop until successful login

		return authUser;
	}

	/**
	 * Prints the main menu once a valid customer has logged in
	 * 
	 * @param user
	 *            The logged-in User object
	 * @param sc
	 *            The Scanner object used for user input
	 */
	public static void printUserMenu(User user, Scanner sc) {

		// Print a summary of the user's accounts
		user.printAccountsSummary();

		// Initialize
		int choice;

		// User menu
		do {
			System.out.printf("Welcome %s, what would you like to do?\n",
					user.getFirstName());
			System.out.println("  1) Show account transaction history");
			System.out.println("  2) Withdraw");
			System.out.println("  3) Deposit");
			System.out.println("  4) Transfer");
			System.out.println("  5) Quit\n");
			System.out.print("Enter choice: ");
			choice = sc.nextInt();

			if (choice < 1 || choice > 5) {
				System.out.println("Invalid choice. Please choose 1-5\n");
			}
		} while (choice < 1 || choice > 5);

		// Process the choice
		switch (choice) {

		case 1:
			ATM.showTransHistory(user, sc);
			break;
		case 2:
			ATM.withdrawFunds(user, sc);
			break;
		case 3:
			ATM.depositFunds(user, sc);
			break;
		case 4:
			ATM.transferFunds(user, sc);
			break;
		case 5:
			// Get the rest of the unused input
			sc.nextLine();
		}

		// Redisplay the menu unit the user wants to quit
		if (choice != 5) {
			ATM.printUserMenu(user, sc);
		}
	}

	/**
	 * Process a fund deposit to an account
	 * 
	 * @param user
	 *            The logged-in User object
	 * @param sc
	 *            The Scanner object used for user input
	 */
	private static void depositFunds(User user, Scanner sc) {

		// Initialize
		int toAccount;
		double amount;
		String memo;

		// Get the account to deposit to
		do {
			System.out.printf("Enter the number (1-%d) of the account "
					+ "to deposit to: ", user.numAccounts());

			toAccount = sc.nextInt() - 1;
			if (toAccount < 0 || toAccount >= user.numAccounts()) {
				System.out.println("Invalid choice. Please try again.\n");
			}
		} while (toAccount < 0 || toAccount >= user.numAccounts());

		// Get the amount to withdraw
		do {
			System.out.print("Enter the amount to deposit: $");

			amount = sc.nextDouble();
			if (amount < 0) {
				System.out.println("Amount must be greater than zero.\n");
			}
		} while (amount < 0);

		// Get the rest of the unused input
		sc.nextLine();

		// Add a memo
		System.out.print("Enter a memo: ");
		memo = sc.nextLine();

		// Finally, do the withdrawal
		user.addAcctTransaction(toAccount, amount, memo);
	}

	/**
	 * Process a fund withdraw from an account
	 * 
	 * @param user
	 *            The logged-in User object
	 * @param sc
	 *            The Scanner object used for user input
	 */
	private static void withdrawFunds(User user, Scanner sc) {

		// Initialize
		int fromAccount;
		double amount;
		double accountBalance;
		String memo;

		// Get the account to withdraw from
		do {
			System.out.printf("Enter the number (1-%d) of the account "
					+ "to withdraw from ", user.numAccounts());

			fromAccount = sc.nextInt() - 1;
			if (fromAccount < 0 || fromAccount >= user.numAccounts()) {
				System.out.println("Invalid choice. Please try again.\n");
			}
		} while (fromAccount < 0 || fromAccount >= user.numAccounts());

		accountBalance = user.getAccountBalance(fromAccount);

		// Get the amount to withdraw
		do {
			System.out.printf("Enter the amount to withdraw (max $%.02f): $",
					accountBalance);

			amount = sc.nextDouble();
			if (amount < 0) {
				System.out.println("Amount must be greater than zero.\n");

			} else if (amount > accountBalance) {
				System.out.printf("Amount must not be greater than "
						+ "the balance of $%.02f.\n\n", accountBalance);
			}
		} while (amount < 0 || amount > accountBalance);

		// Get the rest of the previous line
		sc.nextLine();

		// Add a memo
		System.out.print("Enter a memo: ");
		memo = sc.nextLine();

		// Finally, do the withdrawal
		user.addAcctTransaction(fromAccount, -1 * amount, memo);
	}

	/**
	 * Shows the transaction history for an account
	 * 
	 * @param user
	 *            The logged-in User object
	 * @param sc
	 *            The Scanner object used for user input
	 */
	private static void showTransHistory(User user, Scanner sc) {

		int account;

		// Get the account whose transaction history to look at
		do {
			System.out.printf("Enter the number (1-%d) of the account\n"
					+ "whose transactions you want to see: ",
					user.numAccounts());

			account = sc.nextInt() - 1;
			if (account < 0 || account >= user.numAccounts()) {
				System.out.println("Invalid account. Please try again.\n");
			}
		} while (account < 0 || account >= user.numAccounts());

		// Print the transaction history
		user.printAccountSummary(account);
	}

	/**
	 * Process transferring funds from one account to another
	 * 
	 * @param user
	 *            The logged-in User object
	 * @param sc
	 *            The Scanner object used for user input
	 */
	public static void transferFunds(User user, Scanner sc) {

		// Initialize
		int fromAccount;
		int toAccount;
		double amount;
		double accountBalance;

		// Get the account to transfer from
		do {
			System.out.printf("Enter the number (1-%d) of the account "
					+ "to transer from: ", user.numAccounts());

			fromAccount = sc.nextInt() - 1;
			if (fromAccount < 0 || fromAccount >= user.numAccounts()) {
				System.out.println("Invalid choice. Please try again\n");
			}
		} while (fromAccount < 0 || fromAccount >= user.numAccounts());

		accountBalance = user.getAccountBalance(fromAccount);

		// Get the account to transfer to
		do {
			System.out.printf("Enter the number (1-%d) of the account "
					+ "to transer to: ", user.numAccounts());

			toAccount = sc.nextInt() - 1;
			if (toAccount < 0 || toAccount >= user.numAccounts()) {
				System.out.println("Invalid choice. Please try again.\n");
			}
		} while (toAccount < 0 || toAccount >= user.numAccounts());

		// Get the amount to transfer
		do {
			System.out.printf("Enter the amount to transfer (max $%.02f): $",
					accountBalance);

			amount = sc.nextDouble();
			if (amount < 0) {
				System.out.println("Amount must be greater than zero.\n");
			}
		} while (amount < 0 || amount > accountBalance);

		// Finally, do the transfer
		user.addAcctTransaction(
				fromAccount,
				-1 * amount,
				String.format("Transfer to account: %s",
						user.getAcctUUID(toAccount)));

		user.addAcctTransaction(
				toAccount,
				amount,
				String.format("Transfer from account: %s",
						user.getAcctUUID(fromAccount)));
	}
}
