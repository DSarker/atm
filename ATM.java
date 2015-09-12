import java.text.DecimalFormat;

public class ATM {

	public static void main(String[] args) {

		// Initialize GUI
		Gui gui = new Gui();

		// / Initialize Bank
		Bank bank = new Bank("Bank of Java");

		// Add a user and create savings account
		User user = bank.addUser("John", "Smith", "1234");

		// Add a checking account for our user
		Account newAccount = new Account("Checking", user, bank);
		user.addAccount(newAccount);
		bank.addAccount(newAccount);

		User currentUser;
		while (true) {

			// Stay in the login prompt until successful login
			currentUser = ATM.mainMenuPrompt(bank, gui);

			// Stay in main menu until user quits
			gui.setMainDisplay("");
			ATM.printUserMenu(currentUser, gui);
		}

	}

	/**
	 * Print the ATM's login menu
	 * 
	 * @param bank
	 *            The Bank object whose accounts to use
	 * @param gui
	 *            The Gui object used for user input
	 * @return Authenticated user
	 */
	private static User mainMenuPrompt(Bank bank, Gui gui) {

		// Initialize
		String userID;
		String pin;
		User authUser;

		// Prompt the user for user ID/pin combo until a correct on is reached
		do {

			// System.out.printf
			gui.appendMainDisplay(String.format("Welcome to %s.\n",
					bank.getName()));
			gui.appendMainDisplay("Enter user ID: ");
			userID = gui.getInput();

			gui.appendMainDisplay("\nEnter PIN: ");

			gui.setPwMasker(true);
			pin = gui.getInput();
			gui.setPwMasker(false);

			// Try to get the user object corresponding to the ID/pin combo
			authUser = bank.userLogin(userID, pin);

			if (authUser == null) {
				gui.setMainDisplay("Incorrect user ID/PIN combination. "
						+ "Please try again.\n\n");
			}

		} while (authUser == null); // Loop until successful login

		return authUser;
	}

	/**
	 * Prints the main menu once a valid customer has logged in
	 * 
	 * @param user
	 *            The logged-in User object
	 * @param gui
	 *            The Gui object used for user input
	 */
	private static void printUserMenu(User user, Gui gui) {

		// Print a summary of the user's accounts
		user.printAccountsSummary(gui);

		// Initialize
		int choice;

		// User menu
		do {
			gui.appendMainDisplay(String.format(
					"\nWelcome %s, what would you like to do?\n",
					user.getFirstName()));
			gui.appendMainDisplay("   1) Show account transaction history\n");
			gui.appendMainDisplay("   2) Withdraw\n");
			gui.appendMainDisplay("   3) Deposit\n");
			gui.appendMainDisplay("   4) Transfer\n");
			gui.appendMainDisplay("   5) Quit\n");

			choice = Integer.parseInt(gui.getInput());

			if (choice < 1 || choice > 5) {
				gui.setMainDisplay("Invalid choice. Please choose 1-5\n\n");
			}
		} while (choice < 1 || choice > 5);

		// Process the choice
		switch (choice) {

		case 1:
			ATM.showTransHistory(user, gui);
			break;
		case 2:
			ATM.withdrawFunds(user, gui);
			gui.setMainDisplay("");
			break;
		case 3:
			ATM.depositFunds(user, gui);
			gui.setMainDisplay("");
			break;
		case 4:
			ATM.transferFunds(user, gui);
			gui.setMainDisplay("");
			break;
		case 5:
			gui.setMainDisplay("");
		}

		// Redisplay the menu unit the user wants to quit
		if (choice != 5) {
			ATM.printUserMenu(user, gui);
		}
	}

	/**
	 * Process a fund deposit to an account
	 * 
	 * @param user
	 *            The logged-in User object
	 * @param gui
	 *            The Gui object used for user input
	 */
	private static void depositFunds(User user, Gui gui) {

		// Initialize
		int toAccount;
		double amount;
		DecimalFormat df = new DecimalFormat("#.00");

		gui.setMainDisplay("");

		// Get the account to deposit to
		do {
			gui.appendMainDisplay(String.format(
					"Enter the number (1-%d) of the account "
							+ "to deposit to: ", user.numAccounts()));

			toAccount = Integer.parseInt(gui.getInput()) - 1;
			if (toAccount < 0 || toAccount >= user.numAccounts()) {
				gui.setMainDisplay("Invalid choice. Please try again.\n\n");
			}
		} while (toAccount < 0 || toAccount >= user.numAccounts());

		// Get the amount to deposit
		do {
			gui.appendMainDisplay("\nEnter the amount to deposit: $");

			gui.setDecimal(true);
			amount = Double.parseDouble(gui.getInput());
			gui.setDecimal(false);

			if (amount < 0.01) {
				gui.setMainDisplay("Amount must be greater than zero.\n");
			}
		} while (amount < 0.01);

		// Finally, do the deposit
		user.addAcctTransaction(toAccount, amount, "Deposit");
		gui.setMainDisplay("");
	}

	/**
	 * Process a fund withdraw from an account
	 * 
	 * @param user
	 *            The logged-in User object
	 * @param gui
	 *            The Gui object used for user input
	 */
	private static void withdrawFunds(User user, Gui gui) {

		// Initialize
		int fromAccount;
		double amount;
		double accountBalance;
		String memo;

		gui.setMainDisplay("");

		// Get the account to withdraw from
		do {
			gui.appendMainDisplay(String.format(
					"Enter the number (1-%d) of the account "
							+ "to withdraw from: ", user.numAccounts()));

			fromAccount = Integer.parseInt(gui.getInput()) - 1;
			if (fromAccount < 0 || fromAccount >= user.numAccounts()) {
				gui.setMainDisplay("Invalid choice. Please try again.\n\n");

			} else if (user.getAccountBalance(fromAccount) < 0.01) {
				gui.setMainDisplay("Insufficient funds in that account.\n\n");
				printUserMenu(user, gui);
			}

		} while (fromAccount < 0 || fromAccount >= user.numAccounts());

		accountBalance = user.getAccountBalance(fromAccount);

		// Get the amount to withdraw
		do {
			gui.appendMainDisplay(String.format(
					"\nEnter the amount to withdraw (max $%.02f): $",
					accountBalance));

			gui.setDecimal(true);
			amount = Double.parseDouble(gui.getInput());
			gui.setDecimal(false);

			if (amount < 0.01) {
				gui.setMainDisplay("Amount must be greater than zero.\\nn");

			} else if (amount > accountBalance) {
				gui.setMainDisplay(String.format(
						"Amount must not be greater than "
								+ "the balance of $%.02f.\n", accountBalance));
			}
		} while (amount < 0.01 || amount > accountBalance);

		// Finally, do the withdrawal
		user.addAcctTransaction(fromAccount, -1 * amount, "Withdrawal");
	}

	/**
	 * Shows the transaction history for an account
	 * 
	 * @param user
	 *            The logged-in User object
	 * @param gui
	 *            The Gui object used for user input
	 */
	private static void showTransHistory(User user, Gui gui) {

		int account;

		// Get the account whose transaction history to look at
		do {
			gui.setMainDisplay(String.format(
					"Enter the number (1-%d) of the account "
							+ "whose transactions you want to see: ",
					user.numAccounts()));

			account = Integer.parseInt(gui.getInput()) - 1;
			if (account < 0 || account >= user.numAccounts()) {
				gui.appendMainDisplay("Invalid account. Please try again.\n\n");
			}
		} while (account < 0 || account >= user.numAccounts());

		// Print the transaction history
		user.printAccountSummary(account, gui);
	}

	/**
	 * Process transferring funds from one account to another
	 * 
	 * @param user
	 *            The logged-in User object
	 * @param gui
	 *            The Gui object used for user input
	 */
	private static void transferFunds(User user, Gui gui) {

		// Initialize
		int fromAccount;
		int toAccount;
		double amount;
		double accountBalance;

		gui.setMainDisplay("");

		// Get the account to transfer from
		do {
			gui.appendMainDisplay(String.format(
					"Enter the number (1-%d) of the account "
							+ "to transer from: ", user.numAccounts()));

			fromAccount = Integer.parseInt(gui.getInput()) - 1;
			if (fromAccount < 0 || fromAccount >= user.numAccounts()) {
				gui.setMainDisplay("Invalid choice. Please try again\n\n");

			} else if (user.getAccountBalance(fromAccount) < 0.01) {
				gui.setMainDisplay("Insufficient funds in that account.\n\n");
				printUserMenu(user, gui);
			}
		} while (fromAccount < 0 || fromAccount >= user.numAccounts());

		accountBalance = user.getAccountBalance(fromAccount);

		// Get the account to transfer to
		do {
			gui.appendMainDisplay(String.format(
					"\nEnter the number (1-%d) of the account "
							+ "to transer to: ", user.numAccounts()));

			toAccount = Integer.parseInt(gui.getInput()) - 1;
			if (toAccount < 0 || toAccount >= user.numAccounts()) {
				gui.setMainDisplay("Invalid choice. Please try again.\n");
			}
		} while (toAccount < 0 || toAccount >= user.numAccounts());

		// Get the amount to transfer
		do {
			gui.appendMainDisplay(String.format(
					"\nEnter the amount to transfer (max $%.02f): $",
					accountBalance));

			gui.setDecimal(true);
			amount = Double.parseDouble(gui.getInput());
			gui.setDecimal(false);

			if (amount < 0.01) {
				gui.setMainDisplay("Amount must be greater than zero.\n");

			} else if (amount > accountBalance) {
				gui.setMainDisplay(String.format(
						"Amount must not be greater than "
								+ "the balance of $%.02f.\n", accountBalance));
			}
		} while (amount < 0.01 || amount > accountBalance);

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